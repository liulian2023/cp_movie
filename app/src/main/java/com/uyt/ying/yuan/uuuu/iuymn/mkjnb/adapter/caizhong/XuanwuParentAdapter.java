/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanwuGroupBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanwuParentAdapter extends RecyclerView.Adapter<XuanwuParentAdapter.VH>{

    Context mContext;
    List<String> titleList;
    List<XuanwuGroupBean> xuanwuGroupBeanList;
    int Row;
    int index;
  //  HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;

    public XuanwuParentAdapter(Context context, List<String> titleList, List<XuanwuGroupBean> xuanwuGroupBeanList,
                               int Row, int index, List<IsClickModel> isClickList){

        this.mContext = context;
        this.titleList = titleList;
        this.xuanwuGroupBeanList = xuanwuGroupBeanList;
        this.Row = Row;
        this.index = index;
        this.isClickList = isClickList;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.item_raceparent, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.item_raceparent_tv.setText(titleList.get(position));
        holder.childList.clear();
        for (int i=0;i<xuanwuGroupBeanList.size();i++){
            if (xuanwuGroupBeanList.get(i).getXgroupname().equals(titleList.get(position))){
                holder.childList.add(xuanwuGroupBeanList.get(i));
            }
        }
        if (  holder.raceChildAdapter == null){

            holder.raceChildAdapter = new XuanwuChildAdapter(mContext, holder.childList,Row,index,isClickList);
            GridLayoutManager layoutManage = new GridLayoutManager(mContext, Row);
            holder.item_raceparent_recyclerview.setLayoutManager(layoutManage);
        //    holder.item_raceparent_recyclerview.addItemDecoration(new SpaceItemDecoration(10, SpaceItemDecoration.GRIDLAYOUT));
            holder.item_raceparent_recyclerview.setAdapter(holder.raceChildAdapter);
        }else {
            holder.raceChildAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        if (titleList!=null&&titleList.size()>0){
            return titleList.size();
        }else {
            return 0;
        }
    }

    public  class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.item_raceparent_tv)
        TextView item_raceparent_tv;
        @BindView(R.id.item_raceparent_recyclerview)
        RecyclerView item_raceparent_recyclerview;

        private XuanwuChildAdapter raceChildAdapter;
        private List<XuanwuGroupBean> childList = new ArrayList<>();

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
