package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.event.HandleStateEvent;
import com.kp.monitor.data.vo.UnusalItemVO;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public interface UnusalListView extends BaseListView<UnusalItemVO> {

    public void onHandleStatueChange(HandleStateEvent event);
}
