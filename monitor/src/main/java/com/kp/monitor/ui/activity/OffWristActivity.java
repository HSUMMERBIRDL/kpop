package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.event.HandleStateEvent;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffWristVO;
import com.kp.monitor.data.vo.UnusalInfoVO;
import com.kp.monitor.model.OffWristListModel;
import com.kp.monitor.presenter.OffWristListPresenter;
import com.kp.monitor.ui.adapter.OffWristListAdapter;
import com.kp.monitor.ui.dialog.HandleUnusualDialog;
import com.kp.monitor.view.OffWristListView;

import butterknife.Bind;

/**
 * des: 脱腕界面
 * Created by HL
 * on 2017-06-01.
 */

public class OffWristActivity extends BaseListActivity<OffWristListModel, OffWristListPresenter, OffWristVO> implements OffWristListView {

    public static final String EXCEPTION_ID = "exception_id";

    @Bind(R.id.txt_name)
    TextView txtName;
    @Bind(R.id.txt_card_id)
    TextView txtCardId;
    @Bind(R.id.txt_device_num)
    TextView txtDeviceNum;
    @Bind(R.id.txt_handle_statue)
    TextView txtHandleStatue;
    @Bind(R.id.go_handle)
    TextView goHandle;

    private int handleStatue = -1;  // 当前状态 （-1 代表根本没拿到状态 比如无网络就拿不到）
    private String exceptionId;
    private UnusalInfoVO unusalInfoVO;

    public static void startAction(Context context, String exceptionId) {

        Intent intent = new Intent(context, OffWristActivity.class);
        intent.putExtra(EXCEPTION_ID, exceptionId);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
        super.initDatas();

        Intent intent = getIntent();
        exceptionId = intent.getStringExtra(EXCEPTION_ID);
        parameters.put(ApiConstants.EXCEPTION_ID, exceptionId);
    }

    @Override
    protected void initViews() {
        super.initViews();

        sendRequest();
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        goHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showHanleDescritonDialog();
            }
        });
    }

    /**
     * 将基本信息填充到上半部分的view中
     */
    private void initBasicViews(UnusalInfoVO unusalInfoVO) {

        goHandle.setVisibility(View.GONE);

        if (null != unusalInfoVO) {
            this.unusalInfoVO = unusalInfoVO;
            handleStatue = unusalInfoVO.getHandleStatue();

            if (handleStatue == UnusalInfoVO.DEAL_STATUS_GOING) {
                txtHandleStatue.setText("未处理");
                goHandle.setVisibility(View.VISIBLE);
                goHandle.setText("去处理");
            } else if (handleStatue == UnusalInfoVO.DEAL_STATUS_ING) {
                txtHandleStatue.setText("处理中");
                goHandle.setVisibility(View.VISIBLE);
                goHandle.setText("处理完成");
            } else if (handleStatue == UnusalInfoVO.DEAL_STATUS_ED) {
                txtHandleStatue.setText("处理完成");
                goHandle.setVisibility(View.GONE);
            }


            String name = unusalInfoVO.getName();
            String cardId = "身份证: " + unusalInfoVO.getCardId();
            String deviceNum = "设备号: " + unusalInfoVO.getDeviceNum();

            txtName.setText(name);
            txtCardId.setText(cardId);
            txtDeviceNum.setText(deviceNum);
        }
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_off_wrist;
    }

    @Override
    protected boolean isSetPullRefresh() {
        return false;
    }

    @Override
    protected boolean isGetListRightNow() {
        return false;
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new OffWristListAdapter(mContext, R.layout.list_item_off_line);
    }

    @Override
    public void onOffWristBaicInfoComplete(UnusalInfoVO unusalInfoVO) {
        initBasicViews(unusalInfoVO);
    }

    /**
     * 弹出处理说明对话框
     */
    private void showHanleDescritonDialog() {

        try {
            String statue = "";
            if(handleStatue == UnusalInfoVO.DEAL_STATUS_GOING){
                statue = HandleUnusualDialog.WEI_CHU_LI;
            }else if(handleStatue == UnusalInfoVO.DEAL_STATUS_ING){
                statue = HandleUnusualDialog.CHU_LI_ZHONG;
            }

            if(!StringUtils.isEmpty(statue)){
                HandleUnusualDialog dialog = new HandleUnusualDialog(mContext,statue,unusalInfoVO.getDealContent());
                dialog.setOnHandleButtonClickListener(new HandleUnusualDialog.OnHandleButtonClickListener() {
                    @Override
                    public void onHandleBtnClick(String message, String type) {

                        mPresenter.changeHandleSatue(exceptionId,type,message);
                    }
                });
                dialog.show();
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onHandleComplete(MsgVo msgVo) {

        if (msgVo.msg.equals("X")) {  //  处理中

            txtHandleStatue.setText("处理中");
            goHandle.setVisibility(View.VISIBLE);
            goHandle.setText("处理完成");
            handleStatue = UnusalInfoVO.DEAL_STATUS_ING;   // 更新 handleStatue 的状态值
            HandleStateEvent.post(HandleStateEvent.CHU_LI_ZHONG,exceptionId);
        } else if (msgVo.msg.equals("Y")) { // 处理完成

            txtHandleStatue.setText("处理完成");
            goHandle.setVisibility(View.GONE);
            handleStatue = UnusalInfoVO.DEAL_STATUS_ING;   // 更新 handleStatue 的状态值
            HandleStateEvent.post(HandleStateEvent.CHU_LI_COMPLETE,exceptionId);
        }
    }
}
