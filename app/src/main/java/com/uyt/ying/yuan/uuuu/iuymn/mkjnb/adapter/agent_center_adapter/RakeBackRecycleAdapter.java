package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RakeBackModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RakeBackRecycleAdapter extends RecyclerView.Adapter<RakeBackRecycleAdapter.MyHolder> {
    private ArrayList<RakeBackModel>rakeBackModelArrayList =new ArrayList<>();
    public RakeBackRecycleAdapter(ArrayList<RakeBackModel> rakeBackModelArrayList) {
        this.rakeBackModelArrayList = rakeBackModelArrayList;
    }
    @NonNull
    @Override
    public RakeBackRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rake_back_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RakeBackRecycleAdapter.MyHolder myHolder, int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myHolder.createDate.setText(simpleDateFormat.format(Long.parseLong(rakeBackModelArrayList.get(i).getCreateDate())));
        String price = rakeBackModelArrayList.get(i).getPrice();
        if(price.startsWith("-")){
            myHolder.remark.setText(rakeBackModelArrayList.get(i).getRemake()+"   "+ price);
        }else {
            myHolder.remark.setText(rakeBackModelArrayList.get(i).getRemake()+"   "+"+"+ price);
        }
    }
    @Override
    public int getItemCount() {
        return rakeBackModelArrayList.size();
    }
//    public void errorRefresh(ArrayList<RakeBackModel>newList){
//        rakeBackModelArrayList.removeAll(rakeBackModelArrayList);
//        rakeBackModelArrayList.addAll(newList);
//        notifyDataSetChanged();
//    }
//    public void add(ArrayList<RakeBackModel> rakeBackModelArrayList){
//        int potion = rakeBackModelArrayList.size();
//        rakeBackModelArrayList.addAll(potion,rakeBackModelArrayList);
//        notifyDataSetChanged();
//
//    }
    public class MyHolder extends RecyclerView.ViewHolder {
         TextView remark;
         TextView createDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            remark=itemView.findViewById(R.id.text_remark);
            createDate=itemView.findViewById(R.id.create_time);
        }
    }
}
