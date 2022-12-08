package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.TuVung;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    TextView tvCauHoiHienTai, tvTongCauHoi, tvCauHoi;
    EditText edDapAn;
    ImageView ivHinh, ivThoat;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("DanhSachTuVung");
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_vung);
        edDapAn = findViewById(R.id.edDapAn);
        ivHinh = findViewById(R.id.ivHinh);
        ivThoat = findViewById(R.id.ivThoat);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        tvCauHoiHienTai = findViewById(R.id.tvCauHoiHienTai);
        tvTongCauHoi = findViewById(R.id.tvTongCauHoi);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        edDapAn.addTextChangedListener(watcher);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cauHoi : snapshot.getChildren()) {
                    TuVung tuVung = cauHoi.getValue(TuVung.class);
                    Intent intent = getIntent();
                    String phanLoai = intent.getStringExtra("tuvung");
                    if (tuVung.getPhanLoai().equalsIgnoreCase(phanLoai)) {
                        tuVungs.add(tuVung);
                    }
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
                String check = tuVungs.get(cauHoiHienTai).getNghiaTuVung();
                String dapAn = edDapAn.getText().toString().trim();
                BottomSheetDialog dialog = new BottomSheetDialog(TuVungActivity.this);
                if (dapAn.equalsIgnoreCase(check)) {
                    View view1 = LayoutInflater.from(TuVungActivity.this).inflate(R.layout.dung_bottomsheet, null);
                    Button btnTiepTuc = view1.findViewById(R.id.btnTiepTuc);
                    btnTiepTuc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.stop();
                            cauHoiHienTai++;
                            if (cauHoiHienTai < tuVungs.size()) {
                                selectCauHoi(cauHoiHienTai);
                            } else {
                                KetThucTuVung();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.setContentView(view1);
                    dialog.setCancelable(false);
                    dialog.show();
                    player = MediaPlayer.create(TuVungActivity.this,R.raw.dung);
                    player.start();
                } else {
                    View view1 = LayoutInflater.from(TuVungActivity.this).inflate(R.layout.sai_bottomsheet, null);
                    Button btnHieuRoi = view1.findViewById(R.id.btnHieuRoi);
                    TextView tvDapAnDung = view1.findViewById(R.id.tvDapAnDung);
                    tvDapAnDung.setText(tuVungs.get(cauHoiHienTai).getNghiaTuVung());
                    btnHieuRoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.stop();
                            cauHoiHienTai++;
                            if (cauHoiHienTai < tuVungs.size()) {
                                selectCauHoi(cauHoiHienTai);
                            } else {
                                KetThucTuVung();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.setContentView(view1);
                    dialog.setCancelable(false);
                    dialog.show();
                    player = MediaPlayer.create(TuVungActivity.this,R.raw.sai);
                    player.start();
                }
            }
        });
        ivThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TuVungActivity.this);
                builder.setTitle("Bạn có muốn kết thúc bài học không?");
                builder.setNegativeButton("Không", null);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(TuVungActivity.this, KetThucTuVungActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cauhoi", (Serializable) tuVungs);
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

    private void selectCauHoi(int cauHoiPosition) {
        resetDapAn();
        btnKiemTra.setEnabled(false);
        tvCauHoi.setText(tuVungs.get(cauHoiPosition).getTenTuVung());
        Glide.with(TuVungActivity.this).load(tuVungs.get(cauHoiPosition).getAnh()).into(ivHinh);
        tvCauHoiHienTai.setText("Câu " + (cauHoiPosition + 1));
    }

    private void resetDapAn() {
        edDapAn.setText("");
        btnKiemTra.setText("Kiểm tra");
    }

    private void KetThucTuVung(){
        Intent intent = new Intent(TuVungActivity.this,KetThucTuVungActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cauhoi", (Serializable) tuVungs);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TuVungActivity.this);
        builder.setTitle("Bạn có muốn kết thúc bài học không?");
        builder.setNegativeButton("Không", null);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(TuVungActivity.this, KetThucTuVungActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cauhoi", (Serializable) tuVungs);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}