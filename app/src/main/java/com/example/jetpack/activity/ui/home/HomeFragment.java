package com.example.jetpack.activity.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetpack.R;
import com.example.jetpack.adapter.GalleryAdapter;
import com.example.jetpack.bean.GalleryBean;
import com.example.jetpack.databinding.FragmentHomeBinding;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GalleryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        binding.rc.setLayoutManager(new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false));
        binding.rc.setItemAnimator(new DefaultItemAnimator());
//        Map<String,String> map = new HashMap<>();
//        map.put("url","https://pixabay.com/api/?key=17004424-6837e1cd50fe5f1d15dcaa0da&q=sexy+girl&image_type=photo");
//        homeViewModel.getGalleryData(getActivity(),map);
        homeViewModel.setData(getActivity());
        if (adapter == null){
            adapter = new GalleryAdapter();
        }
        binding.rc.setAdapter(adapter);
        homeViewModel.getGalleryBeanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<GalleryBean>() {
            @Override
            public void onChanged(GalleryBean galleryBean) {
                adapter.refresh(galleryBean.getHits());
            }
        });
    }
}