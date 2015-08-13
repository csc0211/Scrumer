package com.tanyixiu.scrumer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tanyixiu.scrumer.R;

public class LoginActivity extends BaseActivity {

    private Button   btnOk;
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
        this.btnOk      = (Button) findViewById(R.id.login_btn_ok);
        this.edUsername = (EditText) findViewById(R.id.login_ed_username);
        this.edPassword = (EditText) findViewById(R.id.login_ed_password);
    }

    private void initViewEvent() {
        this.btnOk.setOnClickListener(mOnClickListener);
    }

    private void btnLoginClick() {
        flyToMain();
    }

    private void flyToMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_btn_ok:
                    btnLoginClick();
                    break;
                default:
                    break;
            }
        }
    };


}
