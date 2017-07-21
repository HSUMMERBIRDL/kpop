package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.vo.LowPowerVO;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.UnusalInfoVO;

/**
 * des: 低电
 * Created by HL
 * on 2017-05-10.
 */

public interface LowPowerListView extends BaseListView<LowPowerVO> {

    void onLowPowerBaicInfoComplete(UnusalInfoVO unusalInfoVO);

    void onHandleComplete(MsgVo msgVo);
}
