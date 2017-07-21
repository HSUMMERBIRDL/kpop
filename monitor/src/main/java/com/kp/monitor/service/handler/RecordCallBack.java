package com.kp.monitor.service.handler;

import java.io.File;

/**
 * des:录音回调
 * Created by HL
 * on 2017-06-19.
 */

public interface RecordCallBack {

    void onRecordStart(File file);      // 录音开始
    void onRecordComplete(File file,String startTime);   // 录音完成
}
