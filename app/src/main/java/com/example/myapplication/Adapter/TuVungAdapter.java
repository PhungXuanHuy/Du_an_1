package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.NguPhap;
import com.example.myapplication.DTO.TuVung;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TuVungAdapter extends BaseAdapter {
    Context context;
    ArrayList<TuVung> tuVungs;

    public TuVungAdapter(Context context, ArrayList<TuVung> tuVungs) {
        this.context = context;
        this.tuVungs = tuVungs;
    }

    @Override
    public int getCount() {
        return tuVungs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_tu_vung,null);
        }
        String tuVung = tuVungs.get(i).getTenTuVung();
        String nghia = tuVungs.get(i).getNghiaTuVung();
        String anh = tuVungs.get(i).getAnh();
        TextView tvTuVung = view.findViewById(R.id.tvTuVung);
        tvTuVung.setText("Từ vựng: "+tuVung);
        TextView tvNghia = view.findViewById(R.id.tvNghia);
        tvNghia.setText("Nghĩa: "+nghia);
        TextView tvAnh = view.findViewById(R.id.tvAnh);
        tvAnh.setText("Link ảnh: "+anh);
        ImageView ivXoa = view.findViewById(R.id.ivXoa);
        ImageView ivChinhSua = view.findViewById(R.id.ivChinhSua);
        ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có chắc muốn xóa ngữ pháp không?");
                builder.setNegativeButton("Không",null);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("DanhSachTuVung");
                        reference.child(tuVung).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(context, "Xóa từ vựng thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.create();
                builder.show();
            }
        });
        ivChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.sua_tu_vung_dialog,null);
                builder.setView(view1);
                EditText edTuVung = view1.findViewById(R.id.edTuVung);
                EditText edNghia = view1.findViewById(R.id.edNghia);
                EditText edLinkAnh = view1.findViewById(R.id.edLinkAnh);
                edTuVung.setText(tuVung);
                edNghia.setText(nghia);
                edLinkAnh.setText(anh);
                builder.setNegativeButton("Hủy",null);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("DanhSachTuVung");
                        reference.child(tuVung).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                String tuvung = edTuVung.getText().toString().trim();
                                String nghia = edNghia.getText().toString().trim();
                                String anh = edLinkAnh.getText().toString().trim();
                                TuVung tuVung1 = new TuVung(tuvung,nghia,anh);
                                reference.child(tuvung).setValue(tuVung1, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(context, "Sửa từ vựng thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return view;
    }
}
