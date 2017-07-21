package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffWristVO;
import com.kp.monitor.data.vo.UnusalInfoVO;

/**
 * des:脱腕
 * Created by HL
 * on 2017-05-10.
 */

public interface OffWristListView extends BaseListView<OffWristVO> {

    void onOffWristBaicInfoComplete(UnusalInfoVO unusalInfoVO);

    void onHandleComplete(MsgVo msgVo);
}
