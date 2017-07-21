package com.kp.monitor.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.AndroidUtils;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.UIUtil;
import com.hl.foundation.library.widget.CImageButton;
import com.hl.foundation.library.widget.ToastUitl;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview
        .CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.vo.AddressItemVO;
import com.kp.monitor.model.AddressListModel;
import com.kp.monitor.presenter.AddressListPresenter;
import com.kp.monitor.ui.adapter.AddressListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * des:通讯录查询界面
 * Created by HL
 * on 2017-05-12.
 */

@RuntimePermissions
public class SearchAddressActivity extends BaseListActivity<AddressListModel,
        AddressListPresenter, AddressItemVO> implements View.OnClickListener {

    @Bind(R.id.iv_close)
    CImageButton ivClose;
    @Bind(R.id.edt_search_key)
    EditText edtSearchKey;
    @Bind(R.id.txt_search)
    TextView txtSearch;
    @Bind(R.id.iv_search_clear)
    ImageView searchClear;
    private String searchKey = "";

    private String addressPhone = "";
    private String addressName = "";

    private AddressListAdapter addressListAdapter;
    private String officePhoneNum = "";

    public static void startAction(Context context) {

        Intent intent = new Intent(context, SearchAddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_list;
    }

    @Override
    protected boolean isNeedTitle() {
        return false;
    }

    @Override
    protected boolean isSetPullRefresh() {
        return true;
    }

    @Override
    protected boolean isGetListRightNow() {
        return false;
    }


    @Override
    protected void initViews() {
        super.initViews();

        edtSearchKey.setHint(ResourcesUtils.getResourceString(R.string.address_search_tips));

    }

    @Override
    protected void initEvents() {
        super.initEvents();
        searchClear.setOnClickListener(this);
        txtSearch.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        addressListAdapter.setOnActionListener(new AddressListAdapter.OnActionListener() {
            @Override
            public void onItemClick(String phoneNum) {
                SearchAddressActivity.this.officePhoneNum = phoneNum;
                SearchAddressActivityPermissionsDispatcher.onAcceptCallPhoneWithCheck
                        (SearchAddressActivity.this);
            }
        });

        edtSearchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchKey = edtSearchKey.getText().toString().trim();
                if (!StringUtils.isEmpty(searchKey)) {
                    searchClear.setVisibility(View.VISIBLE);
                    if (StringUtils.isNum(searchKey)) {
                        addressPhone = searchKey;
                        parameters.put(ApiConstants.ADDRESS_NAME, "");
                        parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, addressPhone);
                    } else {
                        addressName = searchKey;
                        parameters.put(ApiConstants.ADDRESS_NAME, addressName);
                        parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, "");
                    }

                    adapter.getPageBean().setRefresh(true);
                    resetPageParameter();

                    sendRequest();
                } else {
                    adapter.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        edtSearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchKey = edtSearchKey.getText().toString().trim();
                    if (!StringUtils.isEmpty(searchKey)) {
                        UIUtil.hideSoftInput(mContext, edtSearchKey);

                        if (StringUtils.isNum(searchKey)) {
                            addressPhone = searchKey;
                            parameters.put(ApiConstants.ADDRESS_NAME, "");
                            parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, addressPhone);
                        } else {
                            addressName = searchKey;
                            parameters.put(ApiConstants.ADDRESS_NAME, addressName);
                            parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, "");
                        }
                        sendRequest();
                    } else {
                        ToastUitl.showLong(ResourcesUtils.getResourceString(R.string
                                .please_input_search_key));
                    }

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {

        addressListAdapter = new AddressListAdapter(mContext, R.layout.list_item_address2);
        return addressListAdapter;
    }


    @Override
    public void returnDataListData(List<AddressItemVO> dataList) {
        super.returnDataListData(dataList);

        if (!CollectionUtils.isNullOrEmpty(dataList)) {
            if (StringUtils.isEmpty(searchKey)) {
                adapter.clear();
            }
        }
    }


    @Override
    protected Map<String, Object> getAddParams() {

        Map<String, Object> params = new HashMap<>();
        params.put(ApiConstants.DEP_ID, "");
        params.put(ApiConstants.NAME, addressName);
        params.put(ApiConstants.ID_CARD, addressPhone);

        return params;
    }


    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onAcceptCallPhone() {
        AndroidUtils.goCallPhone(officePhoneNum, SearchAddressActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SearchAddressActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_clear:
                searchClear.setVisibility(View.GONE);
                edtSearchKey.setText("");
                adapter.clear();
                break;
            case R.id.txt_search:
                searchKey = edtSearchKey.getText().toString().trim();

                if (StringUtils.isEmpty(searchKey)) {
                    ToastUitl.showLong(ResourcesUtils.getResourceString(R.string
                            .please_input_search_key));
                } else {

                    if (StringUtils.isNum(searchKey)) {
                        addressPhone = searchKey;
                        parameters.put(ApiConstants.ADDRESS_NAME, "");
                        parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, addressPhone);
                    } else {
                        addressName = searchKey;
                        parameters.put(ApiConstants.ADDRESS_NAME, addressName);
                        parameters.put(ApiConstants.ADDRESS_OFFICE_PHONE, "");
                    }
                    sendRequest();
                }
                break;
            case R.id.iv_close:
                finish();
                break;

        }
    }
}
