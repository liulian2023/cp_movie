/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryPlanList;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.VH> {

    Context mContext;
    List<LotteryPlanList.ResultBean> resultList;

    public PlanListAdapter(Context context, List<LotteryPlanList.ResultBean> resultList){
        mContext = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_planlist,viewGroup,false);
        //创建一个ViewHolder对象
        VH vh = new VH(view);
        //把ViewHolder对象传出去
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        LotteryPlanList.ResultBean bean = resultList.get(i);

        String name = bean.getName().replaceAll(Utils.getString(R.string.期),"");
        String[] arry = name.split("-");
        String a1,a2;
        if (arry[0].length()>=3){
           a1 = arry[0].substring(arry[0].length()-3,arry[0].length());
        }else {
            a1 = arry[0];
        }
        if (arry[1].length()>=3){
            a2 = arry[1].substring(arry[1].length()-3,arry[1].length());
        }else {
            a2 = arry[1];
        }
        vh.mTv1.setText(a1+"-"+a2+Utils.getString(R.string.期));
        vh.mTv2.setText(bean.getGroupNameItem());
        vh.mTv3.setText(bean.getLotteryqishu3());
        if (bean.getIslottery()==1){
       //     vh.mTv3.setText(bean.getQishu());
            vh.mTv4.setText(bean.getResult()+Utils.getString(R.string.中奖));
        }else {
     //       vh.mTv3.setText("--");
            vh.mTv4.setText("--");
        }


    }


    @Override
    public int getItemCount() {
        if (resultList!=null&&resultList.size()>0){
            return resultList.size();
        }else {
            return 0;
        }
    }

    public static class VH extends RecyclerView.ViewHolder{


        private final TextView mTv1;
        private final TextView mTv2;
        private final TextView mTv3;
        private final TextView mTv4;

        public VH(@NonNull View itemView) {
            super(itemView);

            mTv1 = itemView.findViewById(R.id.item_planlist_tv1);
            mTv2 = itemView.findViewById(R.id.item_planlist_tv2);
            mTv3 = itemView.findViewById(R.id.item_planlist_tv3);
            mTv4 = itemView.findViewById(R.id.item_planlist_tv4);

        }
    }
}
