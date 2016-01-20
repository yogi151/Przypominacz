package com.example.maciapek.przypominacz.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.annotation.SuppressLint;

import com.example.maciapek.przypominacz.CacheList;
import com.example.maciapek.przypominacz.ObservedChannelList;
import com.example.maciapek.przypominacz.ObservedFilmList;
import com.example.maciapek.przypominacz.config.Config;
import com.example.maciapek.przypominacz.config.Connection;
import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.model.Channel;
import com.example.maciapek.przypominacz.model.Film;
import com.example.maciapek.przypominacz.model.Remind;
import com.example.maciapek.przypominacz.model.Series;

public class ContentAnalyzer {

    // Adres strony, kt�rej zawarto�� b�dzie analizowana
    private URL url;

    // Typ zapytania: film, serial
    private String queryType = null;

    private static final Pattern RE_FILM_ID = Pattern.compile("=\\{filmId:(\\d+)");
    
    private static final Pattern RE_SERIES_ID = Pattern.compile("=\\{filmId:(\\d+)");

    private static final Pattern RE_FILM_DATA = Pattern.compile("<a href=\"([^\"]*)\" class=\"hdr hdr-medium hitTitle\"");

    private static final Pattern RE_SERIES_DATA = Pattern.compile("<a href=\"([^\"]*)\" class=\"hdr hdr-medium hitTitle\">");

    public ContentAnalyzer() {
        url = null;
    }

    /**
     * Ustawianie adresu strony na podstawie przekazanych parametr�w
     * @param params Parametry - wymagane: title (tytu�), opcja: year (rok produkcji)
     * @return Powodzenie / niepowodzenie
     */
    private boolean setUrlStr(HashMap<String, ?> params) {

        if (this.queryType == null) {
            return false;
        }

        StringBuilder urlStr = new StringBuilder(Config.WWW);
        urlStr.append("/search/");
        urlStr.append(queryType);
        urlStr.append("?q=");

        String title;
        try {
            title = URLEncoder.encode((String)params.get("title"), "UTF-8");
            urlStr.append(title);
        } catch (UnsupportedEncodingException e) {
            return false;
        }

        Integer year = (Integer)params.get("year");
        if (year != null) {
            urlStr.append("&startYear=");
            urlStr.append(year);
            urlStr.append("&endYear=");
            urlStr.append(year);
        }
        try {
            this.url = new URL(urlStr.toString());
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    /**
     * Lista film�w, kt�rych tytu� zawiera dany ci�g znak�w
     * @param params
     *      1. (String) Ci�g znak�w zawarty w tytule,
     *      2. (int) Rok produkcji - opcjonalnie
     * @return Lista film�w
     */
    public ArrayList<Film> getFilmList(Object... params) {
        this.queryType = "film";
        HashMap<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put("title", (String)params[0]);
        if (params.length == 2) urlParams.put("year", (Integer)params[1]);
        return this.setUrlStr(urlParams) ? this.getFilmList() : new ArrayList<Film>();
    }
    
    /**
     * Lista serial�w, kt�rych tytu� zawiera dany ci�g znak�w
     * @param params
     *      1. (String) Ci�g znak�w zawarty w tytule,
     *      2. (int) Rok produkcji - opcjonalnie
     * @return Lista serial�w
     */
    public ArrayList<Series> getSeriesList(Object... params) {
        this.queryType = "serial";
        HashMap<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put("title", (String)params[0]);
        if (params.length == 2) urlParams.put("year", (Integer)params[1]);
        return this.setUrlStr(urlParams) ? this.getSeriesList() : new ArrayList<Series>();
    }

    /**
     * Pobieranie kodu HTML strony o danym adresie
     * @param url Adres strony
     * @param desc Opis strony
     */
    private String getHtmlCode(URL url, String desc) {

        desc = desc != null ? desc+": " : "";

        StringBuilder html = new StringBuilder();
        try {
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));

            String str;
            while ((str = br.readLine()) != null) {
                html.append(str);
            }
            br.close();
        } catch (MalformedURLException e) {
            desc += "Nieprawid�owy adres URL.";
        }
        catch (IOException e) {
            desc += "B��d odczytu danych filmu.";
        }
        return html.toString();
    }

    /**
     * Lista film�w dla ustawionych wcze�niej parametr�w
     * @see public ArrayList<Film> getFilmList(String title, int year)
     * @see public ArrayList<Film> getFilmList(String title)
     * @return Lista film�w
     */
    private ArrayList<Film> getFilmList() {

        String html = getHtmlCode(this.url, "Lista film�w");

        List<String> allMatches = new ArrayList<String>();

        final Pattern exp = RE_FILM_DATA;
        
        Matcher matcher = exp.matcher(html);
        while (matcher.find()) {
            allMatches.add(matcher.group(1));
        }

        Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        ArrayList<Film> filmList = new ArrayList<Film>();
        for (String i : allMatches) {
            URL filmUrl;
            try {
                filmUrl = new URL(Config.WWW + i);
            } catch (MalformedURLException e) {
                filmUrl = null;
            }

            Film film = new Film(api);
            film.setFilmUrl(filmUrl);
            film = getFilmData(film);
            if(CacheList.getFilm(film.getId()) != null) {
            	film = CacheList.getFilm(film.getId());
            } else {
            	film.setFilmData();
            	CacheList.setFilm(film);
            }
            filmList.add(film);
        }
        return filmList;
    }

