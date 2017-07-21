package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.list.BaseListActivity;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.widget.CLinearLayout;
import com.hl.foundation.library.widget.CTextButton;
import com.hl.foundation.library.widget.LoadingTip;
import com.hl.foundation.library.widget.ireyclerview.IRecyclerView;
import com.hl.foundation.library.widget.ireyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.kp.monitor.R;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.vo.MemberItemVO;
import com.kp.monitor.data.vo.XianVO;
import com.kp.monitor.model.MemberListModel;
import com.kp.monitor.presenter.MemberListPresenter;
import com.kp.monitor.service.ZoneService;
import com.kp.monitor.ui.adapter.MemberListAdapter;
import com.kp.monitor.ui.widget.ZoneSelectView;
import com.kp.monitor.view.MemberListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * des: 人员列表界面
 * Created by HL
 * on 2017-05-10.
 */

public class MemberListActivity extends BaseListActivity<MemberListModel, MemberListPresenter, MemberItemVO> implements MemberListView {


    public static final String MEMBER_TYPE = "member_type";
    public static final String IS_ATTACH_MONITOR_COMMAND = "is_attach_monitor_command";
    public static final String NOT_UPLOAD_FILE_NUM = "not_upload_file_num";

    public static final int MEMBER_TYPE_ALL = 0;  // 所有人员
    public static final int MEMBER_TYPE_UNUSUAL = 1; // 异常人员

    @Bind(R.id.ll_search_bar)
    LinearLayout llSearchBar;
    @Bind(R.id.zsv_select_zone)
    ZoneSelectView zsvSelectZone;
    @Bind(R.id.txt_city_select)
    CTextButton txtCitySelect;
    @Bind(R.id.ll_city_select)
    CLinearLayout llCitySelect;
    @Bind(R.id.txt_back)
    TextView txtBack;
    @Bind(R.id.img_file_num)
    ImageView imgFileNum;
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    Badge badge;

    private List<XianVO> xianList = new ArrayList<>();


    private int memberType = 0;
    private boolean isAttachMonitorCommand;
    private String deptId = ""; // 区域id（最终最小的那个区域ID，上级ID无须传递）

    private int notUploadFileNum;

    /**
     * @param context
     * @param memberType
     * @param isAttachMonitorCommand 是否附带发送监听命令的功能 人员管理进入不需要  监听进入需要发送
     */
    public static void startAction(Context context, int memberType, boolean isAttachMonitorCommand) {

        Intent intent = new Intent(context, MemberListActivity.class);
        intent.putExtra(MEMBER_TYPE, memberType);
        intent.putExtra(IS_ATTACH_MONITOR_COMMAND, isAttachMonitorCommand);
        context.startActivity(intent);
    }

    public static void startAction(Context context, int memberType, boolean isAttachMonitorCommand,int notUploadFileNum) {

        Intent intent = new Intent(context, MemberListActivity.class);
        intent.putExtra(MEMBER_TYPE, memberType);
        intent.putExtra(IS_ATTACH_MONITOR_COMMAND, isAttachMonitorCommand);
        intent.putExtra(NOT_UPLOAD_FILE_NUM, notUploadFileNum);
        context.startActivity(intent);
    }


    @Override
    protected boolean isNeedTitle() {
        return false;
    }

    @Override
    protected void initDatas() {
        super.initDatas();

        Intent intent = getIntent();
        notUploadFileNum = intent.getIntExtra(NOT_UPLOAD_FILE_NUM, 0);
        memberType = intent.getIntExtra(MEMBER_TYPE, 0);
        isAttachMonitorCommand = intent.getBooleanExtra(IS_ATTACH_MONITOR_COMMAND, false);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_list;
    }

    @Override
    protected void initViews() {
        super.initViews();
        zsvSelectZone.setVisibility(View.GONE);

        if(isAttachMonitorCommand){
            imgFileNum.setVisibility(View.VISIBLE);
            badge = new QBadgeView(mContext).bindTarget(imgFileNum).setBadgeBackgroundColor
                    (Color.RED).setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(2, 5,
                    true);
            badge.setBadgeNumber(notUploadFileNum);
        }else{
            imgFileNum.setVisibility(View.GONE);
        }
        initZsvSelectZone();
    }


    private void initZsvSelectZone() {

        ZoneService.getInstance().getXianList(new ZoneService.OnZoneInfoCompleteListener() {
            @Override
            public void onZoneInfoComplete(List<XianVO> xianVOs, String superiorAreaName) {
                txtCitySelect.setText(superiorAreaName);
                xianList.clear();
                xianList.addAll(xianVOs);
                zsvSelectZone.init(xianList, superiorAreaName);
            }
        });

    }

    @Override
    protected void initEvents() {
        super.initEvents();

        if(isAttachMonitorCommand){
            imgFileNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    RecordTestActivity.startAction(mContext);
                    RecordInfoActivity.startAction(mContext,notUploadFileNum);
                }
            });
        }

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        llSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchMemberActivity.startAction(mContext);
            }
        });

        llCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zsvSelectZone.getVisibility() == View.GONE) {

                    if (CollectionUtils.isNullOrEmpty(xianList)) {  // 比如没有网络进入 本地也没有的情况下 要重先去拉数据
                        initZsvSelectZone();
                    }

                    zsvSelectZone.show();
                } else {
                    zsvSelectZone.destory();
                }
            }
        });

        txtCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zsvSelectZone.getVisibility() == View.GONE) {
                    if (CollectionUtils.isNullOrEmpty(xianList)) {
                        initZsvSelectZone();
                    }
                    zsvSelectZone.show();
                } else {
                    zsvSelectZone.destory();
                }
            }
        });

        zsvSelectZone.setOnActionListener(new ZoneSelectView.OnActionListener() {

            @Override
            public void onOkClick(String selectId, String name) {

                txtCitySelect.setText(name);
                parameters.put(ApiConstants.DEP_ID, selectId);
                adapter.getPageBean().setRefresh(true);
                resetPageParameter();
                sendRequest();
            }

            @Override
            public void onCancelClick() {

            }
        });
    }

    @Override
    protected CommonRecycleViewAdapter getAdapter() {
        return new MemberListAdapter(mContext, R.layout.list_item_member2, isAttachMonitorCommand);
    }

    @Override
    protected Map<String, Object> getAddParams() {

        Map<String, Object> params = new HashMap<>();

        params.put(ApiConstants.DEP_ID, deptId);
//        params.put(ApiConstants.SEARCH_KEY, "");

//        if (memberType == MEMBER_TYPE_ALL) {
//            params.put(ApiConstants.MEMBER_TYPE, ApiConstants.C_MEMBER_TYPE_ALL);
//        } else if (memberType == MEMBER_TYPE_UNUSUAL) {
//            params.put(ApiConstants.MEMBER_TYPE, ApiConstants.C_MEMBER_TYPE_UNUSUAL);
//        }

        return params;
    }

    @Override
    public void onNotUploadFileUpdate(int currentNum) {

        updateNotUploadFileNumView(currentNum);
    }

    private void updateNotUploadFileNumView(int currentNum) {

        // 小于等于0会自动隐藏
        notUploadFileNum = currentNum;
        badge.setBadgeNumber(currentNum);
    }

    @Override
    protected void onDestroy() {
        if(null != zsvSelectZone){
            zsvSelectZone.destory();
        }
        super.onDestroy();
    }
}
