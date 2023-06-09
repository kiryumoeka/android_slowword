package com.example.slowword.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * 轮播图
 */
public class LooperPagerAdapter extends PagerAdapter {
    private List<Integer> mColors = null;
    private List<Integer> mPics = null;

    @Override
    public int getCount() {
        if(mPics != null){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    /**
     * 动态创建imageview
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        int realPosition = position % mPics.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(mPics.get(realPosition));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

   /*public void setData(List<Integer> colos){
        this.mColors = colos;
    }
    */
    public void setData(List<Integer> mPics){
        this.mPics = mPics;
    }

    public int getDataRealSize(){
        if(mPics != null){
            return mPics.size();
        }
        return 0;
    }

}
