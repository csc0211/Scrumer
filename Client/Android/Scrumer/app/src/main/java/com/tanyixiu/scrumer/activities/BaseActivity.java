package com.tanyixiu.scrumer.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.data.RequestManager;
import com.tanyixiu.scrumer.util.ToastUtil;
import com.tanyixiu.widgets.LoadingDialog;

/**
 * Created by Mimo on 2015/8/13.
 */
public class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatusBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getRefWatcher().watch(this);
        RequestManager.cancelAll(this.toString());
    }

    protected void setLoading(boolean isLoading) {
        if (false == isLoading && null == mLoadingDialog) {
            return;
        }

        if (true == isLoading && null == mLoadingDialog) {
            mLoadingDialog = LoadingDialog.init(this);
        }

        if (isLoading) {
            mLoadingDialog.show();
        } else {
            mLoadingDialog.dismiss();
        }
    }


    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this.toString());
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                setLoading(false);
                volleyError.printStackTrace();
                ToastUtil.showLong(volleyError.getMessage());
            }
        };
    }

    private void setTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarAlpha(0.0f);
    }

}
