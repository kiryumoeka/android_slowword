package com.example.slowword.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slowword.R;
import com.example.slowword.beans.WordBean;

import java.util.List;

/**
 * RecyclerView基类，可按需求实现线性、网格、瀑布流
 */
public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final List<WordBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBaseAdapter(List<WordBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new InnerHolder(view);
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

    public void setOnItemClickListener(OnItemClickListener listener) {
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
        private TextView mEnglish;
        private TextView mChinese;
        private int mposition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目控件
            mEnglish = itemView.findViewById(R.id.tv_english); // 在这里设置listview条目布局
            mChinese = itemView.findViewById(R.id.tv_chinese);

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
        public void setData(WordBean itemBean,int position) {
            this.mposition = position;
            mEnglish.setText(itemBean.english);
            mChinese.setText(itemBean.chinese);
        }
    }
}
