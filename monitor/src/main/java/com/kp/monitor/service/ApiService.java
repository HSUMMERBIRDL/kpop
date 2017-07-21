package com.kp.monitor.service;

import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.data.dto.AddressItemApiDTO;
import com.kp.monitor.data.dto.DeviceDTO;
import com.kp.monitor.data.dto.DeviceItemApiDTO;
import com.kp.monitor.data.dto.GatherItemDTO;
import com.kp.monitor.data.dto.LoginDTO;
import com.kp.monitor.data.dto.LowPowerApiDTO;
import com.kp.monitor.data.dto.MemberDTO;
import com.kp.monitor.data.dto.MemberListDTO;
import com.kp.monitor.data.dto.MessageDTO;
import com.kp.monitor.data.dto.MsgDTO;
import com.kp.monitor.data.dto.MsgItemApiDTO;
import com.kp.monitor.data.dto.OffLineApiDTO;
import com.kp.monitor.data.dto.OffWristApiDTO;
import com.kp.monitor.data.dto.OverRailDTO;
import com.kp.monitor.data.dto.SeparateApiDTO;
import com.kp.monitor.data.dto.TrakingDTO;
import com.kp.monitor.data.dto.TrakingDetailDTO;
import com.kp.monitor.data.dto.UnMsgDTO;
import com.kp.monitor.data.dto.UnusalItemApiDTO;
import com.kp.monitor.data.dto.UnusualTotalPersonDTO;
import com.kp.monitor.data.dto.UserDTO;
import com.kp.monitor.data.dto.ZoneInfoDTO;
import com.kp.monitor.data.dto.base.BaseResponse;
import com.kp.monitor.data.request.LoginRequest;
import com.kp.monitor.data.request.MsgReadStutas;
import com.kp.monitor.data.request.OverRailStatus;
import com.kp.monitor.data.vo.MsgVo;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * des:
 * Created by HL
 * on 2017-05-02.
 */

public interface ApiService {


    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST(ApiConstants.FUNC_LOGIN)
    Observable<BaseResponse<LoginDTO>> login(@Body LoginRequest loginRequest);

    @DELETE(ApiConstants.FUNC_LOGOUT)
    Observable<BaseResponse> logout();

    /**
     * 重置密码
     *
     * @param phone
     * @param phoneCode
     * @param newPassword
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.FUNC_RESET_PASS)
    Observable<BaseResponse> resetPass(@Field(ApiConstants.PHONE) String phone,
                                       @Field(ApiConstants.PHONE_CODE) String phoneCode,
                                       @Field(ApiConstants.NEW_PASSWORD) String newPassword);


    /**
     * 获取验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.FUNC_GET_PHONE_CODE)
    Observable<BaseResponse<String>> getPhoneCode(@Field(ApiConstants.PHONE) String phone);

    /**
     * 验证验证码
     *
     * @param phone
     * @param phoneCode
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.FUNC_CHECK_PHONE_CODE)
    Observable<BaseResponse> checkPhoneCode(@Field(ApiConstants.PHONE) String phone,
                                            @Field(ApiConstants.PHONE_CODE) String phoneCode);


    /**
     * 获取个人信息
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_USER_INFO)
    Observable<BaseResponse<UserDTO>> getUserInfo();


    /**
     * 获取通讯录列表
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_ADDRESS_LIST)
    Observable<BaseResponse<AddressItemApiDTO>> getAddressList(@Query(ApiConstants.PAGE_INDEX)
                                                                       int pageIndex,
                                                               @Query(ApiConstants.PAGE_SIZE)
                                                                       int pageSize,
                                                               @Query(ApiConstants.ADDRESS_NAME)
                                                                       String name,
                                                               @Query(ApiConstants
                                                                       .ADDRESS_OFFICE_PHONE)
                                                                       String phone);


    /**
     * 获取设备列表
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_DEVICE_LIST + "monitordevice")
    Observable<BaseResponse<DeviceItemApiDTO>> getDeviceLists(@QueryMap Map<String, Object> map);


    @GET(ApiConstants.FUNC_GET_DEVICE_LIST + "monitordevice")
    Observable<BaseResponse<DeviceItemApiDTO>> getSearchDeviceLists(@QueryMap Map<String, Object>
                                                                            map);

    /**
     * 获取设备列表详情
     *
     * @param commentId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_DEVICE_LIST + "monitordevice/{monitorDeviceId}")
    Observable<BaseResponse<DeviceDTO>> getDeviceDetail(@Path("monitorDeviceId") String commentId);


    @GET(ApiConstants.FUNC_GET_GATHER_LIST)
    Observable<BaseResponse<List<GatherItemDTO>>> getGatherList(@Query(ApiConstants.PAGE_SIZE)
                                                                        int pageSize,
                                                                @Query(ApiConstants.PAGE_INDEX)
                                                                        int pageIndex);

    /**
     * 人员列表
     *
     * @param pageSize
     * @param pageIndex
     * @param idCard
     * @param name
     * @param depId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_MEMBER_LIST)
    Observable<BaseResponse<MemberListDTO>> getMemberList(@Query(ApiConstants.PAGE_SIZE)
                                                                  int pageSize,
                                                          @Query(ApiConstants.PAGE_INDEX)
                                                                  int pageIndex,
                                                          @Query(ApiConstants.ID_CARD)
                                                                  String idCard,
                                                          @Query(ApiConstants.NAME)
                                                                  String name,
                                                          @Query(ApiConstants.DEP_ID)
                                                                  String depId);

    @GET(ApiConstants.FUNC_GET_MEMBER_BASIC_INFO + "/{monitorPersonId}")
    Observable<BaseResponse<MemberDTO>> getMemberBasicInfo(@Path(ApiConstants.MONITOR_PERSON_ID)
                                                                   String memberId);


    @GET(ApiConstants.FUNC_GET_MESSAGE_LIST)
    Observable<BaseResponse<MsgItemApiDTO>> getMessageList(@Query(ApiConstants.PAGE_SIZE)
                                                                   int pageSize,
                                                           @Query(ApiConstants.PAGE_INDEX)
                                                                   int pageIndex,
                                                           @Query(ApiConstants.MESSAGE_TYPE)
                                                                   int type
    );

    @GET(ApiConstants.FUNC_GET_MESSAGE_DETAIL + "intercom/{msgId}")
    Observable<BaseResponse<MessageDTO>> getMessageDetail(@Path("msgId") String commentId
            , @Query(ApiConstants.MESSAGE_TYPE) int type);

    /**
     * 改变消息状态
     *
     * @param commentId
     * @return
     */

