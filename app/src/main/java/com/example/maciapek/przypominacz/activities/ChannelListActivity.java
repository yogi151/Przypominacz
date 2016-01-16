package com.example.maciapek.przypominacz.activities;

import java.util.List;

import com.example.maciapek.przypominacz.ObservedChannelList;
import com.example.maciapek.przypominacz.R;
import com.example.maciapek.przypominacz.adapters.ChannelListAdapter;
import com.example.maciapek.przypominacz.api.FilmwebApi;
import com.example.maciapek.przypominacz.model.Channel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChannelListActivity extends Fragment {
	
	private ListView listViewChannel;
	private Context ctx;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//setContentView(R.layout.activity_channel_list);
		View rootview = inflater.inflate(R.layout.activity_channel_list, container, false);
		ctx = getActivity().getApplicationContext();
		
		FilmwebApi api = new FilmwebApi();
		final List<Channel> channelList = api.getChannels();
		
		if(channelList.isEmpty()) {
    		Toast.makeText(getActivity().getApplicationContext(), "Brak znalezionych kanałów", Toast.LENGTH_LONG).show();
    	}
    	
		listViewChannel = ( ListView )rootview.findViewById(R.id.channel_list);
		listViewChannel.setAdapter( new ChannelListAdapter(ctx, R.layout.channel_list_item, channelList));
		
		listViewChannel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	ObservedChannelList.addChannel((Channel)parent.getItemAtPosition(position));
			}
		});
		return rootview;
	}

}

