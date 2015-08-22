package com.tanyixiu.scrumer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.models.User;
import com.tanyixiu.scrumer.util.CommonUtils;
import com.tanyixiu.scrumer.util.JsonHelper;
import com.tanyixiu.scrumer.util.StringHelper;
import com.tanyixiu.scrumer.util.ToastUtil;
import com.tanyixiu.widgets.LoginEditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;
    private ViewHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        setContentView(rootView);
        init(rootView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE != requestCode || RESULT_OK != resultCode) {
            return;
        }
        String name = data.getStringExtra("name");
        mHolder.mEtName.setText(name);
        mHolder.mEtPassword.setText("");
    }

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);

        mHolder.mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLoginClick();
            }
        });

        mHolder.mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.startActivityForResult(LoginActivity.this, REQUEST_CODE);
            }
        });
    }

    private void btnLoginClick() {
        String name = mHolder.mEtName.getText().toString();
        String password = mHolder.mEtPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            CommonUtils.showToast(R.string.login_toast_emptyname);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            CommonUtils.showToast(R.string.login_toast_emptypassword);
            return;
        }
        requestLogin(name, password);
    }

    private void requestLogin(String name, String password) {
        setLoading(true);

        String md5Pwd = StringHelper.toMD5(password);
        String param = SqlHelper.getLoginUser(name, md5Pwd);
        String url = UrlHelper.initReadUrl(param);
        executeRequest(new StringRequest(url, responseListener(), errorListener()));
    }

    private Response.Listener<String> responseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                setLoading(false);
                User user;
                try {
                    user = JsonHelper.toEntity(s, User.class);
                    if (null == user) {
                        ToastUtil.showLong(R.string.login_toast_login_error);
                        return;
                    }
                    user.save();
                    App.setLoginUser(user);
                    TeamActivity.startActivity(LoginActivity.this);
                    finish();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ToastUtil.showLong(ex.getMessage());
                }
            }
        };
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_login.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.login_et_username)
        LoginEditText mEtName;
        @InjectView(R.id.login_et_password)
        LoginEditText mEtPassword;
        @InjectView(R.id.login_btn_ok)
        Button mBtnOk;
        @InjectView(R.id.login_btn_register)
        Button mBtnRegister;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
