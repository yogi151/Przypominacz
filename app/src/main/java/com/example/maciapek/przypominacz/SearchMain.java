package com.example.maciapek.przypominacz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maciapek.przypominacz.activities.MovieListActivity;
import com.example.maciapek.przypominacz.api.ReminderApi;
import com.example.maciapek.przypominacz.enums.Type;

public class SearchMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_main);
        setContentView(R.layout.fragment_search_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText editText = (EditText)findViewById(R.id.search);
        ImageView searchButton = (ImageView)findViewById(R.id.searchButtonF);
     /*   searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchMain.this, MovieListActivity.class);
                Bundle b = new Bundle();
                b.putString("title", editText.getText().toString());
                b.putString("type", Type.FILM.name());
                intent.putExtras(b);
                startActivity(intent);
            }
        });*/

    /*    final EditText editText = (EditText)findViewById(R.id.search);
        final EditText editText1 = (EditText)findViewById(R.id.searchSeries);
        ImageView searchButtonF = (ImageView)findViewById(R.id.searchButtonF);
        ImageView searchButtonS = (ImageView)findViewById(R.id.searchButtonS);
        searchButtonF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchMain.this, MovieListActivity.class);
                Bundle b = new Bundle();
                b.putString("title", editText.getText().toString());
                b.putString("type", Type.FILM.name());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        searchButtonS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchMain.this, MovieListActivity.class);
                Bundle b = new Bundle();
                b.putString("title", editText1.getText().toString());
                b.putString("type", Type.SERIES.name());
                intent.putExtras(b);
                startActivity(intent);
            }
        });*/

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment list = new MovieListActivity();
                Bundle b = new Bundle();

                b.putString("title", editText.getText().toString());
                b.putString("type", Type.FILM.name());
                list.setArguments(b);
                //FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, list);
                ft.commit();
            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
