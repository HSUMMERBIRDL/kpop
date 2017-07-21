package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.hl.foundation.frame.ui.base.BaseLoadingActivity;
import com.hl.foundation.library.utils.CThreadUtils;
import com.kp.monitor.R;
import com.kp.monitor.service.UserService;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017-05-27.
 */

public class SplashActivity extends BaseLoadingActivity {


    private static final int SECONDS = 1;
    @Bind(R.id.fl_container)
    RelativeLayout flContainer;
    private boolean isLogin;
    AlphaAnimation animation = new AlphaAnimation(1, 0.1f);

    public static void startAction(Context context) {

        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected boolean isImmersiveBar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {

        flContainer.startAnimation(animation);
        CThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {

                    goMain();
                } else {
                    goLogin();
                }

              finish();
            }
        }, 1000 * SECONDS);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

        isLogin = UserService.isLogin();
        animation.setDuration(1000 * SECONDS);
        animation.setFillAfter(false);

//        SplashActivityPermissionsDispatcher.onLocationAcceptWithCheck(this);

    }


    private void goLogin() {
        LoginActivity.startAction(mContext);
    }

    private void goMain() {
        MainActivity.startAction(mContext);
    }
}
