package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.MainContract;
import com.kp.monitor.data.event.Events;
import com.kp.monitor.data.vo.UnMsgVO;
import com.kp.monitor.data.vo.UnusualPersonVO;
import com.kp.monitor.data.vo.UserVO;
import com.kp.monitor.service.UserService;

import rx.functions.Action1;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class MainPresenter extends MainContract.Presenter {


    @Override
    protected void onStart() {
        super.onStart();

        mRxManager.on(Events.UPLOAD_FILE_SIZE_CHANGE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if(null != mView){
                    mView.onNotUploadFileUpdate(integer);
                }
            }
        });

    }

    @Override
    public void sendGetUserInfo() {
        UserService.getInstance().getUserInfo(new HttpSubscriber<UserVO>() {
            @Override
            protected void _onNext(UserVO vo) {

                if (mView != null) {
                    mView.onGetUserInfoComplete(vo);
                }
            }
        });
    }

    @Override
    public void logout() {
        mRxManager.add(mModel.logout().subscribe(new HttpSubscriber(mContext, false) {
                    @Override
                    protected void _onNext(Object o) {

                    }
                })
        );
    }

    @Override
    public void requestUnReadMsg() {
        mRxManager.add(mModel.getUnreadMsg().subscribe(new HttpSubscriber<UnMsgVO>() {
            @Override
            protected void _onNext(UnMsgVO unMsgVO) {
                if (null != mView) {
                    mView.onGetUnMessageResponse(unMsgVO);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (null != mView) mView.showErrorTip(e.getMessage());
            }
        }));
    }

    @Override
    public void getUnusualTotalPersonNumber() {
        mRxManager.add(mModel.getUnusualTotalPerson().subscribe(new HttpSubscriber<UnusualPersonVO>() {
            @Override
            protected void _onNext(UnusualPersonVO unusualPersonVO) {
                if (null != mView) {
                    mView.onGetUnusualPersonTotal(unusualPersonVO);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mRxManager.clear();
                if (null != mView) mView.showErrorTip(e.getMessage());
            }
        }));
    }

    @Override
    public void getUnusalPerson() {
        mRxManager.add(mModel.getUnusualPerson().subscribe(new HttpSubscriber<UnusualPersonVO>() {
            @Override
            protected void _onNext(UnusualPersonVO unusualPersonVO) {
                if (null != mView) mView.onGetUnusualPerson(unusualPersonVO);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (null != mView) mView.showErrorTip(e.getMessage());
            }
        }));
    }

    @Override
    public void getGatherNumber() {
        mRxManager.add(mModel.getGatherPerson().subscribe(new HttpSubscriber<UnusualPersonVO>() {
            @Override
            protected void _onNext(UnusualPersonVO unusualPersonVO) {
                if (null != mView) mView.onGetGatherNumber(unusualPersonVO);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (null != mView) mView.showErrorTip(e.getMessage());
            }
        }));
    }

    @Override
    public void getNotUploadFileNum() {

        mModel.getNotUploadFileNum();
    }
}
