package com.example.nicky.rainfallforecast;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.IOException;
import java.security.Permission;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nicky on 05/12/2015.
 */
public class Settings extends AppCompatActivity {

    Button currentLocationButton;
    LocationManager locManager;
    LocListener locListener;
    double latitute = 50.5;
    double longitute = 25.5;
    DBHandler dbHandler;
    NumberPicker dayNumPicker;
    EditText addressText, rainfallText;
    boolean usingCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        currentLocationButton = (Button) findViewById(R.id.currentLocationButton);
        locListener = new LocListener();
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        }
        else{
            getCurrentLocation();
        }

        addressText = (EditText) findViewById(R.id.address);
        dayNumPicker = (NumberPicker) findViewById(R.id.dayNum);
        dayNumPicker.setMinValue(1);
        dayNumPicker.setMaxValue(16);
        rainfallText = (EditText)findViewById(R.id.rainfall);

        dbHandler = new DBHandler(this, null, null, 1);

        retrieveAddress();
        retrieveDayNum();
        retrieveRainfall();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                    return;
            }
        }
    }

    private void getCurrentLocation(){
        currentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    latitute = 0;
                    longitute = 0;
                    usingCurrentLocation = true;
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
                    reverseGeocode();
                    currentLocationButton.setEnabled(false);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void retrieveAddress(){
        addressText.setText(dbHandler.getAddress());
    }

    public void retrieveDayNum(){
        dayNumPicker.setValue(dbHandler.getDays());
    }

    public void retrieveRainfall(){
        rainfallText.setText(Double.toString(dbHandler.getRainfall()));
    }

    public void saveButtonClicked(View view){
        String address = addressText.getText().toString();
        if(address.length() > 0 && usingCurrentLocation == false){
            geocodeLocation(address);
        }
        else if(latitute != 0 && longitute != 0 && usingCurrentLocation == true){
            dbHandler.changeSettings(address ,latitute, longitute, dayNumPicker.getValue(), Double.parseDouble(rainfallText.getText().toString()));
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("location", address);
            startActivity(intent);
        }
        else{
            addressText.setError("Please enter address or use current location");
        }
    }

    public void geocodeLocation(String location){
        try{
            Geocoder geo = new Geocoder(this);
            List<Address> list = geo.getFromLocationName(location, 1);
            if(list.size() >= 1){
                Address geoAddress = list.get(0);
                latitute = geoAddress.getLatitude();
                longitute = geoAddress.getLongitude();
                Log.d("testing", "latitude =" + Double.toString(latitute) + "longitude =" + Double.toString(longitute));
                dbHandler.changeSettings(location, latitute, longitute, dayNumPicker.getValue(), Double.parseDouble(rainfallText.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
            else{
                addressText.setError("Unable to find address, please try another address");
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void reverseGeocode(){
        try{
            Geocoder geo = new Geocoder(this);
            List<Address> list = geo.getFromLocation(latitute, longitute, 1);
            if(list.size() >= 1){
                Address geoAddress = list.get(0);
                String addressName = geoAddress.getLocality();
                addressText.setText(addressName);
            }
            else{
                Toast.makeText(getApplicationContext(), "Unable to find current location, please enter address instead", Toast.LENGTH_SHORT).show();
                usingCurrentLocation = false;
            }

        }
        catch(IOException e){
                e.printStackTrace();
        }
    }

    public class LocListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if(location != null){
                Log.d("testing", Double.toString(location.getLatitude()));
                Log.d("testing", Double.toString(location.getLongitude()));
                latitute = location.getLatitude();
                longitute = location.getLongitude();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

}
