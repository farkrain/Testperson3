package com.example.testperson;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class ReadJson extends AsyncTask<String, Void, String>{
    private String json;

    public ReadJson(String json)  {
        this.json= json;
    }

    @Override
    protected String doInBackground(String... params) {
        String textUrl = params[0];

        InputStream in = null;
        BufferedReader br= null;
        try {
            URL url = new URL(textUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                br= new BufferedReader(new InputStreamReader(in));

                StringBuilder sb= new StringBuilder();
                String s= null;
                while((s= br.readLine())!= null) {
                    sb.append(s);
                    sb.append("\n");
                }
                return sb.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOstream.closeQuietly(in);
            IOstream.closeQuietly(br);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result  != null){
            try {
                json = result;
            }
            catch(Exception e)
            {Log.e("MyMessage", "Failed to fetch data json!");}

        } else{
            Log.e("MyMessage", "Failed to fetch data!");
        }
    }
}
