package com.kp.monitor.contract;

import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.hl.foundation.frame.ui.BaseView;
import com.kp.monitor.data.vo.BaseLatLngVo;
import com.kp.monitor.data.vo.TrakingDetailVO;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ${Stephen} on 2017-06-09.
 */

public interface TrackingContract {
    interface Model extends BaseModel {
        Observable<BaseLatLngVo> getTrakingDetail(Map<String,Object> map);
    }

    interface View extends BaseView {
        void onGetTrakingDetail(BaseLatLngVo trakingDetailVOs);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getTrakingDetailLocation(Map<String,Object> map);
    }
}
