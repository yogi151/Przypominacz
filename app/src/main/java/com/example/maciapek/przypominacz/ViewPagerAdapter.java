package com.example.maciapek.przypominacz;

/**
 * Created by Maciapek on 2016-01-03.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.maciapek.przypominacz.activities.MovieListActivity;
import com.example.maciapek.przypominacz.enums.Type;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private int type = 0;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }


    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            //TODO::Obserwowane filmy
            //ObserwowaneFilmy tab1 = new ObserwowaneFilmy();
            Fragment tab1 = new MovieListActivity();
            Bundle b = new Bundle();
            b.putString("title", "");
            b.putString("type", Type.OBSERVED.name());
            tab1.setArguments(b);
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            //TODO::Obserwowane seriale
            //ObserwowaneSeriale tab2 = new ObserwowaneSeriale();
            Fragment tab2 = new MovieListActivity();
            Bundle b = new Bundle();
            b.putString("title", "");
            b.putString("type", Type.OBSERVED.name());
            tab2.setArguments(b);
            return tab2;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
