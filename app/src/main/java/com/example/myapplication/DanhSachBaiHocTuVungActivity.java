package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Adapter.StringAdapter;

import java.util.ArrayList;

public class DanhSachBaiHocTuVungActivity extends AppCompatActivity {
    ListView lvBaiHocTuVung;
    ImageView ivTroLai;
    ArrayList<String> tuVungs = new ArrayList<>();
    StringAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hoc_tu_vung);
        lvBaiHocTuVung = findViewById(R.id.lvBaiHocTuVung);
        ivTroLai = findViewById(R.id.ivTroLai);
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String tuVungCoBan = "Từ vựng cơ bản";
        String tuVungNangCao = "Từ vựng nâng cao";
        tuVungs.add(tuVungCoBan);
        tuVungs.add(tuVungNangCao);
        adapter = new StringAdapter(DanhSachBaiHocTuVungActivity.this,tuVungs);
        lvBaiHocTuVung.setAdapter(adapter);
        lvBaiHocTuVung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(DanhSachBaiHocTuVungActivity.this,TuVungActivity.class);
                    intent.putExtra("tuvung","tuvungcoban");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(DanhSachBaiHocTuVungActivity.this,TuVungActivity.class);
                    intent.putExtra("tuvung","tuvungnangcao");
                    startActivity(intent);
                }
            }
        });
    }
}