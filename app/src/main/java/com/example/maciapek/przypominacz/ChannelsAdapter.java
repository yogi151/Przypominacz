package com.example.maciapek.przypominacz;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChannelsAdapter extends ArrayAdapter<String> {


    ChannelsAdapter(Context context, String[] title){
        super(context,R.layout.kanal_row_layout, title);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ChannelsAdapterViewHolder holder;
        if (convertView == null){

            holder = new ChannelsAdapterViewHolder();
            LayoutInflater filmInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = filmInflater.inflate(R.layout.kanal_row_layout, parent, false);
            holder.channelName = (TextView)convertView.findViewById(R.id.channelName);
            holder.title = getItem(position);
            convertView.setTag(holder);
        }else{
            holder = (ChannelsAdapterViewHolder)convertView.getTag();
        }

        holder.title = getItem(position);
        holder.channelName.setText(holder.title);

        holder.position = position;

        return convertView;
    }

    private static class ChannelsAdapterViewHolder {
        public String title;
        public int position;
        public TextView channelName;
    }

}
