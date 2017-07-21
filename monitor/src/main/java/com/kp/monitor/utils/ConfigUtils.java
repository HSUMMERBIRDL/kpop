package com.kp.monitor.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hl.foundation.library.manager.AppManager;
import com.kp.monitor.basis.app.AppAplication;
import com.kp.monitor.basis.preference.PreferenceUtil;
import com.kp.monitor.ui.activity.MainActivity;

import java.util.Locale;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class ConfigUtils {

    public static final int LANGUAGE_CHINESE = 0;
    public static final int LANGUAGE_WEIWEN = 1; //  维吾尔族语


    /**
     * 转化语言环境
     */
    public static void switchLanguage(){

        int language = PreferenceUtil.getLanguage();

        PreferenceUtil.setLanguage(language);
        Resources resources =AppAplication.getAppContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if(language == LANGUAGE_CHINESE){
            config.locale = Locale.ENGLISH;
            language = LANGUAGE_WEIWEN;
        }else if( language == LANGUAGE_WEIWEN){
            config.locale = Locale.CHINESE;
            language = LANGUAGE_CHINESE;
        }
        PreferenceUtil.setLanguage(language);

        resources.updateConfiguration(config, dm);


        Activity activity = AppManager.getAppManager().currentActivity();
        Intent it = new Intent(activity, MainActivity.class); //MainActivity是你想要重启的activity
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);
    }


    public static  void setPastLanguage(){

        int language = PreferenceUtil.getLanguage();

        Resources resources =AppAplication.getAppContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if(language == LANGUAGE_CHINESE){
            config.locale = Locale.CHINESE;
        }else if( language == LANGUAGE_WEIWEN){
            config.locale = Locale.ENGLISH;
        }

        resources.updateConfiguration(config, dm);
    }
}
