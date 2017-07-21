package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.CunVO;
import com.kp.monitor.data.vo.XianVO;
import com.kp.monitor.data.vo.XiangVO;

import java.util.ArrayList;
import java.util.List;

/**
 * des: 县
 * Created by HL
 * on 2017-05-16.
 */

public class XianDTO implements Mapper<XianVO>{


    /**
     * id : 1
     * name : 长沙
     * xiangList : [{"cunList":[{"id":111,"name":"莲花小区1"},{"id":112,"name":"莲花小区2"},{"id":113,"name":"莲花小区3"},{"id":114,"name":"莲花小区4"},{"id":115,"name":"莲花小区5"},{"id":116,"name":"莲花小区6"},{"id":117,"name":"莲花小区7"},{"id":118,"name":"莲花小区8"},{"id":119,"name":"莲花小区9"},{"id":1110,"name":"莲花小区10"},{"id":1111,"name":"莲花小区11"},{"id":1112,"name":"莲花小区12"}],"id":11,"name":"天心区"},{"cunList":[{"id":121,"name":"新威名座1"},{"id":122,"name":"新威名座2"},{"id":123,"name":"新威名座3"},{"id":124,"name":"新威名座4"},{"id":125,"name":"新威名座5"},{"id":126,"name":"新威名座6"},{"id":127,"name":"新威名座7"}],"id":12,"name":"雨花区"},{"cunList":[{"id":131,"name":"曹家坡1"},{"id":132,"name":"曹家坡2"},{"id":133,"name":"曹家坡3"},{"id":134,"name":"曹家坡4"},{"id":135,"name":"曹家坡5"},{"id":136,"name":"曹家坡6"},{"id":137,"name":"曹家坡7"}],"id":13,"name":"芙蓉区"},{"cunList":[{"id":141,"name":"涂家冲1"},{"id":142,"name":"涂家冲2"},{"id":143,"name":"涂家冲3"},{"id":144,"name":"涂家冲4"},{"id":145,"name":"涂家冲5"},{"id":146,"name":"涂家冲6"},{"id":147,"name":"涂家冲7"}],"id":14,"name":"开福区"},{"cunList":[{"id":151,"name":"省政府1"},{"id":152,"name":"省政府2"},{"id":153,"name":"省政府3"},{"id":154,"name":"省政府4"},{"id":155,"name":"省政府5"},{"id":156,"name":"省政府6"},{"id":157,"name":"省政府7"}],"id":15,"name":"岳麓区"}]
     */

    private String id;
    private String name;
    private List<XiangListBean> xiangList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XiangListBean> getXiangList() {
        return xiangList;
    }

    public void setXiangList(List<XiangListBean> xiangList) {
        this.xiangList = xiangList;
    }

    @Override
    public XianVO transform() {

        XianVO xianVO = new XianVO();
        xianVO.setName(name);
        xianVO.setId(id);


        List<XiangVO> xiangVOList = new ArrayList<>();

        //加入不限
        XiangVO xiangAll = new XiangVO();
        xiangAll.setName(XiangVO.NAME_XIANG_ALL);
        xiangAll.setId(XiangVO.ID_XIANG_ALL);
        xiangAll.setSelected(true);
        xiangVOList.add(xiangAll);

        for (int i = 0; i <xiangList.size() ; i++) {
            XiangListBean xiangListBean = xiangList.get(i);
            XiangVO xiangVO = xiangListBean.transform();
            xiangVOList.add(xiangVO);
        }

        xianVO.setXiangList(xiangVOList);

        return xianVO;
    }

    public static class XiangListBean implements Mapper<XiangVO>{
        /**
         * cunList : [{"id":111,"name":"莲花小区1"},{"id":112,"name":"莲花小区2"},{"id":113,"name":"莲花小区3"},{"id":114,"name":"莲花小区4"},{"id":115,"name":"莲花小区5"},{"id":116,"name":"莲花小区6"},{"id":117,"name":"莲花小区7"},{"id":118,"name":"莲花小区8"},{"id":119,"name":"莲花小区9"},{"id":1110,"name":"莲花小区10"},{"id":1111,"name":"莲花小区11"},{"id":1112,"name":"莲花小区12"}]
         * id : 11
         * name : 天心区
         */

        private String id;
        private String name;
        private List<CunListBean> cunList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CunListBean> getCunList() {
            return cunList;
        }

        public void setCunList(List<CunListBean> cunList) {
            this.cunList = cunList;
        }

        @Override
        public XiangVO transform() {

            XiangVO vo = new XiangVO();
            vo.setName(name);
            vo.setId(id);

            List<CunVO> cunVOList = new ArrayList<>();

            //加入不限
            CunVO cunAll = new CunVO();
            cunAll.setName(CunVO.NAME_CUN_ALL);
            cunAll.setId(CunVO.ID_CUN_ALL);
            cunAll.setSelected(true);
            cunVOList.add(cunAll);

            for (int i = 0; i <cunList.size() ; i++) {

                CunListBean cunListBean = cunList.get(i);
                CunVO cunVO = cunListBean.transform();
                cunVOList.add(cunVO);
            }

            vo.setCunList(cunVOList);
            return vo;
        }

        public static class CunListBean implements Mapper<CunVO>{
            /**
             * id : 111
             * name : 莲花小区1
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public CunVO transform() {

                CunVO vo = new CunVO();
                vo.setId(id);
                vo.setName(name);
                return vo;
            }
        }
    }
}
