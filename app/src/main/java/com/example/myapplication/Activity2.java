package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        final MediaPlayer book_s = MediaPlayer.create(this, R.raw.book_s);
        final MediaPlayer book_f = MediaPlayer.create(this, R.raw.book_f);
        Button button2 = findViewById(R.id.buttonac);
        ImageView imageView = findViewById(R.id.imageView6);
        imageView.setImageResource(R.drawable.book);
        TextView textView = findViewById(R.id.textView);
        ImageButton imageButton0 = findViewById(R.id.imageButton0);
        ImageButton imageButton1 = findViewById(R.id.imageButton1);
        final EditText editText2 = findViewById(R.id.editText2);
        button2.setText(R.string.cep);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText2.getText().toString().equals("кинигэ")){
                Intent intent = new Intent(Activity2.this, ActivityBlocks.class);
                startActivity(intent);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book_f.start();
            }
        });
        imageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book_f.start();
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book_s.start();
            }
        });
    }
}
