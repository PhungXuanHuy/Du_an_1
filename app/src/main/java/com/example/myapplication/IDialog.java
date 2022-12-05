package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class IDialog extends Dialog {
    private int huongDan1 = 0;
    public IDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idialog_layout);
        Button btnTiepTuc = findViewById(R.id.btnTiepTuc);
        TextView tvHuongDan = findViewById(R.id.tvHuongDan);
        setHuongDan(tvHuongDan,"1. Chọn đáp án và nhấn kiểm tra để trả lời câu hỏi.");
        huongDan1++;
        setHuongDan(tvHuongDan,"2. Bạn sẽ nhận được 1 điểm nếu trả lời đúng.");
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    private void setHuongDan(TextView tvHuongDan, String huongDan2){
        if(huongDan1==0){
            tvHuongDan.setText(huongDan2);
        }else {
            tvHuongDan.setText(tvHuongDan.getText()+"\n\n"+huongDan2);
        }
    }
}
