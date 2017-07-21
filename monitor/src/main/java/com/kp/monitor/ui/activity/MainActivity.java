package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hl.foundation.library.inter.ChangeMsgInte;
import com.hl.foundation.library.manager.InterfaceMgr;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.UIUtil;
import com.hl.foundation.library.widget.statusbar.StatusBarUtil;
import com.kp.monitor.R;
import com.kp.monitor.audio.AudioRecordFunc;
import com.kp.monitor.contract.MainContract;
import com.kp.monitor.data.bean.Record;
import com.kp.monitor.data.vo.PersonNumberVo;
import com.kp.monitor.data.vo.UnMsgVO;
import com.kp.monitor.data.vo.UnusualPersonVO;
import com.kp.monitor.data.vo.UserVO;
import com.kp.monitor.model.MainModel;
import com.kp.monitor.presenter.MainPresenter;
import com.kp.monitor.service.UserService;
import com.kp.monitor.service.helper.DeviceHelper;
import com.kp.monitor.service.helper.GeneralHelper;
import com.kp.monitor.service.helper.UnusualInfoHelper;
import com.kp.monitor.ui.BaseAppActivity;
import com.kp.monitor.ui.adapter.FunctionAdapter;
import com.kp.monitor.ui.adapter.UnusualAdapter;
import com.kp.monitor.ui.widget.FunctionGridView;
import com.kp.monitor.ui.widget.UnusualGridView;
import com.kp.monitor.utils.ConfigUtils;
import com.marswin89.marsdaemon.demo.Service1;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class MainActivity extends BaseAppActivity<MainModel, MainPresenter> implements
        MainContract.View, View.OnClickListener, ChangeMsgInte, AudioRecordFunc
        .AudioRecorderLisener {

    private static final java.lang.String TAG = MainActivity.class.getSimpleName();
    /*策划菜单*/
    LinearLayout llLanguageMode;
    LinearLayout llChangePass;
    LinearLayout llLogout;
    LinearLayout llVersionInfo;
    LinearLayout llPersonInfo;
    TextView txtUserName;
    ImageView imgHeader;
    LinearLayout llDrawer;


    @Bind(R.id.iv_title_menu)
    ImageView ivTitleMenu;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.detail_gridview)
    FunctionGridView function_gridview;
    @Bind(R.id.unusual_gridview)
    UnusualGridView unusual_gridview;
    @Bind(R.id.flush_notify)
    TextView flushNotify;
    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.main_flush)
    ImageView mainFlush;
    private UnusualAdapter unusualAdapter;
    Timer timer = new Timer();
    //    private int count;
    private FunctionAdapter functionAdapter;
    private long firstTime;
    private PersonNumberVo personNumberVo = new PersonNumberVo();
    private int flushHeight;
    private boolean isForceFlush;
    private int msgCount;

    private int notUploadFileNum;

    @Override
    protected void initDatas() {
        DeviceHelper.getInstance().getDisplay(this);
        startService(new Intent(this, Service1.class));
        functionAdapter = new FunctionAdapter(this);
        function_gridview.setAdapter(functionAdapter);
        functionAdapter.setGridView(function_gridview);

        unusualAdapter = new UnusualAdapter(this);
        unusual_gridview.setAdapter(unusualAdapter);
        unusualAdapter.setGridView(unusual_gridview);
        flushHeight = flushNotify.getHeight();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        }, 1000, 60000);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, R.color.colorTheme);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }

        initDrawerLayout();

        mPresenter.sendGetUserInfo();
    }



    @Override
    protected boolean isImmersiveBar() {
        return false;
    }

    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = navView.getHeaderView(0);
        llPersonInfo = (LinearLayout) headerView.findViewById(R.id.ll_person_info);
        llChangePass = (LinearLayout) headerView.findViewById(R.id.ll_change_pass);
        llLogout = (LinearLayout) headerView.findViewById(R.id.ll_exit);
        llVersionInfo = (LinearLayout) headerView.findViewById(R.id.ll_version_info);
        llLanguageMode = (LinearLayout) headerView.findViewById(R.id.ll_language_mode);
        txtUserName = (TextView) headerView.findViewById(R.id.txt_username);
        imgHeader = (ImageView) headerView.findViewById(R.id.img_header);
        llDrawer = (LinearLayout) headerView.findViewById(R.id.ll_drawer);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, (StatusBarUtil.getStatusBarHeight(mContext) + UIUtil.dp2px(48)
        ), 0, 0);
        imgHeader.setLayoutParams(layoutParams);
    }

    @Override
    protected void initEvents() {


        ivTitleMenu.setOnClickListener(this);
        mainFlush.setOnClickListener(this);
        AudioRecordFunc.getInstance().setAudioRecordLisener(this);
        function_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        MemberListActivity.startAction(mContext, MemberListActivity .MEMBER_TYPE_ALL, false);
                        break;
                    case 1:
                        AddressListActivity.startAction(mContext);
                        break;
                    case 2:
                        MsgListActivity.startAction(mContext);
                        break;
                    case 3:
                        DeviceListActivity.startAction(mContext, DeviceListActivity.DEVICE_TYPE_ALL);
                        break;
                    case 4:
                        TrackingActivity.startAction(mContext);
                        break;
                    case 5:
                        MemberListActivity.startAction(mContext, MemberListActivity .MEMBER_TYPE_ALL, true, notUploadFileNum);
                        break;
                }
            }
        });

        llVersionInfo.setOnClickListener(this);

        llPersonInfo.setOnClickListener(this);

        llLanguageMode.setOnClickListener(this);

        llLogout.setOnClickListener(this);
        InterfaceMgr.getInstance().setChangeMsgLisener(this);
        unusual_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        MemberListActivity.startAction(mContext, MemberListActivity.MEMBER_TYPE_ALL, false);
                        break;
                    case 1:
                        UnusalListActivty.startAction(mContext);
                        break;
                    case 2:
