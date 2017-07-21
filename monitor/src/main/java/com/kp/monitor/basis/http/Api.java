package com.kp.monitor.basis.http;

import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.hl.foundation.frame.app.BaseApplication;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.NetWorkUtils;
import com.kp.monitor.basis.AppConfig;
import com.kp.monitor.service.ApiService;
import com.kp.monitor.service.UserService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public class Api {

    public static final String TAG = "HttpApi";
    public static final String TAG2 = "backdate";

    private static final int TYPE_COUNT = 2;

    private static final int TYPE_GENERAL = 0;
    private static final int TYPE_AUTHENTICATE = 1;  // 鉴权方式   需要在请求头添加token
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a 时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 1000 * 50;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 1000*10;

    public  Retrofit retrofit;

    private ApiService apiService;

    private static SparseArray<Api> apiManager = new SparseArray<>(TYPE_COUNT);


    private Api(int type){

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG,  message);
                LogUtils.logi(TAG2, "接口数据：" + message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = null;

        if(type == TYPE_AUTHENTICATE){
            //增加头部鉴权信息
            Interceptor headerInterceptor =new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            .addHeader(ApiConstants.AUTHORIZATION, UserService.getToken())
                            .addHeader("Content-Type","application/json;charset=UTF-8")
                            .addHeader("platform","ANDROID")
                            .build();
                    return chain.proceed(build);
                }
            };


            builder =  new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(logInterceptor);

        }else{

            Interceptor headerInterceptor =new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            .addHeader("Content-Type","application/json;charset=UTF-8")
                            .addHeader("platform","ANDROID")
                            .build();
                    return chain.proceed(build);
                }
            };

            builder =  new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(logInterceptor);
        }



        OkHttpClient okHttpClient =  builder.build();
        Gson gson = new Gson();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConfig.BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    /**
     * 不传参数返回请求头带token,即提供鉴权功能的service
     * @return
     */
    public static ApiService getApiService() {

      return getApiService(true);
    }
    public static String getCacheControl() {
        return NetWorkUtils.isNetConnected(BaseApplication.getAppContext()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }
    public static ApiService getApiService(final String token) {

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "接口数据：" + message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader(ApiConstants.AUTHORIZATION, token)
                        .addHeader("Content-Type","application/json;charset=UTF-8")
                        .addHeader("platform","ANDROID")
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient.Builder builder   =  new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
        OkHttpClient okHttpClient =  builder.build();

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConfig.BASE_URL)
                .build();

        return  retrofit.create(ApiService.class);
    }

    public static ApiService getApiService(boolean isAuth) {

        Api api = null;
        if(isAuth){
            api = apiManager.get(TYPE_AUTHENTICATE);
            if(null == api){
                api = new Api(TYPE_AUTHENTICATE);
                apiManager.put(TYPE_AUTHENTICATE,api);
            }
        }else{
            api = apiManager.get(TYPE_GENERAL);
            if(null == api){
                api = new Api(TYPE_GENERAL);
                apiManager.put(TYPE_GENERAL,api);
            }
        }
        return api.apiService;
    }

    /**
     * 销毁service
     */
    public static void destoryService(){

        apiManager.clear();
    }
}
