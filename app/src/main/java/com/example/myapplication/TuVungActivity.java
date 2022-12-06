package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.TuVung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class TuVungActivity extends AppCompatActivity {
    private ArrayList<TuVung> tuVungs = new ArrayList<>();
    private Button btnKiemTra;
    private int cauHoiHienTai = 0;
    TextView tvCauHoiHienTai,tvTongCauHoi,tvCauHoi;
    EditText edDapAn;
    ImageView ivHinh;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("DanhSachTuVung");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_vung);
        edDapAn = findViewById(R.id.edDapAn);
        ivHinh = findViewById(R.id.ivHinh);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        tvCauHoiHienTai = findViewById(R.id.tvCauHoiHienTai);
        tvTongCauHoi = findViewById(R.id.tvTongCauHoi);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        edDapAn.addTextChangedListener(watcher);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot cauHoi : snapshot.getChildren()){
                    TuVung tuVung = cauHoi.getValue(TuVung.class);
                    tuVungs.add(tuVung);
                }
                tvTongCauHoi.setText("/"+tuVungs.size());
                selectCauHoi(cauHoiHienTai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuVungs.get(cauHoiHienTai).setDapAn(edDapAn.getText().toString().trim());
                cauHoiHienTai++;
                if(cauHoiHienTai<tuVungs.size()){
                    selectCauHoi(cauHoiHienTai);
                }else {
                    KetThucTuVung();
                }
            }
        });
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String dapAn = edDapAn.getText().toString().trim();
            btnKiemTra.setEnabled(!dapAn.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private void selectCauHoi(int cauHoiPosition){
        resetDapAn();
        btnKiemTra.setEnabled(false);
        tvCauHoi.setText(tuVungs.get(cauHoiPosition).getTenTuVung());
        Glide.with(TuVungActivity.this).load(tuVungs.get(cauHoiPosition).getAnh()).into(ivHinh);
        tvCauHoiHienTai.setText("Câu "+(cauHoiPosition+1));
    }
    private void resetDapAn(){
        edDapAn.setText("");
    }

    // chạy app và reset lại


    private void KetThucTuVung(){
        Intent intent = new Intent(TuVungActivity.this,KetThucTuVungActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cauhoi",(Serializable) tuVungs);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}