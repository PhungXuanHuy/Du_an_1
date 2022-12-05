package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.TaiKhoan;
import com.example.myapplication.DTO.TuVung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KetThucTuVungActivity extends AppCompatActivity {
    ImageView ivHinh;
    TextView tvSoCauDung,tvSoCauSai;
    Button btnTroLai,btnLamLai;
    private ArrayList<TuVung> tuVungs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_thuc_tu_vung);
        ivHinh = findViewById(R.id.ivHinh);
        btnTroLai = findViewById(R.id.btnTroLai);
        btnLamLai = findViewById(R.id.btnLamLai);
        tvSoCauDung = findViewById(R.id.tvSoCauDung);
        tvSoCauSai = findViewById(R.id.tvSoCauSai);
        Glide.with(this).load("https://thumbs.gfycat.com/DismalWarmArmadillo-max-1mb.gif").into(ivHinh);
        tuVungs = (ArrayList<TuVung>) getIntent().getSerializableExtra("cauhoi");
        tvSoCauDung.setText(getSoCauDung()+"");
        tvSoCauSai.setText(String.valueOf(tuVungs.size()-getSoCauDung()));
        btnLamLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KetThucTuVungActivity.this,TuVungActivity.class));
                finish();
            }
        });
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("DanhSachTaiKhoan");
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        String tenDangNhap = sharedPreferences.getString("tendangnhap","");
        String tenNguoiDung = sharedPreferences.getString("tennguoidung","");
        String phanLoai = sharedPreferences.getString("phanloai","");
        String linkAnh = sharedPreferences.getString("linkanh","");
        String matKhau = sharedPreferences.getString("matkhau","");
        Query congDiem = reference.orderByChild("tenDangNhap").equalTo(tenDangNhap);
        congDiem.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int tongDiemFireBase = snapshot.child(tenDangNhap).child("tongDiem").getValue(Integer.class);
                TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap,matKhau,tenNguoiDung,tongDiemFireBase+getSoCauDung(),linkAnh,phanLoai);
                reference.child(tenDangNhap).setValue(taiKhoan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private int getSoCauDung(){
        int soCauDung = 0;
        for (int i = 0;i<tuVungs.size();i++){
            String getDapAn = tuVungs.get(i).getDapAn();
            String getNghia = tuVungs.get(i).getNghiaTuVung();
            if(getNghia.equalsIgnoreCase(getDapAn)){
                soCauDung++;
            }
        }
        return soCauDung;
    }
}