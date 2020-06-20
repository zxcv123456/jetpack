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
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GalleryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Map<String, String> map = new HashMap<>();
        final HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        binding.rc.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        binding.rc.setItemAnimator(new DefaultItemAnimator());
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String [] type = new String[]{"girl","woman","car","seas","flower","sexy"};
                int i = (int) (Math.random()*type.length);
                String requestType = type[i];
                map.put("url", "https://pixabay.com/api/?key=17004424-6837e1cd50fe5f1d15dcaa0da&q="+requestType+"&image_type=photo");
                homeViewModel.getGalleryData(getActivity(),map);
            }
        });
        if (adapter == null) {
            adapter = new GalleryAdapter();
        }
        binding.rc.setAdapter(adapter);
        map.put("url", "https://pixabay.com/api/?key=17004424-6837e1cd50fe5f1d15dcaa0da&q=girl&image_type=photo");
        homeViewModel.getGalleryData(getActivity(), map);
//        homeViewModel.setData(getActivity());

        homeViewModel.getGalleryBeanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<GalleryBean>() {
            @Override
            public void onChanged(GalleryBean galleryBean) {
                if (binding.swipeRefreshLayout.isRefreshing()){
                    binding.swipeRefreshLayout.setRefreshing(false);
                }
                adapter.refresh(galleryBean.getHits());
            }
        });
    }
}