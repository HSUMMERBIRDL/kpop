package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.kp.monitor.R;
import com.kp.monitor.contract.MemberDetailContract;
import com.kp.monitor.data.vo.MemberVO;
import com.kp.monitor.model.MemberDetailModel;
import com.kp.monitor.presenter.MemberDetailPresenter;
import com.kp.monitor.ui.BaseAppActivity;
import com.kp.monitor.ui.adapter.MyFragmentPagerAdapter;
import com.kp.monitor.ui.fragment.MemberBasicInfoFragment;
import com.kp.monitor.ui.fragment.RelationShipInfoFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class MemberDetailActivity extends BaseAppActivity<MemberDetailModel, MemberDetailPresenter> implements MemberDetailContract.View {

    public static final String MEMBER_ID = "member_id";
    @Bind(R.id.tab_member_info)
    TabLayout tabMemberInfo;
    @Bind(R.id.vp_member_info)
    ViewPager vpMemberInfo;
    @Bind(R.id.txt_back)
    TextView txtBack;


    private String memberId = "";
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void startAction(Context context, String memberId) {

        Intent intent = new Intent(context, MemberDetailActivity.class);
        intent.putExtra(MEMBER_ID, memberId);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        memberId = intent.getStringExtra(MEMBER_ID);

        initFragmnetList();
    }

    private void initFragmnetList() {

        mTitleList.add("基本信息");
        mTitleList.add("家庭成员");

        mFragments.add(MemberBasicInfoFragment.newInstance(memberId));
        mFragments.add(new RelationShipInfoFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);

        vpMemberInfo.setAdapter(adapter);
        vpMemberInfo.setOffscreenPageLimit(2);
        adapter.notifyDataSetChanged();
        tabMemberInfo.setTabMode(TabLayout.MODE_FIXED);
        tabMemberInfo.setupWithViewPager(vpMemberInfo);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void initViews() {

//        mPresenter.sendGetMemberDetail(memberId);
    }

    @Override
    protected void initEvents() {

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onGetMemberDetailComplete(MemberVO memberVO) {

    }

}
