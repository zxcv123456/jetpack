###集成滴滴的doraemonkit（哆啦A梦）提示超过64k解决方式
在model下依赖
implementation 'com.android.support:multidex:1.0.1'
在 model 中的defaultConfig 下添加
multiDexEnabled true
自定义MyApplication继承MultiDexApplication，重写attachBaseContext函数

@Override
protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
}

在 AndroidManifest.xml 中的  application 标签中添加
name为新建的Application