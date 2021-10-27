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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceGroupBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RaceParentAdapter extends RecyclerView.Adapter<RaceParentAdapter.VH>{

    Context mContext;
    List<String> titleList;
    List<RaceGroupBean> raceGroupBeanList;
    int Row;
    boolean isDam;
   // HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;


    public RaceParentAdapter (Context context, List<String> titleList, List<RaceGroupBean> raceGroupBeanList,
                              int Row, boolean isDam, /*HashMap<String,Boolean> isClickMap*/List<IsClickModel> isClickList){

        this.mContext = context;
        this.titleList = titleList;
        this.raceGroupBeanList = raceGroupBeanList;
        this.Row = Row;
        this.isDam = isDam;
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
        for (int i=0;i<raceGroupBeanList.size();i++){
            if (raceGroupBeanList.get(i).getXgroupname().equals(titleList.get(position))){
                holder.childList.add(raceGroupBeanList.get(i));
            }
        }
        if (holder.raceChildAdapter == null){

            holder.raceChildAdapter = new RaceChildAdapter(mContext, holder.childList,Row,isDam,isClickList);
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

        private RaceChildAdapter raceChildAdapter;
        private List<RaceGroupBean> childList = new ArrayList<>();

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
