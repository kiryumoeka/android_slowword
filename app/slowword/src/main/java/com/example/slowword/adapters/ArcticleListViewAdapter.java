package com.example.slowword.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slowword.R;
import com.example.slowword.beans.ArticleBean;


import java.util.List;

public class ArcticleListViewAdapter extends ArcticleViewBaseAdapter{
    public static final int TYPE_NORMAL = 0;  // 普通条目类型
    public static final int TYPE_LOADER_MORE = 1;  // 加载更多
    private OnRefreshListener mUpPullRefreshListener;

    public ArcticleListViewAdapter(List<ArticleBean> mData) {
        super(mData);
    }


    @Override
    protected View getSubView(View parent, int viewType) {
        View view;
        // 根据类型来创建view
        if(viewType == TYPE_NORMAL){
            view = View.inflate(parent.getContext(), R.layout.item_arcticle_list_view,null);
        } else {
            // 加载更多
            view = View.inflate(parent.getContext(),R.layout.item_list_loader_more,null);
        }
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL && holder instanceof InnerHolder){
            ((InnerHolder)holder).setData(mData.get(position),position);
            Log.d("MainAct", "onBindViewHolder: "+position + "  ItemCount："+getItemCount() + "mDataSize()：" + mData.size());
        } else if(getItemViewType(position) == TYPE_LOADER_MORE && holder instanceof LoaderMoreHolder){
            ((LoaderMoreHolder)holder).update(LoaderMoreHolder.LOADER_STATE_LOADING);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);;
        if (viewType == TYPE_NORMAL){
            return new InnerHolder(view);
        }else {
            return new LoaderMoreHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position){
        if(position == getItemCount() - 1){
            // 最后一个返回则加载更多
            return TYPE_LOADER_MORE;
        } else {
            return TYPE_NORMAL;
        }
    }

    /**
     * 设置刷新的监听的接口
     */
    public void setOnRefreshListener(OnRefreshListener listener){
        this.mUpPullRefreshListener = listener;
    }

    // 定义回调用接口
    public interface OnRefreshListener{
        void OnUpPullRefresh(LoaderMoreHolder loaderMoreHolder);
    }

    public class LoaderMoreHolder extends RecyclerView.ViewHolder{
        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout mLoading;
        private TextView mReload;

        public LoaderMoreHolder(View itemView){
            super(itemView);
            mLoading = (LinearLayout) itemView.findViewById(R.id.loading);
            mReload = (TextView) itemView.findViewById(R.id.reload);

            mReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(LOADER_STATE_LOADING);
                }
            });
        }

        public void update(int state){
            // 重置控件的状态
            mLoading.setVisibility(View.GONE);
            mReload.setVisibility(View.GONE);

            switch (state){
                case LOADER_STATE_LOADING:
                    mLoading.setVisibility(View.VISIBLE);
                    // 触发加载数据
                    startLoadMore();
                    break;
                case LOADER_STATE_RELOAD:
                    mReload.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_NORMAL:
                    mLoading.setVisibility(View.GONE);
                    mReload.setVisibility(View.GONE);
                    break;
            }

        }

        private void startLoadMore() {
            if(mUpPullRefreshListener != null){
                mUpPullRefreshListener.OnUpPullRefresh(this);
            }
        }

    }
}
