package com.hci.reminder.receiver;

import java.util.Calendar;

import com.hci.reminder.ObservedChannelList;
import com.hci.reminder.ObservedFilmList;
import com.hci.reminder.R;
import com.hci.reminder.RemindList;
import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Channel;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.Remind;
import com.hci.reminder.model.Series;
import com.hci.reminder.utils.Utils;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends IntentService {
    public NotificationService() {
        super("SchedulingService");
    }
    
    private static String REMIND = "Przypomnienie";

	@Override
	protected void onHandleIntent(Intent intent) {
		int id = 0;
		for(Remind remind : RemindList.getNearestBroadcastFilm(3)) {
			createNotification(remind, this, ++id);
		}
		
	}
	
	public static void createNotification(Remind remind, Context ctx, int id) {
		
		Film film = ObservedFilmList.getFilm(remind.getFilmId());
		if(film == null) {
			return;
		}
		
		Channel channel = ObservedChannelList.getChannel(remind.getChannelId());
		if(film.getFilmType() == Type.FILM.getCode()) {			
			if(channel == null) {
				return; 
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(film.getFilmType() == Type.FILM.getCode()) {
			sb.append("Film ");
			sb.append(film.getPolishTitle());
			sb.append(" ");
			sb.append(Utils.getDate(remind.getTime()));
			sb.append(" ");
			sb.append(remind.getTime().get(Calendar.HOUR_OF_DAY) + ":" + remind.getTime().get(Calendar.MINUTE));
			sb.append(" ");
			sb.append(channel.getName());
		} else {
			Series series = (Series) film;
			sb.append("Serial ");
			sb.append(series.getTitle());
			sb.append(" ");
			sb.append("odcinek: ");
			sb.append(series.getNextEpisode());
			sb.append(" ");
			sb.append(Utils.getDate(series.getNextEpisodeDate()));
		}		
		
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(ctx)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(REMIND)
				.setContentText(sb.toString());
		
		NotificationManager notificationManager = (NotificationManager)
				ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(id, mBuilder.build());
	}
    
    
}
