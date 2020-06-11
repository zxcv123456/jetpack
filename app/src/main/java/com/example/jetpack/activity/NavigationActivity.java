package com.example.jetpack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigationBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_navigation);
    }
}