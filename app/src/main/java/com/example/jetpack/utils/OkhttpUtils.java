package com.example.jetpack.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ================================================
 *
 * @author xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-13-23:09
 * 描    述：用于网络请求的工具类
 * 修订历史：
 * ================================================
 */
public class OkhttpUtils {
    public static OkhttpUtils okhttpUtils;

    public static OkhttpUtils getInstance() {
        if (okhttpUtils == null) {
            synchronized (OkhttpUtils.class) {
                if (okhttpUtils == null) {
                    okhttpUtils = new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;
    }

    /**
     * @param map 传入相关的参数
     * @param callBack 在调用该方法的地方传入这个回调，然后在网络请求失败和成功中调用回调
     */
    public void getAsynHttp(Map<String, String> map, final AbstractResultCallBack callBack) {
        String url = map.get("url") == null ? "0" : map.get("url");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        //设置网络请求的回调
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(call,response);
            }
        });
    }
}
