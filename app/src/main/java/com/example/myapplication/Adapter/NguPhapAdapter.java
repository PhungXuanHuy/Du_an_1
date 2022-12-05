package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DTO.NguPhap;
import com.example.myapplication.R;

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
        tvCauHoi.setText("Câu hỏi: "+nguPhaps.get(i).getCauHoi());
        TextView tvLuachon1 = view.findViewById(R.id.tvLuachon1);
        tvLuachon1.setText("Lựa chọn 1: "+nguPhaps.get(i).getLuaChon1());
        TextView tvLuachon2 = view.findViewById(R.id.tvLuachon2);
        tvLuachon2.setText("Lựa chọn 2: "+nguPhaps.get(i).getLuaChon2());
        TextView tvLuachon3 = view.findViewById(R.id.tvLuachon3);
        tvLuachon3.setText("Lựa chọn 3: "+nguPhaps.get(i).getLuaChon3());
        TextView tvLuachon4 = view.findViewById(R.id.tvLuachon4);
        tvLuachon4.setText("Lựa chọn 4: "+nguPhaps.get(i).getLuaChon4());
        TextView tvDapAn = view.findViewById(R.id.tvDapAn);
        tvDapAn.setText("Đáp án: "+nguPhaps.get(i).getDapAn());
        ImageView ivXoa = view.findViewById(R.id.ivXoa);
        ImageView ivChinhSua = view.findViewById(R.id.ivChinhSua);
        return view;
    }
}
