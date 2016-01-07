package com.example.maciapek.przypominacz;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Maciapek on 2015-12-30.
 */

public class MenuFilmy_fragment extends Fragment{

    private View rootview;
    //TODO: lista top filmów z filmweba
    private String[] topFilms = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć","siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwana","trzyna", "czterna", "piętna", "szesna", "siedemna", "osiemna","dziewiętna", "dwadz", "d jeden", "d dwa", "d trzy", "d cztery"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.filmy_layout, container, false);

        ListAdapter topFilmsAdapter = new FilmyAdapter(getActivity().getApplicationContext(), topFilms);
        ListView topFilmy = (ListView)rootview.findViewById(R.id.topFilmy);
        topFilmy.setAdapter(topFilmsAdapter);

        topFilmy.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String topFilm = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), topFilm, Toast.LENGTH_SHORT).show();

                    }
                }
        );



        return rootview;
    }
}

