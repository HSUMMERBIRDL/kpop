package com.kp.monitor.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.AndroidUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.AddressItemVO;
import com.kp.monitor.model.AddressListModel;
import com.kp.monitor.presenter.AddressListPresenter;
import com.kp.monitor.ui.adapter.AddressListAdapter;
import com.kp.monitor.ui.dialog.BaseTitleDialog2;

import butterknife.Bind;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * des: 通讯录界面
 * Created by HL
 * on 2017-05-10.
 */

@RuntimePermissions
public class AddressListActivity extends BaseListActivity<AddressListModel, AddressListPresenter, AddressItemVO> {

    @Bind(R.id.ll_search_bar)
    LinearLayout llSearchBar;
    @Bind(R.id.txt_search)
    TextView txtSearch;
    private String phoneNum = "";

    private AddressListAdapter addressListAdapter;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, AddressListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {

        addressListAdapter = new AddressListAdapter(mContext, R.layout.list_item_address2);
        return addressListAdapter;
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        addressListAdapter.setOnActionListener(new AddressListAdapter.OnActionListener() {
            @Override
            public void onItemClick(String phoneNum) {


                AddressListActivity.this.phoneNum = phoneNum;

                if(!StringUtils.isEmpty(phoneNum)){
                    showIfCallDialog();
                }else {
                    showShortToast("无电话");
                }

            }
        });

        llSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchAddressActivity.startAction(mContext);
            }
        });
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchAddressActivity.startAction(mContext);
            }
        });
    }

    private void showIfCallDialog() {

        BaseTitleDialog2 dialog = new BaseTitleDialog2(mContext,true);
        dialog.setTilte("是否拨打电话");
        dialog.setContent("拨打电话 " + phoneNum);
        dialog.setOnDialogClickListener(new BaseTitleDialog2.OnDialogClickListener() {
            @Override
            public void onOkClick() {
                AddressListActivityPermissionsDispatcher.onAcceptCallPhoneWithCheck(AddressListActivity.this);
            }

            @Override
            public void onCancelClick() {

            }
        });
        dialog.show();
    }

    @Override

    protected String getTheTitle() {
        return ResourcesUtils.getResourceString(R.string.title_address_list);

    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onAcceptCallPhone() {
        AndroidUtils.callPhone(phoneNum, AddressListActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddressListActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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
