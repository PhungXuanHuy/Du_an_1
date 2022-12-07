package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.ToiFragment;
import com.example.myapplication.Fragment.XepHangFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    XepHangFragment xepHangFragment;
    ToiFragment toiFragment;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        return true;
                    case R.id.itXepHang:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, xepHangFragment).commit();
                        return true;
                    case R.id.itToi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, toiFragment).commit();
                        return true;
                }
                return true;
            }
        });
    }

    public void AnhXa() {
        navigation = findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
        xepHangFragment = new XepHangFragment();
        toiFragment = new ToiFragment();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Bạn có muốn thoát không?");
        builder.setNegativeButton("Không",null);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}