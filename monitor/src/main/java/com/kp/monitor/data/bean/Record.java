package com.kp.monitor.data.bean;

import java.io.File;

/**
 * des:录音
 * Created by HL
 * on 2017-06-27.
 */

public class Record {

    private File file; // 录音文件
    private String phone; // 录音电话号码
    private String startTime; // 录音开始时间 2017-06-12 12:12:34
    private String endTime; // 录音结束时间 2017-06-12 12:18:02

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {

        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "file=" + file +
                ", phone='" + phone + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
