package com.example.nicky.mapviewer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by nicky on 30/11/2015.
 */
public class MapActivity extends AppCompatActivity {

    private GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        int position = getIntent().getExtras().getInt("position");
        map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.clear();
        LatLng location = new LatLng(UserFragment.latitute[position], UserFragment.longitute[position]);
        map.addMarker(new MarkerOptions().position(location).title(UserFragment.cityNames[position]));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 6);
        map.animateCamera(update);


    }
}