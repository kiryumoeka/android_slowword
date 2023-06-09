package com.example.rcentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button bt_login,bt_reg;
    EditText et_account,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_account = findViewById(R.id.et_account);
        EditText et_password = findViewById(R.id.et_password);

        Button bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if("123456".equals(et_account.getText().toString()) && "123456".equals(et_password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button bt_reg = findViewById(R.id.bt_reg);
        bt_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);

            }
        });


    }
}