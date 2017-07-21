package com.kp.monitor.data.vo;

/**
 * des: 脱腕
 * Created by HL
 * on 2017-06-01.
 */

public class OffWristVO {

    private String time;  // 时间 代表在这个时间是脱腕的

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
