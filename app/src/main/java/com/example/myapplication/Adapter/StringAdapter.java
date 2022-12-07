package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class StringAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> mangs = new ArrayList<>();

    public StringAdapter(Context context, ArrayList<String> mangs) {
        this.context = context;
        this.mangs = mangs;
    }

    @Override
    public int getCount() {
        return mangs.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.string_layout,null);
        }
        TextView tvTen = view.findViewById(R.id.tvTen);
        tvTen.setText(mangs.get(i));
        return view;
    }
}
