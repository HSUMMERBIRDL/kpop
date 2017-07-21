package com.kp.monitor.service.helper;

import android.content.Context;

import com.kp.monitor.ui.dialog.TrackingDialog;

/**
 * Created by ${Stephen} on 2017-06-07.
 */

public class DialogHelper {
    private static DialogHelper dialogHelper;
    private static Context mContext;

    private DialogHelper() {

    }

    public static synchronized DialogHelper getInstance(Context context) {
        mContext = context;
        if (null == dialogHelper) dialogHelper = new DialogHelper();
        return dialogHelper;
    }

    /**
     * 显示处理进度对话框
     */
//    public void showOverRailDialog(String msg) {
//        OverRailDialog dialog = new OverRailDialog(mContext, msg);
//        dialog.show();
//
//    }

    /**
     * 展示日期控件
     */
    public void showTrackingDialog(boolean isStartTime,String time) {
        TrackingDialog dialog = new TrackingDialog(mContext,isStartTime,time);
        dialog.show();
    }
}
