package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.Adapter.XepHangAdapter;
import com.example.myapplication.DTO.TaiKhoan;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XepHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class XepHangFragment extends Fragment {
    ArrayList<TaiKhoan> taiKhoans;
    XepHangAdapter xepHangAdapter;
    ListView lvXepHang;
    ArrayList<TaiKhoan> top10 = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public XepHangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment XepHangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static XepHangFragment newInstance(String param1, String param2) {
        XepHangFragment fragment = new XepHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xep_hang, container, false);
        lvXepHang = view.findViewById(R.id.lvXepHang);
        taiKhoans = new ArrayList<>();
        getAllTaiKhoan();
        return view;
    }
    private void getAllTaiKhoan(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("DanhSachTaiKhoan");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    TaiKhoan taiKhoan = snapshot1.getValue(TaiKhoan.class);
                    if(taiKhoan.getTongDiem()>0){
                        taiKhoans.add(taiKhoan);
                    }
                }
                Collections.sort(taiKhoans, new Comparator<TaiKhoan>() {
                    @Override
                    public int compare(TaiKhoan taiKhoan, TaiKhoan t1) {
                        return taiKhoan.getTongDiem() - t1.getTongDiem();
                    }
                });
//                for (int i=0;i<=taiKhoans.size();i++){
//                    if(i<10){
//                        if(taiKhoans.get(i)!=null){
//                            Log.d("taiKhoan", "taikhoan: "+taiKhoans.get(i));
//                            top10.add(taiKhoans.get(i));
//                        }
//                    }
//                }
                Collections.reverse(taiKhoans);
                for (int i=0;i<taiKhoans.size();i++){
                    if(i>9){
                        taiKhoans.remove(i);
                    }
                }
                xepHangAdapter = new XepHangAdapter(getContext(),taiKhoans);
                lvXepHang.setAdapter(xepHangAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}