package com.example.maciapek.przypominacz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Maciapek on 2015-12-30.
 */
public class MenuListaKanalow_fragment extends Fragment{

    //TODO: lista kanałów z filmweba
    //TODO: po kliknięciu na plus/minus usunięcie z obserwowanych
    private String[] channelsList = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć","siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwana","trzyna", "czterna", "piętna", "szesna", "siedemna", "osiemna","dziewiętna", "dwadz", "d jeden", "d dwa", "d trzy", "d cztery"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.filmy_layout, container, false);

        ListAdapter channelsAdapter = new ChannelsAdapter(getActivity().getApplicationContext(), channelsList);
        ListView channels = (ListView) rootview.findViewById(R.id.topFilmy);
        channels.setAdapter(channelsAdapter);

        channels.setOnItemClickListener(
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
