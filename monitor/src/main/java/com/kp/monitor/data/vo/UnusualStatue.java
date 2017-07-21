package com.kp.monitor.data.vo;

/**
 * des: 异常状态
 * Created by HL
 * on 2017-05-31.
 */

public class UnusualStatue {

    // 异常状态
    public static final int EXCEPTION_TYPE_OVER = 1; // 越栏
    public static final int EXCEPTION_TYPE_OFFLINE = 2; // 掉线
    public static final int EXCEPTION_TYPE_OFF_WRIST = 3; // 脱腕
    public static final int EXCEPTION_TYPE_LOWER_POWER = 4; // 低电
    public static final int EXCEPTION_TYPE_LONG_LESS_MOVE = 5; // 长时间未移动
    public static final int EXCEPTION_TYPE_BASE_SEPARATION = 6; // 底座分离

    private boolean isSelected;
    private  int id;  // 异常状态id
    private  String des;   // 异常说明


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
