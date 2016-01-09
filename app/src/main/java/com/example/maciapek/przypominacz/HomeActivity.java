package com.example.maciapek.przypominacz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Fragment{


    //TODO: lista nadchodzących premier filmów i uri układek
    //TODO: po kliknięciu na plus dodanie do obserwowanych
    private String[] films = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć","siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwana","trzyna", "czterna", "piętna", "szesna", "siedemna", "osiemna","dziewiętna", "dwadz", "d jeden", "d dwa", "d trzy", "d cztery"};
    private String[] coverUriFilms = {"http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/42/46/694246/7718596.3.jpg","http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg","http://1.fwcdn.pl/po/10/51/671051/7709414.3.jpg"};
    private String[] releaseDateF = {"01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001"};
    //TODO: lista nadchodzących premier odcinków i uri układek
    private String[] episodes = {"Sjeden", "Sdwa", "Strzy", "Scztery", "Spięć", "Ssześć","Ssiedem", "Sosiem", "Sdziewięć", "Sdziesięć", "Sjedenaście", "Sdwana","Strzyna", "Sczterna", "Spiętna", "Sszesna", "Ssiedemna", "Sosiemna","Sdziewiętna", "Sdwadz", "Sd jeden", "Sd dwa", "Sd trzy", "Sd cztery"};
    private String[] coverUriSeries = {"http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg","http://1.fwcdn.pl/po/68/48/476848/7604108.3.jpg","http://1.fwcdn.pl/po/06/68/430668/7241342.3.jpg"};
    private String[] releaseDateE = {"01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001","01.01.2001"};
    private Boolean b = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity().getApplicationContext());
        ListAdapter FilmsAdapter = new FilmyAdapter(getActivity().getApplicationContext(), films, coverUriFilms, releaseDateF);
        ListAdapter SeriesAdapter = new FilmyAdapter(getActivity().getApplicationContext(), episodes, coverUriSeries,releaseDateE);

        adapter.addSection("Nadchodzące filmy", FilmsAdapter);
        adapter.addSection("Nadchodzące odcinki", SeriesAdapter);

        ListView list = new ListView(getActivity().getApplicationContext());
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String topFilm = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), topFilm, Toast.LENGTH_SHORT).show();
                        final ImageView icon = (ImageView) view.findViewById(R.id.addOrRemove);
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
                    }
                }
        );


        return list;
    }
}