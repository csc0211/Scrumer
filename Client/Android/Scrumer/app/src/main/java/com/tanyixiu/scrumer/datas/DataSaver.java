package com.tanyixiu.scrumer.datas;


import android.os.Message;
import android.text.TextUtils;

import com.activeandroid.query.Select;
import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.http.VolleyHelper.RequestListener;
import com.tanyixiu.scrumer.models.User;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Mimo on 2015/8/20.
 */
public class DataSaver {
    public interface CallBackListener {
        void onSuccess(String result);

        void onFailure(Exception ex);
    }


    //考虑主线程与子线程之间的切换
    public static void saveRegisterUser(User user, CallBackListener listener) {
        try {

            User existUser = new Select().from(User.class).where("name=?", user.getName()).executeSingle();
            if (null != existUser) {
                throw new Exception(String.format("用户名'%s'已经存在"));
            }
            user.save();
            //Volley post request
            if (null != listener) {
                listener.onSuccess("");
            }

        } catch (Exception ex) {
            if (null != listener) {
                listener.onFailure(ex);
            }
        }
    }

    public static void getLoginUser(String name, String md5Password, CallBackListener listener) {
        try {
            User user = new Select().from(User.class)
                    .where("name=? and password=?", name, md5Password)
                    .executeSingle();

            if (null == user) {
                throw new Exception("用户名或密码不正确");
            }
//        String sql = SqlHelper.getLoginUser(name, password);
//        VolleyHelper.requestString(sql, mRequestListener);
            App.setLoginUser(user);
            if (null != listener) {
                listener.onSuccess("");
            }
        } catch (Exception ex) {
            if (null != listener) {
                listener.onFailure(ex);
            }
        }
    }


    private RequestListener<String> mRequestListener = new RequestListener<String>() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
//            toggleEnable(false);
            volleyError.printStackTrace();
            String errorMessage = volleyError.getMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                CommonUtils.showToast(errorMessage);
            } else {
                CommonUtils.showToast(R.string.login_toast_request_error);
            }
        }

        @Override
        public void onResponse(String response) {
//            toggleEnable(false);
            User user = null;
            try {
                user = JsonHelper.toEntity(response, User.class);
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtils.showToast(e.getMessage());
                return;
            }
            if (null == user) {
                CommonUtils.showToast(R.string.login_toast_login_error);
                return;
            }
//            loginSuccess(user);
        }
    };

    public static void test() {
        android.os.Handler handler = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

    }
}