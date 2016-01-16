package com.hci.reminder.activities;

import java.util.ArrayList;

import com.hci.reminder.CacheList;
import com.hci.reminder.R;
import com.hci.reminder.api.ReminderApi;
import com.hci.reminder.enums.Type;
import com.hci.reminder.model.Film;
import com.hci.reminder.model.Person;
import com.hci.reminder.model.Series;
import com.hci.reminder.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_details);
		
		Bundle b = getIntent().getExtras();
		int id = b.getInt("id");
		final Film film = CacheList.getFilm(id);		
		
		final ImageView cover = (ImageView) findViewById(R.id.imageView1);
		final TextView title = (TextView) findViewById(R.id.textView1);
		final TextView originalTitle = (TextView) findViewById(R.id.textView5);
		final TextView rate = (TextView) findViewById(R.id.textView2);
		final TextView votes = (TextView) findViewById(R.id.textView3);
		final TextView duration = (TextView) findViewById(R.id.textView4);
		final TextView genre = (TextView) findViewById(R.id.textView6);
		final TextView countries = (TextView) findViewById(R.id.textView7);
		final TextView premiere = (TextView) findViewById(R.id.textView8);
		final TextView seasonsCount = (TextView) findViewById(R.id.textView9);
		final TextView episodesCount = (TextView) findViewById(R.id.textView10);
		final TextView description = (TextView) findViewById(R.id.textView11);
		final TextView synopsis = (TextView) findViewById(R.id.textView12);
		final Button reviewButton = (Button) findViewById(R.id.button1);
		final TextView actors = (TextView) findViewById(R.id.textView13);
		
		final ImageView addButton = (ImageView) findViewById(R.id.imageView2);
				addButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						ReminderApi.observeFilm(film);						
					}
					
				});;
		
		cover.setImageBitmap(film.getCover());
		title.setText(film.getPolishTitle() + System.getProperty("line.separator") + "(" + film.getYear() + ")");
		originalTitle.setText(film.getTitle());
		rate.setText("Ocena: " + film.getRate());
		votes.setText("Liczba g³osów: " + film.getVotes());
		duration.setText("Czas trwania: " + film.getDuration() + " min.");
		premiere.setText("Premiera: " +Utils.getDate(film.getPremiereCountry()) + " (Polska) " + Utils.getDate(film.getPremiereWorld()) + " (œwiat)");
		genre.setText("Gatunek: " + Utils.toString(film.getGenre()));
		countries.setText("Kraj produkcji: " + film.getCountries());
		description.setText("Opis: " + System.getProperty("line.separator") + film.getDescription());
		synopsis.setText("Streszczenie: " + System.getProperty("line.separator") + film.getSynopsis());
		
		if(film.getFilmType() == Type.SERIES.getCode()) {
			seasonsCount.setText("Iloœæ sezonów: " + ((Series)film).getSeasonsCount() 
					+ System.getProperty("line.separator") + "Nastêpny odcinek: " + ((Series)film).getNextEpisode() + " " + Utils.getDate(((Series)film).getNextEpisodeDate()));
			episodesCount.setText("Iloœæ odcinków: " + ((Series)film).getEpisodesCount());
		}
		
		if(film.getHasReview()) {
			reviewButton.setVisibility(View.VISIBLE);
		}
		
		// TODO jeszcze fotki ich strzeliæ :)
		ArrayList<Person> actorsList = film.getActors();
		StringBuilder sb = new StringBuilder();
		sb.append("Aktorzy: ");
		sb.append(System.getProperty("line.separator"));
		for(Person actor : actorsList) {
			sb.append(actor.getName() + " - " + actor.getRole());
			sb.append(System.getProperty("line.separator"));
		}
		
		ArrayList<Person> directors = film.getDirectors();
		sb.append("Re¿yseria: ");
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
            	Intent intent = new Intent(MovieDetailsActivity.this, ReviewActivity.class);
            	Bundle b = new Bundle();
            	b.putInt("id", film.getId());
            	intent.putExtras(b);
            	startActivity(intent);				
			}
		});
	}
	
}
