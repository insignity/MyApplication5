package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class FinalView extends AppCompatActivity {
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layoutmistakes);
        final Intent intent = getIntent();
        Integer lessonNum = intent.getExtras().getInt("lessonNum");
        Integer lesson1 = intent.getExtras().getInt("lesson1");
        Integer lesson2 = intent.getExtras().getInt("lesson2");
        Integer lesson3 = intent.getExtras().getInt("lesson3");
        Integer lesson4 = intent.getExtras().getInt("lesson4");
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        TextView text3 = findViewById(R.id.text3);
        TextView text4 = findViewById(R.id.text4);
        text1.setText(text1.getText().toString() + lesson1.toString());
        text2.setText(text2.getText().toString() + lesson2.toString());
        text3.setText(text3.getText().toString() + lesson3.toString());
        text4.setText(text4.getText().toString() + lesson4.toString());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(FinalView.this, MainActivity.class);
                startActivity(intent1);
            }
        }, 3000);
    }
}
