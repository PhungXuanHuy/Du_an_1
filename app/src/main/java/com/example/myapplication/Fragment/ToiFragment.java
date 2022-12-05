package com.example.myapplication.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.CaiDatActivity;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToiFragment extends Fragment {
    TextView tvNguoiDung;
    TextView tvTaiKhoan;
    TextView tvTongDiem;
    ImageView ivHinh;
    ImageView ivCaiDat;
    SharedPreferences sharedPreferences;
    private ActivityResultLauncher<Intent> launcher;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToiFragment newInstance(String param1, String param2) {
        ToiFragment fragment = new ToiFragment();
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
        View view = inflater.inflate(R.layout.fragment_toi, container, false);
        tvNguoiDung = view.findViewById(R.id.tvNguoiDung);
        tvTaiKhoan = view.findViewById(R.id.tvTaiKhoan);
        tvTongDiem = view.findViewById(R.id.tvTongDiem);
        ivHinh = view.findViewById(R.id.ivHinh);
        ivCaiDat = view.findViewById(R.id.ivCaiDat);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== RESULT_OK){
                    getActivity().finish();
                }
            }
        });
        sharedPreferences = getContext().getSharedPreferences("user",MODE_PRIVATE);
        String taikhoan = sharedPreferences.getString("tendangnhap","");
        String linkAnh = sharedPreferences.getString("linkanh","");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("DanhSachTaiKhoan");
        Query query = reference.orderByChild("tenDangNhap").equalTo(taikhoan);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int tongDiemFireBase = snapshot.child(taikhoan).child("tongDiem").getValue(Integer.class);
                String hoTenFireBase = snapshot.child(taikhoan).child("hoTen").getValue(String.class);
                tvNguoiDung.setText(hoTenFireBase);
                tvTaiKhoan.setText(taikhoan);
                tvTongDiem.setText("Tổng điểm: "+tongDiemFireBase);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Glide.with(getContext()).load(linkAnh).into(ivHinh);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CaiDatActivity.class);
                launcher.launch(intent);
            }
        });
    }
}