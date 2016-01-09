package com.example.maciapek.przypominacz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class HomeActivity extends Fragment{


    //TODO: lista top filmów z filmweba i uri ich okładek
    //TODO: po kliknięciu na plus dodanie do obserwowanych
    private String[] films = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć","siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwana","trzyna", "czterna", "piętna", "szesna", "siedemna", "osiemna","dziewiętna", "dwadz", "d jeden", "d dwa", "d trzy", "d cztery"};
    private String[] coverUriFilms = {"http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg"};
    //TODO: lista top seriali z filmweba i uri ich okładek
    private String[] episodes = {"Sjeden", "Sdwa", "Strzy", "Scztery", "Spięć", "Ssześć","Ssiedem", "Sosiem", "Sdziewięć", "Sdziesięć", "Sjedenaście", "Sdwana","Strzyna", "Sczterna", "Spiętna", "Sszesna", "Ssiedemna", "Sosiemna","Sdziewiętna", "Sdwadz", "Sd jeden", "Sd dwa", "Sd trzy", "Sd cztery"};
    private String[] coverUriSeries = {"http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity().getApplicationContext());
        ListAdapter FilmsAdapter = new FilmyAdapter(getActivity().getApplicationContext(), films, coverUriFilms);
        ListAdapter SeriesAdapter = new FilmyAdapter(getActivity().getApplicationContext(), episodes, coverUriSeries);

        adapter.addSection("Nadchodzące filmy", FilmsAdapter);
        adapter.addSection("Nadchodzące odcinki",SeriesAdapter);

        ListView list = new ListView(getActivity().getApplicationContext());
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String topFilm = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), topFilm, Toast.LENGTH_SHORT).show();

                    }
                }
        );


        return list;
    }
}