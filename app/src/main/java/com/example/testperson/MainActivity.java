package com.example.testperson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button BtnStart;
    RecyclerView recyclerView;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = (TextView) this.findViewById(R.id.textView);
        this.BtnStart = (Button) this.findViewById(R.id.BtnStart);
    }


    private boolean checkInternetConnection() {
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }

    public void downloadAndShowJson(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        textView.setVisibility(View.GONE);
        BtnStart.setVisibility(View.GONE);
        String jsonUrl = "https://api.myjson.com/bins/j4i38";

        ReadJson task = new ReadJson(this.json);

        task.execute(jsonUrl);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Person> pers = new ArrayList<Person>();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Person[] array = gson.fromJson(json, Person[].class);
        pers = new ArrayList<Person>(Arrays.asList(array));
        PersonAdapter personAdapter = new PersonAdapter(pers);
        recyclerView.setAdapter(personAdapter);
    }


}
