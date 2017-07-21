package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.MsgItemApiDTO;
import com.kp.monitor.data.vo.MessageItemVO;
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

public class MessageListModel extends BaseListModel<MessageItemVO> {

    @Override
    protected Observable<List<MessageItemVO>> getList(Map<String, Object> parameters) {
        int messageType = (int) parameters.get(ApiConstants.MESSAGE_TYPE);
        return Api.getApiService().getMessageList(pageSize, startPage, messageType)
                .map(new DTOFunc1<MsgItemApiDTO>())
                .map(new Func1<MsgItemApiDTO, List<MsgItemApiDTO.RowsBean>>() {
                    @Override
                    public List<MsgItemApiDTO.RowsBean> call(MsgItemApiDTO msgItemApiDTO) {
                        return msgItemApiDTO.getRows();
                    }
                })
                .map(new Func1<List<MsgItemApiDTO.RowsBean>, List<MessageItemVO>>() {
                    @Override
                    public List<MessageItemVO> call(List<MsgItemApiDTO.RowsBean> rowsBeens) {
                        List<MessageItemVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeens.size(); i++) {
                            MsgItemApiDTO.RowsBean rowsBean = rowsBeens.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
