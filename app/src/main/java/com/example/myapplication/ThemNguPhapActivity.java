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
import com.example.myapplication.DTO.NguPhap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThemNguPhapActivity extends AppCompatActivity {
    ImageView ivTroLai,ivThemNguPhap;
    ArrayList<NguPhap> nguPhaps = new ArrayList<>();
    NguPhapAdapter nguPhapAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    ListView lvNguPhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ngu_phap);
        ivTroLai = findViewById(R.id.ivTroLai);
        ivThemNguPhap = findViewById(R.id.ivThemNguPhap);
        lvNguPhap = findViewById(R.id.lvNguPhap);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("DanhSachNguPhap");
        ivTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivThemNguPhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThemNguPhapActivity.this);
                View view1 = LayoutInflater.from(ThemNguPhapActivity.this).inflate(R.layout.them_ngu_phap_dialog,null);
                builder.setView(view1);
                EditText edCauHoi = view1.findViewById(R.id.edCauHoi);
                EditText edLuaChon1 = view1.findViewById(R.id.edLuaChon1);
                EditText edLuaChon2 = view1.findViewById(R.id.edLuaChon2);
                EditText edLuaChon3 = view1.findViewById(R.id.edLuaChon3);
                EditText edLuaChon4 = view1.findViewById(R.id.edLuaChon4);
                EditText edDapAn = view1.findViewById(R.id.edDapAn);
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cauHoi = edCauHoi.getText().toString().trim();
                        String luaChon1 = edLuaChon1.getText().toString().trim();
                        String luaChon2 = edLuaChon2.getText().toString().trim();
                        String luaChon3 = edLuaChon3.getText().toString().trim();
                        String luaChon4 = edLuaChon4.getText().toString().trim();
                        int dapAn = Integer.parseInt(edDapAn.getText().toString().trim());
                        NguPhap nguPhap = new NguPhap(cauHoi,luaChon1,luaChon2,luaChon3,luaChon4,dapAn);
                        reference.child(cauHoi).setValue(nguPhap);
                        Toast.makeText(ThemNguPhapActivity.this, "Thêm ngữ pháp thành công!", Toast.LENGTH_SHORT).show();
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(nguPhaps!=null){
                                    nguPhaps.clear();
                                }
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    NguPhap nguPhap = dataSnapshot.getValue(NguPhap.class);
                                    nguPhaps.add(nguPhap);
                                }
                                nguPhapAdapter = new NguPhapAdapter(getApplicationContext(),nguPhaps);
                                nguPhapAdapter.notifyDataSetChanged();
                                lvNguPhap.setAdapter(nguPhapAdapter);
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
                    NguPhap nguPhap = dataSnapshot.getValue(NguPhap.class);
                    nguPhaps.add(nguPhap);
                }
                nguPhapAdapter = new NguPhapAdapter(ThemNguPhapActivity.this,nguPhaps);
                lvNguPhap.setAdapter(nguPhapAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}