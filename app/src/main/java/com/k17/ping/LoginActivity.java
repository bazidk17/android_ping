package com.k17.ping;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends AsyncTask<String,Void,String>{
    private Context context;
    String test;
    ProgressDialog pd;
    RelativeLayout rl;


    public LoginActivity(Context context, RelativeLayout rl) {
        this.context = context;
        this.rl=rl;
    }
    String phone;
    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setTitle("Loading");
        pd.setMessage("Please wait while we log you in");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {

            try{
                phone = (String)arg0[0];
                String password = (String)arg0[1];

                String  str_url = "http://103.21.236.22:8080/Ping/login.jsp?Phone="+phone+"&Password="+password;
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                String x = "";

                StringBuffer content = new StringBuffer();
                while((x = r.readLine()) != null)
                {
                    content.append(x);
                }
                in.close();
                r.close();
             return content.toString();

            } catch(Exception e){
                test=e.toString();
                return test;
            }
    }

    @Override
    protected void onPostExecute(String result){
        pd.dismiss();
        String temp=result.trim();
        if(temp.equals("Success")){
            Snackbar.make(rl, "Welcome", Snackbar.LENGTH_SHORT).show();
            Intent i=new Intent(context,Homepage.class);
            i.putExtra("loggedInUser",phone);
            context.startActivity(i);
        }
        else{
            Toast.makeText(context,"Phone Number or Password is incorrect",Toast.LENGTH_SHORT).show();
        }
    }
}