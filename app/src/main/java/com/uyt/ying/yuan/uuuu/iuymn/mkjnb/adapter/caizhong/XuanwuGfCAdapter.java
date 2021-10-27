/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
//import android.support.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanwuGfCAdapter extends RecyclerView.Adapter<XuanwuGfCAdapter.VH> {

    Context mContext;
    int[][] B;
    List<NewplayModel.PlayRuleListFourBean> fourBeanList;
    List<String> list;
    //  HashMap<String, Boolean> isClickMap;
    List<IsClickModel> isClickList;
    int index_gf_x;
    int index_gf_y;

    int last_tag = 0;
    int danma_num = 0;

    public XuanwuGfCAdapter(Context mContext, int[][] B, List<NewplayModel.PlayRuleListFourBean> fourBeanList, List<String> list, List<IsClickModel> isClickList, int index_gf_x, int index_gf_y) {
        this.mContext = mContext;
        this.B = B;
        this.fourBeanList = fourBeanList;
        this.list = list;
        this.isClickList = isClickList;
        this.index_gf_x = index_gf_x;
        this.index_gf_y = index_gf_y;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_raceofficialc, viewGroup, false);
        return new VH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String bean = list.get(position);

        holder.ll_item_raceofficialc.setTag(position);


        if (StrUtil.isclick(isClickList, bean)) {
            holder.item_raceofficialc_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_circle_actioncolor));
            holder.item_raceofficialc_tv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            if (index_gf_x==1&&index_gf_y==2||index_gf_x==6&&index_gf_y==0){
                last_tag = Integer.parseInt(bean);
            }
        } else {
            holder.item_raceofficialc_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_circle_white));
            holder.item_raceofficialc_tv.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
        }
        holder.item_raceofficialc_tv.setText(String.valueOf(Integer.parseInt(bean) % 100));


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

        @BindView(R.id.ll_item_raceofficialc)
        LinearLayout ll_item_raceofficialc;
        @BindView(R.id.item_raceofficialc_tv)
        TextView item_raceofficialc_tv;


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ll_item_raceofficialc.setOnClickListener(v -> {
                String choicebean = list.get((int) v.getTag());

                if (index_gf_x != 4) {
                    for (int i = 0; i < fourBeanList.size(); i++) {
                        if (Integer.parseInt(choicebean) / 100 == fourBeanList.get(i).getId()) {
                            B[i][0] = 0;
                            B[i][1] = 0;
                            B[i][2] = 0;
                            B[i][3] = 0;
                            B[i][4] = 0;
                            B[i][5] = 0;
                        }
                    }
                    //   if (isClickMap.get(choicebean)) {
                    if (StrUtil.isclick(isClickList, choicebean)) {
                        // isClickMap.replace(choicebean, false);
                        StrUtil.isclickReplace(isClickList, choicebean, false);
                    } else {
                        if (index_gf_x == 0 && index_gf_y == 2
                                || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                            danma_num = 0;
                            //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                            for (IsClickModel isClickModel : isClickList) {
                                //    if (entry.getValue()) {
                                if (isClickModel.getIsclick()) {
                                    if (fourBeanList.get(0).getId() == Integer.parseInt(isClickModel.getId()) / 100) {
                                        danma_num++;
                                    }
                                }
                            }
                            //TODO 取余 算不同行同列数据唯一
                            //    for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                            for (IsClickModel isClickModel : isClickList) {
                                // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                                if (Integer.parseInt(isClickModel.getId()) % 100 == Integer.parseInt(choicebean) % 100) {
                                    if (Integer.parseInt(isClickModel.getId()) != Integer.parseInt(choicebean)) {
                                        //  isClickMap.replace(entry.getKey(), false);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), false);
                                    } else {
                                        //isClickMap.replace(entry.getKey(), true);
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                    }
                                }
                            }

                            //TODO  标记胆码最后点击
                            if (index_gf_x == 0 && index_gf_y == 2 || index_gf_x == 1 && index_gf_y == 2 || index_gf_x == 6) {
                                int xxx = 0;
                                if (index_gf_x == 0 && index_gf_y == 2) {
                                    xxx = 2;
                                }
                                if (index_gf_x == 1 && index_gf_y == 2) {
                                    xxx = 1;
                                }
                                if (index_gf_x == 6) {
                                    if (index_gf_y == 0) {
                                        xxx = 1;
                                    }
                                    if (index_gf_y == 1) {
                                        xxx = 2;
                                    }
                                    if (index_gf_y == 2) {
                                        xxx = 3;
                                    }
                                    if (index_gf_y == 3) {
                                        xxx = 4;
                                    }
                                    if (index_gf_y == 4) {
                                        xxx = 5;
                                    }
                                    if (index_gf_y == 5) {
                                        xxx = 6;
                                    }
                                    if (index_gf_y == 6) {
                                        xxx = 7;
                                    }
                                }
                                if (fourBeanList.get(0).getId() == Integer.parseInt(choicebean) / 100) {
                                    if (danma_num < xxx) {
                                        last_tag = Integer.parseInt(choicebean);
                                    } else {
                                        if (last_tag / 100 == fourBeanList.get(0).getId()) {
                                            //  isClickMap.replace(String.valueOf(last_tag), false);
                                            StrUtil.isclickReplace(isClickList, String.valueOf(last_tag), false);
                                            last_tag = Integer.parseInt(choicebean);
                                        }
                                    }
                                }
                            }
                        } else {
                            //  isClickMap.replace(choicebean, true);
                            StrUtil.isclickReplace(isClickList, choicebean, true);
                        }
                    }
                } else {
                    if (index_gf_y == 1) {
                        for (int i = 0; i < 1; i++) {
                            for (int j = 0; j < 6; j++) {
                                B[0][0] = 0;
                                B[0][1] = 0;
                                B[0][2] = 0;
                                B[0][3] = 0;
                                B[0][4] = 0;
                                B[0][5] = 0;
                            }
                        }
                        //  if (isClickMap.get(choicebean)) {
                        if (StrUtil.isclick(isClickList, choicebean)) {
                            // isClickMap.replace(choicebean, false);
                            StrUtil.isclickReplace(isClickList, choicebean, false);
                        } else {
                            //  isClickMap.replace(choicebean, true);
                            StrUtil.isclickReplace(isClickList, choicebean, true);
                        }
                    }
                }


                //       notifyDataSetChanged();
                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                ((XuanwuActivity) mContext).OnGfListener(isClickList, B);

            });


        }
    }
}
