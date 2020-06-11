package com.example.jetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModelApplication extends AndroidViewModel {
    private MutableLiveData<Integer> number;
    private Application application;
    public ViewModelApplication(@NonNull Application application) {
        super(application);
        application = getApplication();
    }

}
