package com.example.maciapek.przypominacz.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.api.ReminderApi;
import com.example.maciapek.przypominacz.model.Series;

import static com.example.maciapek.przypominacz.ObservedFilmList.isObserved;

public class SeriesListAdapter extends ArrayAdapter<Series> {
	
    private int resource;
    private LayoutInflater inflater;
    
    public SeriesListAdapter (Context ctx, int resourceId, List<Series> objects) {
    	super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
    }
    
    @SuppressLint("ViewHolder")
	@Override
    public View getView (int position, View convertView, ViewGroup parent) {
    	
        convertView = ( RelativeLayout ) inflater.inflate(resource, null);
        
        final Series series = getItem(position);
        TextView seriesTittle = (TextView) convertView.findViewById(R.id.filmTittle);
        seriesTittle.setText(series.getPolishTitle());
             
        TextView seriesOriginalTittle = (TextView) convertView.findViewById(R.id.filmOriginalTittle);
        seriesOriginalTittle.setText(series.getTitle());
             
        ImageView seriesCover = (ImageView) convertView.findViewById(R.id.filmCoverImage);
        seriesCover.setImageBitmap(series.getCircleCover());


        final ImageView addButton = (ImageView) convertView.findViewById(R.id.addOrRemove);
        if(isObserved(series.getId())) {

            addButton.setImageResource(R.drawable.minus);
        }else {

            addButton.setImageResource(R.drawable.plus);
        }
        addButton.setTag(new Integer(position));
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int id = series.getId();
                Boolean c = isObserved(id);


                if (!c) {
                    Toast.makeText(getContext(), R.string.added, Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.minus);
                    ReminderApi.observeFilm(series);
                    c = isObserved(id);

                } else {
                    Toast.makeText(getContext(), R.string.removed, Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.plus);
                    ReminderApi.stopObserve(series);
                    c = isObserved(id);
                }
            }
        });

        return convertView;
    }
}