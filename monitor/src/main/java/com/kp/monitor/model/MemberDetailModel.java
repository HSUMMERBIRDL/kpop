package com.kp.monitor.model;

import com.kp.monitor.basis.http.Api;
import com.kp.monitor.contract.MemberDetailContract;
import com.kp.monitor.data.dto.MemberDTO;
import com.kp.monitor.data.vo.MemberVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class MemberDetailModel implements MemberDetailContract.Model {

    @Override
    public Observable<MemberVO> getMemberDetail(String memberId) {

        return Api.getApiService().getMemberBasicInfo(memberId)
                .map(new DTOFunc1<MemberDTO>())
                .map(new VOFunc1<MemberVO>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
