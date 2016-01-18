package com.example.maciapek.przypominacz.adapters;

import java.util.List;

import com.example.maciapek.przypominacz.CacheList;
import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.api.ReminderApi;
import com.example.maciapek.przypominacz.model.Film;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.maciapek.przypominacz.ObservedFilmList.isObserved;

public class FilmListAdapter extends ArrayAdapter<Film> {

    private int resource;
    private LayoutInflater inflater;
    private Context context;

    
    public FilmListAdapter (Context ctx, int resourceId, List<Film> objects) {
    	super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context =  ctx;
    }
    
    @SuppressLint("ViewHolder")
	@Override
    public View getView (int position, View convertView, ViewGroup parent) {
    	
        convertView = ( RelativeLayout ) inflater.inflate(resource, null);
        
        final Film film = getItem(position);

        TextView filmTittle = (TextView) convertView.findViewById(R.id.filmTittle);
        filmTittle.setText(film.getPolishTitle());
             
        TextView filmOriginalTittle = (TextView) convertView.findViewById(R.id.filmOriginalTittle);
        filmOriginalTittle.setText(film.getTitle());
             
        ImageView filmCover = (ImageView) convertView.findViewById(R.id.filmCoverImage);
        filmCover.setImageBitmap(film.getCircleCover());

        //TODO: warunek ifa czy w obserwowanych
        final ImageView addButton = (ImageView) convertView.findViewById(R.id.addOrRemove);
        if(isObserved(film.getId())) {

            addButton.setImageResource(R.drawable.minus);
        }else {

            addButton.setImageResource(R.drawable.plus);
        }
        addButton.setTag(new Integer(position));
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int id = film.getId();
                Boolean c = isObserved(id);


                if (!c) {
                    Toast.makeText(context, R.string.added, Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.minus);
                    ReminderApi.observeFilm(film);
                    c = isObserved(id);

                } else {
                    Toast.makeText(context, R.string.removed, Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.plus);
                    ReminderApi.stopObserve(film);
                    c = isObserved(id);
                }
            }
        });


        return convertView;
    }
}