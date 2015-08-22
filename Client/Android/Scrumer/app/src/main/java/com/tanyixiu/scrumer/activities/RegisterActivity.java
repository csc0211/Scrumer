package com.tanyixiu.scrumer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.dao.UserHelper;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.models.User;
import com.tanyixiu.scrumer.util.StringHelper;
import com.tanyixiu.scrumer.util.ToastUtil;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends BaseActivity {

    private ViewHolder mHolder;

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_register, null);
        setContentView(rootView);
        init(rootView);
    }

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);
        mHolder.mEtName = (EditText) rootView.findViewById(R.id.register_et_name);
        mHolder.mEtPassword = (EditText) rootView.findViewById(R.id.register_et_password);
        mHolder.mEtConfirm = (EditText) rootView.findViewById(R.id.register_et_confirmpassword);
        mHolder.mBtnCancel = (Button) rootView.findViewById(R.id.register_btn_cancel);
        mHolder.mBtnRegister = (Button) rootView.findViewById(R.id.register_btn_ok);

        mHolder.mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        mHolder.mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOkClick();
            }
        });
    }

    private void btnOkClick() {
        String name = String.valueOf(mHolder.mEtName.getText());
        String password = String.valueOf(mHolder.mEtPassword.getText());
        String confirmPassword = String.valueOf(mHolder.mEtConfirm.getText());
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showLong(R.string.register_toast_name_notnull);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showLong(R.string.register_toast_password_notnull);
            return;
        }

        if (!password.equals(confirmPassword)) {
            ToastUtil.showLong(R.string.register_toast_confirm_notequal);
            return;
        }

        User existUser = UserHelper.isExistName(name);
        if (null != existUser) {
            ToastUtil.showLong(getString(R.string.register_toast_name_isexist, name));
            return;
        }

        saveUser(name, password);
    }

    private void saveUser(String name, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setPassword(StringHelper.toMD5(password));
        user.setRegisterTime(StringHelper.getCurrentTime());

        setLoading(true);
        String param = SqlHelper.insertUserSql(user);
        String url = UrlHelper.initWriteUrl(param);
        executeRequest(new StringRequest(url, responseListener(user), errorListener()));
    }

    private Response.Listener<String> responseListener(final User user) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                user.save();
                setLoading(false);
                ToastUtil.showLong(R.string.register_toast_success);

                Intent data = new Intent();
                data.putExtra("name", user.getName());
                setResult(RESULT_OK, data);
                finish();
            }
        };
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_register.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.register_et_name)
        EditText mEtName;
        @InjectView(R.id.register_et_password)
        EditText mEtPassword;
        @InjectView(R.id.register_et_confirmpassword)
        EditText mEtConfirm;
        @InjectView(R.id.register_btn_cancel)
        Button mBtnCancel;
        @InjectView(R.id.register_btn_ok)
        Button mBtnRegister;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
