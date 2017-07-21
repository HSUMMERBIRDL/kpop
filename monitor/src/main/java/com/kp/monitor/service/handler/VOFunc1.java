package com.kp.monitor.service.handler;

import com.kp.monitor.data.Mapper;

import rx.functions.Func1;

/**
 * des:转化为vo ， po
 * DTO map VO/PO
 * Created by HL
 * on 2017-05-02.
 */

public class VOFunc1<T> implements Func1<Mapper<T>, T> {

    @Override
    public T call(Mapper<T> mapper) {
        return mapper.transform();
    }
}
