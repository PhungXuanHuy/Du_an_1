package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CaiDatActivity extends AppCompatActivity {
    Button btnDangXuat,btnGioiThieu,btnChinhSuaThongTin;
    ImageView ivTroLai;
    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        ivTroLai = findViewById(R.id.ivTroLai);
        btnGioiThieu = findViewById(R.id.btnGioiThieu);
        btnChinhSuaThongTin = findViewById(R.id.btnChinhSuaThongTin);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== RESULT_OK){
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        btnChinhSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,ChinhSuaThongTinActivity.class);
                launcher.launch(intent);
            }
        });
        btnGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,GioiThieuActivity.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,Manhinhdangnhap.class);
                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }
        });
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}