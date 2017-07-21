package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.kp.monitor.R;
import com.kp.monitor.contract.GatherDetailContract;
import com.kp.monitor.data.vo.GatherVO;
import com.kp.monitor.model.GatherDetailModel;
import com.kp.monitor.presenter.GatherDetailPresenter;
import com.kp.monitor.ui.BaseAppActivity;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class GatherDetailActivity extends BaseAppActivity<GatherDetailModel,GatherDetailPresenter> implements GatherDetailContract.View{

    public static final String GATHER_ID = "gather_id";

    private String gatherId = "";


    public static void startAction(Context context, String gatherId) {

        Intent intent = new Intent(context, GatherDetailActivity.class);
        intent.putExtra(GATHER_ID,gatherId);
        context.startActivity(intent);
    }
    
    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        gatherId = intent.getStringExtra(GATHER_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_gather;
    }

    @Override
    protected void initViews() {

        mPresenter.sendGetGatherDetail(gatherId);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onGetGatherDetailComplete(GatherVO gatherVO) {

    }
}
