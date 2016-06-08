package com.example.nicky.mapviewer;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserFragment extends ListFragment{

    boolean horizontalMode;
    private GoogleMap map;
    public static String[] cityNames = {"Dublin", "Kerry", "Belfast", "Cork", "Galway", "Wexford"};
    public static double[] latitute = {53.347860, 52.264007, 54.602755, 51.892171, 53.276533, 52.336521};
    public static double[] longitute = {-6.272487, -9.686990, -5.945180, -8.475068, -9.069362, -6.462855};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayAdapter <String> list = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, cityNames);
        setListAdapter(list);

        View mapView = getActivity().findViewById(R.id.map);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            horizontalMode = true;
        }

        if(horizontalMode){
            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(horizontalMode){
            map.clear();
            LatLng location = new LatLng(latitute[position], longitute[position]);
            map.addMarker(new MarkerOptions().position(location).title(cityNames[position]));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 6);
            map.animateCamera(update);
        }
        else{
            Intent intent = new Intent();
            intent.setClass(getActivity(), MapActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }

    }

}
