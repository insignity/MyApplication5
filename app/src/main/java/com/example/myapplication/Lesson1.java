package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Random;

public class Lesson1 extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    final String TAG = "myLogs";
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        Button button = findViewById(R.id.button);

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
        String[] cards_src = new String[100];
        int image_Resource[] = new int[100];
        int sound_Resource[] = new int[100];
        int i = 0;

        Cursor cursor = mDb.rawQuery("SELECT task._id, data.name FROM task INNER JOIN data ON task._id = data.id WHERE task.lesson = " + lessonNum + " AND task.activity = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cards_id[i] = cursor.getInt(0);
            cards_src[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();

        final RelativeLayout rl_0 = findViewById(R.id.rl_0);
        final RelativeLayout rl_1 = findViewById(R.id.rl_1);
        final RelativeLayout rl_2 = findViewById(R.id.rl_2);
        final RelativeLayout rl_3 = findViewById(R.id.rl_3);

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        lParams.setMargins(10, 0, 10, 0);

        ImageView imgView_1 = new ImageView(this);  rl_0.addView(imgView_1, lParams);
        ImageView imgView_2 = new ImageView(this);  rl_1.addView(imgView_2, lParams);
        ImageView imgView_3 = new ImageView(this);  rl_2.addView(imgView_3, lParams);
        ImageView imgView_4 = new ImageView(this);  rl_3.addView(imgView_4, lParams);
        final ImageView audioButton = findViewById(R.id.audiobutton);

        for(int p = 0; p < 4; p++){
            image_Resource[p] = this.getResources().getIdentifier( cards_src[p], "drawable", this.getPackageName());
            sound_Resource[p] = this.getResources().getIdentifier( cards_src[p], "raw", this.getPackageName());
        }

        imgView_1.setImageResource(image_Resource[0]);
        imgView_2.setImageResource(image_Resource[1]);
        imgView_3.setImageResource(image_Resource[2]);
        imgView_4.setImageResource(image_Resource[3]);

        Random random = new Random();
        final int randomizeAnswer = Integer.valueOf(random.nextInt(4));
        Log.d(TAG, String.valueOf(randomizeAnswer));
        final MediaPlayer sound = MediaPlayer.create(this, sound_Resource[randomizeAnswer]);
        sound.start();
        Log.d(TAG, String.valueOf(randomizeAnswer));
        final MediaPlayer sound1 = MediaPlayer.create(this, sound_Resource[0]);
        final MediaPlayer sound2 = MediaPlayer.create(this, sound_Resource[1]);
        final MediaPlayer sound3 = MediaPlayer.create(this, sound_Resource[2]);
        final MediaPlayer sound4 = MediaPlayer.create(this, sound_Resource[3]);

        int rl_answer = this.getResources().getIdentifier( "rl_" + randomizeAnswer, "id", this.getPackageName());
        final int current_rl = rl_answer;
        View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int colorActivated = Color.parseColor("#dcf4fe");
                    int colorNotActivated = Color.parseColor("#ffffff");
                    switch (view.getId()) {
                        case R.id.rl_0:
                            rl_0.setBackgroundColor(colorActivated);
                            rl_1.setBackgroundColor(colorNotActivated);
                            rl_2.setBackgroundColor(colorNotActivated);
                            rl_3.setBackgroundColor(colorNotActivated);
                            rl_1.setActivated(false);
                            rl_2.setActivated(false);
                            rl_3.setActivated(false);
                            if(R.id.rl_0 == current_rl)
                                rl_0.setActivated(true);
                            Log.d(TAG, "Pushed imgView_1");
                            sound1.start();
                            break;
                        case R.id.rl_1:
                            rl_0.setBackgroundColor(colorNotActivated);
                            rl_1.setBackgroundColor(colorActivated);
                            rl_2.setBackgroundColor(colorNotActivated);
                            rl_3.setBackgroundColor(colorNotActivated);
                            rl_0.setActivated(false);
                            rl_2.setActivated(false);
                            rl_3.setActivated(false);
                            if(R.id.rl_1 == current_rl)
                                rl_1.setActivated(true);
                            Log.d(TAG, "Pushed imgView_2");
                            sound2.start();
                            break;
                        case R.id.rl_2:
                            rl_0.setBackgroundColor(colorNotActivated);
                            rl_1.setBackgroundColor(colorNotActivated);
                            rl_2.setBackgroundColor(colorActivated);
                            rl_3.setBackgroundColor(colorNotActivated);
                            rl_0.setActivated(false);
                            rl_1.setActivated(false);
                            rl_3.setActivated(false);
                            if(R.id.rl_2 == current_rl)
                                rl_2.setActivated(true);
                            Log.d(TAG, "Pushed imgView_3");
                            sound3.start();
                            break;
                        case R.id.rl_3:
                            rl_0.setBackgroundColor(colorNotActivated);
                            rl_1.setBackgroundColor(colorNotActivated);
                            rl_2.setBackgroundColor(colorNotActivated);
                            rl_3.setBackgroundColor(colorActivated);
                            rl_0.setActivated(false);
                            rl_1.setActivated(false);
                            rl_2.setActivated(false);
                            if(R.id.rl_3 == current_rl)
                                rl_3.setActivated(true);
                            Log.d(TAG, "Pushed imgView_4");
                            sound4.start();
                            break;
                        case R.id.audiobutton:
                            Log.d(TAG, "Pushed audiobutton");
                            sound.start();
                            break;
                        case R.id.button:
                            Log.d(TAG, "Pushed button");
                            Log.d(TAG,"Try open Lesson2");
                            if (rl_0.isActivated() == true) {
                                Intent intent = new Intent(Lesson1.this, Lesson2.class);
                                intent.putExtra("lessonNum", lessonNum);
                                startActivity(intent);
                            }
                            else if (rl_1.isActivated() == true){
                                Intent intent = new Intent(Lesson1.this, Lesson2.class);
                                intent.putExtra("lessonNum", lessonNum);
                                startActivity(intent);
                            }
                            else if (rl_2.isActivated() == true) {
                                Intent intent = new Intent(Lesson1.this, Lesson2.class);
                                intent.putExtra("lessonNum", lessonNum);
                                startActivity(intent);
                            }
                            else if (rl_3.isActivated() == true) {
                                Intent intent = new Intent(Lesson1.this, Lesson2.class);
                                intent.putExtra("lessonNum", lessonNum);
                                startActivity(intent);
                            }
                            break;
                    }
                }
            };
            Log.d(TAG, "Set up switch completed successfully! ");
            rl_0.setOnClickListener(onClickListener);
            rl_1.setOnClickListener(onClickListener);
            rl_2.setOnClickListener(onClickListener);
            rl_3.setOnClickListener(onClickListener);
            audioButton.setOnClickListener(onClickListener);
            button.setOnClickListener(onClickListener);
        }
    }









