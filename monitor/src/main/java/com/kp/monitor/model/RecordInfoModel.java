package com.kp.monitor.model;

import com.kp.monitor.contract.RecordInfoContract;
import com.kp.monitor.service.RecordService;
import com.kp.monitor.service.handler.RecUpProInfoCallBack;

/**
 * des:
 * Created by HL
 * on 2017-04-25.
 */



public class RecordInfoModel implements RecordInfoContract.Model {


    @Override
    public void startUploadFiles(RecUpProInfoCallBack callBack) {
        RecordService.getInstance().startUploadFile(callBack);
    }


//    @Override
//    public void startUploadFiles() {
//
//
//
//        RecordService.getInstance().startUploadFile();
//    }
}
