package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.R;
import com.kp.monitor.basis.preference.PreferenceUtil;
import com.kp.monitor.contract.LoginContract;
import com.kp.monitor.model.LoginModel;
import com.kp.monitor.presenter.LoginPresenter;
import com.kp.monitor.service.UserService;
import com.kp.monitor.ui.BaseAppActivity;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017-05-09.
 */

public class LoginActivity extends BaseAppActivity<LoginModel, LoginPresenter> implements
        LoginContract.View {

    @Bind(R.id.edt_user_name)
    EditText edtUserName;
    @Bind(R.id.edt_pass)
    EditText edtPass;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.txt_forget_pass)
    TextView txtForgetPass;
    @Bind(R.id.txt_login)
    TextView txtLogin;
    @Bind(R.id.txt_test)
    TextView txtTest;


    private String userName;
    private String pass;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loign;
    }

    @Override
    protected void initViews() {

        if(UserService.isLogin()){
            finish();
            MainActivity.startAction(LoginActivity.this);
        }

        String userName = PreferenceUtil.getGetUserName();
        if(!StringUtils.isEmpty(userName)){
            edtUserName.setText(userName);
            edtUserName.setSelection(userName.length());
        }
    }

    @Override
    protected void initEvents() {

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = edtUserName.getText().toString().trim();
                pass = edtPass.getText().toString().trim();
                if (validate()) {
                    mPresenter.login(userName, pass);
                }
            }
        });
    }

    private boolean validate() {

        if (StringUtils.isEmpty(userName)) {
            showShortToast(getString(R.string.please_input_user_name));
            return false;
        }

        if (StringUtils.isEmpty(pass)) {
            showShortToast(getString(R.string.please_input_pass));
            return false;
        }

        return true;
    }

    @Override
    public void onLoginSuccess() {

        finish();
        MainActivity.startAction(LoginActivity.this);
    }
}
