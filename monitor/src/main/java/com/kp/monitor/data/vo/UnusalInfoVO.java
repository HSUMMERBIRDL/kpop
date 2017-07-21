package com.kp.monitor.data.vo;

/**
 * des:异常基本信息(异常详情的头部view)
 *  低电 人机分离 脱腕 掉线  这四种情况在共用
 * Created by HL
 * on 2017-05-10.
 */

public class UnusalInfoVO{

    public static final int DEAL_STATUS_GOING = 0; // 未处理
    public static final int DEAL_STATUS_ING = 1; // 处理中
    public static final int DEAL_STATUS_ED = 2; // 处理完成

    private int handleStatue; //  处理状态
    private String name; // 姓名
    private String cardId; //  身份证号码
    private String deviceNum; //  设备号码
    private String dealContent; //  设备号码


    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getHandleStatue() {
        return handleStatue;
    }

    public void setHandleStatue(int handleStatue) {
        this.handleStatue = handleStatue;
    }
}
