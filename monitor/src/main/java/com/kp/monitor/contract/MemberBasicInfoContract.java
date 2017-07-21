package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo.MemberVO;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface MemberBasicInfoContract {

    interface Model extends BaseModel{

        Observable<MemberVO> getMemberBasicInfo(String memberId);

    }

    interface View extends BaseLoadingView {

        void onGetMemberBasicInfoComplete(MemberVO memberVO);

    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void sendSendMemberBasicInfo(String memberId);
    }
}

