package com.kp.monitor.data.vo;

/**
 * des: 底座分离
 * Created by HL
 * on 2017-06-01.
 */

public class SeparateVO {

    private String time;  // 时间 代表在这个时间是人机分离的

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SeparateVO{" +
                "time='" + time + '\'' +
                '}';
    }
}
