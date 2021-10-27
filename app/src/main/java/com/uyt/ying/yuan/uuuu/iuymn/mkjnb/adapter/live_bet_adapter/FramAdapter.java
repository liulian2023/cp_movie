package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_bet_adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy10Model;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class FramAdapter extends CommonAdapter<FramAdapter.MyHolder, Happy10Model> {
    private ArrayList<Happy10Model> selecterList=new ArrayList<>();

    public FramAdapter(ArrayList<Happy10Model> list, ArrayList<Happy10Model> selecterList) {
        super(list);
        this.selecterList = selecterList;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        Happy10Model itemModel = getItemModel(position);
        TextView contentTv = commonHolder.contentTv;
        contentTv.setText(itemModel.getBetType());
        commonHolder.oddsTv.setText(itemModel.getRebate());
        int status = itemModel.getStatus();
        if(status==1){
            contentTv.setSelected(true);
        }else {
            contentTv.setSelected(false);
        }
        contentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecterList.remove(itemModel);
                if(itemModel.getStatus()==0){
                    itemModel.setStatus(1);
                    selecterList.add(itemModel);
                }else {
                    itemModel.setStatus(0);
                }
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }

                notifyDataSetChanged();
            }

        });
        contentTv.setTag(position);

    }

    @Override
    public int getLayOutRes() {
        return R.layout.live_bet_reycle_item_layout;
    }

    public static class MyHolder extends CommonHolder {
        private TextView contentTv;
        private TextView oddsTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            contentTv= itemView.findViewById(R.id.fram_bet_content_tv);
            oddsTv=itemView.findViewById(R.id.fram_bet_odd_tv);
        }
    }
}
