package com.kp.monitor.data.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * des:消息列表
 * Created by HL
 * on 2017-05-10.
 */

public class MessageItemVO implements Parcelable{

    public String messageId = "";
    public String time;
    public String newsTitle = "";
    public String newsContent = "";
    public String status = "";
    public String createUserName = "";

    public MessageItemVO(Parcel in) {
        messageId = in.readString();
        time = in.readString();
        newsTitle = in.readString();
        newsContent = in.readString();
        status = in.readString();
        createUserName = in.readString();
    }
    public MessageItemVO(){}

    public static final Creator<MessageItemVO> CREATOR = new Creator<MessageItemVO>() {
        @Override
        public MessageItemVO createFromParcel(Parcel in) {
            return new MessageItemVO(in);
        }

        @Override
        public MessageItemVO[] newArray(int size) {
            return new MessageItemVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(messageId);
        parcel.writeString(time);
        parcel.writeString(newsTitle);
        parcel.writeString(newsContent);
        parcel.writeString(status);
        parcel.writeString(createUserName);
    }
}
