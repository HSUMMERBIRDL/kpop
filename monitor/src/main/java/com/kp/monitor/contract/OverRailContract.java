package com.kp.monitor.contract;

import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.hl.foundation.frame.ui.BaseView;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OverRailVO;

import rx.Observable;

/**
 * Created by ${Stephen} on 2017-06-02.
 * 越栏接口管理
 */

public interface OverRailContract {
    interface Model extends BaseModel {
        Observable<OverRailVO> getOverRail(String msgId);

        Observable<MsgVo> changeOverRailStatus(String exceptionId,String status,String content);
    }

    interface View extends BaseView {
        void onGetOverRail(OverRailVO overRailVO);

        void onGetOverRailStatus(MsgVo msgVo);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void requstOverRail(String msgId);

        public abstract void changeOrderRailStatus(String exceptionId,String status,String content);
    }
}
