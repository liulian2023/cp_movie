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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.RaceActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RaceOfficialCAdapter extends RecyclerView.Adapter<RaceOfficialCAdapter.VH> {

    Context mContext;
    int[][] B;
    List<NewplayModel.PlayRuleListFourBean> fourlist;
    List<String> list;
  //  HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;


    public RaceOfficialCAdapter(Context mContext,int[][] B,List<NewplayModel.PlayRuleListFourBean> fourlist, List<String> list, List<IsClickModel> isClickList) {
        this.mContext = mContext;
        this.B = B;
        this.fourlist = fourlist;
        this.list = list;
        this.isClickList = isClickList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.item_raceofficialc, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String bean = list.get(position);
        holder.ll_item_raceofficialc.setTag(position);

        holder.item_raceofficialc_tv.setText(String.valueOf(Integer.parseInt(bean)%100));
       // if(isClickMap.get(bean)){
        if (StrUtil.isclick(isClickList,bean)){
            holder.item_raceofficialc_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_circle_actioncolor));
            holder.item_raceofficialc_tv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.item_raceofficialc_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_circle_white));
            holder.item_raceofficialc_tv.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
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

    public  class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_raceofficialc)
        LinearLayout ll_item_raceofficialc;
        @BindView(R.id.item_raceofficialc_tv)
        TextView item_raceofficialc_tv;


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ll_item_raceofficialc.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String choicebean = list.get((int)v.getTag());
                    for (int i=0;i<fourlist.size();i++){
                        if (Integer.parseInt(choicebean)/100==fourlist.get(i).getId()){
                            B[i][0]=0;
                            B[i][1]=0;
                            B[i][2]=0;
                            B[i][3]=0;
                            B[i][4]=0;
                            B[i][5]=0;
                        }
                    }
                //    if (isClickMap.get(choicebean)){
                      if (StrUtil.isclick(isClickList,choicebean)){
                       // isClickMap.replace(choicebean,false);
                          StrUtil.isclickReplace(isClickList,choicebean,false);
                    }else {
                       /* for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            if (Integer.parseInt(entry.getKey())%100==Integer.parseInt(choicebean)%100&&Integer.parseInt(entry.getKey())!=Integer.parseInt(choicebean)) {
                                isClickMap.replace(entry.getKey(),false);
                            }
                        }*/
                     //   isClickMap.replace(choicebean,true);
                          StrUtil.isclickReplace(isClickList,choicebean,true);
                    }
                //    notifyDataSetChanged();
                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                    ((RaceActivity) mContext).OnGfListener(isClickList,B);
                }
            });


        }
    }
}
