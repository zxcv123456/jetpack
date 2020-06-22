package com.example.jetpack.app;

import android.app.Application;
import android.content.Context;

import com.didichuxing.doraemonkit.DoraemonKit;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DoraemonKit.install(MyApplication.this);
    }
}
