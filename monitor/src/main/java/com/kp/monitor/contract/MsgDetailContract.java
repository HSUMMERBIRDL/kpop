package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.dto.MsgResponseDTO;
import com.kp.monitor.data.dto.base.BaseResponse;
import com.kp.monitor.data.vo.MessageVO;
import com.kp.monitor.data.vo.MsgVo;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface MsgDetailContract {

    interface Model extends BaseModel{

        Observable<MessageVO> getMsgDetail(String msgId);
        Observable<MsgVo> changeMessageStutas(String msgId);
    }

    interface View extends BaseLoadingView {
        void onGetMsgDetailComplete(MessageVO messageVO);
        void onGetMsgStutasComplete(MsgVo msgVo);
    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void sendGetMsgDetail(String msgId);
        public abstract void sendReadMessage(String msgId);
    }
}
