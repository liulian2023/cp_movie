package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.GiftNumAdapter;

import java.util.ArrayList;
import java.util.List;

public class GiftNumPop  extends PopupWindow {

    private Activity mActivity;
    private Fragment mContext;
    private final View view;
    RecyclerView recy_giftnum;

    GiftNumAdapter giftNumAdapter;
    private List<String> dataList = new ArrayList<>();

    public GiftNumPop(Activity mActivity, Fragment mContext) {
        super(mActivity);
        this.mActivity = mActivity;
        this.mContext = mContext;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_giftnum, null);
        initView();
        initPopWindow();
    }

    private void initView(){
        recy_giftnum = view.findViewById(R.id.recy_giftnum);

        for (int i =0;i<5;i++){
            dataList.add(""+i);
        }

//        giftNumAdapter = new GiftNumAdapter(R.layout.item_giftnum,dataList);
//        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(mActivity);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recy_giftnum.setLayoutManager(linearLayoutManager);
//        recy_giftnum.setHasFixedSize(true);
//        recy_giftnum.setAdapter(giftNumAdapter);

    }

    private void initPopWindow(){
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popAlphaanim0_5);
    }


}
