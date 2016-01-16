package com.hci.reminder.receiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hci.reminder.ObservedFilmList;
import com.hci.reminder.RemindList;
import com.hci.reminder.api.FilmwebApi;
import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.Remind;
import com.hci.reminder.model.Series;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    	if(haveNetworkConnection(context)) {
    		Calendar lastUpdatedDate = getLastUpdatedDate();
    		lastUpdatedDate.add(Calendar.DAY_OF_MONTH, 3);
    		Calendar calendar = Calendar.getInstance();
    		if(calendar.after(lastUpdatedDate)) {
    			// Akutalizacja listy przypomnieñ
    			RemindList.setRemindList(getFilmsNearestBroadcasts());
    			//EWA -> TODO Akcje wymagaj¹ce aktualizacji do bazy
    		}
    		// on Success
    		setUpdatedDate();
    	}
    }
    
    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)   context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;    
    }
    
    private void setUpdatedDate() {
    	try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	    	File database=new File(folder.getPath(), "/lastUpdatedDate");
	        FileOutputStream fos=new FileOutputStream(database);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);
	        oos.writeObject(Calendar.getInstance());
	        oos.close();
	        fos.close();

	    } catch(IOException e) { 
	    	
	    }
    }
    
	private Calendar getLastUpdatedDate() {
    	try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File database=new File(folder.getPath(), "/lastUpdatedDate");
	        FileInputStream fis=new FileInputStream(database);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Calendar date = (Calendar) ois.readObject();
	        ois.close();
	        fis.close();
	        return date;

	    } catch(Exception e) { 
	    	Calendar date = Calendar.getInstance();
	    	date.add(Calendar.DAY_OF_MONTH, -10);
	    	return date;
	    }	
    }
    
    @SuppressWarnings("deprecation")
	private List<Remind> getFilmsNearestBroadcasts() {
    	FilmwebApi api = new FilmwebApi();
    	List<Remind> remindList = new ArrayList<Remind>();
    	
    	for(Film film : ObservedFilmList.getObserved()) {
    		if(film.getFilmType() == Type.FILM.getCode()) {
    			remindList.addAll(api.getFilmsNearestBroadcasts(film.getId(), Type.FILM));
    		} else {
    			Series series = (Series) film;
    			series.setNextEpisodeDate(null);
    			if(series.getNextEpisodeDate() != null) {
    				Remind remind = new Remind();
    				remind.setFilmId(series.getId());
    				Calendar cal = Calendar.getInstance();
    				cal.clear();
    				cal.set(Calendar.DAY_OF_MONTH, series.getNextEpisodeDate().getDate());
    				cal.set(Calendar.MONTH, series.getNextEpisodeDate().getMonth() + 1);
    				cal.set(Calendar.YEAR, series.getNextEpisodeDate().getYear() + 1900);
    				remind.setTime(cal);
    				remindList.add(remind);
    			}    			
    		}
    	}
    	return remindList;
    }

}
