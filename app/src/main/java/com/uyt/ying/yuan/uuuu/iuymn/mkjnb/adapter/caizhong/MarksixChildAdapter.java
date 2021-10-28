/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.MarksixActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarksixChildAdapter extends RecyclerView.Adapter<MarksixChildAdapter.VH> {


    int num_zxbz = 0;
    int num_lm = 0;
    Context mContext;
    List<MarksixBean> list;
    String groupname;
    List<String> zxOddList = new ArrayList<>();//自选不中的赔率
    int Row;
    int index;
    //  HashMap<String, Boolean> isClickMap;
    List<IsClickModel> isClickList;

    public MarksixChildAdapter(Context context, List<MarksixBean> list, int Row, int index, String groupname, /*HashMap<String, Boolean> isClickMap*/List<IsClickModel> isClickList) {
        this.mContext = context;
        this.list = list;
        this.Row = Row;
        this.index = index;
        this.isClickList = isClickList;
        this.groupname = groupname;
        if (index == 8) {
            zxOddList = Arrays.asList(list.get(0).getOdds2().split(","));
        }

    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_racechild, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //   holder.setIsRecyclable(false);
        MarksixBean bean = list.get(position);

        holder.ll_item_racechild.setTag(position);

        if (index == 0 || index == 4 || index == 5 || index == 10) {
            //  if (isClickMap.get(String.valueOf(bean.getId()))) {
            if (StrUtil.isclick(isClickList, String.valueOf(bean.getId()))) {
                if (StringMyUtil.isEmptyString(bean.getColor())) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
                } else if (bean.getColor().equals("red")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgred_onpress));
                } else if (bean.getColor().equals("blue")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgblue_onpress));
                } else if (bean.getColor().equals("green")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkggreen_onpress));
                }
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                if (index==5&&bean.getXgroupname().equals(Utils.getString(R.string.两面))){
                    if (bean.getName().contains(Utils.getString(R.string.蓝))){
                        holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgblue_onpress));
                    }
                    else if (bean.getName().contains(Utils.getString(R.string.绿))){
                        holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkggreen_onpress));
                    }else {
                        holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgred_onpress));
                    }
                }
            } else {

                if (StringMyUtil.isEmptyString(bean.getColor())) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
                } else if (bean.getColor().equals("red")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
                } else if (bean.getColor().equals("blue")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.blue_6he));
                } else if (bean.getColor().equals("green")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.green_6he));
                }
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
                if (index==5&&bean.getXgroupname().equals(Utils.getString(R.string.两面))){
                    if (bean.getName().contains(Utils.getString(R.string.蓝))){
                        holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.blue_6he));
                    }else if (bean.getName().contains(Utils.getString(R.string.绿))){
                        holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.green_6he));
                    }else {
                        holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
                    }
                }
            }
        } else if (index == 1 || index == 2 || index == 7 || index == 9) {
            if (index == 1||index == 7 || index == 9) {
                if (list.size() == 12) {
                    holder.item_racechild_tv3.setVisibility(View.VISIBLE);
                    holder.item_racechild_tv3.setText(bean.getRemark());
                } else if (list.size() == 10) {
                    holder.item_racechild_tv3.setVisibility(View.GONE);
                }
            }
            //  if (isClickMap.get(String.valueOf(bean.getId()))) {
            if (StrUtil.isclick(isClickList, String.valueOf(bean.getId()))) {
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            } else {
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
                holder.item_racechild_tv3.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
            }
        } else if (index == 3) {
            //  if (isClickMap.get(String.valueOf(bean.getId()))) {
            if (StrUtil.isclick(isClickList, String.valueOf(bean.getId()))) {
                if (StringMyUtil.isEmptyString(bean.getCode())) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
                } else if (bean.getCode().equals("red")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgred_onpress));
                } else if (bean.getCode().equals("blue")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgblue_onpress));
                } else if (bean.getCode().equals("green")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkggreen_onpress));
                }
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            } else {

                if (StringMyUtil.isEmptyString(bean.getCode())) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
                } else if (bean.getCode().equals("red")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
                } else if (bean.getCode().equals("blue")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.blue_6he));
                } else if (bean.getCode().equals("green")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.green_6he));
                }
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
            }
        } else if (index == 6) {
            //   if (isClickMap.get(String.valueOf(bean.getId()))) {
            if (StrUtil.isclick(isClickList, String.valueOf(bean.getId()))) {
                if (bean.getCode().equals("blue")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgblue_onpress));
                } else if (bean.getCode().equals("green")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkggreen_onpress));
                } else {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgred_onpress));
                }
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            } else {

                if (bean.getCode().equals("blue")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.blue_6he));
                } else if (bean.getCode().equals("green")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.green_6he));
                } else {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
                }
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
            }
        } else if (index == 8) {
            // if (isClickMap.get(String.valueOf(bean.getId()))) {
            if (StrUtil.isclick(isClickList, String.valueOf(bean.getId()))) {
                if (bean.getColor().equals("blue")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgblue_onpress));
                } else if (bean.getColor().equals("green")) {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkggreen_onpress));
                } else {
                    holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkgred_onpress));
                }
                holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            } else {

                if (bean.getColor().equals("blue")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.blue_6he));
                } else if (bean.getColor().equals("green")) {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.green_6he));
                } else {
                    holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
                }
                holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
                holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
            }
        }

        holder.item_racechild_tv1.setText(bean.getName());
        holder.item_racechild_tv2.setText(Utils.getString(R.string.赔) + String.valueOf(bean.getOdds()));

        if (index == 8) {
            num_zxbz = 0;
            /*for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                if (entry.getValue()) {
                    num_zxbz++;
                }
            }*/
            num_zxbz = StrUtil.isclickCal(isClickList);

            if (num_zxbz < 5) {
                holder.item_racechild_tv2.setText("--");
            } else if (num_zxbz >= 5 && num_zxbz <= (5 + zxOddList.size() - 1)) {
                holder.item_racechild_tv2.setText(zxOddList.get(num_zxbz - 5));
            }
        } else if (index == 10) {
            num_lm = StrUtil.isclickCal(isClickList);
            /*for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                if (entry.getValue()) {
                    num_lm++;
                }
            }*/
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int x = 0;
                switch (index) {
                    case 0:
                        if (list.size() == 49) {
                            if (position >= 0 && position <= 47) {
                                x = 8;
                            } else if (position == 48) {
                                x = 48;
                            }
                        } else if (list.size() == 14) {
                            if (position >= 0 && position <= 11) {
                                x = 12;
                            } else if (position >= 12 && position <= 13) {
                                x = 24;
                            }
                        }
                        break;
                    case 1:
                        x = 1;
                        break;
                    case 2:
                        x = 1;
                        break;
                    case 3:
                        x = 1;
                        break;
                    case 4:
                        if (list.size() == 49) {
                            if (position >= 0 && position <= 47) {
                                x = 4;
                            } else if (position == 48) {
                                x = 24;
                            }
                        } else if (list.size() == 4) {
                            x = 6;
                        }
                        break;
                    case 5:
                        if (list.size() == 49) {
                            if (position >= 0 && position <= 47) {
                                x = 3;
                            } else if (position == 48) {
                                x = 18;
                            }
                        } else if (list.size() == 9) {
                            x = 6;
                        }
                        break;
                    case 6:
                        if (list.size() == 13) {
                            if (position >= 0 && position <= 7) {
                                x = 6;
                            } else if (position >= 8 && position <= 10) {
                                x = 8;
                            } else if (position >= 11 && position <= 12) {
                                x = 12;
                            }
                        }
                        break;
                    case 7:
                        if (list.size() == 12) {
                            x = 2;
                        } else if (list.size() == 10) {
                            if (position >= 0 && position <= 7) {
                                x = 2;
                            } else if (position >= 8 && position <= 9) {
                                x = 4;
                            }
                        }
                        break;
                    case 8:
                        if (list.size() == 49) {
                            if (position >= 0 && position <= 47) {
                                x = 1;
                            } else if (position == 48) {
                                x = 6;
                            }
                        }
                        break;
                    case 9:
                        if (list.size() == 12) {
                            x = 2;
                        } else if (list.size() == 10) {
                            if (position >= 0 && position <= 7) {
                                x = 2;
                            } else if (position >= 8 && position <= 9) {
                                x = 4;
                            }
                        }
                        break;
                    case 10:
                        if (list.size() == 49) {
                            if (position >= 0 && position <= 47) {
                                x = 1;
                            } else if (position == 48) {
                                x = 6;
                            }
                        }
                        break;
                }
                return x;
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_racechild)
        LinearLayout ll_item_racechild;
        @BindView(R.id.item_racechild_tv1)
        TextView item_racechild_tv1;
        @BindView(R.id.item_racechild_tv2)
        TextView item_racechild_tv2;
        @BindView(R.id.item_racechild_tv3)
        TextView item_racechild_tv3;


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ll_item_racechild.setOnClickListener(v -> {
                if (index == 8) {
                    if (num_zxbz < (5 + zxOddList.size() - 1)) {
                        MarksixBean choiceBean = list.get((int) v.getTag());
                        //     if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                        if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                            //  isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                            notifyDataSetChanged();
                            //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                            ((MarksixActivity) mContext).OnClickListener(isClickList);
                        } else {
                            //   isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                            notifyDataSetChanged();
                            //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                            ((MarksixActivity) mContext).OnClickListener(isClickList);
                        }

                    } else if (num_zxbz == (5 + zxOddList.size() - 1)) {
                        MarksixBean choiceBean = list.get((int) v.getTag());
                        // if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                        if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                            //   isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                            notifyDataSetChanged();
                            //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                            ((MarksixActivity) mContext).OnClickListener(isClickList);
                        } else {
                            ToastUtil.showToast(Utils.getString(R.string.不允许超过) + (5 + zxOddList.size() - 1) + Utils.getString(R.string.个选项));

                        }

                    }
                } else if (index == 10) {
                    switch (list.get(0).getXgroupname()) {
                        case "三中二":
                            if (num_lm < 3) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //   if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //  isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                }

                            } else if (num_lm == 3) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //    if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过3个选项));

                                }

                            }
                            break;
                        case "三全中":
                            if (num_lm < 3) {
                                initClick(v);

                            } else if (num_lm == 3) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //    if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过3个选项));

                                }

                            }
                            break;
                        case "二全中":
                            if (num_lm < 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //  if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //    isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    //   isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                }

                            } else if (num_lm == 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //  if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    // isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过2个选项));

                                }

                            }
                            break;
                        case "二中特":
                            if (num_lm < 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //   if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //   isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    //    isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                }

                            } else if (num_lm == 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //   if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    // isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过2个选项));

                                }

                            }
                            break;
                        case "特串":
                            if (num_lm < 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //   if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //  isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                }

                            } else if (num_lm == 2) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //   if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    // isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {

                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过2个选项));

                                }

                            }
                            break;
                        case "四全中":
                            if (num_lm < 4) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //  if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    // isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                }

                            } else if (num_lm == 4) {
                                MarksixBean choiceBean = list.get((int) v.getTag());
                                //  if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                                if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                                    //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                    StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                                    notifyDataSetChanged();
                                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                    ((MarksixActivity) mContext).OnClickListener(isClickList);
                                } else {
                                    ToastUtil.showToast(Utils.getString(R.string.不允许超过4个选项));

                                }

                            }
                            break;

                    }
                } else {
                    MarksixBean choiceBean = list.get((int) v.getTag());
                    //          if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                    if (StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                        //   isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                        StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                    } else {
                        //  isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                        StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                    }
                    notifyDataSetChanged();
                    Utils.logE("isClickList size",""+isClickList.size());
                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                    ((MarksixActivity) mContext).OnClickListener(isClickList);

                }
            });
        }

        private void initClick(View v) {
            MarksixBean choiceBean = list.get((int) v.getTag());
            //   if (!isClickMap.get(String.valueOf(choiceBean.getId()))) {
            if (!StrUtil.isclick(isClickList, String.valueOf(choiceBean.getId()))) {
                //  isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), true);
                notifyDataSetChanged();
                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                ((MarksixActivity) mContext).OnClickListener(isClickList);
            } else {
                StrUtil.isclickReplace(isClickList, String.valueOf(choiceBean.getId()), false);
                notifyDataSetChanged();
                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                ((MarksixActivity) mContext).OnClickListener(isClickList);
            }
        }


    }


}
