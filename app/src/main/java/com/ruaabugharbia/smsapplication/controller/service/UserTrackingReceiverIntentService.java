package com.ruaabugharbia.smsapplication.controller.service;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import com.ruaabugharbia.smsapplication.controller.receiver.SimpleWakefulReceiver;

import java.util.Calendar;

/**
 * Created by ruaabugharbia on 8/10/18.
 */

public class UserTrackingReceiverIntentService extends IntentService {

    public static final String TAG = "UserTrackingReceiverIntentService";
    Context context;
    public UserTrackingReceiverIntentService() {
        super("UserTrackingReceiverIntentService");
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onHandleIntent(Intent intent) {
        this.context = this;


        Calendar calendar = Calendar.getInstance();
        //********************************** SETTING NEXT ALARM *********************************************
        Intent intentWakeFullBroacastReceiver = new Intent(context, SimpleWakefulReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1001, intentWakeFullBroacastReceiver, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+24); // TODO +24 is need


        //MARSHMALLOW OR ABOVE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), sender);
        }
        //LOLLIPOP 21 OR ABOVE
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), sender);
            alarmManager.setAlarmClock(alarmClockInfo, sender);
        }
        //KITKAT 19 OR ABOVE
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), sender);
        }
        //FOR BELOW KITKAT ALL DEVICES
        else {
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), sender);
        }

        SimpleWakefulReceiver.completeWakefulIntent(intent);
    }
}
