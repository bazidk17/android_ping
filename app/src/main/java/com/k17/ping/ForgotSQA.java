package com.k17.ping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotSQA extends AppCompatActivity {
    EditText txtforgotSqaPhone,txtforgotSqaSQ,txtforgotSqaSA;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_sqa);

        txtforgotSqaPhone=(EditText)findViewById(R.id.txtforgotsqaPhone);
        txtforgotSqaSQ=(EditText)findViewById(R.id.txtforgotsqaSQ);
        txtforgotSqaSA=(EditText)findViewById(R.id.txtforgotsqaSA);


        Intent i=getIntent();
        phone=i.getStringExtra("EMAIL GIVEN");
        txtforgotSqaPhone.setText(phone);

        new ForgotSQAgetSQ(this,txtforgotSqaSQ).execute(phone);


    }

    public void gotoForgotCP(View v){
        String empty_check_sa=txtforgotSqaSA.getText().toString();
        if(empty_check_sa.isEmpty())
        {
            txtforgotSqaSA.setError("Mandatory Field");
        }
        else {
            new ForgotSQAActivity(this, txtforgotSqaSA).execute(phone);
        }
    }
}
