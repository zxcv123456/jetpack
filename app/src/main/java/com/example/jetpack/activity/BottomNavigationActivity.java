package com.example.jetpack.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.jetpack.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * 使用nav_host_fragment必须是继承AppCompatActivity或者FragmentActivity才可以
 * AppCompatActivity有导航栏
 */
public class BottomNavigationActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigaion);
        //BottomNavigationView,相关的menu，可以直接在视图界面设置menu
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //这里NavController使用的是有activity，和host的构造方法
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

}