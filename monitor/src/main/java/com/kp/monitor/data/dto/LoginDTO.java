package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.LoginVO;

/**
 * des:
 * Created by HL
 * on 2017-05-03.
 */

public class LoginDTO implements Mapper<LoginVO>{
    /**
     * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4IiwiaWF0IjoxNDk1MTAxMDcyLCJzdWIiOiIiLCJpc3MiOiIiLCJhdWQiOiIiLCJleHAiOjE0OTUxMDgyNzIsIm5iZiI6MTQ5NTEwMTA3Mn0.enwO55g8LMDBbRpOPQtT6B8QMS_SAlpqum19-hIdh2o
     */

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
    public LoginVO transform() {

        LoginVO loginModel = new LoginVO();
        loginModel.setToken(token);
        loginModel.setUserId(userId);
        return loginModel;
    }


}
