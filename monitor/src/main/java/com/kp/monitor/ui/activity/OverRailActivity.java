package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.hl.foundation.library.inter.OverRailDetailInte;
import com.hl.foundation.library.manager.InterfaceMgr;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.CTopBar;
import com.hl.foundation.library.widget.statusbar.StatusBarUtil;
import com.kp.monitor.R;
import com.kp.monitor.contract.OverRailContract;
import com.kp.monitor.data.event.HandleStateEvent;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OverRailVO;
import com.kp.monitor.model.OverRailModel;
import com.kp.monitor.presenter.OverRailPresenter;
import com.kp.monitor.service.helper.LocationControlHelper;
import com.kp.monitor.service.helper.MapHelper;
import com.kp.monitor.ui.BaseAppActivity;
import com.kp.monitor.ui.dialog.HandleUnusualDialog;

import java.util.Calendar;

import butterknife.Bind;

import static android.widget.RelativeLayout.TRUE;

/**
 * des: 越栏详情界面
 * Created by HL
 * on 2017-05-31.
 */

public class OverRailActivity extends BaseAppActivity<OverRailModel, OverRailPresenter>
        implements OverRailContract.View, View.OnClickListener, OverRailDetailInte {

    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.person_sex)
    TextView personSex;

    @Bind(R.id.person_age)
    TextView personAge;

    @Bind(R.id.person_type)
    TextView personType;

    @Bind(R.id.device_number)
    TextView deviceName;
    @Bind(R.id.id_card)
    TextView idCard;
    @Bind(R.id.detail_status)
    TextView detailStatus;
    @Bind(R.id.topbar)
    CTopBar topBar;

    @Bind(R.id.go_handle)
    TextView goHandle;
    @Bind(R.id.ll_info)
    RelativeLayout llInfo;

    private String exceptionId; // 异常id
    private AMap mMap;
    private OverRailVO mOverRailVo;
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    public static void startAction(Context context, String msgId) {

        Intent intent = new Intent(context, OverRailActivity.class);
        intent.putExtra("exceptionId", msgId);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpMapIfNeeded();
        MapHelper.getInstance().setMapView(mMap);
        LocationControlHelper.getInstance().startLocation();
    }

    /**
     * 获取 AMap 对象
     */
    private void setUpMapIfNeeded() {
        if (null == mMap) {
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapview)).getMap();
        }
    }


    @Override
    protected void initDatas() {
        exceptionId = getIntent().getExtras().getString("exceptionId");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_over_rail;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, com.hl.foundation.R.color
                .colorTheme);
    }

    @Override
    protected void initEvents() {
        mPresenter.requstOverRail(exceptionId);
        InterfaceMgr.getInstance().setOnOverRailDetailLisener(this);

        goHandle.setOnClickListener(this);
    }

    private String handlStatue = "";

    @Override
    public void onGetOverRail(OverRailVO overRailVO) {
        mOverRailVo = overRailVO;
        personName.setText(overRailVO.name);
        personAge.setText(overRailVO.age + "岁");
        personSex.setText(overRailVO.sex);
        personType.setText(overRailVO.personType);
        deviceName.setText("设备编号:" + overRailVO.deviceNumber);
        idCard.setText("身份证号:" + overRailVO.cardId);
        detailStatus.setText(overRailVO.exStatus);
        handlStatue = overRailVO.exStatus;
        if (handlStatue.equals("处理完成")) {
            goHandle.setVisibility(View.GONE);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
            llInfo.setLayoutParams(params);

        } else if (handlStatue.equals("处理中")) {
            detailStatus.setTextColor(getResources().getColor(R.color.gray));
            goHandle.setVisibility(View.VISIBLE);
            goHandle.setText("处理完成");
        } else if (handlStatue.equals("未处理")) {
            detailStatus.setTextColor(getResources().getColor(R.color.red));
            goHandle.setVisibility(View.VISIBLE);
            goHandle.setText("去处理");

        }

        int color = getResources().getColor(R.color.colorHalfTransparent);
        int topBarHeight = topBar.getHeight();
        int bottomHeight = 300;
        MapHelper.getInstance().addToMap(overRailVO, color, getResources().getColor(R.color
                .colorBgSearch), topBarHeight, bottomHeight);

    }

    @Override
    public void onGetOverRailStatus(MsgVo msgVo) {
        if (msgVo.msg.equals("X")) {
            detailStatus.setText("处理中");
            handlStatue = "处理中";
            detailStatus.setTextColor(getResources().getColor(R.color.gray));
            goHandle.setVisibility(View.VISIBLE);
            goHandle.setText("处理完成");
            HandleStateEvent.post(HandleStateEvent.CHU_LI_ZHONG, exceptionId);
        } else if (msgVo.msg.equals("Y")) {
            detailStatus.setText("处理完成");
            handlStatue = "处理完成";
            detailStatus.setTextColor(getResources().getColor(R.color.color_blak));
            goHandle.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
            llInfo.setLayoutParams(params);
            HandleStateEvent.post(HandleStateEvent.CHU_LI_COMPLETE, exceptionId);
        }
    }

    private void showHanleDescritonDialog() {

        if (!isFinishing()) {

            String statue = "";
            if (StringUtils.equals("未处理", handlStatue)) {
                statue = HandleUnusualDialog.WEI_CHU_LI;
            } else if (StringUtils.equals("处理中", handlStatue)) {
                statue = HandleUnusualDialog.CHU_LI_ZHONG;
            }

            if (!StringUtils.isEmpty(statue)) {
                HandleUnusualDialog handleUnusualDialog = new HandleUnusualDialog(mContext, statue,mOverRailVo.dealContent);
                handleUnusualDialog.setOnHandleButtonClickListener(new HandleUnusualDialog
                        .OnHandleButtonClickListener() {
                    @Override
                    public void onHandleBtnClick(String message, String type) {
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                            lastClickTime = currentTime;
                            mPresenter.changeOrderRailStatus(mOverRailVo.exceptionId, type,
                                    message);
                        }
                    }
                });
                handleUnusualDialog.show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        showHanleDescritonDialog();
    }

    @Override
    public void overRailDetail(String status, String content) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            mPresenter.changeOrderRailStatus(mOverRailVo.exceptionId, status, content);
        }
    }
}
