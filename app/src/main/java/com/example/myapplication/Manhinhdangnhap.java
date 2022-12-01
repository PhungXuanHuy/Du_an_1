package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Manhinhdangnhap extends AppCompatActivity {
    EditText edTenDangNhap, edMatKhau;
    Button btnDangNhap;
    TextView tvDangKy;
    FirebaseDatabase database;
    DatabaseReference reference;

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
                Intent intent = new Intent(Manhinhdangnhap.this, Manhinhdangky.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KiemTraDangNhap();
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

    private void KiemTraDangNhap() {
        String tenDangNhap = edTenDangNhap.getText().toString().trim();
        String matKhau = edMatKhau.getText().toString().trim();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("DanhSachTaiKhoan");
        Query kiemTra = reference.orderByChild("tenDangNhap").equalTo(tenDangNhap);
        kiemTra.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String matKhauFireBase = snapshot.child(tenDangNhap).child("matKhau").getValue(String.class);
                    if (matKhau.equals(matKhauFireBase)) {
                        Intent intent = new Intent(Manhinhdangnhap.this, MainActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String tenNguoiDungFireBase = snapshot.child(tenDangNhap).child("hoTen").getValue(String.class);
                        int tongDiem = snapshot.child(tenDangNhap).child("tongDiem").getValue(Integer.class);
                        editor.putString("tennguoidung",tenNguoiDungFireBase);
                        editor.putString("tendangnhap",tenDangNhap);
                        editor.putInt("tongdiem",tongDiem);
                        editor.commit();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Manhinhdangnhap.this, "Thông tin đăng nhập sai", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Manhinhdangnhap.this, "Tên đăng nhập không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}