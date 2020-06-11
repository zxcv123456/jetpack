package com.example.jetpack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityBinding;
import com.example.jetpack.utils.JumpUtils;
import com.example.jetpack.viewmodel.MyViewModel;
/**
 * ================================================
 * 作    者：xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-11-13:43
 * 描    述：主Activity
 * 修订历史：
 * ================================================
 */
public class MainActivity extends AppCompatActivity {
    public MyViewModel viewModel;
    public ActivityBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        //viewModel.number.setValue(1); 直接使用number，此时number还是null的，所以设置value导致出错
        viewModel.getNumber().setValue(0);
        binding.setModel(viewModel);
        //设置lifecycleOwner,将viewModel里面的liveData数据进行修改的时候才能实时更新
        binding.setLifecycleOwner(this);
        //跳转到工具界面
        binding.jumpToMarketTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.getInstance().generalJump(MainActivity.this,UtilsActivity.class);
            }
        });
    }





}