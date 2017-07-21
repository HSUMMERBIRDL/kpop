package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MemberVO;

import java.util.Date;

/**
 * des: 人员详情(基本信息)
 * Created by HL
 * on 2017-05-10.
 */

public class MemberDTO implements Mapper<MemberVO> {
    /**
     * addrLat : 28.172701
     * addrLng : 112.995597
     * address : 阿克苏市
     * birthday : 2017-06-15
     * coordinates : [[[81.846829,41.798097],[81.850226,41.798431],[81.854088,41.797631],[81.854392,41.795205],[81.852121,41.794165],[81.84808,41.793818],[81.845541,41.796031],[81.84606,41.797551],[81.846793,41.797551]]]
     * createTime : 1497836470000
     * createUserId : 1
     * depId : 652901000000
     * depName : 阿克苏市
     * fenceAreaId : 71871c2975a7467caf11d499aa806c71
     * fenceAreaName : 拜城镇电子围栏
     * fenceType : 0
     * idCard : 4603198705154622
     * monitorDeviceCode : CODE_008
     * monitorDeviceId : bea5200514e2425185d702841cdf17aa
     * monitorPersonId : f1cd5a3615824130b20a9667d77d97ab
     * monitorPersonName : TEST_004
     * monitorPersonTypeName : 涉恐人员
     * monitorStatus : N
     * personType : R
     * radius : 20000
     * sex : W
     * status : Y
     * typeId : 5
     */

    private String addrLat = "";
    private String addrLng = "";
    private String address;
    private String birthday;
    private String coordinates;
    private long createTime;
    private String createUserId;
    private String depId;
    private String depName;
    private String fenceAreaId;
    private String fenceAreaName;
    private String fenceType;
    private String idCard;
    private String monitorDeviceCode;
    private String monitorDeviceId;
    private String monitorPersonId;
    private String monitorPersonName;
    private String monitorPersonTypeName;
    private String monitorStatus;
    private String personType;
    private String radius;
    private String sex;
    private String status;
    private int typeId;


    @Override
    public MemberVO transform() {
        MemberVO vo = new MemberVO();
        vo.setCardId(idCard);
        vo.setAddress(address);
        vo.setName(monitorPersonName);
        vo.setMemberType(monitorPersonTypeName);
        vo.setRemark("无");

       if(!StringUtils.isEmpty(birthday)){
           Date dateBirthday = TimeUtil.getDateByFormat(birthday, TimeUtil.dateFormatYMD);
           int age = TimeUtil.getAgeByBirthday(dateBirthday);
           vo.setAge(age + "");
       }

        vo.setLng(addrLng);
        vo.setLat(addrLat);
        vo.setBelongArea(depName);

        if(StringUtils.equals("0",fenceType)){
            vo.setFenceType("圆形围栏");
            vo.setFenceTypeType(0);
        }else if(StringUtils.equals("1",fenceType)){
            vo.setFenceType("封闭区域");
            vo.setFenceTypeType(1);
        }

        vo.setCircleRaius(radius);
        vo.setAreaFence(fenceAreaName);
        vo.setDeviceNum(monitorDeviceCode);

        return vo;
    }


    public String getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(String addrLat) {
        this.addrLat = addrLat;
    }

    public String getAddrLng() {
        return addrLng;
    }

    public void setAddrLng(String addrLng) {
        this.addrLng = addrLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getFenceAreaId() {
        return fenceAreaId;
    }

    public void setFenceAreaId(String fenceAreaId) {
        this.fenceAreaId = fenceAreaId;
    }

    public String getFenceAreaName() {
        return fenceAreaName;
    }

    public void setFenceAreaName(String fenceAreaName) {
        this.fenceAreaName = fenceAreaName;
    }

    public String getFenceType() {
        return fenceType;
    }

    public void setFenceType(String fenceType) {
        this.fenceType = fenceType;
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

    public String getMonitorDeviceId() {
        return monitorDeviceId;
    }

    public void setMonitorDeviceId(String monitorDeviceId) {
        this.monitorDeviceId = monitorDeviceId;
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

    public String getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(String monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }


    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


}
