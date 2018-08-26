package com.ruaabugharbia.smsapplication.controller.constants;

import android.Manifest;

/**
 * Created by ruaabugharbia on 06-Aug-18.
 */

public class AppConstants {

    public static String FILE_NAME ="my-messages.xls";
    public static String TYPE_OF_MESSAGES = "type-of-messages";

    public static String[] PERMISSIONS_REQ = {
            android.Manifest.permission.SEND_SMS , Manifest.permission.READ_EXTERNAL_STORAGE};

    public static int PERMISSIONS_REQUEST = 1;

    public static String Receiver_Phone_number = "07------"; // change this to Receiver_Phone_number

    public static String MESSAGE = "azad" ; // change this to the message you want to send

    public static int DATE_TO_SEND = 1 ;  // change this to date you want to send the message


}
