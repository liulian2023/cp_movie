/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class KJTextView extends TextView{

    Context mContext;
    public KJTextView(Context context) {
        super(context);
    }

    public KJTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KJTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}