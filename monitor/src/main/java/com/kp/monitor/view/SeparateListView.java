package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.data.vo.SeparateVO;
import com.kp.monitor.data.vo.UnusalInfoVO;

/**
 * des:底座分离
 * Created by HL
 * on 2017-05-10.
 */

public interface SeparateListView extends BaseListView<SeparateVO> {

    void onSeparateBaicInfoComplete(UnusalInfoVO unusalInfoVO);

    void onHandleComplete(MsgVo msgVo);

}
