package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChildBetRecycleAdapter extends RecyclerView.Adapter<ChildBetRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<ChildBetModel>childBetModelArrayList =new ArrayList<>();

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
    ColorStateList textColors;
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
    public ChildBetRecycleAdapter(ArrayList<ChildBetModel> childBetModelArrayList) {
        this.childBetModelArrayList = childBetModelArrayList;
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }
   private ChildBetRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

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
    public ChildBetRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         if (i == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (i == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        }else{
             View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_bet_recycle_item,viewGroup,false);
             view.setOnClickListener(this);
            return new MyHolder(view);
         }
    }

    @Override
    public void onBindViewHolder(@NonNull ChildBetRecycleAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long l = Long.parseLong(childBetModelArrayList.get(position).getCreatedDate());
            String format = simpleDateFormat.format(l);
             myHolder.name.setText(childBetModelArrayList.get(position).getNikeName());
             myHolder.type.setText(childBetModelArrayList.get(position).getTypename());
             myHolder.time.setText(format);
            textColors=myHolder.name.getTextColors();
             myHolder.count.setText(Utils.getString(R.string.第)+childBetModelArrayList.get(position).getLotteryqishu()+Utils.getString(R.string.期));
        if(childBetModelArrayList.get(position).getRemark().equals(Utils.getString(R.string.中奖))){
            myHolder.status.setText(Utils.getString(R.string.已中奖));
            myHolder.amount.setText(childBetModelArrayList.get(position).getBonus().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//中奖金额
            myHolder.status.setTextColor(Color.RED);
            myHolder.amount.setTextColor(Color.RED);
        }else if(childBetModelArrayList.get(position).getRemark().equals(Utils.getString(R.string.未中))){
            myHolder.status.setText(Utils.getString(R.string.未中奖));
            myHolder.amount.setText(childBetModelArrayList.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            myHolder.status.setTextColor(textColors);
            myHolder.amount.setTextColor(textColors);

        }else {
            myHolder.status.setText(Utils.getString(R.string.等待开奖));
            myHolder.amount.setText(childBetModelArrayList.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
        }
        myHolder.itemView.setTag(position);
        }

    }

    @Override
    public int getItemCount() {
        return childBetModelArrayList.size();
    }
//    public void add(ArrayList<ChildBetModel> childBetModelArrayList) {
//        //增加数据
//        int position = childBetModelArrayList.size();
//        childBetModelArrayList.addAll(position, childBetModelArrayList);
//        notifyDataSetChanged();
//    }
//    public void errorRefresh(ArrayList<ChildBetModel> newList) {
//        //刷新数据
//        childBetModelArrayList.removeAll(childBetModelArrayList);
//        childBetModelArrayList.addAll(newList);
//        notifyDataSetChanged();
//    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView amount;
        TextView time;
        TextView count;
        TextView status;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            type=itemView.findViewById(R.id.type);
            amount=itemView.findViewById(R.id.amount);
            time=itemView.findViewById(R.id.time);
            count=itemView.findViewById(R.id.count);
            status=itemView.findViewById(R.id.status);


        }
    }
}
