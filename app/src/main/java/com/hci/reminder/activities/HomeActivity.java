package com.hci.reminder.activities;

import com.hci.reminder.R;
import com.hci.reminder.enums.Type;
import com.hci.reminder.receiver.ReminderReceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity {

	ReminderReceiver r = new ReminderReceiver();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		r.SetAlarm(this);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		
        final Button button = (Button) findViewById(R.id.button1);
        final EditText editText = (EditText) findViewById(R.id.editText1);
        
        final Button button1 = (Button) findViewById(R.id.button2);
        final EditText editText1 = (EditText) findViewById(R.id.editText2);
        
        final Button button2 = (Button) findViewById(R.id.button3);
        final Button button3 = (Button) findViewById(R.id.button4);
        final Button button4 = (Button) findViewById(R.id.button5);
        final Button button5 = (Button) findViewById(R.id.button6);
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            	Bundle b = new Bundle();
            	b.putString("title", editText.getText().toString());
            	b.putString("type", Type.FILM.name());
            	intent.putExtras(b);
            	startActivity(intent);
            }
        });
        
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            	Bundle b = new Bundle();
            	b.putString("title", editText1.getText().toString());
            	b.putString("type", Type.SERIES.name());
            	intent.putExtras(b);
            	startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            	Bundle b = new Bundle();
            	b.putString("title", "");
            	b.putString("type", Type.POPULAR.name());
            	intent.putExtras(b);
            	startActivity(intent);
            }
        });
        
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            	Bundle b = new Bundle();
            	b.putString("title", "");
            	b.putString("type", Type.UPCOMMING.name());
            	intent.putExtras(b);
            	startActivity(intent);
            }
        });
        
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, ChannelListActivity.class);
            	startActivity(intent);
            }
        });
        
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            	Bundle b = new Bundle();
            	b.putString("title", "");
            	b.putString("type", Type.OBSERVED.name());
            	intent.putExtras(b);
            	startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
