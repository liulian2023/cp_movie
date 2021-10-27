/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.List;

import androidx.annotation.NonNull;

public class TzRecyclerAdapter extends RecyclerView.Adapter<TzRecyclerAdapter.VH> {
    private Activity mContext;
    private List<TouzhuModel> list;
    int MAX_LENGTH = 8;


    private EtChangedListener mEtChangedListener;

    public void setEtChangedListener(EtChangedListener mEtChangedListener) {
        this.mEtChangedListener = mEtChangedListener;
    }

    //7、定义et监听回调接口
    public interface EtChangedListener {
        void onItemChanged(View view, int position, List<TouzhuModel> list);
    }


    //构造方法，一般需要接受两个参数，上下文，集合对象（包含我们所需要的数据）
    public TzRecyclerAdapter(Activity context, List<TouzhuModel> list) {
        mContext = context;
        this.list = list;
    }


    //创建ViewHolder也就是说创建出来一条,并把ViewHolder（item）返回出去
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个View布局对象，决定了item的样子， 参数1：上下文 2. xml布局对象 3.为null
        //   View view = View.inflate(mContext, R.layout.item_k3_recyclerview, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_touzhu_recyclerview, parent, false);
        //创建一个ViewHolder对象
        VH vh = new VH(view);
        //把ViewHolder对象传出去
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.setIsRecyclable(false);

        TouzhuModel bean = list.get(position);
        //整数限制

        holder.mTv3.setText(Long.parseLong(bean.getMoney())*Long.parseLong(bean.getMultiple())+"");
        holder.mEt.setText(bean.getMoney());
        holder.mEt.setSelection(holder.mEt.getText().toString().length());
        holder.mTv1.setText(bean.getGroupname());
        holder.mTv2.setText(bean.getName());
        holder.multiple_tv.setText("x"+bean.getMultiple());

        //  通过tag判断当前editText是否已经设置监听，有监听的话，移除监听再给editText赋值

        if (holder.mEt.getTag() instanceof TextWatcher) {
            holder.mEt.removeTextChangedListener((TextWatcher) holder.mEt.getTag());
        }
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String changeStr = StringMyUtil.isEmptyString(s.toString()) ? "0" : s.toString();
                bean.setMoney(changeStr);
                holder.mTv3.setText(Integer.parseInt(changeStr)*Integer.parseInt(bean.getMultiple())+"");
                //      notifyDataSetChanged();
                mEtChangedListener.onItemChanged(holder.itemView, position, list);

            }
        };

        holder.mEt.addTextChangedListener(textWatcher);
        holder.mEt.setTag(textWatcher);

    }

    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public static class VH extends RecyclerView.ViewHolder {

        //    private final ImageView mIvIcon;
        private final TextView mTv1;
        private final TextView mTv2;
        private final TextView mTv3;
        private final TextView multiple_tv;
        private final EditText mEt;
        //    private final RecyclerView mRecyclerView;


        public VH(View itemView) {
            super(itemView);

            mTv1 = itemView.findViewById(R.id.item_touzhu_recyclerview_tv1);
            mTv2 = itemView.findViewById(R.id.item_touzhu_recyclerview_tv2);
            mTv3 = itemView.findViewById(R.id.item_touzhu_recyclerview_tv3);
            multiple_tv = itemView.findViewById(R.id.multiple_tv);
            mEt = itemView.findViewById(R.id.item_touzhu_recyclerview_et);


        }

       /* public void setData(String data) {
            //给imageView设置图片
            //      mIvIcon.setImageResource(data.icon);
            //给TextView设置文本
            mTv.setText(data);
        }*/
    }


}
