/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.RaceActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RaceOfficialPAdapter extends RecyclerView.Adapter<RaceOfficialPAdapter.VH> {

    Context mContext;
    int[][] B;
    List<NewplayModel.PlayRuleListFourBean> list;
    NewplayModel.PlayRuleListThreeBean playRuleListThreeBean;
  //  HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;



    public RaceOfficialPAdapter(Context mContext,int[][] B, List<NewplayModel.PlayRuleListFourBean> list, NewplayModel.PlayRuleListThreeBean playRuleListThreeBean, List<IsClickModel> isClickList/*HashMap<String, Boolean> isClickMap*/) {
        this.mContext = mContext;
        this.B=B;
        this.list = list;
        this.playRuleListThreeBean = playRuleListThreeBean;
        this.isClickList = isClickList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.item_raceofficialp, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        NewplayModel.PlayRuleListFourBean bean = list.get(position);
        holder.item_raceofficialp_tv3.setTag(position);
        holder.item_raceofficialp_tv4.setTag(position);
        holder.item_raceofficialp_tv5.setTag(position);
        holder.item_raceofficialp_tv6.setTag(position);
        holder.item_raceofficialp_tv7.setTag(position);
        holder.item_raceofficialp_tv8.setTag(position);

        if (B[position][0]==1){  //tv3
            holder.item_raceofficialp_tv3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][1]==1){  //tv4
            holder.item_raceofficialp_tv4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv4.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv4.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][2]==1){  //tv5
            holder.item_raceofficialp_tv5.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv5.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv5.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv5.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][3]==1){  //tv6
            holder.item_raceofficialp_tv6.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv6.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv6.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv6.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][4]==1){  //tv7
            holder.item_raceofficialp_tv7.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv7.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv7.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv7.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][5]==1){  //tv3
            holder.item_raceofficialp_tv8.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv8.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialp_tv8.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv8.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }


        if (position!=0){
            holder.ll_item_raceofficialp_tv1.setVisibility(View.GONE);
        }else {
            holder.ll_item_raceofficialp_tv1.setVisibility(View.VISIBLE);
        }
        BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
        BigDecimal odds_setscale = odds.setScale(2,BigDecimal.ROUND_HALF_UP);
        String remark = playRuleListThreeBean.getRemark();
        remark = remark.substring(0,remark.indexOf(";"))+ Utils.getString(R.string.奖金)+odds_setscale+" "+Utils.getString(R.string.元);
        SpannableString spannableString = new SpannableString(remark);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), remark.indexOf(" ") + 1, remark.lastIndexOf(" "), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.item_raceofficialp_tv1.setText(spannableString);

        holder.item_raceofficialp_tv2.setText(bean.getName());
        holder.childList.clear();
        for (int i=1;i<11;i++){
            holder.childList.add(String.valueOf(bean.getId()*100+i));
        }

        if (holder.raceOfficialCAdapter == null){
            holder.raceOfficialCAdapter = new RaceOfficialCAdapter(mContext,B,list, holder.childList,isClickList);
            GridLayoutManager layoutManage = new GridLayoutManager(mContext, 5);
            holder.item_raceofficialp_recycler.setLayoutManager(layoutManage);
            holder.item_raceofficialp_recycler.setAdapter(holder.raceOfficialCAdapter);
        }else {
            holder.raceOfficialCAdapter.notifyDataSetChanged();
        }



    }

    @Override
    public int getItemCount() {
        if (list!=null&&list.size()>0){
            return list.size();
        }else {
            return 0;
        }
    }

    public  class VH extends RecyclerView.ViewHolder {
        private RaceOfficialCAdapter raceOfficialCAdapter;
        private List<String> childList = new ArrayList<>();
        @BindView(R.id.ll_item_raceofficialp_tv1)
        LinearLayout ll_item_raceofficialp_tv1;
        @BindView(R.id.item_raceofficialp_tv1)
        TextView item_raceofficialp_tv1;
        @BindView(R.id.item_raceofficialp_tv2)
        TextView item_raceofficialp_tv2;
        @BindView(R.id.item_raceofficialp_tv3)
        TextView item_raceofficialp_tv3;
        @BindView(R.id.item_raceofficialp_tv4)
        TextView item_raceofficialp_tv4;
        @BindView(R.id.item_raceofficialp_tv5)
        TextView item_raceofficialp_tv5;
        @BindView(R.id.item_raceofficialp_tv6)
        TextView item_raceofficialp_tv6;
        @BindView(R.id.item_raceofficialp_tv7)
        TextView item_raceofficialp_tv7;
        @BindView(R.id.item_raceofficialp_tv8)
        TextView item_raceofficialp_tv8;
        @BindView(R.id.item_raceofficialp_recycler)
        RecyclerView item_raceofficialp_recycler;

        @OnClick({R.id.item_raceofficialp_tv3,R.id.item_raceofficialp_tv4,R.id.item_raceofficialp_tv5,R.id.item_raceofficialp_tv6,R.id.item_raceofficialp_tv7,R.id.item_raceofficialp_tv8})
        public void tvClick(View view){
            switch (view.getId()){
                case R.id.item_raceofficialp_tv3:
                    int position3 = (int)view.getTag(); //TODO 2019-7-22 获取控件 position tag
                    B[position3][0]=1;
                    B[position3][1]=0;
                    B[position3][2]=0;
                    B[position3][3]=0;
                    B[position3][4]=0;
                    B[position3][5]=0;
               //     list.get(position3).getId()
                  //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(/*entry.getKey()*/isClickModel.getId())/100==list.get(position3).getId()) {
                           // isClickMap.replace(entry.getKey(), true);
                            StrUtil.isclickReplace(isClickList,isClickModel.getId(),true);
                        }
                    }
                //    raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
                case R.id.item_raceofficialp_tv4:
                    int position4 = (int)view.getTag();

                    B[position4][0]=0;
                    B[position4][1]=1;
                    B[position4][2]=0;
                    B[position4][3]=0;
                    B[position4][4]=0;
                    B[position4][5]=0;
                  //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(isClickModel.getId())/100==list.get(position4).getId()) {
                            if ((Integer.parseInt(isClickModel.getId())%100)>5){
                              //  isClickMap.replace(entry.getKey(), true);
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),true);
                            }else {
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),false);
                            }
                        }
                    }
                 //   raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
                case R.id.item_raceofficialp_tv5:

                    int position5 = (int)view.getTag();
                    B[position5][0]=0;
                    B[position5][1]=0;
                    B[position5][2]=1;
                    B[position5][3]=0;
                    B[position5][4]=0;
                    B[position5][5]=0;
                   // for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(isClickModel.getId())/100==list.get(position5).getId()) {
                            if ((Integer.parseInt(isClickModel.getId())%100)<6){
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),true);
                            }else {
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),false);
                            }
                        }
                    }
               //     raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
                case R.id.item_raceofficialp_tv6:
                    int position6 = (int)view.getTag();
                    B[position6][0]=0;
                    B[position6][1]=0;
                    B[position6][2]=0;
                    B[position6][3]=1;
                    B[position6][4]=0;
                    B[position6][5]=0;
                 //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(isClickModel.getId())/100==list.get(position6).getId()) {
                            if ((Integer.parseInt(isClickModel.getId())%2)==1){
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),true);
                            }else {
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),false);
                            }
                        }
                    }
                //    raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
                case R.id.item_raceofficialp_tv7:
                    int position7 = (int)view.getTag();
                    B[position7][0]=0;
                    B[position7][1]=0;
                    B[position7][2]=0;
                    B[position7][3]=0;
                    B[position7][4]=1;
                    B[position7][5]=0;
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(isClickModel.getId())/100==list.get(position7).getId()) {
                            if ((Integer.parseInt(isClickModel.getId())%2)==0){
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),true);
                            }else {
                                StrUtil.isclickReplace(isClickList,isClickModel.getId(),false);
                            }
                        }
                    }
               //     raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
                case R.id.item_raceofficialp_tv8:
                    int position8 = (int)view.getTag();
                    B[position8][0]=0;
                    B[position8][1]=0;
                    B[position8][2]=0;
                    B[position8][3]=0;
                    B[position8][4]=0;
                    B[position8][5]=0;
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (Integer.parseInt(isClickModel.getId())/100==list.get(position8).getId()) {
                            StrUtil.isclickReplace(isClickList,isClickModel.getId(),false);
                        }
                    }
                 //   raceOfficialCAdapter.notifyDataSetChanged();
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                    break;
            }
        }


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
