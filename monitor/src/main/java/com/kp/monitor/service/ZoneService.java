package com.kp.monitor.service;

import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.data.dto.ZoneInfoDTO;
import com.kp.monitor.data.vo.XianVO;
import com.kp.monitor.data.vo.ZoneInfoVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des: 获取地区信息
 * Created by HL
 * on 2017-05-16.
 */

public class ZoneService {

    private static final String TAG = ZoneService.class.getSimpleName();

    private static ZoneService INSTANCE;

    private List<XianVO> xianList = new ArrayList<>(); // 县列表
    private String superiorAreaName = "";

    public static ZoneService getInstance() {
        if (null == INSTANCE) {
            synchronized (ZoneService.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ZoneService();
                }
            }
        }
        return INSTANCE;
    }


//    private OnZoneInfoCompleteListener listener;
//
//    public  void  setOnZoneInfoCompleteListener(OnZoneInfoCompleteListener listener){
//
//        this.listener = listener;
//    }

    public interface OnZoneInfoCompleteListener {

        void onZoneInfoComplete(List<XianVO> xianVOs,String superiorAreaName);
    }

    public void getXianList(final OnZoneInfoCompleteListener listener) {


        if (!CollectionUtils.isNullOrEmpty(xianList)) {

            if (null != listener) {
                listener.onZoneInfoComplete(xianList,superiorAreaName);
            }
            return;
        }

        List<XianVO> fromDBXians = getFromDB();
        if (!CollectionUtils.isNullOrEmpty(fromDBXians)) {

            if (null != listener) {
                listener.onZoneInfoComplete(fromDBXians,superiorAreaName);
            }
            return;
        }


//        Observable<ZoneInfoDTO> zoneInfoDTOObservable = Observable.create(new Observable.OnSubscribe<ZoneInfoDTO>() {
//            @Override
//            public void call(Subscriber<? super ZoneInfoDTO> subscriber) {
//
//                String s = FileUtil.readFileFromAssets(AppAplication.getAppContext(), "zone_real.json");
//                Gson gson = new Gson();
//                ZoneInfoDTO zoneInfoDTO = gson.fromJson(s, ZoneInfoDTO.class);
//                subscriber.onNext(zoneInfoDTO);
//            }
//        });

        //  因为有destory  所有必须在这里需要检测
        if(null == xianList){
            xianList = new ArrayList<>();
        }

        Api.getApiService().getZone()
                .map(new DTOFunc1<List<ZoneInfoDTO>>())
                .map(new Func1<List<ZoneInfoDTO>, ZoneInfoDTO>() {
                    @Override
                    public ZoneInfoDTO call(List<ZoneInfoDTO> zoneInfoDTOs) {
                        return zoneInfoDTOs.get(0);
//                        if(!CollectionUtils.isNullOrEmpty(zoneInfoDTOs)){
//                            return zoneInfoDTOs.get(0);
//                        }else {
//                            return null;
//                        }
                    }
                })
//        zoneInfoDTOObservable
                .map(new VOFunc1<ZoneInfoVO>())
                .doOnNext(new Action1<ZoneInfoVO>() {
                    @Override
                    public void call(ZoneInfoVO zoneInfoVO) {

                        superiorAreaName = zoneInfoVO.getName();
                    }
                })
                .map(new Func1<ZoneInfoVO, List<XianVO>>() {
                    @Override
                    public List<XianVO> call(ZoneInfoVO zoneInfoVO) {
                        return zoneInfoVO.getDatas();
                    }
                })
                .doOnNext(new Action1<List<XianVO>>() {
                    @Override
                    public void call(List<XianVO> xianVOs) {

                        xianList.clear();
                        xianList.addAll(xianVOs);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<XianVO>>() {
                    @Override
                    protected void _onNext(List<XianVO> xianVOs) {

                        if (null != listener) {
                            listener.onZoneInfoComplete(xianVOs,superiorAreaName);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if(e instanceof  IndexOutOfBoundsException){

                        }else{
                            super.onError(e);
                        }


                        LogUtils.loge(TAG,e);
                    }
                });

//        String s = FileUtil.readFileFromAssets(AppAplication.getAppContext(), "zone.json");
//        Gson gson = new Gson();
//        ZoneVO zoneVO = gson.fromJson(s, ZoneVO.class);
//        List<XianVO> list = zoneVO.getDatas();
//        return list;

    }

//    /**
//     * 本地当前获取
//     * @return
//     */
//    private List<XianVO> getFromLocal() {
//
//        return xianList;
//    }

    /**
     * 从数据库获取
     *
     * @return
     */
    private List<XianVO> getFromDB() {

        return null;
    }

    public void destory(){
        if(CollectionUtils.isNullOrEmpty(xianList)){
           return;
        }
        xianList.clear();
        xianList = null;
    }

}
