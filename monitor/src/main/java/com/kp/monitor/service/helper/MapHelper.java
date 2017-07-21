package com.kp.monitor.service.helper;

import android.graphics.Color;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.hl.foundation.library.widget.ToastUitl;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.BaseLatLngVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ${Stephen} on 2017-06-02.
 * 地图辅助类
 */

public class MapHelper {
    private static MapHelper mapHelper;
    public AMap mAmap;
    private PolygonOptions polygonOptions;
    private List<Integer> colorList;
    private List<BitmapDescriptor> textureList;
    private List<BitmapDescriptor> bitmapDescriptors;
    private List<Integer> texIndexList;
    private PolylineOptions polylineOptions;

    private MapHelper() {
        colorList = new ArrayList<>();
        textureList = new ArrayList<>();
        bitmapDescriptors = new ArrayList<>();
        texIndexList = new ArrayList<>();
        polylineOptions = new PolylineOptions();
    }

    public static synchronized MapHelper getInstance() {
        if (null == mapHelper) mapHelper = new MapHelper();
        return mapHelper;
    }

    public void setMapView(AMap aMap) {
        mAmap = aMap;
    }


    public void addToMap(BaseLatLngVo baseLatLngVo, int color, int strokeColor, int topBarHeight,
                         int bottomHeight) {
        mAmap.clear();
        LatLng latLng = new LatLng(baseLatLngVo.lat, baseLatLngVo.lng);
        int type = baseLatLngVo.type;
        if (type == 0) { //画圆
            mAmap.addCircle(new CircleOptions().center(latLng).radius(baseLatLngVo.radius)
                    .fillColor(color)
                    .strokeColor(strokeColor)
                    .strokeWidth(10));
        } else {
            addPolygongraph(baseLatLngVo, strokeColor, color);
        }
        if (null != baseLatLngVo.locations) {
            addPolylinesWithTexture(baseLatLngVo.locations, topBarHeight, bottomHeight, latLng);
        } else {
            ToastUitl.show("尚未有轨迹数据", 0);
            mAmap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mAmap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory
                    .fromResource(R.mipmap.ic_tracking_home)));
        }
    }

    /**
     * 画不规则图形
     * 81.846829,41.798097  81.850226,41.798431  81.854088,41.797631  81.854392,41.795205
     * 81.852121,41.794165 81.84808,41.793818  81.845541,41.796031 81.84606,41.797551
     * 81.846793,41.797551
     *
     * @param overRailVO
     */
    public void addPolygongraph(BaseLatLngVo overRailVO, int strokeColor, int fillColor) {
        if (null == polygonOptions)
            polygonOptions = new PolygonOptions();
        for (int i = 0; i < overRailVO.coordinatesLatLngs.size(); i++) {
            LatLng latLng = overRailVO.coordinatesLatLngs.get(i);
            polygonOptions.add(latLng);
        }
        mAmap.addPolygon(polygonOptions.strokeWidth(4)
                .strokeColor(Color.argb(50, 1, 1, 1))
                .fillColor(Color.argb(50, 1, 1, 1)));
    }


    //绘制一条纹理线  表示轨迹
    private void addPolylinesWithTexture(List<LatLng> list, int top, int bottom, LatLng
            centerLatLng) {
//加入四个点

        LatLng firstLatLng = list.get(0);
        LatLng lastLatLng = list.get(list.size() - 1);

        mAmap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap
                .nova_map_point_start)).anchor(0.5f, 0.5f).position(firstLatLng).zIndex(1f));
        mAmap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap
                .nova_map_point_end)).position(lastLatLng).anchor(0.5f, 0.5f).zIndex(1f));
        mAmap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap
                .ic_tracking_home)).anchor(0.5f, 0.5f).position(centerLatLng));

        LatLngBounds build = LatLngBounds.builder().include(firstLatLng).include(lastLatLng)
                .include(centerLatLng).build();
        mAmap.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(build, 100, 100, top + 400,
                bottom + 400));

        //用一个数组来存放纹理
        List<BitmapDescriptor> texTuresList = new ArrayList<>();
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_alr));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.ic_custtexture));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_alr_night));

        //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色
        List<Integer> texIndexList = new ArrayList<Integer>();
        texIndexList.add(0);//对应上面的第0个纹理
        texIndexList.add(2);
        texIndexList.add(1);


        PolylineOptions options = new PolylineOptions();
        options.width(20);//设置宽度


        for (int i = 0; i < list.size(); i++) {
            LatLng latLng = list.get(i);
            options.add(latLng);
        }

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);

        //设置纹理对应的Index
        options.setCustomTextureIndex(texIndexList);

        mAmap.addPolyline(options);
    }
}
