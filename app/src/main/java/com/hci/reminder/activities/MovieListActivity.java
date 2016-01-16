package com.hci.reminder.activities;

import java.util.List;

import com.hci.reminder.R;
import com.hci.reminder.adapters.FilmListAdapter;
import com.hci.reminder.adapters.SeriesListAdapter;
import com.hci.reminder.api.FilmwebApi;
import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.Series;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MovieListActivity extends Activity {
	
   private ListView listViewFilm;
   private Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_list);
		
		ctx = this;
		
		Bundle b = getIntent().getExtras();
		String title = b.getString("title");
		Type type = Type.valueOf(b.getString("type"));

		if(Type.FILM == type) {
			addFilmListView(title);
		}
		else if(Type.SERIES == type) {
			addSeriesListView(title);
		}
		else if(Type.POPULAR == type) {
			addPopularListView();
		}
		else if(Type.UPCOMMING == type) {
			addUpcommingListView();
		}
		else if(Type.OBSERVED == type) {
			addObservedListView();
		}
	}
	
	private void addFilmListView(String title) {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getFilmList(title);
    	if(filmList.isEmpty()) {
    		Toast.makeText(MovieListActivity.this, "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);
			}
		});
	}
	
	private void addSeriesListView(String title) {
		FilmwebApi api = new FilmwebApi();
    	List<Series> seriesList = api.getSeriesList(title);
    	if(seriesList.isEmpty()) {
    		Toast.makeText(MovieListActivity.this, "Brak znalezionych seriali", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) findViewById(R.id.film_list);
        listViewFilm.setAdapter( new SeriesListAdapter(ctx, R.layout.film_list_item, seriesList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);
			}
		});
	}
	
	private void addPopularListView() {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getPopularFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(MovieListActivity.this, "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);
			}
		});
	}
	
	private void addUpcommingListView() {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getUpcommingFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(MovieListActivity.this, "Brak znalezionych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);
			}
		});
	}
	
	private void addObservedListView() {
		FilmwebApi api = new FilmwebApi();
    	List<Film> filmList = api.getObservedFilms();
    	if(filmList.isEmpty()) {
    		Toast.makeText(MovieListActivity.this, "Brak obserwowanych filmów", Toast.LENGTH_LONG).show();
    	}
    	
    	listViewFilm = ( ListView ) findViewById(R.id.film_list);
        listViewFilm.setAdapter( new FilmListAdapter(ctx, R.layout.film_list_item, filmList));
        
        listViewFilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", ((Film) parent.getItemAtPosition(position)).getId());
            	intent.putExtras(b);
            	startActivity(intent);
			}
		});
	}
}