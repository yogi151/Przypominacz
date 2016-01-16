package com.hci.reminder;

import android.app.Application;
import android.content.Context;

public class ReminderApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ReminderApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ReminderApplication.context;
    }
}