package com.kp.monitor.data.event;

/**
 * des: 事件 只要记录由EventBus发送出去的事件
 * Created by HL
 * on 2017-05-03.
 */

public class Events {

    public static final String HANDLE_STATE_CHANGE = "handle_state_change";  // 异常情况的处理状态的改变
    public static final String UPLOAD_FILE_SIZE_CHANGE = "upload_file_size_change";  // 上传文件的个数变化（这里是指确定的需要上传的文件个数）

}
