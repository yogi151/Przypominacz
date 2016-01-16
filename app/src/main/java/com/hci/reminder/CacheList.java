package com.hci.reminder;

import java.util.HashMap;
import java.util.Map.Entry;

import android.annotation.SuppressLint;

import com.hci.reminder.model.Film;

public class CacheList {

	static HashMap<Integer, Film> cache;
	
	@SuppressLint("UseSparseArrays")
	private static void initCache() {
		if(cache == null) {
			cache = new HashMap<Integer, Film>();
		}
	}
	
	public static Film getFilm(Integer id) {
		if(cache == null) {
			initCache();
		}
		if(cache.containsKey(id)) {
			return cache.get(id);
		}
		return null;
	}
	
	public static void setFilm(Film film) {
		if(cache == null) {
			initCache();
		}
		if(!cache.containsKey(film.getId())) {
			cache.put(film.getId(), film);
		}
	}
	
	public static void setFilms(HashMap<Integer, Film> map) {
		if(cache == null) {
			initCache();
		}
		for(Entry<Integer, Film> entry : map.entrySet()) {
			setFilm(entry.getValue());
		}
	}
}
