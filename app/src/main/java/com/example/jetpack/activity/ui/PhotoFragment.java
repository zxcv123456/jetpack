package com.example.jetpack.activity.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jetpack.R;
import com.example.jetpack.databinding.FragmentPhotoBinding;

/**
 * ================================================
 *
 * @author xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-21-23:09
 * 描    述：用于展示图片的工具类
 * 修订历史：
 * ================================================
 */
public class PhotoFragment extends Fragment {
    private FragmentPhotoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_photo, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //通过getArguments可以获取传过来的值
        assert getArguments() != null;
        String photoUrl = getArguments().getString("photo_url");
        binding.shimmerLayout.setShimmerAngle(10);
        binding.shimmerLayout.setShimmerColor(Color.parseColor("#ffffff"));
        binding.shimmerLayout.startShimmerAnimation();
        Glide.with(getActivity())
                .load(photoUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        binding.shimmerLayout.stopShimmerAnimation();
                        return false;
                    }
                }).into(binding.photoview);

    }
}