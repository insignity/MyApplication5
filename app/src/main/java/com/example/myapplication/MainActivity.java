package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "myLogs";
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView123);
        imageView1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView123:
                final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
                v.startAnimation(animAlpha);
                Intent intent = new Intent(MainActivity.this, Lesson1.class);
                startActivity(intent);
        }
    }
}
