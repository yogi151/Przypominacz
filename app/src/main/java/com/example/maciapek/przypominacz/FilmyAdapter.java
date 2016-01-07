package com.example.maciapek.przypominacz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class FilmyAdapter extends ArrayAdapter<String>{

    private static class FilmyAdapterViewHolder {
        public String title;
        public TextView filmTitle;
        public ImageView filmCover;
    }

    FilmyAdapter(Context context, String[] title){
        super(context,R.layout.filmy_row_layout, title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        FilmyAdapterViewHolder holder;
        if (convertView == null){

            holder = new FilmyAdapterViewHolder();
            LayoutInflater filmInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = filmInflater.inflate(R.layout.filmy_row_layout, parent, false);
            holder.filmTitle = (TextView)convertView.findViewById(R.id.filmTitle);
            holder.filmCover = (ImageView)convertView.findViewById(R.id.filmCover);
            holder.title = getItem(position);

            convertView.setTag(holder);
        }else{
            holder = (FilmyAdapterViewHolder)convertView.getTag();
        }

        holder.title = getItem(position);
        holder.filmTitle.setText(holder.title);
        //TODO: zmienić na plakat filmu
        holder.filmCover.setImageResource(R.drawable.avatar);

        return convertView;
    }
}
