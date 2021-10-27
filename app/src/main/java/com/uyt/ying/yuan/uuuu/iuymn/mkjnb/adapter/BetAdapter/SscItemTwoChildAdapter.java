
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class SscItemTwoChildAdapter extends CommonAdapter<SscItemTwoChildAdapter.MyHolder, SscOfiicialBetMedol> {
    private ArrayList<SscOfiicialBetMedol>selectorList=new ArrayList<>();

    public SscItemTwoChildAdapter(ArrayList<SscOfiicialBetMedol> list, ArrayList<SscOfiicialBetMedol> selectorList) {
        super(list);
        this.selectorList = selectorList;
    }

    @Override
    public void handleViewHolder(final MyHolder commonHolder, int position) {
        final SscOfiicialBetMedol itemModel = getItemModel(position);
        commonHolder.betTypeTv.setText(itemModel.getCodes());
        commonHolder.betTypeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!v.isSelected()){
//                   commonHolder.itemView.setSelected(true);
                    itemModel.setStatus(1);
                    selectorList.add(itemModel);
                }else {
//                   commonHolder.itemView.setSelected(false);
//                   selecterList.remove(position);
                    itemModel.setStatus(0);
                    selectorList.remove(itemModel);
                }
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }


            }

        });
        commonHolder.betTypeTv.setTag(position);
        if(itemModel.getStatus()==1){
            commonHolder.betTypeTv.setSelected(true);
        }
        else{
            commonHolder.betTypeTv.setSelected(false);
        }

    }

    @Override
    public int getLayOutRes() {
        return R.layout.ssc_bet_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView betTypeTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            betTypeTv=itemView.findViewById(R.id.ssc_bet_item_two);
        }
    }
}
