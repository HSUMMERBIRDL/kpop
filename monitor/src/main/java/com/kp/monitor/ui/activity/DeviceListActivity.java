package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.MemonCache;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.DeviceItemVO;
import com.kp.monitor.model.DeviceListModel;
import com.kp.monitor.presenter.DeviceListPresenter;
import com.kp.monitor.ui.adapter.DeviceListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * des: 人员列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class DeviceListActivity extends BaseListActivity<DeviceListModel, DeviceListPresenter,
        DeviceItemVO> {


    public static final String DEVICE_TYPE = "device_type";

    public static final int DEVICE_TYPE_ALL = 0;  // 所有设备
    public static final int DEVICE_TYPE_UNUSUAL = 1; // 异常设备

    @Bind(R.id.ll_search_bar)
    LinearLayout llSearchBar;
    @Bind(R.id.txt_search)
    TextView txtSearch;

    private int deviceType = 0;

    public static void startAction(Context context, int deviceType) {
        Intent intent = new Intent(context, DeviceListActivity.class);
        intent.putExtra(DEVICE_TYPE, deviceType);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
        super.initDatas();

        Intent intent = getIntent();
        deviceType = intent.getIntExtra(DEVICE_TYPE, 0);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_list;
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        llSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchDeviceActivity.startAction(mContext);
            }
        });
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new DeviceListAdapter(mContext, R.layout.list_item_device);
    }

    @Override
    public void returnDataListData(List<DeviceItemVO> dataList) {
        super.returnDataListData(dataList);
        MemonCache.getIntance().list = dataList;
    }

    @Override
    protected Map<String, Object> getAddParams() {

        Map<String, Object> params = new HashMap<>();
//        params.put(ApiConstants.SEARCH_KEY, "");


        return params;
    }

    @Override
    protected String getTheTitle() {
        return ResourcesUtils.getResourceString(R.string.title_device_list);
    }

}
