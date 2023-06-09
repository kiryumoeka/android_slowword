package com.example.rcentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {
    TextView reg_tv_account,reg_tv_password,reg_tv_phone,reg_tv_yzm;
    EditText reg_et_account,reg_et_password,reg_et_phone,reg_et_yzm;
    Button reg_bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
       /*
        reg_tv_account = findViewById(R.id.reg_tv_account);
        reg_tv_password = findViewById(R.id.reg_tv_password);
        reg_tv_phone = findViewById(R.id.reg_tv_phone);
        reg_tv_yzm = findViewById(R.id.reg_tv_yzm);
        reg_et_account = findViewById(R.id.reg_et_account);
        reg_et_password = findViewById(R.id.reg_et_password);
        reg_et_phone = findViewById(R.id.reg_et_phone);
       */
        reg_bt_submit = findViewById(R.id.reg_bt_submit);
        reg_et_yzm = findViewById(R.id.reg_et_yzm);

        reg_bt_submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if("1234".equals(reg_et_yzm.getText().toString())){
                    Toast.makeText(RegistActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistActivity.this,"验证码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}