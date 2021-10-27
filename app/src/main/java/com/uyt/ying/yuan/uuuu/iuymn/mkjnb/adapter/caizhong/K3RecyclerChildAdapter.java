/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.app.Activity;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class K3RecyclerChildAdapter extends RecyclerView.Adapter<K3RecyclerChildAdapter.ViewHolder> {

    Activity mContext;
    GetGroup.GameruleBean bean;
    List<String> list;
    boolean isImage;
    int[] shaizis;//筛子点数



    public K3RecyclerChildAdapter(Activity mContext,GetGroup.GameruleBean bean){
        this.mContext = mContext;
        this.bean = bean;
        checkBean(bean.getName());
    }

    private void checkBean(String str){

        shaizis = Const.getShaziArray(mContext);
        list = new ArrayList<String>();
        String[] arry = str.split("_");
        for (int i = 0; i < arry.length; i++) {
            //       System.out.println(sourceStrArray[i]);
            list.add(arry[i]);
        }
        for (String s :list){
            if (s.contains(Utils.getString(R.string.点))){
                isImage = false;
                return;
            }else if (Utils.isNumeric(s)){
                isImage = true;
            }else {
                isImage = false;
            }
        }

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个View布局对象，决定了item的样子， 参数1：上下文 2. xml布局对象 3.为null
        View view = View.inflate(mContext, R.layout.item_k3_child_recyclerview, null);
        //创建一个ViewHolder对象
        ViewHolder vh = new ViewHolder(view);
        //把ViewHolder对象传出去
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
     //   holder.mTextView.setText("Grid "+position);

        //从集合里拿对应item的数据对象
        String dataBean = list.get(position);
        //给holder里面的控件对象设置数据

        if (!isImage){
            holder.setData(dataBean);
        }




        //5、记录要更改属性的控件
        holder.itemView.setTag(holder.mTextView);
     //   holder.itemView.setTag(holder.itemLayout);
        //6、判断改变属性
        if(isImage){
            holder.mTextView.setBackground(ContextCompat.getDrawable(mContext,shaizis[position]));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;
    //    LinearLayout itemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.item_k3_child_recyclerview_tv);
         //   itemLayout = itemView.findViewById(R.id.item_k3_child_itemLayout);
        }

        public void setData(String data) {

            mTextView.setText(data);
        }
    }
}
