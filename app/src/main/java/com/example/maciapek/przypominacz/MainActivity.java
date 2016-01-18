package com.example.maciapek.przypominacz;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;


import com.example.maciapek.przypominacz.activities.ChannelListActivity;
import com.example.maciapek.przypominacz.activities.MovieListActivity;
import com.example.maciapek.przypominacz.enums.Type;
import com.example.maciapek.przypominacz.receiver.ReminderReceiver;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;
    ReminderReceiver r = new ReminderReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        r.SetAlarm(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } if (!viewIsAtHome) { //if the current view is not the home page
            displayView(R.id.nav_home);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchMain.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Bundle b = new Bundle();
        switch (viewId) {
            case R.id.nav_home:
                fragment = new MovieListActivity();
                b.putString("title", "");
                b.putString("type", Type.UPCOMMING.name());
                fragment.setArguments(b);
                viewIsAtHome = true;
                break;

            case R.id.nav_filmy:
                title  = "Filmy";
                fragment = new MovieListActivity();
                b.putString("title", "");
                b.putString("type", Type.POPULAR.name());
                fragment.setArguments(b);
                viewIsAtHome = false;
                break;

            case R.id.nav_seriale:
                title = "Seriale";
                fragment = new MovieListActivity();
                b.putString("title", "");
                b.putString("type", Type.POPULAR.name());
                fragment.setArguments(b);
                viewIsAtHome = false;
                break;

            case R.id.nav_lista_kanalow:
                fragment = new ChannelListActivity();
                title = "Lista kanałów";
                b.putString("type", "all");
                fragment.setArguments(b);
                viewIsAtHome = false;
                break;

            case R.id.nav_obserwowane:
                fragment = new Obserwowane_fragment();
                title = "Obserwowane";


                viewIsAtHome = false;
                break;

            case R.id.nav_moje_kanaly:
                title = "Moje kanały";
                fragment = new ChannelListActivity();
                b.putString("type", "observed");
                fragment.setArguments(b);
                viewIsAtHome = false;
                break;


        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       displayView(item.getItemId());
        return true;
    }


}