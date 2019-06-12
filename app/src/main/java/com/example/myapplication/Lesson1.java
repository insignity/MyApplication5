package com.example.myapplication;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Lesson1 extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    final String TAG = "myLogs";
    Dialog dialog;
    RelativeLayout rl_0;
    RelativeLayout rl_1;
    RelativeLayout rl_2;
    RelativeLayout rl_3;
    Integer lessonNum;
    Integer countOfMistakes = 0;
    Button button;
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        final Button button = findViewById(R.id.button);

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

        lessonNum = getIntent().getExtras().getInt("lessonNum");
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

        rl_0 = findViewById(R.id.rl_0);
        rl_1 = findViewById(R.id.rl_1);
        rl_2 = findViewById(R.id.rl_2);
        rl_3 = findViewById(R.id.rl_3);

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
        final MediaPlayer sound = MediaPlayer.create(this, sound_Resource[randomizeAnswer]);
        sound.start();
        final MediaPlayer sound1 = MediaPlayer.create(this, sound_Resource[0]);
        final MediaPlayer sound2 = MediaPlayer.create(this, sound_Resource[1]);
        final MediaPlayer sound3 = MediaPlayer.create(this, sound_Resource[2]);
        final MediaPlayer sound4 = MediaPlayer.create(this, sound_Resource[3]);
        button.setEnabled(false);
        final int current_rl = this.getResources().getIdentifier( "rl_" + randomizeAnswer, "id", this.getPackageName());
        View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int colorActivated = Color.parseColor("#dcf4fe");
                    switch (view.getId()) {
                        case R.id.rl_0:
                            notActivated();
                            rl_0.setBackgroundColor(colorActivated);
                            if(R.id.rl_0 == current_rl)
                                rl_0.setActivated(true);
                            sound1.start();
                            break;
                        case R.id.rl_1:
                            notActivated();
                            rl_1.setBackgroundColor(colorActivated);
                            if(R.id.rl_1 == current_rl)
                                rl_1.setActivated(true);
                            sound2.start();
                            break;
                        case R.id.rl_2:
                            notActivated();
                            rl_2.setBackgroundColor(colorActivated);
                            if(R.id.rl_2 == current_rl)
                                rl_2.setActivated(true);
                            sound3.start();
                            break;
                        case R.id.rl_3:
                            notActivated();
                            rl_3.setBackgroundColor(colorActivated);
                            if(R.id.rl_3 == current_rl)
                                rl_3.setActivated(true);
                            sound4.start();
                            break;
                        case R.id.audiobutton:
                            sound.start();
                            break;
                        case R.id.button:
                            if (rl_0.isActivated() == true) {
                                Congratulation(view);
                            }
                            else if (rl_1.isActivated() == true){
                                Congratulation(view);
                            }
                            else if (rl_2.isActivated() == true) {
                                Congratulation(view);
                            }
                            else if (rl_3.isActivated() == true) {
                                Congratulation(view);
                            }else
                                Mistake(view);
                            break;
                    }
                }
            };

            rl_0.setOnClickListener(onClickListener);
            rl_1.setOnClickListener(onClickListener);
            rl_2.setOnClickListener(onClickListener);
            rl_3.setOnClickListener(onClickListener);
            audioButton.setOnClickListener(onClickListener);
            button.setOnClickListener(onClickListener);
        }
        public void notActivated(){
        button = findViewById(R.id.button);
            button.setEnabled(true);
            int notActivated = Color.parseColor("#ffffff");
            rl_0.setActivated(false);
            rl_1.setActivated(false);
            rl_2.setActivated(false);
            rl_3.setActivated(false);
            rl_0.setBackgroundColor(notActivated);
            rl_1.setBackgroundColor(notActivated);
            rl_2.setBackgroundColor(notActivated);
            rl_3.setBackgroundColor(notActivated);
        }
        public void Congratulation(View v){
            final MediaPlayer right = MediaPlayer.create(this, R.raw.right);
            right.start();
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_layout);
            dialog.show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Lesson1.this, Lesson2.class);
                    intent.putExtra("lessonNum", lessonNum);
                    intent.putExtra("lesson1", countOfMistakes);
                    startActivity(intent);
                }
            }, 1000);
        }
        public void Mistake(View v){
        countOfMistakes++;
        final MediaPlayer right = MediaPlayer.create(this, R.raw.mistake);
        right.start();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_layout_mistake);
        dialog.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    dialog.cancel();
                    }
                }, 3000);
        }
    }









