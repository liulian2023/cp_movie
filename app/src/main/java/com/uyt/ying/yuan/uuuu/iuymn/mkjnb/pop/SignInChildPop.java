package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.SignInChildAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondChildEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

import java.util.ArrayList;

public class SignInChildPop extends BasePopupWindow2 {
    RecyclerView sign_in_child_recycler;
    SignInChildAdapter signInChildAdapter;
    ArrayList<DiamondChildEntity> diamondChildEntityArrayList = new ArrayList<>();
    public SignInChildPop(Context context, boolean focusable,  ArrayList<DiamondChildEntity> diamondChildEntityArrayList) {
        super(context, focusable);
        this. diamondChildEntityArrayList = diamondChildEntityArrayList;
        initRecycler();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground((Activity)mContext,0.6f);
            }
        });

    }

    private void initRecycler() {
        signInChildAdapter = new SignInChildAdapter(R.layout.sign_in_recycler_item_layout,diamondChildEntityArrayList);
        sign_in_child_recycler.setLayoutManager(new GridLayoutManager(mContext,4));
        sign_in_child_recycler.setAdapter(signInChildAdapter);
        signInChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.sign_in_child_pop_layout,null);
        sign_in_child_recycler = rootView.findViewById(R.id.sign_in_child_recycler);

    }
}
