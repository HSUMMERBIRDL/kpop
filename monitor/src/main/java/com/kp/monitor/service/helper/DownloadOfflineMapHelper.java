package com.kp.monitor.service.helper;

import android.content.Context;

import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;

/**
 * Created by ${Stephen} on 2017-05-31.
 * 离线地图下载类
 */

public class DownloadOfflineMapHelper implements OfflineMapManager.OfflineMapDownloadListener {
    private static DownloadOfflineMapHelper offlineMapHelper;
    private OfflineMapManager offlineMapManager;

    private DownloadOfflineMapHelper(Context context) {
        if (null == offlineMapManager) {
            offlineMapManager = new OfflineMapManager(context, this);
        }
    }

    public static synchronized DownloadOfflineMapHelper getInstance(Context context) {
        if (null == offlineMapHelper) {
            offlineMapHelper = new DownloadOfflineMapHelper(context);
        }
        return offlineMapHelper;
    }

    /**
     * 通过城市名称检查是否有最新的离线城市
     *
     * @param cityName
     */
    public void checkHasNewOfflineCityByName(String cityName) {
        try {
            offlineMapManager.updateOfflineCityByName(cityName);
        } catch (AMapException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过城市编码检查
     *
     * @param cityCode
     */
    public void checkHasNewOfflineCityByCityCode(String cityCode) {
        try {
            offlineMapManager.updateOfflineCityByCode(cityCode);
        } catch (AMapException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过省份名称检查更新
     *
     * @param provinceName
     */
    public void checkHasNewOfflineCityByProvince(String provinceName) {
        try {
            offlineMapManager.updateOfflineMapProvinceByName(provinceName);
        } catch (AMapException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据城市名称下载
     *
     * @param cityName
     */
    public void downloadByCityName(String cityName) {
        offlineMapHelper.downloadByCityName(cityName);
    }

    /**
     * 根据省会名称下载
     *
     * @param provinceName
     */
    public void downloadByProvinceName(String provinceName) {
        offlineMapHelper.downloadByProvinceName(provinceName);
    }

    /**
     * 根据城市编码
     *
     * @param cityCode
     */
    public void downloadByCityCode(String cityCode) {
        offlineMapHelper.downloadByCityCode(cityCode);

    }

    @Override
    public void onDownload(int status, int completeCode, String name) {

    }

    @Override
    public void onCheckUpdate(boolean hasNew, String name) {
        if (hasNew) {
            downloadByCityName(name);
        }
    }

    @Override
    public void onRemove(boolean success, String name, String describe) {

    }
}
