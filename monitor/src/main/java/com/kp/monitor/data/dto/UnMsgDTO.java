package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.UnMsgVO;

/**
 * Created by ${Stephen} on 2017-05-27.
 */

public class UnMsgDTO implements Mapper<UnMsgVO>{
    public int count;

    @Override
    public UnMsgVO transform() {
        UnMsgVO msgVO = new UnMsgVO();
        msgVO.messageDatas = count;
        return msgVO;
    }
}
