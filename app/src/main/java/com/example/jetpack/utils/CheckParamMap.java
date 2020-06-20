package com.example.jetpack.utils;

import java.util.HashMap;

/**
 *  CheckParamMap<String ,String> 集合的泛型固定为：<String ,String>
 */

public class CheckParamMap extends HashMap<String, String> {
    
    public String url;
    public void setUrl(String url){
        this.url=url;
    }
    /**
     * 返回的是value值，此方法key所对应的value值，不能为空
     * 如果value值可以为空，直接调用map.put<String,String>
     * @param key
     * @param value
     * @return
     */

    public String putHaveValue(String key, String value){
        if (isExitEmptyParameter(value)) {
            LogUtils.e("有参数为空:"+url);
            LogUtils.e("key:"+key+" value:"+value);
            return "";
        }
        return put(key,value);
    };

    public static boolean isExitEmptyParameter(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (str == null || str.equals ("") || str.equals (" ")
                    || str.equals ("NULL") || str.equals ("null")) {
                return true;
            }
        }
        return false;
    }
    
}
