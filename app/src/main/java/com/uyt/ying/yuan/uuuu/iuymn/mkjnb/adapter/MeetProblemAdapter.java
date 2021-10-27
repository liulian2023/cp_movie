package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommonProblemModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class MeetProblemAdapter extends CommonAdapter<MeetProblemAdapter.MyHolder, CommonProblemModel> {
    public MeetProblemAdapter(ArrayList<CommonProblemModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        CommonProblemModel itemModel = getItemModel(position);
        TextView problemTv = commonHolder.problemTv;
        if(itemModel.getStatus()==1){
            problemTv.setSelected(true);
        }else {
            problemTv.setSelected(false);
        }
        problemTv.setText(itemModel.getProblemStr());
        problemTv.setTag(position);
        problemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    itemModel.setStatus(0);
                    notifyDataSetChanged();
                }else {
                    initItemStatus(position);
                }
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }

            private void initItemStatus(int position) {
                for (int i = 0; i < list.size(); i++) {
                    CommonProblemModel problemModel = list.get(i);
                    if(position==i){
                        problemModel.setStatus(1);
                    }else {
                        problemModel.setStatus(0);
                    }
                }
                notifyDataSetChanged();
            }

        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.mine_problem_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView problemTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            problemTv=itemView.findViewById(R.id.meet_problem_tv);
        }
    }
}
