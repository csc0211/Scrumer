package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class StoryGroup implements Parcelable {
    private String id;
    private String name;
    private String projectid;
    private String teamid;

    public StoryGroup() {
    }

    public StoryGroup(String id, String name, String projectid, String teamid) {
        this.id = id;
        this.name = name;
        this.projectid = projectid;
        this.teamid = teamid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.projectid);
        dest.writeString(this.teamid);
    }

    protected StoryGroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.projectid = in.readString();
        this.teamid = in.readString();
    }

    public static final Parcelable.Creator<StoryGroup> CREATOR = new Parcelable.Creator<StoryGroup>() {
        public StoryGroup createFromParcel(Parcel source) {
            return new StoryGroup(source);
        }

        public StoryGroup[] newArray(int size) {
            return new StoryGroup[size];
        }
    };
}
