package com.ruaabugharbia.smsapplication.controller.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.dataBase.AppDatabase;
import com.ruaabugharbia.smsapplication.controller.utils.AppUtils;
import com.ruaabugharbia.smsapplication.controller.utils.IntentExtraNames;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;
import com.ruaabugharbia.smsapplication.view.adapters.SMSAdapter;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BlankFragment {

    RecyclerView smsRecyclerView ;
    SMSAdapter smsAdapter ;
    List<SMSModel> smsModelList ;
    String type ;

    public MessageFragment() {
        // Required empty public constructor
    }


    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createComponant(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    private  void createComponant (View v){

        Bundle bundle = getArguments();
        if(bundle!= null){
            type = bundle.getString(IntentExtraNames.TYPE);

        }

        smsRecyclerView = v.findViewById(R.id.sms_recycler_view);
        smsModelList = new ArrayList<>();
        AppDatabase database = AppDatabase.getAppDatabase(getContext());

        smsModelList = database.smsDao().getByType(type);
        fillSMSAdapter();

    }

    private void fillSMSAdapter(){
        smsAdapter = new SMSAdapter(getContext(),R.layout.sms_item,smsModelList);
        smsRecyclerView.post(new Runnable() {
            public void run() {
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                smsRecyclerView.setLayoutManager(mLayoutManager);
                smsRecyclerView.setAdapter(smsAdapter);
            }
        });

    }

}
