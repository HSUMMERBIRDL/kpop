package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo.UserVO;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface PersonInfoContract {

    interface Model extends BaseModel{

//        Observable<UserVO> getUserInfo();
    }

    interface View extends BaseLoadingView {

        void onGetUserInfoComplete(UserVO vo);
    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void getUserInfo();
    }
}
