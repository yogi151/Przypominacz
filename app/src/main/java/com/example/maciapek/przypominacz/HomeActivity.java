package com.example.maciapek.przypominacz;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;


/**
 * Created by Maciapek on 2016-01-02.
 */

public class HomeActivity extends Fragment{

    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.home_layout, container, false);
        return rootview;
    }
}