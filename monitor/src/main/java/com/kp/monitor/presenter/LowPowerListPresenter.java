package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.data.vo.LowPowerVO;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.UnusalInfoVO;
import com.kp.monitor.model.LowPowerListModel;
import com.kp.monitor.presenter.base.BaseListPresenter;
import com.kp.monitor.view.LowPowerListView;

/**
 * des:低电
 * Created by HL
 * on 2017-05-10.
 */

public class LowPowerListPresenter extends BaseListPresenter<LowPowerListModel,LowPowerListView,LowPowerVO> {


    @Override
    protected boolean hasOtherDatas() {
        return true;
    }

    @Override
    protected void getOtherDatas() {
        mRxManager.add(mModel.getHeaderInfo().subscribe(new HttpSubscriber<UnusalInfoVO>() {
            @Override
            protected void _onNext(UnusalInfoVO unusalInfoVO) {

                if(null != mView){
                    mView.onLowPowerBaicInfoComplete(unusalInfoVO);
                }
            }
        }));
    }

    public void changeHandleSatue(String exceptionId,String content,String type){
        mRxManager.add(mModel.changHandleStatue(exceptionId,content,type).subscribe(new HttpSubscriber<MsgVo>(mContext,true) {
            @Override
            public void _onNext(MsgVo msgVo) {
                if (null != mView) mView.onHandleComplete(msgVo);
            }
        }));
    }
}
