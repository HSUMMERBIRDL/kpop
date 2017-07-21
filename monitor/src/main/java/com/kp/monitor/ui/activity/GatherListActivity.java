package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.GatherItemVO;
import com.kp.monitor.model.GatherListModel;
import com.kp.monitor.presenter.GatherListPresenter;
import com.kp.monitor.ui.adapter.GatherListAdapter;

/**
 * des: 人员列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class GatherListActivity extends BaseListActivity<GatherListModel,GatherListPresenter,GatherItemVO>{


    public static void startAction(Context context) {

        Intent intent = new Intent(context, GatherListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new GatherListAdapter(mContext, R.layout.list_item_gather);
    }

    @Override
    protected String getTheTitle() {
        return ResourcesUtils.getResourceString(R.string.title_gather_list);
    }
}
