package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineIntegralMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MineIntegralRecycleAdapter extends RecyclerView.Adapter<MineIntegralRecycleAdapter.MyHolder> {
    private ArrayList<MineIntegralMedol> mineIntegralMedolArrayList =new ArrayList<>();

    public MineIntegralRecycleAdapter(ArrayList<MineIntegralMedol> mineIntegralMedolArrayList) {
        this.mineIntegralMedolArrayList = mineIntegralMedolArrayList;
    }

    @NonNull
    @Override
    public MineIntegralRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_integral_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineIntegralRecycleAdapter.MyHolder myHolder, int i) {

        MineIntegralMedol mineIntegralMedol = mineIntegralMedolArrayList.get(i);
        if(mineIntegralMedol.getType()==0){
            if(mineIntegralMedol.getPrice().compareTo(BigDecimal.ZERO)==-1){
                myHolder.remark.setText(Utils.getString(R.string.人工扣款));
            }else{
                myHolder.remark.setText(Utils.getString(R.string.人工入款));
            }
        }else if(mineIntegralMedol.getType()==1){
            myHolder.remark.setText(Utils.getString(R.string.前台充值));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        long l = Long.parseLong(mineIntegralMedol.getCreateDate());
        String format = simpleDateFormat.format(l);
        myHolder.createDate.setText(format);
        myHolder.price.setText("+"+mineIntegralMedol.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return mineIntegralMedolArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView remark;
        TextView price;
        TextView createDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            remark=itemView.findViewById(R.id.integral_remark);
            price=itemView.findViewById(R.id.integral_price);
            createDate=itemView.findViewById(R.id.integral_createDate);
        }
    }
}
