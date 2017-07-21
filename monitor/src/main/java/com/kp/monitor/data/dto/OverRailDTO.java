package com.kp.monitor.data.dto;

import com.amap.api.maps.model.LatLng;
import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.OverRailVO;
import com.kp.monitor.service.helper.GeneralHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ${Stephen} on 2017-06-02.
 */

public class OverRailDTO implements Mapper<OverRailVO> {


    /**
     * loginSysUser : {"userId":"3","token":null,"userName":"test","depId":"652900000000",
     * "depName":null,"roleId":17}
     * list : [{"monitorLocationId":"1","monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a",
     * "lng":112,"lat":28,"speed":10,"direction":20,"locationTime":1496385193000,
     * "createTime":1496384923000},{"monitorLocationId":"2",
     * "monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,"lat":28,"speed":20,
     * "direction":20,"locationTime":1496385172000,"createTime":1496384969000},
     * {"monitorLocationId":"3","monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","lng":112,
     * "lat":28,"speed":30,"direction":20,"locationTime":1496385074000,"createTime":1496385076000}]
     * info : {"exceptionId":"09aad32636aa469db1090453e6b38011",
     * "personId":"0651c3444f9d4282a333ea44c85f5e8e","depId":"652900000000",
     * "deviceId":"2b65e7d42fb74d749a294297fb230e9a","exceptionType":"1","dealUserId":1,
     * "exceptionCnt":"2","firstFoundTime":1496108637000,"lastFoudTime":1496195037000,
     * "dealTime":null,"completeTime":null,"dealContent":null,"dealStatus":"N","status":"Y",
     * "monitorPersonName":"TEST005","idCard":"456325198708051234","sex":"M","typeId":"4",
     * "birthday":"2012-05-25","monitorDeviceCode":"BZH001","monitorPersonTypeName":"社区矫正",
     * "fenceType":"0","fenceAreaId":"","addrLng":"112.995116","addrLat":"28.168417","radius":"10
     * .0"}
     */

    private LoginSysUserBean loginSysUser;
    private InfoBean info;
    private List<ListBean> list;
    public FenceArea fenceArea;

