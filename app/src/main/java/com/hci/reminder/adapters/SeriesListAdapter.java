package com.hci.reminder.adapters;

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

import com.hci.reminder.R;
import com.hci.reminder.model.Series;

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
        
        Series series = getItem(position);
        TextView seriesTittle = (TextView) convertView.findViewById(R.id.filmTittle);
        seriesTittle.setText(series.getPolishTitle());
             
        TextView seriesOriginalTittle = (TextView) convertView.findViewById(R.id.filmOriginalTittle);
        seriesOriginalTittle.setText(series.getTitle());
             
        ImageView seriesCover = (ImageView) convertView.findViewById(R.id.filmCoverImage);
        seriesCover.setImageBitmap(series.getCircleCover());

        return convertView;
    }
}