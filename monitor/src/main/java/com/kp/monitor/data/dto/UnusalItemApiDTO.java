package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.UnusalItemVO;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-27.
 */

public class UnusalItemApiDTO {


    /**
     * rows : [{"exceptionId":"09aad32636aa469db1090453e6b38011","personId":"09aad32636aa469db1090453e6b380b3","deviceId":"590cc67d3f4b4cc0aefc4fc63b6d85d1","exceptionType":"1","dealUserId":null,"exceptionCnt":"2","firstFoundTime":1496108637000,"lastFoudTime":1496195037000,"dealTime":null,"completeTime":null,"dealContent":null,"dealStatus":"N","status":"Y","monitorPersonName":"TEST004","idCard":"430613198704121488","sex":"W","typeId":"3","birthday":"1957-05-25","depId":"652901000000","monitorDeviceCode":"BZH004","monitorPersonTypeName":"违法犯罪份子"}]
     * total : 1
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Mapper<UnusalItemVO>{
        /**
         * exceptionId : 09aad32636aa469db1090453e6b38011
         * personId : 09aad32636aa469db1090453e6b380b3
         * deviceId : 590cc67d3f4b4cc0aefc4fc63b6d85d1
         * exceptionType : 1
         * dealUserId : null
         * exceptionCnt : 2
         * firstFoundTime : 1496108637000
         * lastFoudTime : 1496195037000
         * dealTime : null
         * completeTime : null
         * dealContent : null
         * dealStatus : N
         * status : Y
         * monitorPersonName : TEST004
         * idCard : 430613198704121488
         * sex : W
         * typeId : 3
         * birthday : 1957-05-25
         * depId : 652901000000
         * monitorDeviceCode : BZH004
         * monitorPersonTypeName : 违法犯罪份子
         */

        private String exceptionId;
        private String personId;
        private String deviceId;
        private String exceptionType;
        private String dealUserId;
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
        private String depId;
        private String monitorDeviceCode;
        private String monitorPersonTypeName;

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


        public String getDealUserId() {
            return dealUserId;
        }

        public void setDealUserId(String dealUserId) {
            this.dealUserId = dealUserId;
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

        public String getDepId() {
            return depId;
        }

        public void setDepId(String depId) {
            this.depId = depId;
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

        @Override
        public UnusalItemVO transform() {

            UnusalItemVO vo = new UnusalItemVO();
            vo.setPersonId(personId);
            vo.setExceptionId(exceptionId);

            switch (exceptionType){
                case "1":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_OVER);
                    vo.setExceptionTypeDes("越栏");
                    break;
                case "2":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_OFFLINE);
                    vo.setExceptionTypeDes("掉线");
                    break;
                case "3":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_OFF_WRIST);
                    vo.setExceptionTypeDes("脱腕");
                    break;
                case "4":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_LOWER_POWER);
                    vo.setExceptionTypeDes("低电");
                    break;
                case "5":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_LONG_LESS_MOVE);
                    vo.setExceptionTypeDes("长时间未移动");
                    break;
                case "6":
                    vo.setExceptionType(UnusalItemVO.EXCEPTION_TYPE_BASE_SEPARATION);
                    vo.setExceptionTypeDes("底座分离");
                    break;
            }

            switch (dealStatus){
                case "N":
                    vo.setDealStatus(UnusalItemVO.DEAL_STATUS_GOING);
                    vo.setDealStatusDes("未处理");
                    break;
                case "X":
                    vo.setDealStatus(UnusalItemVO.DEAL_STATUS_ING);
                    vo.setDealStatusDes("处理中");
                    break;
                case "Y":
                    vo.setDealStatus(UnusalItemVO.DEAL_STATUS_ED);
                    vo.setDealStatusDes("处理完成");
                    break;

            }

            vo.setName(monitorPersonName);

            vo.setExceptionCount(exceptionCnt);
            vo.setCardId(idCard);
            vo.setDeviceId(monitorDeviceCode);

            return vo;
        }
    }
}
