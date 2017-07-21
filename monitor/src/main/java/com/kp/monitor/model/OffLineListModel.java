package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.MsgDTO;
import com.kp.monitor.data.dto.OffLineApiDTO;
import com.kp.monitor.data.request.OverRailStatus;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffLineVO;
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
 * des: 掉线
 * Created by HL
 * on 2017-05-10.
 */

public class OffLineListModel extends BaseListModel<OffLineVO> {

    private Observable<OffLineApiDTO> offLineApiDTOObservable;

    @Override
    protected Observable<List<OffLineVO>> getList(Map<String, Object> parameters) {


        String exceptionId = (String) parameters.get(ApiConstants.EXCEPTION_ID);

        offLineApiDTOObservable = Api.getApiService().getOffLineList(exceptionId)
                .map(new DTOFunc1<OffLineApiDTO>());

        return offLineApiDTOObservable.map(new Func1<OffLineApiDTO, List<OffLineApiDTO.ListBean>>() {
            @Override
            public List<OffLineApiDTO.ListBean> call(OffLineApiDTO offLineApiDTO) {
                return offLineApiDTO.getList();
            }
        })
                .map(new Func1<List<OffLineApiDTO.ListBean>, List<OffLineVO>>() {
                    @Override
                    public List<OffLineVO> call(List<OffLineApiDTO.ListBean> rowsBeen) {
                        List<OffLineVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeen.size(); i++) {
                            OffLineApiDTO.ListBean listBean = rowsBeen.get(i);
                            list.add(listBean.transform());
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

        return offLineApiDTOObservable.map(new Func1<OffLineApiDTO, OffLineApiDTO.InfoBean>() {
            @Override
            public OffLineApiDTO.InfoBean call(OffLineApiDTO lowPowerApiDTO) {
                return lowPowerApiDTO.getInfo();
            }
        }).map(new Func1<OffLineApiDTO.InfoBean, UnusalInfoVO>() {
            @Override
            public UnusalInfoVO call(OffLineApiDTO.InfoBean infoBean) {
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
