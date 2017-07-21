package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.utils.CThreadUtils;
import com.kp.monitor.data.vo.GatherItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class GatherListModel extends BaseListModel<GatherItemVO> {


    @Override
    protected Observable<List<GatherItemVO>> getList(Map<String, Object> parameters) {

//        return Api.getApiService().getGatherList(startPage,pageSize)
//                .map(new DTOFunc1<List<GatherItemDTO>>())
//                .map(new Func1<List<GatherItemDTO>, List<GatherItemVO>>() {
//                    @Override
//                    public List<GatherItemVO> call(List<GatherItemDTO> GatherItemDTOs) {
//                        List<GatherItemVO> list = new ArrayList<>();
//                        for (int i = 0; i <GatherItemDTOs.size() ; i++) {
//                            GatherItemDTO GatherItemDTO = GatherItemDTOs.get(i);
//                            list.add(GatherItemDTO.transform());
//                        }
//                        return list;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());

        Observable<List<GatherItemVO>> listObservable = Observable.create(new Observable.OnSubscribe<List<GatherItemVO>>() {
            @Override
            public void call(final Subscriber<? super List<GatherItemVO>> subscriber) {


                CThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        List<GatherItemVO> list = new ArrayList();

                        for (int i = 0; i < 23; i++) {

                            GatherItemVO vo = new GatherItemVO();
                            vo.setGatherId("3454" + i);
                            vo.setLoc("新疆克拉玛依" + i);
                            list.add(vo);
                        }
                        subscriber.onNext(list);
                    }
                }, 1000);
            }
        });

        return listObservable;
    }
}
