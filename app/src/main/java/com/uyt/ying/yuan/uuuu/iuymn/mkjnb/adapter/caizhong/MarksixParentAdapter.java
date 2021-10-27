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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarksixParentAdapter extends RecyclerView.Adapter<MarksixParentAdapter.VH>{

    Context mContext;
    List<String> titleList;
    List<MarksixBean> marksixBeanList;
    int Row;
    int index;
 //   HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;

    public MarksixParentAdapter(Context context, List<String> titleList, List<MarksixBean> marksixBeanList,
                                int Row, int index, List<IsClickModel> isClickList){

        this.mContext = context;
        this.titleList = titleList;
        this.marksixBeanList = marksixBeanList;
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
        if (StringMyUtil.isEmptyString(titleList.get(position))){
            holder.item_raceparent_tv.setVisibility(View.GONE);
        }else {
            holder.item_raceparent_tv.setVisibility(View.VISIBLE);
            holder.item_raceparent_tv.setText(titleList.get(position));
        }
        holder.childList.clear();
        holder.groupname="";
        holder.groupname=titleList.get(position);
        for (int i=0;i<marksixBeanList.size();i++){
            if (marksixBeanList.get(i).getXgroupname().equals(titleList.get(position))){
                holder.childList.add(marksixBeanList.get(i));
            }
        }
        if (  holder.marksixChildAdapter == null){

            holder.marksixChildAdapter = new MarksixChildAdapter(mContext, holder.childList,Row,index,holder.groupname,isClickList);

            GridLayoutManager layoutManage = new GridLayoutManager(mContext, Row);
            holder.item_raceparent_recyclerview.setLayoutManager(layoutManage);
         //   holder.marksixChildAdapter.setHasStableIds(true);
        //    holder.item_raceparent_recyclerview.addItemDecoration(new SpaceItemDecoration(10, SpaceItemDecoration.GRIDLAYOUT));
            holder.item_raceparent_recyclerview.setAdapter(holder.marksixChildAdapter);
        }else {
            holder.marksixChildAdapter.notifyDataSetChanged();
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

        private MarksixChildAdapter marksixChildAdapter;
        private List<MarksixBean> childList = new ArrayList<>();
        private String groupname;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
