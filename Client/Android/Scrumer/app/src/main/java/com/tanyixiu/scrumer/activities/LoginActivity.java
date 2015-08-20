package com.tanyixiu.scrumer.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.datas.DataSaver;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.StringHelper;
import com.tanyixiu.widgets.CircularProgressDialog;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class LoginActivity extends BaseActivity {

    private Button btnOk;
    private Button btnRegister;
    private EditText edUsername;
    private EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        setContentView(rootView);
        initView(rootView);
        initViewEvent();
    }

    private void initView(View rootView) {
        this.btnOk = (Button) rootView.findViewById(R.id.login_btn_ok);
        this.btnRegister = (Button) rootView.findViewById(R.id.login_btn_register);
        this.edUsername = (EditText) rootView.findViewById(R.id.login_ed_username);
        this.edPassword = (EditText) rootView.findViewById(R.id.login_ed_password);
    }

    private void initViewEvent() {
        this.btnOk.setOnClickListener(mOnClickListener);
        this.btnRegister.setOnClickListener(mOnClickListener);
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

    private void btnRegisterClick() {
        RegisterActivity.startActivity(LoginActivity.this);
    }

    private void requestLogin(String name, String password) {
        final CircularProgressDialog bar = CircularProgressDialog.show(LoginActivity.this);

        String md5Pwd = StringHelper.toMD5(password);
        DataSaver.getLoginUser(name, md5Pwd, new DataSaver.CallBackListener() {
            @Override
            public void onSuccess(String result) {
                bar.dismiss();
                ProjectActivity.startActivity(LoginActivity.this, "1");
                finish();
            }

            @Override
            public void onFailure(Exception ex) {
                CommonUtils.showToast(ex.getMessage());
                bar.dismiss();
            }
        });

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_ok:
                    btnLoginClick();
                    break;
                case R.id.login_btn_register:
                    btnRegisterClick();
                    break;
                default:
                    break;
            }
        }
    };
}
