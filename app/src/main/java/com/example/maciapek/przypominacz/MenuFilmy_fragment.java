package com.example.maciapek.przypominacz;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by Maciapek on 2015-12-30.
 */

public class MenuFilmy_fragment extends Fragment{

    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.filmy_layout, container, false);
        return rootview;
    }
}

