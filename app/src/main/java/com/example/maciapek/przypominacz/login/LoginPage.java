package com.example.maciapek.przypominacz.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maciapek.przypominacz.MainActivity;
import com.example.maciapek.przypominacz.R;

public class LoginPage extends AppCompatActivity {
    public EditText userName;
    public EditText pass;
    public static final int PLEASE_WAIT_DIALOG=1;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        this.userName = (EditText) findViewById(R.id.userName);
        this.pass = (EditText) findViewById(R.id.userPass);

        register = (Button)findViewById(R.id.goToRegisterButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(i);
            }
        });
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
