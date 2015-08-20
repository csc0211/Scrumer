package com.tanyixiu.scrumer.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.tanyixiu.scrumer.entities.StoryDetail_X;

import java.util.List;

/**
 * Created by Mimo on 2015/8/18.
 */
public class JsonHelper {

    public static <T> T toEntity(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<T> entities = toEntities(json, clazz);
        if (null == entities || 0 == entities.size()) {
            return null;
        }
        return entities.get(0);
    }

    public static <T> List<T> toEntities(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseArray(json, clazz);
    }

    public static StoryDetail_X toStoryDetail(String s) {
        return null;
    }
}