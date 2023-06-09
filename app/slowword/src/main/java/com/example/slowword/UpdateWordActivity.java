package com.example.slowword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slowword.db.MyDatabase;
import com.example.slowword.db.WordDao;
import com.example.slowword.model.Word;

/**
 * 更改单词页面：点击RecyclerView的item跳转到该页面进行修改
 */
public class UpdateWordActivity extends AppCompatActivity {
    Button bt_update,bt_delete;
    EditText et_english, et_chinese;
    String english,chinese;
    int id;
    WordDao wordDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);
        initView();

        // 数据回显
        english = getIntent().getStringExtra("english");
        et_english.setText(english);
        chinese = getIntent().getStringExtra("chinese");
        et_chinese.setText(chinese);
        id = getIntent().getIntExtra("id",1);

        // 设置删除监听事件
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteWord(new Word(id,english,chinese));
                Toast.makeText(UpdateWordActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("flag","word");
                startActivity(intent);
            }
        });

        // 设置修改监听事件
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.updateWord(new Word(id,et_english.getText().toString(),et_chinese.getText().toString()));
                Toast.makeText(UpdateWordActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("flag","word");
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView(){
        et_english = findViewById(R.id.et_english);
        et_chinese = findViewById(R.id.et_chinese);
        bt_update = findViewById(R.id.bt_update);
        bt_delete = findViewById(R.id.bt_delete);
        wordDao = MyDatabase.getInstance(this).getWordDao();
    }
}