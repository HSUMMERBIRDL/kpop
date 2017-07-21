package com.kp.monitor.data.dto.base;

/**
 * des: HTTP接口返回的基本数据结构
 * Created by HL
 * on 2017-05-02.
 */

public class BaseResponse<T> {

    public String msg = "";
    public int code ;
    public T data;
    @Override
    public String toString() {
        return "BaseResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
