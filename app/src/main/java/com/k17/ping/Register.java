package com.k17.ping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity {

    EditText txtUser,txtPhone,txtPassword,txtSA;
    Spinner spinnerSQ;
    boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUser=(EditText)findViewById(R.id.txtregUsername);
        txtPhone=(EditText)findViewById(R.id.txtregPhone);

        txtPassword=(EditText)findViewById(R.id.txtregPassword);
        spinnerSQ=(Spinner)findViewById(R.id.spinnerSQ);

        txtSA=(EditText)findViewById(R.id.txtregSA);
    }

    public void gotoLogin(View v){
        String name=txtUser.getText().toString();
        String phone=txtPhone.getText().toString();

        String pass=txtPassword.getText().toString();
        String sq=spinnerSQ.getSelectedItem().toString();
        String sa=txtSA.getText().toString();
        valid=true;
        if (name.length()==0) {
            valid=false;
            txtUser.setError("Cannot be Empty");
        }
        if (phone.length()<10) {
            valid=false;
            txtPhone.setError("Number should contain 10 digits");
        }

        if (pass.length()<5) {
            valid=false;
            txtPassword.setError("Password must contain atleast 5 digits");
        }
        if (sa.length()==0) {
            valid = false;
            txtSA.setError("Cannot be Empty");
        }
        if(valid) {
            new RegisterActivity(this).execute(name, phone, pass, sq, sa);
        }

    }
}
