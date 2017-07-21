package com.kp.monitor.data.vo;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public class UserVO {

    private int authority;

    private String createTime; // 2017-09-12
    private String userName;  //  用户名
    private String userRealName; // 姓名
    private String sex; // 男 / 女
    private String phone;
    private String officePhone;
    private String email;
    private String deptName; //  部门地区
    private String whereFrom; //  发射源（内存  数据库 网络） 供调试使用

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "whereFrom='" + whereFrom + '\'' +
                ", userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                '}';
    }
}
