package com.tanyixiu.scrumer.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Mimo on 2015/8/16.
 */
@Table(name = "project", id = "tb_project_id")
public class Project extends Model implements Parcelable {
    @Column(name = "id", notNull = true, unique = true, index = true)
    private String id;

    @Column(name = "teamid", notNull = true)
    private String teamId;

    @Column(name = "name", notNull = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creatorid")
    private String creatorId;

    @Column(name = "createtime")
    private String createTime;

    @Column(name = "state")
    private int state;

    @Column(name = "sectionid")
    private String sectionId;

    public Project() {

    }

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            return "";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        if (TextUtils.isEmpty(teamId)) {
            return "";
        }
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (TextUtils.isEmpty(description)) {
            return "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        if (TextUtils.isEmpty(creatorId)) {
            return "";
        }
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        if (TextUtils.isEmpty(createTime)) {
            return "";
        }
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
        if (TextUtils.isEmpty(sectionId)) {
            return "";
        }
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
