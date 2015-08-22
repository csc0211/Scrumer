package com.tanyixiu.scrumer;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tanyixiu.scrumer.models.User;

/**
 * Created by Mimo on 2015/8/17.
 */
public class App extends Application {

    private static Context sContext;
    private static User sLoginUser;
    private static RequestQueue sRequestQueue;
    private static RefWatcher sRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        ActiveAndroid.initialize(sContext);
        sRefWatcher = LeakCanary.install(this);
        sRequestQueue = Volley.newRequestQueue(sContext);
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }

    public static Context getContext() {
        return sContext;
    }

    public static User getLoginUser() {
        return sLoginUser;
    }

    public static void setLoginUser(User mLoginUser) {
        App.sLoginUser = mLoginUser;
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
}