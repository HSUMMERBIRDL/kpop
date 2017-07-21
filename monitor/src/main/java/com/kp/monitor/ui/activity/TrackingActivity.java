package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.TrakingVO;
import com.kp.monitor.model.TrakingListModel;
import com.kp.monitor.presenter.TrackingPresenter;
import com.kp.monitor.ui.adapter.TrakingAdapter;

import butterknife.Bind;

/**
 * Created by ${Stephen} on 2017-06-06.
 * 轨迹追踪
 */

public class TrackingActivity extends BaseListActivity<TrakingListModel, TrackingPresenter,
        TrakingVO> {
    @Bind(R.id.ll_search_bar)
    LinearLayout ll_search_bar;
    public static void startAction(Context context) {
        Intent intent = new Intent(context, TrackingActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void initEvents() {
        ll_search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchDeviceActivity.startAction(mContext);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traking;
    }


    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new TrakingAdapter(this, R.layout.tracking_adapter);
    }

    @Override
    protected String getTheTitle() {
        return ResourcesUtils.getResourceString(R.string.tracking_title);
    }
}
