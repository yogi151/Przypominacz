package com.hci.reminder.receiver;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class ReminderReceiver extends WakefulBroadcastReceiver 
{    
    @Override
    public void onReceive(Context context, Intent intent) 
    {   
    	Intent service = new Intent(context, NotificationService.class);
    	startWakefulService(context, service);
    }

    public void SetAlarm(Context context)
    {
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(System.currentTimeMillis());
    	cal.set(Calendar.HOUR_OF_DAY, 16);
    	cal.set(Calendar.MINUTE, 23);
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ReminderReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,  
                cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, ReminderReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}