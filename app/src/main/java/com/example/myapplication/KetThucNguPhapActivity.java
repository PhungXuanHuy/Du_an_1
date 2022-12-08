package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.NguPhap;
import com.example.myapplication.DTO.TaiKhoan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KetThucNguPhapActivity extends AppCompatActivity {
    ImageView ivHinh;
    TextView tvSoCauDung,tvSoCauSai;
    Button btnTroLai,btnLamLai;
    private ArrayList<NguPhap> nguPhaps = new ArrayList<>();
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_thuc_ngu_phap);
        ivHinh = findViewById(R.id.ivHinh);
        btnTroLai = findViewById(R.id.btnTroLai);
        tvSoCauDung = findViewById(R.id.tvSoCauDung);
        tvSoCauSai = findViewById(R.id.tvSoCauSai);
        Glide.with(this).load("https://thumbs.gfycat.com/AcademicAdoredCoot-size_restricted.gif").into(ivHinh);
        nguPhaps = (ArrayList<NguPhap>) getIntent().getSerializableExtra("cauhoi");
        tvSoCauDung.setText(getSoCauDung()+"");
        tvSoCauSai.setText(String.valueOf(nguPhaps.size()-getSoCauDung()));
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
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
        player = MediaPlayer.create(KetThucNguPhapActivity.this,R.raw.end);
        player.start();
    }
    private int getSoCauDung(){
        int soCauDung = 0;
        for (int i = 0;i<nguPhaps.size();i++){
            String getNguoiDungChonDapAn = nguPhaps.get(i).getNguoiDungChonDapAn();
            String getDapAn = nguPhaps.get(i).getDapAn();
            if(getNguoiDungChonDapAn.equalsIgnoreCase(getDapAn)){
                soCauDung++;
            }
        }
        return soCauDung;
    }
}