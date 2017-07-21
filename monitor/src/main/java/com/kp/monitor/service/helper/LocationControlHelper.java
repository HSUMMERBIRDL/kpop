package com.kp.monitor.service.helper;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hl.foundation.frame.app.BaseApplication;

/**
 * Created by ${Stephen} on 2017-06-01.
 */

public class LocationControlHelper implements AMapLocationListener {
    private AMapLocationClientOption option;
    private AMapLocationClient aMapLocationClient;

    private LocationControlHelper() {
        option = new AMapLocationClientOption();
        option.setNeedAddress(true);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setInterval(5000);
        aMapLocationClient = new AMapLocationClient(BaseApplication
                .getAppContext());
        aMapLocationClient.setLocationListener(this);
        aMapLocationClient.setLocationOption(option);
    }

    private static LocationControlHelper locationControlHelper;

    public static synchronized LocationControlHelper getInstance() {
        if (null == locationControlHelper) {
            locationControlHelper = new LocationControlHelper();
        }
        return locationControlHelper;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        aMapLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                System.out.print(aMapLocation);
            }
        }
    }
}
