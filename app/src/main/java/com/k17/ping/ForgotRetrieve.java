package com.k17.ping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotRetrieve extends AppCompatActivity {
    EditText txtForgotRetrievePhone;
    boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_retrieve);
        txtForgotRetrievePhone=(EditText)findViewById(R.id.txtforgetPhone);
    }

    public void gotoForgotSQA(View v){
        String phone=txtForgotRetrievePhone.getText().toString();

        valid=true;
        if(phone.length()<10){
            valid=false;
            txtForgotRetrievePhone.setError("Phone Number must contain 10 digits");
        }
        if(valid) {
            new ForgotRetrieveActivity(this).execute(phone);
        }
    }
}
