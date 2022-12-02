package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.TaiKhoan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChinhSuaThongTinActivity extends AppCompatActivity {
    ImageView ivHinh,ivTroLai;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edLinkAnh;
    Button btnSua;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin);
        AnhXa();
        edHoTen.addTextChangedListener(textWatcher);
        edMatKhau.addTextChangedListener(textWatcher);
        edNhapLaiMatKhau.addTextChangedListener(textWatcher);
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        String linkAnh = preferences.getString("linkanh","");
        String tenDangNhap = preferences.getString("tendangnhap","");
        String phanLoai = preferences.getString("phanloai","");
        int tongDiem = preferences.getInt("tongdiem",0);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("DanhSachTaiKhoan");
                String hoTen = edHoTen.getText().toString().trim();
                String matKhau = edMatKhau.getText().toString().trim();
                String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString().trim();
                String anh = edLinkAnh.getText().toString().trim();
                if(matKhau.length()<6){
                    Toast.makeText(ChinhSuaThongTinActivity.this, "Mật khẩu phải tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }else if(matKhau.equals(nhapLaiMatKhau)==false){
                    Toast.makeText(ChinhSuaThongTinActivity.this, "Mật khẩu và nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap,matKhau,hoTen,tongDiem,anh,phanLoai);
                reference.child(tenDangNhap).setValue(taiKhoan);
                Toast.makeText(ChinhSuaThongTinActivity.this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChinhSuaThongTinActivity.this,Manhinhdangnhap.class);
                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }
        });
        Glide.with(this).load(linkAnh).into(ivHinh);
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String hoTen = edHoTen.getText().toString().trim();
            String matKhau = edMatKhau.getText().toString().trim();
            String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString().trim();
            btnSua.setEnabled(!hoTen.isEmpty()&&!matKhau.isEmpty()&&!nhapLaiMatKhau.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private void AnhXa() {
        ivHinh = findViewById(R.id.ivHinh);
        edLinkAnh = findViewById(R.id.edLinkAnh);
        edHoTen = findViewById(R.id.edHoTen);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhapLaiMatKhau = findViewById(R.id.edNhapLaiMatKhau);
        btnSua = findViewById(R.id.btnSua);
        ivTroLai = findViewById(R.id.ivTroLai);
    }
}