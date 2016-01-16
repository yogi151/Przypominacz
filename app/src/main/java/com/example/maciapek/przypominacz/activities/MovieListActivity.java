package com.example.maciapek.przypominacz.activities;

import java.util.List;

import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.adapters.FilmListAdapter;
import com.example.maciapek.przypominacz.adapters.SeriesListAdapter;
import com.example.maciapek.przypominacz.api.FilmwebApi;
import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.model.Film;
import com.example.maciapek.przypominacz.model.Series;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MovieListActivity extends Fragment {
	
   private ListView listViewFilm;
   private Context ctx;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//getActivity().setContentView(R.layout.activity_movie_list);
		ctx = getActivity().getApplicationContext();

		//Bundle b = getActivity().getIntent().getExtras();
		Bundle b = getArguments();

		String title = b.getString("title");
		Type type = Type.valueOf(b.getString("type"));


		View rootview = inflater.inflate(R.layout.activity_movie_list, container, false);

		if (Type.FILM == type) {
			addFilmListView(title, rootview);
		} else if (Type.SERIES == type) {
			addSeriesListView(title, rootview);
		} else if (Type.POPULAR == type) {
			addPopularListView(rootview);
		} else if (Type.UPCOMMING == type) {
	    	addUpcommingListView(rootview);
		} else if (Type.OBSERVED == type) {
			addObservedListView(rootview);
		}

		return rootview;
	}
	
	private void addFilmListView(String title, View view) {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getFilmList(title);
    	if(filmList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}

		listViewFilm = ( ListView ) view.findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	/*Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);*/
				MovieDetailsActivity movieDetailsActivity = new MovieDetailsActivity();
				Bundle b = new Bundle();
				b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
				movieDetailsActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, movieDetailsActivity);
				ft.commit();

			}
		});
	}
	
	private void addSeriesListView(String title,View view) {
		FilmwebApi api = new FilmwebApi();
    	List<Series> seriesList = api.getSeriesList(title);
    	if(seriesList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak znalezionych seriali", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView )  view.findViewById(R.id.film_list);
        listViewFilm.setAdapter( new SeriesListAdapter(ctx, R.layout.film_list_item, seriesList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	/*Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);*/
				MovieDetailsActivity movieDetailsActivity = new MovieDetailsActivity();
				Bundle b = new Bundle();
				b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
				movieDetailsActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, movieDetailsActivity);
				ft.commit();
			}
		});
	}
	
	private void addPopularListView(View view) {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getPopularFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView )  view.findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	/*Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);*/
				MovieDetailsActivity movieDetailsActivity = new MovieDetailsActivity();
				Bundle b = new Bundle();
				b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
				movieDetailsActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, movieDetailsActivity);
				ft.commit();
			}
		});
	}
	
	private void addUpcommingListView(View view) {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getUpcommingFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView )  view.findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	/*Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);*/
				MovieDetailsActivity movieDetailsActivity = new MovieDetailsActivity();
				Bundle b = new Bundle();
				b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
				movieDetailsActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, movieDetailsActivity);
				ft.commit();
			}
		});
	}
	
	private void addObservedListView(View view) {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getObservedFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak obserwowanych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) view.findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	/*Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);*/
				MovieDetailsActivity movieDetailsActivity = new MovieDetailsActivity();
				Bundle b = new Bundle();
				b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
				movieDetailsActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, movieDetailsActivity);
				ft.commit();
			}
		});
	}
}