package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Lesson1_3 extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    final String TAG = "myLogs";
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1_3);

        final Button buttonOk = findViewById(R.id.buttonOk);
        final Button button0 = findViewById(R.id.button0);   final Button button4 = findViewById(R.id.button4);
        final Button button1 = findViewById(R.id.button1);   final Button button5 = findViewById(R.id.button5);
        final Button button2 = findViewById(R.id.button2);   final Button button6 = findViewById(R.id.button6);
        final Button button3 = findViewById(R.id.button3);   final Button button7 = findViewById(R.id.button7);

        mDBHelper = new DBHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        int[] cards_id = new int[100];
        String[] cards_src = new String[100];
        String[] cards_name = new String[100];
        int image_Resource[] = new int[100];
        int sound_Resource[] = new int[100];
        int i = 0;

        Cursor cursor = mDb.rawQuery("SELECT task._id, data.name, data.text FROM task INNER JOIN data ON task._id = data.id WHERE task.lesson = 1 AND task.activity = 3", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cards_id[i] = cursor.getInt(0);
            cards_name[i] = cursor.getString(1);
            cards_src[i] = cursor.getString(2);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        for(int p = 0; p < 4; p++){
            image_Resource[p] = this.getResources().getIdentifier( cards_src[p], "drawable", this.getPackageName());
            sound_Resource[p] = this.getResources().getIdentifier( cards_src[p], "raw", this.getPackageName());
        }
        button0.setText(cards_name[0]);     button4.setText(cards_src[0]);
        button1.setText(cards_name[1]);     button5.setText(cards_src[1]);
        button2.setText(cards_name[2]);     button6.setText(cards_src[2]);
        button3.setText(cards_name[3]);     button7.setText(cards_src[3]);
        final int[] lesson = {0};
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button0:
                        if(button4.isActivated() == true)
                        {
                            buttonOk.setEnabled(true);
                            button0.setEnabled(false);
                            button4.setEnabled(false);
                            lesson[0]++;
                        }else
                        reactivateAllButtons();
                        button0.setActivated(true);
                        break;
                    case R.id.button1:
                        if(button5.isActivated() == true)
                        {
                            button1.setEnabled(false);
                            button5.setEnabled(false);
                            lesson[0]++;
                        }else
                            reactivateAllButtons();
                        button1.setActivated(true);
                        break;
                    case R.id.button2:
                        if(button6.isActivated() == true)
                        {
                            lesson[0]++;
                            button2.setEnabled(false);
                            button6.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button2.setActivated(true);
                        break;
                    case R.id.button3:
                        if(button7.isActivated() == true)
                        {
                            lesson[0]++;
                            button3.setEnabled(false);
                            button7.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button3.setActivated(true);
                        break;
                    case R.id.button4:
                        if(button0.isActivated() == true)
                        {
                            lesson[0]++;
                            button0.setEnabled(false);
                            button4.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button4.setActivated(true);
                        break;
                    case R.id.button5:
                        if(button1.isActivated() == true)
                        {
                            lesson[0]++;
                            button1.setEnabled(false);
                            button5.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button5.setActivated(true);
                        break;
                    case R.id.button6:
                        if(button2.isActivated() == true)
                        {
                            lesson[0]++;
                            button2.setEnabled(false);
                            button6.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button6.setActivated(true);
                        break;
                    case R.id.button7:
                        if(button3.isActivated() == true)
                        {
                            lesson[0]++;
                            button3.setEnabled(false);
                            button7.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button7.setActivated(true);
                        break;
                    case R.id.buttonOk:
                        if(lesson[0]==4) {
                            Intent intent = new Intent(Lesson1_3.this, ActivityBlocks.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        };
        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        buttonOk.setOnClickListener(onClickListener);
    }
    void reactivateAllButtons(){
     Button button0 = findViewById(R.id.button0);
     Button button1 = findViewById(R.id.button1);
     Button button2 = findViewById(R.id.button2);
     Button button3 = findViewById(R.id.button3);
     Button button4 = findViewById(R.id.button4);
     Button button5 = findViewById(R.id.button5);
     Button button6 = findViewById(R.id.button6);
     Button button7 = findViewById(R.id.button7);
     button0.setActivated(false);
     button1.setActivated(false);
     button2.setActivated(false);
     button3.setActivated(false);
     button4.setActivated(false);
     button5.setActivated(false);
     button6.setActivated(false);
     button7.setActivated(false);

    }
}
