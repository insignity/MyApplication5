package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Lesson3 extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    final String TAG = "myLogs";
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

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
        Intent intent = getIntent();
        final Integer lessonNum = intent.getExtras().getInt("lessonNum");

        int[] cards_id = new int[100];
        String[] cards_name = new String[100];
        String[] cards_pair = new String[100];
        int i = 0;

        Cursor cursor = mDb.rawQuery("SELECT task._id, data.name, data.pair FROM task INNER JOIN data ON task._id = data.id WHERE task.lesson = " + lessonNum + " AND task.activity = 3", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cards_id[i] = cursor.getInt(0);
            cards_name[i] = cursor.getString(1);
            cards_pair[i] = cursor.getString(2);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        button0.setText(cards_name[3]);     button4.setText(cards_pair[0]);
        button1.setText(cards_name[1]);     button5.setText(cards_pair[1]);
        button2.setText(cards_name[0]);     button6.setText(cards_pair[2]);
        button3.setText(cards_name[2]);     button7.setText(cards_pair[3]);
        final int[] lesson = {0};
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button0:
                        if(button7.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button0.setEnabled(false);
                            button7.setEnabled(false);
                        }else
                        reactivateAllButtons();
                        button0.setActivated(true);
                        break;
                    case R.id.button1:

                        if(button5.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                            buttonOk.setEnabled(true);
                            }
                            button1.setEnabled(false);
                            button5.setEnabled(false);
                            lesson[0]++;
                        }else
                            reactivateAllButtons();
                        button1.setActivated(true);
                        break;
                    case R.id.button2:
                        if(button4.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button2.setEnabled(false);
                            button4.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button2.setActivated(true);
                        break;
                    case R.id.button3:
                        if(button6.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button3.setEnabled(false);
                            button6.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button3.setActivated(true);
                        break;
                    case R.id.button4:
                        if(button2.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button2.setEnabled(false);
                            button4.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button4.setActivated(true);
                        break;
                    case R.id.button5:
                        if(button1.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button1.setEnabled(false);
                            button5.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button5.setActivated(true);
                        break;
                    case R.id.button6:
                        if(button3.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button3.setEnabled(false);
                            button6.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button6.setActivated(true);
                        break;
                    case R.id.button7:
                        if(button0.isActivated() == true)
                        {
                            if(lesson[0]==4) {
                                buttonOk.setEnabled(true);
                            }
                            lesson[0]++;
                            button0.setEnabled(false);
                            button7.setEnabled(false);
                        }else
                            reactivateAllButtons();
                        button7.setActivated(true);
                        break;
                    case R.id.buttonOk:
                        if(lesson[0]==4) {
                            Intent intent = new Intent(Lesson3.this, Lesson4.class);
                            intent.putExtra("lessonNum", lessonNum);
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
