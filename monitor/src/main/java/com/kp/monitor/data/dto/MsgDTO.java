package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MsgVo;

/**
 * Created by ${Stephen} on 2017-06-07.
 */

public class MsgDTO implements Mapper<MsgVo> {

    /**
     * exceptionId : null
     * personId : null
     * depId : null
     * deviceId : null
     * exceptionType : null
     * dealUserId : null
     * exceptionCnt : null
     * firstFoundTime : null
     * lastFoudTime : null
     * dealTime : 2017-06-07 15:20:46
     * completeTime : null
     * dealContent : rree
     * dealStatus : Y
     * status : null
     * monitorPersonName : null
     * idCard : null
     * sex : null
     * typeId : null
     * birthday : null
     * monitorDeviceCode : null
     * monitorPersonTypeName : null
     * fenceType : null
     * fenceAreaId : null
     * addrLng : null
     * addrLat : null
     * radius : null
     */

    private Object exceptionId;
    private Object personId;
    private Object depId;
    private Object deviceId;
    private Object exceptionType;
    private Object dealUserId;
    private Object exceptionCnt;
    private Object firstFoundTime;
    private Object lastFoudTime;
    private String dealTime;
    private Object completeTime;
    private String dealContent;
    private String dealStatus;
    private Object status;
    private Object monitorPersonName;
    private Object idCard;
    private Object sex;
    private Object typeId;
    private Object birthday;
    private Object monitorDeviceCode;
    private Object monitorPersonTypeName;
    private Object fenceType;
    private Object fenceAreaId;
    private Object addrLng;
    private Object addrLat;
    private Object radius;

    @Override
    public MsgVo transform() {
        MsgVo msgVo = new MsgVo();

        msgVo.msg = dealStatus;
        return msgVo;
    }

    public Object getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Object exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Object getPersonId() {
        return personId;
    }

    public void setPersonId(Object personId) {
        this.personId = personId;
    }

    public Object getDepId() {
        return depId;
    }

    public void setDepId(Object depId) {
        this.depId = depId;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Object exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Object getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Object dealUserId) {
        this.dealUserId = dealUserId;
    }

    public Object getExceptionCnt() {
        return exceptionCnt;
    }

    public void setExceptionCnt(Object exceptionCnt) {
        this.exceptionCnt = exceptionCnt;
    }

    public Object getFirstFoundTime() {
        return firstFoundTime;
    }

    public void setFirstFoundTime(Object firstFoundTime) {
        this.firstFoundTime = firstFoundTime;
    }

    public Object getLastFoudTime() {
        return lastFoudTime;
    }

    public void setLastFoudTime(Object lastFoudTime) {
        this.lastFoudTime = lastFoudTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public Object getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Object completeTime) {
        this.completeTime = completeTime;
    }

    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getMonitorPersonName() {
        return monitorPersonName;
    }

    public void setMonitorPersonName(Object monitorPersonName) {
        this.monitorPersonName = monitorPersonName;
    }

    public Object getIdCard() {
        return idCard;
    }

    public void setIdCard(Object idCard) {
        this.idCard = idCard;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public Object getTypeId() {
        return typeId;
    }

    public void setTypeId(Object typeId) {
        this.typeId = typeId;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getMonitorDeviceCode() {
        return monitorDeviceCode;
    }

    public void setMonitorDeviceCode(Object monitorDeviceCode) {
        this.monitorDeviceCode = monitorDeviceCode;
    }

    public Object getMonitorPersonTypeName() {
        return monitorPersonTypeName;
    }

    public void setMonitorPersonTypeName(Object monitorPersonTypeName) {
        this.monitorPersonTypeName = monitorPersonTypeName;
    }

    public Object getFenceType() {
        return fenceType;
    }

    public void setFenceType(Object fenceType) {
        this.fenceType = fenceType;
    }

    public Object getFenceAreaId() {
        return fenceAreaId;
    }

    public void setFenceAreaId(Object fenceAreaId) {
        this.fenceAreaId = fenceAreaId;
    }

    public Object getAddrLng() {
        return addrLng;
    }

    public void setAddrLng(Object addrLng) {
        this.addrLng = addrLng;
    }

    public Object getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(Object addrLat) {
        this.addrLat = addrLat;
    }

    public Object getRadius() {
        return radius;
    }

    public void setRadius(Object radius) {
        this.radius = radius;
    }
}
