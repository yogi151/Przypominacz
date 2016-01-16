package com.hci.reminder.model;

import java.util.Calendar;

public class Remind {
	
	private int filmId;
	private int channelId;
	private Calendar time;
	
	public Remind() {
	
	}
	
	public int getChannelId() {
		return channelId;
	}

	public int getFilmId() {
		return filmId;
	}
	
	public Calendar getTime() {
		return time;
	}
	
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	public void setTime(Calendar time) {
		this.time = time;
	}
	
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
}
