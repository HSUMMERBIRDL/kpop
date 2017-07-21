package com.kp.monitor.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hl.foundation.frame.ui.widget.BaseFrameLayout;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.R;
import com.kp.monitor.data.vo.CunVO;
import com.kp.monitor.data.vo.XianVO;
import com.kp.monitor.data.vo.XiangVO;
import com.kp.monitor.ui.adapter.CunListAdapter;
import com.kp.monitor.ui.adapter.XianListAdapter;
import com.kp.monitor.ui.adapter.XiangListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.kp.monitor.R.id.fl_cun;

/**
 * des:地区选择界面 县 乡 村
 * Created by HL
 * on 2017-05-16.
 */

public class ZoneSelectView extends BaseFrameLayout {

    private static final String TAG = ZoneSelectView.class.getSimpleName();
    private FrameLayout flXian;
    private RecyclerView rlvXian;

    private FrameLayout flXiang;
    private RecyclerView rlvXiang;

    private FrameLayout flCun;
    private RecyclerView rlvCun;

    private TextView txtCancel;
    private TextView txtOk;

    private View viewDown;

    private XianListAdapter xianListAdapter;
    private XiangListAdapter xiangListAdapter;
    private CunListAdapter cunListAdapter;

    private List<XianVO> xianList = new ArrayList<>();
    private List<XiangVO> xiangList = new ArrayList<>();
    private List<CunVO> cunList = new ArrayList<>();
    private OnActionListener listener;


    private XianVO currentSelectXian = null;  //  当前被选中的县
    private XiangVO currentSelectXiang = null;  //  当前被选中的乡
    private CunVO currentSelectCun = null;  //  当前被选中的村


    public ZoneSelectView(Context context) {
        super(context);
    }

