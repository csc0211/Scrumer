package com.tanyixiu.scrumer.http;

/**
 * Created by Mimo on 2015/8/17.
 */
public class SqlHelper {
    public static String getLoginUser(String name, String password) {

        String sql = "select * from user where name= '%s' and password='%s'";
        return String.format(sql, name, password);
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
}
