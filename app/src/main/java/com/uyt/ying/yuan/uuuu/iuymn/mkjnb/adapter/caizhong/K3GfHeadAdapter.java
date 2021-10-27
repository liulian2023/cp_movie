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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.K3GfHeadModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class K3GfHeadAdapter extends RecyclerView.Adapter<K3GfHeadAdapter.VH>{

    Context mContext;
    List<K3GfHeadModel> list;
    int x;
    int[] shaizis;//筛子点数
    private List<Boolean> isClicks = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //7、定义点击事件回调接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public K3GfHeadAdapter(Context mContext, List<K3GfHeadModel> list, int x) {
        this.mContext = mContext;
        this.list = list;
        this.x= x;

        for(int i =0;i<list.size();i++){
            isClicks.add(false);
        }
        isClicks.set(x,true);
        shaizis = Const.getShaziArray(mContext);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_k3_gfhead, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, int position) {
        K3GfHeadModel bean = list.get(position);
        holder.item_k3gfhead_tv1.setText(bean.getTitle());
        holder.item_k3gfhead_tv2.setText(bean.getOdds());
        String[] array = bean.getArr().split(",");
        holder.item_k3gfhead_iv1.setImageResource(shaizis[Integer.parseInt(array[0])-1]);
        holder.item_k3gfhead_iv2.setImageResource(shaizis[Integer.parseInt(array[1])-1]);
        holder.item_k3gfhead_iv3.setImageResource(shaizis[Integer.parseInt(array[2])-1]);

        //6、判断改变属性
        if(isClicks.get(position)){
            holder.ll_item_k3gfhead.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_redbk_enable));
        }else{
            holder.ll_item_k3gfhead.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
        }

        //4：设置点击事件
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    for(int i = 0; i <isClicks.size();i++){
                        isClicks.set(i,false);
                    }
                    isClicks.set(position,true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }else {
            return 0;
        }
    }

    public class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_item_k3gfhead)
        LinearLayout ll_item_k3gfhead;
        @BindView(R.id.item_k3gfhead_tv1)
        TextView item_k3gfhead_tv1;
        @BindView(R.id.item_k3gfhead_tv2)
        TextView item_k3gfhead_tv2;
        @BindView(R.id.item_k3gfhead_iv1)
        ImageView item_k3gfhead_iv1;
        @BindView(R.id.item_k3gfhead_iv2)
        ImageView item_k3gfhead_iv2;
        @BindView(R.id.item_k3gfhead_iv3)
        ImageView item_k3gfhead_iv3;

         public VH(View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);

         }


    }
}
