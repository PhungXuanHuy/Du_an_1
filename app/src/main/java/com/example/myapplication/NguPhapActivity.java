package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DTO.NguPhap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class NguPhapActivity extends AppCompatActivity {
    private RelativeLayout luaChon1,luaChon2,luaChon3,luaChon4;
    private TextView tvCauHoiHienTai,tvTongCauHoi,tvCauHoi,tvLuaChon1,tvLuaChon2,tvLuaChon3,tvLuaChon4;
    private ImageView luaChon1Icon,luaChon2Icon,luaChon3Icon,luaChon4Icon;
    private Button btnKiemTra;
    private ArrayList<NguPhap> nguPhaps = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("DanhSachNguPhap");
    private int cauHoiHienTai = 0;
    private int selectedLuaChon = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_phap);
        AnhXa();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot cauHoi : snapshot.getChildren()){
                    NguPhap nguPhap = cauHoi.getValue(NguPhap.class);
                    nguPhaps.add(nguPhap);
                }
                Log.d("list", "nguphap: "+nguPhaps.size());
                tvTongCauHoi.setText("/"+nguPhaps.size());
                selectCauHoi(cauHoiHienTai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NguPhapActivity.this, "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();
            }
        });
        IDialog iDialog = new IDialog(NguPhapActivity.this);
        iDialog.setCancelable(false);
        iDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iDialog.show();
        luaChon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = 1;
                selectedLuaChon(luaChon1,luaChon1Icon);
            }
        });
        luaChon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = 2;
                selectedLuaChon(luaChon2,luaChon2Icon);
            }
        });
        luaChon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = 3;
                selectedLuaChon(luaChon3,luaChon3Icon);

            }
        });
        luaChon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = 4;
                selectedLuaChon(luaChon4,luaChon4Icon);
            }
        });
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nguPhaps.get(cauHoiHienTai).setNguoiDungChonDapAn(selectedLuaChon);
                selectedLuaChon = 0;
                cauHoiHienTai++;
                if(cauHoiHienTai<nguPhaps.size()){
                    selectCauHoi(cauHoiHienTai);
                }else {
                    KetThucNguPhap();
                }
            }
        });

    }
    private void KetThucNguPhap(){
        Intent intent = new Intent(NguPhapActivity.this,KetThucNguPhapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cauhoi",(Serializable) nguPhaps);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    private void AnhXa() {
        luaChon1 = findViewById(R.id.luaChon1);
        luaChon2 = findViewById(R.id.luaChon2);
        luaChon3 = findViewById(R.id.luaChon3);
        luaChon4 = findViewById(R.id.luaChon4);
        tvCauHoiHienTai = findViewById(R.id.tvCauHoiHienTai);
        tvTongCauHoi = findViewById(R.id.tvTongCauHoi);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        tvLuaChon1 = findViewById(R.id.tvLuaChon1);
        tvLuaChon2 = findViewById(R.id.tvLuaChon2);
        tvLuaChon3 = findViewById(R.id.tvLuaChon3);
        tvLuaChon4 = findViewById(R.id.tvLuaChon4);
        luaChon1Icon = findViewById(R.id.luaChon1Icon);
        luaChon2Icon = findViewById(R.id.luaChon2Icon);
        luaChon3Icon = findViewById(R.id.luaChon3Icon);
        luaChon4Icon = findViewById(R.id.luaChon4Icon);
        btnKiemTra = findViewById(R.id.btnKiemTra);
    }
    private void selectCauHoi(int cauHoiPosition){
        resetLuaChon();
        btnKiemTra.setEnabled(false);
        tvCauHoi.setText(nguPhaps.get(cauHoiPosition).getCauHoi());
        tvLuaChon1.setText(nguPhaps.get(cauHoiPosition).getLuaChon1());
        tvLuaChon2.setText(nguPhaps.get(cauHoiPosition).getLuaChon2());
        tvLuaChon3.setText(nguPhaps.get(cauHoiPosition).getLuaChon3());
        tvLuaChon4.setText(nguPhaps.get(cauHoiPosition).getLuaChon4());
        tvCauHoiHienTai.setText("Câu "+(cauHoiPosition+1));
    }
    private void resetLuaChon(){
        luaChon1.setBackgroundResource(R.drawable.shape_lua_chon);
        luaChon2.setBackgroundResource(R.drawable.shape_lua_chon);
        luaChon3.setBackgroundResource(R.drawable.shape_lua_chon);
        luaChon4.setBackgroundResource(R.drawable.shape_lua_chon);

        luaChon1Icon.setImageResource(R.drawable.lua_chon_icon);
        luaChon2Icon.setImageResource(R.drawable.lua_chon_icon);
        luaChon3Icon.setImageResource(R.drawable.lua_chon_icon);
        luaChon4Icon.setImageResource(R.drawable.lua_chon_icon);
    }
    private void selectedLuaChon(RelativeLayout LuaChon, ImageView ivIcon){
        resetLuaChon();
        ivIcon.setImageResource(R.drawable.ic_kiem_tra);
        LuaChon.setBackgroundResource(R.drawable.shape_lua_chon2);
    }
}