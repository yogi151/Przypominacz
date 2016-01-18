package com.example.maciapek.przypominacz.login;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;

import com.example.maciapek.przypominacz.R;

import java.util.ArrayList;

public class UserProfile extends ListActivity {
    public static final int PLEASE_WAIT_DIALOG=1;
    public EditText userName;
    public EditText userEmail;
    String name;
    String email;
    public ListView listView;
    public EditText movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.userName = (EditText) findViewById(R.id.userName3);
        this.userEmail = (EditText) findViewById(R.id.userEmail2);
        this.listView = (ListView) findViewById(android.R.id.list);
        this.movie = (EditText) findViewById(R.id.movie);

        try{
        Bundle bu = getIntent().getExtras();
        email = bu.getString("email");
        name = bu.getString("name");
        this.userEmail.setText(email);
        this.userName.setText(name);}
        catch(Exception e){}
    }

    public void clickLogout(View v){
        new UserInfo(this, this.userName.getText().toString(),1).execute();
       // Intent intent = new Intent(this, LoginPage.class);
       // startActivity(intent);

    }

    public void clickMovie(View v){
        new UserInfo(this, this.userName.getText().toString(),2).execute();
    }

    public Dialog onCreateDialog(int dialogId) {

        switch (dialogId) {
            case PLEASE_WAIT_DIALOG:
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Fetching user movies");
                dialog.setMessage("Please wait....");
                dialog.setCancelable(true);
                return dialog;

            default:
                break;
        }

        return null;
    }

    public void setUser(String n, String e){
        this.userName.setText(n);
        this.userEmail.setText(e);
    }

    public void setListView(ArrayList arrayList){
        ArrayAdapter adapter = new ArrayAdapter(getListView().getContext(), android.R.layout.simple_list_item_1,arrayList);
        getListView().setAdapter(adapter);
    }
     public String getMovie(){
         return this.movie.getText().toString();
     }
}
