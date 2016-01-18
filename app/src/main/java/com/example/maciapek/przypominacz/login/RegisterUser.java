package com.example.maciapek.przypominacz.login;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Laura on 15.01.16.
 */
public class RegisterUser extends AsyncTask<Void,Void,Void>{
    String name;
    String pass;
    String email;
    RegisterPage activity;
    boolean regpos = true;

    public RegisterUser(RegisterPage activity, String name,String pass, String pass2, String email){
        if(pass.equals(pass2)){
        this.activity = activity;
        this.name = name;
        this.pass = pass;
        this.email = email;}
        else{
            regpos = false;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showDialog(RegisterPage.PLEASE_WAIT_DIALOG);
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(regpos){
            try {
                String s = "http://hclzima2015-2016.netau.net/userReg.php?name="+name+"&password="+pass+"&email="+email;
                InputStream is = new URL(s).openStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                regpos = false;
            } catch (IOException e) {
                e.printStackTrace();
                regpos = false;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.removeDialog(RegisterPage.PLEASE_WAIT_DIALOG);
        try {
            activity.checkIfDone(regpos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
