package com.example.slowword.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.slowword.AddWordActivity;
import com.example.slowword.ArcticleDisplayActivity;
import com.example.slowword.R;
import com.example.slowword.adapters.ArcticleListViewAdapter;
import com.example.slowword.adapters.ArcticleViewBaseAdapter;
import com.example.slowword.adapters.LooperPagerAdapter;
import com.example.slowword.beans.ArticleBean;
import com.example.slowword.db.ArcticleDao;
import com.example.slowword.db.MyDatabase;
import com.example.slowword.model.Arcticle;
import com.example.slowword.viewpager.MyViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 主页面，用来显示主要内容
 */
public class MainFragment extends Fragment implements MyViewPager.OnViewPagerTouchListener, ViewPager.OnPageChangeListener {
    View root;
    LinearLayout ll_addword;
    NavController navController;
    LinearLayout mPointContainer;

    private MyViewPager mLoopPager;
    private LooperPagerAdapter mLooperPagerAdapter;
    private static List<Integer> sColos = new ArrayList<>();
    private static List<Integer> sPics = new ArrayList<>();
    Handler mHandler;
    private boolean mIsTouch = false;

    static {
        sPics.add(R.drawable.lb1);
        sPics.add(R.drawable.art3);
        sPics.add(R.drawable.art4);
        sPics.add(R.drawable.art5);
        sPics.add(R.drawable.lb2);
    }

    private RecyclerView mList;
    private ArrayList<ArticleBean> mData;
    private ArcticleViewBaseAdapter mAdapter;
    SwipeRefreshLayout refreshLayout;
    ArcticleDao arcticleDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_main, container, false);
        }
        // 初始化控件
        initView();
        // 初始化viewpager数据
        if(mHandler == null){
            mHandler = new Handler();
        }
        mLooperPagerAdapter.notifyDataSetChanged();

        // 找到RecyclerView控件
        if(mList == null){
            mList = root.findViewById(R.id.recycler_view);
        }
        // 初始化数据
        getDataBaseData();

        // 展示默认主页
        showlist(true,false);

        mHandler.post(mLooperTask);
        return root;
    }

    private void viewpageData(){
       // Random random = new Random();
        // 准备数据
       // for(int i = 0; i < 5; i++){
        //    sColos.add(Color.argb(random.nextInt(255),random.nextInt(255),random.nextInt(255),random.nextInt(255)));
      //  }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(mLooperTask);
    }

    private Runnable mLooperTask = new Runnable() {
        @Override
        public void run() {
            // 切换viewpager图片
            if(!mIsTouch){
                int currentItem = mLoopPager.getCurrentItem();
                mLoopPager.setCurrentItem(++currentItem,true);
            }
            mHandler.postDelayed(this,3000);
        }
    };


    private void initView(){
        if(ll_addword != null){
            return ;
        }
        ll_addword = root.findViewById(R.id.ll_addword);
        if(ll_addword != null){
            ll_addword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddWordActivity.class);
                    startActivity(intent);
                }
            });
        }
        mLoopPager = (MyViewPager)root.findViewById(R.id.looper_pager);
        // 设置适配器
        mLooperPagerAdapter = new LooperPagerAdapter();
        mLooperPagerAdapter.setData(sPics);
        mLoopPager.setAdapter(mLooperPagerAdapter);
        mLoopPager.setOnViewPagerTouchListener(this);
        mLoopPager.addOnPageChangeListener(this);

        //根据图片的个数加载点的个数
        mPointContainer = root.findViewById(R.id.points_container);
        insertPoint();
        mLoopPager.setCurrentItem( mLooperPagerAdapter.getDataRealSize() * 100,false);

    }

    private void insertPoint() {
        for (int i = 0; i < sPics.size(); i++){
            View point = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(24,24);
            layoutParams.leftMargin = 16;
            point.setBackgroundResource(R.drawable.shape_point_normal);
            point.setLayoutParams(layoutParams);
            mPointContainer.addView(point);
        }
    }


    // 查询所有文章
    private void getDataBaseData(){
        if(arcticleDao != null){
            return;
        }
        arcticleDao = MyDatabase.getInstance(getContext()).getArcticleDao();
        List<Arcticle> allArcticle = arcticleDao.getAllArcticle();
        mData = new ArrayList<>();
        for (Arcticle arcticle : allArcticle) {
            mData.add(new ArticleBean(arcticle.getIconId(), arcticle.getTitle(), arcticle.getDetail()));
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
        mAdapter = new ArcticleListViewAdapter(mData);
        // 设置到RecyclerView里头
        mList.setAdapter(mAdapter);
        // 初始化监听事件
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new ArcticleViewBaseAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                // 点击条目跳转到修改页面
                Intent intent = new Intent(getActivity(), ArcticleDisplayActivity.class);
                intent.putExtra("arc_icon",mData.get(position).id);
                intent.putExtra("arc_title",mData.get(position).title);
                intent.putExtra("arc_detail",mData.get(position).detail);
                startActivity(intent);
            }
        });

        // 在底部往上拉
        if(mAdapter instanceof ArcticleListViewAdapter){
            ((ArcticleListViewAdapter)mAdapter).setOnRefreshListener(new ArcticleListViewAdapter.OnRefreshListener() {
                @Override
                public void OnUpPullRefresh(ArcticleListViewAdapter.LoaderMoreHolder loaderMoreHolder) {
                    // 更新UI
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 随机数、测试加载失败
                            Random random = new Random();
                            if(random.nextInt() % 2 == 0){
                                // 这里面去加载更多数据
                                // 在这里面执行刷新数据的操作
                                ArticleBean data = new ArticleBean(R.drawable.login_bg,"abandon","放弃");
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

    @Override
    public void onPageTouch(boolean isTouch) {
        mIsTouch = isTouch;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int realPosition;
        if(mLooperPagerAdapter.getDataRealSize() != 0){
            realPosition = position % mLooperPagerAdapter.getDataRealSize();
        } else {
            realPosition = 0;
        }
        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            View point = mPointContainer.getChildAt(i);
            if(i != realPosition){
                // 设置白色
                point.setBackgroundResource(R.drawable.shape_point_normal);
            } else {
                point.setBackgroundResource(R.drawable.shape_point_selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}