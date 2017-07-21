package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.OffLineVO;
import com.kp.monitor.data.vo.UnusalInfoVO;

import java.util.List;

/**
 * des: 掉线
 * Created by HL
 * on 2017-06-01.
 */

public class OffLineApiDTO{
    /**
     * info : {"birthday":"1957-05-25","dealStatus":"N","depId":"652901000000","deviceId":"590cc67d3f4b4cc0aefc4fc63b6d85d1","exceptionCnt":"2","exceptionId":"09aad32636aa469db1090453e6b38012","exceptionType":"2","firstFoundTime":1496112237000,"idCard":"430613198704121488","monitorDeviceCode":"BZH004","monitorPersonName":"TEST004","monitorPersonTypeName":"违法犯罪份子","personId":"09aad32636aa469db1090453e6b380b3","sex":"W","status":"Y","typeId":"3"}
     * list : [{"disconnectId":"1","exceptionId":"09aad32636aa469db1090453e6b38012","foundTime":1496298726000,"status":"Y"},{"disconnectId":"2","exceptionId":"09aad32636aa469db1090453e6b38012","foundTime":1496298977000,"status":"Y"},{"disconnectId":"3","exceptionId":"09aad32636aa469db1090453e6b38012","foundTime":1496299125000,"status":"Y"}]
     */

    private InfoBean info;
    private List<ListBean> list;

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

    public static class InfoBean implements Mapper<UnusalInfoVO>{
        /**
         * birthday : 1957-05-25
         * dealStatus : N
         * depId : 652901000000
         * deviceId : 590cc67d3f4b4cc0aefc4fc63b6d85d1
         * exceptionCnt : 2
         * exceptionId : 09aad32636aa469db1090453e6b38012
         * exceptionType : 2
         * firstFoundTime : 1496112237000
         * idCard : 430613198704121488
         * monitorDeviceCode : BZH004
         * monitorPersonName : TEST004
         * monitorPersonTypeName : 违法犯罪份子
         * personId : 09aad32636aa469db1090453e6b380b3
         * sex : W
         * status : Y
         * typeId : 3
         */

        private String birthday;
        private String dealStatus;
        private String dealContent;
        private String depId;
        private String deviceId;
        private String exceptionCnt;
        private String exceptionId;
        private String exceptionType;
        private long firstFoundTime;
        private String idCard;
        private String monitorDeviceCode;
        private String monitorPersonName;
        private String monitorPersonTypeName;
        private String personId;
        private String sex;
        private String status;
        private String typeId;

        public String getDealContent() {
            return dealContent;
        }

        public void setDealContent(String dealContent) {
            this.dealContent = dealContent;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(String dealStatus) {
            this.dealStatus = dealStatus;
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

        public String getExceptionCnt() {
            return exceptionCnt;
        }

        public void setExceptionCnt(String exceptionCnt) {
            this.exceptionCnt = exceptionCnt;
        }

        public String getExceptionId() {
            return exceptionId;
        }

        public void setExceptionId(String exceptionId) {
            this.exceptionId = exceptionId;
        }

        public String getExceptionType() {
            return exceptionType;
        }

        public void setExceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
        }

        public long getFirstFoundTime() {
            return firstFoundTime;
        }

        public void setFirstFoundTime(long firstFoundTime) {
            this.firstFoundTime = firstFoundTime;
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

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
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

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        @Override
        public UnusalInfoVO transform() {

            UnusalInfoVO vo = new UnusalInfoVO();
            vo.setDeviceNum(monitorDeviceCode);
            vo.setName(monitorPersonName);
            vo.setCardId(idCard);
            vo.setDealContent(dealContent);
            if(StringUtils.equals(dealStatus,"N")){
                vo.setHandleStatue(UnusalInfoVO.DEAL_STATUS_GOING);
            }else if(StringUtils.equals(dealStatus,"X")){
                vo.setHandleStatue(UnusalInfoVO.DEAL_STATUS_ING);
            }else if(StringUtils.equals(dealStatus,"Y")){
                vo.setHandleStatue(UnusalInfoVO.DEAL_STATUS_ED);
            }
            return vo;
        }
    }

    public static class ListBean implements Mapper<OffLineVO> {
        /**
         * disconnectId : 1
         * exceptionId : 09aad32636aa469db1090453e6b38012
         * foundTime : 1496298726000
         * status : Y
         */

        private String disconnectId;
        private String exceptionId;
        private long deviceFondTime;
        private String status;

        public String getDisconnectId() {
            return disconnectId;
        }

        public void setDisconnectId(String disconnectId) {
            this.disconnectId = disconnectId;
        }

        public String getExceptionId() {
            return exceptionId;
        }

        public void setExceptionId(String exceptionId) {
            this.exceptionId = exceptionId;
        }


        public long getDeviceFondTime() {
            return deviceFondTime;
        }

        public void setDeviceFondTime(long deviceFondTime) {
            this.deviceFondTime = deviceFondTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public OffLineVO transform() {

            OffLineVO vo = new OffLineVO();
            vo.setTime(TimeUtil.formatData(TimeUtil.dateFormatYMDHMS,deviceFondTime));
            return vo;
        }
    }
}
