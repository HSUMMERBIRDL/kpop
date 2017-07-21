package com.kp.monitor.data.vo;

/**
 * des: 低电
 * Created by HL
 * on 2017-06-01.
 */

public class LowPowerVO {

    private String time;  // 时间 代表在这个时间是掉线的
    private String power; // 电量 用28%表示


    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LowPowerVO{" +
                "time='" + time + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}
