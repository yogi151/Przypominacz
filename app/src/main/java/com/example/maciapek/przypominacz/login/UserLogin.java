package com.example.maciapek.przypominacz.login;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Laura on 15.01.16.
 */
public class UserLogin extends AsyncTask<Void,Void,Void> {
    LoginPage activity;
    String name;
    String pass;
    String email;

    public UserLogin(LoginPage activity, String name, String pass){
        this.activity = activity;
        this.name = name;
        this.pass = pass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showDialog(LoginPage.PLEASE_WAIT_DIALOG);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            URL url = new URL("http://hclzima2015-2016.netau.net/userLog.php?name="+name+"&password="+pass);
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
                    email =jo.getString("email");
                }
            }
            urlConnection.disconnect();}
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.removeDialog(LoginPage.PLEASE_WAIT_DIALOG);
        activity.checkCredentials(email);
    }
}
