package com.kp.monitor.model;

import com.hl.foundation.library.utils.CThreadUtils;
import com.kp.monitor.contract.GatherDetailContract;
import com.kp.monitor.data.vo.GatherVO;

import rx.Observable;
import rx.Subscriber;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class GatherDetailModel implements GatherDetailContract.Model {

    @Override
    public Observable<GatherVO> getGatherDetail(String gatherId) {

        Observable<GatherVO> observable = Observable.create(new Observable.OnSubscribe<GatherVO>() {
            @Override
            public void call(final Subscriber<? super GatherVO> subscriber) {


                CThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {

                        GatherVO vo = new GatherVO();
                        subscriber.onNext(vo);
                    }
                }, 1000);
            }
        });

        return observable;
    }
}
