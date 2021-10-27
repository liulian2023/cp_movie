/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity.PlanListActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanListPopRecyAdapter  extends RecyclerView.Adapter<PlanListPopRecyAdapter.VH>{

    Context context;
    List<String> list;
   // HashMap<String,Boolean> hashMaps;
   List<IsClickModel> isClickList;


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //7、定义点击事件回调接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public PlanListPopRecyAdapter(Context context,List<String> list,List<IsClickModel> isClickList/*HashMap<String,Boolean> hashMaps*/){
        this.context =context;
        this.list = list;
      //  this.hashMaps = hashMaps;
        this.isClickList = isClickList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_planlistrecycler,viewGroup,false);
        //创建一个ViewHolder对象
        VH vh = new VH(view);
        //把ViewHolder对象传出去
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final VH vh, final  int position) {
        vh.planlistrecycler_item.setTag(position);

        vh.item_planlist_tv.setText(list.get(position));
        if (StrUtil.isclick(isClickList,String.valueOf(list.get(position)))/*hashMaps.get(list.get(position))*/){
            vh.item_planlist_tv.setTextColor(ContextCompat.getColor(context,R.color.action_bar_color));
            vh.item_planlist_iv.setVisibility(View.VISIBLE);
        }else {
            vh.item_planlist_tv.setTextColor(ContextCompat.getColor(context,R.color.textgray));
            vh.item_planlist_iv.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
       if (list!=null&&list.size()>0){
           return list.size();
       }else {
           return 0;
       }
    }

    public  class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.planlistrecycler_item)
        RelativeLayout planlistrecycler_item;
        @BindView(R.id.item_planlist_tv)
        TextView item_planlist_tv;
        @BindView(R.id.item_planlist_iv)
        ImageView item_planlist_iv;




        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            planlistrecycler_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = list.get((int) v.getTag());
                 //   hashMaps.clear();
                    isClickList.clear();
                    for (int i =0;i<list.size();i++){
                     //   hashMaps.put(list.get(i),false);
                        StrUtil.isclickAdd(isClickList,list.get(i),false);
                    }
                 //   hashMaps.replace(str,true);
                    StrUtil.isclickReplace(isClickList,str,true);

                    notifyDataSetChanged();
                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                    ((PlanListActivity) context).OnClickListener(isClickList);
                }
            });

        }
    }
}
