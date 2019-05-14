package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static com.example.myapplication.DBHelper.COLUMN_ID;
import static com.example.myapplication.DBHelper.COLUMN_NAME;
import static com.example.myapplication.DBHelper.COLUMN_YEAR;
import static com.example.myapplication.DBHelper.TABLE;

public class LessonActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button button;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        button = findViewById(R.id.button);
        final String TAG = "myLogs";

        int[] cards_id;
        String[] cards_name, cards_src;
        cards_id = new int[100];
        cards_name = new String[100];
        cards_src = new String[100];
        int image_Resource[] = new int[100];
        int sound_Resource[] = new int[100];
        int i = 0;

        String tableNum = TABLE;

        Cursor cursor = db.query(tableNum, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int translateIndex = cursor.getColumnIndex(COLUMN_YEAR);
            do {
                cards_id[i] = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                cards_name[i] = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                cards_src[i] = cursor.getString(cursor.getColumnIndex(COLUMN_YEAR));
                Log.d(TAG, "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", imgsrc = " + cursor.getString(translateIndex));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();



        final RelativeLayout rl_0 = findViewById(R.id.rl_0);   final TextView textView0 = findViewById(R.id.textView0);
        final RelativeLayout rl_1 = findViewById(R.id.rl_1);   final TextView textView1 = findViewById(R.id.textView1);
        final RelativeLayout rl_2 = findViewById(R.id.rl_2);   final TextView textView2 = findViewById(R.id.textView2);
        final RelativeLayout rl_3 = findViewById(R.id.rl_3);   final TextView textView3 = findViewById(R.id.textView3);

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        lParams.setMargins(0, 0, 0, 80);

        ImageView imgView_1 = new ImageView(this);  rl_0.addView(imgView_1, lParams);
        ImageView imgView_2 = new ImageView(this);  rl_1.addView(imgView_2, lParams);
        ImageView imgView_3 = new ImageView(this);  rl_2.addView(imgView_3, lParams);
        ImageView imgView_4 = new ImageView(this);  rl_3.addView(imgView_4, lParams);
        final ImageView audiobutton = findViewById(R.id.audiobutton);

        for(int p = 0; p < 4; p++){
            image_Resource[p] = this.getResources().getIdentifier( cards_src[p], "drawable", this.getPackageName());
            sound_Resource[p] = this.getResources().getIdentifier( cards_src[p], "raw", this.getPackageName());
        }

        textView0.setText(cards_name[0]);         imgView_1.setImageResource(image_Resource[0]);
        textView1.setText(cards_name[1]);         imgView_2.setImageResource(image_Resource[1]);
        textView2.setText(cards_name[2]);         imgView_3.setImageResource(image_Resource[2]);
        textView3.setText(cards_name[3]);         imgView_4.setImageResource(image_Resource[3]);

        Random random = new Random();
        final int voice = Integer.valueOf(random.nextInt(4));
        Log.d(TAG, String.valueOf(voice));
        final MediaPlayer sound = MediaPlayer.create(this, sound_Resource[voice]);
        sound.start();
        Log.d(TAG, String.valueOf(voice));
        final MediaPlayer sound1 = MediaPlayer.create(this, sound_Resource[0]);
        final MediaPlayer sound2 = MediaPlayer.create(this, sound_Resource[1]);
        final MediaPlayer sound3 = MediaPlayer.create(this, sound_Resource[2]);
        final MediaPlayer sound4 = MediaPlayer.create(this, sound_Resource[3]);

        int rl_answer = this.getResources().getIdentifier( "rl_" + voice, "id", this.getPackageName());
        final int current_rl = rl_answer;

        Log.d(TAG, "Current RL" + rl_answer);
        Log.d(TAG, "Current RL" + R.id.rl_0);
        Log.d(TAG, "Current RL" + R.id.rl_1);
        Log.d(TAG, "Current RL" + R.id.rl_2);
        Log.d(TAG, "Current RL" + R.id.rl_3);
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
                            Log.d(TAG,"Try open Activity2");
                            if (rl_0.isActivated() == true) {
                                Intent intent = new Intent(LessonActivity.this, Activity2.class);
                                startActivity(intent);
                            }
                            else if (rl_1.isActivated() == true){
                                Intent intent = new Intent(LessonActivity.this, Activity2.class);
                                startActivity(intent);
                            }
                            else if (rl_2.isActivated() == true) {
                                Intent intent = new Intent(LessonActivity.this, Activity2.class);
                                startActivity(intent);
                            }
                            else if (rl_3.isActivated() == true) {
                                Intent intent = new Intent(LessonActivity.this, Activity2.class);
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
            audiobutton.setOnClickListener(onClickListener);
            button.setOnClickListener(onClickListener);
        db.delete(TABLE, null,null);
        dbHelper.close();
        }
        /*public void insertingValues(){
            dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues cV = new ContentValues();

            dbHelper.insertData(1,0,  "hue" ,"l1_0");
            dbHelper.insertData(1,1,  "ghue","l1_1");
            dbHelper.insertData(1,2,  "ngue","l1_2");
            dbHelper.insertData(1,3,  "eo"  ,"l1_3");

        }
*/
    }









