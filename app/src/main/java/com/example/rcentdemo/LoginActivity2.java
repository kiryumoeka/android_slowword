package com.example.rcentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    Button btLogin,btRegist; // 登录和注册按钮
    EditText loginUser,loginPsw; //账号和密码输入

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        loginUser = findViewById(R.id.loginUser);
        loginPsw = findViewById(R.id.loginPsw);
        Button btLogin = findViewById(R.id.btLogin);

        // 设置登录按钮事件
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                login();
            }
        });

        // 点击注册按钮跳转到注册页面
        Button btRegist = findViewById(R.id.btRegist);
        btRegist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity2.this,RegistActivity2.class);
                startActivity(intent);
            }
        });
    }

    // 登录业务
    public void login(){
        if("LinPeach".equals(loginUser.getText().toString()) && "123".equals(loginPsw.getText().toString())){
            Toast.makeText(LoginActivity2.this,"正在加载Genshin世界...",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity2.this,"想想你的账号密码是否正确？",Toast.LENGTH_SHORT).show();
        }
    }
}