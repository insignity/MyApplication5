package com.example.myapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CongratsView {
        Dialog myDialog;
    public void ShowPopup(View v) {
        LinearLayout linearLayout;
        myDialog.setContentView(R.layout.custom_layout);
        linearLayout = (LinearLayout) myDialog.findViewById(R.id.linearLayoutView);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}