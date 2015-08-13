package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class Story implements Parcelable {
    private String id;
    private String groupid;
    private String projectid;
    private String teamid;
    private String description;
    private int state;

    public Story() {
    }

    public Story(String id, String groupid, String projectid, String teamid, String description, int state) {
        this.id = id;
        this.groupid = groupid;
        this.projectid = projectid;
        this.teamid = teamid;
        this.description = description;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.groupid);
        dest.writeString(this.projectid);
        dest.writeString(this.teamid);
        dest.writeString(this.description);
        dest.writeInt(this.state);
    }

    protected Story(Parcel in) {
        this.id = in.readString();
        this.groupid = in.readString();
        this.projectid = in.readString();
        this.teamid = in.readString();
        this.description = in.readString();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
