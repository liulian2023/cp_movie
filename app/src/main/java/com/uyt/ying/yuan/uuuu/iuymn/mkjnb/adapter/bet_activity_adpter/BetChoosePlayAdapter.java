package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.bet_activity_adpter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetChoosePalyModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class BetChoosePlayAdapter extends CommonAdapter<BetChoosePlayAdapter.MyHolder, BetChoosePalyModel> {
    public BetChoosePlayAdapter() {
    }

    public BetChoosePlayAdapter(ArrayList<BetChoosePalyModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(final MyHolder commonHolder, final int position) {
        BetChoosePalyModel itemModel = getItemModel(position);

        if(itemModel.getStatus()==1){
            commonHolder.typeRadioButton.setChecked(true);
        }else{
            commonHolder.typeRadioButton.setChecked(false);
        }
        commonHolder.typeRadioButton.setText(itemModel.getType());
        commonHolder.typeRadioButton.setTag(position);
        commonHolder.typeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int a = 0; a< list.size(); a++){
                    BetChoosePalyModel betChoosePalyModel = list.get(a);
                    if(a==position) {
                        betChoosePalyModel.setStatus(1);

                    }else{
                        betChoosePalyModel.setStatus(0);
                    }
                }
                notifyDataSetChanged();
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.bet_choose_play_pop_recy_item;
    }

    public static  class MyHolder extends CommonHolder {
        RadioButton typeRadioButton;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            typeRadioButton=itemView.findViewById(R.id.type_radio_button);
        }
    }
}
