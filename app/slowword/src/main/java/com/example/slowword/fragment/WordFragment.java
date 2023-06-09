package com.example.slowword.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.slowword.AddWordActivity;
import com.example.slowword.R;
import com.example.slowword.UpdateWordActivity;
import com.example.slowword.adapters.ListViewAdapter;
import com.example.slowword.adapters.RecyclerViewBaseAdapter;
import com.example.slowword.beans.Art;
import com.example.slowword.beans.WordBean;
import com.example.slowword.db.ArcticleDao;
import com.example.slowword.db.MyDatabase;
import com.example.slowword.db.UserDao;
import com.example.slowword.db.WordDao;
import com.example.slowword.model.Arcticle;
import com.example.slowword.model.User;
import com.example.slowword.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 用来显示单词用
 */
public class WordFragment extends Fragment {
    View root;
    private RecyclerView mList;
    private ArrayList<WordBean> mData;
    private RecyclerViewBaseAdapter mAdapter;
    SwipeRefreshLayout refreshLayout;
    WordDao wordDao;
    public WordFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_word, container, false);
        }
        // 找到RecyclerView控件
        mList = root.findViewById(R.id.recycler_view);

        // 初始化数据
        getDataBaseData();
        // 展示默认主页
        showlist(true,false);

        // 处理下拉刷新
        refreshLayout = root.findViewById(R.id.refresh_layout);
        handlerDownPullUpdate();

        return root;
    }

    // 查询所有单词
    private void getDataBaseData(){
        wordDao = MyDatabase.getInstance(getContext()).getWordDao();
        List<Word> allWord =  wordDao.getAllWord();
        mData = new ArrayList<>();
        for (Word word : allWord) {
            mData.add(new WordBean(word.getEnglish(),word.getChinese(),word.getId()));
        }
    }

    /**
     * 显示数据
     */
    private void showlist(boolean isVertical, boolean isReverse){
        // RecyclerView需要设置样式，其实就是布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        // 设置水平或者垂直
        linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        // 是否反向
        linearLayoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(linearLayoutManager);
        // 创建适配器
        mAdapter = new ListViewAdapter(mData);
        // 设置到RecyclerView里头
        mList.setAdapter(mAdapter);
        // 初始化监听事件
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                // 点击条目跳转到修改页面
                Intent intent = new Intent(getActivity(), UpdateWordActivity.class);
                intent.putExtra("english",mData.get(position).english);
                intent.putExtra("chinese",mData.get(position).chinese);
                intent.putExtra("id",mData.get(position).id);
                startActivity(intent);
            }
        });

        // 在底部往上拉
        if(mAdapter instanceof ListViewAdapter){
            ((ListViewAdapter)mAdapter).setOnRefreshListener(new ListViewAdapter.OnRefreshListener() {
                @Override
                public void OnUpPullRefresh(ListViewAdapter.LoaderMoreHolder loaderMoreHolder) {
                    // 更新UI
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 随机数、测试加载失败
                            Random random = new Random();
                            if(random.nextInt() % 2 == 0){
                                // 这里面去加载更多数据
                                // 在这里面执行刷新数据的操作
                                WordBean data = new WordBean("abandon","放弃");
                                mData.add(data);
                                // 1.更新列表
                                mAdapter.notifyDataSetChanged();
                                // 2.让刷新停止
                                loaderMoreHolder.update(loaderMoreHolder.LOADER_STATE_NORMAL);
                            } else {
                                loaderMoreHolder.update(loaderMoreHolder.LOADER_STATE_RELOAD);
                            }

                        }
                    },1000);
                }
            });
        }
    }

    /**
     * 顶部上下拉
     */
    private void handlerDownPullUpdate() {
        refreshLayout.setEnabled(true); // 使之可用，一个固定的废话
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override  // 在顶部下拉时，该方法会被触发，主线程不可以执行耗时操作，因此要开新线程获取下拉数据
            public void onRefresh() {
                // 在这里面执行刷新数据的操作
                WordBean data = new WordBean("prohibit","禁止");
                mData.add(0,data);
                // 更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 1.更新列表
                        mAdapter.notifyDataSetChanged();
                        // 2.让刷新停止
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    /**
     * 这个方法用于模拟数据：一般数据从数据库或者网络获取
     */
    private void initData() {

        // 创建模拟数据
        mData = new ArrayList<>();
        mData.add(new WordBean("asylum","n.政治庇护、精神病院"));
        mData.add(new WordBean("periphrastic","adj.迂回的、冗长的、委婉的"));
        mData.add(new WordBean("hypothetical","adj.假设的"));
        mData.add(new WordBean("accord","n.协议、条约;v.给予、符合、与...一致"));
        mData.add(new WordBean("informed","adj.有学问的;"));
        mData.add(new WordBean("transfuse","vt.输送、渗透"));
        mData.add(new WordBean("detritus","n.岩屑、碎石、瓦砾、风化物"));
        mData.add(new WordBean("schism","n.教会分立、分裂"));
        mData.add(new WordBean("trowel","n.泥刀、小铲子"));
        mData.add(new WordBean("conviction","n.判罪、坚信、坚定的信念"));

    }
}