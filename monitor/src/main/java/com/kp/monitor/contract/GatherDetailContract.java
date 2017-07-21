package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo. GatherVO;

import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface GatherDetailContract {

    interface Model extends BaseModel{

        Observable<GatherVO> getGatherDetail(String gatherId);
    }

    interface View extends BaseLoadingView {

        void onGetGatherDetailComplete(GatherVO gatherVO);
    }

    abstract class Presenter extends BasePresenter<Model, View>{

        public abstract void sendGetGatherDetail(String  gatherId);
    }
}
