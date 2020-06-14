package com.example.jetpack.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class ImageAdapter {

    @BindingAdapter({"imageUrl"})
    public static void setImageResource(ImageView view,String url){
        Glide.with(view).load(url).into(view);
    }
}
