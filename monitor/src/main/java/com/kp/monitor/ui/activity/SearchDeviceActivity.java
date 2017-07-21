package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.frame.ui.list.BaseListModel;
import com.hl.foundation.library.MemonCache;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.ResourcesUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.widget.CImageButton;
import com.hl.foundation.library.widget.ToastUitl;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.hl.foundation.library.widget.ireyclerview.widget.LoadMoreFooterView;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.vo.DeviceItemVO;
import com.kp.monitor.service.helper.SearchDeviceHelper;
import com.kp.monitor.model.DeviceListModel;
import com.kp.monitor.presenter.DeviceListPresenter;
import com.kp.monitor.ui.adapter.DeviceListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.kp.monitor.R.id.edt_search_key;

/**
 * des:设备查询界面
 * Created by HL
 * on 2017-05-12.
 */

public class SearchDeviceActivity extends BaseListActivity<DeviceListModel, DeviceListPresenter,
        DeviceItemVO> implements View.OnClickListener {

    @Bind(edt_search_key)
    EditText edtSearchKey;

    @Bind(R.id.txt_search)
    TextView txtSearch;

    @Bind(R.id.iv_close)
    CImageButton ivClose;

    @Bind(R.id.iv_search_clear)
    ImageView searchClear;
    private String searchKey = "";

    public static void startAction(Context context) {

        Intent intent = new Intent(context, SearchDeviceActivity.class);
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

        edtSearchKey.setHint(ResourcesUtils.getResourceString(R.string.device_search_tips));

    }

    @Override
    protected void initEvents() {
        super.initEvents();

        txtSearch.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        searchClear.setOnClickListener(this);

        edtSearchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchKey = edtSearchKey.getText().toString().trim();
                if (!StringUtils.isEmpty(searchKey)) {
                    List<DeviceItemVO> list = MemonCache.getIntance().list;
                    List<DeviceItemVO> searchDevice = SearchDeviceHelper.getInstance()
                            .getSearchDevice(searchKey, list);
                    adapter.replaceAll(searchDevice);
                    searchClear.setVisibility(View.VISIBLE);
                } else {
                    searchClear.setVisibility(View.GONE);
                    adapter.clear();
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onRefresh() {

        irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
        adapter.getPageBean().setRefresh(true);
        //发起请求
        mStartPage = 1;
        irc.setRefreshing(true);
        sendSearchRequest();
    }

    @Override
    public void returnDataListData(List<DeviceItemVO> dataList) {
        if (!CollectionUtils.isNullOrEmpty(dataList)) {
            mStartPage++;
            if (adapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                adapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    adapter.replaceAll(dataList);
                } else {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END); //  没有数据了
                }
            }
        } else {  //  没有数据了
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
            irc.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        LogUtils.logi(TAG, "-------onLoadMore----------");
        adapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        parameters.put(BaseListModel.START_PAGE, mStartPage);
        sendSearchRequest();
    }


    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new DeviceListAdapter(mContext, R.layout.list_item_device);
    }


    @Override
    protected Map<String, Object> getAddParams() {

        Map<String, Object> params = new HashMap<>();

        return params;
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
                    sendSearchRequest();
                }
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }

    private void sendSearchRequest() {
        parameters.put(ApiConstants.DEVICE_CODE, searchKey);
        mPresenter.sendSearchDeviceListRequest(parameters);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStartPage = 1;
    }
}
