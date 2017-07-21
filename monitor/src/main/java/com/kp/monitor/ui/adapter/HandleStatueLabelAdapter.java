package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.ResourcesUtils;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.UnusualHandleStatue;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * des: 异常情况处理状态adapter
 * Created by HL
 * on 2017-05-31.
 */

public class HandleStatueLabelAdapter extends TagAdapter<UnusualHandleStatue>{

    private static final String TAG = HandleStatueLabelAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;


    public HandleStatueLabelAdapter(List<UnusualHandleStatue> datas, Context context, FlowLayout mFlowLayout) {

        super(datas);
        this.context = context;
        mInflater = LayoutInflater.from(this.context);
        this.mFlowLayout = mFlowLayout;
    }

    @Override
    public View getView(FlowLayout parent, int position, final UnusualHandleStatue statue) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_tag, mFlowLayout, false);
        tv.setText(statue.getHandleDes());

        boolean selected = statue.isSelected();
        if(selected){
            tv.setBackgroundResource(R.drawable.bg_shape_corner_blue);
            tv.setTextColor(ResourcesUtils.getColor(R.color.colorTxtBlue));

        }else{
            tv.setBackgroundResource(R.drawable.bg_shape_corner_blue);
            tv.setBackgroundResource(R.drawable.bg_shape_corner_gray);
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(null != listener){
                    listener.onItemClick(statue);
                }
            }
        });

        return tv;
    }

    public OnActionListener listener;

    public void setOnActionListener(OnActionListener listener){
        this.listener = listener;
    }

    public interface OnActionListener{

        void onItemClick(UnusualHandleStatue statue);
    }
}
