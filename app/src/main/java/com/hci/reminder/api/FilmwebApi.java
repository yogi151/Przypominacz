package com.hci.reminder.api;

import java.util.ArrayList;
import java.util.List;

import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Channel;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.Remind;
import com.hci.reminder.model.Series;

public class FilmwebApi {
    
    private ContentAnalyzer ca = null;
    
    public FilmwebApi() {
        ca = new ContentAnalyzer();
    }
    
    /**
     * Lista filmów (+ podstawowe informacje) o danym tytule
     * Pozycje posortowane wg trafnoœci (popularnoœæ)
     * @param title Tytu³ filmu
     * @return Lista filmów wraz z podstawowymi informacjami
     */
    public ArrayList<Film> getFilmList(String title) {
        return ca.getFilmList(title);
    }
    
    /**
     * Lista filmów (+ podstawowe informacje) o danym tytule i roku produkcji
     * Pozycje posortowane wg trafnoœci (popularnoœæ)
     * @param title Tytu³ filmu
     * @param year Rok produkcji
     * @return Lista filmów wraz z podstawowymi informacjami
     */
    public ArrayList<Film> getFilmList(String title, int year) {
        return ca.getFilmList(title, year);
    }

    /**
     * Lista seriali (+ podstawowe informacje) o danym tytule
     * Pozycje posortowane wg trafnoœci (popularnoœæ)
     * @param title Tytu³ serialu
     * @return Lista seriali wraz z podstawowymi informacjami
     */
    public ArrayList<Series> getSeriesList(String title) {
        return ca.getSeriesList(title);
    }

    /**
     * Lista seriali (+ podstawowe informacje) o danym tytule i roku produkcji
     * Pozycje posortowane wg trafnoœci (popularnoœæ)
     * @param title Tytu³ serialu
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
    
    public ArrayList<Film> getObservedFilms() {
    	return ca.getObservedFilms();
    }
    
    public List<Remind> getFilmsNearestBroadcasts(int filmId, Type type) {
    	return ca.getFilmsNearestBroadcasts(filmId, type);
    }
}