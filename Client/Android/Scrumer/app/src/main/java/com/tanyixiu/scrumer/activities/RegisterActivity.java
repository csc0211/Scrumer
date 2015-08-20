package com.tanyixiu.scrumer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.datas.DataSaver;
import com.tanyixiu.scrumer.models.User;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.StringHelper;

import java.util.UUID;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvName;
    private TextView tvPassword;
    private TextView tvConfirmPwssword;
    private Button btnCancel;
    private Button btnOk;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_register, null);
        setContentView(rootView);
        initView(rootView);
        initViewEvent();
    }

    private void initView(View rootView) {
        tvName = (TextView) rootView.findViewById(R.id.register_et_name);
        tvPassword = (TextView) rootView.findViewById(R.id.register_et_password);
        tvConfirmPwssword = (TextView) rootView.findViewById(R.id.register_et_confirmpassword);
        btnCancel = (Button) rootView.findViewById(R.id.register_btn_cancel);
        btnOk = (Button) rootView.findViewById(R.id.register_btn_ok);
    }

    private void initViewEvent() {
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_cancel:
                btnCancelClick();
                break;
            case R.id.register_btn_ok:
                btnOkClick();
                break;
            default:
                break;
        }
    }

    private void btnOkClick() {
        String name = String.valueOf(tvName.getText());
        String password = String.valueOf(tvPassword.getText());
        String confirmPassword = String.valueOf(tvConfirmPwssword.getText());
        if (TextUtils.isEmpty(name)) {
            CommonUtils.showToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            CommonUtils.showToast("密码不能为空");
            return;
        }

        if (!password.equals(confirmPassword)) {
            CommonUtils.showToast("两次密码不一致");
            return;
        }

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(name);
        user.setPassword(StringHelper.toMD5(password));
        user.setRegisterTime(StringHelper.getCurrentTime());
        DataSaver.saveRegisterUser(user, new DataSaver.CallBackListener() {
            @Override
            public void onSuccess(String result) {
                CommonUtils.showToast("注册成功，请重新登录");
                RegisterActivity.this.finish();
            }

            @Override
            public void onFailure(Exception ex) {
                CommonUtils.showToast(ex.getMessage());
            }
        });
    }

    private void btnCancelClick() {
        this.finish();
    }
}
