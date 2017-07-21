package com.kp.monitor.presenter;

import com.kp.monitor.contract.RecordInfoContract;
import com.kp.monitor.data.event.Events;
import com.kp.monitor.data.vo.RecUpProInfoVo;
import com.kp.monitor.service.handler.RecUpProInfoCallBack;

import rx.functions.Action1;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */

public class RecordInfoPresenter extends RecordInfoContract.Presenter {

    @Override
    protected void onStart() {
        super.onStart();

        mRxManager.on(Events.UPLOAD_FILE_SIZE_CHANGE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

                if(null != mView){
                    mView.onNotUploadFileUpdate(integer);
                }
            }
        });

    }

    @Override
    public void startUploadFiles() {

        mModel.startUploadFiles(new RecUpProInfoCallBack() {
            @Override
            public void onRecordUploadProgress(RecUpProInfoVo recUpProInfo) {
                if(null != mView){
                    mView.onRecordFilesUploadProgress(recUpProInfo);
                }
            }
        });
    }
}
