package com.kp.monitor.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hl.foundation.library.utils.ResourcesUtils;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.PersonNumberVo;
import com.kp.monitor.ui.widget.UnusualGridView;

/**
 * Created by ${Stephen} on 2017-05-10.
 */

public class UnusualAdapter extends BaseAdapter {
    private String titles[] = {"总人数", "异常情况", "聚集风险", "0", "0", "0"};
    private Context mContext;
    private UnusualGridView gridView;
    private  TextView totalText = null, unusualText = null, gatherText = null;
    public UnusualAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setGridView(UnusualGridView gridView) {
        this.gridView = gridView;
    }

    /**
     * update listview 单条数据
     */
    public void updateItemData(PersonNumberVo personNumberVo) {
        Message msg = Message.obtain();
        msg.obj = personNumberVo;
        mHandler.sendMessage(msg);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            PersonNumberVo personNumberVo = (PersonNumberVo) msg.obj;
            updateItem(personNumberVo);
        }
    };

    /**
     * 刷新指定item
     */
    private void updateItem(PersonNumberVo personNumberVo) {
        if (null == gridView) {
            return;
        }
        // 获取当前可以看到的item位置
        int visiblePosition = gridView.getFirstVisiblePosition();
        // 如添加headerview后 firstview就是hearderview
        // 所有索引+1 取第一个view
        // View view = listview.getChildAt(index - visiblePosition + 1);
        // 获取点击的view

        if (null == totalText) {
            View totalView = gridView.getChildAt(3 - visiblePosition);
            totalText = (TextView) totalView.findViewById(R.id.unusual_text);
//            totalText.setTextSize(14);
        }
        totalText.setText(personNumberVo.totalPersonNumber + "");

        if (null == unusualText) {
            View unusualView = gridView.getChildAt(4 - visiblePosition);
            unusualText = (TextView) unusualView.findViewById(R.id.unusual_text);
            unusualText.setTextColor(Color.RED);
//            unusualText.setTextSize(14);
        }
        unusualText.setText(personNumberVo.unusualPersonNumber + "");

        if (null == gatherText) {
            View gatherView = gridView.getChildAt(5 - visiblePosition);
            gatherText = (TextView) gatherView.findViewById(R.id.unusual_text);
            gatherText.setTextColor(Color.RED);
//            gatherText.setTextSize(14);
        }
        gatherText.setText(personNumberVo.gatherPersonNumber + "");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        UnusualAdapter.ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new UnusualAdapter.ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate( R.layout.unusualadapter, null);
            mHolder.unusual_text = (TextView) convertView.findViewById(R.id.unusual_text);
            convertView.setTag(mHolder);
        } else {
            mHolder = (UnusualAdapter.ViewHolder) convertView.getTag();
        }

//        mHolder.unusual_text.setTextColor(ResourcesUtils.getColor(R.color.red));
//        mHolder.unusual_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
//        TextPaint paint = mHolder.unusual_text.getPaint();
//        paint.setFakeBoldText(true);

        if((4 == position) || (5 == position)){
            mHolder.unusual_text.setTextColor(ResourcesUtils.getColor(R.color.red));
//            mHolder.unusual_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
            TextPaint paint = mHolder.unusual_text.getPaint();
            paint.setFakeBoldText(true);
        }

//        else{
//
//            mHolder.unusual_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
//        }
//        else if(5 == position){
//            mHolder.unusual_text.setTextColor(ResourcesUtils.getColor(R.color.red));
//            mHolder.unusual_text.setTextSize(18);
//            TextPaint paint = mHolder.unusual_text.getPaint();
//            paint.setFakeBoldText(true);
//        }

        mHolder.unusual_text.setText(titles[position]);
        return convertView;
    }

    private class ViewHolder {
        public TextView unusual_text;
    }
}
