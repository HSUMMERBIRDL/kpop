package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MemberItemVO;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-26.
 */

public class MemberListDTO {


    /**
     * rows : [{"address":"燕儿窝街道","birthday":"2017-05-17","createTime":1495444334000,"createUserId":"1","depId":"650102002","idCard":"430613198704121487","monitorDeviceCode":"BZH001","monitorDeviceId":"2b65e7d42fb74d749a294297fb230e9a","monitorPersonId":"1165e7d42fb74d749a294297fb230e9a","monitorPersonName":"BZH","monitorPersonTypeName":"测试类型00","monitorStatus":"Y","personType":"R","phone":"13875817650","sex":"M","status":"N","typeId":1},{"address":"天山区","birthday":"2017-05-25","createTime":1495524414000,"createUserId":"1","depId":"650102","idCard":"430613198704121488","monitorDeviceCode":"BZH004","monitorDeviceId":"590cc67d3f4b4cc0aefc4fc63b6d85d1","monitorPersonId":"09aad32636aa469db1090453e6b380b3","monitorPersonName":"TEST004","monitorPersonTypeName":"测试类型01","monitorStatus":"N","personType":"R","phone":"13875817650","sex":"W","status":"Y","typeId":3},{"address":"湖南省岳阳市","birthday":"2017-05-25","createTime":1495698735000,"createUserId":"1","idCard":"456325198708051234","monitorPersonId":"0651c3444f9d4282a333ea44c85f5e8e","monitorPersonName":"TEST005","monitorStatus":"Y","personType":"R","sex":"M","status":"Y"},{"createTime":1495761519000,"createUserId":"1","depId":"","monitorDeviceCode":"BZH006","monitorDeviceId":"7f444f9992c64afca24e16118abc5f1a","monitorPersonId":"f3afc4feb3d44e2b97e9bf734b80a7a4","monitorPersonName":"BZH006","personType":"R","phone":"123456789","status":"Y"}]
     * total : 4
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

    public static class RowsBean implements Mapper<MemberItemVO> {
        /**
         * address : 燕儿窝街道
         * birthday : 2017-05-17
         * createTime : 1495444334000
         * createUserId : 1
         * depId : 650102002
         * idCard : 430613198704121487
         * monitorDeviceCode : BZH001
         * monitorDeviceId : 2b65e7d42fb74d749a294297fb230e9a
         * monitorPersonId : 1165e7d42fb74d749a294297fb230e9a
         * monitorPersonName : BZH
         * monitorPersonTypeName : 测试类型00
         * monitorStatus : Y
         * personType : R
         * phone : 13875817650
         * sex : M
         * status : N
         * typeId : 1
         */

        private String address;
        private String birthday;
        private long createTime;
        private String createUserId;
        private String depId;
        private String idCard;
        private String monitorDeviceCode;
        private String monitorDeviceId;
        private String monitorPersonId;
        private String monitorPersonName;
        private String monitorPersonTypeName;
        private String monitorStatus;
        private String personType;
        private String phone;
        private String sex;
        private String status;
        private int typeId;

        @Override
        public MemberItemVO transform() {

            MemberItemVO vo = new MemberItemVO();
            vo.setMemberId(monitorPersonId);

            //  不通过这里进行判断了
//            if(StringUtils.equals(monitorStatus,"Y")) {
//                vo.setInMoniter(true);
//                vo.setStatue("监控中");
//            }else if(StringUtils.equals(monitorStatus,"N")){
//                vo.setInMoniter(false);
//                vo.setStatue("未监控");
//            }

            if(StringUtils.isEmpty(monitorDeviceId)){

                vo.setInMoniter(false);
                vo.setStatue("未监控");
            }else{
                vo.setInMoniter(true);
                vo.setStatue("监控中");
            }

            if(StringUtils.equals(sex,"W")){
                vo.setSex("女");
            }else if(StringUtils.equals(sex,"M")){
                vo.setSex("男");
            }

            vo.setName(monitorPersonName);
            vo.setCardId(idCard);
            vo.setCategory(monitorPersonTypeName);
            vo.setDeviceNum(monitorDeviceCode);


            return vo;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
}
