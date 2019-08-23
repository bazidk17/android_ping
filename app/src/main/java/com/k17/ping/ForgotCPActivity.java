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

public class ForgotCPActivity extends AsyncTask<String,Void,String>{
    private Context context;
    String test;
    ProgressDialog pd;

    public ForgotCPActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
        pd=new ProgressDialog(context);
        pd.setTitle("Applying Changes");
        pd.setMessage("Updating your password");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... arg0) {

        try{
            String phone = (String)arg0[0];
            String password = (String)arg0[1];

            String  str_url = "http://103.21.236.22:8080/Ping/forgotCP.jsp?Phone="+phone+"&Password="+password;
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
            Toast.makeText(context,"Password has been updated",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(context,Login.class);

            context.startActivity(i);
        }
        if(temp.equals("Fail")){
            Toast.makeText(context,"FAILED",Toast.LENGTH_SHORT).show();
        }
    }
}