package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Manhinhdangky extends AppCompatActivity {
    EditText edTenDangNhap,edMatKhau,edNhapLaiMatKhau;
    Button btnDangKy;
    ImageView ivTroLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangky);
        AnhXa();
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhapLaiMatKhau = findViewById(R.id.edNhapLaiMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        ivTroLai = findViewById(R.id.ivTroLai);
    }

}