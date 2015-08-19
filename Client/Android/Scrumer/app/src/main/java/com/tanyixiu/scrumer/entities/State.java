package com.tanyixiu.scrumer.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mimo on 2015/8/18.
 */
public class State implements Parcelable{

    private String id;
    private int stateValue;
    private String stateName;
    private String sectionId;
    private boolean isPool;

    public State() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStateValue() {
        return stateValue;
    }

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public boolean isPool() {
        return isPool;
    }

    public void setIsPool(boolean isPool) {
        this.isPool = isPool;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.stateValue);
        dest.writeString(this.stateName);
        dest.writeString(this.sectionId);
        dest.writeByte(isPool ? (byte) 1 : (byte) 0);
    }

    protected State(Parcel in) {
        this.id = in.readString();
        this.stateValue = in.readInt();
        this.stateName = in.readString();
        this.sectionId = in.readString();
        this.isPool = in.readByte() != 0;
    }

    public static final Creator<State> CREATOR = new Creator<State>() {
        public State createFromParcel(Parcel source) {
            return new State(source);
        }

        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
