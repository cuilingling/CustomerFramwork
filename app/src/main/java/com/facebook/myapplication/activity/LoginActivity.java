package com.facebook.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.myapplication.MainActivity;
import com.facebook.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserName;
    private EditText mPassWord;
    private TextInputLayout mTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextInputLayout = (TextInputLayout) findViewById(R.id.til_username);
        mUserName = (EditText) findViewById(R.id.et_username);
        mPassWord = (EditText) findViewById(R.id.et_pwd);
        // 通过TextInputLayout设置hint内容，也可以通过直接设置EditText的hint属性
//        mTextInputLayout.setHint("用户名");
        initEvent();
    }

    private void initEvent() {

    }

    // 确认按钮123123
    public void ok(View view) {
        // 方式一：通过TextInputLayout获取到里面的子控件EditText后在获取编辑的内容
        String username = mTextInputLayout.getEditText().getText().toString();
        if (TextUtils.isEmpty(username)) {
            mTextInputLayout.setError("账号为空!!!");
            final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {100, 400, 100, 400}; // 停止 开启 停止 开启
            vibrator.vibrate(pattern, 2);
            Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);

            mUserName.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    vibrator.cancel();
                    mTextInputLayout.setErrorEnabled(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            return;
        }
        // 方式二：直接通过EditText获取到里面的编辑内容
        String pwd = mPassWord.getText().toString();
        Toast.makeText(this, "username = " + username + "\npwd = " + pwd, Toast.LENGTH_SHORT).show();
        // 显示错误信息

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
