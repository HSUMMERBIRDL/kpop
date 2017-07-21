package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.MsgDetailContract;
import com.kp.monitor.data.vo.MessageVO;
import com.kp.monitor.data.vo.MsgVo;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class MsgDetailPresenter extends MsgDetailContract.Presenter {

    @Override
    public void sendGetMsgDetail(String msgId) {
        mRxManager.add(mModel.getMsgDetail(msgId).subscribe(new HttpSubscriber<MessageVO>(mContext,true) {
            @Override
            protected void _onNext(MessageVO messageVO) {
                if (null != mView) {
                    mView.onGetMsgDetailComplete(messageVO);
                    mView.dismissLoading();
                }
            }
        }));
    }

    @Override
    public void sendReadMessage(String msgId) {
        mRxManager.add(mModel.changeMessageStutas(msgId).subscribe(new HttpSubscriber<MsgVo>(mContext,true) {
            @Override
            protected void _onNext(MsgVo msgVo) {
                if (null != mView) {
                    mView.onGetMsgStutasComplete(msgVo);
                    mView.dismissLoading();
                }
            }
        }));
    }
}
