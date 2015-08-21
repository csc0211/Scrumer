package com.tanyixiu.scrumer.datas;


import com.activeandroid.query.Select;
import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.http.VolleyHelper.RequestListener;
import com.tanyixiu.scrumer.models.User;
import com.tanyixiu.scrumer.utils.JsonHelper;

/**
 * Created by Mimo on 2015/8/20.
 */
public class DataHelper {
    public interface CallBackListener {
        void onSuccess(String result);

        void onFailure(Exception ex);
    }

    public static void saveRegisterUser(User user, final CallBackListener listener) {
        try {
            User existUser = new Select().from(User.class).where("name=?", user.getName()).executeSingle();
            if (null != existUser) {
                throw new Exception(String.format("用户名'%s'已经存在"));
            }
            user.save();

            String sql = SqlHelper.insertUserSql(user);
            VolleyHelper.requestServer(sql, new RequestListener() {
                @Override
                public void onResponse(Object o) {
                    if (null != listener) {
                        listener.onSuccess("");
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    if (null != listener) {
                        listener.onFailure(volleyError);
                    }
                }
            });

        } catch (Exception ex) {
            if (null != listener) {
                listener.onFailure(ex);
            }
        }
    }

    public static void getLoginUser(String name, String md5Password, final CallBackListener listener) {
        try {
            final User user = new Select().from(User.class)
                    .where("name=? and password=?", name, md5Password)
                    .executeSingle();

            if (null != user) {
                App.setLoginUser(user);
                listener.onSuccess("");
                return;
            }

            String sql = SqlHelper.getLoginUser(name, md5Password);
            VolleyHelper.requestServer(sql, new RequestListener<String>() {
                @Override
                public void onResponse(String s) {
                    User serverUser;
                    try {
                        serverUser = JsonHelper.toEntity(s, User.class);
                        if (null == serverUser) {
                            listener.onFailure(new Exception("用户名或密码不正确"));
                            return;
                        }
                        serverUser.save();
                        App.setLoginUser(serverUser);
                        listener.onSuccess("");
                    } catch (Exception ex) {
                        listener.onFailure(ex);
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    listener.onFailure(new Exception("解析数据失败" + volleyError.getMessage()));
                }
            });
        } catch (Exception ex) {
            listener.onFailure(ex);
        }
    }
}