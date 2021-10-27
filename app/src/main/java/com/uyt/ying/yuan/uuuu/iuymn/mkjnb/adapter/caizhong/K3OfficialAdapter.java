/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.K3Activity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class K3OfficialAdapter extends RecyclerView.Adapter<K3OfficialAdapter.VH>  {

    Context mContext;
    List<GetGroup.GameruleTwoBean> list;
    int index;
 //   LinkedHashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;
    int num_4 = 0;

    public K3OfficialAdapter(Context mContext, List<GetGroup.GameruleTwoBean> list, int index, List<IsClickModel> isClickList) {
        this.mContext = mContext;
        this.list = list;
        this.index = index;
        this.isClickList = isClickList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_k3official, viewGroup, false);
        return new VH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        GetGroup.GameruleTwoBean bean = list.get(position);
        holder.ll_item_k3official.setTag(position);
      //  if (isClickMap.get(String.valueOf(bean.getId()))) {
        if (StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
            holder.ll_item_k3official.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            holder.item_k3official_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            holder.item_k3official_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.ll_item_k3official.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            holder.item_k3official_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.red_6he));
            holder.item_k3official_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        holder.item_k3official_tv1.setText(bean.getName());
        if (index==0){
            holder.item_k3official_tv2.setText(Utils.getString(R.string.赔) + String.valueOf(bean.getOdds()));
            holder.item_k3official_tv2.setVisibility(View.VISIBLE);
        }else {
            holder.item_k3official_tv2.setVisibility(View.GONE);
        }
        //TODO  2019-7-19 设置按钮大小
        if (index==1
                ||index==3){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) holder.ll_item_k3official.getLayoutParams();
            params.weight=LinearLayout.LayoutParams.MATCH_PARENT;
            params.leftMargin =100;
            params.rightMargin =100;
            params.height=120;
            holder.ll_item_k3official.setLayoutParams(params);
        }
        if (index==2
                ||index==4
                ||index==5
                ||index==6
                ||index==7){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) holder.ll_item_k3official.getLayoutParams();
            params.weight=LinearLayout.LayoutParams.MATCH_PARENT;
            params.height=120;
            holder.ll_item_k3official.setLayoutParams(params);
        }
        if (index==4){
            num_4 = 0;
            /*for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                if (entry.getValue()) {
                    num_4++;
                }
            }*/
            num_4= StrUtil.isclickCal(isClickList);
        }

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

        @BindView(R.id.ll_item_k3official)
        LinearLayout ll_item_k3official;
        @BindView(R.id.item_k3official_tv1)
        TextView item_k3official_tv1;
        @BindView(R.id.item_k3official_tv2)
        TextView item_k3official_tv2;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ll_item_k3official.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    GetGroup.GameruleTwoBean choiceBean = list.get((int) v.getTag());

                        int choice_flag = (int) v.getTag();
                 //       if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                        if (StrUtil.isclick(isClickList,String.valueOf(choiceBean.getId()))){
                          //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                            StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()),false);
                        } else {
                         //   isClickMap.replace(String.valueOf(choiceBean.getId()), true);
                            StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()),true);
                            if (index==6){
                                if (choice_flag<6){
                               //     isClickMap.replace(String.valueOf(choiceBean.getId()+6), false);
                                    StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()+6),false);
                                }else {
                                 //   isClickMap.replace(String.valueOf(choiceBean.getId()-6), false);
                                    StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()-6),false);
                                }
                            }
                        }
                        notifyDataSetChanged();
                        //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                        ((K3Activity) mContext).OnClickListener(isClickList);



                }
            });


        }
    }
}
