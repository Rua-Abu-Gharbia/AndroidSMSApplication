package com.ruaabugharbia.smsapplication.controller.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import com.ruaabugharbia.smsapplication.controller.constants.AppConstants;
import com.ruaabugharbia.smsapplication.controller.service.UserTrackingReceiverIntentService;
import com.ruaabugharbia.smsapplication.controller.utils.AppPrefs;
import com.ruaabugharbia.smsapplication.controller.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ruaabugharbia on 8/10/18.
 */

public class SimpleWakefulReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // This is the Intent to deliver to our service.
        Calendar calendar = Calendar.getInstance();
        Intent service = new Intent(context, UserTrackingReceiverIntentService.class);
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String DateTime = sdf.format(date);
        //DateTime = Util.locatToUTC(DateTime);
        service.putExtra("date", String.valueOf(DateTime));

        boolean senSMS = AppPrefs.getSendSms(context);
        if(calendar.get(Calendar.DATE) == AppConstants.DATE_TO_SEND ){
            if (senSMS){
                AppUtils.sendSMS(context,AppConstants.Receiver_Phone_number, AppConstants.MESSAGE);
                AppPrefs.setSendSms(context,false);
            } else {
                Toast.makeText(context ,"secaned Time",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context ,"Date wrong",Toast.LENGTH_LONG).show();
            AppPrefs.setSendSms(context,true);
        }

        Log.i("SimpleWakefulReceiver", "Starting service @ " + calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE) + " : " + calendar.get(Calendar.SECOND));

        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, service);
    }

}
