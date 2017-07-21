package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.UnusalItemVO;
import com.kp.monitor.ui.activity.LowPowerActivity;
import com.kp.monitor.ui.activity.OffLineActivity;
import com.kp.monitor.ui.activity.OffWristActivity;
import com.kp.monitor.ui.activity.OverRailActivity;
import com.kp.monitor.ui.activity.SeparateActivity;

import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class UnusalListAdapter extends CommonRecycleViewAdapter<UnusalItemVO> {


    public UnusalListAdapter(Context context, int layoutId, List<UnusalItemVO> mDatass) {
        super(context, layoutId, mDatass);
    }


    public UnusalListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, final UnusalItemVO unusalItemVO) {



        helper.setText(R.id.txt_name,unusalItemVO.getName());
        helper.setText(R.id.txt_unusual_statue,unusalItemVO.getExceptionTypeDes());

        TextView excetionCountTxt = helper.getView(R.id.txt_exception_count);
        Spanned excetionCount = Html.fromHtml("<font color='#000000'>发生次数：</font>" + "<font color='#da1f1f'> " + unusalItemVO.getExceptionCount() + "</font>");
        excetionCountTxt.setText(excetionCount);

        int dealStatus = unusalItemVO.getDealStatus();

        if(dealStatus == UnusalItemVO.DEAL_STATUS_ED){
            helper.setTextColor(R.id.txt_handle_type,R.color.colorTxtGray);
        }else{
            helper.setTextColor(R.id.txt_handle_type,R.color.colorTxtRed);
        }

        helper.setText(R.id.txt_handle_type,unusalItemVO.getDealStatusDes());
        helper.setText(R.id.txt_card_id,"身份证号:" + unusalItemVO.getCardId());
        helper.setText(R.id.txt_device_num,"设备编号:" + unusalItemVO.getDeviceId());

//        driverTypeTxt.setText(Html.fromHtml("<font color='#999999'>已选司机类型：</font>" + "<font color='#00bfff'> " + driverTypeName + "</font>"));


        final int exceptionType = unusalItemVO.getExceptionType();
        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_OVER ){

                    OverRailActivity.startAction(mContext,unusalItemVO.getExceptionId());
                }else if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_OFFLINE){

                    OffLineActivity.startAction(mContext,unusalItemVO.getExceptionId());
                }else if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_OFF_WRIST){
                    OffWristActivity.startAction(mContext,unusalItemVO.getExceptionId());
                }else if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_LOWER_POWER){
                    LowPowerActivity.startAction(mContext,unusalItemVO.getExceptionId());
                }else if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_LONG_LESS_MOVE){
                }else if(exceptionType == UnusalItemVO.EXCEPTION_TYPE_BASE_SEPARATION){
                    SeparateActivity.startAction(mContext,unusalItemVO.getExceptionId());

                }
            }
        });
    }
}
