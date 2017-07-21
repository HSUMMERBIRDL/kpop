package com.kp.monitor.service.helper;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import com.amap.api.maps.model.LatLng;
import com.kp.monitor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ${Stephen} on 2017-06-03.
 * 通用helper
 */

public class GeneralHelper {
    private static GeneralHelper generalHelper;
    private List<LatLng> latLngs = new ArrayList<>();
    private TranslateAnimation downTranslateAnimation;
    private TranslateAnimation topTranslateAnimation;
    private AlphaAnimation alphaAnimation;
    private AnimationSet animationSet;

    private GeneralHelper() {
    }

    public static synchronized GeneralHelper getInstance() {
        if (null == generalHelper) {
            generalHelper = new GeneralHelper();
        }
        return generalHelper;
    }

    /**
     * 根据 date得到实际年龄
     *
     * @param birthDay
     * @return
     */
    public int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    public List<LatLng> strToLng(String coordinates) {

        String[] split = coordinates.split("\\[");
        latLngs.clear();
        for (int i = 0; i < split.length; i++) {
            String lngChar = split[i];
            if (!lngChar.equals("")) {
                String[] split1 = lngChar.split("\\]");

                for (int j = 0; j < split1.length; j++) {
                    if (!split1[j].equals(",")) {
                        String s = split1[j];
                        String[] split2 = s.split(",");
                        String s1 = split2[0];
                        double lng = Double.parseDouble(s1);
                        String s2 = split2[1];
                        double lat = Double.parseDouble(s2);
                        LatLng latLng1 = new LatLng(lat, lng);
                        latLngs.add(latLng1);
                    }
                }
            }
        }
        return latLngs;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public int getDaysByYearMonth(long time) {

        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(time);
        int month = (a.get(Calendar.MONTH)) + 1;
//        int year = a.get(Calendar.YEAR);
//        a.set(Calendar.YEAR, year);
//        a.set(Calendar.MONTH, month - 1);
//        a.set(Calendar.DATE, 1);
//        a.roll(Calendar.DATE, -1);
//        int maxDate = a.get(Calendar.DATE);
        return month;

    }

    /**
     * 开启旋转动画
     *
     * @param view
     * @param context
     */
    public void startFlushAnimation(View view, Context context) {
        Animation circle_anim = AnimationUtils.loadAnimation(context, R.anim.flush_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
        circle_anim.setInterpolator(interpolator);
        if (circle_anim != null) {
            view.startAnimation(circle_anim);  //开始动画
        }
    }

    /**
     * 停止旋转动画
     *
     * @param view
     */
    public void stopFlushAnimation(View view) {
        view.clearAnimation();
    }

    /**
     * 向下动画
     *
     * @param view
     * @param titleHeight
     */
    public void startDownTranslateAnimation(View view, int titleHeight) {
        if (null == downTranslateAnimation)
            downTranslateAnimation = new TranslateAnimation(0, 0, 0, titleHeight);
        view.setVisibility(View.VISIBLE);
        downTranslateAnimation.setDuration(2000);
        downTranslateAnimation.setFillAfter(true);
        view.startAnimation(downTranslateAnimation);
    }

    /**
     * 向上动画
     *
     * @param view
     * @param titleHeight
     */
    public void startUpTranslateAnimation(final View view, int titleHeight) {
        //向上移动动画
        if (null == topTranslateAnimation)
            topTranslateAnimation = new TranslateAnimation(0, 0, titleHeight, 0);
        topTranslateAnimation.setDuration(2000);
        topTranslateAnimation.setFillAfter(true);

        //改变透明度
        if (null == alphaAnimation)
            alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);

        //两个动画添加到动画集合中
        if (null == animationSet)
            animationSet = new AnimationSet(true);
        animationSet.addAnimation(topTranslateAnimation);
        animationSet.addAnimation(alphaAnimation);

        view.startAnimation(animationSet);//开启动画
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
