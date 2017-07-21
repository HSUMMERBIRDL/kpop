package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface LoginContract {

    interface Model extends BaseModel{

        Observable login(String username, String pass);
    }

    interface View extends BaseLoadingView {

        void onLoginSuccess();
    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void login(String username,String pass);
    }
}
