package com.kp.monitor.model;

import com.kp.monitor.basis.http.Api;
import com.kp.monitor.contract.TrackingContract;
import com.kp.monitor.data.dto.TrakingDetailDTO;
import com.kp.monitor.data.vo.BaseLatLngVo;
import com.kp.monitor.service.handler.DTOFunc1;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Stephen} on 2017-06-09.
 */

public class TrackingDetailModel implements TrackingContract.Model {
    @Override
    public Observable<BaseLatLngVo> getTrakingDetail(Map<String, Object> map) {
        return Api.getApiService().getTrackingLocationLngLat(map).map(new
                DTOFunc1<TrakingDetailDTO>()).map(new Func1<TrakingDetailDTO, BaseLatLngVo>() {


            @Override
            public BaseLatLngVo call(TrakingDetailDTO trakingDetailDTO) {
                if (null != trakingDetailDTO) {
                    return trakingDetailDTO.transform();
                }
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
