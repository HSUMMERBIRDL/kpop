package com.kp.monitor.service.task;

/**
 * des:录音线程
 * Created by HL
 * on 2017-06-19.
 */

public class RecordThread implements Runnable{

    private boolean isStop = false; // 是否停止

    public RecordThread(){

    }

    @Override
    public void run() {

        while (!isStop){

            //  开始录音
        }

    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
