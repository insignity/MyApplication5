package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Random;

public class Lesson2 extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    final String TAG = "myLogs";
    Integer lessonNum;
    Integer lesson1;
    Dialog dialog;
    Integer countOfMistakes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);

        Button buttonOk = findViewById(R.id.buttonOk);
        ImageView imageView = findViewById(R.id.imageView);

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
        lessonNum = intent.getExtras().getInt("lessonNum");
        lesson1 = intent.getExtras().getInt("lesson1");
        int[] cards_id = new int[100];
        String[] cards_src = new String[100];
        String[] cards_name = new String[100];
        int image_Resource[] = new int[100];
        int sound_Resource[] = new int[100];
        int i = 0;
        Cursor cursor = mDb.rawQuery("SELECT task._id, data.text, data.name FROM task INNER JOIN data ON task._id = data.id WHERE task.lesson = " + lessonNum + " AND task.activity = 2", null);
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

        final Button button0 = findViewById(R.id.button0);    button0.setText(cards_name[0]);
        final Button button1 = findViewById(R.id.button1);    button1.setText(cards_name[1]);
        final Button button2 = findViewById(R.id.button2);    button2.setText(cards_name[2]);
        final Button button3 = findViewById(R.id.button3);    button3.setText(cards_name[3]);

        Random random = new Random();
        final int randomizeAnswer = Integer.valueOf(random.nextInt(4));
        final MediaPlayer sound0 = MediaPlayer.create(this, sound_Resource[0]);
        final MediaPlayer sound1 = MediaPlayer.create(this, sound_Resource[1]);
        final MediaPlayer sound2 = MediaPlayer.create(this, sound_Resource[2]);
        final MediaPlayer sound3 = MediaPlayer.create(this, sound_Resource[3]);




        imageView.setImageResource(image_Resource[randomizeAnswer]);

        Log.d(TAG, String.valueOf(randomizeAnswer));

        final MediaPlayer sound = MediaPlayer.create(this, sound_Resource[randomizeAnswer]);
        sound.start();
        final int[] y = new int[1];
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.imageView:
                        sound.start();
                        break;
                    case R.id.button0:
                        LastClickedButton(button0);
                        y[0] = 0;
                        sound0.start();
                        break;
                    case R.id.button1:
                        LastClickedButton(button1);
                        y[0] = 1;
                        sound1.start();
                        break;
                    case R.id.button2:
                        LastClickedButton(button2);
                        y[0] = 2;
                        sound2.start();
                        break;
                    case R.id.button3:
                        LastClickedButton(button3);
                        y[0] = 3;
                        sound3.start();
                        break;
                    case R.id.buttonOk:
                        if(y[0]==randomizeAnswer) {
                            Congratulation(v);
                        }else Mistake(v);
                        break;
                }
            }
        };
        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        buttonOk.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
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
                Intent intent = new Intent(Lesson2.this, Lesson3.class);
                intent.putExtra("lessonNum", lessonNum);
                intent.putExtra("lesson1", lesson1);
                intent.putExtra("lesson2", countOfMistakes);
                startActivity(intent);
            }
        }, 1000);
    }
    public void LastClickedButton(Button button){
        Drawable d = getResources().getDrawable(R.drawable.custombutton);
        Drawable s = getResources().getDrawable(R.drawable.custombuttonselected);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button0.setBackground(d);
        button1.setBackground(d);
        button2.setBackground(d);
        button3.setBackground(d);
        button.setBackground(s);
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
