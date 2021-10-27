package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.InvestModel;


import java.util.ArrayList;

public class InvestRecycleAdapter extends RecyclerView.Adapter<InvestRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<InvestModel> investModelArrayList =new ArrayList<>();
    Context context;
    public InvestRecycleAdapter(ArrayList<InvestModel> investModelArrayList, Context context) {
        this.investModelArrayList = investModelArrayList;
        this.context = context;
    }
    //define interface
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);
    }
    private InvestRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @NonNull
    @Override
    public InvestRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invest_recycle_item, viewGroup, false);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestRecycleAdapter.MyHolder myHolder, int i) {
        if(investModelArrayList.get(i).getRechargeBankList()==null){
            //代充
            myHolder.imgUrl.setImageResource(R.drawable.rengongcz_icon);
            myHolder.maxMix.setText(Utils.getString(R.string.人工充值));
        }else {
            Glide.with(context)
                    .load(investModelArrayList.get(i).getImgUrl())
                    .into(myHolder.imgUrl);
            myHolder.maxMix.setText(Utils.getString(R.string.单笔最低 )+ investModelArrayList.get(i).getMix()+Utils.getString(R.string. 元, )+Utils.getString(R.string.最高 )+ investModelArrayList.get(i).getMax()+Utils.getString(R.string. 元。));
        }

        myHolder.type.setText(investModelArrayList.get(i).getType());

        myHolder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return investModelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgUrl;
        TextView type;
        TextView maxMix;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgUrl=itemView.findViewById(R.id.invest_img);
            type=itemView.findViewById(R.id.invest_type);
            maxMix=itemView.findViewById(R.id.invest_max_mix);
        }
    }
}
