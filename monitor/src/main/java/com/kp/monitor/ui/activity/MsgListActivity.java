package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.vo.MessageItemVO;
import com.kp.monitor.model.MessageListModel;
import com.kp.monitor.presenter.MessageListPresenter;
import com.kp.monitor.ui.adapter.MessageListAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * des: 消息列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class MsgListActivity extends BaseListActivity<MessageListModel, MessageListPresenter,
        MessageItemVO>{

    Map<String, Object> params = new HashMap<>();
    private MessageListAdapter adapter;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, MsgListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
    }


    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        adapter = new MessageListAdapter(mContext, R.layout
                .list_item_message);
        return adapter;
    }

    @Override
    protected String getTheTitle() {
        return ResourcesUtils.getResourceString(R.string.title_message_list);
    }

    @Override
    protected Map<String, Object> getAddParams() {


        params.put(ApiConstants.MESSAGE_TYPE, ApiConstants.MESSAGE_TYPE_MSG);


        return params;
    }


}
