package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.XiangVO;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-16.
 */

public class XiangListAdapter extends CommonRecycleViewAdapter<XiangVO> {

    private OnItemClickListener listener;

    public XiangListAdapter(Context context, int layoutId, List<XiangVO> mDatass) {
        super(context, layoutId, mDatass);
    }

    public XiangListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final XiangVO xiangVO) {
        helper.setText(R.id.txt_name, xiangVO.getName());

        TextView view = helper.getView(R.id.txt_name);
        if (xiangVO.isSelected()) {
            view.setTextColor(ResourcesUtils.getColor(R.color.colorTxtGreen));
//            view.setBackgroundColor(ResourcesUtils.getColor(R.color.colorLightGray));
        } else {
            view.setTextColor(ResourcesUtils.getColor(R.color.colorTxtGray));
//            view.setBackgroundColor(ResourcesUtils.getColor(R.color.colorWhite));
        }

        helper.setOnClickListener(R.id.txt_name, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != listener) {
                    if (!xiangVO.isSelected()) {
                        listener.onItemClick(xiangVO);
                    }
                }
            }
        });


        LogUtils.logi(TAG, "xiangVO " + xiangVO.getName() + " 是否被选中: " + xiangVO.isSelected());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(XiangVO xiangVO);
    }
}
