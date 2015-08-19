package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Mimo on 2015/8/14.
 */
public class Story implements Parcelable {

    private String id;
    private String projectId;
    private String name;
    private String description;
    private String stateId;
    private String creatorId;
    private String createTime;
    private int priority;

    public Story() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.projectId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.stateId);
        dest.writeString(this.creatorId);
        dest.writeString(this.createTime);
        dest.writeInt(this.priority);
    }

    protected Story(Parcel in) {
        this.id = in.readString();
        this.projectId = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.stateId = in.readString();
        this.creatorId = in.readString();
        this.createTime = in.readString();
        this.priority = in.readInt();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