//                        GatherListActivity.startAction(mContext);
                        break;
                    case 3:
                        MemberListActivity.startAction(mContext, MemberListActivity
                                .MEMBER_TYPE_ALL, false);
                        break;
                    case 4:
                        UnusalListActivty.startAction(mContext);
                        break;
                    case 5:
//                        GatherListActivity.startAction(mContext);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - firstTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = currentTimeMillis;
            } else {
                super.onBackPressed();
                finish();
                System.exit(0);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onGetUserInfoComplete(UserVO userVO) {
        updateUserInfoView(userVO);

    }

    private void updateUserInfoView(UserVO userVO) {
        txtUserName.setText(userVO.getUserName());

        String sex = userVO.getSex();
        if (StringUtils.equals(sex, "女")) {
            imgHeader.setImageResource(R.drawable.header_defult_women);
        } else {
            imgHeader.setImageResource(R.drawable.header_defult_men);
        }
    }

    @Override
    public void onGetUnMessageResponse(UnMsgVO unMsgVO) {
        msgCount = unMsgVO.messageDatas;
        functionAdapter.addBageView(msgCount);
    }

    @Override
    public void onGetUnusualPersonTotal(UnusualPersonVO unusualPersonVO) {
        personNumberVo.totalPersonNumber = unusualPersonVO.totalCount;
        unusualAdapter.updateItemData(personNumberVo);
    }

    @Override
    public void onGetUnusualPerson(UnusualPersonVO unusualPersonVO) {
        personNumberVo.unusualPersonNumber = unusualPersonVO.totalCount;
        unusualAdapter.updateItemData(personNumberVo);
        GeneralHelper.getInstance().stopFlushAnimation(mainFlush);
        if (isForceFlush) {
            isForceFlush = false;
            GeneralHelper.getInstance().startUpTranslateAnimation(flushNotify, flushHeight);
        }
    }

    @Override
    public void onGetGatherNumber(UnusualPersonVO unusualPersonVO) {
        personNumberVo.gatherPersonNumber = unusualPersonVO.totalCount;
        unusualAdapter.updateItemData(personNumberVo);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNotUploadFileUpdate(int currentNum) {

        LogUtils.logi(TAG,"post后的当前文件数量：" +  currentNum);

        notUploadFileNum = currentNum;
        functionAdapter.addBageViewToMoniterView(currentNum);
    }

    @Override
    public void showErrorTip(String msg) {
        super.showErrorTip(msg);
        if (isForceFlush) {
            isForceFlush = false;
            flushNotify.setText("加载失败...");
            GeneralHelper.getInstance().startUpTranslateAnimation(flushNotify, flushHeight);
            GeneralHelper.getInstance().stopFlushAnimation(mainFlush);
        }

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {

        LogUtils.logi(TAG,"---------onDestroy-----------");
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
        UnusualInfoHelper.baseTime = 0;
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == 1) {
                UnusualInfoHelper.requestUnusalInfoHelper(mPresenter, false);
            }
        }
    };


    /**
     * 关闭抽屉
     */
    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_flush:
                isForceFlush = true;
                GeneralHelper.getInstance().startFlushAnimation(mainFlush, this);
                GeneralHelper.getInstance().startDownTranslateAnimation(flushNotify, flushHeight);
                UnusualInfoHelper.requestUnusalInfoHelper(mPresenter, true);
                break;
            case R.id.iv_title_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.ll_version_info:
                closeDrawer();
                VersionInfoActivity.startAction(mContext);
                break;

            case R.id.ll_person_info:
                closeDrawer();
                PersonInfoActivity.startAction(mContext);
                break;
            case R.id.ll_language_mode:
                closeDrawer();
                ConfigUtils.switchLanguage();
                break;
            case R.id.ll_exit:
                closeDrawer();
                mPresenter.logout();
                UserService.exit();
                LoginActivity.startAction(mContext);
                break;
        }
    }

    @Override
    public void changeMsg(int position) {
        msgCount--;
        functionAdapter.addBageView(msgCount);
    }

    @Override
    public void audioRecorderReslut(Record record) {
        LogUtils.logi("文件" + record);
    }

}
