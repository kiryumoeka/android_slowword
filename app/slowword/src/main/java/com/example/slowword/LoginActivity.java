package com.example.slowword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slowword.beans.Art;
import com.example.slowword.db.ArcticleDao;
import com.example.slowword.db.MyDatabase;
import com.example.slowword.db.UserDao;
import com.example.slowword.db.WordDao;
import com.example.slowword.model.Arcticle;
import com.example.slowword.model.User;
import com.example.slowword.model.Word;

public class LoginActivity extends AppCompatActivity {
    Button bt_login,bt_reg;
    EditText et_account,et_password;
    UserDao userDao;
    WordDao wordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        userDao = MyDatabase.getInstance(this).getUserDao();
        wordDao = MyDatabase.getInstance(this).getWordDao();
        EditText et_account = findViewById(R.id.et_account);
        EditText et_password = findViewById(R.id.et_password);
        //initDataBaseData(); //第一次没数据时调用
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                User user = userDao.getUser(et_account.getText().toString(), et_password.getText().toString());
                if(user != null){
                    Toast.makeText(LoginActivity.this,"欢迎"+ et_account.getText().toString() +"！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.putExtra("username",user.getUsername());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_reg = findViewById(R.id.bt_reg);
        bt_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 创建数据库并且插入数据
     */
    private void initDataBaseData(){
        wordDao.insertWord(new Word("asylum","n.政治庇护、精神病院"),
                new Word("periphrastic","adj.迂回的、冗长的、委婉的"),
                new Word("hypothetical","adj.假设的"),
 //               new Word("accord","n.协议、条约;v.给予、符合、与...一致"),
   //             new Word("informed","adj.有学问的;"),
                new Word("transfuse","vt.输送、渗透"),
                new Word("detritus","n.岩屑、碎石、瓦砾、风化物"),
                new Word("schism","n.教会分立、分裂"),
                new Word("trowel","n.泥刀、小铲子"),
   //             new Word("conviction","n.判罪、坚信、坚定的信念"),
    //            new Word("chortle","v.哈哈大笑"),
                new Word("annihilate","vt.歼灭;vi.湮灭")
   //             new Word("headway","n.前进、进展")
        );
        userDao = MyDatabase.getInstance(this).getUserDao();
        userDao.insertUser(new User("admin","123"));

        ArcticleDao arcticleDao = MyDatabase.getInstance(this).getArcticleDao();
        arcticleDao.insertArcticle(new Arcticle(R.drawable.art1,"忘怀深空", Art.art1),
                new Arcticle(R.drawable.art2,"人类之蜜糖、动物之砒霜", Art.art2),
                new Arcticle(R.drawable.art3,"保护着海岸和生命的消失的森林", Art.art3),
                new Arcticle(R.drawable.art4,"小黄鸟与大老板", Art.art4),
                new Arcticle(R.drawable.art5,"读者文摘:搞笑趣事两则", Art.art5),
                new Arcticle(R.drawable.art6,"黑洞形成的临界点", Art.art6),
                new Arcticle(R.drawable.art7,"环球之旅", Art.art7),
                new Arcticle(R.drawable.art8,"城市化与未来都市", Art.art8));
    }
}