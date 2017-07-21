package com.kp.monitor.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.frame.dialog.BaseCenterDialog;
import com.kp.monitor.R;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017/2/22 0022.
 */

public class BaseTitleDialog extends BaseCenterDialog {

    @Bind(R.id.txt_title)
    TextView txtTitle;
    @Bind(R.id.txt_content)
    TextView txtContent;
    @Bind(R.id.txt_ok)
    TextView txtOk;

    private OnDialogClickListener listener;

    public BaseTitleDialog(Context context, boolean isCancelable) {
        super(context, isCancelable,true);
    }

    public BaseTitleDialog(Context context, boolean isCancelable,boolean isCancelOutSide) {
        super(context, isCancelable,isCancelOutSide);
    }

    @Override
    protected void initEvents() {

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
                if(null != listener){
                    listener.onOkClick();
                }
            }
        });
    }

    @Override
    protected int getLaoyoutId() {
        return R.layout.dialog_base_title;
    }

    public BaseTitleDialog setTilte(String title){
        txtTitle.setText(title);
        return this;
    }

    public BaseTitleDialog setContent(String content){
        txtContent.setText(content);
        return this;
    }

    public BaseTitleDialog setOnDialogClickListener(OnDialogClickListener listener){
        this.listener = listener;
        return this;
    }

    public interface  OnDialogClickListener{

        void onOkClick();
    }
}
