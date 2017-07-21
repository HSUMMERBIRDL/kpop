package com.kp.monitor.presenter;

import com.kp.monitor.data.event.Events;
import com.kp.monitor.data.event.HandleStateEvent;
import com.kp.monitor.data.vo.UnusalItemVO;
import com.kp.monitor.model.UnusalListModel;
import com.kp.monitor.presenter.base.BaseListPresenter;
import com.kp.monitor.view.UnusalListView;

import rx.functions.Action1;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class UnusalListPresenter extends BaseListPresenter<UnusalListModel,UnusalListView,UnusalItemVO> {


    @Override
    protected void onStart() {
        super.onStart();

        mRxManager.on(Events.HANDLE_STATE_CHANGE, new Action1<HandleStateEvent>() {
            @Override
            public void call(HandleStateEvent handleStateEvent) {

                if(null != mView){
                    mView.onHandleStatueChange(handleStateEvent);
                }
            }
        });
    }
}
