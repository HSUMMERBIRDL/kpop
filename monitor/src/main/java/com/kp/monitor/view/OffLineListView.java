package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.OffLineVO;
import com.kp.monitor.data.vo.UnusalInfoVO;

/**
 * des:掉线
 * Created by HL
 * on 2017-05-10.
 */

public interface OffLineListView extends BaseListView<OffLineVO> {

    void onOffLineBaicInfoComplete(UnusalInfoVO unusalInfoVO);

    void onHandleComplete(MsgVo msgVo);

}
