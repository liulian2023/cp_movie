/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
//import android.support.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanwuGroupBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanwuChildAdapter extends RecyclerView.Adapter<XuanwuChildAdapter.VH> {

    Context mContext;
    List<XuanwuGroupBean> list;
    int Row;
    int index;
 //   HashMap<String, Boolean> isClickMap;
    List<IsClickModel> isClickList;

    public XuanwuChildAdapter(Context context, List<XuanwuGroupBean> list, int Row, int index, List<IsClickModel> isClickList) {
        this.mContext = context;
        this.list = list;
        this.Row = Row;
        this.index = index;
        this.isClickList = isClickList;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_racechild, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        XuanwuGroupBean bean = list.get(position);

        holder.ll_item_racechild.setTag(position);

      //  if (isClickMap.get(String.valueOf(bean.getId()))) {
        if (StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
            holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        } else {
            holder.ll_item_racechild.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            holder.item_racechild_tv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
            holder.item_racechild_tv2.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
        }
        holder.item_racechild_tv1.setText(bean.getName());
        holder.item_racechild_tv2.setText(Utils.getString(R.string.赔) + String.valueOf(bean.getOdds()));

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int x = 0;
                switch (index){
                    case 0:
                        if (list.size()==8){
                            if (position>=0&&position<=5){
                                x = 8;
                            }else if (position>=6&&position<=7){
                                x = 12;
                            }
                        }else if (list.size()==4){
                            x = 6;
                        }
                        break;
                    case 1:
                        x = 1;
                        break;
                    case 2:
                        x = 1;
                        break;
                    case 3:
                        x = 1;
                        break;
                    case 4:
                        x = 1;
                        break;
                    case 5:
                        x = 1;
                        break;
                }
               return x;
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class VH extends RecyclerView.ViewHolder {
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
            //    @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    XuanwuGroupBean choiceBean = list.get((int) v.getTag());
                  //  if (isClickMap.get(String.valueOf(choiceBean.getId()))) {
                    if (StrUtil.isclick(isClickList,String.valueOf(choiceBean.getId()))){
                      //  isClickMap.replace(String.valueOf(choiceBean.getId()), false);
                        StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()), false);
                    } else {
                        StrUtil.isclickReplace(isClickList,String.valueOf(choiceBean.getId()), true);
                    }
                    notifyDataSetChanged();
                    //不用在这里调用Activity的回调方法   notifyDataSetChanged()   会自动更新数据
                    ((XuanwuActivity) mContext).OnClickListener(isClickList);
                }
            });
        }


    }


}
