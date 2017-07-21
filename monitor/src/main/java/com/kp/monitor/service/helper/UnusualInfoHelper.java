package com.kp.monitor.service.helper;

import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.data.vo.UnusualHandleStatue;
import com.kp.monitor.data.vo.UnusualStatue;
import com.kp.monitor.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * des:异常信息的吧帮助类
 * Created by HL
 * on 2017-05-31.
 */

public class UnusualInfoHelper {

    private static final String TAG = UnusualInfoHelper.class.getSimpleName();
    public static int baseTime;

    /**
     * 获取异常状态列表
     *
     * @return
     */
    public static List<UnusualStatue> getUnusualStatues() {

        List<UnusualStatue> unusualStatueList = new ArrayList<>();

        UnusualStatue statue1 = new UnusualStatue();
        statue1.setId(UnusualStatue.EXCEPTION_TYPE_OVER);
        statue1.setDes("越栏");

        UnusualStatue statue2 = new UnusualStatue();
        statue2.setId(UnusualStatue.EXCEPTION_TYPE_OFFLINE);
        statue2.setDes("掉线");

        UnusualStatue statue3 = new UnusualStatue();
        statue3.setId(UnusualStatue.EXCEPTION_TYPE_OFF_WRIST);
        statue3.setDes("脱腕");

        UnusualStatue statue4 = new UnusualStatue();
        statue4.setId(UnusualStatue.EXCEPTION_TYPE_LOWER_POWER);
        statue4.setDes("低电");

        UnusualStatue statue5 = new UnusualStatue();
        statue5.setId(UnusualStatue.EXCEPTION_TYPE_LONG_LESS_MOVE);
        statue5.setDes("长时间未移动");

        UnusualStatue statue6 = new UnusualStatue();
        statue6.setId(UnusualStatue.EXCEPTION_TYPE_BASE_SEPARATION);
        statue6.setDes("底盒分离");

        unusualStatueList.add(statue1);
        unusualStatueList.add(statue2);
        unusualStatueList.add(statue3);
        unusualStatueList.add(statue4);
        unusualStatueList.add(statue5);
        unusualStatueList.add(statue6);

        return unusualStatueList;
    }

    /**
     * 获取处理状态列表信息
     *
     * @return
     */
    public static List<UnusualHandleStatue> getHanleStatueList() {

        List<UnusualHandleStatue> unusualStatueList = new ArrayList<>();

        UnusualHandleStatue statue1 = new UnusualHandleStatue();
        statue1.setId(UnusualHandleStatue.DEAL_STATUS_GOING);
        statue1.setHandleDes("未处理");

        UnusualHandleStatue statue2 = new UnusualHandleStatue();
        statue2.setId(UnusualHandleStatue.DEAL_STATUS_ING);
        statue2.setHandleDes("处理中");

        UnusualHandleStatue statue3 = new UnusualHandleStatue();
        statue3.setId(UnusualHandleStatue.DEAL_STATUS_ED);
        statue3.setHandleDes("处理完成");

        unusualStatueList.add(statue1);
        unusualStatueList.add(statue2);
        unusualStatueList.add(statue3);

        return unusualStatueList;
    }


    /**
     * 添加异常状态的id 到列表
     *
     * @param statue
     * @param unusualStatueSelectedList
     */
    public static void addUnusualStatueToSelectList(UnusualStatue statue, List<String>
            unusualStatueSelectedList) {

        if (null == unusualStatueSelectedList) {
            LogUtils.loge(TAG, "unusualStatueSelectedList 不能为空");
            return;
        }

        String id = String.valueOf(statue.getId());
        if (unusualStatueSelectedList.contains(id)) {
            return;
        }

        unusualStatueSelectedList.add(String.valueOf(id));

    }

    /**
     * 添加某个处理状态到列表
     *
     * @param statue
     * @param handleStatueSelectedList
     */
    public static void addHandleStatueToSelectList(UnusualHandleStatue statue, List<String>
            handleStatueSelectedList) {

        if (null == handleStatueSelectedList) {
            LogUtils.loge(TAG, "handleStatueSelectedList 不能为空");
            return;
        }

        String id = statue.getId();
        if (handleStatueSelectedList.contains(id)) {
            return;
        }

        handleStatueSelectedList.add(String.valueOf(id));

    }

    /**
     * 移除某个异常状态
     *
     * @param statue
     * @param unusualStatueSelectedList
     */
    public static void removeUnusualStatueFromSelectList(UnusualStatue statue, List<String>
            unusualStatueSelectedList) {

        if (null == unusualStatueSelectedList) {
            LogUtils.loge(TAG, "unusualStatueSelectedList 不能为空");
            return;
        }

        String id = String.valueOf(statue.getId());
        if (unusualStatueSelectedList.contains(id)) {
            unusualStatueSelectedList.remove(id);
        }
    }

    /**
     * 移除某个处理状态
     *
     * @param statue
     * @param handleStatueSelectedList
     */
    public static void removeHandleStatueFromSelectList(UnusualHandleStatue statue, List<String>
            handleStatueSelectedList) {

        if (null == handleStatueSelectedList) {
            LogUtils.loge(TAG, "unusualStatueSelectedList 不能为空");
            return;
        }

        String id = statue.getId();
        if (handleStatueSelectedList.contains(id)) {
            handleStatueSelectedList.remove(id);
        }
    }

    /**
     * 清空状态
     *
     * @param handleStatueSelectedList
     */
    public static void setAllStatueIsSelected(boolean isSelected, List<String>
            handleStatueSelectedList) {

        if (!CollectionUtils.isNullOrEmpty(handleStatueSelectedList)) {

            if (isSelected) {

            } else {
                handleStatueSelectedList.clear();
            }
        }
    }

    /**
     * 根据时间请求 总人数 异常人数 聚集  通知公告
     *
     * @param presenter
     */
    public static void requestUnusalInfoHelper(MainPresenter presenter, boolean isCheck) {
        presenter.getUnusalPerson();//1分钟刷新一次
        presenter.getGatherNumber();//1分钟刷新一次
        if (isCheck) {
            presenter.requestUnReadMsg();//5分钟刷新一次
            presenter.getUnusualTotalPersonNumber();//30分钟刷新一次
        } else {

            if (baseTime % 5 == 0) {
                presenter.requestUnReadMsg();//5分钟刷新一次
            }
            if (baseTime % 30 == 0) {
                presenter.getUnusualTotalPersonNumber();//30分钟刷新一次
            }
            baseTime++;
        }

    }

}

