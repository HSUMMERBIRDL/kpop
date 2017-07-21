package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.hl.foundation.library.utils.AndroidUtils;
import com.kp.monitor.R;
import com.kp.monitor.ui.BaseAppActivity;

import butterknife.Bind;

/**
 * des: 版本信息界面
 * Created by HL
 * on 2017-06-05.
 */

public class VersionInfoActivity extends BaseAppActivity {

    @Bind(R.id.txt_version_info)
    TextView txtVersionInfo;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, VersionInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_version_info;
    }

    @Override
    protected void initViews() {
        txtVersionInfo.setText("当前版本:" + AndroidUtils.getLocalVersion(mContext));

    }

    @Override
    protected void initEvents() {

    }

}
