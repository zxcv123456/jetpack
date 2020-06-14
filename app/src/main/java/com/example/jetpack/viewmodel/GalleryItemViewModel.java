package com.example.jetpack.viewmodel;

import android.app.Application;

import com.example.jetpack.bean.GalleryBean;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class GalleryItemViewModel extends AndroidViewModel {
    public MutableLiveData<GalleryBean.HitsBean> mutableLiveData;
    public GalleryItemViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<GalleryBean.HitsBean> getMutableLiveData() {
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<GalleryBean.HitsBean> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }
}
