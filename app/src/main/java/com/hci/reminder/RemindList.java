package com.hci.reminder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Environment;

import com.hci.reminder.model.Remind;

public class RemindList {
	
	static List<Remind> remindList;
	
	private static void initRemindList() {
		if(remindList == null) {
			readRemindList();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void readRemindList() {
		try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File remind = new File(folder.getPath(), "/remind");
	        FileInputStream fis=new FileInputStream(remind);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        remindList = (ArrayList<Remind>) ois.readObject();
	        ois.close();
	        fis.close();

	    } catch(Exception e) { 
	    	remindList = new ArrayList<Remind>();
	    }
	}
	
	private static void writeRemindList() {
		try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File remind = new File(folder.getPath(), "/remind");
	        FileOutputStream fos = new FileOutputStream(remind);
	        ObjectOutputStream oos = new ObjectOutputStream(fos); 
	        oos.writeObject(remindList);
            oos.flush();
            oos.close();
            fos.close();
	    } catch(IOException e) {
	    	System.out.println("B³¹d: " + e.getMessage());
	    }
	}
	
	public static void setRemindList(List<Remind> updatedRemindList) {
		remindList = updatedRemindList;
		writeRemindList();
	}
	
	public static List<Remind> getNearestBroadcastFilm(int dayBeforeCount) {
		if(remindList == null) {
			initRemindList();
		}
		List<Remind> nearestBroadcast = new ArrayList<Remind>();
		for(Remind remind : remindList) {
			Calendar beforeCal = Calendar.getInstance();
			beforeCal.add(Calendar.DAY_OF_MONTH, dayBeforeCount);
			if(remind.getTime().before(beforeCal)) {
				nearestBroadcast.add(remind);
			}
		}
		return nearestBroadcast;
	}
}
