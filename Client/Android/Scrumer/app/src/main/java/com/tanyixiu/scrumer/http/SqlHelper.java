package com.tanyixiu.scrumer.http;

import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.models.TeamUser;
import com.tanyixiu.scrumer.models.User;

/**
 * Created by Mimo on 2015/8/17.
 */
public class SqlHelper {
    public static String getLoginUser(String name, String md5Password) {

        String sql = "select * from user where name= '%s' and password='%s'";
        return String.format(sql, name, md5Password);
    }

    public static String getProjects(String teamId) {
        String sql = "select * from project where teamid= '%s'";
        return String.format(sql, teamId);
    }

    public static String getProjectState(String sectionId) {
        return String.format("select * from `state` where sectionid = '%s'", sectionId);
    }

    public static String getStories(String projectId, String stateId) {

        return String.format("select * from story where projectid='%s' and stateid='%s'", projectId, stateId);
    }

    public static String getStoryState(String id) {
        return String.format("select * from `state` where id = '%s'", id);
    }

    public static String getStoryUser(String id) {
        return String.format("select * from `story_user` where id = '%s'", id);
    }

    public static String insertUserSql(User user) {

        String sql = "insert into user \n" +
                "(id, `name`, `password`, tel, email, registertime, photourl) \n" +
                "values('%s', '%s', '%s','%s', '%s', '%s', '%s')";

        return String.format(sql, user.getId(), user.getName(), user.getPassword(),
                user.getTel(), user.getEmail(), user.getRegisterTime(), user.getPhotoUrl());
    }

    public static String getTeams(String userId) {
        return String.format("select t.*  from team t join team_user tu on t.id = tu.teamid where tu.userid = '%s'", userId);
    }

    public static String insertTeamSql(Team team) {
        String sql = "insert into team \n" +
                "\t(id, `name`, description, creatorid, createtime)\n" +
                "\tvalues\n" +
                "\t('%s', '%s', '%s', '%s', '%s')";
        return String.format(sql, team.getId(), team.getName(), team.getDescription(),
                team.getCreatorId(), team.getCreateTime());
    }

    public static String insertTeamUserSql(TeamUser teamUser) {
        String sql = "insert into team_user (id, teamid, userid, jointime)values('%s', '%s', '%s', '%s')";
        return String.format(sql, teamUser.getId(), teamUser.getTeamId(), teamUser.getUserId(), teamUser.getJoinTime());
    }
}