    @PUT(ApiConstants.FUNC_GET_READ_MESSAGE + "intercom/{accountId}")
    Observable<MsgVo> changeReadMessageStutas(@Path("accountId") String commentId
            , @Body MsgReadStutas msgReadStutas);


    @PUT(ApiConstants.FUNC_GET_OVERRAIL_MSG + "monitorexceptioninfo/{exceptionId}")
    Observable<BaseResponse<MsgDTO>> changeOverRailStatus(@Path("exceptionId") String
                                                                  exceptionId, @Body
                                                                  OverRailStatus dealStatus);

    /**
     * 获取通知公告里面的图片
     *
     * @param cacheControl
     * @param photoPath
     * @return
     */
    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Header("Cache-Control") String cacheControl,
            @Url String photoPath);

    @GET(ApiConstants.FUNC_GET_ZONE_INFO)
    Observable<BaseResponse<List<ZoneInfoDTO>>> getZone();

    /**
     * 未读消息
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_UNREAD_MSG)
    Observable<BaseResponse<UnMsgDTO>> getUnreadMsg();


    /**
     * 总人数
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_UNUSALPERSON_TOTALNUMBER)
    Observable<BaseResponse<UnusualTotalPersonDTO>> getUnusualTotalPersonNumber();

    /**
     * 异常人数
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_UNUSUALPERSON_NUMBER)
    Observable<BaseResponse<UnusualTotalPersonDTO>> getUnusualPersonNumber();


    /**
     * 聚集事件
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_GATHER_NUMBER)
    Observable<BaseResponse<UnusualTotalPersonDTO>> getGatherNumber();

    @GET(ApiConstants.FUNC_GET_UNUSAL_LIST)
    Observable<BaseResponse<UnusalItemApiDTO>> getUnusalList(@Query(ApiConstants.PAGE_SIZE)
                                                                     int pageSize,
                                                             @Query(ApiConstants.PAGE_INDEX)
                                                                     int pageIndex,
                                                             @Query(ApiConstants.EXCEPTION_TYPES)
                                                                     List<String> exceptionTypes,
                                                             @Query(ApiConstants.DEAL_STATUSES)
                                                                     List<String> dealStatuses);

    /**
     * 低电列表
     *
     * @param exceptionId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_LOW_POWER_LIST + "/{exceptionId}")
    Observable<BaseResponse<LowPowerApiDTO>> getLowPowerList(@Path(ApiConstants.EXCEPTION_ID)
                                                                     String exceptionId);


    /**
     * 掉线列表
     *
     * @param exceptionId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_OFF_LINE_LIST + "/{exceptionId}")
    Observable<BaseResponse<OffLineApiDTO>> getOffLineList(@Path(ApiConstants.EXCEPTION_ID)
                                                                   String exceptionId);


    /**
     * 脱腕列表
     *
     * @param exceptionId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_OFF_WRIST_LIST + "/{exceptionId}")
    Observable<BaseResponse<OffWristApiDTO>> getOffWristList(@Path(ApiConstants.EXCEPTION_ID)
                                                                     String exceptionId);


    /**
     * 底座分离列表
     *
     * @param exceptionId
     * @return
     */
    @GET(ApiConstants.FUNC_GET_SEPARATE_LIST + "/{exceptionId}")
    Observable<BaseResponse<SeparateApiDTO>> getSeparateList(@Path(ApiConstants.EXCEPTION_ID)
                                                                     String exceptionId);

    @GET(ApiConstants.FUNC_GET_OVERRAIL_MSG + "monitorexceptioninfo/{exceptionId}")
    Observable<BaseResponse<OverRailDTO>> getOverRail(@Path("exceptionId") String msgId);

    /**
     * 轨迹列表详情
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_LOCATION_LIST_BASE)
    Observable<BaseResponse<TrakingDTO>> getTrakingLocationList(@QueryMap Map<String,
            Object> map);

    /**
     * 根据时间得到人员的追踪
     *
     * @return
     */
    @GET(ApiConstants.FUNC_GET_LOCATION_LIST_BASE + "/device")
    Observable<BaseResponse<TrakingDetailDTO>> getTrackingLocationLngLat(@QueryMap Map<String,
            Object> map);


    @Multipart
    @POST((ApiConstants.FILE_UPLOAD_URL))
    Observable<BaseResponse<Object>> uploadFlie(
            @PartMap() Map<String, RequestBody> partMap,
            @Part() MultipartBody.Part file);


}
