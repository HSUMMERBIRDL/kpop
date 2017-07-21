package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.DeviceDTO;
import com.kp.monitor.data.dto.DeviceItemApiDTO;
import com.kp.monitor.data.dto.MsgItemApiDTO;
import com.kp.monitor.data.vo.DeviceItemVO;
import com.kp.monitor.data.vo.MessageItemVO;
import com.kp.monitor.service.handler.DTOFunc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class DeviceListModel extends BaseListModel<DeviceItemVO> {


    @Override
    protected Observable<List<DeviceItemVO>> getList(Map<String, Object> parameters) {
        return Api.getApiService().getDeviceLists(parameters)
                .map(new DTOFunc1<DeviceItemApiDTO>())
                .map(new Func1<DeviceItemApiDTO, List<DeviceItemApiDTO.RowsBean>>() {

                    @Override
                    public List<DeviceItemApiDTO.RowsBean> call(DeviceItemApiDTO deviceItemApiDTO) {
                        return deviceItemApiDTO.rows;
                    }
                }).map(new Func1<List<DeviceItemApiDTO.RowsBean>, List<DeviceItemVO>>() {

                    @Override
                    public List<DeviceItemVO> call(List<DeviceItemApiDTO.RowsBean> rowsBeens) {
                        List<DeviceItemVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeens.size(); i++) {
                            DeviceItemApiDTO.RowsBean rowsBean = rowsBeens.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
