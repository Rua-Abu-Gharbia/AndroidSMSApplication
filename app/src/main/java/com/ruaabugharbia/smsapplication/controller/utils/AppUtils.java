package com.ruaabugharbia.smsapplication.controller.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ruaabugharbia on 06-Aug-18.
 */

public class AppUtils {

    public static  List <SMSModel> readExcelFile(Context context, String filename) {
        List <SMSModel> smsModelList = new ArrayList<>();

        try {

            InputStream stream = context.getAssets().open(filename);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(stream);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells. **/
            Iterator<Row> rowIter = mySheet.rowIterator();

            int length = (mySheet.getLastRowNum() - mySheet
                    .getFirstRowNum()) - 1;

            rowIter.hasNext();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(myRow.getRowNum()==0 ){
                    continue; //just skip the rows if row number is 0
                }
                Iterator<Cell> cellIter = myRow.cellIterator();
                SMSModel smsModel = new SMSModel();
                int colNumber = -1;
                while (cellIter.hasNext()) {
                    colNumber++;
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    String ColValue = myCell.toString().trim();
                    switch (colNumber) {
                        /*case 0:
                             smsModel.setSmsId(Integer.parseInt(ColValue));
                            break;*/
                        case 1:
                            smsModel.setSmsBody(ColValue);
                            break;
                       case 2:
                            smsModel.setType(ColValue);
                            break;
                    }
                }
                smsModel.setFavourite(false);
                smsModelList.add(smsModel);
            }

        } catch (Exception e){
            Log.e("AppUtils ",e.getMessage());

        }

        return smsModelList;

    }

    public static  List <TypeModel> readExcelFileTypes(Context context, String filename) {
        List <TypeModel> typeModelList = new ArrayList<>();

        try {

            InputStream stream = context.getAssets().open(filename);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(stream);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells. **/
            Iterator<Row> rowIter = mySheet.rowIterator();

            int length = (mySheet.getLastRowNum() - mySheet
                    .getFirstRowNum()) - 1;

            rowIter.hasNext();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(myRow.getRowNum()==0 ){
                    continue; //just skip the rows if row number is 0
                }
                Iterator<Cell> cellIter = myRow.cellIterator();
                TypeModel typeModel = new TypeModel();
                int colNumber = -1;
                while (cellIter.hasNext()) {
                    colNumber++;
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    String ColValue = myCell.toString();
                    switch (colNumber) {
                        /*case 0:
                            if(!ColValue.equals("id")){
                                typeModel.setTypeId(Long.parseLong(ColValue));
                            }
                            break;*/
                        case 1:
                            typeModel.setTypeName(ColValue);
                            break;
                    }
                }
                typeModelList.add(typeModel);
            }

        } catch (Exception e){
            Log.e("AppUtils ",e.getMessage());

        }

        return typeModelList;

    }

    public static void sendSMS(Context context ,String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();

        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isMyServiceRunning(Context context ,Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void showAlertToast(String message, Context context) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
