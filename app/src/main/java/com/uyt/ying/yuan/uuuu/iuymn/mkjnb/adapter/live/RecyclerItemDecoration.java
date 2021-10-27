package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private int itemSpaceLeft;
    private int itemSpaceCenter;
    private int itemNum;

    public RecyclerItemDecoration(int itemSpaceLeft, int itemSpaceCenter, int itemNum) {
        this.itemSpaceLeft = itemSpaceLeft;
        this.itemSpaceCenter = itemSpaceCenter;
        this.itemNum = itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (parent.getChildCount() > 0) {
            if (position % itemNum == 0) {                  //最左边Item
                outRect.left = itemSpaceLeft;
                outRect.right = itemSpaceCenter / 2;
            } else if (position % itemNum == itemNum - 1) { //最右边Item
                outRect.left = itemSpaceCenter / 2;
                outRect.right = itemSpaceLeft;
            } else {                                        //中间Item
                outRect.left = itemSpaceCenter / 2;
                outRect.right = itemSpaceCenter / 2;
            }
        }
    }

}