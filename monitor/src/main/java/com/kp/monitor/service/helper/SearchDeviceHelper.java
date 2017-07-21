package com.kp.monitor.service.helper;


import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.data.vo.DeviceItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Stephen} on 2017-05-23.
 */

public class SearchDeviceHelper {
    public List<DeviceItemVO> deviceItemVOs = new ArrayList<>();

    private SearchDeviceHelper() {
    }

    private static SearchDeviceHelper helper;

    public static synchronized SearchDeviceHelper getInstance() {
        if (null == helper) {
            helper = new SearchDeviceHelper();
        }
        return helper;
    }

    public List<DeviceItemVO> getSearchDevice(String key, List<DeviceItemVO> list) {

        if (!StringUtils.isEmpty(key) && null != list) {
            deviceItemVOs.clear();
            for (int i = 0; i < list.size(); i++) {
                DeviceItemVO deviceItemVO = list.get(i);
                String deviceNum = deviceItemVO.deviceNum;
                if (deviceNum.contains(key)) {
                    deviceItemVOs.add(deviceItemVO);
                }
            }
        }

        return deviceItemVOs;
    }
}
