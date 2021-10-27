/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;


public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mtv;
    private Context mContext;
    private int mTabPosition = -1;

    public BottomBarTab(Context context, @DrawableRes int icon,String tv) {
        this(context, null, icon,tv);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon,String tv) {
        this(context, attrs, 0, icon,tv);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon,String tv) {
        super(context, attrs, defStyleAttr);
        init(context, icon,tv);
    }

    private void init(Context context, int icon,String tv) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.TOP|Gravity.CENTER_HORIZONTAL;
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        addView(mIcon);

        mtv = new TextView(context);
        int tv_size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, getResources().getDisplayMetrics());
        LayoutParams tv_params =  new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_params.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
        mtv.setText(tv);
        mtv.setTextSize(tv_size);
        mtv.setLayoutParams(tv_params);
        mtv.setTextColor(ContextCompat.getColor(context, R.color.tab_unselect));
        addView(mtv);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
            mtv.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            mtv.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
