package com.tanyixiu.scrumer.utils;

import android.widget.Toast;

import com.tanyixiu.scrumer.App;

/**
 * Created by Mimo on 2015/8/18.
 */
public class CommonUtils {

    public static void showToast(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(int stringResourceId) {
        Toast.makeText(App.getContext(), stringResourceId, Toast.LENGTH_LONG).show();
    }
}
