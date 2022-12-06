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

import com.example.myapplication.DTO.NguPhap;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NguPhapAdapter extends BaseAdapter {
    Context context;
    ArrayList<NguPhap> nguPhaps;

    public NguPhapAdapter(Context context, ArrayList<NguPhap> nguPhaps) {
        this.context = context;
        this.nguPhaps = nguPhaps;
    }

    @Override
    public int getCount() {
        return nguPhaps.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_ngu_phap,null);
        }
        TextView tvCauHoi = view.findViewById(R.id.tvCauHoi);
        String cauHoi = nguPhaps.get(i).getCauHoi();
        tvCauHoi.setText("Câu hỏi: "+cauHoi);
        TextView tvLuachon1 = view.findViewById(R.id.tvLuachon1);
        String luaChon1 = nguPhaps.get(i).getLuaChon1();
        tvLuachon1.setText("Lựa chọn 1: "+luaChon1);
        TextView tvLuachon2 = view.findViewById(R.id.tvLuachon2);
        String luaChon2 = nguPhaps.get(i).getLuaChon2();
        tvLuachon2.setText("Lựa chọn 2: "+luaChon2);
        TextView tvLuachon3 = view.findViewById(R.id.tvLuachon3);
        String luaChon3 = nguPhaps.get(i).getLuaChon3();
        tvLuachon3.setText("Lựa chọn 3: "+luaChon3);
        TextView tvLuachon4 = view.findViewById(R.id.tvLuachon4);
        String luaChon4 = nguPhaps.get(i).getLuaChon4();
        tvLuachon4.setText("Lựa chọn 4: "+luaChon4);
        TextView tvDapAn = view.findViewById(R.id.tvDapAn);
        int dapAn = nguPhaps.get(i).getDapAn();
        tvDapAn.setText("Đáp án: "+dapAn);
        ImageView ivXoa = view.findViewById(R.id.ivXoa);
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
                        DatabaseReference reference = database.getReference("DanhSachNguPhap");
                        reference.child(cauHoi).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(context, "Xóa ngữ pháp thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.create();
                builder.show();
            }
        });
        ImageView ivChinhSua = view.findViewById(R.id.ivChinhSua);
        ivChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.sua_ngu_phap_dialog,null);
                builder.setView(view1);
                EditText edCauHoi = view1.findViewById(R.id.edCauHoi);
                EditText edLuaChon1 = view1.findViewById(R.id.edLuaChon1);
                EditText edLuaChon2 = view1.findViewById(R.id.edLuaChon2);
                EditText edLuaChon3 = view1.findViewById(R.id.edLuaChon3);
                EditText edLuaChon4 = view1.findViewById(R.id.edLuaChon4);
                EditText edDapAn = view1.findViewById(R.id.edDapAn);
                edCauHoi.setText(cauHoi);
                edLuaChon1.setText(luaChon1);
                edLuaChon2.setText(luaChon2);
                edLuaChon3.setText(luaChon3);
                edLuaChon4.setText(luaChon4);
                edDapAn.setText(dapAn+"");
                builder.setNegativeButton("Hủy",null);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("DanhSachNguPhap");
                        reference.child(cauHoi).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                String cauHoi1 = edCauHoi.getText().toString().trim();
                                String luaChon1 = edLuaChon1.getText().toString().trim();
                                String luaChon2 = edLuaChon2.getText().toString().trim();
                                String luaChon3 = edLuaChon3.getText().toString().trim();
                                String luaChon4 = edLuaChon4.getText().toString().trim();
                                int dapAn = Integer.parseInt(edDapAn.getText().toString().trim());
                                NguPhap nguPhap = new NguPhap(cauHoi1,luaChon1,luaChon2,luaChon3,luaChon4,dapAn);
                                reference.child(cauHoi1).setValue(nguPhap, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(context, "Sửa ngữ pháp thành công", Toast.LENGTH_SHORT).show();
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
