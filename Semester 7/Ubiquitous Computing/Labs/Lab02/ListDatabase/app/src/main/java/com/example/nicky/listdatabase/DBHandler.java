package com.example.nicky.listdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nicky on 12/10/2015.
 */
public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listDB.db";
    public static final String TABLE_lISTS = "lists";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTNAME = "listName";
    public static final String COLUMN_LIST = "list";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_lISTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTNAME + " TEXT, " +
                COLUMN_LIST + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_lISTS);
        onCreate(db);
    }

    public void addList(List list){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LISTNAME, list.get_listName());
        values.put(COLUMN_LIST, list.get_list());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_lISTS, null, values);
        db.close();
    }

    public String searchDatabase(String searchQuery){
        String results = "";
        String query = "SELECT * FROM " + TABLE_lISTS + " WHERE " + COLUMN_LISTNAME +  " = '" + searchQuery + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() > 0){
            results = c.getString(c.getColumnIndex("list"));
        }

        return results;

    }

    public void saveList(List list){
        String query = "SELECT * FROM " + TABLE_lISTS + " WHERE " + COLUMN_LISTNAME +  " = '" + list.get_listName() + "' LIMIT 1";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int i = 1;

        if(c.getCount() > 0){
            String query2 = "UPDATE " + TABLE_lISTS + " SET " + COLUMN_LISTNAME + " = '" + list.get_listName() + "', " + COLUMN_LIST + " = '" + list.get_list() + "' WHERE " + COLUMN_LISTNAME  + " = '" + list.get_listName() + "'";
            db.execSQL(query2);
            db.close();
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_LISTNAME, list.get_listName());
            values.put(COLUMN_LIST, list.get_list());
            db.insert(TABLE_lISTS, null, values);
            db.close();
        }

    }

    public void deleteList(String listName){
        String query = "DELETE FROM " + TABLE_lISTS + " WHERE " + COLUMN_LISTNAME + " = '" + listName + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
