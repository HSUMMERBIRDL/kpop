package com.kp.monitor.presenter;

import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.contract.TrackingContract;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.BaseLatLngVo;
import com.kp.monitor.data.vo.TrakingDetailVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Stephen} on 2017-06-09.
 * presenter具体实现类
 */

public class TrakingDetailPresenter extends TrackingContract.Presenter {
    @Override
    public void getTrakingDetailLocation(Map<String,Object> map) {
        mRxManager.add(mModel.getTrakingDetail(map).subscribe(new HttpSubscriber<BaseLatLngVo>() {
            @Override
            protected void _onNext(BaseLatLngVo baseLatLngVo) {
                if (null != mView)mView.onGetTrakingDetail(baseLatLngVo);
            }
        }));
    }
}
