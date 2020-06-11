package com.example.jetpack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.jetpack.R;
import com.example.jetpack.databinding.UtilsActivityBinding;
import com.example.jetpack.utils.JumpUtils;
import com.example.jetpack.viewmodel.UtilsViewModel;

public class UtilsActivity extends AppCompatActivity {
    UtilsActivityBinding binding;
    UtilsViewModel model;
    /**
     * 小七手游
     */
    public String x7packageName = "com.smwl.x7market";
    /**
     * 微信
     */
    public String comTencentMm = "com.tencent.mm";
    /**
     * qq
     */
    public String comTencentMobileQq = "com.tencent.mobileqq";
    /**
     * 注意包名，之前包名就是少了.activity
     */
    public String jumpActivityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_utils);
        model = new ViewModelProvider(this).get(UtilsViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        binding.jumpUtilsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JumpUtils.getInstance().jumpToOtherAppActivity(MainActivity.this,packageName,jumpActivityName);
//                JumpUtils.getInstance().jumToOtherApp(MainActivity.this, comTencentMm);
//                JumpUtils.getInstance().jumpToX7APP(UtilsActivity.this);
                JumpUtils.getInstance().jumpToX7APPWithURL(UtilsActivity.this);
            }
        });
    }
}