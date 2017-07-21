package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.DeviceItemVO;
import com.kp.monitor.ui.activity.DeviceDetailActivity;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class DeviceListAdapter extends CommonRecycleViewAdapter<DeviceItemVO> {


    public DeviceListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolderHelper helper, final DeviceItemVO member) {
        helper.setText(R.id.txt_device_num, member.deviceNum);
        helper.setText(R.id.txt_device_type, member.deviceType);
        helper.setText(R.id.txt_device_site, member.deviceSite);
        String deviceState = member.deviceState;

        TextView txtStateView = helper.getView(R.id.txt_device_state);
        String state = "未知";
        if (null != deviceState)
            if (deviceState.equals("Y")) {
                state = "已佩戴";
                txtStateView.setTextColor(ResourcesUtils.getColor(R.color.colorTxtGray));
//                txtStateView.setBackgroundResource(R.drawable.shape_rec_green);
            } else if (deviceState.equals("N")) {
                txtStateView.setTextColor(ResourcesUtils.getColor(R.color.colorTxtRed));
                state = "未绑定";
//                txtStateView.setBackgroundResource(R.drawable.shape_rec_red);
            }
//        else if (deviceState.equals("B")) {
//            txtStateView.setBackgroundResource(R.drawable.shape_rec_green);
//            state = "损坏";
//        } else if (deviceState.equals("D")) {
//            txtStateView.setBackgroundResource(R.drawable.shape_rec_green);
//            state = "已删除";
//        }


        helper.setText(R.id.txt_device_state, state);

        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceDetailActivity.startAction(mContext, member.deviceId);
            }
        });
    }
}
