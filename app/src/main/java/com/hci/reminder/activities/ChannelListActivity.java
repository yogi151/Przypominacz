package com.hci.reminder.activities;

import java.util.List;

import com.hci.reminder.ObservedChannelList;
import com.hci.reminder.R;
import com.hci.reminder.adapters.ChannelListAdapter;
import com.hci.reminder.api.FilmwebApi;
import com.hci.reminder.model.Channel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChannelListActivity extends Activity {
	
	private ListView listViewChannel;
	private Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_list);
		
		ctx = this;
		
		FilmwebApi api = new FilmwebApi();
		final List<Channel> channelList = api.getChannels();
		
		if(channelList.isEmpty()) {
    		Toast.makeText(ChannelListActivity.this, "Brak znalezionych kana³ów", Toast.LENGTH_LONG).show();
    	}
    	
		listViewChannel = ( ListView ) findViewById(R.id.channel_list);
		listViewChannel.setAdapter( new ChannelListAdapter(ctx, R.layout.channel_list_item, channelList));
		
		listViewChannel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
            	ObservedChannelList.addChannel((Channel)parent.getItemAtPosition(position));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.channel_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
