package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MyClickableSpan extends ClickableSpan {
    private String str;

    private Context context;
    private int position;

    public MyClickableSpan(String str, Context context,int position) {
        this.str = str;
        this.context = context;
        this.position = position;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        //ds.setColor(Color.BULE)   通过这里设置出来的颜色有差别

    }

    @Override
    public void onClick(@NonNull View view) {
        //这里的判断是为了去掉在点击后字体出现的背景色
        if(view  instanceof  TextView){
            ((TextView)view).setHighlightColor(Color.TRANSPARENT);
        }
        //在这里写下你想要的点击效果
//        context.startActivity(newIntent(context,AgreementActivity.class));
        if(onClickSpanLintener!=null){
            onClickSpanLintener.onClickSpanLintener(view,position);
        }
        }

        public interface OnClickSpanLintener{
        void onClickSpanLintener(View view,int position);
        }
        public OnClickSpanLintener onClickSpanLintener;

    public void setOnClickSpanLintener(OnClickSpanLintener onClickSpanLintener) {
        this.onClickSpanLintener = onClickSpanLintener;
    }
}
