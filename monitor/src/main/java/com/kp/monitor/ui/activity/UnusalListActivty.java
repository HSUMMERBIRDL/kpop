package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.event.HandleStateEvent;
import com.kp.monitor.data.vo.UnusalItemVO;
import com.kp.monitor.data.vo.UnusualHandleStatue;
import com.kp.monitor.data.vo.UnusualStatue;
import com.kp.monitor.model.UnusalListModel;
import com.kp.monitor.presenter.UnusalListPresenter;
import com.kp.monitor.ui.adapter.HandleStatueSelectAdapter;
import com.kp.monitor.ui.adapter.UnusalListAdapter;
import com.kp.monitor.ui.adapter.UnusualStatueSelectAdapter;
import com.kp.monitor.ui.widget.UnusualStatueSelectView;
import com.kp.monitor.view.UnusalListView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * des: 异常情况列表
 * Created by HL
 * on 2017-05-27.
 */

public class UnusalListActivty extends BaseListActivity<UnusalListModel, UnusalListPresenter, UnusalItemVO> implements UnusalListView{

    @Bind(R.id.usv_statue)
    UnusualStatueSelectView usvStatue;
    @Bind(R.id.txt_select)
    TextView txtSelect;
    @Bind(R.id.ll_type_select)
    LinearLayout llTypeSelect;

    @Bind(R.id.txt_tag_statue)
    TextView txtTagStatue;
    @Bind(R.id.tfl_select_unusual_statue)
    TagFlowLayout tflSelectUnusualStatue;

    @Bind(R.id.divide_1)
    View divide1;

    @Bind(R.id.txt_tag_handle_statue)
    TextView txtTagHandleStatue;
    @Bind(R.id.tfl_select_handle_statue)
    TagFlowLayout tflSelectHandleStatue;

    @Bind(R.id.ll_select_tag)
    LinearLayout llSelectTag;


    List<UnusualStatue> unusualSelectedStatueList = new ArrayList<>();
    List<UnusualHandleStatue> unusualSelectedHandleStatueList = new ArrayList<>();



