/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.DialogJJAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;

import java.util.List;

public class JiangjinDialog extends Dialog {
    Context mContext;
    private Button yes;//确定按钮
    RecyclerView dialog_jiangjin_recyclerview;
    List<NewplayModel.PlayRuleListFourBean> list;

    DialogJJAdapter dialogJJAdapter;


    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public JiangjinDialog(Context context, List<NewplayModel.PlayRuleListFourBean> list) {
        super(context,R.style.MyDialog);
        this.mContext = context;
        this.list = list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_jiangjin);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }


    /**
     * 设置确定按钮的显示内容和监听
     *
     *
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {
        this.yesOnclickListener = onYesOnclickListener;
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dialog_jiangjin_recyclerview.setLayoutManager(layoutManager);
        dialog_jiangjin_recyclerview.setFocusableInTouchMode(false);
        dialogJJAdapter = new DialogJJAdapter(mContext, list);
        dialog_jiangjin_recyclerview.setAdapter(dialogJJAdapter);
    }
    /**
     * 初始化界面控件
     */
    public void initView() {
        yes = findViewById(R.id.yes);
        dialog_jiangjin_recyclerview = findViewById(R.id.dialog_jiangjin_recyclerview);


    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }


}
