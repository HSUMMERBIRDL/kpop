package com.kp.monitor.service;

import android.media.MediaPlayer;

import com.hl.foundation.library.utils.LogUtils;

import java.io.File;

/**
 * des:播放服务
 * Created by HL
 * on 2017-06-20.
 */

public class MediaService {


    private static final String TAG = MediaService.class.getSimpleName();
    private static MediaService INSTANCE;

    private MediaPlayer mediaPlayer;

    public static MediaService getInstance() {
        if (null == INSTANCE) {
            synchronized (MediaService.class) {
                if (null == INSTANCE) {
                    INSTANCE = new MediaService();
                }
            }
        }
        return INSTANCE;
    }


    public  void  playVoice(File file){

        stopVoice();
        try {
            if((null != file) && (file.exists())){

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(file.getAbsolutePath());
                mediaPlayer.prepare(); //准备
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        if(null != mediaPlayer){
                            stopVoice();
                        }
                    }
                });

            }
        }catch (Exception e){
            LogUtils.loge(TAG,e);
        }
    }

    public void stopVoice(){

        try{
            if(mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
//                mediaPlayer = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.loge(TAG,"停止出错：" + e.getMessage());
        }
    }
}
