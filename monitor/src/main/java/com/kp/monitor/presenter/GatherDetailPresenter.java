package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.GatherDetailContract;
import com.kp.monitor.data.vo.GatherVO;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class GatherDetailPresenter extends GatherDetailContract.Presenter {

    @Override
    public void sendGetGatherDetail(String gatherId) {
        mRxManager.add(mModel.getGatherDetail(gatherId).subscribe(new HttpSubscriber<GatherVO>(mContext,true) {
            @Override
            protected void _onNext(GatherVO gatherVO) {

                if(null != mView){
                    mView.onGetGatherDetailComplete(gatherVO);
                    mView.dismissLoading();
                }
            }
        }));
    }
}
