package com.kp.monitor.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kp.monitor.audio.AudioRecordFunc;
import com.kp.monitor.data.bean.Record;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${Stephen} on 2017-06-29.
 * 接受另一个进程广播
 */

public class AudioRecordBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.monitor.audiorecord")) {
            int telePhoneState = intent.getExtras().getInt("telePhoneState");
            String phoneNumber = intent.getExtras().getString("phoneNumber");
            if (null != phoneNumber) {

                switch (telePhoneState) {
                    case 1://挂断
                        Toast.makeText(context, "call 挂断" + phoneNumber, Toast.LENGTH_SHORT).show();
                        AudioRecordFunc.getInstance().stopRecordAndFile();

                        break;
                    case 2://接听
                        Toast.makeText(context, "call 接听" + phoneNumber, Toast.LENGTH_SHORT).show();
                        AudioRecordFunc.getInstance().startRecordAndFile(phoneNumber);

                        break;
                    case 3://响铃
                        Toast.makeText(context, "call 响铃状态" + phoneNumber, Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }

        }
    }

}
