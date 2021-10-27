
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseTwoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class SscOffcialChooseThreeAdapter extends CommonAdapter<SscOffcialChooseThreeAdapter.MyHolder, SscOfficialChooseMedol> {
    private ArrayList<SscOfficialChooseTwoMedol> sscTwoList =new ArrayList<>();
    ArrayList<SscOfficialChooseMedol> sscThreeLIstAll = new ArrayList<>();
    Context context;

    public SscOffcialChooseThreeAdapter() {
    }

    public SscOffcialChooseThreeAdapter(Context context, ArrayList<SscOfficialChooseMedol> list, ArrayList<SscOfficialChooseTwoMedol> sscTwoList, ArrayList<SscOfficialChooseMedol> sscThreeLIstAll) {
        super(list);
        this.context=context;
        this.sscTwoList = sscTwoList;
        this.sscThreeLIstAll = sscThreeLIstAll;
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
        return R.layout.ssc_bet_choose_play_pop_recy_item;
    }

    private void checked(View v, int potision) {
        for (int i = 0; i < list.size(); i++) {
            SscOfficialChooseMedol sscOfficialChooseMedol = list.get(i);
            if(potision==i){
//                sscOfficialChooseMedol.setStatus(1);
                initStatus(sscOfficialChooseMedol);
                if(onStatusListener!=null){
                    //将所点击的model回调到二级列表适配器进行选中状态的处理(需要配合initStatus()同时进行处理)
                    onStatusListener.statusListener(sscOfficialChooseMedol,potision);
                }
            }else{
                sscOfficialChooseMedol.setStatus(0);
            }
        }
        notifyDataSetChanged();
        SscOffcialChooseTwoAdapter sscOffcialChooseTwoAdapter = new SscOffcialChooseTwoAdapter(context, sscTwoList, sscThreeLIstAll);
        sscOffcialChooseTwoAdapter.notifyDataSetChanged();
    }
    //初始化除了被点击item以外的所有三级列表model选中状态(需要配合回调同时进行处理)
    private void initStatus (SscOfficialChooseMedol medol){
        for (int i = 0; i < sscTwoList.size(); i++) {
            ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList = sscTwoList.get(i).getSscOfficialChooseMedolArrayList();
            for (int j = 0; j < sscOfficialChooseMedolArrayList.size(); j++) {
                sscOfficialChooseMedolArrayList.get(j).setStatus(0);
            }
        }
        medol.setStatus(1);

    }
    public static class MyHolder extends CommonHolder {
        private RadioButton radioButton;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.type_radio_button);
        }

    }

    public  static  interface  OnStatusListener{
        void statusListener(SscOfficialChooseMedol model, int position);
    }
    public SscOffcialChooseThreeAdapter.OnStatusListener  onStatusListener=null;

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }
}
