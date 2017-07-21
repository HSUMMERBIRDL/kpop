package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.GatherVO;

/**
 * des:
 * Created by HL
 * on 2017-05-10.
 */

public class GatherDTO implements Mapper<GatherVO> {

    private String loc; // 聚集地点

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public GatherVO transform() {
        return null;
    }
}
