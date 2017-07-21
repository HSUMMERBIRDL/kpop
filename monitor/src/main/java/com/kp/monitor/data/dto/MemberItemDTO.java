package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MemberItemVO;

/**
 * des: 人员列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class MemberItemDTO implements Mapper<MemberItemVO> {


    /**
     * idCard : 435435
     * monitorDeviceCode : 435
     * monitorPersonId : 435
     * monitorPersonName : 张三
     * monitorPersonTypeName : 极端宗教
     * monitorStatuName : 监控中
     * monitorStatus : Y
     * sex : W
     */

    private String idCard;
    private String monitorDeviceCode;
    private String monitorPersonId;
    private String monitorPersonName;
    private String monitorPersonTypeName;
    private String monitorStatuName;
    private String monitorStatus;
    private String sex;

    @Override
    public MemberItemVO transform() {

        MemberItemVO vo = new MemberItemVO();
        vo.setMemberId(monitorPersonId);

        if(StringUtils.equals(monitorStatus,"Y")) {
            vo.setInMoniter(true);
        }else if(StringUtils.equals(monitorStatus,"N")){
            vo.setInMoniter(false);
        }

        if(StringUtils.equals(sex,"W")){
            vo.setSex("女");
        }else if(StringUtils.equals(sex,"M")){
            vo.setSex("男");
        }

        vo.setName(monitorPersonName);
        vo.setCardId(idCard);
        vo.setCategory(monitorPersonTypeName);
        vo.setStatue(monitorStatuName);

        return vo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMonitorDeviceCode() {
        return monitorDeviceCode;
    }

    public void setMonitorDeviceCode(String monitorDeviceCode) {
        this.monitorDeviceCode = monitorDeviceCode;
    }

    public String getMonitorPersonId() {
        return monitorPersonId;
    }

    public void setMonitorPersonId(String monitorPersonId) {
        this.monitorPersonId = monitorPersonId;
    }

    public String getMonitorPersonName() {
        return monitorPersonName;
    }

    public void setMonitorPersonName(String monitorPersonName) {
        this.monitorPersonName = monitorPersonName;
    }

    public String getMonitorPersonTypeName() {
        return monitorPersonTypeName;
    }

    public void setMonitorPersonTypeName(String monitorPersonTypeName) {
        this.monitorPersonTypeName = monitorPersonTypeName;
    }

    public String getMonitorStatuName() {
        return monitorStatuName;
    }

    public void setMonitorStatuName(String monitorStatuName) {
        this.monitorStatuName = monitorStatuName;
    }

    public String getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(String monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
