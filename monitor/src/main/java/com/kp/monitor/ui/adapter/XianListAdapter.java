package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.XianVO;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-16.
 */

public class XianListAdapter extends CommonRecycleViewAdapter<XianVO> {

    private OnItemClickListener listener;

    public XianListAdapter(Context context, int layoutId, List<XianVO> mDatass) {
        super(context, layoutId, mDatass);
    }

    public XianListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final XianVO xianVO) {
        helper.setText(R.id.txt_name, xianVO.getName());

        TextView view = helper.getView(R.id.txt_name);

        if (xianVO.isSelected()) {
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

                    if (!xianVO.isSelected()) {
                        listener.onItemClick(xianVO);
                    }
                }
            }
        });
        LogUtils.logi(TAG, "xianVO " + xianVO.getName() + " 是否被选中: " + xianVO.isSelected());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(XianVO xian);
    }
}
