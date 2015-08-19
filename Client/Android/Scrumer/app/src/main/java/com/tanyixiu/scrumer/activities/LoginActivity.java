package com.tanyixiu.scrumer.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.entities.Project;
import com.tanyixiu.scrumer.entities.User;
import com.tanyixiu.scrumer.http.HttpHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.http.VolleyHelper.RequestListener;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.security.MessageDigest;

public class LoginActivity extends BaseActivity {

    private Button btnOk;
    private EditText edUsername;
    private EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initViewEvent();
    }

    private void initView() {
        this.btnOk = (Button) findViewById(R.id.login_btn_ok);
        this.edUsername = (EditText) findViewById(R.id.login_ed_username);
        this.edPassword = (EditText) findViewById(R.id.login_ed_password);
    }

    private void initViewEvent() {
        this.btnOk.setOnClickListener(mOnClickListener);
    }

    private void btnLoginClick() {
        String name = edUsername.getText().toString();
        String password = edPassword.getText().toString();
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

    private void toggleEnable(boolean isLoading) {
        this.edUsername.setEnabled(!isLoading);
        this.edPassword.setEnabled(!isLoading);
        this.btnOk.setEnabled(!isLoading);
    }

    private void requestLogin(String name, String password) {
        toggleEnable(true);
        String sql = SqlHelper.getLoginUser(name, password);
        VolleyHelper.requestString(sql, mRequestListener);
    }

    private void loginSuccess(User user) {
        App.setLoginUser(user);
        ProjectActivity.startActivity(LoginActivity.this, "1");
        finish();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_ok:
                    btnLoginClick();
                    break;
                default:
                    break;
            }
        }
    };

    private RequestListener<String> mRequestListener = new RequestListener<String>() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            toggleEnable(false);
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
            toggleEnable(false);
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
            loginSuccess(user);
        }
    };
}
