package com.kp.monitor.data.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * des:县
 * Created by HL
 * on 2017-05-16.
 */

public class XiangVO {

    public static final String ID_XIANG_ALL = "id_xiang_all";
    public static final String NAME_XIANG_ALL = "不限";

    private boolean isSelected;
    private String name;
    private String id;

    private List<CunVO> cunList = new ArrayList<>();

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

    public List<CunVO> getCunList() {
        return cunList;
    }

    public void setCunList(List<CunVO> cunList) {
        this.cunList = cunList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String toString2() {
        return "XiangVO{" +
                "isSelected=" + isSelected +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", cunList=" + cunList +
                '}';
    }

    @Override
    public String toString() {
        return "XiangVO{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
