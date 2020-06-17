package com.example.jetpack.utils;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

/**
 * ================================================
 * 作    者：xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-17-18:04
 * 描    述：视频获取宽高的测试类
 * 修订历史：
 * ================================================
 */
public class VideoAttributesUtils {
    private MediaPlayer mediaPlayer;
    private String url = "https://image8.x7sy.com/video_folder/comment/20200607/23564852828110130465.mp4";
    public static VideoAttributesUtils videoAttributesUtils;
    public static VideoAttributesUtils getInstance(){
        if (videoAttributesUtils == null){
            synchronized (VideoAttributesUtils.class){
                if (videoAttributesUtils == null){
                    videoAttributesUtils = new VideoAttributesUtils();
                }
            }
        }
        return videoAttributesUtils;
    }

    /**
     * 需要5-6秒才能得到宽高的数据
     * @param activity
     * @return
     */
    public String videoOrientation(Activity activity){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer = getVideoMediaPlayer(activity,url);
        int height = mediaPlayer == null ? 0:mediaPlayer.getVideoHeight();
        int width  = mediaPlayer == null ? 0:mediaPlayer.getVideoWidth();
        Log.d("xfp", "测试获取视频属性height:"+height+"：width"+width);
        if (height>width){
            return "port";
        }
        return "land";
    }

//    public VideoCommentBean setVideoWidthAndHeight(VideoCommentBean videoCommentBean) {
//        if (mediaPlayer == null) {
//            mediaPlayer = new MediaPlayer();
//        }
//        if (videoCommentBean == null) {
//            return null;
//        }
//        try {
//            mediaPlayer.setDataSource(videoCommentBean.video_path);
//            mediaPlayer.prepareAsync(new ONPREPAD);
//        } catch (Exception e) {
//            Log.d("xfp", "测试获取视频属性height:"+height+"：width"+width);
//        }
//
////        mediaPlayer = getVideoMediaPlayer(videoCommentBean.video_path);
//        videoCommentBean.videoHeight = mediaPlayer == null ? 0 : mediaPlayer.getVideoHeight();
//        videoCommentBean.videoWidth = mediaPlayer == null ? 0 : mediaPlayer.getVideoWidth();
//        return videoCommentBean;
//
//        try {
//            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
//            metadataRetriever.setDataSource(videoCommentBean.video_path);
//            String width = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); //宽
//            String height = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); //高
//
//        } catch (Exception e) {
//            Log.e("xfp", "setVideoWidthAndHeight: "+e );
//        }
//
//
//    }

    private MediaPlayer getVideoMediaPlayer(Activity activity,String url){
        try {
            return MediaPlayer.create(activity, Uri.parse(url));
        } catch (Exception e) {
            Log.e("xfp", "setVideoWidthAndHeight: "+e );
        }
        return null;
    }

    /**
     * ================================================
     * （1）属于在android api的那个包下：`android.media.MediaMetadataRetriever`
     * （2）类简单介绍：
     *  MediaMetadataRetriever类提供了用于从输入媒体文件检索帧和元数据的统一接口。
     * （3）这个是android提供的类，用来获取本地和网络media相关文件的信息
     * ================================================
     */

    /**
     * @param activity
     * 该方法获取的时间更加快比mediaPlayer，快的比较显著
     */
    public void getVideoWidthAndHeight(Activity activity){
        final MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        try {
            metadataRetriever.setDataSource(url,new HashMap<String, String>());
            new Thread(){
                @Override
                public void run() {
                    int width = Integer.parseInt(metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                    int height = Integer.parseInt(metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                    Log.d("xfp", "setVideoWidthAndHeight: "+width+"#####"+height );

                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("xfp", "setVideoWidthAndHeight: "+e );
        }finally {
            metadataRetriever.release();
        }
    }
}
