package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    Dialog dialog;
    String TAG = "myLogs";
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent1 = getIntent();
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Intent intent = new Intent(MainActivity.this, Lesson1.class);

        if (intent1.hasExtra("lessonNum")) {
            MediaPlayer finish = MediaPlayer.create(this, R.raw.mistake);
            finish.start();
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_layoutmistakes);
            dialog.show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                }
            }, 4000);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v == imageView1)
                    intent.putExtra("lessonNum", 1);
                if(v == imageView2)
                    intent.putExtra("lessonNum", 2);
                if(v == imageView3)
                    intent.putExtra("lessonNum", 3);
                if(v == imageView4)
                    intent.putExtra("lessonNum", 4);
                if(v == imageView5)
                    intent.putExtra("lessonNum", 5);
                startActivity(intent);
            }
        };
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        imageView5.setOnClickListener(onClickListener);
    }
}
