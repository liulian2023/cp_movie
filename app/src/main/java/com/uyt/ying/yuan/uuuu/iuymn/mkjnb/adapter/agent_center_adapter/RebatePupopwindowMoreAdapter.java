package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Odds;

import java.util.ArrayList;
        /*
        返点赔率表里层recycleView适配器
         */
public class RebatePupopwindowMoreAdapter extends RecyclerView.Adapter<RebatePupopwindowMoreAdapter.MyHolder> implements View.OnClickListener {
    private  ArrayList<Odds> oddsArrayList =new ArrayList<>();//数据容器(在外层recycleView适配器中赋值,在当前适配器做数据处理)

    public RebatePupopwindowMoreAdapter(ArrayList<Odds> oddsArrayList) {
        this.oddsArrayList = oddsArrayList;
    }

    @NonNull
    @Override
    public RebatePupopwindowMoreAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.rebate_more_pupopwindow_item,null);
        return new MyHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RebatePupopwindowMoreAdapter.MyHolder myHolder, int i) {
        //数据处理
        myHolder.rebate.setText(oddsArrayList.get(i).getRebate());
        myHolder.odds.setText(oddsArrayList.get(i).getOdds());
//        myHolder.linearLayout.setOnClickListener(this);
        myHolder.setIsRecyclable(false);
        myHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return oddsArrayList.size();
    }

    @Override
    public void onClick(View v) {

    if(mOnItemClickListener!=null){
        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }
    }
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    private RebatePupopwindowMoreAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView rebate;
        TextView odds;
//        LinearLayout linearLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            rebate=itemView.findViewById(R.id.rebateText);
            odds=itemView.findViewById(R.id.odds);
//            linearLayout=itemView.findViewById(R.id.linear);
        }
    }
}
