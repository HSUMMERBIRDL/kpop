package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.LatLng;
import com.hl.foundation.library.inter.ChooseTrackingDateInte;
import com.hl.foundation.library.manager.InterfaceMgr;
import com.hl.foundation.library.widget.ToastUitl;
import com.hl.foundation.library.widget.statusbar.StatusBarUtil;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.contract.TrackingContract;
import com.kp.monitor.data.vo.BaseLatLngVo;
import com.kp.monitor.data.vo.TrakingDetailVO;
import com.kp.monitor.model.TrackingDetailModel;
import com.kp.monitor.presenter.TrackingPresenter;
import com.kp.monitor.presenter.TrakingDetailPresenter;
import com.kp.monitor.service.helper.DialogHelper;
import com.kp.monitor.service.helper.GeneralHelper;
import com.kp.monitor.service.helper.MapHelper;
import com.kp.monitor.ui.BaseAppActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by ${Stephen} on 2017-06-08.
 * 轨迹列表详情界面
 */

public class TrakingDetailActivity extends BaseAppActivity<TrackingDetailModel,
        TrakingDetailPresenter> implements TrackingContract.View, View.OnClickListener,
        ChooseTrackingDateInte {
    @Bind(R.id.linear_start_time)
    LinearLayout linearStartTime;

    @Bind(R.id.linear_end_time)
    LinearLayout linearEndTime;
    @Bind(R.id.time_linear)
    LinearLayout timeLinear;

    @Bind(R.id.start_time)
    TextView startTime;

    @Bind(R.id.end_time)
    TextView endTime;
    private SimpleDateFormat simpleDateFormat;
    private List<LatLng> latLngs = new ArrayList<>();
    private static final String DEVICEID = "deviceId";
    private static final String PERSONID = "personId";
    private String deviceId, personId;
    private AMap mMap;
    private String lngStartTime;
    private String lngEndTime;
    private Map<String, Object> map = new HashMap<>();

    public static void startAction(Context context, String deviceId, String personId) {

        Intent intent = new Intent(context, TrakingDetailActivity.class);
        intent.putExtra(DEVICEID, deviceId);
        intent.putExtra(PERSONID, personId);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
        deviceId = getIntent().getExtras().getString(DEVICEID, "");
        personId = getIntent().getExtras().getString(PERSONID, "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traking_detail;
    }

    //    "2017-06-02 10:09:10", "2017-06-08 10:09:10"
    @Override
    protected void initViews() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, R.color.colorTheme);
        if (null == simpleDateFormat) simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startFormat = simpleDateFormat.format(System.currentTimeMillis());
        startTime.setText(startFormat);
        lngStartTime = startFormat + " 00:00:00";
        lngEndTime = startFormat + " 23:59:59";
        endTime.setText(startFormat);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview)).getMap();
        MapHelper.getInstance().setMapView(mMap);
        map.put(ApiConstants.START_TIME, lngStartTime);
        map.put(ApiConstants.END_TIME, lngEndTime);
        map.put(ApiConstants.PERSONID, personId);
        map.put(ApiConstants.DEVICEID, deviceId);
        mPresenter.getTrakingDetailLocation(map);
    }

    @Override
    protected void initEvents() {
        linearStartTime.setOnClickListener(this);
        linearEndTime.setOnClickListener(this);
        InterfaceMgr.getInstance().setChooseTrackingDateLisener(this);
    }


    @Override
    public void onGetTrakingDetail(BaseLatLngVo trakingDetailVO) {
        latLngs.clear();
        int color = getResources().getColor(R.color.colorHalfTransparent);
        int color1 = getResources().getColor(R.color
                .colorBgSearch);
        if (null != trakingDetailVO)
            MapHelper.getInstance().addToMap(trakingDetailVO, color, color1, timeLinear.getHeight
                    (), 100);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_start_time:
                String startT = startTime.getText().toString();
                DialogHelper.getInstance(mContext).showTrackingDialog(true, startT);
                break;
            case R.id.linear_end_time:
                String endT = endTime.getText().toString();
                DialogHelper.getInstance(mContext).showTrackingDialog(false, endT);
                break;
        }
    }

    @Override
    public void chooseDate(boolean isStart, long time) {
        try {
            String startString = startTime.getText().toString();
            String endString = endTime.getText().toString();
            long startDate = simpleDateFormat.parse(startString).getTime();
            long endDate = simpleDateFormat.parse(endString).getTime();
            String formatDate = simpleDateFormat.format(time);

            int daysByYearMonth = GeneralHelper.getInstance().getDaysByYearMonth(time);
            if (isStart) {
                int startMonth = GeneralHelper.getInstance().getDaysByYearMonth(startDate);
                if (time > endDate || daysByYearMonth != startMonth) {
                    startTime.setText(formatDate);
                    endTime.setText(formatDate);
                } else {
                    startTime.setText(formatDate);
                }

                lngStartTime = formatDate + " 00:00:00";
            } else {
                int endMonth = GeneralHelper.getInstance().getDaysByYearMonth(endDate);
                if (time < startDate || daysByYearMonth != endMonth) {
                    startTime.setText(formatDate);
                    endTime.setText(formatDate);
                } else {
                    endTime.setText(formatDate);
                }
                lngEndTime = formatDate + " 23:59:59";
            }

            map.put(ApiConstants.START_TIME, lngStartTime);
            map.put(ApiConstants.END_TIME, lngEndTime);
            map.put(ApiConstants.PERSONID, personId);
            map.put(ApiConstants.DEVICEID, deviceId);
            mPresenter.getTrakingDetailLocation(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
