package com.example.jetpack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jetpack.R;

/**
 * @author xfp
 * EventBus 研究
 */
public class EventBusActivity extends AppCompatActivity {

    /**
     * 使用下面这个javadoc注解可以链接代码，让注释看的更加清楚易懂
     * {@link #testLink(int)}   使用这个链接到方法
     * {@link com.example.jetpack.activity.UtilsActivity} 使用这个链接到具体的类
     * {@link UtilsActivity #name} 不需要写绝对路径也可以
     * {@link #testMode}  链接到具体的变量
     */
    public int testMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
    }

    public void testLink(int mode){

    }
}