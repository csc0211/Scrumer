package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class LogMessage implements Parcelable {
    private String id;
    private String description;
    private String actiontime;

    public LogMessage() {
    }

    public LogMessage(String id, String description, String actiontime) {
        this.id = id;
        this.description = description;
        this.actiontime = actiontime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActiontime() {
        return actiontime;
    }

    public void setActiontime(String actiontime) {
        this.actiontime = actiontime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeString(this.actiontime);
    }

    protected LogMessage(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.actiontime = in.readString();
    }

    public static final Parcelable.Creator<LogMessage> CREATOR = new Parcelable.Creator<LogMessage>() {
        public LogMessage createFromParcel(Parcel source) {
            return new LogMessage(source);
        }

        public LogMessage[] newArray(int size) {
            return new LogMessage[size];
        }
    };
}
