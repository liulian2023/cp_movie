package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.InviteRedModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class InviteRedAdapter extends CommonAdapter<InviteRedAdapter.MyHolder, InviteRedModel> {
    public InviteRedAdapter(ArrayList<InviteRedModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        InviteRedModel itemModel = getItemModel(position);
        commonHolder.effective_person_num_tv.setText(itemModel.getPeoperNum()+"");
        TextView left_dark_tv = commonHolder.left_dark_tv;
        ViewGroup.LayoutParams leftLp = left_dark_tv.getLayoutParams();
        leftLp.width=Utils.dp2px(itemModel.leftProgress*38);
        left_dark_tv.setLayoutParams(leftLp);
        TextView right_dark_tv = commonHolder.right_dark_tv;
        ViewGroup.LayoutParams rightLp = right_dark_tv.getLayoutParams();
        rightLp.width=Utils.dp2px(itemModel.rightProgress*38);
        right_dark_tv.setLayoutParams(rightLp);
        ImageView get_red_iv = commonHolder.get_red_iv;
        if(itemModel.isOpened){
            get_red_iv.setImageResource(R.drawable.lq_ed);
        }else {
            if(itemModel.canOpen){
                get_red_iv.setImageResource(R.drawable.lq);
            }else {
                get_red_iv.setImageResource(R.drawable.lq_un);
            }
        }
        commonHolder.red_num_tv.setText("x"+itemModel.redCount);
        get_red_iv.setTag(position);
        get_red_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.effective_invite_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView effective_person_num_tv;
        TextView left_light_tv;
        TextView left_dark_tv;
        TextView right_light_tv;
        TextView right_dark_tv;
        ImageView get_red_iv;
        TextView red_num_tv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            effective_person_num_tv=itemView.findViewById(R.id.effective_person_num_tv);
            left_light_tv=itemView.findViewById(R.id.left_light_tv);
            left_dark_tv=itemView.findViewById(R.id.left_dark_tv);
            right_light_tv=itemView.findViewById(R.id.right_light_tv);
            right_dark_tv=itemView.findViewById(R.id.right_dark_tv);
            get_red_iv=itemView.findViewById(R.id.get_red_iv);
            red_num_tv=itemView.findViewById(R.id.red_num_tv);
        }
    }
}
