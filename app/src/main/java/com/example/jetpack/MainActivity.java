package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.jetpack.databinding.ActivityBinding;
import com.example.jetpack.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {
    public MyViewModel viewModel;
    public ActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.number.setValue(1);
        binding.setModel(viewModel);
    }
}