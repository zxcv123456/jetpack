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
import com.example.jetpack.utils.AbstractResultCallBack;
import com.example.jetpack.utils.OkhttpUtils;

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
import okhttp3.Call;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GalleryAdapter adapter;
    private String tern = "girl";
    private int page = 1;

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
        binding.setLifecycleOwner(this);
        binding.rc.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        binding.rc.setItemAnimator(new DefaultItemAnimator());
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String[] type = new String[]{"girl", "car", "seas", "flower",};
                int i = (int) (Math.random() * type.length);
                String requestType = type[i];
                page = requestType.equals(tern) ? page++ : 1;
                tern = requestType;
                map.put("url", "https://pixabay.com/api/?key=17004424-6837e1cd50fe5f1d15dcaa0da&q=" + tern + "&page=" + page + "" + "&image_type=photo");
                homeViewModel.getGalleryData(getActivity(), map);

            }
        });
        if (adapter == null) {
            adapter = new GalleryAdapter();
        }
        binding.rc.setAdapter(adapter);
        map.put("url", "https://pixabay.com/api/?key=17004424-6837e1cd50fe5f1d15dcaa0da&q=" + tern + "&page=" + page + "" + "&image_type=photo");
        homeViewModel.getGalleryData(getActivity(), map);

        homeViewModel.getGalleryBeanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<GalleryBean>() {
            @Override
            public void onChanged(GalleryBean galleryBean) {
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    binding.swipeRefreshLayout.setRefreshing(false);
                }
                adapter.refresh(galleryBean.getHits());
            }
        });
    }

    /**
     * 不能用post请求，只能用get请求
     *
     * @param tern
     * @param page
     * @param homeViewModel
     */
    public void getData(String tern, int page, HomeViewModel homeViewModel) {
        Map<String, String> map = new HashMap<>();
        map.put("url", "https://pixabay.com/api/");
        map.put("?key", "17004424-6837e1cd50fe5f1d15dcaa0da");
        map.put("image_type", "photo");
        map.put("q", tern);
        map.put("page", page + "");
        homeViewModel.setData(getActivity(), map);
    }
}