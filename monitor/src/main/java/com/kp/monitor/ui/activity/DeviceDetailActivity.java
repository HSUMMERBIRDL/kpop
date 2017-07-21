package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.hl.foundation.library.widget.statusbar.StatusBarUtil;
import com.kp.monitor.R;
import com.kp.monitor.contract.DeviceDetailContract;
import com.kp.monitor.data.vo.DeviceVO;
import com.kp.monitor.model.DeviceDetailModel;
import com.kp.monitor.presenter.DeviceDetailPresenter;
import com.kp.monitor.ui.BaseAppActivity;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class DeviceDetailActivity extends BaseAppActivity<DeviceDetailModel,
        DeviceDetailPresenter> implements DeviceDetailContract.View {


    public static final String DEVICE_ID = "device_id";

    private String deviceId = "";
    @Bind(R.id.device_number)
    TextView deviceNumber;

    @Bind(R.id.device_site)
    TextView deviceSite;

    @Bind(R.id.device_type)
    TextView deviceType;


    @Bind(R.id.storage_time)
    TextView storageTime;


    @Bind(R.id.device_status)
    TextView deviceStatus;
    @Bind(R.id.device_)
    TextView device;

    public static void startAction(Context context, String deviceId) {

        Intent intent = new Intent(context, DeviceDetailActivity.class);
        intent.putExtra(DEVICE_ID, deviceId);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {

        Intent intent = getIntent();
        deviceId = intent.getStringExtra(DEVICE_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_device;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, R.color.colorTheme);
        mPresenter.sendGetDeviceDetail(deviceId);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onGetDeviceDetailComplete(DeviceVO deviceVO) {
        if (null != deviceVO.status)
            if (deviceVO.status.equals("Y")) {
                deviceStatus.setText("已佩戴");
                deviceStatus.setBackgroundResource(R.drawable.shape_rec_green);
            } else if (deviceVO.status.equals("N")) {
                deviceStatus.setText("未绑定");
                deviceStatus.setBackgroundResource(R.drawable.shape_rec_red);
            }
        deviceNumber.setText("设备名称:" + deviceVO.deviceNumber);
        deviceType.setText("类型:" + deviceVO.type);
        deviceSite.setText("地区:" + deviceVO.deviceSite);
        storageTime.setText("入库时间:" + deviceVO.createTime);
        device.setText("设备编号:" + deviceVO.simNo);
    }
}
