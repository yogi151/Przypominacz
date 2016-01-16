package com.hci.reminder.model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import com.hci.reminder.enums.Type;

public class FilmTable implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
    private String title = null;
    private String polishTitle = null;
    private Integer year = null;
    private URL coverUrl = null;
    private URL filmUrl = null;
    private String description = null;
    private Double rate = null;
    private Integer votes = null;
    private String[] genre = null;
    private Integer duration = null;
    private String countries = null;
    private String synopsis = null;
    private Integer commentsCount = null;
    private URL forumUrl = null;
    private Boolean hasReview = null;
    private Boolean hasDescription = null;
    private Date premiereWorld = null;
    private Date premiereCountry = null;
    private Integer filmType = null;
    private ArrayList<Person> directors = null;
    private ArrayList<Person> screenwriters = null;
    private ArrayList<Person> basedOn = null;
    private ArrayList<Person> actors = null;
   
    private int episodesCount = 0;
    private int seasonsCount = 0;
    private String nextEpisode = "";
    private Date nextEpisodeDate;

    private String review = null;

    public FilmTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

    public void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public URL getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(URL coverUrl) {
        this.coverUrl = coverUrl;
    }

    public URL getFilmUrl() {
        return filmUrl;
    }

    public void setFilmUrl(URL filmUrl) {
        this.filmUrl = filmUrl;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getSynopsis() {
        return synopsis;
    }
    
    public Integer getFilmType() {
		return filmType;
	}
    
    public Boolean getHasDescription() {
		return hasDescription;
	}
    
    public Boolean getHasReview() {
		return hasReview;
	}
    
    public Date getPremiereCountry() {
		return premiereCountry;
	}
    
    public Date getPremiereWorld() {
		return premiereWorld;
	}
    
    public void setHasReview(Boolean hasReview) {
		this.hasReview = hasReview;
	}
    
    public void setHasDescription(Boolean hasDescription) {
		this.hasDescription = hasDescription;
	}
    
    public void setPremiereCountry(Date premiereCountry) {
		this.premiereCountry = premiereCountry;
	}
    
    public void setPremiereWorld(Date premiereWorld) {
		this.premiereWorld = premiereWorld;
	}
    
    public void setFilmType(Integer filmType) {
		this.filmType = filmType;
	}

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    public Integer getCommentsCount() {
		return commentsCount;
	}
    
    public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}
    
    public URL getForumUrl() {
		return forumUrl;
	}
    
    public void setForumUrl(URL forumUrl) {
		this.forumUrl = forumUrl;
	}

    public ArrayList<Person> getDirectors() {
        return directors;
    }

    public ArrayList<Person> getScreenwriters() {
        return screenwriters;
    }

    public ArrayList<Person> getBasedOn() {
        return basedOn;
    }

    public ArrayList<Person> getActors() {
        return actors;
    }
    
    public void setActors(ArrayList<Person> actors) {
		this.actors = actors;
	}

    public String getDescription() {
        return description;
    }
    
    public void setReview(String review) {
		this.review = review;
	}
    
    public String getReview() {
    	return review;
	}
	
    public void setDescription(String description) {
		this.description = description;
	}
    
    public void setBasedOn(ArrayList<Person> basedOn) {
		this.basedOn = basedOn;
	}
    
    public void setDirectors(ArrayList<Person> directors) {
		this.directors = directors;
	}
    
    public void setScreenwriters(ArrayList<Person> screenwriters) {
		this.screenwriters = screenwriters;
	}
    
    public void setEpisodesCount(int episodesCount) {
		this.episodesCount = episodesCount;
	}
    
    public void setNextEpisode(String nextEpisode) {
		this.nextEpisode = nextEpisode;
	}
    
    public void setNextEpisodeDate(Date nextEpisodeDate) {
		this.nextEpisodeDate = nextEpisodeDate;
	}
    
    public void setSeasonsCount(int seasonsCount) {
		this.seasonsCount = seasonsCount;
	}
    
    public int getEpisodesCount() {
		return episodesCount;
	}
    
    public String getNextEpisode() {
		return nextEpisode;
	}
    
    public Date getNextEpisodeDate() {
		return nextEpisodeDate;
	}
    
    public int getSeasonsCount() {
		return seasonsCount;
	}
    
    public void convert(Film film) {
    	setId(film.getId());
    	setTitle(film.getTitle());
    	setPolishTitle(film.getPolishTitle());
    	setYear(film.getYear());
    	setCoverUrl(film.getCoverUrl());
    	setFilmUrl(film.getFilmUrl());
    	setDescription(film.getDescription());
    	setRate(film.getRate());
    	setVotes(film.getVotes());
    	setGenre(film.getGenre());
    	setDuration(film.getDuration());
    	setCountries(film.getCountries());
    	setSynopsis(film.getSynopsis());
    	setCommentsCount(film.getCommentsCount());
    	setForumUrl(film.getForumUrl());
    	setHasReview(film.getHasReview());
    	setHasDescription(film.getHasDescription());
    	setPremiereWorld(film.getPremiereWorld());
    	setPremiereCountry(film.getPremiereCountry());
    	setFilmType(film.getFilmType());
    	setDirectors(film.getDirectors());
    	setScreenwriters(film.getScreenwriters());
    	setBasedOn(film.getBasedOn());
    	setActors(film.getActors());
    	if(filmType == Type.SERIES.getCode()) {
    		Series series = (Series) film;
    		setSeasonsCount(series.getSeasonsCount());
    		setEpisodesCount(series.getEpisodesCount());
    		setNextEpisode(series.getNextEpisode());
    		setNextEpisodeDate(series.getNextEpisodeDate());
    	}
    }
}