package com.kp.monitor.ui.adapter;

import android.content.Context;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.LowPowerVO;

/**
 * des:低电adapter
 * Created by HL
 * on 2017/2/14 0014.
 */

public class LowPowerListAdapter extends CommonRecycleViewAdapter<LowPowerVO> {

    public LowPowerListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final LowPowerVO lowPowerVO) {

        if(null != lowPowerVO){
            helper.setText(R.id.txt_time_info,lowPowerVO.getTime() + " ");
            helper.setText(R.id.txt_power,"发生掉线异常 \n电量:" + lowPowerVO.getPower());
        }
    }
}
