package com.kp.monitor.data.dto;

import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.data.Mapper;
import com.kp.monitor.data.vo.MessageItemVO;

import java.util.List;

/**
 * des: 消息列表接口 dto 对象
 * Created by HL
 * on 2017-05-18.
 */

public class MsgItemApiDTO {


    /**
     * rows : [{"createId":"1","createTime":1494905654000,
     * "intercomId":"26aa8305843f4013a4b496e2d9ed0567",
     * "intercomReceiveId":"26aa8305843f4013a4b496e2d9ed0533","level":1,"receiveUserId":1,
     * "sendTime":1494905634000,"title":"聚集通知","type":"1"},{"createId":"1",
     * "createTime":1494905654000,"intercomId":"26aa8305843f4013a4b496e2d9ed0567",
     * "intercomReceiveId":"26aa8305843f4013a4b496e2d9ed0535","level":1,"readStatus":"",
     * "receiveUserId":1,"sendTime":1494905634000,"title":"聚集通知","type":"1"}]
     * total : 2
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Mapper<MessageItemVO> {
        /**
         * createId : 1
         * createTime : 1494905654000
         * intercomId : 26aa8305843f4013a4b496e2d9ed0567
         * intercomReceiveId : 26aa8305843f4013a4b496e2d9ed0533
         * level : 1
         * receiveUserId : 1
         * sendTime : 1494905634000
         * title : 聚集通知
         * type : 1
         * readStatus :
         */

        private String createId;
        private long createTime;
        private String intercomId;
        private String intercomReceiveId;
        private int level;
        private String receiveUserId;
        private long sendTime;
        private String title;
        private String type;
        private String readStatus;
        private String content;
        private String createUserName;
        private String receiveUserName;

        public String getReceiveUserName() {
            return receiveUserName;
        }

        public void setReceiveUserName(String receiveUserName) {
            this.receiveUserName = receiveUserName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateNames() {
            return createUserName;
        }

        public void setCreateNames(String createNames) {
            this.createUserName = createNames;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getReceiveUserId() {
            return receiveUserId;
        }

        public void setReceiveUserId(String receiveUserId) {
            this.receiveUserId = receiveUserId;
        }

        public long getSendTime() {
            return sendTime;
        }

        public void setSendTime(long sendTime) {
            this.sendTime = sendTime;
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

        public String getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(String readStatus) {
            this.readStatus = readStatus;
        }

        @Override
        public MessageItemVO transform() {

            MessageItemVO message = new MessageItemVO();
            message.newsTitle = title;
            message.messageId = intercomReceiveId;
//            message.time =
            message.time = TimeUtil.formatData(TimeUtil.dateFormat, sendTime);
            message.createUserName = createUserName;
            message.newsContent = content;
            if (StringUtils.equals(readStatus, "Y")) {
                message.status = "已读";
            } else if (StringUtils.equals(readStatus, "N")) {
                message.status = "未读";
            }
            return message;
        }
    }
}
