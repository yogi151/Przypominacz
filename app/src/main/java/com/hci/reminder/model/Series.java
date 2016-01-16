package com.hci.reminder.model;

import java.util.Date;

import com.hci.reminder.api.FilmwebApiHelper;
import com.hci.reminder.enums.Type;

public class Series extends Film {

	// Iloœæ odcinków
    private int episodesCount = 0;
    
    // Iloœæ sezonów
    private int seasonsCount = 0;
    
    // nastêpny odcinek
    private String nextEpisode = "";
    
    // data nastêpnego odcinka
    private Date nextEpisodeDate;

    public Series(FilmwebApiHelper api) {
        super(api);
    }

    public int getEpisodesCount() {
        super.setFilmData();
        return episodesCount;
    }

    public void setEpisodesCount(int episodesCount) {
        this.episodesCount = episodesCount;
    }

    public int getSeasonsCount() {
        super.setFilmData();
        return seasonsCount;
    }

    public void setSeasonsCount(int seasonsCount) {
        this.seasonsCount = seasonsCount;
    }
    
    public void setSeriesData() {
    	setFilmData();
    }
    
    public void setNextEpisode(String nextEpisode) {
		this.nextEpisode = nextEpisode;
	}
    
    public void setNextEpisodeDate(Date nextEpisodeDate) {
		this.nextEpisodeDate = nextEpisodeDate;
	}
    
    public String getNextEpisode() {
    	if(nextEpisode == null || nextEpisode.isEmpty()) {
    		api.setNextEpisodeData(this);
    	}
		return nextEpisode;
	}
    
    public Date getNextEpisodeDate() {
    	if(nextEpisodeDate == null) {
    		api.setNextEpisodeData(this);
    	}
		return nextEpisodeDate;
	}
    
    @Override
    public void convert(FilmTable film) {
    	super.convert(film);
    	if(film.getFilmType() == Type.SERIES.getCode()) {
    		setSeasonsCount(film.getSeasonsCount());
    		setEpisodesCount(film.getEpisodesCount());
    		setNextEpisode(film.getNextEpisode());
    		setNextEpisodeDate(film.getNextEpisodeDate());
    	}
    }
}