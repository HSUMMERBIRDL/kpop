package com.kp.monitor.presenter.base;


import com.hl.foundation.frame.ui.list.BaseListContract;
import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.basis.http.HttpSubscriber;

import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * des:
 * Created by HL
 * on 2017/2/13 0013.
 */

/**
 * @param <V>
 * @param <M>
 * @param <O>
 */
public abstract class BaseListPresenter<M extends BaseListContract.Model, V extends
        BaseListContract.View, O>
        extends BaseListContract.Presenter<M, V> {

    protected boolean isRefresh = false;  // 是否是刷新 (有可能是上啦加载)

    @Override
    public void sendGetDataListRequest(Map<String, Object> parameters) {

        int startPage = (int) parameters.get(BaseListModel.START_PAGE);

        if (startPage == 0) {
            isRefresh = true;
        } else {
            isRefresh = false;
        }

        mRxManager.add(mModel.getDatasList(parameters).subscribe(getSubscriber()));
    }

    @Override
    public void sendSearchDeviceListRequest(Map<String, Object> parameters) {



        mRxManager.add(mModel.getDatasList(parameters).subscribe(getSubscriber()));
    }

    private Subscriber getSubscriber() {
        return new HttpSubscriber<List<O>>(mContext, false) {

            @Override
            public void onStart() {
                super.onStart();

//                if (null != mView) {
//                    mView.showLoading("请稍后...");
//                }
            }

            @Override
            protected void _onNext(final List<O> list) {

                if (null != mView) {
                    mView.stopLoading();
                    mView.returnDataListData(list);

                    if(hasOtherDatas()){
                        getOtherDatas();
                    }
                }
            }

            @Override
            protected void _onError(String message, int code) {

                super._onError(message, code);
                LogUtils.loge(TAG, "接口onError： " + message);
//                if(code != 401){
                if (null != mView) {
                    mView.showErrorTip(message);
                }
//                }
            }
        };
    }

    protected void getOtherDatas() {

    }

    protected boolean hasOtherDatas() {
        return false;
    }
}
