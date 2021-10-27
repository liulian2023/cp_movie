package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Lottety;

import java.util.ArrayList;

/**
 * 全部彩票adapter
 */

public class AllLotteryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<Lottety> lottetyArray =new ArrayList<>();
    private ArrayList<Lottety> lottetyTwoList=new ArrayList<>();
    Context context;
    public AllLotteryAdapter(ArrayList<Lottety> lottetyArray,Context context,ArrayList<Lottety> lottetyTwoList) {
        this.lottetyArray = lottetyArray;
        this.context = context;
        this.lottetyTwoList = lottetyTwoList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder viewHolder = null;
        Context context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.all_tollery_recycleview_one, parent, false);
        viewHolder = new lottetyTypeViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        lottetyTypeViewHodler vh = (lottetyTypeViewHodler) holder;
        Lottety lotteryOne = lottetyArray.get(position);
        vh.type.setText(lotteryOne.getName());
        Glide.with(context)
                .load(R.drawable.lottery_type_icon)
                .into(vh.fireId);
        ArrayList<Lottety> lottetyArraytList=new ArrayList<>();
        for (int i = 0; i <lottetyTwoList.size() ; i++) {
            Lottety lottety = lottetyTwoList.get(i);
            String id = lottety.getId();
            if(lotteryOne.getIdList().contains(id)){
                lottetyArraytList.add(lottety);
            }
        }
        AllLotteryAdapterTwo allLotteryAdapterTwo = new AllLotteryAdapterTwo(lottetyArraytList, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        vh.recyclerView.setLayoutManager(gridLayoutManager);
        vh.recyclerView.setAdapter(allLotteryAdapterTwo);
        allLotteryAdapterTwo.notifyDataSetChanged();
        allLotteryAdapterTwo.setItemClickLintener(new AllLotteryAdapterTwo.ItemClickLintener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,lottetyArraytList,position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return lottetyArray.size();
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,ArrayList<Lottety> lottetyArraytList, int position);
    }
    private AllLotteryAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(AllLotteryAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public class lottetyTypeViewHodler extends RecyclerView.ViewHolder {
        TextView type;
        ImageView fireId;
        RecyclerView recyclerView;
        public lottetyTypeViewHodler(View itemView) {
            super(itemView);
            type =  itemView.findViewById(R.id.lottrty_type);
            fireId = itemView.findViewById(R.id.fire);
            recyclerView=  itemView.findViewById(R.id.all_lottery_recycle_two);
        }
    }


}
