package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class Project implements Parcelable {
    private String id;
    private String name;
    private String photoid;
    private String teamid;
    private String description;

    public Project() {
    }

    public Project(String id, String name, String photoid, String teamid, String description) {
        this.id = id;
        this.name = name;
        this.photoid = photoid;
        this.teamid = teamid;
        this.description = description;
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

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.photoid);
        dest.writeString(this.teamid);
        dest.writeString(this.description);
    }

    protected Project(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.photoid = in.readString();
        this.teamid = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
