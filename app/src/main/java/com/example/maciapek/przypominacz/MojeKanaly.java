package com.example.maciapek.przypominacz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MojeKanaly  extends Fragment{

    //private TextView textView;

    //TODO: programy na danym kanale
    private String[] program = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć","siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwana","trzyna", "czterna", "piętna", "szesna", "siedemna", "osiemna","dziewiętna", "dwadz", "d jeden", "d dwa", "d trzy", "d cztery"};
    private String[] time = {"10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00","10:00"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.filmy_layout,container,false);
        Bundle bundle = getArguments();

        //textView = (TextView)rootview.findViewById(R.id.textView);
        //textView.setText(bundle.getCharSequence("title"));

        ListAdapter programsAdapter = new ChannelProgramAdapter(getActivity().getApplicationContext(), program, time);
        ListView programList = (ListView) rootview.findViewById(R.id.topFilmy);
        programList.setAdapter(programsAdapter);

        programList.setOnItemClickListener(
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
