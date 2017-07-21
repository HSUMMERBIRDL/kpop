package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.view.View;

import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.AddressItemVO;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class AddressListAdapter extends CommonRecycleViewAdapter<AddressItemVO> {

    private OnActionListener listener;

    public AddressListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolderHelper helper, final AddressItemVO addressItem) {

        if(null != addressItem){
//            helper.setText(R.id.txt_phone,"电话:" + addressItem.getPhone());
//            helper.setText(R.id.txt_name,"姓名:" + addressItem.getName());
//            helper.setText(R.id.txt_dep_name,"地区:" + addressItem.getDepName());

            helper.setText(R.id.txt_phone,addressItem.getPhone());
            helper.setText(R.id.txt_name,addressItem.getName());
            helper.setText(R.id.txt_dep_name,addressItem.getDepName());
        }

        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(null != listener){
                    listener.onItemClick(addressItem.getPhone());
                }
            }
        });
    }

;

    public void setOnActionListener(OnActionListener listener){
        this.listener = listener;
    }

   public interface OnActionListener{
        void onItemClick(String phoneNum);
    }

}
