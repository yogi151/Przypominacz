package com.hci.reminder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.hci.reminder.model.Channel;
import com.hci.reminder.model.ChannelTable;

public class ObservedChannelList {
	
	static HashMap<Integer, Channel> observedChannels;
	
	@SuppressLint("UseSparseArrays")
	private static void initObservedChannels() {
		if(observedChannels == null) {
			readFile();
		}
	}
	
	public static Channel getChannel(Integer id) {
		if(observedChannels == null) {
			initObservedChannels();
		}
		if(observedChannels.containsKey(id)) {
			return observedChannels.get(id);
		}
		return null;
	}
	
	public static void addChannel(Channel channel) {
		if(observedChannels == null) {
			initObservedChannels();
		}
		if(!observedChannels.containsKey(channel.getId())) {
			observedChannels.put(channel.getId(), channel);
			writeFile();
		}
	}
	
	public static void removeChannel(Channel channel) {
		if(observedChannels != null && observedChannels.containsKey(channel.getId())) {
			observedChannels.remove(channel.getId());
			writeFile();
			deleteImage(channel.getId());
		}
	}
	
	private static void writeFile() {
	    try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File channels = new File(folder.getPath(), "/channels");
	        FileOutputStream fos=new FileOutputStream(channels);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);
	        
	        ArrayList<ChannelTable> list = new ArrayList<ChannelTable>();
	        for(Entry<Integer, Channel> entry : observedChannels.entrySet()) {
	            Channel entryChannel = entry.getValue();
	            ChannelTable channel = new ChannelTable();
	            channel.convert(entryChannel);
	            list.add(channel);
	            if(entryChannel.getLogo() != null) {
	            	writeImage(entryChannel.getLogo(), folder.getPath(), entryChannel.getId());
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
	
	private static void readImage(Channel channel, String path, int id) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(path + "/" + id, options);
		channel.setLogo(bitmap);
	}
	
	@SuppressLint("UseSparseArrays")
	@SuppressWarnings("unchecked")
	private static void readFile() {
	    try {
	    	File folder = new File(Environment.getExternalStorageDirectory() + "/Reminder");
	    	if (!folder.exists()) {
	    	    folder.mkdir();
	    	}
	        File channels = new File(folder.getPath(), "/channels");
	        FileInputStream fis = new FileInputStream(channels);
	        ObjectInputStream ois = new ObjectInputStream(fis);

	        ArrayList<ChannelTable> list = (ArrayList<ChannelTable>) ois.readObject();
	        observedChannels = new HashMap<Integer, Channel>();
	        for(ChannelTable channelEntry : list) {
	        	Channel channel = new Channel();
	        	channel.convert(channelEntry);
	        	observedChannels.put(channelEntry.getId(), channel);
	        	readImage(channel, folder.getPath(), channel.getId());
	        }

	        ois.close();
	        fis.close();

	    } catch(Exception e) { 
	    	observedChannels = new HashMap<Integer, Channel>();
	    }
	}
	
	public static ArrayList<Channel> getObserved() {
		if(observedChannels == null) {
			initObservedChannels();
		}
		ArrayList<Channel> list = new ArrayList<Channel>();
	    for(Entry<Integer, Channel> entry : observedChannels.entrySet()) {
	    	list.add(entry.getValue());
	    }
	    return list;	       
	}	
}