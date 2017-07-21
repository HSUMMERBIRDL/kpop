package com.kp.monitor.data.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * des:
 * Created by HL
 * on 2017-05-26.
 */

public class ZoneInfoVO {

    private String name;
    private String id;
    private List<XianVO> datas = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<XianVO> getDatas() {
        return datas;
    }

    public void setDatas(List<XianVO> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "ZoneVO{" +
                "datas=" + datas +
                '}';
    }
}
