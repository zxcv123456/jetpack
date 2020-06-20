package com.example.jetpack.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
     * 使用get请求的
     *
     * @param map      传入相关的参数
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
                callBack.onFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                byte b[] = body.bytes();
                String result = new String(b);
                callBack.onSuccess(call, result);
            }
        });
    }

    /**
     * 使用异步post请求的
     * 里面应该已经做了线程切换了，需要测试一下
     * @param map
     * @param callBack
     */
    public void execute(Map<String, String> map, final AbstractResultCallBack callBack) {
        String url = map.get("url");
        FormBody.Builder requestBodybuilder = new FormBody.Builder();
        OkHttpClient okHttpClient = new OkHttpClient();
        //通过该方法获得map里面的所有的key
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            if (StrUtils.IsKong(value)) {
                LogUtils.e(url+"接口的参数key所传的value为空");
                return;
            }
            if (!"url".equals(key)){
                assert value != null;
                requestBodybuilder.add(key,value);
            }
        }
        assert url != null;
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                //使用这个添加头信息
                .addHeader("user_agent","xfp");

        final Request request = requestBuilder.post(requestBodybuilder.build()).build();
        Call call = okHttpClient.newCall(request);
        //call调用callBack回调
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody body = response.body();
                    byte b[] = body.bytes();
                    String result = new String(b);
                    callBack.onSuccess(call,result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}
