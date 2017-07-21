package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.ViewHolderHelper;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.MessageItemVO;
import com.kp.monitor.ui.activity.MsgDetailActivity;

/**
 * des:
 * Created by HL
 * on 2017/2/14 0014.
 */

public class MessageListAdapter extends CommonRecycleViewAdapter<MessageItemVO> {


    public MessageListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(final ViewHolderHelper helper, final MessageItemVO messageItem) {
        helper.setText(R.id.txt_time, messageItem.time);
        helper.setText(R.id.txt_news_title, messageItem.newsTitle);

        String newsContent = messageItem.newsContent;
        if (StringUtils.isEmpty(newsContent)) {
            helper.setVisible(R.id.txt_news_content, false);
        } else {
            helper.setVisible(R.id.txt_news_content, true);
            helper.setText(R.id.txt_news_content, newsContent);
        }


        helper.setText(R.id.txt_status, messageItem.status);
        TextView txtStatus = helper.getView(R.id.txt_status);
        if (messageItem.status.equals("未读")) {
            txtStatus.setTextColor(Color.RED);
        } else {
            txtStatus.setTextColor(Color.GRAY);
        }
        TextView newsTitle = helper.getView(R.id.txt_news_title);
        newsTitle.getPaint().setFakeBoldText(true);
        newsTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        helper.setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = helper.getItemPosition();
                MsgDetailActivity.startAction(mContext, messageItem,itemPosition);
            }
        });
    }
}
