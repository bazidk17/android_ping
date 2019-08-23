package com.k17.ping;

/**
 * Created by bazid on 16-02-2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewPostActivity extends AsyncTask<String,Void,String>{
    private Context context;
    String test;
    ProgressDialog pd;


    public NewPostActivity(Context context) {
        this.context = context;

    }

    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setMessage("Posting");

        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        HttpClient httpclient=new DefaultHttpClient();
        String msg = (String)arg0[0];
        String phone = (String)arg0[1];
        String encImage = (String)arg0[2];
        String filename = (String)arg0[3];
        HttpPost httppost=new HttpPost("http://103.21.236.22:8080/Ping/postNewContent.jsp");

        try {

            List<NameValuePair> namevaluepair=new ArrayList<NameValuePair>();
            namevaluepair.add(new BasicNameValuePair("Phone", phone));
            namevaluepair.add(new BasicNameValuePair("Message", msg));
            namevaluepair.add(new BasicNameValuePair("filename", filename));
            namevaluepair.add(new BasicNameValuePair("image", encImage));

            httppost.setEntity(new UrlEncodedFormEntity(namevaluepair));
            HttpResponse httpresponse=httpclient.execute(httppost);
            int status=httpresponse.getStatusLine().getStatusCode();

            StringBuilder builder = new StringBuilder();
            HttpEntity entity = httpresponse.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line = null;
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();

        } catch (Exception e) {
            test=e.toString();
            return test;
        }

    }

    @Override
    protected void onPostExecute(String result){
        String temp=result.trim();
        pd.dismiss();
        if(temp.equals("Success")){
            Toast.makeText(context,"Posted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,temp,Toast.LENGTH_LONG).show();
        }
    }
}