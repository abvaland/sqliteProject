package com.example.ajay.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Ajay on 17-01-2017.
 */
public class DBHelper extends SQLiteAssetHelper {


    public static String DbName="project1.sqlite";
    public static int DBVersion=1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
