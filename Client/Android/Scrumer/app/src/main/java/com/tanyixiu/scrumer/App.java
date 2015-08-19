package com.tanyixiu.scrumer;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tanyixiu.scrumer.entities.User;

/**
 * Created by Mimo on 2015/8/17.
 */
public class App extends Application {

    private static Context sContext;
    private static User sMLoginUser;
    private static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        sRequestQueue = Volley.newRequestQueue(sContext);
    }

    public static Context getContext() {
        return sContext;
    }

    public static User getLoginUser() {
        return sMLoginUser;
    }

    public static void setLoginUser(User mLoginUser) {
        App.sMLoginUser = mLoginUser;
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
}
