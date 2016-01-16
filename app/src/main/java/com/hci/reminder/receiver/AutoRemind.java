package com.hci.reminder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoRemind extends BroadcastReceiver
{   
    ReminderReceiver alarm = new ReminderReceiver();
    
    @Override
    public void onReceive(Context context, Intent intent)
    {   
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            alarm.SetAlarm(context);
        }
    }
}