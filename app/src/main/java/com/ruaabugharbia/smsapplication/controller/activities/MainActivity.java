package com.ruaabugharbia.smsapplication.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.constants.AppConstants;
import com.ruaabugharbia.smsapplication.controller.utils.AppUtils;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.view.adapters.SMSAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView smsRecyclerView ;
    SMSAdapter smsAdapter ;
    List<SMSModel> smsModelList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsRecyclerView = findViewById(R.id.sms_recycler_view);
        smsModelList = new ArrayList<>();

        smsModelList = AppUtils.readExcelFile(MainActivity.this , AppConstants.fileName);
        fillSMSAdapter();

    }

    private void fillSMSAdapter(){
        smsAdapter = new SMSAdapter(MainActivity.this,R.layout.sms_item,smsModelList);
        smsRecyclerView.post(new Runnable() {
            public void run() {
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                smsRecyclerView.setLayoutManager(mLayoutManager);
                smsRecyclerView.setAdapter(smsAdapter);
            }
        });

    }

}
