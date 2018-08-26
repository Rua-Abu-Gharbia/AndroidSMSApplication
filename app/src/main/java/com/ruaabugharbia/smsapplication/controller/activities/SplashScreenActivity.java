package com.ruaabugharbia.smsapplication.controller.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.constants.AppConstants;
import com.ruaabugharbia.smsapplication.controller.dataBase.AppDatabase;
import com.ruaabugharbia.smsapplication.controller.service.UserTrackingReceiverIntentService;
import com.ruaabugharbia.smsapplication.controller.utils.AppUtils;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.ruaabugharbia.smsapplication.controller.constants.AppConstants.PERMISSIONS_REQ;
import static com.ruaabugharbia.smsapplication.controller.constants.AppConstants.PERMISSIONS_REQUEST;

public class SplashScreenActivity extends AppCompatActivity {

    public  String TAG = SplashScreenActivity.class
            .getSimpleName();

    private static final int TIME_TO_LEAVE = 5000;//5000

    WebView  imageWebView ;
    List<SMSModel> smsModelList ;
    List<TypeModel> typeModelList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(!AppUtils.isMyServiceRunning(SplashScreenActivity.this,UserTrackingReceiverIntentService.class)){
           startService(new Intent(SplashScreenActivity.this, UserTrackingReceiverIntentService.class));
        }

        getData();

        InputStream stream = null;
        try {
            stream = getAssets().open("message.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageWebView = findViewById(R.id.webView);
        imageWebView.loadUrl("file:///android_asset/message.gif");

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
        } else {
            navigate();
        }

    }

    private void getData(){
        AppDatabase database = AppDatabase.getAppDatabase(this);
        smsModelList = new ArrayList<>();
        typeModelList = new ArrayList<>();
        smsModelList = AppUtils.readExcelFile(this, AppConstants.FILE_NAME);
        typeModelList = AppUtils.readExcelFileTypes(this, AppConstants.TYPE_OF_MESSAGES);
        database.smsDao().insertAll(smsModelList);
        database.typeDao().insertAll(typeModelList);
    }

    private void navigate(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,DetailsActivity.class);
                startActivity(intent);
                finish();

            }
        }, TIME_TO_LEAVE);
    }

    public void checkPermission() {
        Log.i(TAG, "Show button pressed. Checking permission.");
        // BEGIN_INCLUDE(permission)
        // Check if the  permission is already available.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // permission has not been granted.
            requestPermission();

        } else {
            // permissions is already available, show the preview.
            Log.i(TAG,
                    "permission has already been granted. Displaying preview.");
            navigate();
        }
// END_INCLUDE(cpermission)
    }

    private void requestPermission() {
        Log.i(TAG, "permission has NOT been granted. Requesting permission.");
        // BEGIN_INCLUDE(permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS) ||ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(TAG, " permission rationale to provide additional context.");

            ActivityCompat.requestPermissions(this,
                    PERMISSIONS_REQ,
                    PERMISSIONS_REQUEST);
        } else {
            //  permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, PERMISSIONS_REQ,
                    PERMISSIONS_REQUEST);
        }
        // END_INCLUDE(permission_request)
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST) {
            // BEGIN_INCLUDE(permission_result)
            // Received permission result for  permission.
            Log.i(TAG, "Received response for  permission request.");
            // Check if the only required permission has been granted
              if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  permission has been granted, preview can be displayed
            Log.i(TAG, " permission has now been granted. Showing preview.");

            } else {
                Log.i(TAG, " permission was NOT granted.");
                  Toast.makeText(SplashScreenActivity.this ,getResources().getString(R.string.sms_permission_error),Toast.LENGTH_LONG).show();
            }
            navigate();
            // END_INCLUDE(permission_result)
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
