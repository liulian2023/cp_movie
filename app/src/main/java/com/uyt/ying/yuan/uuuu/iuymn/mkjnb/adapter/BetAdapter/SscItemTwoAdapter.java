

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

public class SscItemTwoAdapter extends CommonAdapter<SscItemTwoAdapter.MyHolder, SscOfiicialBetMedol> {
    private Context context;
//    private SscItemTwoChildAdapter sscItemTwoChildAdapter;
    private ArrayList<SscOfiicialBetMedol>selectorList=new ArrayList<>();

    public SscItemTwoAdapter(/*Context context,*/ArrayList<SscOfiicialBetMedol> list,  ArrayList<SscOfiicialBetMedol> selectorList) {
        super(list);
//        this.context = context;
        this.selectorList = selectorList;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, final int position) {
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
                if(onBetTypeClickLintener!=null){
                    onBetTypeClickLintener.initSelectedNum(selectorList.size());
                }
                if(onFourItemClickLintener!=null){
                    onFourItemClickLintener.onFourItemClick(v,selectorList,position);
                }
                notifyDataSetChanged();
            }

        });
        commonHolder.betTypeTv.setTag(position);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.ssc_official_item_two_recycle;
    }

    public  static  interface  OnBetTypeClickLintener{
        void onBetTypeListener(View view,  ArrayList<SscOfiicialBetMedol> sscBetChildMedols,int position);
        void initSelectedNum(int size);

    }
    public SscItemTwoAdapter.OnBetTypeClickLintener onBetTypeClickLintener=null;

    public void setOnBetTypeClickLintener(OnBetTypeClickLintener onBetTypeClickLintener) {
        this.onBetTypeClickLintener = onBetTypeClickLintener;
    }

    public  static  interface  onFourItemClickLintener{
        void onFourItemClick(View view,  ArrayList<SscOfiicialBetMedol>officialSelector,int position);


    }
   public SscItemTwoAdapter.onFourItemClickLintener onFourItemClickLintener=null;

    public void setOnFourItemClickLintener(SscItemTwoAdapter.onFourItemClickLintener onFourItemClickLintener) {
        this.onFourItemClickLintener = onFourItemClickLintener;
    }

    public static class MyHolder extends CommonHolder {
        private TextView betTypeTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            betTypeTv=itemView.findViewById(R.id.ssc_bet_textview);
//            keyboardLinear=itemView.findViewById(R.id.keyboard_linear);
//            recyclerView=itemView.findViewById(R.id.ssc_official_bet_item_two_recycle);
        }
    }
}
