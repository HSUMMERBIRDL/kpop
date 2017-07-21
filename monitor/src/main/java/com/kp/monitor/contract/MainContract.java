package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo.UnMsgVO;
import com.kp.monitor.data.vo.UnusualPersonVO;
import com.kp.monitor.data.vo.UserVO;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface MainContract {

    interface Model extends BaseModel {

//        Observable<UserVO> getUserInfo();

        Observable logout();  //  退出登录接口 无需等待返回

        Observable<UnMsgVO> getUnreadMsg();

        //获取总人数
        Observable<UnusualPersonVO> getUnusualTotalPerson();

        //异常人数
        Observable<UnusualPersonVO> getUnusualPerson();

        //聚集风险
        Observable<UnusualPersonVO> getGatherPerson();

        void getNotUploadFileNum();
    }

    interface View extends BaseLoadingView {

        void onGetUserInfoComplete(UserVO userVO);

        void onGetUnMessageResponse(UnMsgVO unMsgVO);

        void onGetUnusualPersonTotal(UnusualPersonVO unusualPersonVO);

        void onGetUnusualPerson(UnusualPersonVO unusualPersonVO);

        void onGetGatherNumber(UnusualPersonVO unusualPersonVO);

        void onNotUploadFileUpdate(int currentNum);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void sendGetUserInfo();

        public abstract void logout();

        public abstract void requestUnReadMsg();

        public abstract void getUnusualTotalPersonNumber();

        public abstract void getUnusalPerson();

        public abstract void getGatherNumber();

        public abstract void getNotUploadFileNum();
    }
}
