package com.kp.monitor.data.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-25.
 */

public class ZoneDTO {

    private List<XianDTO> datas = new ArrayList<>();

    public List<XianDTO> getDatas() {
        return datas;
    }

    public void setDatas(List<XianDTO> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "ZoneVO{" +
                "datas=" + datas +
                '}';
    }

}
