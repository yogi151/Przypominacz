 package com.example.maciapek.przypominacz;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

/**
 * Created by Maciapek on 2015-12-30.
 */
public class MojeKanaly_fragment extends Fragment implements MaterialTabListener{
    private View rootview;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;


    //TODO: lista kanałów użytkownika
    private CharSequence Titles[]={"TVP1", "TVP2", "polsat", "discovery", "Animal Planet", "TV trwam", "HBO", "Comedy Central"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.moje_kanaly_layout, container, false);

        //TabLayout tabLayout = (TabLayout) rootview.findViewById(R.id.tab_layout);

        tabHost = (MaterialTabHost)rootview.findViewById(R.id.materialTabHost);
        viewPager = (ViewPager)rootview.findViewById(R.id.viewPager);

        viewPager.setAdapter((new ViewPagerAdapterS(getActivity().getSupportFragmentManager(), Titles, Titles.length)));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (CharSequence x: Titles
             ) {
            tabHost.addTab(tabHost.newTab().setText(x).setTabListener(this));
        }

        return rootview;
    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}