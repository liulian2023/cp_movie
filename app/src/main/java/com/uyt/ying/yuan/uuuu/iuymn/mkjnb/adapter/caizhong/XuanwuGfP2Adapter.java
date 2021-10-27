/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
//import android.support.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanwuGfP2Adapter extends RecyclerView.Adapter<XuanwuGfP2Adapter.VH>{


    Context mContext;

    List<NewplayModel.PlayRuleListFourBean> list;
 //   HashMap<String,Boolean> isClickMap;
    int[][] B;
    List<IsClickModel> isClickList;


    public XuanwuGfP2Adapter(Context mContext,int[][] B, List<NewplayModel.PlayRuleListFourBean> list, /*HashMap<String, Boolean> isClickMap*/List<IsClickModel> isClickList) {
        this.mContext = mContext;
        this.B = B;
        this.list = list;
        this.isClickList = isClickList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_raceofficialc2, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.ll_item_raceofficialc2.setTag(position);
        NewplayModel.PlayRuleListFourBean bean = list.get(position);

      //  if (isClickMap.get(String.valueOf(bean.getId()))) {
        if (StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
            holder.item_raceofficialc2_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            holder.item_raceofficialc2_tv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.item_raceofficialc2_tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            holder.item_raceofficialc2_tv.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
        }
        holder.item_raceofficialc2_tv.setText(bean.getCodes());
    }

    @Override
    public int getItemCount() {
       if (list!=null&&list.size()>0){
           return list.size();
       }else
           return 0;
    }

    public  class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_raceofficialc2)
        LinearLayout ll_item_raceofficialc2;
        @BindView(R.id.item_raceofficialc2_tv)
        TextView item_raceofficialc2_tv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            ll_item_raceofficialc2.setOnClickListener(new View.OnClickListener() {
             //   @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    NewplayModel.PlayRuleListFourBean choicebean = list.get((int) v.getTag());

                //    if (isClickMap.get(String.valueOf(choicebean.getId()))){
                    if (StrUtil.isclick(isClickList,String.valueOf(choicebean.getId()))){
                     //   isClickMap.replace(String.valueOf(choicebean.getId()), false);
                        StrUtil.isclickReplace(isClickList,String.valueOf(choicebean.getId()), false);
                    }else {
                      //  isClickMap.replace(String.valueOf(choicebean.getId()), true);
                        StrUtil.isclickReplace(isClickList,String.valueOf(choicebean.getId()), true);
                    }

                    ((XuanwuActivity) mContext).OnGfListener(isClickList,B);
                }

            });

        }
    }

}
