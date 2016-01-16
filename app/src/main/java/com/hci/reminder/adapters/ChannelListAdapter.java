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
import com.hci.reminder.model.Channel;

public class ChannelListAdapter extends ArrayAdapter<Channel> {
	
    private int resource;
    private LayoutInflater inflater;
    
    public ChannelListAdapter (Context ctx, int resourceId, List<Channel> objects) {
    	super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
    }
    
    @SuppressLint("ViewHolder")
	@Override
    public View getView (int position, View convertView, ViewGroup parent) {
    	
        convertView = ( RelativeLayout ) inflater.inflate(resource, null);
        
        Channel channel = getItem(position);
        TextView channelName = (TextView) convertView.findViewById(R.id.channelName);
        channelName.setText(channel.getName());
             
        ImageView channelLogo = (ImageView) convertView.findViewById(R.id.channelLogo);
        channelLogo.setImageBitmap(channel.getLogo());
        
        return convertView;
    }
}