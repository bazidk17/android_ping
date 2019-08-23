package com.k17.ping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Login extends AppCompatActivity {

    EditText txtPhone,txtPassword;
    boolean valid;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtPhone=(EditText) findViewById(R.id.txtloginPhone);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        relativeLayout=(RelativeLayout) findViewById(R.id.rlLogin);
    }

    public void gotoHome(View v){
        String uphone = txtPhone.getText().toString();
        String upass = txtPassword.getText().toString();
        valid=true;
        if(uphone.length()<10){
            valid=false;
            txtPhone.setError("Phone Number must contain 10 digits");
        }
        if(upass.length()<5){
            valid=false;
            txtPassword.setError("Password must contain atleast 5 digits");
        }
        if(valid) {
            new LoginActivity(this,relativeLayout).execute(uphone, upass);
        }
    }

    public void gotoForgotRetrieve(View v){
        Intent i =new Intent(this,ForgotRetrieve.class);
        startActivity(i);
    }

    public void gotoSignUp(View v){
        Intent i=new Intent(this,Register.class);
        startActivity(i);
    }

}