    /**
     * Pobranie ze strony filmu podstawowych informacji
     * @param film
     * @return Film z uzupe�nionym ID
     */
    private Film getFilmData(Film film) {

        String html = getHtmlCode(film.getFilmUrl(), "Film");
        Matcher matcher = RE_FILM_ID.matcher(html);
        if (matcher.find()) {
            try {
                int id = Integer.parseInt(matcher.group(1));
                film.setId(id);
            }
            catch (NumberFormatException e) {
            }
        }
        return film;
    }
    
    /**
     * Lista seriali dla ustawionych wcze�niej parametr�w
     * @see public ArrayList<Series> getSeriesList(String title, int year)
     * @see public ArrayList<Series> getSeriesList(String title)
     * @return Lista seriali
     */
    private ArrayList<Series> getSeriesList() {

        String html = getHtmlCode(this.url, "Lista seriali");

        List<String> allMatches = new ArrayList<String>();

        final Pattern exp;
        exp = RE_SERIES_DATA;
        
        Matcher matcher = exp.matcher(html);
        while (matcher.find()) {
            allMatches.add(matcher.group(1));
        }

        Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        ArrayList<Series> seriesList = new ArrayList<Series>();
        for (String i : allMatches) {
            URL filmUrl;
            try {
                filmUrl = new URL(Config.WWW + i);
            } catch (MalformedURLException e) {
                filmUrl = null;
            }

            Series series = new Series(api);
            series.setFilmUrl(filmUrl);
            series = getSeriesData(series);
            if(CacheList.getFilm(series.getId()) != null) {
            	series = (Series)CacheList.getFilm(series.getId());
            } else {
            	series.setSeriesData();
            	CacheList.setFilm(series);
            }
            seriesList.add(series);
        }
        return seriesList;
    }
    
    /**
     * Pobranie ze strony serialu podstawowych informacji
     * @param series
     * @return series z uzupe�nionym ID
     */
    private Series getSeriesData(Series series) {

        String html = getHtmlCode(series.getFilmUrl(), "Serial");
        Matcher matcher = RE_SERIES_ID.matcher(html);
        if (matcher.find()) {
            try {
                int id = Integer.parseInt(matcher.group(1));
                series.setId(id);
            }
            catch (NumberFormatException e) {
            }
        }
        return series;
    }
    
   /* public ArrayList<Film> getPopularFilms() {
    	Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        return api.getPopularFilms();
    }*/
    
    public ArrayList<Film> getUpcommingFilms() {
    	Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        return api.getUpcommingFilms();
    }
    public ArrayList<Film> getObservedFilms() {
        ArrayList<Film> observed = new ArrayList<Film>();
        for(Film film : ObservedFilmList.getObserved()) {
            if(film.getFilmType() == Type.FILM.getCode()) {
                observed.add(film);
            }
        }
        return observed;
    }
    public ArrayList<Film> getPopularFilms() {
        Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        ArrayList<Film> popular = new ArrayList<Film>();
        for(Film film : api.getPopularFilms()) {
            if(film.getFilmType()!= null &&  film.getFilmType()== Type.FILM.getCode()) {
                popular.add(film);
            }
        }
        return popular;
    }

    public ArrayList<Film> getPopularSeries() {
        Connection conn = new Connection();
        FilmwebApiHelper api = new FilmwebApiHelper(conn);
        ArrayList<Film> popular = new ArrayList<Film>();
        for(Film film : api.getPopularFilms()) {
            if(film.getFilmType()!= null &&  film.getFilmType()== Type.SERIES.getCode()) {
                popular.add(film);
            }
        }
        return popular;
    }
    public ArrayList<Film> getObservedSeries() {
        ArrayList<Film> observed = new ArrayList<Film>();
        for(Film film : ObservedFilmList.getObserved()) {
            if(film.getFilmType() == Type.SERIES.getCode()) {
                observed.add(film);
            }
        }
        return observed;
    }
    public ArrayList<Channel> getObservedChannels() {
        return ObservedChannelList.getObserved();
    }
    public ArrayList<Channel> getChannels() {
    	Connection conn = new Connection();
    	FilmwebApiHelper api = new FilmwebApiHelper(conn);
    	return api.getChannels();
    }
    
    public List<Remind> getFilmsNearestBroadcasts(int filmId, Type type) {
    	Connection conn = new Connection();
    	FilmwebApiHelper api = new FilmwebApiHelper(conn);
    	return api.getFilmsNearestBroadcasts(filmId, type);
    }
    
    
   /* public ArrayList<Film> getObservedFilms() {
    	return ObservedFilmList.getObserved();
    }*/
 
    @SuppressLint("SimpleDateFormat")
	public Series setNextEpisodeData(Series series) {
    	String html = getHtmlCode(series.getFilmUrl(), "Serial");

    	Document doc = Jsoup.parse(html);
    	if(doc.getElementsByClass("episodeDate").size() > 0) {
    		Element e = doc.getElementsByClass("episodeDate").get(0);

    		String [] str  = e.text().split(" ");
    		String tittle = "";
    		for(int i = 0; i < str.length; i++) {
    			if(str[i].length() == 6 && str[i].startsWith("s") && str[i].getBytes()[3] == 'e') {
    				for(int j = i; j < str.length; j ++) {
    					tittle += str[j] + " ";
    				}
    				break;
    			}
    		}
    		series.setNextEpisode(tittle);
    		String date = e.data().substring(22, 32);
    		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		try {
    			series.setNextEpisodeDate(formatter.parse(date));
    		} catch (ParseException e1) {
    		}    	
    		System.out.println(tittle);
    		System.out.println(date);
    	}
    	return series;
    }
}

