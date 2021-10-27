
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.uyt.ying.yuan.R;

public class Head1CenterPopupWindow extends PopupWindow {

    public View  mView;
    public Context mContext;

    public Head1CenterPopupWindow (Activity context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.popupwindow_head1center, null);

        int height = context.getWindowManager().getDefaultDisplay().getHeight();
        int width = context.getWindowManager().getDefaultDisplay().getWidth();






    }
}
