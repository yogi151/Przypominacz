package com.example.maciapek.przypominacz.api;

import java.util.ArrayList;
import java.util.List;

import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.model.Channel;
import com.example.maciapek.przypominacz.model.Film;
import com.example.maciapek.przypominacz.model.Remind;
import com.example.maciapek.przypominacz.model.Series;

public class FilmwebApi {
    
    private ContentAnalyzer ca = null;
    
    public FilmwebApi() {
        ca = new ContentAnalyzer();
    }
    
    /**
     * Lista film�w (+ podstawowe informacje) o danym tytule
     * Pozycje posortowane wg trafno�ci (popularno��)
     * @param title Tytu� filmu
     * @return Lista film�w wraz z podstawowymi informacjami
     */
    public ArrayList<Film> getFilmList(String title) {
        return ca.getFilmList(title);
    }
    
    /**
     * Lista film�w (+ podstawowe informacje) o danym tytule i roku produkcji
     * Pozycje posortowane wg trafno�ci (popularno��)
     * @param title Tytu� filmu
     * @param year Rok produkcji
     * @return Lista film�w wraz z podstawowymi informacjami
     */
    public ArrayList<Film> getFilmList(String title, int year) {
        return ca.getFilmList(title, year);
    }

    /**
     * Lista seriali (+ podstawowe informacje) o danym tytule
     * Pozycje posortowane wg trafno�ci (popularno��)
     * @param title Tytu� serialu
     * @return Lista seriali wraz z podstawowymi informacjami
     */
    public ArrayList<Series> getSeriesList(String title) {
        return ca.getSeriesList(title);
    }

    /**
     * Lista seriali (+ podstawowe informacje) o danym tytule i roku produkcji
     * Pozycje posortowane wg trafno�ci (popularno��)
     * @param title Tytu� serialu
     * @param year Rok produkcji
     * @return Lista seriali wraz z podstawowymi informacjami
     */
    public ArrayList<Series> getSeriesList(String title, int year) {
        return ca.getSeriesList(title, year);
    }
    
    public ArrayList<Film> getPopularFilms() {
    	return ca.getPopularFilms();
    }
    
    public ArrayList<Film> getUpcommingFilms() {
    	return ca.getUpcommingFilms();
    }
    
    public ArrayList<Channel> getChannels() {
    	return ca.getChannels();
    }
    public List<Channel> getObservedChannels() {
        return ca.getObservedChannels();
    }
    public ArrayList<Film> getObservedSeries() {
        return ca.getObservedSeries();
    }
    public ArrayList<Film> getPopularSeries() {
                return ca.getPopularSeries();
            }
    public ArrayList<Film> getObservedFilms() {
    	return ca.getObservedFilms();
    }
    
    public List<Remind> getFilmsNearestBroadcasts(int filmId, Type type) {
    	return ca.getFilmsNearestBroadcasts(filmId, type);
    }
}