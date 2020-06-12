package com.example.jetpack.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * ================================================
 * 作    者：xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-09-10:40
 * 描    述：跳转的工具类(主要有显式调用和隐式调用
 *          （包名，action，url：这个可以直接从浏览器用url调起来）,常见跳转方式都包含了
 * 修订历史：
 * ================================================
 */
public class JumpUtils {
    private  static JumpUtils jumpUtils;

    /**
     * @return 使用单例创建工具类
     */
    public static JumpUtils getInstance(){
        if (jumpUtils==null){
            synchronized (JumpUtils.class){
                if (jumpUtils == null){
                    jumpUtils = new JumpUtils();
                }
            }
        }
        return jumpUtils;
    }

    /**
     * 通用的跳转方法
     * @param jumpActivity      跳转的activity
     * @param targetActivity    跳转的目标activity
     */
    public void generalJump(Activity jumpActivity,Class<?>  targetActivity){
        Intent intent = new Intent(jumpActivity,targetActivity);
        jumpActivity.startActivity(intent);
    }


    /**
     *用来判断是否安装了该应用
     *  @param packageName 要调起来应用的包名
     * @return
     */
    private boolean isApkInstall(Activity activity,String packageName){
        try {
            //packageManager 里面保存了手机安装的包名信息，通过使用getApplicationInfo来判断手机中是否安装这个，
            // 如果没有安装，则会报找不到该包名的异常，通过try catch捕获从而不会闪退，如果异常了表示未安装
            ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(packageName,0);
            return true;
        } catch (Exception e) {
            //使用这个打印错误栈信息
            e.printStackTrace();
            Toast.makeText(activity,"手机未安装该应用",Toast.LENGTH_SHORT).show();
            Log.d("xfp", "isApkInstall: 应用未下载");
            return false;
        }
    }

    /**
     * 用来判断应用中是否包含该activity，在隐式调用的时候先用这个判断
     * @param activity
     * @param intent
     * @return
     * MATCH_DEFAULT_ONLY 这个参数表示仅仅匹配那些在intent-filter中
     * 声明了<category android:name="android.intent.category.DEFAULT" />这个category的Activity，
     * 因为只有声明了这个才能接收隐式Intent
     */
    public boolean isContainsActivity(Activity activity,Intent intent){
        if (activity.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!=null){
           return true;
        }
        Toast.makeText(activity,"应用中不存在该界面",Toast.LENGTH_SHORT).show();
        return false;
    }



    /**
     * 拉起打开其他app并且指定了相关的界面
     * 除了判断packageName,应该也要判断activity，如果activity写错了跳转过去也会闪退
     */
    public void jumpToOtherAppActivity(Activity activity, String packageName, String jumpActivityName){
        //直接这样调用会try-catch，找不到这个类
        if (!isApkInstall(activity,packageName)){
            return;
        }
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(packageName,jumpActivityName);
        intent.setComponent(componentName);
        if (isContainsActivity(activity,intent)){
            activity.startActivity(intent);
        }

    }

    /**
     * 跳转到其他app
     * @param activity
     * @param packageName 跳转到app的包名（只需要包名就可以了）
     */
    public void jumToOtherApp(Activity activity,String packageName){
        if (!isApkInstall(activity,packageName)){
            return;
        }
        //需要判断不为null才可以
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent!=null){
            //使用flags是用来拉起第三方应用使用的方式
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }

    }



    /**
     * 使用action的方式   也可以再添加componentName
     * @param activity
     * action要唯一，不然如果其他app也设置了，点击的时候会弹起很多app选择
     * 默认已经添加了addcategory，添加的是默认的DEFAULT
     */
    public void jumpToX7APP(Activity activity){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     *使用Url拉起的方式：（推荐都用url来拉起：不需要设置action和category，只需要setData就可以拉起来了），优点如下
     * 不必暴露第三方应用的包名和类名，
     * 通过包名或者ACTION拉起时假如B应用已经运行在后台，然后你再次在A应用中将其拉起，
     * 此时你会发现的确拉起了B应用，但是页面还是刚刚点击HOME键退出前的页面，所有页面的所有生命周期都没有触发，
     * 也因此并不会走你准备好的跳转到相应页面的逻辑，而URL却是正常的会走相应页面的生命周期。
     * @param activity
     */
    public void jumpToX7APPWithURL(Activity activity){
        Intent intent = new Intent();
        //要看data里面设置了mimeType没有，这个就是setDataAndType里面的type
        //intent.setDataAndType()
        /**
         * <data
         *     android:host="main"
         *     android:scheme="smwl" />
         *     在跳转的activity可以通过intent.getData(),获取Url，然后使用uri.getScheme等获取到相关跳转的值
         */
        //需要使用Uri.parse将string转成url
        intent.setData(Uri.parse("smwl://app"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //使用隐式调用都需要判断是否有该activiy，不然会报错
        if (isContainsActivity(activity,intent)){
            activity.startActivity(intent);
        }
    }




}
