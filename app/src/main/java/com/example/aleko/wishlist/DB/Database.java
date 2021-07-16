package com.example.aleko.wishlist.DB;

/**
 * Created by aleko on 16/07/2021.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/wishlist/wishlist.sqlite";
    protected String query;
    protected String[] columnsNames;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    //Query result into array
    protected JSONArray get_results_from_query() throws JSONException {
        JSONArray results = null;
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            results = new JSONArray();
            columnsNames = cursor.getColumnNames();
            do {
                JSONObject Object = new JSONObject();
                for (int i = 0; i < columnsNames.length; i++) {
                    Object.put(columnsNames[i], cursor.getString(i));
                }
                results.put(Object);
            } while (cursor.moveToNext());
        }
        this.getReadableDatabase().close();
        cursor.close();
        return results;
    }

    //Insert query
    protected Long Insert(String table, ContentValues values) {

        Long id = null;
        try {

            id = this.getWritableDatabase().insert(table, null, values);
            this.getWritableDatabase().close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return id;
    }

    //Update query
    protected void Edit(String table, ContentValues values, String whereClause) {
        this.getWritableDatabase().update(table, values, whereClause, null);
        this.getWritableDatabase().close();
    }

    //Delete query
    protected void Delete(String table, String whereClause) {
        this.getWritableDatabase().delete(table, whereClause, null);
        this.getWritableDatabase().close();
    }

}
