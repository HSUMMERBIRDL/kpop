package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MsgVo;

/**
 * Created by ${Stephen} on 2017-05-20.
 */

public class MsgResponseDTO implements Mapper<MsgVo> {
    public String msg = "";
    public int code;


    @Override
    public MsgVo transform() {
        MsgVo msgVo = new MsgVo();
        msgVo.code = code;
        return null;
    }


}
