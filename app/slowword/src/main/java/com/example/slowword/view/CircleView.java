package com.example.slowword.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.slowword.R;

public class CircleView extends androidx.appcompat.widget.AppCompatImageView{

    private Paint mPaint;
    Bitmap mBitmap;
    Bitmap bitmapMask,mScaledBitmap;
    private float borderWidth = 10;
    private int borderColor = Color.WHITE;


    public CircleView(@NonNull Context context) {
        this(context,null);
    }

    public CircleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        borderWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleView_borderWidth,10);
        borderColor = typedArray.getDimensionPixelOffset(R.styleable.CircleView_borderColor,Color.WHITE);
        typedArray.recycle();
        init();
    }


    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Drawable drawable = getDrawable();
        if(drawable instanceof BitmapDrawable){
             mBitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        // 绘制源图像Src
        createMask();
    }

    /**
     * src图缩放
     */
    private void scaleBitmap(){
        if(mScaledBitmap == null && mBitmap != null){
            int bitmapWidth = mBitmap.getWidth();
            int bitmapHeight = mBitmap.getHeight();

            int viewWidth = getWidth();
            int viewHeight = getHeight();

            float scaleW = viewWidth / (float)bitmapWidth;
            float scaleH = viewHeight / (float)bitmapHeight;
            // 缩放矩阵
            Matrix mMatrix = new Matrix();
            mMatrix.reset();
            mMatrix.setScale(scaleW,scaleH);
            mScaledBitmap = Bitmap.createBitmap(mBitmap,0,0,bitmapWidth,bitmapHeight,mMatrix,true);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null){
            drawCircleView(canvas);
        }else{
            super.onDraw(canvas);
        }
    }

    private void drawCircleView(Canvas canvas){
        int saveLayer = canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        // 绘制目标图像
        scaleBitmap();
        canvas.drawBitmap(mScaledBitmap,0,0,mPaint);
        // 设置XFermode DstIn
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 绘制源图像Src 遮罩
        canvas.drawBitmap(bitmapMask,0,0,mPaint);
        // 清空 XFermode
        mPaint.setXfermode(null);
        // 绘制边框
       // drawBorder(canvas);

        canvas.restoreToCount(saveLayer);
    }

    /**
     *  创建圆形遮罩
     */
    private void createMask() {
        // 如何创建一个图片
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        bitmapMask = Bitmap.createBitmap(getWidth(), getHeight(), config);

        // 把bitmap放到画布上
        Canvas canvas = new Canvas(bitmapMask);

        // 绘制圆形
        canvas.drawOval(0,0,getWidth(),getHeight(),mPaint);
    }

    private void drawBorder(Canvas canvas){
        // 设置空心圆风格
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔宽度
        mPaint.setStrokeWidth(borderWidth);
        // 设置画笔颜色
        mPaint.setColor(borderColor);

        // 画边框
        canvas.drawOval(borderWidth / 2f, borderWidth /2f,
                getWidth() - borderWidth / 2f,
                getHeight() - borderWidth,
                mPaint);

    }

}
