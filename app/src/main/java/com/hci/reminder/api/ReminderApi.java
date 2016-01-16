package com.hci.reminder.api;

import com.hci.reminder.ObservedChannelList;
import com.hci.reminder.ObservedFilmList;
import com.hci.reminder.model.Channel;
import com.hci.reminder.model.Film;

public class ReminderApi {

	public static void observeFilm(Film film) {
		ObservedFilmList.addFilm(film);
	}
	
	public static void stopObserve(Film film) {
		ObservedFilmList.removeFilm(film);
	}
	
	public static void observeChannel(Channel channel) {
		ObservedChannelList.addChannel(channel);
	}
	
	public static void stopObserveChannel(Channel channel) {
		ObservedChannelList.removeChannel(channel);
	}
}
