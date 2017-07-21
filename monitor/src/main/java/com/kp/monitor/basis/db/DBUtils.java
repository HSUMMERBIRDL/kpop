package com.kp.monitor.basis.db;

import android.content.Context;

import com.kp.monitor.basis.AppConfig;

/**
 * des:
 * Created by HL
 * on 2017-03-16.
 */

public class DBUtils {

//    private static DBUtils instance;

    private static DaoMaster.DevOpenHelper devOpenHelper;
    private static DaoSession daoSession;
    private static DaoMaster daoMaster;

//    public static DBUtils getInstance() {
//        if (instance == null) {
//            synchronized (DBUtils.class) {
//                if (instance == null) {
//                    instance = new DBUtils();
//                }
//            }
//        }
//        return instance;
//    }

    public static void initDB(Context context) {

        devOpenHelper = new DaoMaster.DevOpenHelper(context, AppConfig.DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();


    }

    public static UserDao getUserDao() {
        UserDao userDao = null;
        if (null != daoSession) {
            userDao = daoSession.getUserDao();
        }

        return userDao;
    }

    public static RecordFileDao getRecordFileDao() {
        RecordFileDao recordFileDao = null;
        if (null != daoSession) {
            recordFileDao = daoSession.getRecordFileDao();
        }

        return recordFileDao;
    }
}
