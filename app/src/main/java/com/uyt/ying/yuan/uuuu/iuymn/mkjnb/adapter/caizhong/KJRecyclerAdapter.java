/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.uyt.ying.yuan.R;

import java.util.List;

import pl.droidsonroids.gif.GifTextView;

public class KJRecyclerAdapter  extends RecyclerView.Adapter<KJRecyclerAdapter.VH> {

    private Activity mContext;
    private List<Integer> list;

    //构造方法，一般需要接受两个参数，上下文，集合对象（包含我们所需要的数据）
    public KJRecyclerAdapter(Activity context, List<Integer> list) {
        this.mContext = context;
        this.list = list;
    }

    //创建ViewHolder也就是说创建出来一条,并把ViewHolder（item）返回出去
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个View布局对象，决定了item的样子， 参数1：上下文 2. xml布局对象 3.为null
        View view = View.inflate(mContext, R.layout.item_kjrecycler, null);
        //创建一个ViewHolder对象
        VH viewHolder = new VH(view);
        //把ViewHolder对象传出去
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        //从集合里拿对应item的数据对象
       // int i = list.get(position);

        holder.itemView.setTag(holder.mTvName);

          holder.mTvName.setBackgroundResource(list.get(position));
     //   holder.mTvName.setBackground(mContext.getResources().getDrawable(i));
     //   holder.mTvName.setText(list.get(position));  setBackgroundResource(R.drawable.shaizi6);

    }

    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }


    public class VH extends RecyclerView.ViewHolder{


        private final GifTextView mTvName;


        public VH(View itemView) {
            super(itemView);

            mTvName = (GifTextView) itemView.findViewById(R.id.item_kjrecycler_tv);

        }

        public void setData(String data) {

        //    mTvName.setText(data);
        }
    }
}
