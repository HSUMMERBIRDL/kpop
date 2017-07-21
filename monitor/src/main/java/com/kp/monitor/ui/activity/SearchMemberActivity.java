package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
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
import com.kp.monitor.data.vo.MemberVO;
import com.kp.monitor.model.MemberListModel;
import com.kp.monitor.presenter.MemberListPresenter;
import com.kp.monitor.ui.adapter.MemberListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * des:人员查询界面
 * Created by HL
 * on 2017-05-12.
 */

public class SearchMemberActivity extends BaseListActivity<MemberListModel, MemberListPresenter,
        MemberVO> implements View.OnClickListener {

    @Bind(R.id.iv_close)
    CImageButton ivClose;
    @Bind(R.id.edt_search_key)
    EditText edtSearchKey;
    @Bind(R.id.txt_search)
    TextView txtSearch;
    @Bind(R.id.iv_search_clear)
    ImageView searchClear;
    private String searchKey = "";

    private String idCard = "";
    private String name = "";

    public static void startAction(Context context) {

        Intent intent = new Intent(context, SearchMemberActivity.class);
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

        edtSearchKey.setHint(ResourcesUtils.getResourceString(R.string.member_search_tips));

    }

    @Override
    protected void initEvents() {
        super.initEvents();

        txtSearch.setOnClickListener(this);
        searchClear.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        edtSearchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchKey = edtSearchKey.getText().toString().trim();
                if (!StringUtils.isEmpty(searchKey)) {
                    searchClear.setVisibility(View.VISIBLE);
                    if (StringUtils.isNumOrChartersX(searchKey)) {
                        idCard = searchKey;
                        parameters.put(ApiConstants.NAME, "");
                        parameters.put(ApiConstants.ID_CARD, idCard);
                    } else {
                        name = searchKey;
                        parameters.put(ApiConstants.NAME, name);
                        parameters.put(ApiConstants.ID_CARD, "");
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

                        if (StringUtils.isNumOrChartersX(searchKey)) {
                            idCard = searchKey;
                            parameters.put(ApiConstants.NAME, "");
                            parameters.put(ApiConstants.ID_CARD, idCard);
                        } else {
                            name = searchKey;
                            parameters.put(ApiConstants.NAME, name);
                            parameters.put(ApiConstants.ID_CARD, "");
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

        return new MemberListAdapter(mContext, R.layout.list_item_member2,false);
    }


    @Override
    public void returnDataListData(List<MemberVO> dataList) {
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
        params.put(ApiConstants.NAME, name);
        params.put(ApiConstants.ID_CARD, idCard);

        return params;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_search:
                searchKey = edtSearchKey.getText().toString().trim();

                if (StringUtils.isEmpty(searchKey)) {
                    ToastUitl.showLong(ResourcesUtils.getResourceString(R.string
                            .please_input_search_key));
                } else {

                    if (StringUtils.isNumOrChartersX(searchKey)) {
                        idCard = searchKey;
                        parameters.put(ApiConstants.NAME, "");
                        parameters.put(ApiConstants.ID_CARD, idCard);
                    } else {
                        name = searchKey;
                        parameters.put(ApiConstants.NAME, name);
                        parameters.put(ApiConstants.ID_CARD, "");
                    }
                    sendRequest();
                }
                break;
            case R.id.iv_search_clear:
                searchClear.setVisibility(View.GONE);
                edtSearchKey.setText("");
                adapter.clear();
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }
}
