package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DTO.NguPhap;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    private ImageView luaChon1Icon,luaChon2Icon,luaChon3Icon,luaChon4Icon,ivThoat;
    private Button btnKiemTra;
    private ArrayList<NguPhap> nguPhaps = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("DanhSachNguPhap");
    private int cauHoiHienTai = 0;
    private String selectedLuaChon = "";
    MediaPlayer player;
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
                    Intent intent = NguPhapActivity.this.getIntent();
                    String phanLoaiNguPhap = intent.getStringExtra("phanloai");
                    if(nguPhap.getPhanLoai().equalsIgnoreCase(phanLoaiNguPhap)){
                        nguPhaps.add(nguPhap);
                    }
                }
                tvTongCauHoi.setText("/"+nguPhaps.size());
                selectCauHoi(cauHoiHienTai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NguPhapActivity.this, "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();
            }
        });
        luaChon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = nguPhaps.get(cauHoiHienTai).getLuaChon1();
                selectedLuaChon(luaChon1,luaChon1Icon);
            }
        });
        luaChon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = nguPhaps.get(cauHoiHienTai).getLuaChon2();
                selectedLuaChon(luaChon2,luaChon2Icon);
            }
        });
        luaChon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = nguPhaps.get(cauHoiHienTai).getLuaChon3();
                selectedLuaChon(luaChon3,luaChon3Icon);
            }
        });
        luaChon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKiemTra.setEnabled(true);
                selectedLuaChon = nguPhaps.get(cauHoiHienTai).getLuaChon4();
                selectedLuaChon(luaChon4,luaChon4Icon);
            }
        });
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nguPhaps.get(cauHoiHienTai).setNguoiDungChonDapAn(selectedLuaChon);
                String check = nguPhaps.get(cauHoiHienTai).getNguoiDungChonDapAn();
                String dapAn = nguPhaps.get(cauHoiHienTai).getDapAn();
                BottomSheetDialog dialog = new BottomSheetDialog(NguPhapActivity.this);
                if(check.equalsIgnoreCase(dapAn)){
                    View view1 = LayoutInflater.from(NguPhapActivity.this).inflate(R.layout.dung_bottomsheet, null);
                    Button btnTiepTuc = view1.findViewById(R.id.btnTiepTuc);
                    btnTiepTuc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.stop();
                            selectedLuaChon = "";
                            cauHoiHienTai++;
                            if(cauHoiHienTai<nguPhaps.size()){
                                selectCauHoi(cauHoiHienTai);
                            }else {
                                KetThucNguPhap();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.setContentView(view1);
                    dialog.setCancelable(false);
                    dialog.show();
                    player = MediaPlayer.create(NguPhapActivity.this,R.raw.dung);
                    player.start();
                } else {
                    View view1 = LayoutInflater.from(NguPhapActivity.this).inflate(R.layout.sai_bottomsheet, null);
                    Button btnHieuRoi = view1.findViewById(R.id.btnHieuRoi);
                    TextView tvDapAnDung = view1.findViewById(R.id.tvDapAnDung);
                    tvDapAnDung.setText(nguPhaps.get(cauHoiHienTai).getDapAn());
                    btnHieuRoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.stop();
                            selectedLuaChon = "";
                            cauHoiHienTai++;
                            if(cauHoiHienTai<nguPhaps.size()){
                                selectCauHoi(cauHoiHienTai);
                            }else {
                                KetThucNguPhap();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.setContentView(view1);
                    dialog.setCancelable(false);
                    dialog.show();
                    player = MediaPlayer.create(NguPhapActivity.this,R.raw.sai);
                    player.start();
                }
            }
        });
        ivThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NguPhapActivity.this);
                builder.setTitle("Bạn có muốn kết thúc bài học không?");
                builder.setNegativeButton("Không",null);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(NguPhapActivity.this,KetThucNguPhapActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cauhoi",(Serializable) nguPhaps);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.create();
                builder.show();
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
        ivThoat = findViewById(R.id.ivThoat);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        Intent intent = NguPhapActivity.this.getIntent();
        String phanLoaiNguPhap = intent.getStringExtra("phanloai");
        AlertDialog.Builder builder = new AlertDialog.Builder(NguPhapActivity.this);
        View view = LayoutInflater.from(NguPhapActivity.this).inflate(R.layout.ngu_phap_dialog,null);
        TextView tvHuongDan = view.findViewById(R.id.tvHuongDan);
        builder.setView(view);
        builder.setCancelable(false);
        if(phanLoaiNguPhap.equalsIgnoreCase("thihientaidon")){
            tvHuongDan.setText("thì hiện tại đơn");
        }else if(phanLoaiNguPhap.equalsIgnoreCase("thihientaitiepdien")){
            tvHuongDan.setText("thì hiện tại tiếp diễn");
        }else {
            tvHuongDan.setText("thì tương lai đơn");
        }
        builder.setPositiveButton("Hiểu rồi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NguPhapActivity.this);
        builder.setTitle("Bạn có muốn kết thúc bài học không?");
        builder.setNegativeButton("Không",null);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(NguPhapActivity.this,KetThucNguPhapActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cauhoi",(Serializable) nguPhaps);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}