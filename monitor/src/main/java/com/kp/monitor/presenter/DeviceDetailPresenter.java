package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.DeviceDetailContract;
import com.kp.monitor.data.vo.DeviceVO;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class DeviceDetailPresenter extends DeviceDetailContract.Presenter {


    @Override
    public void sendGetDeviceDetail(String deviceId) {

        mRxManager.add(mModel.getDeviceDetail(deviceId).subscribe(new HttpSubscriber<DeviceVO>(mContext,true) {
            @Override
            protected void _onNext(DeviceVO deviceVO) {

                if(null != mView){
                    mView.onGetDeviceDetailComplete(deviceVO);
                    mView.dismissLoading();
                }
            }
        }));
    }
}
