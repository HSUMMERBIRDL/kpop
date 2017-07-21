package com.kp.monitor.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.frame.dialog.BaseCenterDialog;
import com.hl.foundation.library.widget.CTextButton;
import com.kp.monitor.R;

import butterknife.Bind;

/**
 * des: 相比于 BaseTitleDialog  有取消和确定两个按钮
 * Created by HL
 * on 2017/2/22 0022.
 */

public class BaseTitleDialog2 extends BaseCenterDialog {

    @Bind(R.id.txt_title)
    TextView txtTitle;
    @Bind(R.id.txt_content)
    TextView txtContent;
    @Bind(R.id.txt_ok)
    TextView txtOk;
    @Bind(R.id.txt_cancel)
    CTextButton txtCancel;

    private OnDialogClickListener listener;

    public BaseTitleDialog2(Context context, boolean isCancelable) {
        super(context, isCancelable,true);
    }

    public BaseTitleDialog2(Context context, boolean isCancelable,boolean isCancelOutSide) {
        super(context, isCancelable,isCancelOutSide);
    }



    @Override
    protected void initEvents() {

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
                if (null != listener) {
                    listener.onOkClick();
                }
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
                if (null != listener) {
                    listener.onCancelClick();
                }
            }
        });
    }

    @Override
    protected int getLaoyoutId() {
        return R.layout.dialog_base_title2;
    }

    public BaseTitleDialog2 setTilte(String title) {
        txtTitle.setText(title);
        return this;
    }

    public BaseTitleDialog2 setContent(String content) {
        txtContent.setText(content);
        return this;
    }

    public BaseTitleDialog2 setOnDialogClickListener(OnDialogClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnDialogClickListener {

        void onOkClick();
        void onCancelClick();
    }
}
