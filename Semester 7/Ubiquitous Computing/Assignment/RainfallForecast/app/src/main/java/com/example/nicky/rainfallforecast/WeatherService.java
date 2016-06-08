package com.example.nicky.rainfallforecast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherService extends Service {

    DBHandler dbHandler;
    public double maxRain;

    public WeatherService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbHandler = new DBHandler(this, null, null, 1);
        double latitude = dbHandler.getLatitude();
        double longitude = dbHandler.getLongitude();
        int days = dbHandler.getDays();
        maxRain = dbHandler.getRainfall();

        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon=" + longitude + "&cnt=" + days + "&mode=json&appid=6db30e6e740aee0b771aa9b102044dd7";
        new JSONTask().execute(url);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class JSONTask extends AsyncTask<String, String, ArrayList<String[]>> {

        @Override
        protected ArrayList<String[]> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String json = buffer.toString();
                JSONObject root = new JSONObject(json);
                JSONArray list = root.getJSONArray("list");
                ArrayList<String[]> data = new ArrayList<String[]>();
                String[] rainfall = new String[list.length()];
                String[] description = new String[list.length()];
                String[] icon = new String[list.length()];

                for (int i = 0; i < list.length(); i++) {
                    JSONObject listItem = list.getJSONObject(i);
                    if(listItem.has("rain")){
                        double rain = listItem.getDouble("rain");
                        rainfall[i] = Double.toString(rain);
                    }
                    else{
                        double rain = Double.NaN;
                        rainfall[i] = Double.toString(rain);
                    }
                    JSONArray weather = listItem.getJSONArray("weather");
                    JSONObject weatherObject = weather.getJSONObject(0);
                    if(weatherObject.has("description")){
                        String desc = weatherObject.getString("description");
                        description[i] = desc;
                    }
                    else{
                        String desc = "none";
                        description[i] = desc;
                    }
                    if(weatherObject.has("icon")){
                        String ic = weatherObject.getString("icon");
                        icon[i] = ic;
                    }
                    else{
                        String ic = "none";
                        icon[i] = ic;
                    }
                }

                data.add(0, rainfall);
                data.add(1, description);
                data.add(2, icon);

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally{
                if(connection != null){
                    connection.disconnect();
                }
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String[]> result) {
            super.onPostExecute(result);
            if(result != null){
                //printing out data to console
                for (int i = 0; i < result.size(); i++) {
                    for (int j = 0; j < result.get(i).length; j++) {
                        String[] array = result.get(i);
                        Log.d("testing", (j + 1) + " " + array[j]);
                    }
                }

                String[] rain = result.get(0);
                //String[] description = result.get(1);
                //String[] icon = result.get(2);

                for (int i = 0; i < rain.length; i++) {
                    String when;
                    if(i == 0){
                        when = "today";
                    }
                    else if(i == 1){
                        when = "tomorrow";
                    }
                    else{
                        when = "in "+ (i + 1) + " days";
                    }
                    if(Double.parseDouble(rain[i]) >= maxRain){
                        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Rain warning")
                                .setContentText("Excedding max " + when)
                                .setAutoCancel(true)
                                .setSmallIcon(R.drawable.icon);
                        Notification notification = builder.build();

                        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(1, notification);

                        break;//break out and stop looking if next day that exceeds max is found
                    }
                }
            }

        }
    }
}
