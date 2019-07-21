package com.nszx.bbs.ui;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nszx.bbs.R;

import cn.bmob.v3.BmobUser;


public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mDoLogin;
    private FloatingActionButton mGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        initView();
        setListener();
    }

    private void initView() {
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        mDoLogin = findViewById(R.id.bt_go);
        mGoRegister = findViewById(R.id.fab);
    }

    private void setListener() {
        mDoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

            }
        });
        mGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
