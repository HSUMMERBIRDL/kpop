package com.kp.monitor.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hl.foundation.frame.dialog.BaseBottomDialog;
import com.hl.foundation.library.manager.InterfaceMgr;
import com.kp.monitor.R;
import com.kp.monitor.ui.widget.TrackingPickTimeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${Stephen} on 2017-06-09.
 */

public class TrackingDialog extends BaseBottomDialog implements View.OnClickListener,
        TrackingPickTimeView.onSelectedChangeListener {


    private SimpleDateFormat sdf, simpleDateFormat;
    @Bind(R.id.number_picker)
    TrackingPickTimeView numberPicker;
    @Bind(R.id.tx_time)
    TextView txTime;
    @Bind(R.id.tracking_dialog_cancle)
    Button cancle;
    @Bind(R.id.tracking_dialog_sure)
    Button sure;
    private boolean currentStatus;
    private String currentTx;
    private long timeMillis;

    public TrackingDialog(Context context, boolean isStartTime, String time) {
        super(context);
        try {
            currentStatus = isStartTime;
            ButterKnife.bind(this);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (currentStatus) {
                currentTx = "开始时间:";
            } else {
                currentTx = "结束时间:";
            }
            Date parse = sdf.parse(time);
            this.timeMillis = parse.getTime();
            String substring = time.substring(5, time.length());
            txTime.setText(currentTx + substring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        initLisener();
        numberPicker.setViewType(TrackingPickTimeView.TYPE_PICK_DATE);
        numberPicker.setTimeMillis(timeMillis);
    }

    @Override
    protected void windowPoy() {
        setContentView(R.layout.dialog_tracking);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(com.hl.foundation.R.style.pictureDialog);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        WindowManager.LayoutParams lp = window.getAttributes();
        int height = (int) (window.getWindowManager().getDefaultDisplay().getHeight() / 2);
        lp.x = 0;
        lp.height = height;
        lp.alpha = 9f;
        window.setAttributes(lp);
    }

    private void initLisener() {
        numberPicker.setOnSelectedChangeListener(this);
        cancle.setOnClickListener(this);
        sure.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tracking_dialog_cancle:
                cancel();
                break;
            case R.id.tracking_dialog_sure:
                InterfaceMgr.getInstance().chooseDate(currentStatus, timeMillis);
                cancel();
                break;
        }
    }

    @Override
    public void onSelected(TrackingPickTimeView view, long timeMillis) {
        if (null == simpleDateFormat)
            simpleDateFormat = new SimpleDateFormat("MM-dd");
        this.timeMillis = timeMillis;
        txTime.setText(currentTx + simpleDateFormat.format(timeMillis));
    }
}
