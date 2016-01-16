package com.hci.reminder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.hci.reminder.api.FilmwebApi;
import com.hci.reminder.api.FilmwebApiHelper;
import com.hci.reminder.config.Connection;
import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.FilmTable;
import com.hci.reminder.model.Remind;
import com.hci.reminder.model.Series;
import com.hci.reminder.receiver.NotificationService;

public class ObservedFilmList {
	
	static HashMap<Integer, Film> observedFilms;
	
	@SuppressLint("UseSparseArrays")
	private static void initObservedFilms() {
		if(observedFilms == null) {
			readFile();
			CacheList.setFilms(observedFilms);
		}
	}
	
	public static Film getFilm(Integer id) {
		if(observedFilms == null) {
			initObservedFilms();
		}
		if(observedFilms.containsKey(id)) {
			return observedFilms.get(id);
		}
		return null;
	}
	
	public static void addFilm(Film film) {
		if(observedFilms == null) {
			initObservedFilms();
		}
		if(!observedFilms.containsKey(film.getId())) {
			observedFilms.put(film.getId(), film);
			writeFile();
			FilmwebApi api = new FilmwebApi();
			List<Remind> reminds = api.getFilmsNearestBroadcasts(film.getId(), Type.getByCode(film.getFilmType()));
			RemindList.setRemindList(reminds);
			if(!reminds.isEmpty()) {
				NotificationService.createNotification(reminds.get(0), ReminderApplication.getAppContext(), 0);
			}
		}
	}
	
	public static void removeFilm(Film film) {
		if(observedFilms != null && observedFilms.containsKey(film.getId())) {
			observedFilms.remove(film.getId());
			writeFile();
			deleteImage(film.getId());
		}
	}
	
	private static void writeFile() {
	    try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File database=new File(folder.getPath(), "/database");
	        FileOutputStream fos=new FileOutputStream(database);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);
	        
	        ArrayList<FilmTable> list = new ArrayList<FilmTable>();
	        for(Entry<Integer, Film> entry : observedFilms.entrySet()) {
	            Film entryFilm = entry.getValue();
	            FilmTable film = new FilmTable();
	            film.convert(entryFilm);
	            list.add(film);
	            if(entryFilm.getCover() != null) {
	            	writeImage(entryFilm.getCover(), folder.getPath(), entryFilm.getId());
	            }
	        }  
	        oos.writeObject(list);
            oos.flush();
            oos.close();
            fos.close();
	    } catch(IOException e) {
	    	System.out.println("B³¹d: " + e.getMessage());
	    }
	}
	
	private static void writeImage(Bitmap image, String path, int id) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path + "/" + id);
			image.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.close();
		} catch (IOException e) {
			System.out.println("B³¹d: " + e.getMessage());
		} 
	}
	
	private static void deleteImage(int id) {
		File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
		if(folder.exists()) {
			File imageFile = new File(folder.getPath(), "" + id);
			if(imageFile.exists()) {
				imageFile.delete();
			}
		}
	}
	
	private static void readImage(Film film, String path, int id) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(path + "/" + id, options);
		film.setCover(bitmap);
	}
	
	@SuppressLint("UseSparseArrays")
	@SuppressWarnings("unchecked")
	private static void readFile() {
	    try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File database=new File(folder.getPath(), "/database");
	        FileInputStream fis=new FileInputStream(database);
	        ObjectInputStream ois=new ObjectInputStream(fis);

	        ArrayList<FilmTable> list = (ArrayList<FilmTable>) ois.readObject();
	        observedFilms = new HashMap<Integer, Film>();
	        for(FilmTable filmEntry : list) {
	        	if(filmEntry.getFilmType() == Type.FILM.getCode()) {
	        		Film film = new Film(new FilmwebApiHelper(new Connection()));
	        		film.convert(filmEntry);
	        		observedFilms.put(filmEntry.getId(), film);
	        		readImage(film, folder.getPath(), film.getId());
	        	} else {
	        		Series series = new Series(new FilmwebApiHelper(new Connection()));
	        		series.convert(filmEntry);
	        		observedFilms.put(filmEntry.getId(), series);
	        		readImage(series, folder.getPath(), series.getId());
	        	}
	        }

	        ois.close();
	        fis.close();

	    } catch(Exception e) { 
	    	observedFilms = new HashMap<Integer, Film>();
	    }
	}
	
	public static ArrayList<Film> getObserved() {
		if(observedFilms == null) {
			initObservedFilms();
		}
		ArrayList<Film> list = new ArrayList<Film>();
	    for(Entry<Integer, Film> entry : observedFilms.entrySet()) {
	    	list.add(entry.getValue());
	    }
	    return list;	       
	}
	
}
