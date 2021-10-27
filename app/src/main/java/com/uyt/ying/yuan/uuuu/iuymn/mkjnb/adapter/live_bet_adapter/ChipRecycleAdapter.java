package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_bet_adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChipModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class ChipRecycleAdapter extends CommonAdapter<ChipRecycleAdapter.MyHolder, ChipModel> {
    int chipNum;//用户上次选择的筹码缓存数据
    int editChipNum;//用户输入的自定义筹码缓存数据

    public ChipRecycleAdapter(ArrayList<ChipModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ChipModel itemModel = getItemModel(position);
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        TextView nowChipTv = commonHolder.nowChipTv;
        ImageView chipIv = commonHolder.chipIv;
        String customAmout = sp.getCustomAmout();//自定义金额
        String currentAmout = sp.getCurrentAmout();//上次选中的金额
        boolean isCustomChip = sp.getIsCustomChip();//上次选中的金额是否是自定义
        boolean current = itemModel.isCurrent();
        TextView amountTv = commonHolder.amountTv;
        String amount = itemModel.getAmount();
        amountTv.setText(amount);
        if(amount.length()>=4){
            if(amount.length()==4){
                amountTv.setTextSize(12);
            }else {
                amountTv.setTextSize(10);
            }
        }else {
            if(amount.equals(Utils.getString(R.string.自定义))){
                amountTv.setTextSize(10);
            }else{
                amountTv.setTextSize(14);

            }
        }
        chipIv.setImageResource(itemModel.getDrawableId());
        if(itemModel.isCheck()){
            commonHolder.backgroundIv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.backgroundIv.setVisibility(View.INVISIBLE);
        }
        if(itemModel.isCustom()){//当前item是自定义筹码
            if(customAmout.equals(Utils.getString(R.string.自定义))){
                nowChipTv.setText("");
            }else {
                if(current){
                    nowChipTv.setText(Utils.getString(R.string.当前筹码));
                    nowChipTv.setTextColor(Color.WHITE);
                }else {
                    nowChipTv.setText(Utils.getString(R.string.修改筹码 ));
                    nowChipTv.setTextColor(Color.parseColor("#F8D047"));
                }
            }
        }else {//不是自定义筹码
            nowChipTv.setTextColor(Color.WHITE);
            if(current){
                nowChipTv.setText(Utils.getString(R.string.当前筹码));
            }else {
                nowChipTv.setText("");
            }
        }

        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemModel.isCustom()&&(itemModel.getAmount().equals(Utils.getString(R.string.自定义))||itemModel.isCheck())){
                    if(onShowPopLintener!=null){
                        //弹窗回调
                        onShowPopLintener.onShowPop(itemModel);
                    }
                }else {
                    initCheck(position);
                }

        /*        if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }*/
            }
        });
        commonHolder.itemView.setTag(position);
    }

    private void initCheck(int position) {
        for (int i = 0; i < list.size(); i++) {
            ChipModel chipModel = list.get(i);
            chipModel.setCheck(false);
            if(position==i){
                chipModel.setCheck(true);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getLayOutRes() {
        return R.layout.choose_chip_recycle_item_layout;
    }

    public static class MyHolder extends CommonHolder {
        ImageView backgroundIv;
        ImageView chipIv;
        TextView amountTv;
        TextView nowChipTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            backgroundIv=itemView.findViewById(R.id.choose_chip_background_iv);
            chipIv=itemView.findViewById(R.id.chip_iv);
            amountTv=itemView.findViewById(R.id.chip_amount_tv);
            nowChipTv=itemView.findViewById(R.id.now_choose_chip_tv);
        }
    }
    public interface  OnShowPopLintener{
        void onShowPop(ChipModel chipModel);
    }
    OnShowPopLintener onShowPopLintener=null;

    public void setOnShowPopLintener(OnShowPopLintener onShowPopLintener) {
        this.onShowPopLintener = onShowPopLintener;
    }
}
