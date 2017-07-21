package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.GatherItemVO;
import com.kp.monitor.ui.activity.GatherDetailActivity;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class GatherListAdapter extends CommonRecycleViewAdapter<GatherItemVO> {


    public GatherListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolderHelper helper, final GatherItemVO gather) {
        helper.setText(R.id.txt_gather_loc,gather.getLoc());
        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GatherDetailActivity.startAction(mContext,gather.getGatherId());
            }
        });
    }
}
