package com.kp.monitor.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.hl.foundation.frame.dialog.BaseBottomDialog;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ToastUitl;
import com.kp.monitor.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * des: 处理异常状态 对话框
 * Created by HL
 * on 2017-06-07.
 */

public class HandleUnusualDialog extends BaseBottomDialog implements TextWatcher {
    @Bind(R.id.edit_msg)
    EditText editMsg;
    @Bind(R.id.detail_plan)
    Button detailPlan;
    @Bind(R.id.detail_cancel)
    Button detailCancel;
    private boolean detail;
    private boolean complete;
    private String content;

    public static final String WEI_CHU_LI = "N";
    public static final String CHU_LI_ZHONG = "Y";

    /**
     * @param context
     * @param statue  当前异常的状态  N：未处理 Y：处理中   处理完成的不要来这里了
     */
    public HandleUnusualDialog(Context context, String statue, String dealMsg) {
        super(context);
        ButterKnife.bind(this);
        content = dealMsg;
        setCanceledOnTouchOutside(true);
        if (null != dealMsg){
            editMsg.setText(dealMsg);
            editMsg.setSelection(dealMsg.length());
        }

        if (statue.equals(WEI_CHU_LI)) {
            detail = true;
            complete = false;
        } else {
            detail = false;
            complete = true;
        }
        initLisener();
    }


    @Override
    protected void windowPoy() {
        setContentView(R.layout.dialog_overtail);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(com.hl.foundation.R.style.pictureDialog);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        WindowManager.LayoutParams lp = window.getAttributes();
        int height = (int) (window.getWindowManager().getDefaultDisplay().getHeight() * 0.4);
        lp.x = 0;
        lp.height = height;
        lp.alpha = 9f;
        window.setAttributes(lp);
    }


    private void initLisener() {
        editMsg.addTextChangedListener(this);
        detailPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (null != listener) {

                    if (validate()) {
                        dismiss();
                        if (detail) {
                            listener.onHandleBtnClick(content, "X");
                        } else if (complete) {
                            listener.onHandleBtnClick(content, "Y");
                        }
                    }
                } else {
                    dismiss();
                }
            }
        });

        detailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private boolean validate() {

        if (StringUtils.isEmpty(content)) {

            ToastUitl.showShort("请输入处理说明");
            return false;
        }

        if (!detail && !complete) {
            ToastUitl.showShort("请选择处理状态");
            return false;
        }

        return true;
    }

    private OnHandleButtonClickListener listener;

    public void setOnHandleButtonClickListener(OnHandleButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnHandleButtonClickListener {

        void onHandleBtnClick(String message, String type);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        content = editable.toString();
    }

}

