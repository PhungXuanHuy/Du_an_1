package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BatDauNguPhapActivity extends AppCompatActivity {
    Button btnBatDau,btnVanChua;
    ImageView ivHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau);
        AnhXa();
        Glide.with(getApplicationContext()).load("https://i.gifer.com/origin/3a/3a4719e030c7ba54dc56146015e139c8_w200.gif").into(ivHinh);
        btnVanChua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BatDauNguPhapActivity.this,DanhSachBaiHocNguPhapActivity.class));
            }
        });
    }

    private void AnhXa() {
        btnBatDau = findViewById(R.id.btnBatDau);
        btnVanChua = findViewById(R.id.btnVanChua);
        ivHinh = findViewById(R.id.ivHinh);
    }
}