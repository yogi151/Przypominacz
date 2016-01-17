package com.example.maciapek.przypominacz.activities;

import java.util.ArrayList;

import com.example.maciapek.przypominacz.CacheList;
import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.api.ReminderApi;
import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.model.Film;
import com.example.maciapek.przypominacz.model.Person;
import com.example.maciapek.przypominacz.model.Series;
import com.example.maciapek.przypominacz.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsActivity extends Fragment {
	private Boolean c = true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//setContentView(R.layout.activity_movie_details);
		View rootview = inflater.inflate(R.layout.activity_movie_details, container, false);
		//Bundle b = getIntent().getExtras();
		Bundle b = getArguments();
		int id = b.getInt("id");
		final Film film = CacheList.getFilm(id);		
		
		final ImageView cover = (ImageView) rootview.findViewById(R.id.cover);
		final TextView title = (TextView) rootview.findViewById(R.id.engTitle);
		final TextView originalTitle = (TextView) rootview.findViewById(R.id.originalTitle);
		final TextView rate = (TextView) rootview.findViewById(R.id.rate);
		final TextView votes = (TextView) rootview.findViewById(R.id.numberOfVotes);
		final TextView duration = (TextView) rootview.findViewById(R.id.duration);
		final TextView genre = (TextView) rootview.findViewById(R.id.genre);
		final TextView countries = (TextView) rootview.findViewById(R.id.countries);
		final TextView premiere = (TextView) rootview.findViewById(R.id.premiere);
		final TextView seasonsCount = (TextView) rootview.findViewById(R.id.textView9);
		final TextView episodesCount = (TextView) rootview.findViewById(R.id.textView10);
		final TextView description = (TextView) rootview.findViewById(R.id.description);
		final TextView synopsis = (TextView) rootview.findViewById(R.id.synopsis);
		final Button reviewButton = (Button) rootview.findViewById(R.id.button1);
		final TextView actors = (TextView) rootview.findViewById(R.id.actors);


		//TODO: warunek czy obserwowany
		final ImageView addButton = (ImageView) rootview.findViewById(R.id.addOrRemove);
				addButton.setOnClickListener(new View.OnClickListener() {

					//TODO: do ifa sprawdzenie warunku czy w obserwowanych
					@Override
					public void onClick(View v) {
						//ReminderApi.observeFilm(film);
						if (c) {
							Toast.makeText(getActivity().getApplicationContext(), R.string.added, Toast.LENGTH_SHORT).show();
							addButton.setImageResource(R.drawable.remove);
							ReminderApi.observeFilm(film);
							c = false;
						} else {
							Toast.makeText(getActivity().getApplicationContext(), R.string.removed, Toast.LENGTH_SHORT).show();
							addButton.setImageResource(R.drawable.add);
							ReminderApi.stopObserve(film);
							c = true;
						}
					}

				});;
		
		cover.setImageBitmap(film.getCover());
		title.setText(film.getPolishTitle() + System.getProperty("line.separator") + "(" + film.getYear() + ")");
		originalTitle.setText(film.getTitle());
		rate.setText("Ocena: " + film.getRate());
		votes.setText("Liczba głosów: " + film.getVotes());
		duration.setText("Czas trwania: " + film.getDuration() + " min.");
		premiere.setText("Premiera: " +Utils.getDate(film.getPremiereCountry()) + " (Polska) " + Utils.getDate(film.getPremiereWorld()) + " (Świat)");
		genre.setText("Gatunek: " + Utils.toString(film.getGenre()));
		countries.setText("Kraj produkcji: " + film.getCountries());
		description.setText("Opis: " + System.getProperty("line.separator") + film.getDescription());
		synopsis.setText("Streszczenie: " + System.getProperty("line.separator") + film.getSynopsis());
		
		if(film.getFilmType() == Type.SERIES.getCode()) {
			seasonsCount.setText("Ilość sezonów: " + ((Series)film).getSeasonsCount()
					+ System.getProperty("line.separator") + "Następny odcinek: " + ((Series)film).getNextEpisode() + " " + Utils.getDate(((Series)film).getNextEpisodeDate()));
			episodesCount.setText("Ilość odcinków: " + ((Series)film).getEpisodesCount());
		}
		
		if(film.getHasReview()) {
			reviewButton.setVisibility(View.VISIBLE);
		}
		
		// TODO jeszcze fotki ich strzelic :)
		ArrayList<Person> actorsList = film.getActors();
		StringBuilder sb = new StringBuilder();
		sb.append("Aktorzy: ");
		sb.append(System.getProperty("line.separator"));
		for(Person actor : actorsList) {
			sb.append(actor.getName() + " - " + actor.getRole());
			sb.append(System.getProperty("line.separator"));
		}
		
		ArrayList<Person> directors = film.getDirectors();
		sb.append("Reżyseria: ");
		sb.append(System.getProperty("line.separator"));
		for(Person director : directors) {
			sb.append(director.getName());
			sb.append(System.getProperty("line.separator"));
		}
		
		ArrayList<Person> screenwriters = film.getScreenwriters();
		sb.append("Scenariusz: ");
		sb.append(System.getProperty("line.separator"));
		for(Person screenwriter : screenwriters) {
			sb.append(screenwriter.getName());
			sb.append(System.getProperty("line.separator"));
		}
		
		actors.setText(sb.toString());
		
		reviewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
            	Intent intent = new Intent(getActivity().getApplicationContext(), ReviewActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", film.getId());
            	intent.putExtras(b);
            	startActivity(intent);
				/*ReviewActivity reviewActivity = new ReviewActivity();
				Bundle b = new Bundle();
				b.putInt("id", film.getId());
				reviewActivity.setArguments(b);
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, reviewActivity);
				ft.commit();*/
			}
		});
		return rootview;
	}
	
}
