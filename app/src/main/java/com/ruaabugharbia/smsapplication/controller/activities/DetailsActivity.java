package com.ruaabugharbia.smsapplication.controller.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.controller.fragments.DetailsFragment;
import com.ruaabugharbia.smsapplication.controller.fragments.MessageSectionsFragment;

public class DetailsActivity extends AppCompatActivity {

    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new MessageSectionsFragment();

        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
