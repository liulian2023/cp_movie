

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class ShoppingCoontentTypeTwoAdapter extends CommonAdapter<ShoppingCoontentTypeTwoAdapter.MyHolder, ShoppingContentChessModel.ChessEntity.DataBean> {
    Context context;
    public ShoppingCoontentTypeTwoAdapter(ArrayList<ShoppingContentChessModel.ChessEntity.DataBean> list, Context context ) {
        super(list);
        this.context=context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ShoppingContentChessModel.ChessEntity.DataBean itemModel = getItemModel(position);
        commonHolder.xianIv.setVisibility(View.GONE);
        commonHolder.qishuCount.setVisibility(View.GONE);
        Glide.with(context)
                .load(Utils.checkImageUrl(itemModel.getImage()))
                .into(commonHolder.lolletyImage);
            commonHolder.lolletyName.setText(itemModel.getTypename());
        commonHolder.itemView.setTag(position);
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener !=null){
                    itemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.all_tollery_recycleview_two;
    }
    public static interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public ItemClickListener itemClickListener = null;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static class MyHolder extends CommonHolder {
        ImageView lolletyImage;
        TextView lolletyName;
        TextView qishuCount;
        ImageView xianIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lolletyImage=  itemView.findViewById(R.id.buy_lottety_image);
            lolletyName=  itemView.findViewById(R.id.buy_lottety_name);
            qishuCount=  itemView.findViewById(R.id.qishu_count);
            xianIv=  itemView.findViewById(R.id.xian_iv);

        }
    }
}
