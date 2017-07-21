package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.AddressItemVO;

import java.util.List;

/**
 * des: 通讯录列表接口
 * Created by HL
 * on 2017-05-19.
 */

public class AddressItemApiDTO {


    /**
     * rows : [{"userId":22,"userName":"444455","userRealName":"44444","password":"11111","sex":"M","phone":"4444","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494575355000,"updateTime":1494575355000,"roleId":null,"depId":1,"depName":null},{"userId":20,"userName":"1231111","userRealName":"123","password":"11111","sex":"M","phone":"123123","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494500305000,"updateTime":1494500305000,"roleId":null,"depId":1,"depName":null},{"userId":17,"userName":"4444","userRealName":"44444","password":"11111","sex":"M","phone":"44444","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494499646000,"updateTime":1494499646000,"roleId":null,"depId":1,"depName":null},{"userId":16,"userName":"33333","userRealName":"3333","password":"11111","sex":"M","phone":"33333","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494499526000,"updateTime":1494499526000,"roleId":null,"depId":1,"depName":null},{"userId":15,"userName":"123333","userRealName":"1233","password":"11111","sex":"M","phone":"1111","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494499444000,"updateTime":1494499444000,"roleId":null,"depId":1,"depName":null},{"userId":14,"userName":"123111","userRealName":"123123","password":"11111","sex":"M","phone":"111111","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494498698000,"updateTime":1494498698000,"roleId":null,"depId":1,"depName":null},{"userId":13,"userName":"123","userRealName":"123","password":"11111","sex":"M","phone":"123","officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494498298000,"updateTime":1494498298000,"roleId":null,"depId":1,"depName":null},{"userId":10,"userName":"userName","userRealName":"D22emo","password":"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92","sex":"M","phone":null,"officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494391295000,"updateTime":1494391295000,"roleId":null,"depId":null,"depName":null},{"userId":8,"userName":"1","userRealName":null,"password":"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92","sex":"M","phone":null,"officePhone":null,"birthday":null,"email":null,"status":"Y","createTime":1494384159000,"updateTime":1494384159000,"roleId":null,"depId":null,"depName":null},{"userId":7,"userName":"zhou","userRealName":"zhou","password":"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92","sex":"M","phone":"03131","officePhone":null,"birthday":null,"email":"46456","status":"Y","createTime":1486975728000,"updateTime":1486975926000,"roleId":18,"depId":8,"depName":null}]
     * total : 15
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

    public static class RowsBean implements Mapper<AddressItemVO>{
        /**
         * userId : 22
         * userName : 444455
         * userRealName : 44444
         * password : 11111
         * sex : M
         * phone : 4444
         * officePhone : null
         * birthday : null
         * email : null
         * status : Y
         * createTime : 1494575355000
         * updateTime : 1494575355000
         * roleId : null
         * depId : 1
         * depName : null
         */

        private String userId;
        private String userName;
        private String userRealName;
        private String password;
        private String sex;
        private String phone;
        private String officePhone;
        private String birthday;
        private String email;
        private String status;
        private long createTime;
        private long updateTime;
        private String roleId;
        private String depId;
        private String depName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserRealName() {
            return userRealName;
        }

        public void setUserRealName(String userRealName) {
            this.userRealName = userRealName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOfficePhone() {
            return officePhone;
        }

        public void setOfficePhone(String officePhone) {
            this.officePhone = officePhone;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
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

        @Override
        public AddressItemVO transform() {

            AddressItemVO addressItemVO = new AddressItemVO();
            addressItemVO.setPhone(officePhone);
            addressItemVO.setName(userRealName);
            addressItemVO.setDepName(depName);
            return addressItemVO;
        }
    }
}
