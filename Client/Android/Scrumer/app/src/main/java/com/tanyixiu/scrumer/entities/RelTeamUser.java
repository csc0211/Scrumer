package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class RelTeamUser implements Parcelable {
    private String id;
    private String teamid;
    private String userid;

    public RelTeamUser() {
    }

    public RelTeamUser(String id, String teamid, String userid) {
        this.id = id;
        this.teamid = teamid;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.teamid);
        dest.writeString(this.userid);
    }

    protected RelTeamUser(Parcel in) {
        this.id = in.readString();
        this.teamid = in.readString();
        this.userid = in.readString();
    }

    public static final Parcelable.Creator<RelTeamUser> CREATOR = new Parcelable.Creator<RelTeamUser>() {
        public RelTeamUser createFromParcel(Parcel source) {
            return new RelTeamUser(source);
        }

        public RelTeamUser[] newArray(int size) {
            return new RelTeamUser[size];
        }
    };
}
