package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.utils.CThreadUtils;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.data.dto.TrakingDTO;
import com.kp.monitor.data.vo.TrakingVO;
import com.kp.monitor.service.handler.DTOFunc1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Stephen} on 2017-06-07.
 */

public class TrakingListModel extends BaseListModel<TrakingVO> {
    @Override
    protected Observable<List<TrakingVO>> getList(Map<String, Object> parameters) {
        return Api.getApiService().getTrakingLocationList(parameters).map(new
                DTOFunc1<TrakingDTO>())
                .map(new Func1<TrakingDTO, List<TrakingDTO.RowsBean>>() {
                    @Override
                    public List<TrakingDTO.RowsBean> call(TrakingDTO trakingDTO) {
                        return trakingDTO.getRows();
                    }
                }).map(new Func1<List<TrakingDTO.RowsBean>, List<TrakingVO>>() {
                    @Override
                    public List<TrakingVO> call(List<TrakingDTO.RowsBean> rowsBeen) {
                        List<TrakingVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeen.size(); i++) {
                            TrakingDTO.RowsBean rowsBean = rowsBeen.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

//    Observable<List<TrakingVO>> listObservable = Observable.create(new Observable
//            .OnSubscribe<List<TrakingVO>>() {
//        @Override
//        public void call(final Subscriber<? super List<TrakingVO>> subscriber) {
//
//            CThreadUtils.runOnMainThread(new Runnable() {
//                @Override
//                public void run() {
//                    List<TrakingVO> list = new ArrayList<>();
//
//                    for (int i = 0; i < 23; i++) {
//                        TrakingVO trakingVO = new TrakingVO();
//                        trakingVO.name = "张三";
//                        trakingVO.cardId = "44585885656565625335";
//                        trakingVO.category = "极端宗教";
//                        trakingVO.sex = "男";
//                        trakingVO.status = "监控中";
//                        trakingVO.monitorDeviceId = "S4356";
//                        list.add(trakingVO);
//                    }
//                    subscriber.onNext(list);
//                }
//            }, 1000);
//        }
//    });

}
