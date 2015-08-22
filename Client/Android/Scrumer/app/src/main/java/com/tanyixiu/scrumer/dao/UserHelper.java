package com.tanyixiu.scrumer.dao;

import com.activeandroid.query.Select;
import com.tanyixiu.scrumer.models.User;

/**
 * Created by Mimo on 2015/8/21.
 */
public class UserHelper {

    public static User getUser(String name, String md5Password) {

        return new Select().from(User.class).where("name=? and password=?", name, md5Password).executeSingle();
    }

    public static User isExistName(String name) {
        return new Select().from(User.class).where("name=?", name).executeSingle();
    }
}
