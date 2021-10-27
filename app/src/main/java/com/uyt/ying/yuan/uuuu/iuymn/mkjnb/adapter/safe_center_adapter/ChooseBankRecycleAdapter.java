package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.safe_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

import java.util.ArrayList;

public class ChooseBankRecycleAdapter extends RecyclerView.Adapter<ChooseBankRecycleAdapter.MyHolder>implements View.OnClickListener {
    ArrayList<String> banks =new ArrayList<>();

    public ChooseBankRecycleAdapter(ArrayList<String> banks) {
        this.banks = banks;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @NonNull
    @Override
    public ChooseBankRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choose_bank_recylce_item,viewGroup,false);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.bankName.setText(banks.get(i));
        myHolder.itemView.setTag(i);
    }
    @Override
    public int getItemCount() {
        return banks.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView bankName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            bankName=itemView.findViewById(R.id.choose_bank_text);
        }
    }
}
