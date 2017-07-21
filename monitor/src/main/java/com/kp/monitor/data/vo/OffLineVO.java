package com.kp.monitor.data.vo;

/**
 * des: 掉线
 * Created by HL
 * on 2017-06-01.
 */

public class OffLineVO {

    private String time;  // 时间 代表在这个时间是掉线的

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OffLineVO{" +
                "time='" + time + '\'' +
                '}';
    }
}
