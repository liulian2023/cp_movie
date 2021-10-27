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

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewGridAdapter extends RecyclerView.Adapter<RecyclerViewGridAdapter.GridViewHolder>{
    private Activity mContext;
    //泛型是RecylerView所需的Bean类
    private List<String> mDateBean;
    private int x;
    private List<Boolean> isClicks;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //7、定义点击事件回调接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    //构造方法，一般需要接受两个参数，上下文，集合对象（包含我们所需要的数据）
    public RecyclerViewGridAdapter(Activity context, List<String> dates,int x) {
        this.mContext = context;
        this.mDateBean = dates;
        this.x= x;
        //3、为集合添加值
        isClicks = new ArrayList<>();
      //  isClicks.add(true);
        for(int i =0;i<dates.size();i++){
            isClicks.add(false);
        }
        isClicks.set(x,true);
    }

    //创建ViewHolder也就是说创建出来一条,并把ViewHolder（item）返回出去
    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个View布局对象，决定了item的样子， 参数1：上下文 2. xml布局对象 3.为null
        View view = View.inflate(mContext, R.layout.item_head1recyclergrid, null);
        //创建一个ViewHolder对象
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        //把ViewHolder对象传出去
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(final GridViewHolder holder, final int position) {
        //从集合里拿对应item的数据对象
        String dateBean = mDateBean.get(position);
        //给holder里面的控件对象设置数据
        holder.setData(dateBean);

        //5、记录要更改属性的控件
        holder.itemView.setTag(holder.mTvName);
     //   holder.itemView.setTag(holder.itemLayout);
        //6、判断改变属性
        if(isClicks.get(position)){
            holder.mTvName.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            holder.mTvName.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
        }else{
            holder.mTvName.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            holder.mTvName.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
        }

        //4：设置点击事件
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    for(int i = 0; i <isClicks.size();i++){
                        isClicks.set(i,false);
                    }
                    isClicks.set(position,true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (mDateBean != null && mDateBean.size() > 0) {
            return mDateBean.size();
        }
        return 0;
    }
    public class GridViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTvName;

        public GridViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.item_head1recyclergrid_tv);
        }

        public void setData(String data) {
            //给imageView设置图片
      //      mIvIcon.setImageResource(data.icon);
            //给TextView设置文本
            mTvName.setText(data);
        }
    }


}

