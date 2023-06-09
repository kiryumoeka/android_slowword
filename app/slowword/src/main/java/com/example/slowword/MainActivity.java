package com.example.slowword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 4个fragment都依托该Activity
 */
public class MainActivity extends AppCompatActivity {

    Button bt_article, bt_main, bt_word, bt_me,bt_setpressed;
    NavController navController;
    Drawable background,dwpressed;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        background = bt_article.getBackground();
        dwpressed = bt_setpressed.getBackground();
        setListneer();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentApp);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);
        bt_main.setBackgroundResource(R.drawable.btn_pressed);
        toWhatFragment();
    }

    protected void toWhatFragment(){
        String flag = getIntent().getStringExtra("flag");
        if("word".equals(flag)){
            navController.navigate(R.id.wordFragment);
        }
    }

    private void initView() {
        bt_article = findViewById(R.id.bt_article);
        bt_main = findViewById(R.id.bt_main);
        bt_word = findViewById(R.id.bt_word);
        bt_me = findViewById(R.id.bt_me);
        bt_setpressed = findViewById(R.id.bt_setpressed);

    }

    private void setListneer() {
        bt_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_article.setBackgroundResource(R.drawable.btn_pressed);
                bt_main.setBackgroundResource(R.drawable.btn);
                bt_word.setBackgroundResource(R.drawable.btn);
                bt_me.setBackgroundResource(R.drawable.btn);
                navController.navigate(R.id.articleFragment);
            }
        });


        bt_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_main.setBackgroundResource(R.drawable.btn_pressed);
                bt_article.setBackgroundResource(R.drawable.btn);
                bt_word.setBackgroundResource(R.drawable.btn);
                bt_me.setBackgroundResource(R.drawable.btn);
                navController.navigate(R.id.mainFragment);
            }
        });


        bt_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_word.setBackgroundResource(R.drawable.btn_pressed);
                bt_main.setBackgroundResource(R.drawable.btn);
                bt_article.setBackgroundResource(R.drawable.btn);
                bt_me.setBackgroundResource(R.drawable.btn);
                navController.navigate(R.id.wordFragment);
            }
        });

        bt_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_me.setBackgroundResource(R.drawable.btn_pressed);
                bt_word.setBackgroundResource(R.drawable.btn);
                bt_main.setBackgroundResource(R.drawable.btn);
                bt_article.setBackgroundResource(R.drawable.btn);
                navController.navigate(R.id.myFragment);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.fragmentApp);
        return navController.navigateUp();
    }
}