package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.MemberBasicInfoContract;
import com.kp.monitor.data.vo.MemberVO;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class MemberBasicInfoPresenter extends MemberBasicInfoContract.Presenter {


    @Override
    public void sendSendMemberBasicInfo(String memberId) {

        mRxManager.add(mModel.getMemberBasicInfo(memberId).subscribe(new HttpSubscriber<MemberVO>(mContext,true) {
            @Override
            protected void _onNext(MemberVO memberVO) {

                if(null != mView){
                    mView.onGetMemberBasicInfoComplete(memberVO);
                }
            }
        }));
    }
}
