package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DTO.TaiKhoan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manhinhdangky extends AppCompatActivity {
    EditText edTenDangNhap,edMatKhau,edNhapLaiMatKhau;
    Button btnDangKy;
    ImageView ivTroLai;
    FirebaseDatabase database;
    DatabaseReference reference;
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
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDangNhap = edTenDangNhap.getText().toString().trim();
                String matKhau = edMatKhau.getText().toString().trim();
                String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString().trim();
                if(tenDangNhap.length()<6){
                    Toast.makeText(Manhinhdangky.this, "Tên đăng nhập phải tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }else if(matKhau.length()<6){
                    Toast.makeText(Manhinhdangky.this, "Mật khẩu phải tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }else if(matKhau.equals(nhapLaiMatKhau)==false){
                    Toast.makeText(Manhinhdangky.this, "Mật khẩu và nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("DanhSachTaiKhoan");
                TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap,matKhau,"Người dùng",0,"https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png","khách hàng");
                reference.child(tenDangNhap).setValue(taiKhoan);
                Toast.makeText(Manhinhdangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
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
        edTenDangNhap.addTextChangedListener(textWatcher);
        edMatKhau.addTextChangedListener(textWatcher);
        edNhapLaiMatKhau.addTextChangedListener(textWatcher);
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String tenDangNhap = edTenDangNhap.getText().toString().trim();
            String matKhau = edMatKhau.getText().toString().trim();
            String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString().trim();
            btnDangKy.setEnabled(!tenDangNhap.isEmpty() && !matKhau.isEmpty() && !nhapLaiMatKhau.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}