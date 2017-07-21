package com.kp.monitor.data.dto;

import com.amap.api.maps.model.LatLng;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.BaseLatLngVo;
import com.kp.monitor.data.vo.TrakingDetailVO;
import com.kp.monitor.service.helper.GeneralHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Stephen} on 2017-06-09.
 */

public class TrakingDetailDTO implements Mapper<BaseLatLngVo> {
    /**
     * person : {"monitorPersonId":"0651c3444f9d4282a333ea44c85f5e8e",
     * "monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","typeId":4,"personType":"R",
     * "monitorPersonName":"TEST005","phone":"13875817650","depId":"652900000000",
     * "address":"阿克苏地区","idCard":"456325198708051234","sex":"M","status":"Y",
     * "monitorStatus":"N","fenceType":"1","addrLng":112,"addrLat":28,"radius":0,
     * "fenceAreaId":"f1","birthday":"2012-05-25","createTime":1495698735000,"createUserId":"1",
     * "monitorDeviceCode":null,"monitorPersonTypeName":null,"depName":null,"fenceAreaName":null,
     * "coordinates":null,"operType":null}
     * fenceArea : {"fenceAreaId":"f1","fenceAreaName":"测试围栏2","deptId":"652927000000",
     * "status":"Y","createUserId":"3","createTime":1496386144000,"coordinates":"123123"}
     * locations : [{"monitorLocationId":"4",
     * "monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,"lat":28,"speed":30,
     * "direction":20,"locationTime":1496917874000,"createTime":1496903476000},
     * {"monitorLocationId":"3","monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,
     * "lat":28,"speed":30,"direction":20,"locationTime":1496910125000,
     * "createTime":1496385076000},{"monitorLocationId":"2",
     * "monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,"lat":28,"speed":20,
     * "direction":20,"locationTime":1496385172000,"createTime":1496384969000},
     * {"monitorLocationId":"1","monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,
     * "lat":28,"speed":10,"direction":20,"locationTime":1496373725000,"createTime":1496384923000}]
     */

    private PersonBean person;
    private FenceAreaBean fenceArea;
    private List<LocationsBean> locations;

    public PersonBean getPerson() {
        return person;
    }

    public void setPerson(PersonBean person) {
        this.person = person;
    }

    public FenceAreaBean getFenceArea() {
        return fenceArea;
    }

    public void setFenceArea(FenceAreaBean fenceArea) {
        this.fenceArea = fenceArea;
    }

    public List<LocationsBean> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsBean> locations) {
        this.locations = locations;
    }

    @Override
    public BaseLatLngVo transform() {
        BaseLatLngVo baseLatLngVo = new BaseLatLngVo();
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.clear();
        baseLatLngVo.lat = person.addrLat;
        baseLatLngVo.lng = person.addrLng;
        baseLatLngVo.type = Integer.parseInt(person.fenceType);
        baseLatLngVo.radius = person.radius;
        if (null != fenceArea) {
            String coordinates = fenceArea.coordinates;
            baseLatLngVo.coordinatesLatLngs = GeneralHelper.getInstance().strToLng(coordinates);
        }

        for (int i = 0; i < locations.size(); i++) {
            LocationsBean locationsBean = locations.get(i);
            LatLng latLng = new LatLng(locationsBean.getLat(), locationsBean.getLng());
            latLngs.add(latLng);
            baseLatLngVo.locations = latLngs;
        }
        return baseLatLngVo;
    }

    public static class PersonBean {
        /**
         * monitorPersonId : 0651c3444f9d4282a333ea44c85f5e8e
         * monitorDeviceId : 2b65e7d42fb74d749a294297fb230e9a
         * typeId : 4
         * personType : R
         * monitorPersonName : TEST005
         * phone : 13875817650
         * depId : 652900000000
         * address : 阿克苏地区
         * idCard : 456325198708051234
         * sex : M
         * status : Y
         * monitorStatus : N
         * fenceType : 1
         * addrLng : 112
         * addrLat : 28
         * radius : 0.0
         * fenceAreaId : f1
         * birthday : 2012-05-25
         * createTime : 1495698735000
         * createUserId : 1
         * monitorDeviceCode : null
         * monitorPersonTypeName : null
         * depName : null
         * fenceAreaName : null
         * coordinates : null
         * operType : null
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
        private double addrLng;
        private double addrLat;
        private double radius;
        private String fenceAreaId;
        private String birthday;
        private long createTime;
        private String createUserId;
        private Object monitorDeviceCode;
        private Object monitorPersonTypeName;
        private Object depName;
        private Object fenceAreaName;
        private String coordinates;
        private Object operType;

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

        public double getAddrLng() {
            return addrLng;
        }

        public void setAddrLng(int addrLng) {
            this.addrLng = addrLng;
        }

        public double getAddrLat() {
            return addrLat;
        }

        public void setAddrLat(int addrLat) {
            this.addrLat = addrLat;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public String getFenceAreaId() {
            return fenceAreaId;
        }

        public void setFenceAreaId(String fenceAreaId) {
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

        public Object getMonitorPersonTypeName() {
            return monitorPersonTypeName;
        }

        public void setMonitorPersonTypeName(Object monitorPersonTypeName) {
            this.monitorPersonTypeName = monitorPersonTypeName;
        }

        public Object getDepName() {
            return depName;
        }

        public void setDepName(Object depName) {
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

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }

        public Object getOperType() {
            return operType;
        }

        public void setOperType(Object operType) {
            this.operType = operType;
        }
    }

    public static class FenceAreaBean {
        /**
         * fenceAreaId : f1
         * fenceAreaName : 测试围栏2
         * deptId : 652927000000
         * status : Y
         * createUserId : 3
         * createTime : 1496386144000
         * coordinates : 123123
         */

        private String fenceAreaId;
        private String fenceAreaName;
        private String deptId;
        private String status;
        private String createUserId;
        private long createTime;
        private String coordinates;

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

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class LocationsBean {
        /**
         * monitorLocationId : 4
         * monitorDeviceId : 2b65e7d42fb74d749a294297fb230e9a
         * lng : 112
         * lat : 28
         * speed : 30.0
         * direction : 20.0
         * locationTime : 1496917874000
         * createTime : 1496903476000
         */

        private String monitorLocationId;
        private String monitorDeviceId;
        private double lng;
        private double lat;
        private double speed;
        private double direction;
        private long locationTime;
        private long createTime;

        public String getMonitorLocationId() {
            return monitorLocationId;
        }

        public void setMonitorLocationId(String monitorLocationId) {
            this.monitorLocationId = monitorLocationId;
        }

        public String getMonitorDeviceId() {
            return monitorDeviceId;
        }

        public void setMonitorDeviceId(String monitorDeviceId) {
            this.monitorDeviceId = monitorDeviceId;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(int lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDirection() {
            return direction;
        }

        public void setDirection(double direction) {
            this.direction = direction;
        }

        public long getLocationTime() {
            return locationTime;
        }

        public void setLocationTime(long locationTime) {
            this.locationTime = locationTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }

}
