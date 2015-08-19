package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/16.
 */
public class StoryUser implements Parcelable {
    private String id;
    private String storyId;
    private String userId;
    private String stateId;
    private String startTime;
    private String finishTime;

    public StoryUser() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.storyId);
        dest.writeString(this.userId);
        dest.writeString(this.stateId);
        dest.writeString(this.startTime);
        dest.writeString(this.finishTime);
    }

    protected StoryUser(Parcel in) {
        this.id = in.readString();
        this.storyId = in.readString();
        this.userId = in.readString();
        this.stateId = in.readString();
        this.startTime = in.readString();
        this.finishTime = in.readString();
    }

    public static final Creator<StoryUser> CREATOR = new Creator<StoryUser>() {
        public StoryUser createFromParcel(Parcel source) {
            return new StoryUser(source);
        }

        public StoryUser[] newArray(int size) {
            return new StoryUser[size];
        }
    };
}
