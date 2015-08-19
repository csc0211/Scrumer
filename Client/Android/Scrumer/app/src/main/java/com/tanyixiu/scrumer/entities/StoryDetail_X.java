package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mimo on 2015/8/19.
 */
public class StoryDetail_X implements Parcelable {
    private Story mStory;
    private State mState;
    private List<StoryUser> mStoryUsers;


    public StoryDetail_X() {
    }

    public Story getStory() {
        return mStory;
    }

    public void setStory(Story story) {
        mStory = story;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public List<StoryUser> getStoryUsers() {
        return mStoryUsers;
    }

    public void setStoryUsers(List<StoryUser> storyUsers) {
        mStoryUsers = storyUsers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mStory, 0);
        dest.writeParcelable(this.mState, flags);
        dest.writeList(this.mStoryUsers);
    }

    protected StoryDetail_X(Parcel in) {
        this.mStory = in.readParcelable(Story.class.getClassLoader());
        this.mState = in.readParcelable(State.class.getClassLoader());
        this.mStoryUsers = new ArrayList<StoryUser>();
        in.readList(this.mStoryUsers, List.class.getClassLoader());
    }

    public static final Creator<StoryDetail_X> CREATOR = new Creator<StoryDetail_X>() {
        public StoryDetail_X createFromParcel(Parcel source) {
            return new StoryDetail_X(source);
        }

        public StoryDetail_X[] newArray(int size) {
            return new StoryDetail_X[size];
        }
    };
}
