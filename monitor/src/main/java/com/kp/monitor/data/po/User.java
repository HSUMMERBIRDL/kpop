package com.kp.monitor.data.po;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.UserVO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * des:
 * Created by HL
 * on 2017-05-05.
 */

@Entity
public class User implements Mapper<UserVO>{

    public static final int ROOT = 0; //  超级管理员
    public static final int GENERAL = 1; //  普通管理员

    @Id
    private String  userId;
    @Property
    private String userName;
    @Property
    private int authority;
    @Property
    private String deptName;
    @Property
    private String createTime; // 2017-09-12
    @Property
    private String userRealName; // 姓名
    @Property
    private String sex; // 男 / 女
    @Property
    private String phone;
    @Property
    private String officePhone;
    @Property
    private String email;


    @Generated(hash = 577079916)
    public User(String userId, String userName, int authority, String deptName,
            String createTime, String userRealName, String sex, String phone,
            String officePhone, String email) {
        this.userId = userId;
        this.userName = userName;
        this.authority = authority;
        this.deptName = deptName;
        this.createTime = createTime;
        this.userRealName = userRealName;
        this.sex = sex;
        this.phone = phone;
        this.officePhone = officePhone;
        this.email = email;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAuthority() {
        return this.authority;
    }
    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public UserVO transform() {

        UserVO userVO = new UserVO();
        userVO.setAuthority(authority);
        userVO.setUserName(userName);
        userVO.setDeptName(deptName);
        userVO.setCreateTime(createTime);
        userVO.setEmail(email);
        userVO.setPhone(phone);
        userVO.setSex(sex);
        userVO.setOfficePhone(officePhone);
        userVO.setUserRealName(userRealName);
        return userVO;
    }
    public String getDeptName() {
        return this.deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getUserRealName() {
        return this.userRealName;
    }
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getOfficePhone() {
        return this.officePhone;
    }
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
