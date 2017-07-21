package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.widget.ToastUitl;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.TrakingVO;
import com.kp.monitor.ui.activity.TrakingDetailActivity;

/**
 * Created by ${Stephen} on 2017-06-07.
 */

public class TrakingAdapter extends CommonRecycleViewAdapter<TrakingVO> {
    public TrakingAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolderHelper helper, final TrakingVO trakingVO) {
        if (trakingVO.sex.equals("男")) {
            helper.setImageResource(R.id.img_header, R.drawable.header_defult_men);
        } else {
            helper.setImageResource(R.id.img_header, R.drawable.header_defult_women);
        }

        helper.setText(R.id.txt_name, trakingVO.name);
        helper.setText(R.id.txt_type, trakingVO.category);
        helper.setText(R.id.txt_sex, trakingVO.sex);
        helper.setText(R.id.txt_status, trakingVO.status);


        helper.setText(R.id.txt_card_id, "身份证：" + trakingVO.cardId);

        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!trakingVO.status.equals("未监控")) {
                    TrakingDetailActivity.startAction(mContext, trakingVO.monitorDeviceId, trakingVO
                            .monitorPersonId);
                } else {
                    ToastUitl.show("未有详细轨迹", 0);
                }

            }
        });
    }
}
