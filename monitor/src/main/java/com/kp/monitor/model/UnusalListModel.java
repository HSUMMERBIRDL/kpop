package com.kp.monitor.model;

import com.hl.foundation.frame.ui.list.BaseListModel;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.UnusalItemApiDTO;
import com.kp.monitor.data.vo.UnusalItemVO;
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

public class UnusalListModel extends BaseListModel<UnusalItemVO> {


    @Override
    protected Observable<List<UnusalItemVO>> getList(Map<String, Object> parameters) {


        List<String> unusualStatueSelectedList = (List<String>) parameters.get(ApiConstants.EXCEPTION_TYPES);
        List<String> handleStatueSelectedList = (List<String>) parameters.get(ApiConstants.DEAL_STATUSES);

        return Api.getApiService().getUnusalList(pageSize, startPage, unusualStatueSelectedList, handleStatueSelectedList)
                .map(new DTOFunc1<UnusalItemApiDTO>())
                .map(new Func1<UnusalItemApiDTO, List<UnusalItemApiDTO.RowsBean>>() {
                    @Override
                    public List<UnusalItemApiDTO.RowsBean> call(UnusalItemApiDTO unusalItemApiDTO) {
                        return unusalItemApiDTO.getRows();
                    }
                })
                .map(new Func1<List<UnusalItemApiDTO.RowsBean>, List<UnusalItemVO>>() {
                    @Override
                    public List<UnusalItemVO> call(List<UnusalItemApiDTO.RowsBean> rowsBeen) {
                        List<UnusalItemVO> list = new ArrayList<>();
                        for (int i = 0; i < rowsBeen.size(); i++) {
                            UnusalItemApiDTO.RowsBean rowsBean = rowsBeen.get(i);
                            list.add(rowsBean.transform());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
