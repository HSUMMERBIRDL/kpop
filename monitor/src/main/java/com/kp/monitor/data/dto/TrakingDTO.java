package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.TrakingVO;

import java.util.List;

/**
 * Created by ${Stephen} on 2017-06-07.
 */

public class TrakingDTO {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Mapper<TrakingVO> {
        /**
         * monitorPersonId : f3afc4feb3d44e2b97e9bf734b80a7a5
         * monitorDeviceId :
         * typeId : 4
         * personType : R
         * monitorPersonName : CH
         * phone : 18570046456
         * depId : 652900000000
         * address : 阿克苏地区
         * idCard : 430181199001020458
         * sex : M
         * status : Y
         * monitorStatus : N
         * fenceType : 0
         * addrLng : null
         * addrLat : null
         * radius : null
         * fenceAreaId : null
         * birthday :
         * createTime : 1496645497000
         * createUserId : 1
         * monitorDeviceCode : null
         * monitorPersonTypeName : 社区矫正
         * depName : 阿克苏地区
         * fenceAreaName : null
         * coordinates : null
         */

        private String monitorPersonId;
        private String monitorDeviceId;
        private int typeId;
        private String personType;
        private String monitorPersonName;
        private String phone;
        private String depId;
        private String address;
        private String idCard;
        private String sex;
        private String status;
        private String monitorStatus;
        private String fenceType;
        private Object addrLng;
        private Object addrLat;
        private Object radius;
        private Object fenceAreaId;
        private String birthday;
        private long createTime;
        private String createUserId;
        private Object monitorDeviceCode;
        private String monitorPersonTypeName;
        private String depName;
        private Object fenceAreaName;
        private Object coordinates;

        public String getMonitorPersonId() {
            return monitorPersonId;
        }

        public void setMonitorPersonId(String monitorPersonId) {
            this.monitorPersonId = monitorPersonId;
        }

        public String getMonitorDeviceId() {
            return monitorDeviceId;
        }

        public void setMonitorDeviceId(String monitorDeviceId) {
            this.monitorDeviceId = monitorDeviceId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getPersonType() {
            return personType;
        }

        public void setPersonType(String personType) {
            this.personType = personType;
        }

        public String getMonitorPersonName() {
            return monitorPersonName;
        }

        public void setMonitorPersonName(String monitorPersonName) {
            this.monitorPersonName = monitorPersonName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDepId() {
            return depId;
        }

        public void setDepId(String depId) {
            this.depId = depId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
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

        public String getMonitorStatus() {
            return monitorStatus;
        }

        public void setMonitorStatus(String monitorStatus) {
            this.monitorStatus = monitorStatus;
        }

        public String getFenceType() {
            return fenceType;
        }

        public void setFenceType(String fenceType) {
            this.fenceType = fenceType;
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

        public Object getFenceAreaId() {
            return fenceAreaId;
        }

        public void setFenceAreaId(Object fenceAreaId) {
            this.fenceAreaId = fenceAreaId;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public Object getMonitorDeviceCode() {
            return monitorDeviceCode;
        }

        public void setMonitorDeviceCode(Object monitorDeviceCode) {
            this.monitorDeviceCode = monitorDeviceCode;
        }

        public String getMonitorPersonTypeName() {
            return monitorPersonTypeName;
        }

        public void setMonitorPersonTypeName(String monitorPersonTypeName) {
            this.monitorPersonTypeName = monitorPersonTypeName;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public Object getFenceAreaName() {
            return fenceAreaName;
        }

        public void setFenceAreaName(Object fenceAreaName) {
            this.fenceAreaName = fenceAreaName;
        }

        public Object getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Object coordinates) {
            this.coordinates = coordinates;
        }

        @Override
        public TrakingVO transform() {
            TrakingVO trakingVO = new TrakingVO();
            if (sex.equals("M")) {
                trakingVO.sex = "男";
            } else {
                trakingVO.sex = "女";
            }
            trakingVO.cardId = idCard;

            if (null == monitorDeviceId || monitorDeviceId.equals("")) {
                trakingVO.status = "未监控";
            }else {
                trakingVO.status = "监控中";
            }
            trakingVO.category = monitorPersonTypeName;
            trakingVO.monitorDeviceId = monitorDeviceId;
            trakingVO.name = monitorPersonName;
            trakingVO.monitorPersonId = monitorPersonId;
            return trakingVO;
        }
    }
}
