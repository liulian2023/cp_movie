package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.uyt.ying.yuan.R;


public class MyCornerTextView extends androidx.appcompat.widget.AppCompatTextView  {
    private int mBorderWidth = 1;
    private int mBorderWidthColor = Color.YELLOW;
    private int mCornersize = 8; //我们默认以px为单位
    private Paint mCornerPaint; //边框画笔   文字我们只用系统的 TextView
//    private int textColor;
//    private int backGround;


    @Override
    public boolean removeCallbacks(Runnable action) {
        return super.removeCallbacks(action);
    }

    public MyCornerTextView(Context context) {
        this(context,null);
    }

    public MyCornerTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCornerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context,attrs);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCornerTextView);
        mBorderWidth = (int) array.getDimension(R.styleable.MyCornerTextView_borderWid,mBorderWidth);
        mBorderWidthColor = array.getColor(R.styleable.MyCornerTextView_borderWidthColor,mBorderWidthColor);
        mCornersize = (int) array.getDimension(R.styleable.MyCornerTextView_cornersize,mCornersize);
//        textColor=array.getColor(R.styleable.MyCornerTextView_titleTextColor,textColor);
//        backGround=array.getColor(R.styleable.MyCornerTextView_mbackground,backGround);
        array.recycle();

        mCornerPaint =  new Paint();
        mCornerPaint.setAntiAlias(true);
        mCornerPaint.setDither(true);
        mCornerPaint.setStrokeWidth(mBorderWidth);
        mCornerPaint.setStyle(Paint.Style.FILL_AND_STROKE); //实心 只画边框也画心
        mCornerPaint.setColor(mBorderWidthColor);



    }

    //这里我们不去测量
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }


    @Override
    protected void onDraw(Canvas canvas) {


        RectF rectF = new RectF(mBorderWidth/2,mBorderWidth/2,getMeasuredWidth()-mBorderWidth,getMeasuredHeight()-mBorderWidth);
        canvas.drawRoundRect(rectF,mCornersize,mCornersize,mCornerPaint);


        super.onDraw(canvas);
    }

    /**
     * 设置 corner 边角
     * @param size
     */
    public void setCornerSize(int size) { //设置的单位默认是px

        mCornersize  =size;
//        invalidate();

    }
    //设置颜色

    public MyCornerTextView setfilColor (int color) {

        this.mBorderWidthColor = color;
        mCornerPaint.setColor(mBorderWidthColor);
        return this;
//        invalidate();
    }
}
