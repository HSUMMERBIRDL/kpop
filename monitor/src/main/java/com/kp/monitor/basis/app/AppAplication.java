package com.kp.monitor.basis.app;

import android.content.Context;

import com.hl.foundation.frame.app.BaseApplication;
import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.basis.AppConfig;
import com.kp.monitor.basis.db.DBUtils;
import com.kp.monitor.utils.ConfigUtils;
import com.marswin89.marsdaemon.DaemonClient;
import com.marswin89.marsdaemon.DaemonConfigurations;
import com.marswin89.marsdaemon.demo.Receiver1;
import com.marswin89.marsdaemon.demo.Receiver2;
import com.marswin89.marsdaemon.demo.Service1;
import com.marswin89.marsdaemon.demo.Service2;

/**
 * des:
 * Created by HL
 * on 2017-05-03.
 */

public class AppAplication extends BaseApplication {
    private static AppAplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        LogUtils.logInit(AppConfig.isDebug);
        resetConfig();
        DBUtils.initDB(this);
    }


    /**
     * 回复以前的配置
     */
    private void resetConfig() {
        ConfigUtils.setPastLanguage();

    }

    public static Context getAppContext() {
        return baseApplication;
    }

    private DaemonClient mDaemonClient;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mDaemonClient = new DaemonClient(createDaemonConfigurations());
        mDaemonClient.onAttachBaseContext(base);
    }



    private DaemonConfigurations createDaemonConfigurations(){
        DaemonConfigurations.DaemonConfiguration configuration1 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:aa1",
                Service1.class.getCanonicalName(),
                Receiver1.class.getCanonicalName());
        DaemonConfigurations.DaemonConfiguration configuration2 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:aa2",
                Service2.class.getCanonicalName(),
                Receiver2.class.getCanonicalName());
        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        //return new DaemonConfigurations(configuration1, configuration2);//listener can be null
        return new DaemonConfigurations(configuration1, configuration2, listener);
    }


    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
        }

        @Override
        public void onWatchDaemonDaed() {
        }
    }
}
