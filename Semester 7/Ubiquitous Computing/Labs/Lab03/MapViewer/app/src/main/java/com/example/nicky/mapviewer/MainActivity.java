package com.example.nicky.mapviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private GoogleMap map;
    private int dataBlock = 100;
    ArrayList<String> cityNames, longitute, latitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*cityNames = new ArrayList<String>();
        longitute = new ArrayList<String>();
        latitute = new ArrayList<String>();

        //map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        try {
            //InputStream is = getResources().getAssets().open("raw/cities.txt");
            //InputStreamis =getResources(R.res.)
            InputStream is = getResources().openRawResource(R.raw.cities);
            BufferedReader br = new BufferedReader(new InputStreamReader((is)));
            String line = null;
            int i = 0;
            while((line = br.readLine()) != null){
                String [] splitted = line.split("\\s+");
                if(splitted.length >= 2){
                    cityNames.set(i, splitted[0]);
                    longitute.set(i, splitted[1]);
                    latitute.set(i, splitted[2]);
                    i++;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

            Log.d("testing", cityNames.get(0));
            Log.d("testing", cityNames.get(1));
            Log.d("testing", cityNames.get(2));

        */
    }
}
