
/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChangLongBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.util.ArrayList;

public class ChangLongAdapter extends CommonAdapter<ChangLongAdapter.MyHolder, ChangLongBetModel> {
    private Context context;
    private ArrayList<ChangLongBetModel> selectorList=new ArrayList<>();

    public ChangLongAdapter(ArrayList<ChangLongBetModel> list,  Context context, ArrayList<ChangLongBetModel> selectorList) {
        super(list);
        this.context = context;
        this.selectorList = selectorList;
    }

    @Override
    public void handleViewHolder(final MyHolder commonHolder, final int position) {
        final ChangLongBetModel itemModel = getItemModel(position);
        if(itemModel.isXian()){
            commonHolder.xian_iv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.xian_iv.setVisibility(View.GONE);
        }
        //头像
        Glide.with(context)
                .load(itemModel.getImgUrl())
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(commonHolder.touxiangIv);
        //typaname
        commonHolder.typeNameTv.setText(itemModel.getTypaname());
        //期数
        commonHolder.qishuTv.setText(Utils.getString(R.string.第)+itemModel.getQishu()+Utils.getString(R.string.期));
        //倒计时
        String time = itemModel.getTime();//倒计时结束时间
//        long serviceTime = Long.parseLong(Utils.getFileData("time"));//服务器时间
        long nowTime = System.currentTimeMillis();//当前时间
//        long shijiancha = Long.parseLong(itemModel.getShijaincha());//服务器时间和当地时间差
        Long shijiancha = SharePreferencesUtil.getLong(context, "shijiancha", 0l);
        long currenTime = Long.parseLong(time) + shijiancha - nowTime ;
//                 long days = currenTime / (1000 * 60 * 60 * 24);
                long hours = (currenTime/* - days * (1000 * 60 * 60 * 24)*/) / (1000 * 60 * 60);
                long minutes = (currenTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (currenTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;

                commonHolder.hourTv.setText(hours+"");
                commonHolder.minuteTv.setText(minutes+"");
                commonHolder.secandTv.setText(seconds+"");
                if(hours==0||hours<10){
                    commonHolder.hourTv.setText("0"+hours); }
                else{
                    commonHolder.hourTv.setText(""+hours);
                        }
                        if(minutes==0||minutes<10){
                            commonHolder.minuteTv.setText("0"+minutes);
                        }else{
                            commonHolder.minuteTv.setText(""+minutes);
                        }
                        if(seconds==0||seconds<10){
                            commonHolder.secandTv.setText("0"+seconds);
                        }else{
                            commonHolder.secandTv.setText(""+seconds);
                        }
                        if(currenTime<=0){
                            commonHolder.currentLinear.setVisibility(View.GONE);
                            commonHolder.kaijiangIngTv.setVisibility(View.VISIBLE);
                            commonHolder.weizhiLinear.setVisibility(View.VISIBLE);
                            commonHolder.ruleLinear.setVisibility(View.GONE);
                            commonHolder.hourTv.setText("00");
                            commonHolder.minuteTv.setText("00");
                            commonHolder.secandTv.setText("00");
                            if(endListener!=null){
                                endListener.onEnd(itemModel.getType_id()+"",position);
                            }
                        }else {
                            commonHolder.currentLinear.setVisibility(View.VISIBLE);
                            commonHolder.kaijiangIngTv.setVisibility(View.GONE);
                            commonHolder.weizhiLinear.setVisibility(View.GONE);
                            commonHolder.ruleLinear.setVisibility(View.VISIBLE);
                        }

        //第几球
        commonHolder.playTypeTv.setText(itemModel.getPlayType());
        commonHolder.playTypeTv.setfilColor(Color.parseColor("#C6C6C6"));
        commonHolder.playTypeTv.setCornerSize(5);
        //大小
        commonHolder.daxiaoTv.setText(itemModel.getDaxiao());
        commonHolder.daxiaoTv.setfilColor(Color.parseColor("#DC3B40"));
        commonHolder.daxiaoTv.setCornerSize(5);
        //期数2
        commonHolder.qishu2Tv.setText(itemModel.getQishu2()+Utils.getString(R.string.期));
        commonHolder.qishu2Tv.setfilColor(Color.parseColor("#5691D7"));
        commonHolder.qishu2Tv.setCornerSize(5);
        //左侧的投注类型
        commonHolder.ruleOneTv.setText(itemModel.getBetTypeLeft());
        commonHolder.weizhiLeftRule.setText(itemModel.getBetTypeLeft());
        //右侧大的投注类型
        commonHolder.ruleTwoTv.setText(itemModel.getBetTypeRight());
        commonHolder.weizhiRightRule.setText(itemModel.getBetTypeRight());
        //左侧的赔率
        commonHolder.rebateOneTv.setText(Utils.getString(R.string.奖)+itemModel.getRebateLeft());
        commonHolder.weizhiLeftRebate.setText(Utils.getString(R.string.奖)+itemModel.getRebateLeft());
        //右侧的赔率
        commonHolder.rebateTwoTv.setText(Utils.getString(R.string.奖)+itemModel.getRebateRight());
        commonHolder.weizhiRightRebate.setText(Utils.getString(R.string.奖)+itemModel.getRebateRight());



        commonHolder.selectorOneLinear.setTag(position);
        commonHolder.selectorTwoLinear.setTag(position);
            commonHolder.selectorOneLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!commonHolder.selectorOneLinear.isSelected()){
                        for (int i = 0; i < list.size(); i++) {
                            ChangLongBetModel changLongBetModel = list.get(i);
                            if(i==position){
                                changLongBetModel.setStatusLeft(1);
                                changLongBetModel.setStatusRight(0);
                                changLongBetModel.setLeft(true);
                                selectorList.clear();
                                selectorList.add(changLongBetModel);
                            }else{
                                changLongBetModel.setStatusLeft(0);
                                changLongBetModel.setStatusRight(0);
                                changLongBetModel.setLeft(false);
                            }
                        }

                    }else {
                        itemModel.setStatusLeft(0);
                        selectorList.remove(itemModel);
                    }
                    notifyDataSetChanged();
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                    }
                }
            });
            commonHolder.selectorTwoLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!commonHolder.selectorTwoLinear.isSelected()){
                        for (int i = 0; i < list.size(); i++) {
                                ChangLongBetModel changLongBetModel = list.get(i);
                                if(position==i){
                                    changLongBetModel.setStatusRight(1);
                                    changLongBetModel.setStatusLeft(0);
                                    changLongBetModel.setLeft(false);
                                    selectorList.clear();
                                    selectorList.add(changLongBetModel);
                                }else{
                                    changLongBetModel.setStatusLeft(0);
                                    changLongBetModel.setStatusRight(0);
                                    changLongBetModel.setLeft(false);
                                }
                        }
                    }else {
                        itemModel.setStatusRight(0);
                        itemModel.setLeft(false);
                        selectorList.remove(itemModel);
                    }
                    notifyDataSetChanged();
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                    }
                }
            });
        if(itemModel.getStatusLeft()==1){
            commonHolder.selectorOneLinear.setSelected(true);
        }else {
            commonHolder.selectorOneLinear.setSelected(false);

        }
        if(itemModel.getStatusRight()==1){
            commonHolder.selectorTwoLinear.setSelected(true);
        }else{
            commonHolder.selectorTwoLinear.setSelected(false);

        }

    }

    @Override
    public int getLayOutRes() {
        return R.layout.changlong_recycle_item;
    }
    public static interface EndListener {
        void onEnd(String id,int index);
    }
    private EndListener endListener;
    public void setOnEndListener(EndListener endListener) {
        this.endListener = endListener;
    }
    public static class MyHolder extends CommonHolder {
        private ImageView touxiangIv;
        private TextView typeNameTv;
        private TextView qishuTv;
        private TextView hourTv;
        private TextView minuteTv;
        private TextView secandTv;
        private MyCornerTextView playTypeTv;
        private MyCornerTextView daxiaoTv;
        private MyCornerTextView qishu2Tv;
        private LinearLayout selectorOneLinear;
        private LinearLayout selectorTwoLinear;
        private TextView ruleOneTv;
        private TextView rebateOneTv;
        private TextView ruleTwoTv;
        private TextView rebateTwoTv;
        private LinearLayout currentLinear;
        private TextView kaijiangIngTv;
        private LinearLayout weizhiLinear;
        private LinearLayout ruleLinear;
        private TextView weizhiLeftRule;
        private TextView weizhiLeftRebate;
        private TextView weizhiRightRule;
        private TextView weizhiRightRebate;
        private ImageView xian_iv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            touxiangIv=itemView.findViewById(R.id.lottery_img);
            typeNameTv=itemView.findViewById(R.id.type_name);
            qishuTv=itemView.findViewById(R.id.qishu);
            hourTv=itemView.findViewById(R.id.hour);
            minuteTv=itemView.findViewById(R.id.minutes);
            secandTv=itemView.findViewById(R.id.seconds);
            playTypeTv=itemView.findViewById(R.id.play_type);
            daxiaoTv=itemView.findViewById(R.id.open_daxiao);
            qishu2Tv=itemView.findViewById(R.id.qishu2);
            selectorOneLinear=itemView.findViewById(R.id.changlong_one_selector);
            selectorTwoLinear=itemView.findViewById(R.id.changlong_two_selector);
            ruleOneTv=itemView.findViewById(R.id.bet_type_one);
            ruleTwoTv=itemView.findViewById(R.id.bet_type_two);
            rebateOneTv=itemView.findViewById(R.id.bet_rebate_one);
            rebateTwoTv=itemView.findViewById(R.id.bet_rebate_two);
            currentLinear=itemView.findViewById(R.id.current_linear);
            kaijiangIngTv=itemView.findViewById(R.id.kaijiangIng);
            weizhiLinear=itemView.findViewById(R.id.selector_weizhi);
            ruleLinear=itemView.findViewById(R.id.rule_linear);
            weizhiLeftRule=itemView.findViewById(R.id.weizhi_left_rule);
            weizhiLeftRebate=itemView.findViewById(R.id.weizhi_left_rebate);
            weizhiRightRule=itemView.findViewById(R.id.weizhi_right_rule);
            weizhiRightRebate=itemView.findViewById(R.id.weizhi_right_rebate);
            xian_iv=itemView.findViewById(R.id.xian_iv);
        }
    }
}
