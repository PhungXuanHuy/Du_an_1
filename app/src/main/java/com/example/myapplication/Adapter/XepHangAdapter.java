package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.TaiKhoan;
import com.example.myapplication.R;

import java.util.ArrayList;

public class XepHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<TaiKhoan> taiKhoans;

    public XepHangAdapter(Context context, ArrayList<TaiKhoan> taiKhoans) {
        this.context = context;
        this.taiKhoans = taiKhoans;
    }

    @Override
    public int getCount() {
        return taiKhoans.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_xep_hang,null);
        }
        ImageView ivXepHang = view.findViewById(R.id.ivXepHang);
        if(i==0){
            Glide.with(context).load("https://img.icons8.com/fluency/512/1.png").into(ivXepHang);
        }else if(i==1){
            Glide.with(context).load("https://img.icons8.com/fluency/512/2.png").into(ivXepHang);
        }else if(i==2){
            Glide.with(context).load("https://img.icons8.com/fluency/512/3.png").into(ivXepHang);
        }else if(i==3){
            Glide.with(context).load("https://img.icons8.com/fluency/512/4.png").into(ivXepHang);
        }else if(i==4){
            Glide.with(context).load("https://img.icons8.com/fluency/512/5.png").into(ivXepHang);
        }else if(i==5){
            Glide.with(context).load("https://img.icons8.com/fluency/512/6.png").into(ivXepHang);
        }else if(i==6){
            Glide.with(context).load("https://img.icons8.com/fluency/512/7.png").into(ivXepHang);
        }else if(i==7){
            Glide.with(context).load("https://img.icons8.com/fluency/512/8.png").into(ivXepHang);
        }else if(i==8){
            Glide.with(context).load("https://img.icons8.com/fluency/512/9.png").into(ivXepHang);
        }else if(i==9){
            Glide.with(context).load("https://img.icons8.com/fluency/512/10.png").into(ivXepHang);
        }
        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
        Glide.with(context).load(taiKhoans.get(i).getLinkAnh()).into(ivAvatar);
        TextView tvHoTen = view.findViewById(R.id.tvHoTen);
        tvHoTen.setText(taiKhoans.get(i).getHoTen());
        TextView tvTongDiem = view.findViewById(R.id.tvTongDiem);
        tvTongDiem.setText(taiKhoans.get(i).getTongDiem()+" điểm");
        return view;
    }
}
