package com.ruaabugharbia.smsapplication.controller.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.dataBase.AppDatabase;
import com.ruaabugharbia.smsapplication.controller.utils.IntentExtraNames;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.view.adapters.SMSAdapter;

import java.util.ArrayList;
import java.util.List;


public class FavoriteMessageFragment extends BlankFragment {

    RecyclerView smsRecyclerView ;
    SMSAdapter smsAdapter ;
    List<SMSModel> smsModelList ;

    public FavoriteMessageFragment() {
        // Required empty public constructor
    }


    public static FavoriteMessageFragment newInstance(String param1, String param2) {
        FavoriteMessageFragment fragment = new FavoriteMessageFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_message, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createComponant(view);
    }

    private  void createComponant (View v){
        smsRecyclerView = v.findViewById(R.id.sms_recycler_view);
        smsModelList = new ArrayList<>();
        AppDatabase database = AppDatabase.getAppDatabase(getContext());

        smsModelList = database.smsDao().getFavorite(true);
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
