package com.example.jetpack.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.jetpack.R;
import com.example.jetpack.bean.GalleryBean;
import com.example.jetpack.databinding.GalleryItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryBean.HitsBean> beans;
    public GalleryAdapter() {
        super();
        if (beans == null){
            beans = new ArrayList<>();
        }
    }
    public void refresh(List<GalleryBean.HitsBean> hits){
        beans = hits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //使用layoutInflater来生成inflater
        //这里设置了parent，这个布局里面的根布局相关配置才会生效
        GalleryItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.gallery_item,parent,false);
        return new GalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, int position) {
        if (beans.size()>position){
            holder.getBinding().shimmerLayout.setShimmerColor(Color.parseColor("#ffffff"));
            holder.getBinding().shimmerLayout.setShimmerAngle(0);
            holder.getBinding().shimmerLayout.startShimmerAnimation();
            holder.getBinding().setModel(beans.get(position));
            Glide.with(holder.itemView)
                    .load(beans.get(position).getLargeImageURL())
                    //用来设置圆角，设置的要变，设置之后图片变得不一样了
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    //需要设置placeholder将位置占好，不然显示是一条白的，都是根据placeholder的比例来的了
                    .placeholder(R.drawable.ic_gallery_item_photo_24)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            //图片加载完成后停止
                            holder.getBinding().shimmerLayout.stopShimmerAnimation();
                            return false;
                        }
                    })
                    .into(holder.getBinding().imageView);
        }
    }

    @Override
    public int getItemCount() {
        return beans.size();
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
