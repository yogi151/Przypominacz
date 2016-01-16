package com.hci.reminder.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import com.hci.reminder.api.FilmwebApiHelper;
import com.hci.reminder.enums.Profession;


public class Film {

	// ID w serwisie Filmweb
    private int id = 0;
    
    // Tytu³ oryginalny
    private String title = null;

    // Tytu³ polski
    private String polishTitle = null;

    // Rok produkcji
    private Integer year = null;

    // Adres ok³adki
    private URL coverUrl = null;
    
    private Bitmap cover = null;
    
    // Adres strony filmu
    private URL filmUrl = null;

    // Opis filmu
    private String description = null;

    // Œrednia ocen filmu
    private Double rate = null;

    // Liczba g³osów
    private Integer votes = null;

    // Gatunek
    private String[] genre = null;

    // Czas trwania
    private Integer duration = null;

    // Kraj produkcji
    private String countries = null;

    // Streszczenie
    private String synopsis = null;
    
    // Liczba komentarzy
    private Integer commentsCount = null;
    
    // Adres forum
    private URL forumUrl = null;
    
    // czy posiada recenzje
    private Boolean hasReview = null;
    
    // czy posiada opis
    private Boolean hasDescription = null;
    
    // data premiery na œwiecie
    private Date premiereWorld = null;
    
    // data premiery w Polsce 
    private Date premiereCountry = null;
    
    // typ multimediu
    private Integer filmType = null;

    // Lista re¿yserów
    private ArrayList<Person> directors = null;

    // Lista scenarzystów
    private ArrayList<Person> screenwriters = null;

    // Na podstawie
    private ArrayList<Person> basedOn = null;

    // Lista aktorów
    private ArrayList<Person> actors = null;

    // Czy zosta³y pobrane informacje o filmie z u¿yciem metody zdalnej
    protected boolean isFilmDataChecked = false;

    private String review = null;
    
    /**
     * Zbiór metod nie bêd¹cych czêœci¹ API, s³u¿¹cych jednak do pobierania danych z serwisu Filmweb
     * (U¿ywane niejawnie)
     */
    protected FilmwebApiHelper api = null;

    public Film(FilmwebApiHelper api) {
        this.api = api;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        setFilmData();
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPolishTitle() {
        setFilmData();
        return polishTitle;
    }

    public void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public Integer getYear() {
        setFilmData();
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public URL getCoverUrl() {
        setFilmData();
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
        setFilmData();
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getVotes() {
        setFilmData();
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String[] getGenre() {
        setFilmData();
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        setFilmData();
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCountries() {
        setFilmData();
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getSynopsis() {
        setFilmData();
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
        if (directors == null) {
            directors = new ArrayList<Person>(api.getFilmPersons(id, Profession.DIRECTOR));
        }
        return directors;
    }

    public ArrayList<Person> getScreenwriters() {
        if (screenwriters == null) {
            screenwriters = new ArrayList<Person>(api.getFilmPersons(id, Profession.SCREENWRITER));
        }
        return screenwriters;
    }

    public ArrayList<Person> getBasedOn() {
        if (basedOn == null) {
            basedOn = new ArrayList<Person>(api.getFilmPersons(id, Profession.ORIGINAL_MATERIALS));
        }
        return basedOn;
    }

    public ArrayList<Person> getActors() {
        if (actors == null) {
            actors = new ArrayList<Person>(api.getFilmPersons(id, Profession.ACTOR));
        }
        return actors;
    }
    
    public void setActors(ArrayList<Person> actors) {
		this.actors = actors;
	}

    public String getDescription() {
        if (description == null) {
            description = api.getFilmDescription(id);
        }
        return description;
    }

    /**
     * Ustawienie informacji o filmie przy pierwszym rz¹daniu którejkolwiek brakuj¹cej
     */
    public void setFilmData() {
        if (!isFilmDataChecked) {
            api.getFilmData(this);
            isFilmDataChecked = true;
        }
    }
    
    public void setCover(Bitmap cover) {
		this.cover = cover;
	}
    
    public Bitmap getCover() {
    	if(cover == null && coverUrl != null) {
    		try {
    			HttpURLConnection connection = (HttpURLConnection) coverUrl
    				.openConnection();
    			connection.setDoInput(true);
    			connection.connect();
    			InputStream input = connection.getInputStream();
    			cover = BitmapFactory.decodeStream(input);
    			return cover;
    		} catch (IOException e) {
    			int w = 100, h = 100;
        		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        		cover = Bitmap.createBitmap(w, h, conf);
    			return cover;
    		}
    	} else {
    		return cover;
    	}
    }
    
    public Bitmap getCircleCover() {
    	if(cover == null) {
    		getCover();
    	}
    	if(cover == null) {
    		int w = 100, h = 100;

    		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
    		cover = Bitmap.createBitmap(w, h, conf);
    	}
    	 Bitmap output = Bitmap.createBitmap(cover.getWidth(),
    			 cover.getHeight(), Config.ARGB_8888);
         Canvas canvas = new Canvas(output);

         final int color = 0xff424242;
         final Paint paint = new Paint();
         final Rect rect = new Rect(0, 0, cover.getWidth(),
        		 cover.getHeight());

         paint.setAntiAlias(true);
         canvas.drawARGB(0, 0, 0, 0);
         paint.setColor(color);
         canvas.drawCircle(cover.getWidth() / 2,
        		 cover.getHeight() / 2, cover.getWidth() / 2, paint);
         paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
         canvas.drawBitmap(cover, rect, rect, paint);
         return output;
    }
    
    public void setReview(String review) {
		this.review = review;
	}
    
    public String getReview() {
		if(review == null || review.isEmpty()) {
			review = api.getFilmReview(id);
		}
    	return review;
	}
    
    public void setBasedOn(ArrayList<Person> basedOn) {
		this.basedOn = basedOn;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public void setDirectors(ArrayList<Person> directors) {
		this.directors = directors;
	}
    
    public void setScreenwriters(ArrayList<Person> screenwriters) {
		this.screenwriters = screenwriters;
	}
    
    public void setFilmDataChecked(boolean isFilmDataChecked) {
		this.isFilmDataChecked = isFilmDataChecked;
	}
    
    public void convert(FilmTable film) {
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
    	setFilmDataChecked(true);
    }
}


