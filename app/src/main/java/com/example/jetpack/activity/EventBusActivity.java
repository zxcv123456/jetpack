package com.example.jetpack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jetpack.R;
import com.example.jetpack.bean.GalleryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
     * {@link # https://www.cnblogs.com/dolphin0520/p/3920373.html } 链接到具体的网址
     *
     */
    public int testMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
    }

    public void testLink(int mode) {
        /**
         * 泛型：泛型允许程序员在强类型程序设计语言中编写代码时使用一些以后才指定的类型，在实例化时作为参数指明这些类型。
         * ？表示不确定的java类型
         * T 表示java类型
         * K V 表示java键值对中的key和value
         * E 代表Element
         * Object是所有的类的根类，是具体的一个类，使用的时候可能是需要类型强制转换的，但是T,?这些的话，
         * 在实际用之前类型就已经确定好了，不需要强制转换
         */
        Map<Class<?>, List<Class<?>>> eventType;
        /**
         * 在并发环境下，我们在选择对象容器的时候需要考量是否需要选择线程安全的容器，如果不需要，
         * 则优先选择ArrayList等没有线程安全保障的容器，如果需要线程安全保障，
         * 那么必须选择类似CopyOnWriteArrayList的线程安全的容器集合，否则会造成不可预料的错误。
         */
        final Map<Class<?>, CopyOnWriteArrayList<GalleryBean>> subscriptionsByEventType;

        /**
         * ThreadLocal是一个关于创建线程局部变量的类。
         * 通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
         * 而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改。
         * 为ThreadLocal设置默认的get初始值，需要重写initialValue方法，下面是一段代码，我们将默认值修改成了
         * 发送线程的状态
         */
        final ThreadLocal<PostingThreadState> currentPostingThreadState = new ThreadLocal<PostingThreadState>() {
            @Override
            protected PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };

    }

    /** For ThreadLocal, much faster to set (and get multiple values). */
    final static class PostingThreadState {
        final List<Object> eventQueue = new ArrayList<>();
        boolean isPosting;
        boolean isMainThread;
        Object event;
        boolean canceled;
    }
}