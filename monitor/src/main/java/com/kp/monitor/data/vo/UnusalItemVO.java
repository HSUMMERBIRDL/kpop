package com.kp.monitor.data.vo;

import java.io.Serializable;

/**
 * des:通讯录列表
 * Created by HL
 * on 2017-05-10.
 */

public class UnusalItemVO implements Serializable{


    // 异常状态
    public static final int EXCEPTION_TYPE_OVER = 1; // 越栏
    public static final int EXCEPTION_TYPE_OFFLINE = 2; // 掉线
    public static final int EXCEPTION_TYPE_OFF_WRIST = 3; // 脱腕
    public static final int EXCEPTION_TYPE_LOWER_POWER = 4; // 低电
    public static final int EXCEPTION_TYPE_LONG_LESS_MOVE = 5; // 长时间未移动
    public static final int EXCEPTION_TYPE_BASE_SEPARATION = 6; // 底座分离 / 人机分离

    public static final int DEAL_STATUS_GOING = 1; // 未处理
    public static final int DEAL_STATUS_ING = 2; // 处理中
    public static final int DEAL_STATUS_ED = 3; // 处理完成


    private String personId; // 监控人员Id
    private String exceptionId;
    private int exceptionType;  // 异常类型（1:越栏；2掉线 3脱腕 4低电 5长时间未移动 6底座分离）
    private int dealStatus; // 处理状态 （N未处理 X处理中 Y处理完成）

    private String name; // 姓名
    private String exceptionTypeDes;  // 异常类型描述（1:越栏；2掉线 3脱腕 4低电 5长时间未移动 6底座分离）
    private String exceptionCount; // 异常次数
    private String dealStatusDes; // 处理状态（N未处理 X处理中 Y处理完成）
    private String cardId; //  身份证号码
    private String deviceId; //  设备号码 (并不是设备主见  而是设备的一个编号)


    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(String exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public int getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(int exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionTypeDes() {
        return exceptionTypeDes;
    }

    public void setExceptionTypeDes(String exceptionTypeDes) {
        this.exceptionTypeDes = exceptionTypeDes;
    }

    public String getDealStatusDes() {
        return dealStatusDes;
    }

    public void setDealStatusDes(String dealStatusDes) {
        this.dealStatusDes = dealStatusDes;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }

    @Override
    public String toString() {
        return "UnusalItemVO{" +
                "name='" + name + '\'' +
                ", personId='" + personId + '\'' +
                ", exceptionType=" + exceptionType +
                ", exceptionTypeDes='" + exceptionTypeDes + '\'' +
                ", exceptionCount='" + exceptionCount + '\'' +
                ", dealStatusDes='" + dealStatusDes + '\'' +
                ", dealStatus=" + dealStatus +
                '}';
    }
}
