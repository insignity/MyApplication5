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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;

public class Lesson4 extends AppCompatActivity {
    final String TAG = "myLogs";
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    boolean task = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4);
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
        int[] id_word = new int[100];
        final String[] text_word = new String[100];
        final String[] text_russian = new String[100];
        int z = 0;
        Cursor cursor = mDb.rawQuery("SELECT * FROM sentences WHERE id_lesson = " + lessonNum, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            id_word[z] = cursor.getInt(1);
            text_word[z] = cursor.getString(2);
            text_russian[z] = cursor.getString(3);
            z++;
            cursor.moveToNext();
        }
        cursor.close();

        final Button word[] = new Button[100];
        final Positions words = new Positions();
        words.wallpaper = findViewById(R.id.wallpaper);
        final Button button = findViewById(R.id.button);
        word[0] = findViewById(R.id.word0);
        word[1] = findViewById(R.id.word1);
        word[2] = findViewById(R.id.word2);
        word[3] = findViewById(R.id.word3);
        word[4] = findViewById(R.id.word4);

        String answer = "";
        TextView textView = findViewById(R.id.textView);
        for (int p = 0; p < z; p++) {
            answer += text_russian[p];
            word[p].setText(text_word[p]);
        }
        textView.setText(answer);
        final Intent intent1 = new Intent(Lesson4.this, MainActivity.class);
        for (int i = 0; i < words.positions.length; i++)
            words.setPositions(i, word[i].getX(), word[i].getY());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button) {
                    if (task)
                        intent1.putExtra("lessonNum", lessonNum);
                        startActivity(intent1);
                }
                for (int i = 0; i < words.positions.length; i++)
                    if (v == word[i]) {
                        task = false;
                        if (words.used[i]) {
                            moveView(word[i], words.getStartPositionX(i), words.getStartPositionY(i));
                            words.removeExistingPosition(i);
                            words.used[i] = false;
                        } else {
                            moveView(word[i], words.getPositionX(), words.getPositionY());
                            words.addPosition(word[i].getWidth(), word[i].getHeight(), i);
                            words.used[i] = true;
                        }
                        Log.d(TAG, "if" + words.positions[0]);
                        if (Arrays.equals(words.positions, words.answer)) {
                            task = true;
                            Log.d(TAG, " pos = ans");
                        }
                    }

            }
        };
        for (int i = 0; i < words.positions.length; i++)
            word[i].setOnClickListener(onClickListener);
        button.setOnClickListener(onClickListener);

    }


    public void moveView(Button button, float x, float y) {
        button.animate().x(x).y(y).setDuration(500).start();
    }

    class Positions {
        int[] positions;
        int[] answer;
        boolean[] used;
        public LinearLayout wallpaper;
        float x[];
        float y[];
        float firstX[];
        float firstY[];
        boolean b;
        boolean a;

        public void addPosition(float width, float height, int i) {
            x[getCurrentPosition()] = width;
            y[getCurrentPosition()] = height;
            positions[getCurrentPosition()] = i;
        }
        public void removeExistingPosition(int i){
            x[i] = 0;
            y[i] = 0;
            a = false;
            for (int j = 0; j < positions.length; j++)
                if(positions[j] == i) positions[j] = -1;
            checkingB();
        }
        public void checkingB(){
            float g = wallpaper.getX();
            for(int i = 0; i < 3; i++)
                g += x[i];
            if(g > 705f)
                b = true;
            else
                b = false;
        }
        public float getPositionX() {
            float returnX = wallpaper.getX();
            checkingB();
            if (b == false){
                for (int i = 0; i < getCurrentPosition(); i++)
                returnX += x[getCurrentPosition() - 1];
            }else if(a == true){
                returnX += x[getCurrentPosition() - 1];
            }else
                a = true;
            return returnX;
        }

        public float getPositionY() {
            checkingB();
            float returnY = wallpaper.getY() + 5f;
            if (b == true) {
                return returnY + 120;
            }
            return returnY;
        }
        Positions() {
            positions = new int[]{-1, -1, -1, -1, -1};
            answer = new int[]{0, 1, 2, -1, -1};
            used = new boolean[]{false, false, false, false, false};
            x = new float[]{0, 0, 0, 0, 0};
            y = new float[]{0, 0, 0, 0, 0};
            firstX = new float[]{0, 0, 0, 0, 0};
            firstY = new float[]{0, 0, 0, 0, 0};
            b = false;
            a = false;
        }

        public void setPositions(int i, float setx, float sety) {
            firstX[i] = setx;
            firstY[i] = sety;
        }

        public float getStartPositionX(int i) {
            return firstX[i];
        }

        public float getStartPositionY(int i) {
            return firstY[i];
        }

        public int getCurrentPosition() {
            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == -1)
                    return i;
            }
            return 4;
        }
    }
}