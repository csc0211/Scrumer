package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/13.
 */
public class RelStoryUser implements Parcelable {
    private String id;
    private String storyid;
    private String userid;

    public RelStoryUser() {
    }

    public RelStoryUser(String id, String storyid, String userid) {
        this.id = id;
        this.storyid = storyid;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoryid() {
        return storyid;
    }

    public void setStoryid(String storyid) {
        this.storyid = storyid;
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
        dest.writeString(this.storyid);
        dest.writeString(this.userid);
    }

    protected RelStoryUser(Parcel in) {
        this.id = in.readString();
        this.storyid = in.readString();
        this.userid = in.readString();
    }

    public static final Parcelable.Creator<RelStoryUser> CREATOR = new Parcelable.Creator<RelStoryUser>() {
        public RelStoryUser createFromParcel(Parcel source) {
            return new RelStoryUser(source);
        }

        public RelStoryUser[] newArray(int size) {
            return new RelStoryUser[size];
        }
    };
}
