package com.kp.monitor.ui.adapter;

import android.content.Context;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.OffLineVO;

/**
 * des:掉线adapter
 * Created by HL
 * on 2017/2/14 0014.
 */

public class OffLineListAdapter extends CommonRecycleViewAdapter<OffLineVO> {

    public OffLineListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final OffLineVO offLineVO) {

        if(null != offLineVO){
            helper.setText(R.id.txt_time_info,offLineVO.getTime());
            helper.setText(R.id.txt_status_name,"发生掉线异常");
        }
    }
}
