package com.kp.monitor.contract;


import com.hl.foundation.frame.ui.BaseLoadingView;
import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.kp.monitor.data.vo.RecUpProInfoVo;
import com.kp.monitor.service.handler.RecUpProInfoCallBack;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface RecordInfoContract {

    interface Model extends BaseModel {

        void startUploadFiles(RecUpProInfoCallBack callBack);

    }

    interface View extends BaseLoadingView {

        void onRecordFilesUploadProgress(RecUpProInfoVo vo);
        void onNotUploadFileUpdate(int currentNum);

    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void startUploadFiles();

    }
}
