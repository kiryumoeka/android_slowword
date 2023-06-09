package com.example.slowword.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slowword.R;


/**
 * 显示个人信息的页面
 */
public class MyFragment extends Fragment {
    View root;
    Intent intent;
    TextView me_tv_username;
    /**
     * 每次切换到fragment都会执行该方法
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_my, container, false);
        }

        if(intent == null){
            intent = getActivity().getIntent();
        }

        TextView userTV = root.findViewById(R.id.me_tv_username);
        userTV.setText(intent.getStringExtra("username"));

        return root;
    }
}