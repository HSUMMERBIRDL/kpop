package com.marswin89.marsdaemon.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by ${Stephen} on 2017-05-17.
 */

public class Service2 extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return  Service.START_NOT_STICKY;
    }
}
