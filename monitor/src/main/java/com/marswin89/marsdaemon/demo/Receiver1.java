package com.marswin89.marsdaemon.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by ${Stephen} on 2017-05-17.
 */

public class Receiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //如果是去电
        if (!intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String phoneNumber = intent.getExtras().getString(TelephonyManager
                    .EXTRA_INCOMING_NUMBER);
            //空闲状态
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Intent idleIntent = new Intent();
                idleIntent.setAction("com.monitor.audiorecord");
                idleIntent.putExtra("phoneNumber", phoneNumber);
                idleIntent.putExtra("telePhoneState", 1);
                context.sendBroadcast(idleIntent);
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Intent idleIntent = new Intent();
                idleIntent.setAction("com.monitor.audiorecord");
                idleIntent.putExtra("phoneNumber", phoneNumber);
                idleIntent.putExtra("telePhoneState", 2);
                context.sendBroadcast(idleIntent);
                //摘机状态
            } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                //来电响铃状态

                Intent idleIntent = new Intent();
                idleIntent.setAction("com.monitor.audiorecord");
                idleIntent.putExtra("phoneNumber", phoneNumber);
                idleIntent.putExtra("telePhoneState", 3);
                context.sendBroadcast(idleIntent);
            }
        }
    }


}
