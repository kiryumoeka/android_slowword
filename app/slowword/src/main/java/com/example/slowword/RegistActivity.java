package com.example.slowword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slowword.db.MyDatabase;
import com.example.slowword.db.UserDao;
import com.example.slowword.model.User;

public class RegistActivity extends AppCompatActivity {
    TextView reg_tv_account,reg_tv_password,reg_tv_phone,reg_tv_yzm;
    EditText reg_et_account,reg_et_password,reg_et_phone,reg_et_yzm;
    Button reg_bt_submit;
    UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        userDao = MyDatabase.getInstance(this).getUserDao();

        reg_et_account = findViewById(R.id.reg_et_account);
        reg_et_password = findViewById(R.id.reg_et_password);
        reg_bt_submit = findViewById(R.id.reg_bt_submit);
        reg_et_yzm = findViewById(R.id.reg_et_yzm);

        reg_bt_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if("1234".equals(reg_et_yzm.getText().toString())){
                    userDao.insertUser(new User(reg_et_account.getText().toString(),reg_et_password.getText().toString()));
                    Toast.makeText(RegistActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegistActivity.this,"验证码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

       /*
        reg_tv_account = findViewById(R.id.reg_tv_account);
        reg_tv_password = findViewById(R.id.reg_tv_password);
        reg_tv_phone = findViewById(R.id.reg_tv_phone);
        reg_tv_yzm = findViewById(R.id.reg_tv_yzm);
        reg_et_account = findViewById(R.id.reg_et_account);
        reg_et_password = findViewById(R.id.reg_et_password);
        reg_et_phone = findViewById(R.id.reg_et_phone);
       */
       /* reg_bt_submit = findViewById(R.id.reg_bt_submit);
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
        });*/
}