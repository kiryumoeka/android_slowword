package com.example.slowword.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slowword.R;
import com.example.slowword.beans.ArticleBean;



import java.util.List;

/**
 * 阅读模块的RecyclerView
 */
public abstract class ArcticleViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected final List<ArticleBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public ArcticleViewBaseAdapter(List<ArticleBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new ArcticleViewBaseAdapter.InnerHolder(view);
    }

    protected abstract View getSubView(View parent,int viewType);


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((InnerHolder)holder).setData(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(ArcticleViewBaseAdapter.OnItemClickListener listener) {
        // 设置一个监听
        this.mOnItemClickListener = listener;
    }

    /**
     * 编写回调的步骤
     * 1. 创建这个接口
     * 2. 定义接口内部的方法
     * 3. 提供设置接口的方法
     * 4. 接口方法的调用
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mDeatil;
        private ImageView mIcon;
        private int mposition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目控件
            mTitle = itemView.findViewById(R.id.arcticle_itemview_title); // 在这里设置listview条目布局
            mDeatil = itemView.findViewById(R.id.arcticle_itemview_detail);
            mIcon = itemView.findViewById(R.id.arcticle_itemview_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(mposition);
                    }
                }
            });
        }

        /**
         * 这个方法用于设置数据
         * @param itemBean
         */
        public void setData(ArticleBean itemBean,int position) {
            this.mposition = position;
            mTitle.setText(itemBean.title);
            mDeatil.setText(itemBean.detail);
            mIcon.setImageResource(itemBean.id);
        }
    }

}
