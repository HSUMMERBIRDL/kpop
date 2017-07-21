package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.CunVO;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-16.
 */

public class CunListAdapter extends CommonRecycleViewAdapter<CunVO> {

    private OnItemClickListener listener;

    public CunListAdapter(Context context, int layoutId, List<CunVO> mDatass) {
        super(context, layoutId, mDatass);
    }

    public CunListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final CunVO cunVO) {
        helper.setText(R.id.txt_name, cunVO.getName());

        TextView view = helper.getView(R.id.txt_name);
        if (cunVO.isSelected()) {
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
                    if (!cunVO.isSelected()) {
                        listener.onItemClick(cunVO);
                    }
                }
            }
        });

        LogUtils.logi(TAG, "cunVO " + cunVO.getName() + " 是否被选中: " + cunVO.isSelected());
    }

//    private void setAllisSelected(boolean isSelected) {
//
//        if(!CollectionUtils.isNullOrEmpty(mDatas)){
//
//            for (CunVO cun: mDatas) {
//                cun.setSelected(isSelected);
//            }
//        }
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(CunVO cun);
    }
}
