package com.example.maciapek.przypominacz.model;

import java.util.Date;

import com.example.maciapek.przypominacz.api.FilmwebApiHelper;
import com.example.maciapek.przypominacz.enums.Type;

public class Series extends Film {

	// Ilo�� odcink�w
    private int episodesCount = 0;
    
    // Ilo�� sezon�w
    private int seasonsCount = 0;
    
    // nast�pny odcinek
    private String nextEpisode = "";
    
    // data nast�pnego odcinka
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