package com.example.slowword;



import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.slowword.db.MyDatabase;
import com.example.slowword.db.WordDao;
import com.example.slowword.model.Word;


public class AddWordActivity extends AppCompatActivity {
    EditText et_english,et_chinese;
    Button bt_add;
    WordDao wordDao;
    public AddWordActivity() {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        // 初始化控件
        initView();

        // 添加单词到数据库
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWord();
                Toast.makeText(AddWordActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }
    /**
     * 初始化控件
     */
    private void initView(){
        et_english = findViewById(R.id.et_english);
        et_chinese = findViewById(R.id.et_chinese);
        bt_add = findViewById(R.id.bt_add);
        wordDao = MyDatabase.getInstance(this).getWordDao();
    }


    /**
     * 添加单词
     */
    private void setWord(){
        wordDao.insertWord(new Word(et_english.getText().toString(),et_chinese.getText().toString()));
    }
}