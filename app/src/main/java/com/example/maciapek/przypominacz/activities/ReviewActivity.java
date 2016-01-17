package com.example.maciapek.przypominacz.activities;

import com.example.maciapek.przypominacz.CacheList;
import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.model.Film;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);

		Bundle b = getIntent().getExtras();
		int id = b.getInt("id");
		Film film = CacheList.getFilm(id);

		final TextView review = (TextView) findViewById(R.id.textView1);

		review.setText(film.getReview());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


/*
public class ReviewActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//setContentView(R.layout.activity_review);

		//Bundle b = getIntent().getExtras();
		View rootview = inflater.inflate(R.layout.activity_review, container, false);
		Bundle b = getArguments();

		int id = b.getInt("id");
		Film film = CacheList.getFilm(id);

		final TextView review = (TextView) rootview.findViewById(R.id.textView1);

		review.setText(film.getReview());

		return rootview;
	}
}
*/
