package com.example.maciapek.przypominacz.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maciapek.przypominacz.MainActivity;
import com.example.maciapek.przypominacz.R;

public class LoginPage extends AppCompatActivity {
    public EditText userName;
    public EditText pass;
    public static final int PLEASE_WAIT_DIALOG=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        this.userName = (EditText) findViewById(R.id.userName);
        this.pass = (EditText) findViewById(R.id.userPass);
    }

    public void clickLogin(View v){
        new UserLogin(this,this.userName.getText().toString(),this.pass.getText().toString()).execute();

    }

    public void clickGoTo(View v){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);

    }

    public Dialog onCreateDialog(int dialogId) {

        switch (dialogId) {
            case PLEASE_WAIT_DIALOG:
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Checking credentials");
                dialog.setMessage("Please wait....");
                dialog.setCancelable(true);
                return dialog;

            default:
                break;
        }

        return null;
    }

    public void checkCredentials(String p){
        if(!p.equals("")){
            //Intent intent = new Intent(this, UserProfile.class);
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bu = new Bundle();
            bu.putString("name",this.userName.getText().toString());
            bu.putString("email",p);
            intent.putExtras(bu);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT);
        }

    }
}
