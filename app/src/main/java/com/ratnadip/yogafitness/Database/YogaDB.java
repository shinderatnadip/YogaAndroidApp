package com.ratnadip.yogafitness.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratnadip on 08-Apr-18.
 */

public class YogaDB extends SQLiteAssetHelper {

    private static final String DB_Name ="Yoga.db";
    private static final int DB_VER=1;

    public YogaDB(Context context) {
        super(context, DB_Name,null,DB_VER);
    }

    public int getSettingMode(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Mode"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("Mode"));

    }


    public void saveSettingMode(int value){

        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Mode= "+value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDays(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Days"};
        String sqlTable = "WorkoutDays";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Days")));
                 }while (cursor.moveToNext());
        }
        return result;

    }

    public void saveDays(String value){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Days) VALUES ('%s')",value);
        db.execSQL(query);
    }


}
