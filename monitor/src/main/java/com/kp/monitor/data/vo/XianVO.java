package com.kp.monitor.data.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * des:县
 * Created by HL
 * on 2017-05-16.
 */

public class XianVO {

    public static final String ID_XIAN_ALL = "id_xian_all";
    public static final String NAME_XIAN_ALL = "不限";

    private boolean isSelected;
    private String name;
    private String id;

    private List<XiangVO> xiangList = new ArrayList<>();

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

    public List<XiangVO> getXiangList() {
        return xiangList;
    }

    public void setXiangList(List<XiangVO> xiangList) {
        this.xiangList = xiangList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public String toString() {
        return "XianVO{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
