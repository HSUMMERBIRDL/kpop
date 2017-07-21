package com.kp.monitor.presenter;

import com.kp.monitor.data.event.Events;
import com.kp.monitor.presenter.base.BaseListPresenter;
import com.kp.monitor.data.vo.MemberItemVO;
import com.kp.monitor.model.MemberListModel;
import com.kp.monitor.view.MemberListView;

import rx.functions.Action1;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class MemberListPresenter extends BaseListPresenter<MemberListModel,MemberListView,MemberItemVO> {

    @Override
    protected void onStart() {
        super.onStart();

        mRxManager.on(Events.UPLOAD_FILE_SIZE_CHANGE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

                if(null != mView){
                    mView.onNotUploadFileUpdate(integer);
                }
            }
        });

    }

}