    private UnusualStatueSelectAdapter unusualStatueSelectAdapter;
    private HandleStatueSelectAdapter handleStatueSelectAdapter;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, UnusalListActivty.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unusal_list;
    }


    @Override
    protected void initViews() {
        super.initViews();

        txtSelect.setText("类别选择（全部）");
        usvStatue.setVisibility(View.GONE);
        llSelectTag.setVisibility(View.GONE);

        unusualStatueSelectAdapter = new UnusualStatueSelectAdapter(unusualSelectedStatueList, mContext, tflSelectUnusualStatue);
        handleStatueSelectAdapter = new HandleStatueSelectAdapter(unusualSelectedHandleStatueList, mContext, tflSelectHandleStatue);

        tflSelectUnusualStatue.setAdapter(unusualStatueSelectAdapter);
        tflSelectHandleStatue.setAdapter(handleStatueSelectAdapter);
    }

    @Override
    protected void initEvents() {
        super.initEvents();



        llTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usvStatue.getVisibility() == View.GONE) {
                    usvStatue.show();
                } else {
                    usvStatue.destory();
                }
            }
        });

        txtSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usvStatue.getVisibility() == View.GONE) {
                    usvStatue.show();
                } else {
                    usvStatue.destory();
                }
            }
        });

        usvStatue.setOnActionListener(new UnusualStatueSelectView.OnActionListener() {
            @Override
            public void onOkClick(List<String> unusualStatueSelectedList, List<String> handleStatueSelectedList, List<UnusualStatue> unusualStatueList, List<UnusualHandleStatue> unusualHandleStatueList,boolean isAllSelected) {


                try {

                    //  加入参数 发送请求
                    parameters.put(ApiConstants.EXCEPTION_TYPES, unusualStatueSelectedList);
                    parameters.put(ApiConstants.DEAL_STATUSES, handleStatueSelectedList);

                    adapter.getPageBean().setRefresh(true);
                    resetPageParameter();
                    sendRequest();

                    // 更新已选中的界面
                    unusualSelectedStatueList.clear();
                    unusualSelectedHandleStatueList.clear();

                    for (int i = 0; i < unusualStatueList.size(); i++) {
                        UnusualStatue unusualStatue = unusualStatueList.get(i);
                        if (unusualStatue.isSelected()) {
                            unusualSelectedStatueList.add(unusualStatue);
                        }
                    }

                    for (int i = 0; i < unusualHandleStatueList.size(); i++) {
                        UnusualHandleStatue unusualHandleStatue = unusualHandleStatueList.get(i);
                        if (unusualHandleStatue.isSelected()) {
                            unusualSelectedHandleStatueList.add(unusualHandleStatue);
                        }
                    }


                    if(isAllSelected){
                        llSelectTag.setVisibility(View.GONE);
                        txtSelect.setText("类别选择（全部）");
                    }else{
                        if ((unusualSelectedStatueList.isEmpty()) && (unusualSelectedHandleStatueList.isEmpty())) {
                            llSelectTag.setVisibility(View.GONE);
                            txtSelect.setText("类别选择（全部）");
                        }else {
                            txtSelect.setText("类别选择");
                            if (unusualSelectedStatueList.isEmpty()) {
                                txtTagStatue.setVisibility(View.GONE);
                                tflSelectUnusualStatue.setVisibility(View.GONE);
                                divide1.setVisibility(View.GONE);

                            } else {
                                divide1.setVisibility(View.VISIBLE);
                                tflSelectUnusualStatue.setVisibility(View.VISIBLE);
                                llSelectTag.setVisibility(View.VISIBLE);
                                txtTagStatue.setVisibility(View.VISIBLE);

                            }
                            unusualStatueSelectAdapter.notifyDataChanged();

                            if (unusualSelectedHandleStatueList.isEmpty()) {
                                txtTagHandleStatue.setVisibility(View.GONE);
                                tflSelectHandleStatue.setVisibility(View.GONE);
                            } else {
                                llSelectTag.setVisibility(View.VISIBLE);
                                txtTagHandleStatue.setVisibility(View.VISIBLE);
                                tflSelectHandleStatue.setVisibility(View.VISIBLE);
                            }

                            handleStatueSelectAdapter.notifyDataChanged();
                        }
                    }

                    LogUtils.logi(TAG, "选中的异常状态: " + unusualStatueSelectedList);
                    LogUtils.logi(TAG, "选中的异常状态: " + handleStatueSelectedList);
                } catch (Exception e) {
                    LogUtils.loge(TAG, e);
                }
            }
        });
    }


    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new UnusalListAdapter(mContext, R.layout.list_item_unusal);
    }

    @Override
    public void onHandleStatueChange(HandleStateEvent event) {

        if(!CollectionUtils.isNullOrEmpty(datas)){

            LogUtils.logi(TAG,"异常列表大小: " + datas.size());
            LogUtils.logi(TAG,"异常列表: " + datas);

            for (int i = 0; i < datas.size(); i++) {

                UnusalItemVO unusalItemVO = datas.get(i);

                String exceptionId = event.getExceptionId();
                int currentStatue = event.getCurrentStatue();

                if(StringUtils.equals(exceptionId,unusalItemVO.getExceptionId())){

                    switch (currentStatue){

                        case HandleStateEvent.WEI_CHU_LI:
                            unusalItemVO.setDealStatus(UnusalItemVO.DEAL_STATUS_GOING);
                            unusalItemVO.setDealStatusDes("未处理");
                            break;
                        case HandleStateEvent.CHU_LI_ZHONG:
                            unusalItemVO.setDealStatus(UnusalItemVO.DEAL_STATUS_ING);
                            unusalItemVO.setDealStatusDes("处理中");
                            break;
                        case HandleStateEvent.CHU_LI_COMPLETE:
                            unusalItemVO.setDealStatus(UnusalItemVO.DEAL_STATUS_ED);
                            unusalItemVO.setDealStatusDes("处理完成");
                            break;
                    }
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }

    }
}
