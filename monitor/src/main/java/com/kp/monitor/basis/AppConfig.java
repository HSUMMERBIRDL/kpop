package com.kp.monitor.basis;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public class AppConfig {

    /*app*/
    public static final boolean isDebug = true;



    /*数据库*/
    public static final String DB_NAME = "monitor.db"; //  数据库名



    /*http*/

//    public static final String IP = "192.168.1.198";  //; 陈桂
//    public static final String IP = "192.168.1.145";  //; 洪伟
//    public static final String IP = "192.168.1.187";  //; 志华
//    public static final String IP = "192.168.1.20";  //; 申总
    public static final String IP = "192.168.1.40";  //; 41测试环境
    public static final String BASE_URL = "http://" + IP + ":8080/";
}