    @Override
    public OverRailVO transform() {
        try {
            OverRailVO railVO = new OverRailVO();
            List<LatLng> latLngs = new ArrayList<>();
            latLngs.clear();
            railVO.name = info.monitorPersonName;
            if (info.sex.equals("M")) {
                railVO.sex = "男";
            } else {
                railVO.sex = "女";
            }
            railVO.cardId = info.idCard;
            railVO.deviceNumber = info.monitorDeviceCode;
            railVO.personType = info.monitorPersonTypeName;

            String radius = info.getRadius();
            if (!StringUtils.isEmpty(radius)) {
                railVO.radius = Double.valueOf(info.getRadius());
            }
            railVO.lng = Double.valueOf(info.getAddrLng());
            railVO.lat = Double.valueOf(info.getAddrLat());
            railVO.type = Integer.parseInt(info.fenceType);
            String dealStatus = info.dealStatus;
            railVO.exceptionId = info.exceptionId;
            if (dealStatus.equals("Y")) {
                railVO.exStatus = "处理完成";
            } else if (dealStatus.equals("X")) {
                railVO.exStatus = "处理中";
            } else if (dealStatus.equals("N")) {
                railVO.exStatus = "未处理";
            }
            railVO.dealContent = info.dealContent;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(info.birthday);
            int age = GeneralHelper.getInstance().getAge(date);
            railVO.age = age;
            if (null != fenceArea)
                railVO.coordinatesLatLngs = GeneralHelper.getInstance().strToLng(fenceArea
                        .coordinates);
            if (null != list && list.size() > 0)
                for (int i = 0; i < list.size(); i++) {
                    ListBean listBean = list.get(i);
                    LatLng latLng = new LatLng(listBean.getLat(), listBean.getLng());
                    latLngs.add(latLng);
                    railVO.locations = latLngs;
                }
            return railVO;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginSysUserBean getLoginSysUser() {
        return loginSysUser;
    }

    public void setLoginSysUser(LoginSysUserBean loginSysUser) {
        this.loginSysUser = loginSysUser;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class FenceArea {


        /**
         * fenceAreaId : f1
         * fenceAreaName : 测试围栏1
         * deptId : 652901000000
         * status : Y
         * createUserId : 09aad32636aa469db1090453e6b380b3
         * createTime : 1496386144000
         * coordinates : [[112.994359,28.169485],[112.9943,28.169254],[112.994756,28.16889],[112
         * .994922,28.169339]]
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

    public static class LoginSysUserBean {
        /**
         * userId : 3
         * token : null
         * userName : test
         * depId : 652900000000
         * depName : null
         * roleId : 17
         */

        private String userId;
        private String token;
        private String userName;
        private String depId;
        private String depName;
        private int roleId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }

    public static class InfoBean {
        /**
         * exceptionId : 09aad32636aa469db1090453e6b38011
         * personId : 0651c3444f9d4282a333ea44c85f5e8e
         * depId : 652900000000
         * deviceId : 2b65e7d42fb74d749a294297fb230e9a
         * exceptionType : 1
         * dealUserId : 1
         * exceptionCnt : 2
         * firstFoundTime : 1496108637000
         * lastFoudTime : 1496195037000
         * dealTime : null
         * completeTime : null
         * dealContent : null
         * dealStatus : N
         * status : Y
         * monitorPersonName : TEST005
         * idCard : 456325198708051234
         * sex : M
         * typeId : 4
         * birthday : 2012-05-25
         * monitorDeviceCode : BZH001
         * monitorPersonTypeName : 社区矫正
         * fenceType : 0
         * fenceAreaId :
         * addrLng : 112.995116
         * addrLat : 28.168417
         * radius : 10.0
         */

        private String exceptionId;
        private String personId;
        private String depId;
        private String deviceId;
        private String exceptionType;
        private int dealUserId;
        private String exceptionCnt;
        private String firstFoundTime;
        private String lastFoudTime;
        private String dealTime;
        private String completeTime;
        private String dealContent;
        private String dealStatus;
        private String status;
        private String monitorPersonName;
        private String idCard;
        private String sex;
        private String typeId;
        private String birthday;
        private String monitorDeviceCode;
        private String monitorPersonTypeName;
        private String fenceType;
        private String fenceAreaId;
        private double addrLng;
        private double addrLat;
        private String radius;

        public String getExceptionId() {
            return exceptionId;
        }

        public void setExceptionId(String exceptionId) {
            this.exceptionId = exceptionId;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getDepId() {
            return depId;
        }

        public void setDepId(String depId) {
            this.depId = depId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getExceptionType() {
            return exceptionType;
        }

        public void setExceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
        }

        public int getDealUserId() {
            return dealUserId;
        }

        public void setDealUserId(int dealUserId) {
            this.dealUserId = dealUserId;
        }

        public String getExceptionCnt() {
            return exceptionCnt;
        }

        public void setExceptionCnt(String exceptionCnt) {
            this.exceptionCnt = exceptionCnt;
        }

        public String getFirstFoundTime() {
            return firstFoundTime;
        }

        public void setFirstFoundTime(String firstFoundTime) {
            this.firstFoundTime = firstFoundTime;
        }

        public String getLastFoudTime() {
            return lastFoudTime;
        }

        public void setLastFoudTime(String lastFoudTime) {
            this.lastFoudTime = lastFoudTime;
        }

        public String getDealTime() {
            return dealTime;
        }

        public void setDealTime(String dealTime) {
            this.dealTime = dealTime;
        }

        public String getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(String completeTime) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMonitorPersonName() {
            return monitorPersonName;
        }

        public void setMonitorPersonName(String monitorPersonName) {
            this.monitorPersonName = monitorPersonName;
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

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getMonitorDeviceCode() {
            return monitorDeviceCode;
        }

        public void setMonitorDeviceCode(String monitorDeviceCode) {
            this.monitorDeviceCode = monitorDeviceCode;
        }

        public String getMonitorPersonTypeName() {
            return monitorPersonTypeName;
        }

        public void setMonitorPersonTypeName(String monitorPersonTypeName) {
            this.monitorPersonTypeName = monitorPersonTypeName;
        }

        public String getFenceType() {
            return fenceType;
        }

        public void setFenceType(String fenceType) {
            this.fenceType = fenceType;
        }

        public String getFenceAreaId() {
            return fenceAreaId;
        }

        public void setFenceAreaId(String fenceAreaId) {
            this.fenceAreaId = fenceAreaId;
        }

        public double getAddrLng() {
            return addrLng;
        }

        public void setAddrLng(double addrLng) {
            this.addrLng = addrLng;
        }

        public double getAddrLat() {
            return addrLat;
        }

        public void setAddrLat(double addrLat) {
            this.addrLat = addrLat;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }
    }

    public static class ListBean {
        /**
         * monitorLocationId : 1
         * monitorDeviceId : 2b65e7d42fb74d749a294297fb230e9a
         * lng : 112
         * lat : 28
         * speed : 10.0
         * direction : 20.0
         * locationTime : 1496385193000
         * createTime : 1496384923000
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
