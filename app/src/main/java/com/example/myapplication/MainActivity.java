package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{


    String TAG = "myLogs";
    ImageView imageView1;
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Intent intent = new Intent(MainActivity.this, Lesson1.class);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == imageView1)
                    intent.putExtra("lessonNum", 1);
                if(v == imageView2)
                    intent.putExtra("lessonNum", 2);
                startActivity(intent);
            }
        };
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
    }

}
