package com.k17.ping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotCP extends AppCompatActivity {
    EditText txtpass,txtcpass;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_cp);
        txtpass=(EditText)findViewById(R.id.txtforgotCP);
        txtcpass=(EditText)findViewById(R.id.txtforgotCCP);
        Intent i=getIntent();
        phone=i.getStringExtra("ROOM1");
    }

    public void changeANDgotoLogin(View v){
        String pass=txtpass.getText().toString();
        String cpass=txtcpass.getText().toString();

        if(pass.length()<6){
            txtpass.setError("Password should contain atleast 6 digits");
        }
        else if (!cpass.equals(pass)){
            txtcpass.setError("Passwords do not match");
        }
        else {
            new ForgotCPActivity(this).execute(phone,pass);
        }
    }
}
