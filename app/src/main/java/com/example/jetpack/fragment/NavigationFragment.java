package com.example.jetpack.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.databinding.NavigationFragmentBinding;

public class NavigationFragment extends Fragment {

    private NavigationViewModel mViewModel;
    private NavigationFragmentBinding binding;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.navigation_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
        //直接使用navigation.createNavigationOnclickListener可以实现点击跳转到相关的界面
        binding.textView4.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigationFragment_to_navigation2Fragment));
    }

}