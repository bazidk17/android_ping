package com.k17.ping;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by bazid on 18-03-2018.
 */

public class MyPostActivity extends AsyncTask<String,Void,String> {
    private Context context;
    String test;
    List<Post> postList;
    PostListAdapter postListAdapter;
    ListView listView;
    ProgressDialog pd;
    String phone;

    public MyPostActivity(Context context, List<Post> postList, PostListAdapter postListAdapter, ListView listView) {
        this.context = context;
        this.postList=postList;
        this.postListAdapter=postListAdapter;
        this.listView=listView;
    }

    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setTitle("Loading");
        pd.setMessage("Please wait while we retrieve your requested posts");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {


        try{
            phone = (String)arg0[0];

            String  str_url = "http://103.21.236.22:8080/Ping/getSpecifiedContent.jsp?Phone="+phone;
            URL url = new URL(str_url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            String x = "";

            StringBuilder content = new StringBuilder();
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

        if (temp.equals("0")){
            Toast.makeText(context,"User has not posted any content",Toast.LENGTH_SHORT).show();
        }
        else if (temp.equals("No User")){
            AlertDialog.Builder adb=new AlertDialog.Builder(context);
            adb.setTitle("No User Found");
            adb.setMessage("Would you like to invite the user?");
            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent sendSMS=new Intent(Intent.ACTION_VIEW);
                    sendSMS.setType("vnd.android-dir/mms-sms");
                    sendSMS.putExtra("address",phone.toString());
                    sendSMS.putExtra("sms_body","Install Ping. :)");
                    context.startActivity(sendSMS);

                }
            });
            adb.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog ad=adb.create();
            ad.setCancelable(false);
            ad.show();
        }
        else {
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject oneObject = new JSONObject(jArray.getString(i));
                    // Pulling items from the array
                    int col1 = Integer.parseInt(oneObject.getString("Pid"));
                    String col2 = oneObject.getString("Ptext");
                    String col3 = oneObject.getString("Pdate");
                    String col4 = oneObject.getString("Ptime");
                    Bitmap col5=null;
                    String encImageReceived = oneObject.getString("Pimage");

                    byte[] decodedString = Base64.decode(encImageReceived, Base64.DEFAULT);
                    col5 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    postList.add(new Post(col1, col2, col3, col4,col5));

                }
                postListAdapter = new PostListAdapter(context, postList);
                listView.setAdapter(postListAdapter);
            } catch (JSONException e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }
        }


    }
}