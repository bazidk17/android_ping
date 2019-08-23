package com.k17.ping;

/**
 * Created by bazid on 16-02-2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForgotRetrieveActivity extends AsyncTask<String,Void,String>{
    private Context context;
    String test,phone;
    ProgressDialog pd;


    public ForgotRetrieveActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setTitle("Loading");
        pd.setMessage("Please wait while we check our records");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {


            try{

                phone = (String)arg0[0];


                String  str_url = "http://103.21.236.22:8080/Ping/forgotretrieve.jsp?Phone="+phone;
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
        String temp=result.trim();
        pd.dismiss();

        if(temp.equals("Success")){
            Intent i=new Intent(context,ForgotSQA.class);
            i.putExtra("EMAIL GIVEN",phone);
            context.startActivity(i);

        }
        else if(temp.equals("Fail")){
            Toast.makeText(context,"User doesnt Exist",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,temp,Toast.LENGTH_SHORT).show();
        }

    }
}