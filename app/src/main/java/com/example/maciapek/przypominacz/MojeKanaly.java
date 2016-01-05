package com.example.maciapek.przypominacz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Maciapek on 2016-01-04.
 */
public class MojeKanaly  extends Fragment{

    private TextView textView;

    //TODO: programy na danym kanale

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.moje_kanaly,container,false);
        Bundle bundle = getArguments();

        textView = (TextView)v.findViewById(R.id.textView);
        textView.setText(bundle.getCharSequence("title"));

        return v;
    }
}
