package com.kp.monitor.service;

import com.hl.foundation.library.manager.AppManager;
import com.hl.foundation.library.utils.NetWorkUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.SystemTool;
import com.kp.monitor.basis.db.DBUtils;
import com.kp.monitor.basis.db.UserDao;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.preference.PreferenceUtil;
import com.kp.monitor.data.dto.LoginDTO;
import com.kp.monitor.data.dto.UserDTO;
import com.kp.monitor.data.po.User;
import com.kp.monitor.data.request.LoginRequest;
import com.kp.monitor.data.vo.LoginVO;
import com.kp.monitor.data.vo.UserVO;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.VOFunc1;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * des: 管理账户的服务
 * Created by HL
 * on 2017-05-02.
 */

public class UserService {


    private static final java.lang.String TAG = UserService.class.getSimpleName();
    private static UserService INSTANCE;

    public static UserService getInstance() {
        if (null == INSTANCE) {
            synchronized (UserService.class) {
                if (null == INSTANCE) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    private Observable<UserVO> netUser;
    private Observable<UserVO> memoryUser;

    private UserVO userVO;

    /**
     * 登录 并保存相关登录信息
     *
     * @param username
     * @param pass
     * @return
     */
    public Observable login(final String username, String pass) {

        LoginRequest request = new LoginRequest();
        request.setUserName(username);
        request.setPassword(pass);

        int[] resolution = SystemTool.getResolution();
        request.setScreenResolution(resolution[0] + "*" + resolution[1]);

        request.setOperatingSystem("Android " + SystemTool.getSystemVersion());
        request.setIp(NetWorkUtils.getIPAddress());
        request.setNicAddress(SystemTool.getPhoneIMEI());
        request.setBrowser(SystemTool.getMaunfacturer() + " " + SystemTool.getDeviceModel());

//        Log.i(TAG,"系统版本:          " + SystemTool.getSystemVersion());
//        Log.i(TAG,"手机IMEI码:        " + SystemTool.getPhoneIMEI());
//        Log.i(TAG,"运营商sim卡imsi号: " + SystemTool.getPhoneIMSI());
//        Log.i(TAG,"运营商名称:        " + SystemTool.getSimOperator());
//        Log.i(TAG,"机型:              " + SystemTool.getDeviceModel());
//        Log.i(TAG,"系统厂商:          " + SystemTool.getMaunfacturer());
//        Log.i(TAG,"IP:                " + NetWorkUtils.getIPAddress());

        return Api.getApiService(false).login(request)
                .map(new DTOFunc1<LoginDTO>())
                .map(new VOFunc1<LoginVO>())
                .doOnNext(new Action1<LoginVO>() {
                    @Override
                    public void call(LoginVO loginModel) {
                        saveLoginInfo(loginModel, username);
                        getZoneInfo();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void getZoneInfo() {

        ZoneService.getInstance().getXianList(null);
    }

    public Observable logout() {

        String token = getToken();
        return Api.getApiService(token).logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    // TODO: 2017-05-09   先做成都从网络上获取   如果用 mRxManager.add 的方法 concat 操作符在如果网络没打开 netUserObservable 没有数据 会导致 localUserObservable 的数据也发布出去 所以用
//    public Observable<UserVO> getUserInfo() {
//
//
//        Observable<UserVO> localUserObservable = Observable.create(new Observable.OnSubscribe<UserVO>() {
//            @Override
//            public void call(Subscriber<? super UserVO> subscriber) {
//                userVO = new UserVO();
//                userVO.setUserRealName("jkdfl");
//                userVO.setUserName("ldklfgklklgflkhlgk");
//                if (null != userVO) {
//                    subscriber.onNext(userVO);
//                }
//                subscriber.onCompleted();
//
//            }
//        })
//                .subscribeOn(Schedulers.io());
//
//        Observable<UserVO> netUserObservable = Api.getApiService().getUserInfo()
//                .map(new DTOFunc1<UserDTO>())
//                .map(new VOFunc1<User>())
//                .doOnNext(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//                        saveUser(user);
//                    }
//                })
//                .map(new VOFunc1<UserVO>())
//                .doOnNext(new Action1<UserVO>() {
//                    @Override
//                    public void call(UserVO userVO) {
//                        saveUser(userVO);
//                    }
//                })
//                .subscribeOn(Schedulers.io());
//
//        return Observable.concat(localUserObservable, netUserObservable)
////                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }


    /**
     * 先返回从内存拿数据  在从数据库那数据  最后从网络拿数据  并且从网络上拿到数据之后 保存在本地和数据库
     * @param subscriber
     */
    public void getUserInfo(Subscriber<UserVO> subscriber) {

        if (null != userVO) {
            userVO.setWhereFrom("内存");
            subscriber.onNext(userVO);
        }

        String userId = PreferenceUtil.getGetUserId();
        if (!StringUtils.isEmpty(userId)) {
            User user = getUserFromDatabase(userId);
            if (null != user) {
                userVO = user.transform();
                userVO.setWhereFrom("数据库");
                subscriber.onNext(userVO);
            }
        }

        Api.getApiService().getUserInfo()
                .map(new DTOFunc1<UserDTO>())
                .map(new VOFunc1<User>())
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        saveUser(user);
                    }
                })
                .map(new VOFunc1<UserVO>())
                .doOnNext(new Action1<UserVO>() {
                    @Override
                    public void call(UserVO userVO) {
                        saveUser(userVO);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


//    public void getUserInfo2(Subscriber<UserVO> subscriber) {
//
//
//        Observable<UserVO> localUserObservable = Observable.create(new Observable.OnSubscribe<UserVO>() {
//            @Override
//            public void call(Subscriber<? super UserVO> subscriber) {
////                userVO = new UserVO();
////                userVO.setUserRealName("jkdfl");
////                userVO.setUserName("ldklfgklklgflkhlgk");
//                if (null != userVO) {
//                    subscriber.onNext(userVO);
//                }
//                subscriber.onCompleted();
//
//            }
//        }).subscribeOn(Schedulers.io());
//
//
//        Observable<UserVO> dateBaseUserObservable = Observable.create(new Observable.OnSubscribe<UserVO>() {
//            @Override
//            public void call(Subscriber<? super UserVO> subscriber) {
//
//                String userId = PreferenceUtil.getGetUserId();
//                if (!StringUtils.isEmpty(userId)) {
//                    User user = getUserFromDatabase(userId);
//                    if (null != user) {
//                        userVO = user.transform();
//                        if (null != userVO) {
//                            subscriber.onNext(userVO);
//                        }
//                    }
//                }
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<UserVO> netUserObservable = Api.getApiService().getUserInfo()
//                .map(new DTOFunc1<UserDTO>())
//                .map(new VOFunc1<User>())
//                .doOnNext(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//                        saveUser(user);
//                    }
//                })
//                .map(new VOFunc1<UserVO>())
//                .doOnNext(new Action1<UserVO>() {
//                    @Override
//                    public void call(UserVO userVO) {
//                        saveUser(userVO);
//                    }
//                })
//                .subscribeOn(Schedulers.io());
////
////
//
//        Observable.concat(localUserObservable, dateBaseUserObservable, netUserObservable)
////                .subscribeOn(Schedulers.io())
//
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }


    /**
     * 从数据库拿用户
     *
     * @return
     */
    private User getUserFromDatabase(String userId) {

        User user = null;

        if (!StringUtils.isEmpty(userId)) {
            user = DBUtils.getUserDao().queryBuilder().where(UserDao.Properties.UserId.eq(userId)).unique();
        }
        return user;
    }

    /**
     * user入库
     *
     * @param user
     */
    public void saveUser(User user) {

        String userId = "";
        if (null != user) {
            userId = user.getUserId();
        }
        if (StringUtils.isEmpty(userId)) {  //  如果没有userId那就不存数据库了
            return;
        }

        if (isTheUserInUserTable(userId)) {
            // 更新
            DBUtils.getUserDao().update(user);
        } else {
            // 插入
            DBUtils.getUserDao().insert(user);
        }

    }

    /**
     * 判断某个用户是否存在数据表中
     *
     * @param userId
     * @return
     */
    private boolean isTheUserInUserTable(String userId) {

        UserDao userDao = DBUtils.getUserDao();
        User user = userDao.queryBuilder().where(UserDao.Properties.UserId.eq(userId)).unique();
        if (null != user) {
            return true;
        }
        return false;
    }

    public void saveUser(UserVO user) {
        this.userVO = user;
    }


    /**
     * 保存登录信息
     *
     * @param loginModel
     * @param username
     */
    private void saveLoginInfo(LoginVO loginModel, String username) {

        if (null == loginModel) {
            return;
        }
        PreferenceUtil.setToken(loginModel.getToken());
        PreferenceUtil.setUserId(loginModel.getUserId());
        PreferenceUtil.setUserName(username);
    }

// -----------------------我是分割线------------------------

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {

        if (StringUtils.isEmpty(getToken())) {
            return false;
        }
        return true;
    }

    /**
     * 清除登录状态
     */
    public static void clearLoginState() {
        PreferenceUtil.setToken("");
        PreferenceUtil.setUserId("");
    }

    /**
     * 清除登录状态 销毁界面退出
     */
    public static void exit() {
        clearLoginState();
        ZoneService.getInstance().destory();
//        getInstance().logout2();
//        getInstance().sss();

        AppManager.getAppManager().finishAllActivity();

//        Activity activity = AppManager.getAppManager().currentActivity();
//        if((null != activity) && (!activity.isFinishing())){
//            LoginActivity.startAction(activity);
//        }

    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        return PreferenceUtil.getToken();
    }


}
