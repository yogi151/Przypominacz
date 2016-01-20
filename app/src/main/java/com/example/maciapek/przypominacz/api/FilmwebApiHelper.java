package com.example.maciapek.przypominacz.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;

import com.example.maciapek.przypominacz.CacheList;
import com.example.maciapek.przypominacz.ObservedFilmList;
import com.example.maciapek.przypominacz.config.Config;
import com.example.maciapek.przypominacz.config.Connection;
import com.example.maciapek.przypominacz.enums.Profession;
import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.model.Channel;
import com.example.maciapek.przypominacz.model.Film;
import com.example.maciapek.przypominacz.model.Person;
import com.example.maciapek.przypominacz.model.Remind;
import com.example.maciapek.przypominacz.model.Series;

public class FilmwebApiHelper {

	private Connection conn = null;

    public FilmwebApiHelper(Connection conn) {
        this.conn = conn;
    }

    /**
     * Lista os�b danej profesji z danego filmu
     * @param filmId ID filmu
     * @param profession Nazwa profesji
     * @return Lista os�b
     */
    @SuppressWarnings("unchecked")
	public ArrayList<Person> getFilmPersons(int filmId, Profession profession) {
        if (filmId <= 0) return null;

        int pageNo = 0;
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> partPersonList = new ArrayList<Person>();
        try {
            do {
                if (conn.setMethod("getFilmPersons [" + filmId + "," + profession.getCode() 
                		+ "," + 50 * pageNo + "," + 50 * (1 + pageNo) + "]")) {
                    partPersonList = (ArrayList<Person>)conn.prepareResponse();
                    if (partPersonList.size() > 0) {
                        personList.addAll(partPersonList);
                    }
                    pageNo++;
                }
            } while(partPersonList.size() == 50);
        }
        catch (NullPointerException e) {
        }
        return personList;
    }

    /**
     * Opis filmu
     * @param filmId ID filmu
     * @return Opis filmu
     */
    public String getFilmDescription(int filmId) { 
        if (filmId <= 0) return null;
        String desc = "";
        if (conn.setMethod("getFilmDescription ["+filmId+"]")) {
            try {
                desc = conn.prepareResponse().toString();
            }
            catch(NullPointerException e) {
                desc = null;
            }
        }
        return desc;
    }
    
