package com.example.jetpack.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryBean.HitsBean> beans;

    public GalleryAdapter() {
        super();
        if (beans == null) {
            beans = new ArrayList<>();
        }
    }

    public void refresh(List<GalleryBean.HitsBean> hits) {
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
                R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, int position) {
        if (beans.size() > position) {
            holder.getBinding().shimmerLayout.setShimmerColor(Color.parseColor("#ffffff"));
            holder.getBinding().shimmerLayout.setShimmerAngle(0);
            holder.getBinding().shimmerLayout.startShimmerAnimation();
            holder.getBinding().setModel(beans.get(position));
            //可以通过bundle传递数据过去,通过在action后面添加参数
            Bundle bundle = new Bundle();
            bundle.putString("photo_url", beans.get(position).getLargeImageURL());
            holder.getBinding().getRoot().setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_photoFragment, bundle));
            Glide.with(holder.itemView)
                    .load(beans.get(position).getLargeImageURL())
                    /*
                     *用来设置圆角，直接设置圆角没有centerCrop的效果，xml里面设置了也没用
                     *需用使用下面的MultiTransformation这种方式
                     */
                    .apply(RequestOptions.bitmapTransform(new MultiTransformation<Bitmap>(
                            new CenterCrop()
                            , new RoundedCorners(10))))
                    //需要设置placeholder将位置占好，不然显示是一条白的，都是根据placeholder的比例来的了
                    //或者可以在xml里面的image的宽高设置具体的大小
                    .placeholder(R.drawable.ic_gallery_item_photo_24)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e,
                                                    Object model, Target<Drawable> target,
                                                    boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource,
                                                       Object model, Target<Drawable> target,
                                                       DataSource dataSource,
                                                       boolean isFirstResource) {
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

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
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
