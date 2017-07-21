package com.kp.monitor.ui.adapter;

import android.content.Context;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.OffWristVO;

/**
 * des:脱腕adapter
 * Created by HL
 * on 2017/2/14 0014.
 */

public class OffWristListAdapter extends CommonRecycleViewAdapter<OffWristVO> {

    public OffWristListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final OffWristVO offWristVO) {

        if(null != offWristVO){
            helper.setText(R.id.txt_time_info,offWristVO.getTime());
            helper.setText(R.id.txt_status_name,"发生脱腕异常");
        }

    }
}
