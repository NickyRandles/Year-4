package com.example.nicky.rainfallforecast;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by nicky on 06/12/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "settingsDB.db";
    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_DAYS = "days";
    public static final String COLUMN_RAINFALL = "rainfall";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SETTINGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_LATITUDE + " DOUBLE, " +
                COLUMN_LONGITUDE + " DOUBLE, " +
                COLUMN_DAYS + " INTEGER, " +
                COLUMN_RAINFALL + " DOUBLE);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_SETTINGS;
        db.execSQL(query);
        onCreate(db);
    }

    public void changeSettings(String address, double latitude, double longitude, int dayNum, double rainfall) {
        String query = "SELECT * FROM " + TABLE_SETTINGS;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.getCount() < 1){
            String insert = "INSERT INTO " + TABLE_SETTINGS + " (" + COLUMN_ADDRESS + ", " + COLUMN_LATITUDE + ", " + COLUMN_LONGITUDE + ", "
                                                                + COLUMN_DAYS + ", " + COLUMN_RAINFALL + ")"
                                                                +" VALUES('"+ address + "', " + latitude + ", " + longitude + ", "
                                                                + dayNum + ", " + rainfall + ");";
            db.execSQL(insert);
        }
        else{
            String update = "UPDATE " + TABLE_SETTINGS + " SET " + COLUMN_ADDRESS + "='" + address + "', "
                                                                 + COLUMN_LATITUDE + "=" + latitude + ", "
                                                                 + COLUMN_LONGITUDE + "=" + longitude + ", "
                                                                 + COLUMN_DAYS + "=" + dayNum + ", "
                                                                 + COLUMN_RAINFALL + "=" + rainfall
                                                                 + " WHERE " + COLUMN_ID + "=1";
            db.execSQL(update);
        }
        db.close();
        checkData();
    }

    public void checkData(){
        String query = "SELECT * FROM " + TABLE_SETTINGS;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c!=null && c.getCount()>0)
        {
            c.moveToFirst();
            do {
                Log.d("testing", c.getString(0));
                Log.d("testing", c.getString(1));
                Log.d("testing", c.getString(2));
                Log.d("testing", c.getString(3));
                Log.d("testing", c.getString(4));
                Log.d("testing", c.getString(5));
            } while (c.moveToNext());
        }
        db.close();
    }

    public String getAddress(){
        String address = null;
        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + "=1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            address = c.getString(1);
        }

        return address;
    }

    public double getLatitude(){
        double latitude = Double.NaN;
        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + "=1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            latitude = c.getDouble(2);
        }

        return latitude;
    }

    public double getLongitude(){
        double longitude = Double.NaN;
        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + "=1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            longitude = c.getDouble(3);
        }

        return longitude;
    }


    public int getDays(){
        int days = 1;
        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + "=1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            days = c.getInt(4);
        }

        return days;
    }

    public double getRainfall(){
        double rainfall = 0;
        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + "=1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            rainfall = c.getDouble(5);
        }

        return rainfall;
    }



}
