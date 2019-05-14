package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityBlocks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks);

        final View target = findViewById(R.id.target);
        final View viewToMove = findViewById(R.id.viewToMove);
        viewToMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shift = moveView(target, viewToMove);
            }
        });
    }
    public int removeView(View target, View viewToMove) {
        viewToMove.animate().x(target.getX()).y(target.getY()).setDuration(1000).start();
        return 0;
    }
    public int moveView(View target, View viewToMove){
        viewToMove.animate().x(target.getX()).y(target.getY()).setDuration(1000).start();
        return 1;
    }

}
