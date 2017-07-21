package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.OverRailContract;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OverRailVO;

/**
 * Created by ${Stephen} on 2017-06-02.
 */

public class OverRailPresenter extends OverRailContract.Presenter {
    @Override
    public void requstOverRail(String msgId) {
        mRxManager.add(mModel.getOverRail(msgId).subscribe(new HttpSubscriber<OverRailVO>() {
            @Override
            protected void _onNext(OverRailVO overRailVO) {
                if (null != mView) mView.onGetOverRail(overRailVO);
            }
        }));
    }

    @Override
    public void changeOrderRailStatus(String id,String status,String content) {
        mRxManager.add(mModel.changeOverRailStatus(id,status,content).subscribe(new HttpSubscriber<MsgVo>(mContext,true) {
            @Override
            public void _onNext(MsgVo msgVo) {
                if (null != mView) mView.onGetOverRailStatus(msgVo);
            }
        }));
    }
}
