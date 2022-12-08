package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.NguPhapAdapter;
import com.example.myapplication.Adapter.TuVungAdapter;
import com.example.myapplication.DTO.NguPhap;
import com.example.myapplication.DTO.TuVung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThemTuVungActivity extends AppCompatActivity {
    ImageView ivTroLai,ivThemTuVung;
    ArrayList<TuVung> tuVungs = new ArrayList<>();
    TuVungAdapter tuVungAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    ListView lvTuVung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tu_vung);
        ivTroLai = findViewById(R.id.ivTroLai);
        ivThemTuVung = findViewById(R.id.ivThemTuVung);
        lvTuVung = findViewById(R.id.lvTuVung);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("DanhSachTuVung");
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lvTuVung.setAdapter(tuVungAdapter);
        ivThemTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThemTuVungActivity.this);
                View view1 = LayoutInflater.from(ThemTuVungActivity.this).inflate(R.layout.them_tu_vung_dialog,null);
                builder.setView(view1);
                EditText edTuVung = view1.findViewById(R.id.edTuVung);
                EditText edNghia = view1.findViewById(R.id.edNghia);
                EditText edAnh = view1.findViewById(R.id.edAnh);
                EditText edPhanLoai = view1.findViewById(R.id.edPhanLoai);
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tuVung = edTuVung.getText().toString().trim();
                        String nghia = edNghia.getText().toString().trim();
                        String anh = edAnh.getText().toString().trim();
                        String phanLoai = edPhanLoai.getText().toString().trim();
                        TuVung tuVung1 = new TuVung(tuVung,nghia,anh,phanLoai);
                        reference.child(tuVung).setValue(tuVung1);
                        Toast.makeText(ThemTuVungActivity.this, "Thêm ngữ pháp thành công!", Toast.LENGTH_SHORT).show();
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(tuVungs!=null){
                                    tuVungs.clear();
                                }
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    TuVung tuVung2 = dataSnapshot.getValue(TuVung.class);
                                    tuVungs.add(tuVung2);
                                }
                                tuVungAdapter = new TuVungAdapter(ThemTuVungActivity.this,tuVungs);
                                tuVungAdapter.notifyDataSetChanged();
                                lvTuVung.setAdapter(tuVungAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TuVung tuVung = dataSnapshot.getValue(TuVung.class);
                    tuVungs.add(tuVung);
                }
                tuVungAdapter = new TuVungAdapter(ThemTuVungActivity.this,tuVungs);
                lvTuVung.setAdapter(tuVungAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}