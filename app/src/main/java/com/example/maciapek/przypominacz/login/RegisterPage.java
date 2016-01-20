package com.example.maciapek.przypominacz.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maciapek.przypominacz.R;

public class RegisterPage extends AppCompatActivity {
    public static final int PLEASE_WAIT_DIALOG=1;
    public EditText userName;
    public EditText pass;
    public EditText pass2;
    public EditText email;
    String un="";
    String em="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        this.userName = (EditText) findViewById(R.id.userName2);
        this.pass = (EditText) findViewById(R.id.userPass2);
        this.pass2 = (EditText) findViewById(R.id.userPass3);
        this.email = (EditText) findViewById(R.id.userEmail);
    }

    public void clickRegister(View v){
        new RegisterUser(this,this.userName.getText().toString(),this.pass.getText().toString(), this.pass2.getText().toString(), this.email.getText().toString()).execute();


    }

    public Dialog onCreateDialog(int dialogId) {

        switch (dialogId) {
            case PLEASE_WAIT_DIALOG:
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Registration");
                dialog.setMessage("Please wait....");
                dialog.setCancelable(true);
                return dialog;

            default:
                break;
        }

        return null;
    }

    public void checkIfDone(boolean b) throws InterruptedException {
        if(b){
           /* Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            //new UserInfo(this,this.userName.getText().toString()).execute();
            Intent intent = new Intent(this, UserProfile.class);
            Thread.sleep(500);
            Bundle bu = new Bundle();
            bu.putString("name",un);
           // bu.putString("email",em);
            intent.putExtras(bu);
            startActivity(intent);*
           // finish();;*/
           Intent i = new Intent(this,LoginPage.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Registration failed",Toast.LENGTH_SHORT).show();
        }
    }

  /*  public void setCredential(String s1, String s2){
        this.un = s1;
        this.em = s2;
    }*/
}
