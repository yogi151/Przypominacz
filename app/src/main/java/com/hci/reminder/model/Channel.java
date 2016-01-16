package com.hci.reminder.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Channel {

	// ID w serwisie Filmweb
    private int id = 0;
    
    // Nazwa kana³u
    private String name = null;

    private URL logoURL = null;
    
    private Bitmap logo = null;
    
    public Channel() {
		
	}
    
    public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public URL getLogoURL() {
		return logoURL;
	}
    
    public void setLogoURL(URL logoURL) {
		this.logoURL = logoURL;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public Bitmap getLogo() {
    	if(logo == null && logoURL != null) {
    		try {
    			HttpURLConnection connection = (HttpURLConnection) logoURL
    				.openConnection();
    			connection.setDoInput(true);
    			connection.connect();
    			InputStream input = connection.getInputStream();
    			logo = BitmapFactory.decodeStream(input);
    			return logo;
    		} catch (IOException e) {
    			e.printStackTrace();
    			return null;
    		}
    	} else {
    		return logo;
    	}
	}
    
    public void setLogo(Bitmap logo) {
		this.logo = logo;
	}
    
    public void convert(ChannelTable channel) {
    	setId(channel.getId());
    	setLogoURL(channel.getLogoURL());
    	setName(channel.getName());
    }
}
