package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class SscOfficialChooseAdapter extends CommonAdapter<SscOfficialChooseAdapter.MyHolder,SscOfficialChooseMedol> {
    public SscOfficialChooseAdapter(ArrayList<SscOfficialChooseMedol> list) {
        super(list);
    }



    @Override
    public void handleViewHolder(MyHolder commonHolder, final int position) {
        SscOfficialChooseMedol itemModel = getItemModel(position);
        commonHolder.radioButton.setText(itemModel.getName());
        if(itemModel.getStatus()==1){
            commonHolder.radioButton.setChecked(true);
        }else{
            commonHolder.radioButton.setChecked(false);
        }
        commonHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked(v,position);
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        commonHolder.radioButton.setTag(position);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.bet_choose_play_pop_recy_item;
    }
    private void checked(View v, int potision) {
        for (int i = 0; i < list.size(); i++) {
            SscOfficialChooseMedol sscOfficialChooseMedol = list.get(i);
            if(potision==i){
                sscOfficialChooseMedol.setStatus(1);
            }else{
                sscOfficialChooseMedol.setStatus(0);
            }
        }
        notifyDataSetChanged();
    }
    public static class MyHolder extends CommonHolder {
        private RadioButton radioButton;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.type_radio_button);
        }
    }
}
