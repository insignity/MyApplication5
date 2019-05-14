package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db35";
    public static final String TABLE1 = "table1";
    public static final String TABLE2 = "table2";
    public static final String TABLE3 = "table3";
    public static final String TABLE4 = "table4";
    public static final String TABLE5 = "table5";
    public static final String TABLE6 = "table6";
    public static final String TABLE7 = "table7";
    public static final String TABLE8 = "table8";
    public static final String USER = "user";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_SRC = "imgsrc";
    public static final String COL_PERCENTAGE = "percentage";
    public final String TAG = "myLogs";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Database was created");
        db.execSQL("CREATE TABLE " + TABLE1 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE2 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE3 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE4 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE5 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE6 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE7 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE8 + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_NAME + " TEXT," + COL_SRC + " TEXT)");
        db.execSQL("CREATE TABLE " + USER + "(" + COL_ID + " INTEGER," + COL_PERCENTAGE + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+USER + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE4 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE5 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE6 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE7 + ";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE8 + ";");
        onCreate(db);
        Log.d(TAG, "Database was updated");
    }

    public void insertData(int table, int id, String name, String imgsrc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SRC, imgsrc);
        switch (table){
            case 1: db.insert(TABLE1, null, contentValues); break;
            case 2: db.insert(TABLE2, null, contentValues); break;
            case 3: db.insert(TABLE3, null, contentValues); break;
            case 4: db.insert(TABLE4, null, contentValues); break;
            case 5: db.insert(TABLE5, null, contentValues); break;
            case 6: db.insert(TABLE6, null, contentValues); break;
            case 7: db.insert(TABLE7, null, contentValues); break;
            case 8: db.insert(TABLE8, null, contentValues); break;
        }
    }
    public void insertUserData(int id, int percent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_PERCENTAGE, percent);
        db.insert(USER, null, contentValues);
    }
}