package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommissionPlanModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;

import java.util.ArrayList;

public class CommisionPlanAdapter extends CommonAdapter<CommisionPlanAdapter.MyHolder, CommissionPlanModel.CodeListBean> {
    public CommisionPlanAdapter(ArrayList<CommissionPlanModel.CodeListBean> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        CommissionPlanModel.CodeListBean itemModel = getItemModel(position);
        TextView show_more_tv = commonHolder.show_more_tv;
        TextView fandian_tv = commonHolder.fandian_tv;
        TextView invite_code_tv = commonHolder.invite_code_tv;
        TextView invite_num_tv = commonHolder.invite_num_tv;
        TextView time_tv = commonHolder.time_tv;
        ImageView selector_iv = commonHolder.selector_iv;
        ConstraintLayout commision_plan_item_contentView = commonHolder.commision_plan_item_contentView;
        invite_code_tv.setText(itemModel.getInviteCode());//邀请码
        fandian_tv.setText(itemModel.getK3Rate()+"");//返点(默认显示快三)
        invite_num_tv.setText(itemModel.getNum()+"");//注册人数
        time_tv.setText(DateUtil.stampToDate(itemModel.getCreatedDate()+""));//创建时间

        if(itemModel.getInCheck()==1){
            selector_iv.setVisibility(View.VISIBLE);
        }else {
            selector_iv.setVisibility(View.INVISIBLE);
        }
        commision_plan_item_contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelector(v,(Integer) v.getTag());
            }
        });
        selector_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelector(v,(Integer) v.getTag());
            }
        });
        show_more_tv.setOnClickListener(this);
        show_more_tv.setTag(position);
        commision_plan_item_contentView.setTag(position);
        selector_iv.setTag(position);
    }

    private void initSelector(View view,int position) {
        for (int i = 0; i < list.size(); i++) {
            CommissionPlanModel.CodeListBean codeListBean = list.get(i);
            if(position==i){
                codeListBean.setInCheck(1);
            }else {
                codeListBean.setInCheck(0);
            }
        }
        notifyDataSetChanged();
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(view,position);
        }
    }

    @Override
    public int getLayOutRes() {
        return R.layout.commsion_plan_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView invite_num_tv;
        TextView invite_code_tv;
        TextView time_tv;
        TextView fandian_tv;
        TextView show_more_tv;
        ImageView selector_iv;
        ConstraintLayout commision_plan_item_contentView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            invite_num_tv=itemView.findViewById(R.id.invite_num_tv);
            invite_code_tv=itemView.findViewById(R.id.invite_code_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            fandian_tv=itemView.findViewById(R.id.fandian_tv);
            show_more_tv=itemView.findViewById(R.id.show_more_tv);
            selector_iv=itemView.findViewById(R.id.selector_iv);
            commision_plan_item_contentView=itemView.findViewById(R.id.commision_plan_item_contentView);
        }
    }
}
