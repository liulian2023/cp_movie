
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class ClassFyAdapterTwo extends CommonAdapter<ClassFyAdapterTwo.MyHolder, LotteryClassModel> {
    private Context context;

    public ClassFyAdapterTwo(ArrayList<LotteryClassModel> list, Context context) {
        super(list);

        this.context = context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        LotteryClassModel itemModel = getItemModel(position);
        if(itemModel.getStatus()==1){
            commonHolder.lolletyName.setChecked(true);
        }else{
            commonHolder.lolletyName.setChecked(false);
        }

        commonHolder.lolletyName.setText(itemModel.getTypename());
        if(itemModel.isXian()){
            commonHolder.xianIv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.xianIv.setVisibility(View.GONE);
        }
        commonHolder.lolletyName.setTag(position);
        commonHolder.lolletyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked(commonHolder,position);
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }
    private void checked(@NonNull MyHolder contentViewHodler, int position) {
        for (int a = 0; a< list.size(); a++){
            LotteryClassModel lotteryClassModel = list.get(a);
            if(a==position) {
                lotteryClassModel.setStatus(1);
            }else{
                lotteryClassModel.setStatus(0);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getLayOutRes() {
        return R.layout.lottery_classify_recycle_two_item;
    }

    public static class MyHolder extends CommonHolder {
        RadioButton lolletyName;
        ImageView xianIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lolletyName= itemView.findViewById(R.id.lottery_classify_lottery_name);
            xianIv= itemView.findViewById(R.id.xian_iv);
        }
    }
}
