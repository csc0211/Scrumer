package com.tanyixiu.scrumer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Mimo on 2015/8/20.
 */
@Table(name = "user", id = "tb_user_id")
public class User extends Model {

    @Column(name = "userid", notNull = true, unique = true, index = true)
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

