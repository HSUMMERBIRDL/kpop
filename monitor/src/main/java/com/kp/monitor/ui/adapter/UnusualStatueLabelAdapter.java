package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.ResourcesUtils;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.UnusualStatue;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * des: 异常状态标签adapter
 * Created by HL
 * on 2017-05-31.
 */

public class UnusualStatueLabelAdapter extends TagAdapter<UnusualStatue>{

    private static final String TAG = UnusualStatueLabelAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;


    public UnusualStatueLabelAdapter(List<UnusualStatue> datas, Context context, FlowLayout mFlowLayout) {

        super(datas);
        this.context = context;
        mInflater = LayoutInflater.from(this.context);
        this.mFlowLayout = mFlowLayout;
    }


    @Override
    public View getView(FlowLayout parent, int position, final UnusualStatue unusualStatue) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_tag, mFlowLayout, false);
        tv.setText(unusualStatue.getDes());

        boolean selected = unusualStatue.isSelected();
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
                    listener.onItemClick(unusualStatue);
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

        void onItemClick(UnusualStatue statue);
    }
}
