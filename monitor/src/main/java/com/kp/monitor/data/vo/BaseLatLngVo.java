package com.kp.monitor.data.vo;

import com.amap.api.maps.model.LatLng;

import java.util.List;

/**
 * Created by ${Stephen} on 2017-06-13.
 */

public  class BaseLatLngVo {
    public double lat;//中心坐标纬度
    public double lng;//中心坐标经度
    public double radius; //半径
    public int type; //根据类型判断画圆还是不规则图形
    public List<LatLng> coordinatesLatLngs;//围栏坐标
    public List<LatLng> locations;// 轨迹坐标
}
