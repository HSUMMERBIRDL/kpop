package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ToastUitl;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.MemberItemVO;
import com.kp.monitor.ui.activity.MemberDetailActivity;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class MemberListAdapter extends CommonRecycleViewAdapter<MemberItemVO> {


    private boolean isAttachMonitorCommand;

    public MemberListAdapter(Context context, int layoutId,boolean isAttachMonitorCommand) {
        super(context, layoutId);
        this.isAttachMonitorCommand = isAttachMonitorCommand;
    }


    @Override
    public void convert(ViewHolderHelper helper, final MemberItemVO member) {

        String sex = member.getSex();

        TextView txtStatuView = helper.getView(R.id.txt_status);

        helper.setText(R.id.txt_name,member.getName());
        helper.setText(R.id.txt_status,member.getStatue());
        helper.setText(R.id.txt_sex,sex);
        helper.setText(R.id.txt_type,member.getCategory());
        helper.setText(R.id.txt_card_id,"身份证号:" + member.getCardId());

        TextView sexView = helper.getView(R.id.txt_sex);

//        if(member.isInMoniter()){
//            txtStatuView.setBackgroundResource(R.drawable.shape_rec_green);
//        }else{
//            txtStatuView.setBackgroundResource(R.drawable.shape_rec_red);
//        }

        if(StringUtils.equals(sex,"女")){
            helper.setImageResource(R.id.img_header,R.drawable.header_defult_women);

            Drawable sexDrawable = mContext.getResources().getDrawable(R.drawable.ic_sex_women);
            sexDrawable.setBounds(0, 0, sexDrawable.getMinimumWidth(), sexDrawable.getMinimumHeight());
            sexView.setCompoundDrawables(sexDrawable, null, null, null);
        }else{
            helper.setImageResource(R.id.img_header,R.drawable.header_defult_men);

            Drawable sexDrawable = mContext.getResources().getDrawable(R.drawable.ic_sex_men);
            sexDrawable.setBounds(0, 0, sexDrawable.getMinimumWidth(), sexDrawable.getMinimumHeight());
            sexView.setCompoundDrawables(sexDrawable, null, null, null);
        }

        if(member.isInMoniter()){
            helper.setVisible(R.id.txt_device_num,true);
            helper.setText(R.id.txt_device_num,"设备编号:" + member.getDeviceNum());
        }else{
            helper.setVisible(R.id.txt_device_num,false);
        }


        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isAttachMonitorCommand){
                    ToastUitl.showShort("监听记录页面");
                }else {
                    MemberDetailActivity.startAction(mContext,member.getMemberId());
                }

            }
        });


        if(isAttachMonitorCommand){
            helper.setVisible(R.id.txt_send_moniter_command,true);
            helper.setOnClickListener(R.id.txt_send_moniter_command, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ToastUitl.showShort("已经发送");
                }
            });
        }else {
            helper.setVisible(R.id.txt_send_moniter_command,false);
        }
    }
}
