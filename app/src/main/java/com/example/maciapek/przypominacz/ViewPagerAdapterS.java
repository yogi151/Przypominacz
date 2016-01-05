package com.example.maciapek.przypominacz;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Maciapek on 2016-01-04.
 */
public class ViewPagerAdapterS extends ViewPagerAdapter {

    private Bundle bundl;
    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created


    public ViewPagerAdapterS(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);

        this.Titles=mTitles;
    }



    @Override
    public Fragment getItem(int position) {

            bundl = new Bundle();
            bundl.putCharSequence("title", Titles[position]);

            MojeKanaly tab1 = new MojeKanaly();

            tab1.setArguments(bundl);

            return tab1;

    }



}
