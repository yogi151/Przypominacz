package com.example.maciapek.przypominacz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class FilmyAdapter extends ArrayAdapter<String>{

    FilmyAdapter(Context context, String[] title){
        super(context,R.layout.filmy_row_layout, title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater filmInflater = LayoutInflater.from(getContext());
        View customView = filmInflater.inflate(R.layout.filmy_row_layout, parent, false);

        String title = getItem(position);
        TextView filmTitle = (TextView)customView.findViewById(R.id.filmTitle);
        ImageView filmCover = (ImageView)customView.findViewById(R.id.filmCover);

        filmTitle.setText(title);
        //TODO: zmienić na plakat filmu
        filmCover.setImageResource(R.drawable.mybackground);

        return customView;
    }
}
