package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.UnusualPersonVO;

/**
 * Created by ${Stephen} on 2017-06-01.
 */

public class UnusualTotalPersonDTO implements Mapper<UnusualPersonVO>{
    public int totalCount;

    @Override
    public UnusualPersonVO transform() {
        UnusualPersonVO unusualTotalPersonVO = new UnusualPersonVO();
        unusualTotalPersonVO.totalCount = totalCount;
        return unusualTotalPersonVO;
    }
}
