package com.tanyixiu.widgets;

import android.app.Dialog;
import android.content.Context;

import com.tanyixiu.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Mimo on 2015/8/20.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.progress_dialog);
    }

    public static LoadingDialog init(Context context) {
        return init(context, false);
    }

    public static LoadingDialog init(Context context, boolean cancelable) {
        LoadingDialog dialog = new LoadingDialog(context);
        CircularProgressBar circularProgressBar = new CircularProgressBar(context);
        dialog.setContentView(circularProgressBar);
        dialog.setCancelable(cancelable);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static LoadingDialog show(Context context) {
        return show(context, false);
    }

    public static LoadingDialog show(Context context, boolean cancelable) {
        LoadingDialog dialog = new LoadingDialog(context);

        CircularProgressBar circularProgressBar = new CircularProgressBar(context);
        dialog.setContentView(circularProgressBar);

        dialog.setCancelable(cancelable);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        return dialog;
    }
}
