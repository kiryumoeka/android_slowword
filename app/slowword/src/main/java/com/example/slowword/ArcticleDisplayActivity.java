package com.example.slowword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 文章详情页：点击文章的RecyclerView跳转到该Activity展示文章详情
 */
public class ArcticleDisplayActivity extends AppCompatActivity {

    ImageView arc_icon;
    TextView arc_title,arc_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcticle_display);

        initView();


        arc_icon.setImageResource(getIntent().getIntExtra("arc_icon",0));
        arc_title.setText(getIntent().getStringExtra("arc_title"));
        arc_detail.setText(getIntent().getStringExtra("arc_detail"));
        arc_detail.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    public void initView(){
        arc_icon = findViewById(R.id.arc_icon);
        arc_title = findViewById(R.id.arc_title);
        arc_detail = findViewById(R.id.arc_detail);
    }

}