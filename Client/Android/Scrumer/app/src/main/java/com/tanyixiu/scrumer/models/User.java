package com.tanyixiu.scrumer.models;

import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Mimo on 2015/8/20.
 */
@Table(name = "user", id = "tb_user_id")
public class User extends Model {

    @Column(name = "id", notNull = true, unique = true, index = true)
    private String id;

    @Column(name = "name", notNull = true, unique = true, index = true)
    private String name;

    @Column(name = "password", notNull = true)
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "registertime")
    private String registerTime;

    @Column(name = "photourl")
    private String photoUrl;


    public User() {
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

    public String getPassword() {
        if (TextUtils.isEmpty(password)) {
            return "";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        if (TextUtils.isEmpty(tel)) {
            return "";
        }
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        if (TextUtils.isEmpty(email)) {
            return "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterTime() {
        if (TextUtils.isEmpty(registerTime)) {
            return "";
        }
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPhotoUrl() {
        if (TextUtils.isEmpty(photoUrl)) {
            return "";
        }
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {

        this.photoUrl = photoUrl;
    }
}

