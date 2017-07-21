package com.kp.monitor.model;

import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.contract.MsgDetailContract;
import com.kp.monitor.data.dto.MessageDTO;
import com.kp.monitor.data.dto.MsgResponseDTO;
import com.kp.monitor.data.dto.base.BaseResponse;
import com.kp.monitor.data.request.MsgReadStutas;
import com.kp.monitor.data.vo.MessageVO;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class MsgDetailModel implements MsgDetailContract.Model {
    private MsgReadStutas msgReadStutas;

    @Override
    public Observable<MessageVO> getMsgDetail(String msgId) {
        return Api.getApiService().getMessageDetail(msgId, ApiConstants.MESSAGE_TYPE_MSG)
                .map(new DTOFunc1<MessageDTO>())
                .map(new VOFunc1<MessageVO>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<MsgVo> changeMessageStutas(String msgId) {
        if (null == msgReadStutas)
            msgReadStutas = new MsgReadStutas();
        msgReadStutas.readStatus = ApiConstants.MESSAGE_YTPE;
        return Api.getApiService().changeReadMessageStutas(msgId, msgReadStutas).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Func1<MsgVo, MsgVo>() {

                    @Override
                    public MsgVo call(MsgVo msgVo) {
                        return msgVo;
                    }
                });
    }
}