    @SuppressWarnings("deprecation")
	public List<Remind> getFilmsNearestBroadcasts(int filmId, Type type) {
        if (filmId <= 0) return null;
        List<Remind> remindList = new ArrayList<Remind>();
        if(type == Type.FILM) {
        	if (conn.setMethod("getFilmsNearestBroadcasts [["+filmId+",0,100]]")) {
        		try {
        			String response = conn.getResponse();
        			JSONArray entry = getJSONArray(response);
        			for(int i = 0; i < entry.length(); i++) {
        				try {
        					JSONArray remindEntry = entry.getJSONArray(i);
        					Remind remind = new Remind();
        					remind.setFilmId(remindEntry.getInt(0));
        					remind.setChannelId(remindEntry.getInt(1));
        				
        					String time = remindEntry.getString(2);
        					Calendar cal = Calendar.getInstance();
        					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, time.indexOf(":"))));
        					cal.set(Calendar.MINUTE, Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length())));
        				
        					String date = remindEntry.getString(3);
        					cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, date.indexOf("-"))));
        					cal.set(Calendar.MONTH, Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"))));
        					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(date.lastIndexOf("-") + 1, date.length())));
        				
        					remind.setTime(cal);        				
        					remindList.add(remind);        				
        				} catch (JSONException e) {
        					e.printStackTrace();
        				}
        			}	
        		}
        		catch(NullPointerException e) {
            	}
        	}
        } else {
        	Series series = (Series) ObservedFilmList.getFilm(filmId);
        	series.setNextEpisodeDate(null);
        	if(series.getNextEpisodeDate() != null) {
    		Remind remind = new Remind();
    			remind.setFilmId(series.getId());
    			Calendar cal = Calendar.getInstance();
    			cal.clear();
    			cal.set(Calendar.DAY_OF_MONTH, series.getNextEpisodeDate().getDate());
    			cal.set(Calendar.MONTH, series.getNextEpisodeDate().getMonth() + 1);
    			cal.set(Calendar.YEAR, series.getNextEpisodeDate().getYear() + 1900);
    			remind.setTime(cal);
    			remindList.add(remind);
    		}
        }
        return remindList;
    }
    
    /**
     * Recenzja
     * @param filmId ID filmu
     * @return Recenzja
     */
    public String getFilmReview(int filmId) {
        if (filmId <= 0) return null;
        String desc = "";
        if (conn.setMethod("getFilmReview ["+filmId+"]")) {
            try {
                desc = conn.prepareResponse().toString();
            }
            catch(NullPointerException e) {
                desc = null;
            }
        }
        return desc;
    }

    public ArrayList<Film> getPopularFilms() {
    	conn.setMethod("getPopularFilms");
    	ArrayList<Film> popularFilmList = new ArrayList<Film>();
    	String response = conn.getResponse();
    	JSONArray entry = getJSONArray(response);
    	
    	for(int i = 0; i < entry.length(); i++) {
    		try {
				JSONArray filmEntry = entry.getJSONArray(i);
				Film film = new Film(new FilmwebApiHelper(conn));
				Integer id = Integer.valueOf(filmEntry.getInt(6));
				if(CacheList.getFilm(id) != null) {
					film = CacheList.getFilm(id);
				} else {
					film.setTitle(filmEntry.getString(0));
					film.setYear(Integer.valueOf(filmEntry.getInt(1)));
					film.setRate(filmEntry.optDouble(2, 0.0d));
					film.setVotes(filmEntry.optInt(3, 0));
					film.setDuration(filmEntry.isNull(4) ? null : Integer.valueOf(filmEntry.getInt(4)));
					film.setCoverUrl(filmEntry.isNull(5) ? null : getURL(Config.URL_POSTER + filmEntry.getString(5)));
					film.setId(id);
					try {
						film.setFilmData();
						CacheList.setFilm(film);
					} catch (NullPointerException e) {

					}
				}
				popularFilmList.add(film);
			} catch (JSONException e) {
				e.printStackTrace();
			}
    	}
    	return popularFilmList;
    }
    
    @SuppressLint("SimpleDateFormat")
	public ArrayList<Film> getUpcommingFilms() {
    	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	conn.setMethod("getUpcommingFilms [" + date + "])");
    	ArrayList<Film> upcommingFilmList = new ArrayList<Film>();
    	String response = conn.getResponse();
    	JSONArray entry = getJSONArray(response);
    	
    	try {
    		for(int i = 0; i < entry.length(); i++) {
    			JSONArray weekEntry = entry.getJSONArray(i);
    			Date weekDate = getDateFromString(weekEntry.getString(0));
    			JSONArray movieArray = weekEntry.getJSONArray(1);
    			
    			for(int j = 0; j < movieArray.length(); j++) {
    				JSONArray filmEntry = movieArray.getJSONArray(j);
    				Film film = new Film(new FilmwebApiHelper(conn));
    				Integer id = Integer.valueOf(filmEntry.getInt(0));
    				if(CacheList.getFilm(id) != null) {
    					film = CacheList.getFilm(id);
    				} else {
    					film.setId(id);
    					film.setPremiereCountry(weekDate);
    					film.setTitle(filmEntry.getString(1));
    					film.setYear(Integer.valueOf(filmEntry.getInt(2)));
    					film.setCoverUrl(filmEntry.isNull(3) ? null : getURL(Config.URL_POSTER + filmEntry.getString(3)));
    					CacheList.setFilm(film);
    				}
    				upcommingFilmList.add(film);
    			}
    		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	catch (NullPointerException e) {
			e.printStackTrace();
		}
    	return upcommingFilmList;
    }
    
    public ArrayList<Channel> getChannels() {
    	conn.setMethod("getAllChannels [1]");
    	ArrayList<Channel> channelList = new ArrayList<Channel>();
    	String response = conn.getResponse();
    	JSONArray entry = getJSONArray(response);
    	
    	for(int i = 0; i < entry.length(); i++) {
    		try {
				JSONArray channelEntry = entry.getJSONArray(i);
				Channel channel = new Channel();
				channel.setId(Integer.valueOf(channelEntry.getInt(0)));
				channel.setName(channelEntry.getString(1));
				channel.setLogoURL(channelEntry.isNull(2) ? null : getURL(Config.URL_CHANNEL + channelEntry.getString(2)));
				channelList.add(channel);
			} catch (JSONException e) {
				e.printStackTrace();
			}
    	}
    	return channelList;
    }
    
    /**
     * Ustawienie informacji nt filmu/serialu
     */
    public void getFilmData(Film film) {
        if (conn.setMethod("getFilmInfoFull ["+film.getId()+"]")) {
            String response = conn.getResponse();
            
            /*
             * 0 - tytu�
             * 1 - oryginalny tytu�
             * 2 - �rednia ocen
             * 3 - liczba ocen
             * 4 - gatunki
             * 5 - rok
             * 6 - czas trwania
             * 7 - liczba komentarzy
             * 8 - adres forum 
             * 9 - czy ma recenzje
             * 10 - czy ma opis
             * 11 - �cie�ka obrazka
             * 12 - tablica video
             * 13 - data premieri na �wiecie
             * 14 - data premiery w kraju
             * 15 - rodzaj filmu (0 - film, 1 - serial, 2 - gra)
             * 16 - liczba sezon�w
             * 17 - liczba odcink�w
             * 18 - lista kraj�w
             * 19 - streszczenie
             */
            
            JSONArray entry = getJSONArray(response);
            
            //tytu� polski
            film.setPolishTitle(getString(entry, 0));
            
            //tytu� oryginalny
            film.setTitle(entry.isNull(1) ? null : entry.optString(1, ""));
            
            //�rednia ocena
            film.setRate(entry.optDouble(2, 0.0d));            

            //liczba g�os�w
            film.setVotes(entry.optInt(3, 0));
           
            //gatunek
            film.setGenre(entry.isNull(4) ? null : entry.optString(4, "").split(","));
            
            //rok produkcji 
            film.setYear(entry.isNull(5) ? null : Integer.valueOf(getInteger(entry, 5)));

            // czas trwania
            film.setDuration(entry.isNull(6) ? null : Integer.valueOf(getInteger(entry, 6)));
            
            // liczba komentarzy
            film.setCommentsCount(entry.optInt(7, 0));
            
            // forum url
            String data = entry.isNull(8) ? null : entry.optString(8);
            if (data != null) {
                film.setForumUrl(getURL(data));
            }
            
            // czy posiada recenzje
            film.setHasReview(entry.optInt(9, 0) > 0);
            
            // czy posiada opis
            film.setHasDescription(entry.optInt(10, 0) > 0);
          
            // ok�adka
            data = entry.isNull(11) ? null : entry.optString(11);
            if (data != null) {
                String urlStr = Config.URL_POSTER + data;
                film.setCoverUrl(getURL(urlStr));
            }
            
            // data premiery na �wiecie
            film.setPremiereWorld(entry.isNull(13) ? null : getDateFromString(getString(entry, 13)));
            
            // data premiery w Polsce
            film.setPremiereCountry(entry.isNull(14) ? null : getDateFromString(getString(entry, 14)));
            
            // typ multimediu: 0 - film, 1 - serial, 2 - gra
            film.setFilmType(entry.optInt(15, 0));

            // kraj produkcji
            film.setCountries(entry.isNull(18) ? null : getString(entry, 18));

            // streszczenie
            film.setSynopsis(entry.isNull(19) ? null : getString(entry, 19).trim());

            //serial posiada kilka dodatkowych p�l
            if (film.getFilmType() == 1) {
				try{
				Series series = (Series) film;

				//liczba sezon�w
				series.setSeasonsCount(entry.optInt(16, 0));

				//liczba odcink�w
				series.setEpisodesCount(entry.optInt(17, 0));
				CacheList.setFilm(series);
			} catch (ClassCastException e) {

	}
            } else {
            	CacheList.setFilm(film);
            }
        }
    }
    
    @SuppressLint("SimpleDateFormat")
	public Date getDateFromString(String text) {
    	System.out.println("TL : " + text);
    	Date temp;
		try {
			temp = new SimpleDateFormat("yyyy-MM-dd").parse(text);
			return temp;
		} catch (ParseException e) {
			return null;
		}    	
    }
    
    private JSONArray getJSONArray(String response) {
    	 JSONArray entry;
		try {
			entry = new JSONArray(response);
			 return entry;
		} catch (JSONException e) {
			return null;
		}
    }
    
    private String getString(JSONArray entry, int number) {
    	try {
			String s = entry.getString(number);
			return s;
		} catch (JSONException e) {
			return "";
		}  catch (NullPointerException e){
			return "";
		}
    }
    
    private Integer getInteger(JSONArray entry, int number) {
    	try {
			Integer s = entry.getInt(number);
			return s;
		} catch (NumberFormatException e) {
			return null;
		}   	
    	catch (JSONException e) {
			return null;
		}
    }
    
    private URL getURL(String str) {
    	try {
			return new URL(str);
		} catch (MalformedURLException e) {
			return null;
		}
    }
    
    public void setNextEpisodeData(Series series) {
    	ContentAnalyzer ca = new ContentAnalyzer();
    	ca.setNextEpisodeData(series);
    }
}
