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
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.constants.AppConstants;
import com.ruaabugharbia.smsapplication.controller.dataBase.AppDatabase;
import com.ruaabugharbia.smsapplication.controller.utils.AppUtils;
import com.ruaabugharbia.smsapplication.controller.utils.IntentExtraNames;
import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;
import com.ruaabugharbia.smsapplication.view.adapters.SMSAdapter;

import java.util.ArrayList;
import java.util.List;


public class MessageSectionsFragment extends BlankFragment {

    RecyclerView smsRecyclerView ;
    SMSAdapter smsAdapter ;
    List<SMSModel> smsModelList ;
    List<TypeModel> typeModelList ;

    LinearLayout typesGridView ;


    public MessageSectionsFragment() {
        // Required empty public constructor
    }


    public static MessageSectionsFragment newInstance(String param1, String param2) {
        MessageSectionsFragment fragment = new MessageSectionsFragment();

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
        return inflater.inflate(R.layout.fragment_message_sections, container, false);
    }

    private  void createComponant (View v){
        smsRecyclerView = v.findViewById(R.id.sms_recycler_view);
        typesGridView = v.findViewById(R.id.message_sections_grid);
        smsModelList = new ArrayList<>();
        typeModelList = new ArrayList<>();
        AppDatabase database = AppDatabase.getAppDatabase(getContext());

        smsModelList = database.smsDao().getAll();
        typeModelList = database.typeDao().getAll();
        fillTypeModels();
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

    private void fillTypeModels(){

      for(final TypeModel model :typeModelList){
          final CardView card = new CardView(getContext());

          // Set the CardView layoutParams
          ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                  ViewGroup.LayoutParams.WRAP_CONTENT,
                  ViewGroup.LayoutParams.WRAP_CONTENT
          );
          // Set the CardView layoutParams
          ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(
                  200,
                  100
          );
          card.setLayoutParams(params1);

          // Set CardView corner radius
          card.setRadius(9);

          // Set cardView content padding
          card.setContentPadding(15, 20, 15, 20);

          // Set a background color for CardView
          card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

          // Set the CardView maximum elevation
          card.setMaxCardElevation(15);

          // Set CardView elevation
          card.setCardElevation(9);
          card.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AppUtils.showAlertToast(model.getTypeName(),getContext());
                  Bundle bundle = new Bundle();
                  bundle.putString(IntentExtraNames.TYPE,model.getTypeName());
                  gotoFragment(new MessageFragment(), getActivity().getSupportFragmentManager(),bundle);
              }
          });

          // Initialize a new TextView to put in CardView
          TextView tv = new TextView(getContext());
          tv.setLayoutParams(params);
          tv.setText(model.getTypeName());
          tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
          tv.setTextColor(Color.RED);
          tv.setGravity(View.TEXT_ALIGNMENT_CENTER);

          // Put the TextView in CardView
          card.addView(tv);

          // Finally, add the CardView in root layout
          typesGridView.addView(card);
      }

    }

}
