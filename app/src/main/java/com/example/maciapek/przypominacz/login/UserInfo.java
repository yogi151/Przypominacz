package com.example.maciapek.przypominacz.login;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Laura on 15.01.16.
 */
public class UserInfo extends AsyncTask<Void,Void,Void>{
    UserProfile activity;
    String name;
    ArrayList arrayList;
    int z=0;

    public UserInfo(UserProfile up, String name,int z){
        this.activity = up;
        this.name = name;
        this.z = z;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showDialog(UserProfile.PLEASE_WAIT_DIALOG);
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(z ==1) {
            arrayList = populate();
        }
        if(z==2){
            addMovie();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(z==1){
        activity.setListView(arrayList);}
        super.onPostExecute(aVoid);
        activity.removeDialog(UserProfile.PLEASE_WAIT_DIALOG);
    }

    public void addMovie(){
        try{
            String s = "http://hclzima2015-2016.netau.net/saveMovie.php?name="+name+"&movie="+activity.getMovie();
            InputStream is = new URL(s).openStream();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ArrayList populate(){
        ArrayList mo = new ArrayList();
        try{
            URL url = new URL("http://hclzima2015-2016.netau.net/userMovies.php?name="+name);
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));

            String next;
            while ((next = bufferedReader.readLine()) != null) {
                JSONArray ja = new JSONArray(next);

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    mo.add(jo.getString("movie"));
                }
            }

            urlConnection.disconnect();}
        catch (Exception e){
            e.printStackTrace();
        }
        return mo;
    }
}
