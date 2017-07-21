package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.DeviceItemVO;
import com.kp.monitor.data.vo.DeviceVO;

import java.util.List;

/**
 * des: 设备详情
 * Created by HL
 * on 2017-05-10.
 */

public class DeviceItemApiDTO {
    public int total;
    public List<RowsBean> rows;

    public class RowsBean implements Mapper<DeviceItemVO> {
        public String monitorDeviceId;
        public String monitorDeviceCode;
        public String depId;
        public String simNo;
        public String version;
        public long recordTime;
        public String recordId;
        public String status;
        public String monitorPersonName;
        public String phone;
        public String depName;
        public String depShortName;
        public String bindStatus;

        @Override
        public DeviceItemVO transform() {
            DeviceItemVO deviceVO = new DeviceItemVO();
            deviceVO.deviceNum = monitorDeviceCode;
            deviceVO.deviceType = "手环";
            deviceVO.deviceSite = depName;
            if (null != bindStatus)
                deviceVO.deviceState = bindStatus;
            deviceVO.deviceId = monitorDeviceId;
            deviceVO.createTime = TimeUtil.formatData(TimeUtil.dateFormatYMDHMofChinese,
                    recordTime);
            return deviceVO;
        }
    }
}
