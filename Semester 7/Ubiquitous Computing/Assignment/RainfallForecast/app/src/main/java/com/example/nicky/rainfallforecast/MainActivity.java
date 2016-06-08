package com.example.nicky.rainfallforecast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button)findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        if(location != null){
            Toast.makeText(this, "Settings saved for " + location, Toast.LENGTH_SHORT).show();
        }

        Intent weatherIntent = new Intent(this, WeatherService.class);
        PendingIntent weatherPendingIntent = PendingIntent.getService(this, 0, weatherIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, weatherPendingIntent);


    }



}
