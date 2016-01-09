package com.example.maciapek.przypominacz;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChannelProgramAdapter extends ArrayAdapter<String> {

    private String[] time;
    ChannelProgramAdapter(Context context, String[] title, String[] time){
        super(context,R.layout.moj_kanal_row_layout, title);
        this.time = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ChannelProgramAdapterViewHolder holder;
        if (convertView == null){

            holder = new ChannelProgramAdapterViewHolder();
            LayoutInflater filmInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = filmInflater.inflate(R.layout.moj_kanal_row_layout, parent, false);
            holder.programTitle = (TextView)convertView.findViewById(R.id.filmTitle);
            holder.programTime = (TextView)convertView.findViewById(R.id.programTime);
            holder.title = getItem(position);
            holder.time = time[position];
            convertView.setTag(holder);
        }else{
            holder = (ChannelProgramAdapterViewHolder)convertView.getTag();
        }

        holder.title = getItem(position);
        holder.programTitle.setText(holder.title);
        holder.time = time[position];
        holder.programTime.setText(holder.time);

        holder.position = position;

        return convertView;
    }

    private static class ChannelProgramAdapterViewHolder {
        public String title;
        public String time;
        public int position;
        public TextView programTitle;
        public TextView programTime;
    }

}
