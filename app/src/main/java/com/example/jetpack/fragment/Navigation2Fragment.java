package com.example.jetpack.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.databinding.Navigation2FragmentBinding;

public class Navigation2Fragment extends Fragment {

    private Navigation2ViewModel mViewModel;
    Navigation2FragmentBinding binding;

    public static Navigation2Fragment newInstance() {
        return new Navigation2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //fragment使用DataBinding的方式
        binding = DataBindingUtil.inflate(inflater,R.layout.navigation2_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Navigation2ViewModel.class);
        binding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建navController，将点击的view传给navigation，然后使用navController.navigate实现跳转
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation2Fragment_to_navigationFragment);
            }
        });
    }

}