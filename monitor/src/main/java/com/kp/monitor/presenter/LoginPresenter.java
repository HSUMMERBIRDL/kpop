package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.LoginContract;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class LoginPresenter extends LoginContract.Presenter {


    @Override
    public void login(String username, String pass) {

//        if(null != mView){
//            mView.onLoginSuccess();
//        }

        mRxManager.add(mModel.login(username,pass).subscribe(new HttpSubscriber(mContext,true) {
            @Override
            protected void _onNext(Object o) {

                if(null != mView){
                    mView.onLoginSuccess();
                }
            }
        }));
    }
}
