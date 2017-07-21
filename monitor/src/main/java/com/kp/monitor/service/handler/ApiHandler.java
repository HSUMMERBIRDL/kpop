package com.kp.monitor.service.handler;

import android.app.Activity;

import com.hl.foundation.library.manager.AppManager;
import com.kp.monitor.service.UserService;
import com.kp.monitor.ui.activity.LoginActivity;
import com.kp.monitor.ui.dialog.BaseTitleDialog;

/**
 * des:统一处理http请求的某些业务
 * Created by HL
 * on 2017/2/22 0022.
 */

public class ApiHandler {


    private static boolean hasOtherLoginDialogShow = false;

    public static void showtOtherLoginDialog() {

        UserService.clearLoginState();

        if(hasOtherLoginDialogShow){
            return;
        }

        final Activity activity = AppManager.getAppManager().currentActivity();

        if(null != activity){
            if(!activity.isFinishing()){

                final BaseTitleDialog otherLoginDialog = new BaseTitleDialog(activity, false);
                otherLoginDialog.setTilte("登录失效");
                otherLoginDialog.setContent("请点击确定重先登录验证");
                otherLoginDialog.setOnDialogClickListener(new BaseTitleDialog.OnDialogClickListener() {
                    @Override
                    public void onOkClick() {
                        hasOtherLoginDialogShow = false;
                        otherLoginDialog.dismiss(); //关闭dialog
                        AppManager.getAppManager().finishAllActivity();
                        LoginActivity.startAction(activity);
                    }
                });

                if(!otherLoginDialog.isShow()){
                    hasOtherLoginDialogShow = true;
                    otherLoginDialog.show();
                }
            }
        }

//        CThreadUtils.runOnMainThread(new Runnable() {
//            @Override
//            public void run() {
//                final Activity activity = AppManager.getAppManager().currentActivity();
//
//                if(null != activity){
//                    if(!activity.isFinishing()){
//
//                        final BaseTitleDialog otherLoginDialog = new BaseTitleDialog(activity, false);
//                        otherLoginDialog.setTilte("登录失效");
//                        otherLoginDialog.setContent("请点击确定重先登录验证");
//                        otherLoginDialog.setOnDialogClickListener(new BaseTitleDialog.OnDialogClickListener() {
//                            @Override
//                            public void onOkClick() {
//                                hasOtherLoginDialogShow = false;
//                                otherLoginDialog.dismiss(); //关闭dialog
//                                AppManager.getAppManager().finishAllActivity();
//                                LoginActivity.startAction(activity);
//                            }
//                        });
//
//                        if(!otherLoginDialog.isShow()){
//                            hasOtherLoginDialogShow = true;
//                            otherLoginDialog.show();
//                        }
//                    }
//                }
//            }
//        });
    }
}
