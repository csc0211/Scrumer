package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/16.
 */
public class Project implements Parcelable {
    private String id;
    private String teamId;
    private String name;
    private String description;
    private String creatorId;
    private String createTime;
    private int state;
    private String sectionId;

    public Project() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.teamId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.creatorId);
        dest.writeString(this.createTime);
        dest.writeInt(this.state);
        dest.writeString(this.sectionId);
    }

    protected Project(Parcel in) {
        this.id = in.readString();
        this.teamId = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.creatorId = in.readString();
        this.createTime = in.readString();
        this.state = in.readInt();
        this.sectionId = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
