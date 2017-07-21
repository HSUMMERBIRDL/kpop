package com.kp.monitor.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hl.foundation.frame.ui.widget.BaseFrameLayout;
import com.hl.foundation.library.utils.AnimationUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.UnusualHandleStatue;
import com.kp.monitor.data.vo.UnusualStatue;
import com.kp.monitor.service.helper.UnusualInfoHelper;
import com.kp.monitor.ui.adapter.HandleStatueLabelAdapter;
import com.kp.monitor.ui.adapter.UnusualStatueLabelAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-31.
 */

public class UnusualStatueSelectView extends BaseFrameLayout {


    private static final String TAG = UnusualStatueSelectView.class.getSimpleName();

    public UnusualStatueSelectView(Context context) {
        super(context);
    }

    public UnusualStatueSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnusualStatueSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private TagFlowLayout unusualStatueTagFlowLayout;
    private CheckBox cbUnusualStatue;
    private TagFlowLayout handleStatueTagFlowLayout;
    private CheckBox cbHandleStatue;

    private UnusualStatueLabelAdapter unusualStatueLabelAdapter;
    private HandleStatueLabelAdapter handleStatueLabelAdapter;

    private List<UnusualStatue> unusualStatueList;
    private List<UnusualHandleStatue> unusualHandleStatueList;

    private OnActionListener listener;
    private List<String> unusualStatueSelectedList;  //  选中的异常状态
    private List<String> handleStatueSelectedList;  // 选中的处理状态

    private TextView txtReset;
    private TextView txtOk;

    @Override
    protected int setRootViewId() {
        return R.layout.view_unusual_statue;
    }


    @Override
    protected void initDatas() {
        super.initDatas();

        unusualStatueList = new ArrayList<>();
        unusualHandleStatueList = new ArrayList<>();
        unusualStatueSelectedList = new ArrayList<>();  //  选中的异常状态
        handleStatueSelectedList = new ArrayList<>();  // 选中的处理状态


        if (null != unusualStatueList) {
            if (unusualStatueList.isEmpty()) {
                unusualStatueList.addAll(UnusualInfoHelper.getUnusualStatues());
            }
        }

        if (null != unusualHandleStatueList) {
            if (unusualHandleStatueList.isEmpty()) {
                unusualHandleStatueList.addAll(UnusualInfoHelper.getHanleStatueList());
            }
        }
    }

    @Override
    protected void initViews() {

        unusualStatueTagFlowLayout = $(R.id.tfl_unusual_statue);
        cbUnusualStatue = $(R.id.cb_unusual_statue);
        handleStatueTagFlowLayout = $(R.id.tfl_handle_statue);
        cbHandleStatue = $(R.id.cb_handle_statue);
        txtReset = $(R.id.txt_reset);
        txtOk = $(R.id.txt_ok);

        unusualStatueLabelAdapter = new UnusualStatueLabelAdapter(unusualStatueList, mContext, unusualStatueTagFlowLayout);
        handleStatueLabelAdapter = new HandleStatueLabelAdapter(unusualHandleStatueList, mContext, handleStatueTagFlowLayout);

        unusualStatueTagFlowLayout.setAdapter(unusualStatueLabelAdapter);
        handleStatueTagFlowLayout.setAdapter(handleStatueLabelAdapter);

        //  当异常状态被全部选中时 cbUnusualStatue 应该是已选状态  否则就不是 (事实上只有第一次进入该页面的时候才会执行 所有都会一个都没选中)

        checkUnusualCbStatue();
        checkHandleCbStatue();

    }

    /**
     * 检测 cbHandleStatue 的状态
     */
    private void checkHandleCbStatue() {
        if((handleStatueSelectedList.size()) == unusualHandleStatueList.size()){
            cbHandleStatue.setChecked(true);
        }else {
            cbHandleStatue.setChecked(false);
        }
    }

    /**
     * 检测 cbUnusualStatue 的状态
     */
    private void checkUnusualCbStatue() {
        if((unusualStatueSelectedList.size()) == unusualStatueList.size()){
            cbUnusualStatue.setChecked(true);
        }else {
            cbUnusualStatue.setChecked(false);
        }
    }

    @Override

