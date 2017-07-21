package com.kp.monitor.service.helper;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by ${Stephen} on 2017-06-02.
 * 获取设备宽高
 */

public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    public int height;

    private DeviceHelper() {
    }

    public static synchronized DeviceHelper getInstance() {
        if (null == deviceHelper) deviceHelper = new DeviceHelper();
        return deviceHelper;
    }

    public void getDisplay(Activity mainActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int densityDpi = displayMetrics.densityDpi;
        height = displayMetrics.heightPixels;
//        Toast.makeText(mainActivity, displayMetrics.widthPixels + "  " + displayMetrics
//                .heightPixels +
//                "  " + densityDpi + "  " + displayMetrics.density, Toast.LENGTH_SHORT).show();
    }

    public int getHeight() {
        return height;
    }
}
