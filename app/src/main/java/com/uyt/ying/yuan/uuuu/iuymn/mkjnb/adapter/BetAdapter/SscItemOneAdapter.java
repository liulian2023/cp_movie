
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

/*
投注list第一种布局中recycleView适配器(大小单双复式布局)
 */
public class SscItemOneAdapter extends CommonAdapter<SscItemOneAdapter.MyHolder, SscOfiicialBetMedol> {
        private ArrayList<SscOfiicialBetMedol>selectorList=new ArrayList<>();
        Context context;


        public SscItemOneAdapter(Context context,ArrayList<SscOfiicialBetMedol> list, ArrayList<SscOfiicialBetMedol> selectorList) {
            super(list);
            this.selectorList = selectorList;
            this.context = context;
        }

        @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        final SscOfiicialBetMedol itemModel = getItemModel(position);
        commonHolder.betTypeTv.setText(itemModel.getCodes());
            if(itemModel.getStatus()==1){
                commonHolder.betTypeTv.setSelected(true);
            }
            else{
                commonHolder.betTypeTv.setSelected(false);
            }

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
                notifyDataSetChanged();
            }

        });
        commonHolder.betTypeTv.setTag(position);

    }

    @Override
    public int getLayOutRes() {
        return R.layout.ssc_official_item_one_recycle;
    }

    public static class MyHolder extends CommonHolder {
        private TextView betTypeTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            betTypeTv=itemView.findViewById(R.id.ssc_bet_textview);
        }
    }
}
