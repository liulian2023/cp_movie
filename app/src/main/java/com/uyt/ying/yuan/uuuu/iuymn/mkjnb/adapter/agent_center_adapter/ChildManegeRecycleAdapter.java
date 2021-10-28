package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildManageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChildManegeRecycleAdapter extends RecyclerView.Adapter<ChildManegeRecycleAdapter.MyHolder> implements View.OnClickListener {
     ArrayList<ChildManageModel> childManageModelArrayList =new ArrayList<>();

    public ChildManegeRecycleAdapter(ArrayList<ChildManageModel> childManageModelArrayList) {
        this.childManageModelArrayList = childManageModelArrayList;
    }
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);
    }
    private ChildManegeRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

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
    public ChildManegeRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_manage_recycle_item,viewGroup,false);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildManegeRecycleAdapter.MyHolder myHolder, int i) {
    myHolder.childNum.setText(childManageModelArrayList.get(i).getChildNUm());
    myHolder.name.setText(childManageModelArrayList.get(i).getName());
    myHolder.level.setText(childManageModelArrayList.get(i).getLevel());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long l = Long.parseLong(childManageModelArrayList.get(i).getLoginDate());
    String format1 = format.format(l);
    long l1 = Long.parseLong(childManageModelArrayList.get(i).getRegisterDate());
    String format2 = format.format(l1);
    myHolder.ljfy.setText(childManageModelArrayList.get(i).getCommission()+ Utils.getString(R.string.元));
    myHolder.dqye.setText(childManageModelArrayList.get(i).getAmount()+Utils.getString(R.string.元));
    myHolder.zcsj.setText(format2);
    myHolder.zhdl.setText(format1);
    myHolder.wjlx.setText(childManageModelArrayList.get(i).getAgentLevel()+Utils.getString(R.string.级代理));
    myHolder.ckfd.setOnClickListener(this);
    myHolder.ckxj.setOnClickListener(this);
    myHolder.ckfd.setTag(i);
    myHolder.ckxj.setTag(i);
    myHolder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return childManageModelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView level;
        TextView childNum;
        TextView ljfy;
        TextView dqye;
        TextView zcsj;
        TextView zhdl;
        TextView wjlx;
        TextView ckxj;
        TextView ckfd;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name);
            level =itemView.findViewById(R.id.level);
            childNum =itemView.findViewById(R.id.child_num);
            ljfy =itemView.findViewById(R.id.ljfy);
            dqye =itemView.findViewById(R.id.dqye);
            zcsj =itemView.findViewById(R.id.zcsj);
            zhdl =itemView.findViewById(R.id.zhdl);
            wjlx =itemView.findViewById(R.id.wjlx);
            ckxj =itemView.findViewById(R.id.ckxj);
            ckfd =itemView.findViewById(R.id.ckfd);
        }
    }
}
