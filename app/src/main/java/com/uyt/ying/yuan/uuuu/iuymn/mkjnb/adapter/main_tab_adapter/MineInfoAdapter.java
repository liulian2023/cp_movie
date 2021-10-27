package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class MineInfoAdapter extends CommonAdapter<MineInfoAdapter.MyHolder, MineInfoEntity> {

    public MineInfoAdapter(ArrayList<MineInfoEntity> mineInfoEntities) {
        super(mineInfoEntities);

    }


    @Override
    public int getLayOutRes() {
        return R.layout.mine_recycleview_item;
    }


    @Override
    public void handleViewHolder(final MyHolder holder, int position) {
        final MineInfoEntity itemModel = getItemModel(position);
        holder.imageId.setImageResource(itemModel.getImageId());
        holder.textView.setText(itemModel.getRemark());
        holder.textView1.setVisibility(View.GONE);
        if(itemModel.getRemark().endsWith(Utils.getString(R.string.我的消息))){
            if(itemModel.getMessageNum()!=0l){
                holder.textView1.setText(itemModel.getMessageNum()+"");
                holder.textView1.setVisibility(View.VISIBLE);
            }else{
                holder.textView1.setVisibility(View.GONE);
            }
        }
//        final boolean selected = holder.itemView.isSelected();
        final boolean activated = holder.itemView.isActivated();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(!activated){
////                    holder.itemView.setSelected(true);
//                    holder.itemView.setActivated(true);
//                }else{
////                    holder.itemView.setSelected(false);
//                    holder.itemView.setActivated(false);
//                }
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        holder.itemView.setTag(position);
    }


    public static class MyHolder extends CommonHolder {
        TextView textView;
        ImageView imageId;
        ImageView rightHot;
        MyTextView textView1;
        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.mine_text);
            imageId = itemView.findViewById(R.id.mine_image);
            rightHot = itemView.findViewById(R.id.mine_right);
            textView1 = itemView.findViewById(R.id.un_read_tip_tv);
        }


        }

}

