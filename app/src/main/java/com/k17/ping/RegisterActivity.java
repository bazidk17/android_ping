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

public class RegisterActivity extends AsyncTask<String,Void,String>{
    private Context context;
    String test;
    ProgressDialog pd;

    public RegisterActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setTitle("Creating Account");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
            try{
                String username = (String)arg0[0];
                String phone = (String)arg0[1];
                String password = (String)arg0[2];
                String sq = (String)arg0[3];
                String sa = (String)arg0[4];

                String  str_url = "http://103.21.236.22:8080/Ping/registration.jsp?Name="+username+"&Phone="+phone+"&Password="+password+"&SQ="+sq+"&SA="+sa;
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
            Toast.makeText(context,"Account Created Successfully",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(context,Login.class);
            context.startActivity(i);
        }
        if(temp.equals("Fail")){
            Toast.makeText(context,"FAILED",Toast.LENGTH_SHORT).show();
        }
    }
}