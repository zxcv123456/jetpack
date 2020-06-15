package com.example.jetpack.activity.ui.home;

import android.app.Activity;

import com.example.jetpack.bean.GalleryBean;
import com.example.jetpack.utils.AbstractResultCallBack;
import com.example.jetpack.utils.OkhttpUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<GalleryBean> galleryBeanMutableLiveData;

    public MutableLiveData<GalleryBean> getGalleryBeanMutableLiveData() {
        if (galleryBeanMutableLiveData == null){
            galleryBeanMutableLiveData = new MutableLiveData<GalleryBean>();
        }
        return galleryBeanMutableLiveData;
    }
    public void getGalleryData(final Activity activity, Map<String, String> map){
        OkhttpUtils.getInstance().getAsynHttp(map, new AbstractResultCallBack() {
            @Override
            public void onSuccess(Call call, final String response) {
                //需要的是response.body才是返回的需要的数据
                //使用谷歌的Gson进行序列化，给livedata设置value
                final Gson jsonObject = new Gson();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //需要在主线程调用setvalue、方法
                        galleryBeanMutableLiveData.setValue(jsonObject.fromJson(response,GalleryBean.class));
                    }
                });

            }
        });
    }
}