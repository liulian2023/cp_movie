/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

public class BetDialog extends Dialog {

    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView dialog_bet_groupname;
    private TextView dialog_bet_qishu;
    private TextView dialog_bet_danjia;
    private TextView dialog_bet_content;
    private TextView dialog_bet_totalnum;
    private TextView dialog_bet_totalmoney;

    String groupnameStr,qishuStr,danjiaStr,contentStr,totalnumStr,totalmoneyStr;


    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public BetDialog(Context context) {
        super(context,R.style.MyDialog);
    }

    public BetDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BetDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    /**
     * 设置取消按钮的显示内容和监听
     *
     *
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bet);
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
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        if (groupnameStr != null) {
            dialog_bet_groupname.setText(groupnameStr);
        }
        if (qishuStr != null) {
            dialog_bet_qishu.setText(qishuStr);
        }
        if (danjiaStr!=null){
            dialog_bet_danjia.setText(danjiaStr);
        }
        if (contentStr!=null){
            dialog_bet_content.setText(contentStr);
        }
        if (totalnumStr!=null){
            dialog_bet_totalnum.setText(totalnumStr);
        }
        if (totalmoneyStr!=null){
            dialog_bet_totalmoney.setText(totalmoneyStr);
        }

    }
    /**
     * 初始化界面控件
     */
    public void initView() {
        yes = (Button) findViewById(R.id.dialog_bet_yes);
        no = (Button) findViewById(R.id.dialog_bet_no);
        dialog_bet_groupname = (TextView) findViewById(R.id.dialog_bet_groupname);
        dialog_bet_qishu = (TextView) findViewById(R.id.dialog_bet_qishu);
        dialog_bet_danjia = (TextView) findViewById(R.id.dialog_bet_danjia);
        dialog_bet_content = (TextView) findViewById(R.id.dialog_bet_content);
        dialog_bet_totalnum = (TextView) findViewById(R.id.dialog_bet_totalnum);
        dialog_bet_totalmoney = (TextView) findViewById(R.id.dialog_bet_totalmoney);
    }



    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param
     */
    public void setGroupname(String groupnameStr) {
        this.groupnameStr = groupnameStr;
    }
    public void setQishu(String qishuStr) {
        this.qishuStr = qishuStr;
    }
    public void setDanjia(String danjiaStr) {
        this.danjiaStr = danjiaStr;
    }
    public void setContent(String contentStr) {
        this.contentStr = contentStr;
    }
    public void setTotalnum(String totalnumStr) {
        this.totalnumStr = totalnumStr;
    }
    public void setTotalmoney(String totalmoneyStr) {
        this.totalmoneyStr = totalmoneyStr;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }



}
