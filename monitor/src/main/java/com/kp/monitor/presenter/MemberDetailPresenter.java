package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.MemberDetailContract;
import com.kp.monitor.data.vo.MemberVO;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class MemberDetailPresenter extends MemberDetailContract.Presenter {



    @Override
    public void sendGetMemberDetail(final String memberId) {
        mRxManager.add(mModel.getMemberDetail(memberId).subscribe(new HttpSubscriber<MemberVO>(mContext,true) {
            @Override
            protected void _onNext(MemberVO memberVO) {
                if(null != mView){
                    mView.onGetMemberDetailComplete(memberVO);
                }
            }
        }));
    }
}
