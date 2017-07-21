package com.kp.monitor.basis.http;

import android.content.Context;

import com.hl.foundation.library.rx.RxSubscriber;
import com.kp.monitor.service.handler.ApiHandler;

/**
 * des:
 * Created by HL
 * on 2017-05-24.
 */

public abstract  class HttpSubscriber<T> extends RxSubscriber<T>{


    public  HttpSubscriber(){

    }

    public HttpSubscriber(Context context, String msg, boolean showDialog) {
        super(context, msg, showDialog);
    }

    public HttpSubscriber(Context context) {
        super(context);
    }

    public HttpSubscriber(Context context, boolean showDialog) {
        super(context, showDialog);
    }

    public HttpSubscriber(Context context, boolean showDialog, String uuid) {
        super(context, showDialog, uuid);
    }



    @Override
    protected void _onError(String message, int code) {
        super._onError(message, code);

        if(code == 401){ //  token 失效  登录异常
            unsubscribe();
            ApiHandler.showtOtherLoginDialog();
        }
    }
}
