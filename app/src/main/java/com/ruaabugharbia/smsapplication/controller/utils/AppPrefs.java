package com.ruaabugharbia.smsapplication.controller.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ruaabugharbia on 09-Aug-18.
 */

public class AppPrefs {

    private static final String SEND_SMS = "send_sms";

    private static SharedPreferences prefs = null;

    private static SharedPreferences getPrefs(Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }

        return prefs;
    }

    public static boolean getSendSms(Context context) {
        return getPrefs(context).getBoolean(SEND_SMS, true);
    }


    public static void setSendSms(Context context, boolean sendSMS) {
        SharedPreferences.Editor prefEditor = getPrefs(context).edit();
        prefEditor.putBoolean(SEND_SMS, sendSMS).commit();
    }

}
