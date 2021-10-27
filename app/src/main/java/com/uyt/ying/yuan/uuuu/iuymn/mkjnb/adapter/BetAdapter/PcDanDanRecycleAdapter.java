
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PcDanDanRecycleModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class PcDanDanRecycleAdapter extends CommonAdapter<PcDanDanRecycleAdapter.MyHolder,PcDanDanRecycleModel> {
    private ArrayList<PcDanDanRecycleModel> selecterList=new ArrayList<>();
    public PcDanDanRecycleAdapter(ArrayList<PcDanDanRecycleModel> list, ArrayList<PcDanDanRecycleModel> selecterList) {
        super(list);
        this.selecterList = selecterList;
    }
    @Override
    public void handleViewHolder(final MyHolder commonHolder, final int position) {
        final PcDanDanRecycleModel itemModel = getItemModel(position);
        commonHolder.betType.setText(itemModel.getBetType());
        commonHolder.rebate.setText(Utils.getString(R.string.赔)+itemModel.getRebate());
        if(itemModel.getStatus()==1){
           commonHolder.itemView.setSelected(true);
//            selecterList.add(itemModel);
        }else{
            commonHolder.itemView.setSelected(false);
//            selecterList.remove(itemModel);
        }
//        for (int i = 0; i < list.size(); i++) {
//            selecterList.add(i);
//        }
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!commonHolder.itemView.isSelected()){
//                   commonHolder.itemView.setSelected(true);
                   itemModel.setStatus(1);
                   selecterList.add(itemModel);
               }else {
//                   commonHolder.itemView.setSelected(false);
//                   selecterList.remove(position);
                   itemModel.setStatus(0);
                   selecterList.remove(itemModel);
               }
               if(mOnItemClickListener!=null){
                   mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
               }
//                System.out.println("selecterList.size()=   "+selecterList.size());
//                for (int i = 0; i < selecterList.size(); i++) {
//                    System.out.println(Utils.getString(R.string. selecterList 值    )+position);
//                }
                notifyDataSetChanged();
            }

        });
        commonHolder.itemView.setTag(position);
        //取消复用(避免切换玩法时,前一种玩法界面中选中的item,有一定几率在当前界面也显示选中)
         commonHolder.setIsRecyclable(false);
        //另一个解决方法:给medol设置一个标识(Status),在每次切换玩法时,将之前点击过的所有item(或者全部item)对应的medol中的标识(Status)设为默认值,
        // 最后通过status判断item的选中情况(有时间再优化)

    }

    @Override
    public int getLayOutRes() {
        return R.layout.pcdandan_recycle_item;
    }
    public static class MyHolder extends CommonHolder {
        TextView betType;
        TextView rebate;
        LinearLayout linearLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            betType=itemView.findViewById(R.id.bet_type);
            rebate=itemView.findViewById(R.id.bet_rebate);
            linearLayout=itemView.findViewById(R.id.pc_item_wrap);
        }
    }
}
