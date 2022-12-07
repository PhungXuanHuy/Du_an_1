package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myapplication.Adapter.StringAdapter;

import java.util.ArrayList;

public class DanhSachBaiHocNguPhapActivity extends AppCompatActivity {
    ListView lvBaiHocNguPhap;
    ImageView ivTroLai;
    ArrayList<String> nguPhaps = new ArrayList<>();
    StringAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hoc_ngu_phap);
        lvBaiHocNguPhap = findViewById(R.id.lvBaiHocTuVung);
        ivTroLai = findViewById(R.id.ivTroLai);
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String thiHienTaiDon = "Thì hiện tại đơn";
        String thiHienTaiTiepDien = "Thì hiện tại tiếp diễn";
        String thiTuongLaiDon = "Thì tương lai đơn";
        nguPhaps.add(thiHienTaiDon);
        nguPhaps.add(thiHienTaiTiepDien);
        nguPhaps.add(thiTuongLaiDon);
        adapter = new StringAdapter(DanhSachBaiHocNguPhapActivity.this,nguPhaps);
        lvBaiHocNguPhap.setAdapter(adapter);
        lvBaiHocNguPhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(DanhSachBaiHocNguPhapActivity.this,NguPhapActivity.class);
                    intent.putExtra("phanloai","thihientaidon");
                    startActivity(intent);
                }else if(i==1){
                    Intent intent = new Intent(DanhSachBaiHocNguPhapActivity.this,NguPhapActivity.class);
                    intent.putExtra("phanloai","thihientaitiepdien");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(DanhSachBaiHocNguPhapActivity.this,NguPhapActivity.class);
                    intent.putExtra("phanloai","thituonglaidon");
                    startActivity(intent);
                }
            }
        });
        IDialog iDialog = new IDialog(DanhSachBaiHocNguPhapActivity.this);
        iDialog.setCancelable(false);
        iDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iDialog.show();
    }
}