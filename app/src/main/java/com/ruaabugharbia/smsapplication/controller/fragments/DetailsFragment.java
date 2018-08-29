package com.ruaabugharbia.smsapplication.controller.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ruaabugharbia.smsapplication.R;



public class DetailsFragment extends BlankFragment {



    public DetailsFragment() {
        // Required empty public constructor
    }


    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();

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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPager(view);
    }


    private void initPager(View view) {
        ViewPager pager =  view.findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        PagerSlidingTabStrip tabs =  view.findViewById(R.id.tabs);
        tabs.setAllCaps(false);
        tabs.setViewPager(pager);
        tabs.setIndicatorColor(getResources().getColor(R.color.border_color));
        tabs.setTextSize(30);
        tabs.setTextColor(Color.WHITE);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = getResources().getStringArray(R.array.sms_tabs);

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

                switch (position) {
                    case 0: {
                        return new MessageSectionsFragment();
                    }
                    case 1: {
                        return new FavoriteMessageFragment();
                    }
                }

            return null;
        }
    }

}
