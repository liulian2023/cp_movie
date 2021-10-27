package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.test;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StudyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StudyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        int start = s.indexOf("(");
        int end = s.indexOf(")");
        int start1 = s.indexOf("【");
        int end1 = s.indexOf("】");
        SpannableString spannableString = new SpannableString(s);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        if(start<0||end<0||start1<0||end1<0){
            return;
        }
        spannableString.setSpan(colorSpan,start,end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(colorSpan,start1,end1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

       TextView textView = baseViewHolder.getView(R.id.xuexi_tv);
       textView.setText(spannableString);

    }
}
