package com.kp.monitor.model;

import com.kp.monitor.basis.http.Api;
import com.kp.monitor.contract.DeviceDetailContract;
import com.kp.monitor.data.dto.DeviceDTO;
import com.kp.monitor.data.vo.DeviceVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class DeviceDetailModel implements DeviceDetailContract.Model {

    @Override
    public Observable<DeviceVO> getDeviceDetail(String deviceId) {
        return Api.getApiService().getDeviceDetail(deviceId)
                .map(new DTOFunc1<DeviceDTO>())
                .map(new VOFunc1<DeviceVO>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
