package com.kp.monitor.data.po;

import com.hl.foundation.library.utils.StringUtils;
import com.kp.monitor.data.bean.Record;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.File;

/**
 * des:录音文件
 * Created by HL
 * on 2017-06-23.
 */
@Entity
public class RecordFile{

    @Id
    private String  fileId; // 文件id
    @Property
    private String  filePath; // 文件路径
    @Property
    private String startTime; // 录音开始时间
    @Property
    private String endTime; // 录音结束时间
    @Property
    private boolean isUploadCpmplete;  // 是否长传完成 （当上传完成是时  文件与记录都应该删除）
    @Property
    private long totalLength; // 总长度
    @Property
    private long hasUploadLength; // 已经长传的长度
    @Property
    private long remainUploadLength; //  剩余需要上传的长度
    @Property
    private String percent; // (remainUploadLength / totalLength) * 100%  剩余上传所占的百分比
    @Property
    private int statue; // 状态  0：开始录音 1：录音中 2：录音完成 3：开上上传 4：上传中 5：上传完成
    @Property
    private String phone; // 录音的电话号码
    @Property
    private int deviceId; // 对应的设备id

    @Property
    private String name; // 文件名称

    @Property
    private int needUploadStatue;  // 0 待定（还未请求接口 或者接口请求没成功） 1 需要上传  2 不需要上传

    @Generated(hash = 1103984345)
    public RecordFile(String fileId, String filePath, String startTime,
                      String endTime, boolean isUploadCpmplete, long totalLength,
                      long hasUploadLength, long remainUploadLength, String percent,
                      int statue, String phone, int deviceId, String name,
                      int needUploadStatue) {
        this.fileId = fileId;
        this.filePath = filePath;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isUploadCpmplete = isUploadCpmplete;
        this.totalLength = totalLength;
        this.hasUploadLength = hasUploadLength;
        this.remainUploadLength = remainUploadLength;
        this.percent = percent;
        this.statue = statue;
        this.phone = phone;
        this.deviceId = deviceId;
        this.name = name;
        this.needUploadStatue = needUploadStatue;
    }

    @Generated(hash = 805337688)
    public RecordFile() {
    }


    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean getIsUploadCpmplete() {
        return this.isUploadCpmplete;
    }

    public void setIsUploadCpmplete(boolean isUploadCpmplete) {
        this.isUploadCpmplete = isUploadCpmplete;
    }

    public long getTotalLength() {
        return this.totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getHasUploadLength() {
        return this.hasUploadLength;
    }

    public void setHasUploadLength(long hasUploadLength) {
        this.hasUploadLength = hasUploadLength;
    }

    public long getRemainUploadLength() {
        return this.remainUploadLength;
    }

    public void setRemainUploadLength(long remainUploadLength) {
        this.remainUploadLength = remainUploadLength;
    }

    public String getPercent() {
        return this.percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeedUploadStatue() {
        return this.needUploadStatue;
    }

    public void setNeedUploadStatue(int needUploadStatue) {
        this.needUploadStatue = needUploadStatue;
    }

    public int getStatue() {
        return this.statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    /**
     * 创建初始的 RecordFile
     * @param record
     * @return
     */
    public static RecordFile createInitRecordFile(Record record){


        RecordFile recordFile = new RecordFile();

        File file = record.getFile();

        recordFile.setFileId(StringUtils.getUUID());
        recordFile.setFilePath(file.getAbsolutePath());
        recordFile.setStartTime(record.getStartTime());
        recordFile.setEndTime(record.getEndTime());
        recordFile.setPhone(record.getPhone());
        recordFile.setIsUploadCpmplete(false);
        recordFile.setTotalLength(file.length());
        recordFile.setHasUploadLength(0);
        recordFile.setRemainUploadLength(file.length());
        recordFile.setPercent("0%");
        recordFile.setStatue(2);
        recordFile.setNeedUploadStatue(1);  //测试  把状态置为1
        recordFile.setName(file.getName());

        return recordFile;
    }
}
