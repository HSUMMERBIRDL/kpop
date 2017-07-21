package com.kp.monitor.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kp.monitor.R;
import com.kp.monitor.service.RecordService;
import com.kp.monitor.ui.widget.FunctionGridView;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by ${Stephen} on 2017-05-10.
 */

public class FunctionAdapter extends BaseAdapter implements Badge.OnDragStateChangedListener {
    private Context mContext;
    private String functions[] = {"人员管理", "通讯录", "通知公告", "设备查询", "轨迹追踪", "监听"};
    private int images[] = {R.drawable.ic_member_manager,R.drawable.ic_address_manager,
            R.drawable.ic_notice_manager,R.drawable.ic_device_manager,R.drawable.ic_trail_manager,R.drawable.ic_moniter_manager};
    private FunctionGridView gridView;
    ImageView functionText;
    ImageView functionTextMoniter;

    Badge badge;
    Badge badgeMoniter; // 监听上面的红点

    public FunctionAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return functions.length;
    }

    @Override
    public Object getItem(int i) {
        return functions[i];
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.function_adapter, null);
            mHolder.function_image = (ImageView) convertView.findViewById(R.id.function_image);
            mHolder.function_text = (TextView) convertView.findViewById(R.id.function_text);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.function_text.setText(functions[position]);
        mHolder.function_image.setImageResource(images[position]);

        if(position == 5){ // 之所以要到这里post监听个数 是因为在mainactivity里面发送的话 gridview里面的item还是空的 导致不能刷新 并且有可能导致订阅的事件由于发生了异常而后面当事件发送时不再推送过来
            RecordService.getInstance().postUploadFileSize();
        }
        return convertView;
    }

    public void setGridView(FunctionGridView gridView) {
        this.gridView = gridView;
    }

    public void addBageView(int number) {
        int firstVisiblePosition = gridView.getFirstVisiblePosition();
        if (null == functionText) {
            View view = gridView.getChildAt(2 - firstVisiblePosition);
            functionText = (ImageView) view.findViewById(R.id.function_image);
            badge = new QBadgeView(mContext).bindTarget(functionText).setBadgeBackgroundColor
                    (Color.RED).setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(2, 5,
                    true);
            badge.setOnDragStateChangedListener(this);
        }
        badge.setBadgeNumber(number);
    }

    public void addBageViewToMoniterView(int number) {
        int firstVisiblePosition = gridView.getFirstVisiblePosition();
        if (null == functionTextMoniter) {
            View view = gridView.getChildAt(5 - firstVisiblePosition);
            if(null != view){
                functionTextMoniter = (ImageView) view.findViewById(R.id.function_image);
                badgeMoniter = new QBadgeView(mContext).bindTarget(functionTextMoniter).setBadgeBackgroundColor
                        (Color.RED).setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(2, 5,
                        true);
            }
        }
        badgeMoniter.setBadgeNumber(number);
    }

    @Override
    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
    }

    private class ViewHolder {
        public ImageView function_image;
        public TextView function_text;
    }
}
