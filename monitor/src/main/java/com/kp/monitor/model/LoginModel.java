package com.kp.monitor.model;

import com.hl.foundation.library.utils.Md5Security;
import com.kp.monitor.contract.LoginContract;
import com.kp.monitor.service.UserService;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */



public class LoginModel implements LoginContract.Model {

    @Override
    public Observable login(String username, String pass) {

        String password = Md5Security.getMD5(pass.toLowerCase());
        String s = password.toLowerCase();


        return UserService.getInstance().login(username,s);
    }
}
