/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.RaceActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceGroupBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RaceChildAdapter extends RecyclerView.Adapter<RaceChildAdapter.VH> {

    Context mContext;
    List<RaceGroupBean> list;
    int Row;
    boolean isDam;
  //  HashMap<String,Boolean> isClickMap;
  List<IsClickModel> isClickList;


    public RaceChildAdapter(Context context,List<RaceGroupBean> list,int Row, boolean isDam, /*HashMap<String,Boolean> isClickMap*/List<IsClickModel> isClickList){
        this.mContext = context;
        this.list = list;
        this.Row = Row;
        this.isDam = isDam;
        this.isClickList = isClickList;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.item_racechild, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        RaceGroupBean bean = list.get(position);

        holder.ll_item_racechild.setTag(position);

      //  if(isClickMap.get(String.valueOf(bean.getId()))){
        if (StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
            holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
            holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        holder.item_racechild_tv1.setText(bean.getName());
        holder.item_racechild_tv2.setText(Utils.getString(R.string.赔)+String.valueOf(bean.getOdds()));



    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (!isDam){  //设置全部行数每列row个
                    return 1;
                }else {
                    //第一行4个     中间行都是3个 x = (num-4)/3   余   y  = 1|2
                    int x =0;
                    if (list.size()==4){
                        if (position>=0&&position<=3){
                           x =  6;
                        }
                    }
                    else if (list.size()==6){
                        if (position>=0&&position<=3){
                            x = 6;
                        }else if(position>=4&&position<=5){
                            x = 12;
                        }
                    }
                    else if (list.size()==21){
                        if (position>=0&&position<=3){
                            x =  6;
                        }else if (position>=4&&position<=18){
                            x = 8;
                        }else if (position>=19&&position<=20){
                            x = 12;
                        }
                    }
                    return x;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
      if (list!=null&&list.size()>0){
          return list.size();
      }else {
          return 0;
      }
    }

    public  class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_racechild)
        LinearLayout ll_item_racechild;
        @BindView(R.id.item_racechild_tv1)
        TextView item_racechild_tv1;
        @BindView(R.id.item_racechild_tv2)
        TextView item_racechild_tv2;



        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ll_item_racechild.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    int raceCount =SharePreferencesUtil.getInt(mContext, "raceCount", 8);

                    RaceGroupBean choiceBean1 = list.get((int)v.getTag());
                    int num = 0;
                   // for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel:isClickList){
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (/*entry.getValue()*/isClickModel.getIsclick()) {
                       //     num++;
                            for (int i =0;i<list.size();i++){
                                if (/*entry.getKey()*/isClickModel.getId().equals(String.valueOf(list.get(i).getId()))){
                                        num++;
                                }
                            }
                        }
                    }
                    if (Row==5&&list.get((int)v.getTag()).getDgroupname().equals(Utils.getString(R.string.一到10名))){
                        if (num<raceCount){
                            RaceGroupBean choiceBean = list.get((int)v.getTag());
                         //   if (!isClickMap.get(String.valueOf(choiceBean.getId()))){
                            if (!StrUtil.isclick(isClickList,String.valueOf(choiceBean.getId()))){
                              //  isClickMap.replace(String.valueOf(choiceBean.getId()),true);
                                StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()),true);
                                notifyDataSetChanged();
                                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                ((RaceActivity) mContext).OnClickListener(isClickList);
                            }else {
                             //   isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()), false);
                                notifyDataSetChanged();
                                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                ((RaceActivity) mContext).OnClickListener(isClickList);
                            }

                        }else if (num==raceCount){
                            RaceGroupBean choiceBean = list.get((int)v.getTag());
                           // if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                            if (StrUtil.isclick(isClickList,String.valueOf(choiceBean.getId()))){
                              //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                                StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()), false);
                                notifyDataSetChanged();
                                //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                                ((RaceActivity) mContext).OnClickListener(isClickList);
                            } else {
                                ToastUtil.showToast(Utils.getString(R.string.该玩法一期投注不能超过) + raceCount + Utils.getString(R.string.个));

                            }
                        }
                    }else {
                        RaceGroupBean choiceBean = list.get((int)v.getTag());
                      //  if (isClickMap.get(String.valueOf(choiceBean.getId()))){
                        if (StrUtil.isclick(isClickList,String.valueOf(choiceBean.getId()))){
                          //  isClickMap.replace(String.valueOf(choiceBean.getId()),false);
                            StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()),false);
                        }else {
                           // isClickMap.replace(String.valueOf(choiceBean.getId()),true);
                            StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()),true);
                        }
                        notifyDataSetChanged();
                        //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                        ((RaceActivity) mContext).OnClickListener(isClickList);
                    }


                }
            });
        }


    }


}
