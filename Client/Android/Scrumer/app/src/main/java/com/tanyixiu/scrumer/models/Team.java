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
@Table(name = "team", id = "tb_team_id")
public class Team extends Model implements Parcelable {

    @Column(name = "id", notNull = true, unique = true, index = true)
    private String id;

    @Column(name = "name", notNull = true, unique = true, index = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creatorid")
    private String creatorId;

    @Column(name = "createtime")
    private String createTime;

    public Team() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.creatorId);
        dest.writeString(this.createTime);
    }

    protected Team(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.creatorId = in.readString();
        this.createTime = in.readString();
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
