package com.hl.foundation.library.manager;

import com.hl.foundation.library.inter.ChangeMsgInte;
import com.hl.foundation.library.inter.ChooseTrackingDateInte;
import com.hl.foundation.library.inter.OverRailDetailInte;

/**
 * Created by ${Stephen} on 2017-06-07.
 * 内存缓存接口管理类
 */

public class InterfaceMgr {
    private static InterfaceMgr interfaceMgr;

    private InterfaceMgr() {
    }

    public static synchronized InterfaceMgr getInstance() {
        if (null == interfaceMgr) interfaceMgr = new InterfaceMgr();
        return interfaceMgr;
    }

    private OverRailDetailInte overRailDetailLisener;
    private ChooseTrackingDateInte chooseTrackingDateLisener;
    private ChangeMsgInte changeMsgLisener;

    public void setChangeMsgLisener(ChangeMsgInte changeMsgLisener) {
        this.changeMsgLisener = changeMsgLisener;
    }

    /**
     * 更改消息
     */
    public void changeMsg(int position) {
        if (null != changeMsgLisener) changeMsgLisener.changeMsg(position);
    }


    public void setChooseTrackingDateLisener(ChooseTrackingDateInte chooseTrackingDateLisener) {
        this.chooseTrackingDateLisener = chooseTrackingDateLisener;
    }

    /**
     * 选择开始、借宿日期
     *
     * @param isStart
     */
    public void chooseDate(boolean isStart, long time) {
        if (null != chooseTrackingDateLisener) chooseTrackingDateLisener.chooseDate(isStart, time);
    }

    public void setOnOverRailDetailLisener(OverRailDetailInte onOverRailDetailLisener) {
        this.overRailDetailLisener = onOverRailDetailLisener;
    }

    /**
     * 提交处理进度
     */
    public void overRailDetail(String status, String content) {
        if (null != overRailDetailLisener) {
            overRailDetailLisener.overRailDetail(status, content);
        }
    }
}
