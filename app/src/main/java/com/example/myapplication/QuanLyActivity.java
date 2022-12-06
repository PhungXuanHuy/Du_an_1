package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QuanLyActivity extends AppCompatActivity {
    ImageView ivTroLai;
    Button btnThemNguPhap,btnThemTuVung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);
        ivTroLai = findViewById(R.id.ivTroLai);
        btnThemNguPhap = findViewById(R.id.btnThemNguPhap);
        btnThemTuVung = findViewById(R.id.btnThemTuVung);
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThemNguPhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyActivity.this,ThemNguPhapActivity.class));
            }
        });
        btnThemTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyActivity.this,ThemTuVungActivity.class));
            }
        });
    }
}