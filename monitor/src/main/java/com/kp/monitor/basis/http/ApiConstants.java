package com.kp.monitor.basis.http;

/**
 * des: API 的一些常量
 * Created by HL
 * on 2017-05-03.
 */

public class ApiConstants {


    /*路径*/

    public static final String PATH = "/keypopu/api";

    public static final String FILE_UPLOAD_URL = PATH + "/monitorlisten/uploadListenFile";  //  文件上传地址

    public static final String FUNC_LOGIN = PATH + "/token ";  //  登录
    public static final String FUNC_LOGOUT = PATH + "/token ";  //  退出登录
    public static final String FUNC_RESET_PASS = PATH + "/ntoms/app/driver"; // 忘记密码
    public static final String FUNC_GET_PHONE_CODE = PATH + "/ntoms/app/driver"; // 获取验证码
    public static final String FUNC_CHECK_PHONE_CODE = PATH + "/ntoms/app/driver"; // 验证码校验
    public static final String FUNC_GET_USER_INFO = PATH + "/loginuser";  //  获取个人信息

    public static final String FUNC_GET_ADDRESS_LIST = PATH + "/communicate/sysuser";  //  通讯录列表

    public static final String FUNC_GET_DEVICE_LIST = PATH + "/";  //  设备列表


    public static final String FUNC_GET_GATHER_LIST = PATH + "/ntoms/app/driver";  //  聚集列表
    public static final String FUNC_GET_MEMBER_LIST = PATH + "/monitorperson";  //  人员列表
    public static final String FUNC_GET_MEMBER_BASIC_INFO = PATH + "/monitorperson";  //  人员详情
    public static final String FUNC_GET_LOCATION_LIST_BASE = PATH + "/monitorlocation";//轨迹追踪列表


    public static final String FUNC_GET_ZONE_INFO = PATH + "/sysdept";//获取区域的信息

    public static final String FUNC_GET_MESSAGE_LIST = PATH + "/intercom";  //  消息列表
    public static final String FUNC_GET_MESSAGE_DETAIL = PATH + "/"; //消息详情

    public static final String FUNC_GET_READ_MESSAGE = PATH + "/";//已读消息

    public static final String FUNC_GET_UNREAD_MSG = PATH + "/intercom/unreadNoticeCount"; //未读消息
    public static final String FUNC_GET_UNUSAL_LIST = PATH + "/monitorexceptioninfo"; //异常列表
    public static final String FUNC_GET_LOW_POWER_LIST = PATH + "/monitorexceptioninfo"; // 低电列表
    public static final String FUNC_GET_OFF_LINE_LIST = PATH + "/monitorexceptioninfo"; // 掉线列表
    public static final String FUNC_GET_OFF_WRIST_LIST = PATH + "/monitorexceptioninfo"; // 脱腕列表
    public static final String FUNC_GET_SEPARATE_LIST = PATH + "/monitorexceptioninfo"; // 底座分离列表

    //异常总人数
    public static final String FUNC_GET_UNUSALPERSON_TOTALNUMBER = PATH +
            "/monitorperson/totalCount";


    //异常人数
    public static final String FUNC_GET_UNUSUALPERSON_NUMBER = PATH +
            "/monitorexceptioninfo/totalCount";

    //聚集事件
    public static final String FUNC_GET_GATHER_NUMBER = PATH + "/monitorfocus/totalCount";

    //越栏
    public static final String FUNC_GET_OVERRAIL_MSG = PATH + "/";
    //keypopu/api/intercom/{intercomReceiveId}?readStatus=Y


    /*常量  */
    public static final int SUCCESS = 200; // 接口返回的的成功码

    public static final int C_REGION_RANGE_ALL = 0;  //  所有区域  即该权限下的所有区域

    public static final int C_MEMBER_TYPE_ALL = 0;  //  所有人员
    public static final int C_MEMBER_TYPE_UNUSUAL = 1; // 异常人员  所有异常状态下的人员
    public static final int C_DEVICE_TYPE_ALL = 0;  //  所有设备
    public static final int C_DEVICE_TYPE_UNUSUAL = 1; // 异常设备

    public static final int MESSAGE_TYPE_MSG = 2; //  消息类型:消息

    public static final String MESSAGE_YTPE = "Y"; //消息状态
    /*key*/

    public static final String PAGE_INDEX = "page";  // 分页第几页
    public static final String PAGE_SIZE = "rows";  // 分页大小

    public static final String MESSAGE_TYPE = "type"; //消息类型
    public static final String READ_MESSAGE_STATES = "readStatus";
    public static final String TOKEN = "token";
    public static final String AUTHORIZATION = "authorization";

    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";

    public static final String PHONE = "phone";
    public static final String PHONE_CODE = "phoneCode";
    public static final String NEW_PASSWORD = "newPassword";

    public static final String ID_CARD = "idCard";  //  身份证（搜索输入的纯数字）
    public static final String NAME = "monitorPersonName"; // 姓名（人员搜索的姓名输入 不是纯数字）
    public static final String DEP_ID = "depId"; // 区域id
    public static final String SEARCH_KEY = "searchKey"; // 查询关键字

    public static final String MONITOR_PERSON_ID = "monitorPersonId"; // 查询关键字

    public static final String DEVICE_CODE = "monitorDeviceCode"; //输入设备号

    public static final String EXCEPTION_TYPES = "exceptionTypes"; // 异常情况状态列表
    public static final String DEAL_STATUSES = "dealStatuses"; // 处理情况状态列表


    public static final String EXCEPTION_ID = "exceptionId"; // 异常id

    public static final String ADDRESS_NAME = "userRealName"; // 姓名（通讯录搜索姓名）
    public static final String ADDRESS_OFFICE_PHONE = "officePhone"; // 办公室电话 （通讯录搜索电话）

    public static final String START_TIME = "startTime";//开始时间
    public static final String END_TIME = "endTime";//结束时间
    public static final String PERSONID = "monitorPersonId";//人员id
    public static final String DEVICEID="monitorDeviceId";//设备id

    // 录音文件上传接口
    public static final String RECORD_START_TIME = "startTime";//开始时间
    public static final String RECORD_END_TIME = "endTime";//结束时间
    public static final String RECORD_PHONE = "devicePhoneNo";//被监听设备的电话
    public static final String RECORD_FILE_ID="appFileId";//录音文件id
}
