package com.kp.monitor.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.library.utils.AndroidUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.R;
import com.kp.monitor.contract.PersonInfoContract;
import com.kp.monitor.data.vo.UserVO;
import com.kp.monitor.model.PersonInfoModel;
import com.kp.monitor.presenter.PersonInfoPresenter;
import com.kp.monitor.ui.BaseAppActivity;
import com.kp.monitor.ui.dialog.BaseTitleDialog2;

import butterknife.Bind;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * des:个人信息界面
 * Created by HL
 * on 2017-06-05.
 */
@RuntimePermissions
public class PersonInfoActivity extends BaseAppActivity<PersonInfoModel, PersonInfoPresenter> implements PersonInfoContract.View {

    @Bind(R.id.txt_nick_name)
    TextView txtNickName;
    @Bind(R.id.txt_real_name)
    TextView txtRealName;
    @Bind(R.id.txt_sex)
    TextView txtSex;
    @Bind(R.id.txt_phone)
    TextView txtPhone;
    @Bind(R.id.txt_office_phone)
    TextView txtOfficePhone;
    @Bind(R.id.txt_email)
    TextView txtEmail;
    @Bind(R.id.txt_dept_name)
    TextView txtDeptName;
    @Bind(R.id.txt_create_time)
    TextView txtCreateTime;
    @Bind(R.id.img_header)
    ImageView imgHeader;
    @Bind(R.id.ll_office_phone)
    LinearLayout llOfficePhone;
    private String officePhoneNum="";

    public static void startAction(Context context) {

        Intent intent = new Intent(context, PersonInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initDatas() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initViews() {
        mPresenter.getUserInfo();
    }

    @Override
    protected void initEvents() {

        llOfficePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!StringUtils.isEmpty(officePhoneNum)  || StringUtils.equals(officePhoneNum,"无")){
                    showIfCallDialog();
                }else {
                    showShortToast("无办公室电话");
                }

            }
        });
    }

    private void showIfCallDialog() {

        BaseTitleDialog2 dialog = new BaseTitleDialog2(mContext,true);
        dialog.setTilte("是否拨打电话");
        dialog.setContent("拨打电话 " + officePhoneNum);
        dialog.setOnDialogClickListener(new BaseTitleDialog2.OnDialogClickListener() {
            @Override
            public void onOkClick() {
                PersonInfoActivityPermissionsDispatcher.onAcceptCallPhoneWithCheck(PersonInfoActivity.this);
            }

            @Override
            public void onCancelClick() {

            }
        });
        dialog.show();
    }

    @Override
    public void onGetUserInfoComplete(UserVO vo) {

        LogUtils.logi(TAG, "个人信息: " + vo.toString());

        if (null != vo) {

            updateInfoView(vo);
        }
    }

    private void updateInfoView(UserVO vo) {

        if (null != vo) {


            officePhoneNum = vo.getOfficePhone();

            String sex = vo.getSex();
            if (StringUtils.equals(sex, "女")) {
                imgHeader.setImageResource(R.drawable.header_defult_women);
            } else {
                imgHeader.setImageResource(R.drawable.header_defult_men);
            }
            txtNickName.setText("用户名: " + vo.getUserName());
            txtRealName.setText(vo.getUserRealName());
            txtSex.setText(sex);
            txtPhone.setText(vo.getPhone());
            txtOfficePhone.setText(officePhoneNum);
            txtEmail.setText(vo.getEmail());
            txtDeptName.setText(vo.getDeptName());
            txtCreateTime.setText("录入时间: " + vo.getCreateTime());
        }
    }


    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onAcceptCallPhone() {

        AndroidUtils.goCallPhone(officePhoneNum, PersonInfoActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonInfoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showRationaleForCallPhone(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("请允许电话权限！")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行请求
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void onCallPhoneDenied() {
        showShortToast("你必须给予电话权限才能打电话");
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onCallPhoneNeverAskAgain() {

        showShortToast("你必须给予电话权限才能打电话");
    }
}
