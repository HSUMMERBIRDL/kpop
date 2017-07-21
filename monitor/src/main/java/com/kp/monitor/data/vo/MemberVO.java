package com.kp.monitor.data.vo;

/**
 * des: 人员详情
 * Created by HL
 * on 2017-05-10.
 *
 * 1: 年龄可能没有
 * 2：（1） 圆形围栏  有半径  无区域围栏名称
 *    （2） 封闭围栏  无半径  有区域围栏名称
 * 3：设备编号也可能没有  没有绑定设备
 */

public class MemberVO {

    public static final int MEN = 0;
    public static final int WOMEN = 1;

    private String name ="";
    private String age = "";
    private String memberType = ""; // 监控类别 极端宗教
    private String address = ""; // 家庭地址
    private String cardId = ""; //  身份证

    private String belongArea = ""; //  归属地区
    private String circleRaius = ""; //  圆半径大小  （单位 米）
    private String areaFence = ""; //  区域围栏
    private String deviceNum = ""; //  设备编号
    private String lng = ""; //  经度
    private String lat = ""; //  纬度
    private String fenceType = ""; //  围栏类型

    private int sex;  // 0 男 1 女
    private int fenceTypeType; // 围栏类型  0 圆形围栏    1 封闭区域


    private String remark = ""; // 备注  html

    public int getFenceTypeType() {
        return fenceTypeType;
    }

    public void setFenceTypeType(int fenceTypeType) {
        this.fenceTypeType = fenceTypeType;
    }

    public String getFenceType() {
        return fenceType;
    }

    public void setFenceType(String fenceType) {
        this.fenceType = fenceType;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public String getCircleRaius() {
        return circleRaius;
    }

    public void setCircleRaius(String circleRaius) {
        this.circleRaius = circleRaius;
    }

    public String getAreaFence() {
        return areaFence;
    }

    public void setAreaFence(String areaFence) {
        this.areaFence = areaFence;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "name='" + name + '\'' +
                '}';
    }
}
