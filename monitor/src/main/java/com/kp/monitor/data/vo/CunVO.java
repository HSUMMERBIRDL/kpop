package com.kp.monitor.data.vo;

/**
 * des:县
 * Created by HL
 * on 2017-05-16.
 */

public class CunVO {

    public static final String ID_CUN_ALL = "id_cun_all";
    public static final String NAME_CUN_ALL = "不限";

    private boolean isSelected;
    private String name;
    private String id;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "CunVO{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
