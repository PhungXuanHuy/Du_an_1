package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Manhinhdangnhap extends AppCompatActivity {
    EditText edTenDangNhap,edMatKhau;
    Button btnDangNhap;
    TextView tvDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangnhap);
        AnhXa();
        edTenDangNhap.addTextChangedListener(textWatcher);
        edMatKhau.addTextChangedListener(textWatcher);
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manhinhdangnhap.this,Manhinhdangky.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manhinhdangnhap.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AnhXa() {
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDangKy = findViewById(R.id.tvDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        edTenDangNhap.setText("");
        edMatKhau.setText("");
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String tenDangNhap = edTenDangNhap.getText().toString().trim();
            String matKhau = edMatKhau.getText().toString().trim();
            btnDangNhap.setEnabled(!tenDangNhap.isEmpty() && !matKhau.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}