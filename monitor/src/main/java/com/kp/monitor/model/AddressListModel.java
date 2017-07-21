package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.AddressItemApiDTO;
import com.kp.monitor.data.vo.AddressItemVO;
import com.kp.monitor.service.handler.DTOFunc1;

import java.util.ArrayList;
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


public class AddressListModel extends BaseListModel<AddressItemVO> {


    @Override
    protected Observable<List<AddressItemVO>> getList(Map<String, Object> parameters) {

        String addressName = (String) parameters.get(ApiConstants.ADDRESS_NAME);
        String addressPhone = (String)parameters.get(ApiConstants.ADDRESS_OFFICE_PHONE);

        return Api.getApiService().getAddressList(startPage, pageSize,addressName,addressPhone)
                .map(new DTOFunc1<AddressItemApiDTO>())
                .map(new Func1<AddressItemApiDTO, List<AddressItemApiDTO.RowsBean>>() {
                    @Override
                    public List<AddressItemApiDTO.RowsBean> call(AddressItemApiDTO
                                                                         addressItemApiDTO) {
                        return addressItemApiDTO.getRows();
                    }
                })
                .map(new Func1<List<AddressItemApiDTO.RowsBean>, List<AddressItemVO>>() {
                    @Override
                    public List<AddressItemVO> call(List<AddressItemApiDTO.RowsBean> rowsBeens) {
                        List<AddressItemVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeens.size(); i++) {
                            AddressItemApiDTO.RowsBean rowsBean = rowsBeens.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
