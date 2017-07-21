package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.PersonInfoContract;
import com.kp.monitor.data.vo.UserVO;
import com.kp.monitor.service.UserService;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class PersonInfoPresenter extends PersonInfoContract.Presenter {
    @Override
    public void getUserInfo() {

        UserService.getInstance().getUserInfo(new HttpSubscriber<UserVO>() {
            @Override
            protected void _onNext(UserVO vo) {

                if (mView != null) {
                    mView.onGetUserInfoComplete(vo);
                }
            }
        });
    }
}
