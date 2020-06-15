package com.example.jetpack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.bean.GalleryBean;
import com.example.jetpack.databinding.GalleryItemBinding;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryBean.HitsBean> beans;
    public GalleryAdapter(List<GalleryBean.HitsBean> hits) {
        super();
        beans = hits;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //使用layoutInflater来生成inflater
        GalleryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.gallery_item,null,false);
        return new GalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        if (beans.size()>position){
            holder.getBinding().setModel(beans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
        private GalleryItemBinding binding;
        public GalleryViewHolder(GalleryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public GalleryItemBinding getBinding() {
            return binding;
        }
    }
}
