package com.kp.monitor.basis.preference;

import com.hl.foundation.library.persis.PreferenceHelper;
import com.kp.monitor.basis.app.AppAplication;

/**
 * des:
 * Created by HL
 * on 2017-05-03.
 */

public class PreferenceUtil {

    private static int language;

    public static void setToken(String token) {
        PreferenceHelper.write(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.TOKEN,
                token);
    }

    public static String getToken() {
        return PreferenceHelper.readString(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.TOKEN,
                "");
    }

    public static void setUserId(String token) {
        PreferenceHelper.write(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_ID,
                token);
    }

    public static String getGetUserId() {
        return PreferenceHelper.readString(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_ID,
                "");
    }

    public static void setUserName(String userName) {
        PreferenceHelper.write(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_NAME,
                userName);
    }

    public static String getGetUserName() {
        return PreferenceHelper.readString(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_NAME,
                "");
    }

    public static void setLanguage(int language) {
        PreferenceHelper.write(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.LANGUAGE,
                language);
    }

    public static int getLanguage() {
        return PreferenceHelper.readInt(
                AppAplication.getAppContext(),
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.LANGUAGE,
                0);
    }

    /*用法举例*/

//    /**
//     * 保存用户id
//     * @param userId
//     */
//    public static void setAuthCode(String userId) {
//        PreferenceHelper.write(
//                AppAplication.getAppContext(),
//                PreferenceConfig.PREFERENCE_COMMON,
//                PreferenceConfig.AUTH_CODE,
//                userId);
//    }
//
//    /**
//     * 获取用户id
//     */
//    public static String getAuthCode() {
//        return PreferenceHelper.readString(
//                AppAplication.getAppContext(),
//                PreferenceConfig.PREFERENCE_COMMON,
//                PreferenceConfig.AUTH_CODE,
//                null);
//    }
}
