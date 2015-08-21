package com.tanyixiu.scrumer.models;

import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Mimo on 2015/8/16.
 */
@Table(name = "userteam", id = "tb_user_team_id")
public class TeamUser extends Model{
    @Column(name = "id", notNull = true, unique = true, index = true)
    private String id;

    @Column(name = "teamid", notNull = true, uniqueGroups = {"userid", "teamid"})
    private String teamId;

    @Column(name = "userid", notNull = true, uniqueGroups = {"userid", "teamid"})
    private String userId;

    @Column(name = "jointime")
    private String joinTime;

    public TeamUser() {
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

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            return "";
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getJoinTime() {
        if (TextUtils.isEmpty(joinTime)) {
            return "";
        }
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
}
