package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.MemberListDTO;
import com.kp.monitor.data.vo.MemberItemVO;
import com.kp.monitor.service.handler.DTOFunc1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class MemberListModel extends BaseListModel<MemberItemVO> {

    @Override
    protected Observable<List<MemberItemVO>> getList(Map<String, Object> parameters) {


        String depId = (String) parameters.get(ApiConstants.DEP_ID);
        String idCard = (String) parameters.get(ApiConstants.ID_CARD);
        String name = (String)parameters.get(ApiConstants.NAME);
//
        return Api.getApiService().getMemberList(pageSize,startPage,idCard,name,depId)
                .map(new DTOFunc1<MemberListDTO>())
                .map(new Func1<MemberListDTO, List<MemberListDTO.RowsBean>>() {
                    @Override
                    public List<MemberListDTO.RowsBean> call(MemberListDTO memberListDTO) {
                        return memberListDTO.getRows();
                    }
                })
                .map(new Func1<List<MemberListDTO.RowsBean>, List<MemberItemVO>>() {
                    @Override
                    public List<MemberItemVO> call(List<MemberListDTO.RowsBean> memberItemDTOs) {
                        List<MemberItemVO> list = new ArrayList<>();
                        for (int i = 0; i <memberItemDTOs.size() ; i++) {
                            MemberListDTO.RowsBean rowsBean = memberItemDTOs.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


//        Observable<List<MemberItemVO>> listObservable = Observable.create(new Observable.OnSubscribe<List<MemberItemVO>>() {
//            @Override
//            public void call(final Subscriber<? super List<MemberItemVO>> subscriber) {
//
//
//                CThreadUtils.runOnMainThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<MemberItemVO> list = new ArrayList<MemberItemVO>();
//
//                        for (int i = 0; i < 23; i++) {
//
//                            MemberItemVO vo = new MemberItemVO();
//                            vo.setName("田总");
//                            vo.setCardId("44585885656565625335");
//                            vo.setCategory("极端宗教");
//                            vo.setDeviceNum("S4356");
//                            vo.setSex("男");
//                            vo.setStatue("监控中");
//                            vo.setMemberId("" + i);
//                            vo.setInMoniter(true);
//                            list.add(vo);
//                        }
//                        subscriber.onNext(list);
//                    }
//                }, 1000);
//            }
//        });
//
//        return listObservable;
    }
}
