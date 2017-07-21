package com.kp.monitor.model;

import com.kp.monitor.basis.http.Api;
import com.kp.monitor.contract.MainContract;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.dto.UnMsgDTO;
import com.kp.monitor.data.dto.UnusualTotalPersonDTO;
import com.kp.monitor.data.vo.UnMsgVO;
import com.kp.monitor.data.vo.UnusualPersonVO;
import com.kp.monitor.service.RecordService;
import com.kp.monitor.service.UserService;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */


public class MainModel implements MainContract.Model {

    private static final java.lang.String TAG = MainModel.class.getSimpleName();

//    @Override
//    public Observable<UserVO> getUserInfo() {
//
//        return UserService.getInstance().getUserInfo();
//    }

    @Override
    public Observable logout() {
//        return null;
        return UserService.getInstance().logout();
    }

    @Override
    public Observable<UnMsgVO> getUnreadMsg() {
        return Api.getApiService().getUnreadMsg().map(new DTOFunc1<UnMsgDTO>()).map(new VOFunc1
                <UnMsgVO>() {
            @Override
            public UnMsgVO call(Mapper<UnMsgVO> mapper) {
                return mapper.transform();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<UnusualPersonVO> getUnusualPerson() {
        return Api.getApiService().getUnusualPersonNumber().map(new
                DTOFunc1<UnusualTotalPersonDTO>()).map(new VOFunc1<UnusualPersonVO>() {

            @Override
            public UnusualPersonVO call(Mapper<UnusualPersonVO> mapper) {
                return mapper.transform();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UnusualPersonVO> getUnusualTotalPerson() {
        return Api.getApiService().getUnusualTotalPersonNumber().map(new
                DTOFunc1<UnusualTotalPersonDTO>()).map(new VOFunc1<UnusualPersonVO>() {

            @Override
            public UnusualPersonVO call(Mapper<UnusualPersonVO> mapper) {
                return mapper.transform();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UnusualPersonVO> getGatherPerson() {
        return Api.getApiService().getGatherNumber().map(new DTOFunc1<UnusualTotalPersonDTO>())
                .map(new VOFunc1<UnusualPersonVO>() {

            @Override
            public UnusualPersonVO call(Mapper<UnusualPersonVO> mapper) {
                return mapper.transform();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void getNotUploadFileNum() {

        RecordService.getInstance().postUploadFileSize();
    }

}
