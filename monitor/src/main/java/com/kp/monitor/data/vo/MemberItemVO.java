package com.kp.monitor.data.vo;

/**
 * des: 人员列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class MemberItemVO {

    private String memberId = "";

    private String statue = ""; //  监控中/未纳入监控
    private String name = "";
    private String sex = "";
    private String cardId = ""; // 身份证号码
    private String deviceNum = "";
    private String category = ""; // 类别 ， 极端宗教 ......

    private boolean isInMoniter; // 是否监控中

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isInMoniter() {
        return isInMoniter;
    }

    public void setInMoniter(boolean inMoniter) {
        isInMoniter = inMoniter;
    }


}
