package com.tanyixiu.scrumer.util;

import android.widget.Toast;

import com.tanyixiu.scrumer.App;

/**
 * Created by Mimo on 2015/8/21.
 */
public class ToastUtil {

    private ToastUtil() {

    }

    public static void showLong(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_LONG).show();
    }

}
