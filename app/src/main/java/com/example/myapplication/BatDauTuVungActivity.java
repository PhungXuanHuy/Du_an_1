package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BatDauTuVungActivity extends AppCompatActivity {
    Button btnBatDau,btnVanChua;
    ImageView ivHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau_tu_vung);
        btnBatDau = findViewById(R.id.btnBatDau);
        btnVanChua = findViewById(R.id.btnVanChua);
        ivHinh = findViewById(R.id.ivHinh);
        Glide.with(this).load("https://thumbs.gfycat.com/ExaltedSharpEland-size_restricted.gif").into(ivHinh);
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BatDauTuVungActivity.this,TuVungActivity.class));
            }
        });
        btnVanChua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}