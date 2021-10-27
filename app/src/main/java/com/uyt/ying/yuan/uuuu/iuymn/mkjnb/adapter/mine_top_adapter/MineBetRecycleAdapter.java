package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MineBetRecycleAdapter extends RecyclerView.Adapter<MineBetRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<MineBetModel>mineBetModelArrayList =new ArrayList<>();

    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private RecyclerView mRecyclerView;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }
    public MineBetRecycleAdapter(ArrayList<MineBetModel> mineBetModelArrayList) {
        this.mineBetModelArrayList = mineBetModelArrayList;
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
   private MineBetRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
        @NonNull
    @Override
    public MineBetRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         if (i == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (i == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        }else{
             View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_bet_recycle_item,viewGroup,false);
             view.setOnClickListener(this);
            return new MyHolder(view);
         }
    }

    @Override
    public void onBindViewHolder(@NonNull MineBetRecycleAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            long l = Long.parseLong(mineBetModelArrayList.get(position).getCreatedDate());
             String format = simpleDateFormat.format(l);
             myHolder.typeName.setText(mineBetModelArrayList.get(position).getTypeName());
             myHolder.time.setText(format);
             myHolder.qishu.setText(Utils.getString(R.string.第)+mineBetModelArrayList.get(position).getLotteryqishu()+Utils.getString(R.string.期));
        if(mineBetModelArrayList.get(position).getRemark().equals(Utils.getString(R.string.中奖))){
            myHolder.remark.setText(Utils.getString(R.string.已中奖));
            myHolder.amount.setText(mineBetModelArrayList.get(position).getBonus().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//中奖金额
            myHolder.remark.setTextColor(Color.RED);
            myHolder.amount.setTextColor(Color.RED);

        }else if(mineBetModelArrayList.get(position).getRemark().equals(Utils.getString(R.string.未中))){
            myHolder.remark.setText(Utils.getString(R.string.未中奖));
            myHolder.amount.setText(mineBetModelArrayList.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            myHolder.remark.setTextColor(Color.parseColor("#868686"));
            myHolder.amount.setTextColor(Color.parseColor("#868686"));

        }else if (mineBetModelArrayList.get(position).getRemark().equals(Utils.getString(R.string.撤单))){
            myHolder.remark.setText(Utils.getString(R.string.已撤单));
            myHolder.amount.setText(mineBetModelArrayList.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            myHolder.remark.setTextColor(Color.parseColor("#868686"));
            myHolder.amount.setTextColor(Color.parseColor("#868686"));
        }
        else{
            myHolder.remark.setText(Utils.getString(R.string.等待开奖));
            myHolder.amount.setText(mineBetModelArrayList.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            myHolder.remark.setTextColor(Color.parseColor("#868686"));
            myHolder.amount.setTextColor(Color.parseColor("#868686"));
        }
        myHolder.groundName.setText("["+mineBetModelArrayList.get(position).getGroupname()+"]"+mineBetModelArrayList.get(position).getRulename());
        myHolder.itemView.setTag(position);
        }

    }

    @Override
    public int getItemCount() {
        return mineBetModelArrayList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView typeName;
        TextView qishu;
        TextView time;
        TextView amount;
        TextView groundName;
        TextView remark;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            typeName=itemView.findViewById(R.id.mine_bet_typeName);
            qishu=itemView.findViewById(R.id.mine_bet_qishu);
            time=itemView.findViewById(R.id.mine_bet_time);
            amount=itemView.findViewById(R.id.mine_bet_amount);
            groundName=itemView.findViewById(R.id.mine_bet_groudName);
            remark=itemView.findViewById(R.id.mine_bet_remark);
        }
    }
}
