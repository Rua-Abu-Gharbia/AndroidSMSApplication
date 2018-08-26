package com.ruaabugharbia.smsapplication.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruaabugharbia.smsapplication.R;


public class FavoriteMessageFragment extends BlankFragment {


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

}
