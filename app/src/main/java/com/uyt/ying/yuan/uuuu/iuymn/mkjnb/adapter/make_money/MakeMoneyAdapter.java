package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MakeMoneyModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class MakeMoneyAdapter extends CommonAdapter<MakeMoneyAdapter.MyHolder, MakeMoneyModel> {
    public MakeMoneyAdapter(ArrayList<MakeMoneyModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        MakeMoneyModel itemModel = getItemModel(position);
        ImageView imageView = commonHolder.imageView;
        TextView remarkTv = commonHolder.remarkTv;
        TextView remark2tv = commonHolder.remark2tv;
        TextView typeTv = commonHolder.typeTv;
        ImageView questionIv = commonHolder.questionIv;
        typeTv.setText(itemModel.getType());
        remarkTv.setText(itemModel.getRemark());
        imageView.setImageResource(itemModel.getImgId());
        switch (position){
            case 0:
            case 1:
                remark2tv.setVisibility(View.VISIBLE);
            break;
            case 4:
            case 5:
            case 2:
            case 3:
                remark2tv.setVisibility(View.GONE);
                break;
                    default:
                break;
        }
        if(position==1){
            questionIv.setVisibility(View.VISIBLE);
        }else {
            questionIv.setVisibility(View.GONE);
        }
        questionIv.setTag(position);
        questionIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.make_money_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        ImageView imageView;
        TextView typeTv;
        TextView remarkTv;
        TextView remark2tv;
        ImageView questionIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.make_money_iv);
            typeTv = itemView.findViewById(R.id.make_money_type_tv);
            remarkTv = itemView.findViewById(R.id.make_money_remark_tv);
            remark2tv = itemView.findViewById(R.id.make_money_remark2_tv);
            questionIv = itemView.findViewById(R.id.question_iv);
        }
    }
}
