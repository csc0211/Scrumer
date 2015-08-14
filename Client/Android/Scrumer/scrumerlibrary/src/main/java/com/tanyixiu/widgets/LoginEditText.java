package com.tanyixiu.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.tanyixiu.R;

/**
 * Created by Mimo on 2015/8/13.
 */
public class LoginEditText extends EditText {

    public LoginEditText(Context context) {
        super(context);
        initEvent();
    }

    public LoginEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEvent();
    }

    public LoginEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEvent();
    }

    private void initEvent() {
        this.addTextChangedListener(mTextWatcher);
        this.setOnTouchListener(mOnTouchListener);
        this.setOnFocusChangeListener(mOnFocusChangeListener);
        setSelectionEnd();
    }

    private void setSelectionEnd(){
        String text = this.getText().toString();
        if(TextUtils.isEmpty(text)){
            return;
        }
        this.setSelection(text.length());
    }

    private void showRightDrawable() {
        LoginEditText.this.setCompoundDrawablesWithIntrinsicBounds(
                LoginEditText.this.getCompoundDrawables()[0],
                LoginEditText.this.getCompoundDrawables()[1],
                getResources().getDrawable(R.drawable.pic_delete),
                LoginEditText.this.getCompoundDrawables()[3]);
    }

    private void hideRightDrawable() {
        LoginEditText.this.setCompoundDrawablesWithIntrinsicBounds(
                LoginEditText.this.getCompoundDrawables()[0],
                LoginEditText.this.getCompoundDrawables()[1],
                null,
                LoginEditText.this.getCompoundDrawables()[3]);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                showRightDrawable();
            } else {
                hideRightDrawable();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable rightDrawable = LoginEditText.this.getCompoundDrawables()[2];
                if (rightDrawable != null) {
                    float x = (LoginEditText.this.getRight()
                            - LoginEditText.this.getLeft()
                            - rightDrawable.getBounds().width());
                    if (event.getX() >= x) {
                        LoginEditText.this.setText("");
                    }
                }
            }
            return false;
        }
    };

    private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            String text = LoginEditText.this.getText().toString();

            if (hasFocus && !TextUtils.isEmpty(text)) {
                showRightDrawable();
            } else {
                hideRightDrawable();
            }
        }
    };

}
