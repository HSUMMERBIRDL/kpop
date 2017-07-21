package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.MsgDTO;
import com.kp.monitor.data.dto.OffWristApiDTO;
import com.kp.monitor.data.request.OverRailStatus;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffWristVO;
import com.kp.monitor.data.vo.UnusalInfoVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des: 脱腕
 * Created by HL
 * on 2017-05-10.
 */

public class OffWristListModel extends BaseListModel<OffWristVO> {

    private Observable<OffWristApiDTO> offWristApiDTOObservable;

    @Override
    protected Observable<List<OffWristVO>> getList(Map<String, Object> parameters) {


        String exceptionId = (String) parameters.get(ApiConstants.EXCEPTION_ID);

        offWristApiDTOObservable =  Api.getApiService().getOffWristList(exceptionId)
                .map(new DTOFunc1<OffWristApiDTO>());

        return offWristApiDTOObservable.map(new Func1<OffWristApiDTO, List<OffWristApiDTO.ListBean>>() {
                    @Override
                    public List<OffWristApiDTO.ListBean> call(OffWristApiDTO offWristApiDTO) {
                        return offWristApiDTO.getList();
                    }
                })
                .map(new Func1<List<OffWristApiDTO.ListBean>, List<OffWristVO>>() {
                    @Override
                    public List<OffWristVO> call(List<OffWristApiDTO.ListBean> rowsBeen) {
                        List<OffWristVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeen.size(); i++) {
                            OffWristApiDTO.ListBean rowsBean = rowsBeen.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 拿到该接口的非list部分的数据
     *
     * @return
     */
    public Observable<UnusalInfoVO> getHeaderInfo() {

        return offWristApiDTOObservable.map(new Func1<OffWristApiDTO, OffWristApiDTO.InfoBean>() {
            @Override
            public OffWristApiDTO.InfoBean call(OffWristApiDTO lowPowerApiDTO) {
                return lowPowerApiDTO.getInfo();
            }
        }).map(new Func1<OffWristApiDTO.InfoBean, UnusalInfoVO>() {
            @Override
            public UnusalInfoVO call(OffWristApiDTO.InfoBean infoBean) {
                return infoBean.transform();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MsgVo> changHandleStatue(String exceptionId, String status, String
            content) {

        OverRailStatus overRailStatus = new OverRailStatus();
        String stringByFormat = TimeUtil.getStringByFormat(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss");
        overRailStatus.dealStatus = status;
        overRailStatus.dealContent = content;
        overRailStatus.dealTime = stringByFormat;
        return Api.getApiService().changeOverRailStatus(exceptionId, overRailStatus)
                .map(new DTOFunc1<MsgDTO>())
                .map(new VOFunc1<MsgVo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
