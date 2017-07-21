package com.kp.monitor.contract;

import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.hl.foundation.frame.ui.BaseView;
import com.kp.monitor.data.vo.UnusualDetailVO;

import rx.Observable;


/**
 * Created by ${Stephen} on 2017-06-01.
 * 管理异常接口
 */

public interface UnusalDetailContract {
    interface Model extends BaseModel {
        Observable<UnusualDetailVO> getUnusalDetail(String id);
    }

    interface View extends BaseView {
        void onGetUnusalDetailComplete(UnusualDetailVO unusalDetailVO);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getUnusalDetail(String id);
    }
}
