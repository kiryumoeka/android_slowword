package com.example.slowword.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ShaderCircleView extends AppCompatImageView {
    Drawable drawable;
    private Paint mPaint;
    private Bitmap mBitmap;

    public ShaderCircleView(@NonNull Context context) {
        this(context,null);
    }

    public ShaderCircleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawable = getDrawable();
        if(drawable instanceof BitmapDrawable){
            mBitmap =  ((BitmapDrawable) drawable).getBitmap();
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if(mBitmap != null){
            drawCircle(canvas);
        }else{
            super.onDraw(canvas);
        }

    }

    private void drawCircle(Canvas canvas) {

        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        float scaleW = viewWidth / (float)bitmapWidth;
        float scaleH = viewHeight / (float)bitmapHeight;
        Matrix mMatrix = new Matrix();
        mMatrix.setScale(scaleW,scaleH);
        mPaint.setShader(bitmapShader);

        canvas.drawCircle(getWidth()/2f,
                getHeight()/2f,
                Math.min(getWidth()/2f, getHeight()/2f),
                mPaint);

        drawBorder(canvas);
    }

    private void drawBorder(Canvas canvas){
        mPaint.reset();
        mPaint.setAntiAlias(true);
        // 设置空心圆风格
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔宽度
        mPaint.setStrokeWidth(10);
        // 设置画笔颜色
        mPaint.setColor(Color.BLUE);

        // 画边框
        canvas.drawCircle(getWidth() / 2f, getHeight() /2f,
                Math.min(getWidth()/2f, getHeight()/2f) - 10 / 2f,
                mPaint);

    }
}
