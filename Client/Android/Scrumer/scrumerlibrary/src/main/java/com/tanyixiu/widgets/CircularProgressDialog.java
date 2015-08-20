package com.tanyixiu.widgets;

import android.app.Dialog;
import android.content.Context;

import com.tanyixiu.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Mimo on 2015/8/20.
 */
public class CircularProgressDialog extends Dialog {

    public CircularProgressDialog(Context context) {
        super(context, R.style.progress_dialog);
    }


    public static CircularProgressDialog show(Context context) {
        return show(context, false);
    }

    public static CircularProgressDialog show(Context context, boolean cancelable) {
        CircularProgressDialog dialog = new CircularProgressDialog(context);

        CircularProgressBar circularProgressBar = new CircularProgressBar(context);
        dialog.setContentView(circularProgressBar);

        dialog.setCancelable(cancelable);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        return dialog;
    }
}
