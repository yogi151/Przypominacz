package com.hci.reminder.model;

import java.io.Serializable;
import java.net.URL;

public class ChannelTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ID w serwisie Filmweb
    private int id = 0;
    
    // Nazwa kana³u
    private String name = null;

    private URL logoURL = null;
    
    public ChannelTable() {
		
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

    public void convert(Channel channel) {
    	setId(channel.getId());
    	setLogoURL(channel.getLogoURL());
    	setName(channel.getName());
    }
}
