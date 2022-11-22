package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    BottomNavigationView bottom_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                        return true;
                    case R.id.itXepHang:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,xepHangFragment).commit();
                        return true;
                    case R.id.itToi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,toiFragment).commit();
                        return true;
                }
                return true;
            }
        });
    }

    public void AnhXa() {
        bottom_nav = findViewById(R.id.bottom_nav);
        homeFragment = new HomeFragment();
        xepHangFragment = new XepHangFragment();
        toiFragment = new ToiFragment();
    }

}