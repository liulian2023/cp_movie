package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetAndDealAllPop;

import java.util.ArrayList;

public class    ChildBetAndDealAllPupopwindowAdapter extends RecyclerView.Adapter<ChildBetAndDealAllPupopwindowAdapter.MyHolder> {
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPopArrayList =new ArrayList<>();

    public ChildBetAndDealAllPupopwindowAdapter(ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPopArrayList) {
        this.childBetAndDealAllPopArrayList = childBetAndDealAllPopArrayList;

    }

    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(MyHolder view,int position);
    }
    private ChildBetAndDealAllPupopwindowAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(ChildBetAndDealAllPupopwindowAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ChildBetAndDealAllPupopwindowAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_bet_dealall_pupopwindow_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildBetAndDealAllPupopwindowAdapter.MyHolder myHolder, final int i) {
        myHolder.name.setText(childBetAndDealAllPopArrayList.get(i).getName());
        ChildBetAndDealAllPop childBetAndDealAllPop = childBetAndDealAllPopArrayList.get(i);
        if(childBetAndDealAllPop.getStatus()==1) {
            myHolder.name.setChecked(true);
            mOnItemClickListener.onItemClick(myHolder,i);
        }else{
            myHolder.name.setChecked(false);
        }
        myHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked( i);
            }
        });
        myHolder.name.setTag(i);
    }

    private void checked(int i) {
        for (int a = 0; a< childBetAndDealAllPopArrayList.size(); a++){
            ChildBetAndDealAllPop childBetAndDealAllPop = childBetAndDealAllPopArrayList.get(a);
            if(a==i) {
                childBetAndDealAllPop.setStatus(1);

            }else{
                childBetAndDealAllPop.setStatus(0);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return childBetAndDealAllPopArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RadioButton name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.type);
        }
    }
}
