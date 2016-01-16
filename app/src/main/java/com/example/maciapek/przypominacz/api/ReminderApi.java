package com.example.maciapek.przypominacz.api;

import com.example.maciapek.przypominacz.ObservedChannelList;
import com.example.maciapek.przypominacz.ObservedFilmList;
import com.example.maciapek.przypominacz.model.Channel;
import com.example.maciapek.przypominacz.model.Film;

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
