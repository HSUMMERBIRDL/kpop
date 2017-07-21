package com.kp.monitor.view;

import com.hl.foundation.frame.ui.list.BaseListView;
import com.kp.monitor.data.vo.MemberItemVO;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public interface MemberListView extends BaseListView<MemberItemVO> {

    void onNotUploadFileUpdate(int currentNum);
}
