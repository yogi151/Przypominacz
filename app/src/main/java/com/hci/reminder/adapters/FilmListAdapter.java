package com.hci.reminder.adapters;

import java.util.List;

import com.hci.reminder.R;
import com.hci.reminder.model.Film;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FilmListAdapter extends ArrayAdapter<Film> {
	
    private int resource;
    private LayoutInflater inflater;
    
    public FilmListAdapter (Context ctx, int resourceId, List<Film> objects) {
    	super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
    }
    
    @SuppressLint("ViewHolder")
	@Override
    public View getView (int position, View convertView, ViewGroup parent) {
    	
        convertView = ( RelativeLayout ) inflater.inflate(resource, null);
        
        Film film = getItem(position);
        TextView filmTittle = (TextView) convertView.findViewById(R.id.filmTittle);
        filmTittle.setText(film.getPolishTitle());
             
        TextView filmOriginalTittle = (TextView) convertView.findViewById(R.id.filmOriginalTittle);
        filmOriginalTittle.setText(film.getTitle());
             
        ImageView filmCover = (ImageView) convertView.findViewById(R.id.filmCoverImage);
        filmCover.setImageBitmap(film.getCircleCover());

        return convertView;
    }
}