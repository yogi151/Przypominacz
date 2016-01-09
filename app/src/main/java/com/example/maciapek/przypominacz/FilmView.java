package com.example.maciapek.przypominacz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FilmView extends Fragment {

        private Boolean b = true;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootview = inflater.inflate(R.layout.film_layout, container, false);
            Bundle bundle = getArguments();

            TextView title = (TextView)rootview.findViewById(R.id.engTitle);
            title.setText(bundle.getCharSequence("title"));

            final ImageView icon = (ImageView)rootview.findViewById(R.id.addOrRemove);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: dodanie/usunięcie z obserwowanych i zmiana ikony
                    //TODO: do ifa sprawdzenie warunku czy w obserwowanych
                    if (b) {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.added, Toast.LENGTH_SHORT).show();
                        icon.setImageResource(R.drawable.minus);
                        b = false;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.removed, Toast.LENGTH_SHORT).show();
                        icon.setImageResource(R.drawable.plus);
                        b = true;
                    }
                }
            });

        return rootview;
        }
}
