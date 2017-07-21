package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo.DeviceVO;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface DeviceDetailContract {

    interface Model extends BaseModel{

        Observable<DeviceVO> getDeviceDetail(String deviceId);
    }

    interface View extends BaseLoadingView {

        void onGetDeviceDetailComplete(DeviceVO deviceVO);
    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void sendGetDeviceDetail(String deviceId);
    }
}
