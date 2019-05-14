package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static com.example.myapplication.DBHelper.COL_ID;
import static com.example.myapplication.DBHelper.COL_SRC;
import static com.example.myapplication.DBHelper.COL_NAME;
import static com.example.myapplication.DBHelper.COL_PERCENTAGE;
import static com.example.myapplication.DBHelper.DATABASE_VERSION;
import static com.example.myapplication.DBHelper.USER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbHelper;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "myLogs";

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        imageView1 = (ImageView) findViewById(R.id.imageView123);
        imageView1.setOnClickListener(this);
        Cursor cursor = db.query(USER, null, null, null, null, null, null, null);
        int[] lesson_id, lesson_done;
        int idIndex = cursor.getColumnIndex(COL_ID);
        int percentIndex = cursor.getColumnIndex(COL_PERCENTAGE);
        lesson_id = new int[100];
        lesson_done = new int[100];
        int i = 0;
        if(DATABASE_VERSION == 1) insertingFirstValues();
        String dbv = String.valueOf(DATABASE_VERSION);
        Log.d(TAG,"Database version is "+ dbv);
        Log.d(TAG,"Trying find recent actions");
        if (cursor.moveToFirst()) {

            do {
                lesson_id[i] = cursor.getInt(cursor.getColumnIndex(COL_ID));
                lesson_done[i] = cursor.getInt(cursor.getColumnIndex(COL_PERCENTAGE));
                Log.d(TAG, "ID_Lesson = " + cursor.getInt(idIndex)
                        + "ID_Lesson = " + cursor.getInt(percentIndex));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView123:
                final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
                v.startAnimation(animAlpha);
                Intent intent = new Intent(MainActivity.this, ActivityBlocks.class);
                startActivity(intent);
        }
    }
    public void insertingFirstValues(){
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cV = new ContentValues();
        dbHelper.insertUserData(1,1);
        dbHelper.insertUserData(2,0);
        dbHelper.insertUserData(3,0);
        dbHelper.insertUserData(4,0);
        dbHelper.insertUserData(5,0);
        dbHelper.insertUserData(6,0);
        dbHelper.insertUserData(7,0);
        dbHelper.insertUserData(8,0);
    }
}
