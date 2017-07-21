package com.hl.foundation.frame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hl.foundation.R;
import com.hl.foundation.library.utils.UIUtil;

import butterknife.ButterKnife;

/**
 * des: 显示在屏幕中间的dialog
 * Created by HL
 * on 2017/2/8 0008.
 */

public abstract class BaseCenterDialog {

    private boolean isShow = false;

    protected Context mContext;
    protected Dialog mDialog;
    protected View mDialogView;

    public BaseCenterDialog(Context context, boolean isCancelable,boolean isCancelOutSide){

        this.mContext = context;

        mDialog = new Dialog(mContext, R.style.MyDialogStyle);
        mDialogView = View.inflate(mContext,getLaoyoutId(),null);
        ButterKnife.bind(this, mDialogView);
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (UIUtil.getScreenWidth() * 0.8);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        dialogWindow.setAttributes(lp);

        mDialog.setCancelable(isCancelable);
        mDialog.setCanceledOnTouchOutside(isCancelOutSide);
        
        initEvents();
    }


    protected  void setCancelable(boolean isCancelable){
        mDialog.setCancelable(isCancelable);
    }

    protected abstract void initEvents();

    protected abstract int getLaoyoutId();

    public void show() {

        mDialog.show();
    }

    public boolean isShow() {
        return   mDialog.isShowing();
    }

    public void dismiss(){
        ButterKnife.unbind(this);
        mDialog.dismiss();
    }
}
