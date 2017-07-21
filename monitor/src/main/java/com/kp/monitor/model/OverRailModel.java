package com.kp.monitor.model;

import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.contract.OverRailContract;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.dto.MsgDTO;
import com.kp.monitor.data.dto.OverRailDTO;
import com.kp.monitor.data.request.OverRailStatus;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OverRailVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Stephen} on 2017-06-02.
 */

public class OverRailModel implements OverRailContract.Model {
    OverRailStatus overRailStatus;

    @Override
    public Observable<OverRailVO> getOverRail(String msgId) {
        return Api.getApiService().getOverRail(msgId).map(new DTOFunc1<OverRailDTO>()).map(new VOFunc1<OverRailVO>() {
            @Override
            public OverRailVO call(Mapper<OverRailVO> mapper) {
                return mapper.transform();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<MsgVo> changeOverRailStatus(String exceptionId, String status, String
            content) {
        String stringByFormat = TimeUtil.getStringByFormat(System.currentTimeMillis(),
                "yyyy-MM-dd HH:mm:ss");
        if (null == overRailStatus)
            overRailStatus = new OverRailStatus();
        overRailStatus.dealStatus = status;
        overRailStatus.dealContent = content;
        overRailStatus.dealTime = stringByFormat;
        return Api.getApiService().changeOverRailStatus(exceptionId, overRailStatus)
                .map(new DTOFunc1<MsgDTO>())
                .map(new VOFunc1<MsgVo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