    public ZoneSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoneSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.view_zone_select;
    }

    @Override
    protected void initViews() {

        flXian = $(R.id.fl_xian);
        rlvXian = $(R.id.rlv_xian);
        flXian.setVisibility(VISIBLE);

        flXiang = $(R.id.fl_xiang);
        rlvXiang = $(R.id.rlv_xiang);
        flXiang.setVisibility(GONE);

        flCun = $(fl_cun);
        rlvCun = $(R.id.rlv_cun);
        flCun.setVisibility(GONE);

        txtCancel = $(R.id.txt_cancel);
        txtOk = $(R.id.txt_ok);

        viewDown = $(R.id.view_down);

        rlvXian.setLayoutManager(new LinearLayoutManager(mContext));
        rlvXiang.setLayoutManager(new LinearLayoutManager(mContext));
        rlvCun.setLayoutManager(new LinearLayoutManager(mContext));
    }


    public void setOnActionListener(OnActionListener listener) {
        this.listener = listener;
    }

    public void show() {

        setVisibility(View.VISIBLE);
    }

    public interface OnActionListener {
        void onOkClick(String selectId, String name);

        void onCancelClick();
    }

    public static final String INVALID_ID = "无效ID";

    @Override
    protected void initEvents() {

        viewDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                destory();
                setVisibility(GONE);
            }
        });

        txtOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectId = INVALID_ID;
                String selectName = "";  // 纯粹为了 调试打印名字

                String xianId = "";
                String xiangId = "";
                String cunId = "";

                String xianName = "";
                String xiangName = "";
                String cunName = "";

                if (null != currentSelectCun) {
                    cunId = currentSelectCun.getId();
                    cunName = currentSelectCun.getName();
                }

                if (null != currentSelectXiang) {
                    xiangId = currentSelectXiang.getId();
                    xiangName = currentSelectXiang.getName();
                }

                if (null != currentSelectXian) {
                    xianId = currentSelectXian.getId();
                    xianName = currentSelectXian.getName();
                }

                if (null != currentSelectCun) {  //  当时界面有选中的村
                    if (StringUtils.equals(cunId, CunVO.ID_CUN_ALL)) {  // 选了不限村
                        // 那么必定乡是选了的

                        if (StringUtils.isEmpty(xiangId)) {
                            LogUtils.loge(TAG, "既然有选中的村 那么不可能有没有选中的乡");
                        }

                        if (StringUtils.equals(xiangId, XiangVO.ID_XIANG_ALL)) {
                            LogUtils.loge(TAG, "选的村是不限，而选的乡也是不限，如果选的乡是不限，那么就不应该出现村列表的view");
                        } else {
                            selectId = xiangId;  //  那么id就是乡id
                            selectName = xiangName;  //  那么id就是乡id
                        }
                    } else {
                        selectId = cunId;  //  既然选的不是不限村，那么id就是村ID
                        selectName = cunName;  //  既然选的不是不限村，那么id就是村ID
                    }
                } else {

                    if (null != currentSelectXiang) { // 当时界面有选中的乡

                        if (StringUtils.isEmpty(xiangId)) {
                            LogUtils.loge(TAG, "既然有选中的乡 那么不可能有没有选中的县");
                        }

                        if (StringUtils.equals(xiangId, XiangVO.ID_XIANG_ALL)) {  // 选了不限乡

                            if (StringUtils.equals(xianId, XianVO.ID_XIAN_ALL)) {
                                LogUtils.loge(TAG, "选的乡是不限，而选的县也是不限，如果选的县是不限，那么就不应该出现乡列表的view");
                            } else {
                                selectId = xianId;  //  那么id就是乡id
                                selectName = xianName;  //  那么id就是乡id
                            }

                        } else {

                            selectId = xiangId;  //  既然选的不是不限乡，那么id就是乡ID
                            selectName = xiangName;  //  既然选的不是不限乡，那么id就是乡ID
                        }
                    } else {   //  如果连选的乡都没有

                        if (null != currentSelectXian) {  // 如果有选中的县

                            if (StringUtils.equals(xianId, XiangVO.ID_XIANG_ALL)) {

                                selectId = ""; // 选的是不限 那么返回的是全部
                                selectName = ""; // 选的是不限 那么返回的是全部
                            } else {

                                selectId = xianId; // 返回该县
                                selectName = xianName; // 返回该县
                            }
                        } else {
                            selectId = ""; //  该情况不会出现
                            selectName = ""; //  该情况不会出现
                        }
                    }
                }

                LogUtils.logi(TAG, "选中的id:" + selectId + " 选中的名字: " + selectName);


                if (StringUtils.equals(selectId, XianVO.ID_XIAN_ALL)) {
                    selectName = superiorAreaName;
                }

                if ((StringUtils.equals(selectId, XianVO.ID_XIAN_ALL)) || (StringUtils.equals(selectId, XiangVO.ID_XIANG_ALL)) || (StringUtils.equals(selectId, CunVO.ID_CUN_ALL))) {
                    selectId = "";
                }

                LogUtils.logi(TAG, "选中的id:" + selectId + " 选中的名字: " + selectName);

                destory();

                if (null != listener) {
                    listener.onOkClick(selectId, selectName);
                }
            }
        });

        txtCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                destory();
                if (null != listener) {
                    listener.onCancelClick();
                }
            }
        });
    }

    public void destory() {

        try {
            if (null != currentSelectCun) {
                currentSelectCun.setSelected(false);
                currentSelectCun = null;
            }

            if (null != currentSelectXiang) {
                currentSelectXiang.setSelected(false);
                currentSelectXiang = null;
            }

            if (null != currentSelectXian) {
                currentSelectXian.setSelected(false);
                currentSelectXian = null;
            }

            if (!CollectionUtils.isNullOrEmpty(xianList)) {
                XianVO xianVO = xianList.get(0);
                xianVO.setSelected(true);
                currentSelectXian = xianVO;
            } else {
                currentSelectXian = null;
            }


            cunListAdapter.notifyDataSetChanged();
            xiangListAdapter.notifyDataSetChanged();
            xianListAdapter.notifyDataSetChanged();

            flXiang.setVisibility(GONE);
            flCun.setVisibility(GONE);

            setVisibility(GONE);
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        } finally {
            setVisibility(GONE);
        }
    }


    private String superiorAreaName;

    public void init(List<XianVO> xianList1, String superiorAreaName) {

        try {


            this.superiorAreaName = superiorAreaName;

            if (!CollectionUtils.isNullOrEmpty(xianList1)) {

                xianList.clear();
                xianList.addAll(xianList1);
                XianVO xianVO = xianList.get(0);
                xianVO.setSelected(true);
                currentSelectXian = xianVO;  //  设置第一个县（不限县） 我当前选择的县

                if (xianList.size() > 1) {
                    xiangList.addAll(xianList.get(1).getXiangList());
                }

                if (xiangList.size() > 1) {
                    cunList.addAll(xiangList.get(1).getCunList());
                }

            }

            xianListAdapter = new XianListAdapter(mContext, R.layout.list_item_zone, xianList);
            xiangListAdapter = new XiangListAdapter(mContext, R.layout.list_item_zone, xiangList);
            cunListAdapter = new CunListAdapter(mContext, R.layout.list_item_zone, cunList);

            rlvXian.setAdapter(xianListAdapter);
            rlvXiang.setAdapter(xiangListAdapter);
            rlvCun.setAdapter(cunListAdapter);

            xianListAdapter.setOnItemClickListener(new XianListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(XianVO xian) {

                    //  某个县被点击 其余县则设置为未被点击
                    //  另一个县里面所有的 不管是县、乡、村都要设置成没有被点击(其实让其保存以前的状态也没关系)
                    //  乡列表替换为该县下面的乡列表
                    //  村列表替换为选中的乡的村列表 如果没有选中的乡 则不显示

                    //  如果当前选中的县不为空 且为当前选中的 则直接返回
                    if (null != currentSelectXian) {
                        if (currentSelectXian == xian) {
                            return;
                        }
                    }

                    // 1: 既然选中的是另一个县了 那就将当前 选中的 县 乡 村 （如果不为空） 全部设为false 并且暂时将他们置为null
                    if (null != currentSelectXian) {
                        currentSelectXian.setSelected(false);
                    }
                    if (null != currentSelectXiang) {
                        currentSelectXiang.setSelected(false);
                    }
                    if (null != currentSelectCun) {
                        currentSelectCun.setSelected(false);
                    }
                    currentSelectXian = null;
                    currentSelectXiang = null;
                    currentSelectCun = null;

                    flCun.setVisibility(GONE); // 村的界面不需要了

                    //  1: 对县进行处理
                    xian.setSelected(true);
                    currentSelectXian = xian;
                    xianListAdapter.notifyDataSetChanged();

                    // 2: 对乡进行处理
                    String id = xian.getId();
                    if (StringUtils.equals(id, XianVO.ID_XIAN_ALL)) { // 点击的是不限县 那乡不需要了
                        flXiang.setVisibility(GONE);
                    } else {
                        List<XiangVO> xiangList = xian.getXiangList();
                        if (!CollectionUtils.isNullOrEmpty(xiangList)) {
                            XiangVO buXianXiang = xiangList.get(0);
                            buXianXiang.setSelected(true);

                            currentSelectXiang = buXianXiang;
                            flXiang.setVisibility(VISIBLE);
                            xiangListAdapter.replaceAll(xiangList);
                        } else {  // 这代表着该村下面什么都没有  其实不会出现这种情况 毕竟至少加了个不限
                            currentSelectXiang = null;
                            flXiang.setVisibility(GONE);
                        }
                    }

                }
            });

            xiangListAdapter.setOnItemClickListener(new XiangListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(XiangVO xiangVO) {


                    // 点击的是当前的乡 不做任何处理
                    if (null != currentSelectXiang) {
                        if (xiangVO == currentSelectXiang) {
                            return;
                        }
                    }

                    if (null != currentSelectXiang) {
                        currentSelectXiang.setSelected(false);
                    }
                    if (null != currentSelectCun) {
                        currentSelectCun.setSelected(false);
                    }
                    currentSelectXiang = null;
                    currentSelectCun = null;


                    // 1:乡处理
                    xiangVO.setSelected(true);
                    currentSelectXiang = xiangVO;
                    xiangListAdapter.notifyDataSetChanged();

                    // 村处理
                    String id = xiangVO.getId();
                    if (StringUtils.equals(id, XiangVO.ID_XIANG_ALL)) {  // 点击的是不限
                        flCun.setVisibility(GONE);
                    } else {
                        List<CunVO> cunList = xiangVO.getCunList();
                        if (!CollectionUtils.isNullOrEmpty(cunList)) {

                            if (cunList.size() == 1) {  // 等于1 说明只有不限  没有别的下一级单位了 直接返回
                                flCun.setVisibility(GONE);
                                return;
                            }

                            CunVO cunVO = cunList.get(0);  // 第一个按理就是不限
                            cunVO.setSelected(true);
                            currentSelectCun = cunVO;
                            flCun.setVisibility(VISIBLE);
                            cunListAdapter.replaceAll(cunList);
                        } else {
                            flCun.setVisibility(GONE);
                        }
                    }
                }
            });

            cunListAdapter.setOnItemClickListener(new CunListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(CunVO cun) {
                    // 被选中的县下面 其余的乡全部设置成未被点击

                    Log.i(TAG, "------点击村-------------------------------------------------");
                    Log.i(TAG, "当前被选中的乡：" + currentSelectXiang);
                    Log.i(TAG, "村情况1：" + currentSelectXiang.getCunList());

                    // 点击的是当前的村 不做任何处理
                    if (null != currentSelectCun) {
                        if (cun == currentSelectCun) {
                            return;
                        }
                    }

                    if (null != currentSelectCun) {
                        currentSelectCun.setSelected(false);
                    }
                    cun.setSelected(true);
                    currentSelectCun = cun;
                    cunListAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        }
    }
}
