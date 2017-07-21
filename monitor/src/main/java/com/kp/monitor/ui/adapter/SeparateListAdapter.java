package com.kp.monitor.ui.adapter;

import android.content.Context;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.SeparateVO;

/**
 * des:人机分离adapter
 * Created by HL
 * on 2017/2/14 0014.
 */

public class SeparateListAdapter extends CommonRecycleViewAdapter<SeparateVO> {

    public SeparateListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final SeparateVO separateVO) {

        if(null != separateVO){
            helper.setText(R.id.txt_time_info,separateVO.getTime());
            helper.setText(R.id.txt_status_name,"发生底盒分离异常");
        }
    }
}
