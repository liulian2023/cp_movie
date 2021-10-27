package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

public class MyTextView extends View {
    private Paint roundRectPaint;
    private RectF roundRectF;
    private int height;
    private int roundWidth;
    private Paint textPaint;
    private float textSize;
    private String text;
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initView() {
        text = "";
        roundRectPaint = new Paint();
        roundRectPaint.setStyle(Paint.Style.FILL);
        roundRectPaint.setAntiAlias(true);
        roundRectPaint.setColor(Color.RED);
        roundRectF = new RectF();

        //设置字体为粗体
        textPaint = new Paint(Paint.LINEAR_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        //实现抗锯齿
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件高度
        height = MeasureSpec.getSize(heightMeasureSpec);
        //定义字体大小，与高度相差20的字体大小看着还是挺舒服的
        textSize = height-5;
        textPaint.setTextSize(textSize);
        //获取文本宽度
        int textWidth = (int) textPaint.measureText(text);
        //区分画圆的是圆形还是圆角矩形
        if (text.length() > 1) {
            roundWidth = textWidth + height - textWidth / text.length();
        } else {
            roundWidth = height;
        }
        //重新测量控件
        setMeasuredDimension(roundWidth, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画矩形
        canvas.setDrawFilter(paintFlagsDrawFilter);
        roundRectF.set(0, 0, roundWidth, height);
        canvas.drawRoundRect(roundRectF, height / 2, height / 2, roundRectPaint);

        //画字
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int baseLintY = (int) (roundRectF.centerY() - top / 2 - bottom / 2);
        canvas.drawText(text, roundRectF.centerX(), baseLintY, textPaint);
    }

    public void setText(String text) {
        this.text = text;
        //重走onMeasure
        requestLayout();
    }
}
