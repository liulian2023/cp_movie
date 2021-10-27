/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;


import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;



public class DialogJJAdapter extends RecyclerView.Adapter<DialogJJAdapter.VH> {

    List<NewplayModel.PlayRuleListFourBean> list;
    Context mContext;


    public DialogJJAdapter(Context mContext,List<NewplayModel.PlayRuleListFourBean> list ) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_jiangjin, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        NewplayModel.PlayRuleListFourBean bean = list.get(position);
        //TODO 2019-7-22  设置控件position tag
        holder.item_jiangjin_name.setText(bean.getCodes());
        holder.item_jiangjin_money.setText(String.valueOf(bean.getOdds()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.item_jiangjin_name)
        TextView item_jiangjin_name;
        @BindView(R.id.item_jiangjin_money)
        TextView item_jiangjin_money;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
