package com.example.jetpack.viewmodel;

import android.content.ComponentName;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ================================================
 * 作    者：xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-11-13:43
 * 描    述：MainActivity的ViewModel
 * 修订历史：
 * ================================================
 */
public class MyViewModel extends ViewModel {
    /**
     * 使用私有的，从而外部不会直接调取导致空指针异常
     */
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int n) {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        number.setValue(number.getValue() + n);
    }

    public void sub(int n) {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        number.setValue(number.getValue() - n);
    }

}
