package com.kp.monitor.data.vo;

/**
 * des:
 * Created by HL
 * on 2017-05-03.
 */

public class LoginVO {

    private String token;
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "LoginVO{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
