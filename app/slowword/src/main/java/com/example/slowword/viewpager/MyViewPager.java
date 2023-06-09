package com.example.slowword.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.slowword.fragment.MainFragment;

public class MyViewPager extends ViewPager {
    OnViewPagerTouchListener mTouchListener = null;
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mTouchListener != null){
                    mTouchListener.onPageTouch(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                if(mTouchListener != null){
                    mTouchListener.onPageTouch(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnViewPagerTouchListener(OnViewPagerTouchListener listener){
        this.mTouchListener = listener;
    }


    public interface OnViewPagerTouchListener{
        void onPageTouch(boolean isTouch);
    }
}
