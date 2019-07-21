package com.nszx.bbs.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nszx.bbs.R;
import com.nszx.bbs.Utils.Tool;
import com.nszx.bbs.bean.UserBean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {
    private Tool t = new Tool();
    private TimeCount time;
    private Button get_pass;//获取验证码
    private EditText user_email;
    private EditText user_pass;
    private EditText passAgain;
    private Button register;//注册

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        user_email = findViewById(R.id.ed_name);//邮箱
        user_pass = findViewById(R.id.ed_pass);//密码
        passAgain = findViewById(R.id.ed_pass_again);//重复密码
        register = findViewById(R.id.login);//注册按钮
        get_pass = findViewById(R.id.button_email_pass);//获取验证码
        time = new TimeCount(60000, 1000);
        get_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_pass.getText().toString().equals("") != false) {
                    emailVerify(user_email.getText().toString());
                } else {
                    Toasty.error(RegisterActivity.this, "邮箱地址不能为空").show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user_email.getText().toString();
                String pass = user_pass.getText().toString();
                String pass_again = passAgain.getText().toString();
                Log.d("user", name + "     " + pass);

                if (pass.equals(pass_again)) {

                    UserBean userBean = new UserBean();
                    userBean.setUsername(name);
                    userBean.setPassword(pass);
                    userBean.setToken(t.token());
                    userBean.setImei(t.imei(RegisterActivity.this));
                    Log.d("token test", t.token());
                    userBean.signUp(new SaveListener<UserBean>() {
                        @Override
                        public void done(UserBean userBean, BmobException e) {
                            if (e == null) {
                                Toasty.success(RegisterActivity.this, "注册成功").show();
                            } else {
                                Toasty.success(RegisterActivity.this, "注册失败\n原因：" + e.getMessage()).show();
                                Log.d("Failed", e.getMessage());
                            }
                        }
                    });

                } else {
                    Toasty.error(RegisterActivity.this, "注册失败\n原因：两次密码不一致").show();
                }
            }
        });
    }

    private void emailVerify(final String email) {
        BmobUser.requestEmailVerify(email, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    time.start();
                    Toasty.success(RegisterActivity.this, "请求验证邮件成功，请到" + email + "邮箱中进行激活账户。").show();
                } else {
                    Log.e("BMOB", e.getMessage());
                    if (e.getMessage().equals("email Must be a valid email address")) {
                        Toasty.error(RegisterActivity.this, "请输入正确的邮箱地址").show();
                    }

                }
            }
        });
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            get_pass.setBackgroundColor(Color.parseColor("#B6B6D8"));
            get_pass.setClickable(false);
            get_pass.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            get_pass.setText("重新获取验证码");
            get_pass.setClickable(true);
            get_pass.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}

