package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.SeparateVO;
import com.kp.monitor.data.vo.UnusalInfoVO;
import com.kp.monitor.model.SeparateListModel;
import com.kp.monitor.presenter.base.BaseListPresenter;
import com.kp.monitor.view.SeparateListView;

/**
 * des: 底座分离
 * Created by HL
 * on 2017-05-10.
 */

public class SeparateListPresenter extends BaseListPresenter<SeparateListModel,SeparateListView,SeparateVO> {

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
                    mView.onSeparateBaicInfoComplete(unusalInfoVO);
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
