package com.kp.monitor.data.dto;

import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MessageVO;

/**
 * des: 消息详情
 * Created by HL
 * on 2017-05-10.
 */

public class MessageDTO implements Mapper<MessageVO> {


    /**
     * intercomId : 26aa8305843f4013a4b496e2d9ed0567
     * intercomReceiveId : 26aa8305843f4013a4b496e2d9ed0533
     * title : 聚集通知
     * type : 1
     * sendTime : 1494905634000
     * level : 1
     * createId : 1
     * createTime : null
     * receiveUserId : null
     * readStatus : Y
     * readTime : null
     * content : fdfsdsda
     * createUserName : null
     */

    private String intercomId;
    private String intercomReceiveId;
    private String title;
    private String type;
    private long sendTime;
    private int level;
    private String createId;
    private String createTime;
    private String receiveUserId;
    private String readStatus;
    private String readTime;
    private String content;
    private String createUserName;

    @Override
    public MessageVO transform() {
        MessageVO messageVO = new MessageVO();
        messageVO.content = content;
        messageVO.readStatus = readStatus;
        return messageVO;
    }


    public String getIntercomId() {
        return intercomId;
    }

    public void setIntercomId(String intercomId) {
        this.intercomId = intercomId;
    }

    public String getIntercomReceiveId() {
        return intercomReceiveId;
    }

    public void setIntercomReceiveId(String intercomReceiveId) {
        this.intercomReceiveId = intercomReceiveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
