package com.kp.monitor.service.handler;

import com.hl.foundation.library.exception.ApiException;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.base.BaseResponse;

import java.util.IllegalFormatCodePointException;

import rx.functions.Func1;

/**
 * des: 剥去业务响应消息
 * BaseResponse map  data
 * Created by HL
 * on 2017-05-02.
 */

public class DTOFunc1<T> implements Func1<BaseResponse<T>, T> {


    @Override
    public T call(BaseResponse<T> baseResponse) {
        int returnCode = baseResponse.code;
        String returnMsg = baseResponse.msg;

        if (baseResponse.code == ApiConstants.SUCCESS) {
            return baseResponse.data;
        } else {
            throw new ApiException(returnCode, returnMsg);
        }
    }
}
