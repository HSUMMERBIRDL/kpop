package com.kp.monitor.data.vo;

/**
 * des:通讯录列表
 * Created by HL
 * on 2017-05-10.
 */

public class AddressItemVO {

    private String phone;
    private String name;
    private String depName; // 地区

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
