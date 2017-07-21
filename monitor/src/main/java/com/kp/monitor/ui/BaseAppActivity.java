package com.kp.monitor.ui;

import android.os.Bundle;

import com.hl.foundation.frame.ui.BaseModel;
import com.hl.foundation.frame.ui.BasePresenter;
import com.hl.foundation.frame.ui.base.BaseLoadingActivity;
import com.hl.foundation.library.widget.statusbar.StatusBarUtil;
import com.kp.monitor.R;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public abstract class BaseAppActivity<M extends BaseModel,P extends BasePresenter> extends BaseLoadingActivity<M,P> {


    //
//
//    private Toolbar mtoolBar;
//    private LinearLayout mDectorView = null;//根布局
//    private FrameLayout mContentView = null;//activity内容布局
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//
//    protected abstract void initDatas();
//
//    protected abstract int getLayoutId();
//
//    protected abstract void initViews();
//
//    protected abstract void initEvents();
//
//    @Override
//    public void setContentView(@LayoutRes int layoutResID) {
//        getLayoutInflater().inflate(layoutResID,mContentView);
//        super.setContentView(mDectorView);
//    }
//
//    private void initStatusView() {
//        View viewStatus = getLayoutInflater().inflate(R.layout.status_bar,mDectorView);
//        ViewGroup.LayoutParams layoutParams = viewStatus.getLayoutParams();
//        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
//        mDectorView.addView(viewStatus);
//        viewStatus.setLayoutParams(layoutParams);
//    }
//
//    /**
//     * 初始化toolbar
//     */
//    private void initToolBar() {
//        View view = getLayoutInflater().inflate(R.layout.toolbar_item,null);
//        mtoolBar = (Toolbar) view.findViewById(R.id.toolbar);
//        mtoolBar.setTitleTextColor(Color.parseColor("#ffffff"));
//        mDectorView.addView(view);
//        setCustomToolbar(mtoolBar);//让activity自己设置toolbar
//    }
//
//    /**
//     * 初始化根布局
//     */
//    private void initDectorView() {
//        mDectorView = new LinearLayout(this);
//        mDectorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//        mDectorView.setOrientation(LinearLayout.VERTICAL);
//        initStatusView();
//        initToolBar();
//        mContentView = new FrameLayout(this);
//        mContentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        mDectorView.addView(mContentView);
//    }
//
//
//
//    /**
//     * 让子activity自己定义toolbar
//     * @param mtoolBar
//     */
//    protected void setCustomToolbar(Toolbar mtoolBar) {
//
//    }
}
