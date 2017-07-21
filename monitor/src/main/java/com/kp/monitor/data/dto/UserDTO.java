package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.po.User;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public class UserDTO implements Mapper<User>{


    /**
     * createTime : 1483602496000
     * depId : 650000000000
     * depName : 新疆维吾尔自治区
     * email : bzh@outlook.com
     * officePhone : 0743-08051531
     * phone : 13875817650
     * roleId : 17
     * sex : W
     * status : Y
     * updateTime : 1485155960000
     * userId : 3
     * userRealName : test
     * userName : 张三
     */

    private long createTime;
    private String depId;
    private String depName;
    private String email;
    private String officePhone;
    private String phone;
    private int roleId;
    private String sex;
    private String status;
    private long updateTime;
    private String userId;
    private String userRealName;
    private String userName;


    @Override
    public User transform() {

        User user = new User();
        user.setUserId(userId);

        user.setCreateTime(TimeUtil.formatData(TimeUtil.dateFormat,createTime));

        user.setUserName(userName);
        user.setUserRealName(userRealName);

        if(StringUtils.equals(sex,"M")){
            user.setSex("男");
        }else if(StringUtils.equals(sex,"W")){
            user.setSex("女");
        }
        user.setPhone(phone);
        user.setOfficePhone(officePhone);
        user.setEmail(email);
        user.setDeptName(depName);

        return user;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
