package com.example.maciapek.przypominacz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import com.example.maciapek.przypominacz.activities.MovieListActivity;
import com.example.maciapek.przypominacz.enums.Type;


public class SearchMainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View rootview = inflater.inflate(R.layout.fragment_search_main, container, false);
/*
        final EditText editText = (EditText)rootview.findViewById(R.id.search);
        ImageView searchButton = (ImageView)rootview.findViewById(R.id.searchButtonF);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieListActivity.class);
                Bundle b = new Bundle();
                b.putString("title", editText.getText().toString());
                b.putString("type", Type.FILM.name());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
       /* searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment list = new MovieListActivity();
                Bundle b = new Bundle();

                b.putString("title", editText.getText().toString());
                b.putString("type", Type.FILM.name());
                list.setArguments(b);
                //FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, list);
                ft.commit();
            }

        });;*/


        return rootview;
    }
}
