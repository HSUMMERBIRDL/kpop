package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.DeviceVO;

/**
 * des: 设备详情
 * Created by HL
 * on 2017-05-10.
 */

public class DeviceDTO implements Mapper<DeviceVO> {

    /**
     * monitorDeviceId : 590cc67d3f4b4cc0aefc4fc63b6d85d1
     * monitorDeviceCode : BZH004
     * depId : 652901
     * simNo : null
     * recordId : null
     * recordTime : 1495524414000
     * status : N
     * monitorPersonName : TEST004
     * phone : 13875817650
     * depName : 阿克苏市
     * depShortName : 阿克苏
     * createUserId : null
     */

    public String monitorDeviceId;
    public String monitorDeviceCode;
    public String depId;
    public String simNo;
    public String recordId;
    public long recordTime;
    public String status;
    public String monitorPersonName;
    public String phone;
    public String depName;
    public String depShortName;
    public String createUserId;
    public String bindStatus;

    @Override
    public DeviceVO transform() {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.deviceNumber = monitorDeviceCode;
        deviceVO.type = "手环";
        deviceVO.deviceSite = depName;
        deviceVO.simNo = simNo;
        if (null != bindStatus)
            deviceVO.status = bindStatus;
        deviceVO.createTime = TimeUtil.formatData(TimeUtil.dateFormatYMDHMofChinese,
                recordTime);
        return deviceVO;
    }


}
