package com.kp.monitor.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.base.BaseFragment;
import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.R;
import com.kp.monitor.contract.MemberBasicInfoContract;
import com.kp.monitor.data.vo.MemberVO;
import com.kp.monitor.model.MemberBasicInfoModel;
import com.kp.monitor.presenter.MemberBasicInfoPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * des:人员基本信息界面
 * Created by HL
 * on 2017-05-22.
 */

public class MemberBasicInfoFragment extends BaseFragment<MemberBasicInfoModel, MemberBasicInfoPresenter> implements MemberBasicInfoContract.View {

    public static final String MEMBER_ID = "member_id";

    @Bind(R.id.txt_card_id)
    TextView txtCardId;
    @Bind(R.id.txt_member_type)
    TextView txtMemberType;
    @Bind(R.id.txt_address)
    TextView txtAddress;
    @Bind(R.id.txt_belong_address)
    TextView txtBelongAddress;
    @Bind(R.id.txt_rail_type)
    TextView txtRailType;
    @Bind(R.id.txt_area_fence)
    TextView txtAreaFence;
    @Bind(R.id.txt_circle_radius)
    TextView txtCircleRadius;
    @Bind(R.id.txt_device_num)
    TextView txtDeviceNum;
    @Bind(R.id.txt_lon_lat)
    TextView txtLonLat;
    @Bind(R.id.ll_radius)
    LinearLayout llRadius;
    @Bind(R.id.ll_area_fence)
    LinearLayout llAreaFence;
    @Bind(R.id.txt_name)
    TextView txtName;
    @Bind(R.id.txt_age)
    TextView txtAge;
    @Bind(R.id.img_sex)
    ImageView imgSex;

    private String memberId = "";


    public static MemberBasicInfoFragment newInstance(String memberId) {
        MemberBasicInfoFragment fragment = new MemberBasicInfoFragment();
        Bundle args = new Bundle();
        args.putString(MEMBER_ID, memberId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getFromArguments(Bundle arguments) {

        memberId = arguments.getString(MEMBER_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member_info;
    }

    @Override
    protected void initViews() {

        mPresenter.sendSendMemberBasicInfo(memberId);
    }

    @Override
    public void onGetMemberBasicInfoComplete(MemberVO memberVO) {

        if (null != memberVO) {

            String name = memberVO.getName();
            String age = memberVO.getAge();
            String cardId = memberVO.getCardId();
            String memberType = memberVO.getMemberType();
            String address = memberVO.getAddress();
            int sex = memberVO.getSex();
//            String remark = ResourcesUtils.getResourceString(R.string.member_info_remark) + ": " + memberVO.getRemark();

            if (0 == sex) {
                imgSex.setImageResource(R.drawable.header_defult_men);
            } else if (1 == sex) {
                imgSex.setImageResource(R.drawable.header_defult_women);
            }


            txtName.setText(name);

            if (StringUtils.isEmpty(age)) {
                txtAge.setText("年龄：无");
            } else {
                txtAge.setText("年龄：" + age);
            }


            txtCardId.setText(cardId);
            txtMemberType.setText(memberType);
            txtAddress.setText(address);


            txtBelongAddress.setText(memberVO.getBelongArea());
            txtRailType.setText(memberVO.getFenceType());

            String deviceNum = memberVO.getDeviceNum();
            if (StringUtils.isEmpty(deviceNum)) {
                txtDeviceNum.setText("无绑定设备");
            } else {
                txtDeviceNum.setText(deviceNum);
            }


            int fenceTypeType = memberVO.getFenceTypeType();

            if (fenceTypeType == 1) { // 封闭区域
                llRadius.setVisibility(View.GONE);
                llAreaFence.setVisibility(View.VISIBLE);
                txtAreaFence.setText(memberVO.getAreaFence());

            } else if (fenceTypeType == 0) { // 圆形围栏
                llAreaFence.setVisibility(View.GONE);
                llRadius.setVisibility(View.VISIBLE);
                txtCircleRadius.setText(memberVO.getCircleRaius() + "米"); // memberVO.getCircleRaius() 如果这位空（比如后台没有改字段的返回）  则会显示为null米  如果只是setText(memberVO.getCircleRaius()) 空的时候则不会setText进去
            }

            // 防止出现null 其实没什么用  不可能所有的都去判断  如果出现null其实也很大问题  况且这种问题是很少出现的
            String lng = memberVO.getLng();
            if (!StringUtils.isEmpty(lng)) {
                txtLonLat.setText("[" + memberVO.getLng() + "，" + memberVO.getLat() + "]");
            }


        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
