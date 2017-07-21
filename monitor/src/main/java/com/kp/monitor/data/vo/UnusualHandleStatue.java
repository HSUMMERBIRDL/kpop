package com.kp.monitor.data.vo;

/**
 * des: 异常处理情况
 * Created by HL
 * on 2017-05-31.
 */

public class UnusualHandleStatue {


    public static final String DEAL_STATUS_GOING = "N"; // 未处理
    public static final String DEAL_STATUS_ING = "X"; // 处理中
    public static final String DEAL_STATUS_ED = "Y"; // 处理完成

    private boolean isSelected;
    private  String id;  // 处理情况id
    private  String handleDes;   // 处理情况说明


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandleDes() {
        return handleDes;
    }

    public void setHandleDes(String handleDes) {
        this.handleDes = handleDes;
    }
}
