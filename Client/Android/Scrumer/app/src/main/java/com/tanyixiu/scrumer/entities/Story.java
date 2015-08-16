package com.tanyixiu.scrumer.entities;

import android.text.TextUtils;

/**
 * Created by Mimo on 2015/8/14.
 */
public class Story{

    private String id;
    private String projectId;
    private String name;
    private String description;
    private int    state;
    private int    priority;
    private String parentId;
    private String creatorId;
    private String createTime;

    public Story() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isParent(){
        return this.parentId.equals("0") || TextUtils.isEmpty(this.parentId);
    }

    public final class StoryPriority{
        public static final int LOW     = 0;
        public static final int NORMAL  = 1;
        public static final int HIGHT   = 2;
    }

    public final class StoryState{
        public static final int TO_DEV  = 0;
        public static final int DEVING  = 1;
        public static final int TO_TEST = 2;
        public static final int TESTING = 3;
        public static final int DONE    = 4;
    }
}
