package com.kp.monitor.data.request;

/**
 * des:
 * Created by HL
 * on 2017-05-18.
 */

public class LoginRequest {

    private String userName;
    private String password;
    private String screenResolution;  // 分辨率
    private String operatingSystem;  // 操作系统版本
    private String ip;  // 手机ip
    private String nicAddress; // 手机IMEI码
    private String browser; // 厂商+机型

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNicAddress() {
        return nicAddress;
    }

    public void setNicAddress(String nicAddress) {
        this.nicAddress = nicAddress;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
