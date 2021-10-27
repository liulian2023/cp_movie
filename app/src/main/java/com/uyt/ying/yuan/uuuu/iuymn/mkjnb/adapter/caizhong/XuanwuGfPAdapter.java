/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
//import android.support.annotation.RequiresApi;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.JiangjinDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XuanwuGfPAdapter extends RecyclerView.Adapter<XuanwuGfPAdapter.VH> {

    List<NewplayModel.PlayRuleListFourBean> list;
    NewplayModel.PlayRuleListThreeBean playRuleListThreeBean;
    Context mContext;
    int[][] B;
    // HashMap<String, Boolean> isClickMap;
    List<IsClickModel> isClickList;
    int index_gf_x, index_gf_y;

    public XuanwuGfPAdapter(Context mContext, int[][] B, List<NewplayModel.PlayRuleListFourBean> list, NewplayModel.PlayRuleListThreeBean playRuleListThreeBean, List<IsClickModel> isClickList, int index_gf_x, int index_gf_y) {
        this.list = list;
        this.playRuleListThreeBean = playRuleListThreeBean;
        this.mContext = mContext;
        this.B = B;
        this.isClickList = isClickList;
        this.index_gf_x = index_gf_x;
        this.index_gf_y = index_gf_y;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_raceofficialp, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        NewplayModel.PlayRuleListFourBean bean = list.get(position);
        //TODO 2019-7-22  设置控件position tag
        holder.item_raceofficialp_tv3.setTag(position);
        holder.item_raceofficialp_tv4.setTag(position);
        holder.item_raceofficialp_tv5.setTag(position);
        holder.item_raceofficialp_tv6.setTag(position);
        holder.item_raceofficialp_tv7.setTag(position);
        holder.item_raceofficialp_tv8.setTag(position);

        if (B[position][0] == 1) {  //tv3
            holder.item_raceofficialp_tv3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][1] == 1) {  //tv4
            holder.item_raceofficialp_tv4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv4.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv4.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][2] == 1) {  //tv5
            holder.item_raceofficialp_tv5.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv5.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv5.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv5.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][3] == 1) {  //tv6
            holder.item_raceofficialp_tv6.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv6.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv6.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv6.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][4] == 1) {  //tv7
            holder.item_raceofficialp_tv7.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv7.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv7.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv7.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        if (B[position][5] == 1) {  //tv3
            holder.item_raceofficialp_tv8.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc_enable));
            holder.item_raceofficialp_tv8.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialp_tv8.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_retc));
            holder.item_raceofficialp_tv8.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }


        if (position != 0) {
            holder.ll_item_raceofficialp_tv1.setVisibility(View.GONE);
        } else {
            holder.ll_item_raceofficialp_tv1.setVisibility(View.VISIBLE);
        }
        BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
        BigDecimal odds_setscale = odds.setScale(2, BigDecimal.ROUND_HALF_UP);
        String remark = playRuleListThreeBean.getRemark();
        if (index_gf_x == 4 && index_gf_y == 1) {
            remark = remark + Utils.getString(R.string.逗号奖金详情);
        } else {
            remark = remark + Utils.getString(R.string.奖金逗号) + odds_setscale + " " + Utils.getString(R.string.元);
        }
        SpannableString spannableString = new SpannableString(remark);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), remark.indexOf(" ") + 1, remark.lastIndexOf(" "), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.item_raceofficialp_tv1.setText(spannableString);

        holder.item_raceofficialp_tv2.setText(bean.getName());
        if (bean.getIsQuick() == 1) {
            holder.ll_item_raceofficialp_tvparent.setVisibility(View.VISIBLE);
        } else {
            holder.ll_item_raceofficialp_tvparent.setVisibility(View.INVISIBLE);
        }

        holder.childList.clear();

        if (index_gf_x != 4) {
            String[] arry = bean.getCodes().split(",");
            for (int i = 0; i < arry.length; i++) {
                holder.childList.add(String.valueOf(bean.getId() * 100 + Integer.parseInt(arry[i])));
            }

            if (holder.xuanwuGfCAdapter == null) {
                holder.xuanwuGfCAdapter = new XuanwuGfCAdapter(mContext, B, list, holder.childList, isClickList, index_gf_x, index_gf_y);
                GridLayoutManager layoutManage = new GridLayoutManager(mContext, 6);
                holder.item_raceofficialp_recycler.setLayoutManager(layoutManage);
                holder.item_raceofficialp_recycler.setAdapter(holder.xuanwuGfCAdapter);
            } else {
                holder.xuanwuGfCAdapter.notifyDataSetChanged();
            }
        } else {

            if (index_gf_y == 0) {
                for (int i = 0; i < list.size(); i++) {
                    holder.childList.add(list.get(i).getName());
                }
            }

            if (index_gf_y == 1) {
                for (int i = 0; i < list.size(); i++) {
                    holder.childList.add(String.valueOf(list.get(i).getId() * 100 + Integer.parseInt(list.get(i).getCodes())));
                }

            }
            if (holder.xuanwuGfCAdapter == null) {
                holder.xuanwuGfCAdapter = new XuanwuGfCAdapter(mContext, B, list, holder.childList, isClickList, index_gf_x, index_gf_y);
                GridLayoutManager layoutManage = new GridLayoutManager(mContext, 6);
                holder.item_raceofficialp_recycler.setLayoutManager(layoutManage);
                holder.item_raceofficialp_recycler.setAdapter(holder.xuanwuGfCAdapter);
            } else {
                holder.xuanwuGfCAdapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            if (index_gf_x == 4) {
                return 1;
            } else {
                return list.size();
            }
        } else {
            return 0;
        }
    }

    public class VH extends RecyclerView.ViewHolder {
        private XuanwuGfCAdapter xuanwuGfCAdapter;

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
        @BindView(R.id.ll_item_raceofficialp_tvparent)
        LinearLayout ll_item_raceofficialp_tvparent;


        @OnClick({R.id.item_raceofficialp_tv3, R.id.item_raceofficialp_tv4, R.id.item_raceofficialp_tv5, R.id.item_raceofficialp_tv6, R.id.item_raceofficialp_tv7, R.id.item_raceofficialp_tv8})
        public void tvClick(View view) {
            switch (view.getId()) {
                case R.id.item_raceofficialp_tv3:
                    int position3 = (int) view.getTag(); //TODO 2019-7-22 获取控件 position tag
                    B[position3][0] = 1;
                    B[position3][1] = 0;
                    B[position3][2] = 0;
                    B[position3][3] = 0;
                    B[position3][4] = 0;
                    B[position3][5] = 0;
                    //     list.get(position3).getId()
                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            // for (Map.Entry<String,Boolean> entry:isClickMap.entrySet()){
                            for (IsClickModel isClickModel : isClickList) {
                                //  isClickMap.replace(entry.getKey(), true);
                                StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                            }
                        }
                    } else {
                        //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position3).getId()) {
                                //   isClickMap.replace(entry.getKey(), true);
                                StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                            } else {
                                if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                    // isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            }
                        }
                    }

                    //     xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
                case R.id.item_raceofficialp_tv4:
                    int position4 = (int) view.getTag();
                    B[position4][0] = 0;
                    B[position4][1] = 1;
                    B[position4][2] = 0;
                    B[position4][3] = 0;
                    B[position4][4] = 0;
                    B[position4][5] = 0;
                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            //  for (Map.Entry<String,Boolean> entry:isClickMap.entrySet()){
                            for (IsClickModel isClickModel : isClickList) {
                                if (Integer.parseInt(isClickModel.getId()) % 100 > 5) {
                                    //  isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //  isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            }
                        }
                    } else {
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position4).getId()) {
                                if ((Integer.parseInt(isClickModel.getId()) % 100) > 5) {
                                    //    isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    // isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            } else {
                                if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                    if ((Integer.parseInt(isClickModel.getId()) % 100) > 5) {
                                        //isClickMap.replace(entry.getKey(), false);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                    }
                                }
                            }
                        }
                    }
                    //    xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
                case R.id.item_raceofficialp_tv5:
                    int position5 = (int) view.getTag();
                    B[position5][0] = 0;
                    B[position5][1] = 0;
                    B[position5][2] = 1;
                    B[position5][3] = 0;
                    B[position5][4] = 0;
                    B[position5][5] = 0;

                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            //   for (Map.Entry<String,Boolean> entry:isClickMap.entrySet()){
                            for (IsClickModel isClickModel : isClickList) {
                                if (Integer.parseInt(isClickModel.getId()) % 100 < 6) {
                                    //   isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //  isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            }
                        }
                    } else {
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position5).getId()) {
                                if ((Integer.parseInt(isClickModel.getId()) % 100) < 6) {
                                    //  isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //  isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            } else {
                                if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                    if ((Integer.parseInt(isClickModel.getId()) % 100) < 6) {
                                        // isClickMap.replace(entry.getKey(), false);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                    }
                                }
                            }
                        }
                    }


                    //   xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
                case R.id.item_raceofficialp_tv6:

                    int position6 = (int) view.getTag();
                    B[position6][0] = 0;
                    B[position6][1] = 0;
                    B[position6][2] = 0;
                    B[position6][3] = 1;
                    B[position6][4] = 0;
                    B[position6][5] = 0;
                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            //  for (Map.Entry<String,Boolean> entry:isClickMap.entrySet()){
                            for (IsClickModel isClickModel : isClickList) {
                                if (Integer.parseInt(isClickModel.getId()) % 2 == 1) {
                                    // isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //  isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            }
                        }
                    } else {
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position6).getId()) {
                                if ((Integer.parseInt(isClickModel.getId()) % 2) == 1) {
                                    //  isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //   isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            } else {
                                if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                    if ((Integer.parseInt(isClickModel.getId()) % 2) == 1) {
                                        //  isClickMap.replace(entry.getKey(), false);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                    }
                                }
                            }
                        }
                    }


                    //  xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
                case R.id.item_raceofficialp_tv7:
                    int position7 = (int) view.getTag();
                    B[position7][0] = 0;
                    B[position7][1] = 0;
                    B[position7][2] = 0;
                    B[position7][3] = 0;
                    B[position7][4] = 1;
                    B[position7][5] = 0;

                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            //     for (Map.Entry<String,Boolean> entry:isClickMap.entrySet()){
                            for (IsClickModel isClickModel : isClickList) {
                                if (Integer.parseInt(isClickModel.getId()) % 2 == 0) {
                                    //   isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    //  isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            }
                        }
                    } else {
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position7).getId()) {
                                if ((Integer.parseInt(isClickModel.getId()) % 2) == 0) {
                                    //    isClickMap.replace(entry.getKey(), true);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                } else {
                                    // isClickMap.replace(entry.getKey(), false);
                                    StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                }
                            } else {
                                if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                    if ((Integer.parseInt(isClickModel.getId()) % 2) == 0) {
                                        //isClickMap.replace(entry.getKey(), false);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                    }
                                }
                            }
                        }
                    }

                    //   xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
                case R.id.item_raceofficialp_tv8:
                    int position8 = (int) view.getTag();
                    B[position8][0] = 0;
                    B[position8][1] = 0;
                    B[position8][2] = 0;
                    B[position8][3] = 0;
                    B[position8][4] = 0;
                    B[position8][5] = 0;
                    if (index_gf_x == 4) {
                        if (index_gf_y == 1) {
                            //    for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                            for (IsClickModel isClickModel : isClickList) {
                                // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                                //  isClickMap.replace(entry.getKey(), false);
                                StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                            }
                        }
                    } else {
                        // for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(isClickModel.getId()) / 100 == list.get(position8).getId()) {
                                //  isClickMap.replace(entry.getKey(), false);
                                StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                            }
                        }
                    }
                    //    xuanwuGfCAdapter.notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnGfListener(isClickList, B);
                    break;
            }
        }

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            item_raceofficialp_tv1.setOnClickListener(v -> {
                        if (index_gf_x == 4 && index_gf_y == 1) {
                            JiangjinDialog jiangjinDialog = new JiangjinDialog(mContext, list);
                            jiangjinDialog.show();
                            jiangjinDialog.setCanceledOnTouchOutside(true);
                            jiangjinDialog.setYesOnclickListener(() -> jiangjinDialog.dismiss()
                            );
                        }
                    }
            );
        }
    }
}
