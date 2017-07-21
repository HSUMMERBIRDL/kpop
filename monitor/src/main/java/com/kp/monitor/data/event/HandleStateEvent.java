package com.kp.monitor.data.event;

import com.hl.foundation.library.rx.RxBus;

/**
 * des: 处理状态改变
 * Created by HL
 * on 2017-06-08.
 */

public class HandleStateEvent {

    public static final int WEI_CHU_LI = 0; // 未处理
    public static final int CHU_LI_ZHONG = 1; // 处理中
    public static final int CHU_LI_COMPLETE = 2; // 处理完成

    private String exceptionId="" ; // 异常状态ID
    private int currentStatue =-1; //当前状态

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public int getCurrentStatue() {
        return currentStatue;
    }

    public void setCurrentStatue(int currentStatue) {
        this.currentStatue = currentStatue;
    }

    public static void  post(int currentStatue, String exceptionId){

        HandleStateEvent event = new HandleStateEvent();
        event.setCurrentStatue(currentStatue);
        event.setExceptionId(exceptionId);
        RxBus.getInstance().post(Events.HANDLE_STATE_CHANGE,event);
    }

}
