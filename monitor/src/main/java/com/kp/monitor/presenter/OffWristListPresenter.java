package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffWristVO;
import com.kp.monitor.data.vo.UnusalInfoVO;
import com.kp.monitor.model.OffWristListModel;
import com.kp.monitor.presenter.base.BaseListPresenter;
import com.kp.monitor.view.OffWristListView;

/**
 * des: 脱腕
 * Created by HL
 * on 2017-05-10
 */

public class OffWristListPresenter extends BaseListPresenter<OffWristListModel,OffWristListView,OffWristVO> {

    @Override
    protected boolean hasOtherDatas() {
        return true;
    }

    @Override
    protected void getOtherDatas() {
        mRxManager.add(mModel.getHeaderInfo().subscribe(new HttpSubscriber<UnusalInfoVO>(mContext,true) {
            @Override
            protected void _onNext(UnusalInfoVO unusalInfoVO) {

                if(null != mView){
                    mView.onOffWristBaicInfoComplete(unusalInfoVO);
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
