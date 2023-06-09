package com.example.rcentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistActivity2 extends AppCompatActivity {
    ImageView submit;  // 提交按钮
    CheckBox agreement; // 勾选同意协议
    EditText regUser,regPsw;  // 输入账号、密码
    ArrayList<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist2);

        agreement = findViewById(R.id.agreement);
        submit = findViewById(R.id.submit);
        regUser = findViewById(R.id.regUser);
        regPsw = findViewById(R.id.regPsw);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 注册
                if(agreement.isChecked()){
                    User user = new User();
                    user.username = regUser.getText().toString();
                    user.psw = regPsw.getText().toString();
                    regist(user);
                    Toast.makeText(RegistActivity2.this,"欢迎加入Genshin大家庭",Toast.LENGTH_SHORT).show();
               // 注册失败
                } else {
                    Toast.makeText(RegistActivity2.this,"请勾选协议",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void regist(User user){
        users.add(user);
    }

}

class User {
    String username;
    String psw;
}