    protected void initEvents() {

        cbUnusualStatue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //  下面的逻辑是这样的 没错
                LogUtils.logi(TAG,"onClick  cbUnusualStatue： " + cbUnusualStatue.isChecked());
                if(cbUnusualStatue.isChecked()){
                    cbUnusualStatue.setChecked(true);
                    setAllUnusualStatueIsSelected(true);
                }else{
                    cbUnusualStatue.setChecked(false);
                    setAllUnusualStatueIsSelected(false);
                }
            }
        });

        cbHandleStatue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbHandleStatue.isChecked()){
                    cbHandleStatue.setChecked(true);
                    setAllHandleStatueIsSelected(true);
                }else{
                    cbHandleStatue.setChecked(false);
                    setAllHandleStatueIsSelected(false);
                }
            }
        });

        unusualStatueLabelAdapter.setOnActionListener(new UnusualStatueLabelAdapter.OnActionListener() {
            @Override
            public void onItemClick(UnusualStatue statue) {

                if (statue.isSelected()) {
                    statue.setSelected(false);
                    UnusualInfoHelper.removeUnusualStatueFromSelectList(statue, unusualStatueSelectedList);
                } else {
                    statue.setSelected(true);
                    UnusualInfoHelper.addUnusualStatueToSelectList(statue, unusualStatueSelectedList);
                }

                unusualStatueLabelAdapter.notifyDataChanged();
                checkUnusualCbStatue();
            }
        });

        handleStatueLabelAdapter.setOnActionListener(new HandleStatueLabelAdapter.OnActionListener() {
            @Override
            public void onItemClick(UnusualHandleStatue statue) {

                if (statue.isSelected()) {
                    statue.setSelected(false);
                    UnusualInfoHelper.removeHandleStatueFromSelectList(statue, handleStatueSelectedList);
                } else {
                    statue.setSelected(true);
                    UnusualInfoHelper.addHandleStatueToSelectList(statue, handleStatueSelectedList);
                }

                handleStatueLabelAdapter.notifyDataChanged();
                checkHandleCbStatue();
            }
        });

        txtOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (null != listener) {

                    boolean isAllSelected = false;

                    LogUtils.logi(TAG,"cbUnusualStatue： " + cbUnusualStatue.isChecked());

                    if((cbUnusualStatue.isChecked()) && (cbHandleStatue.isChecked())){
                        isAllSelected = true;
                    }

                    listener.onOkClick(unusualStatueSelectedList, handleStatueSelectedList, unusualStatueList, unusualHandleStatueList,isAllSelected);
                }

                destory();
            }
        });

        txtReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                destory();
            }
        });

    }

    /**
     *  将所有异常状态设置为 选中或者不选中 并且将选中的id列表置为空 或者全部加入
     * @param isAllSelected
     */
    private void setAllUnusualStatueIsSelected(boolean isAllSelected) {

        try {
            for (int i = 0; i < unusualStatueList.size(); i++) {
                UnusualStatue unusualStatue = unusualStatueList.get(i);
                unusualStatue.setSelected(isAllSelected);
                if(isAllSelected){
                    UnusualInfoHelper.addUnusualStatueToSelectList(unusualStatue,unusualStatueSelectedList);
                }
            }
            if(!isAllSelected){
                unusualStatueSelectedList.clear();
            }
            unusualStatueLabelAdapter.notifyDataChanged();
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        }
    }


    /**
     *  将所有异常状态设置为 选中或者不选中 并且将选中的id列表置为空 或者全部加入
     * @param isAllSelected
     */
    private void setAllHandleStatueIsSelected(boolean isAllSelected) {

        try {
            for (int i = 0; i < unusualHandleStatueList.size(); i++) {
                UnusualHandleStatue unusualHandleStatue = unusualHandleStatueList.get(i);
                unusualHandleStatue.setSelected(isAllSelected);
                if(isAllSelected){
                    UnusualInfoHelper.addHandleStatueToSelectList(unusualHandleStatue,handleStatueSelectedList);
                }
            }
            if(!isAllSelected){
                handleStatueSelectedList.clear();
            }
            handleStatueLabelAdapter.notifyDataChanged();
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        }
    }

    public void setOnActionListener(OnActionListener listener) {
        this.listener = listener;
    }

    public interface OnActionListener {

        void onOkClick(List<String> unusualStatueSelectedList, List<String> handleStatueSelectedList, List<UnusualStatue> unusualStatueList, List<UnusualHandleStatue> unusualHandleStatueList,boolean isAllSelected);
    }

    public void show() {

        AnimationUtils.showSlideTopIn(this);
    }

    public void destory() {
        AnimationUtils.hideSlideButtomOut(this);
    }
}
