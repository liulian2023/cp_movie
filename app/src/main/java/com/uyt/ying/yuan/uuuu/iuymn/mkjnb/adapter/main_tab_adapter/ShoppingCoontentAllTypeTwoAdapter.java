

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentRecommendModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class ShoppingCoontentAllTypeTwoAdapter extends CommonAdapter<ShoppingCoontentAllTypeTwoAdapter.MyHolder, ShoppingContentRecommendModel> {
    Context context;
    public ShoppingCoontentAllTypeTwoAdapter(ArrayList<ShoppingContentRecommendModel> list, Context context ) {
        super(list);
        this.context=context;
    }
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ShoppingContentRecommendModel itemModel = getItemModel(position);
        NavigateEntity.GameClassListBean lotteryDataBean = itemModel.getLotteryDataBean();
        ShoppingContentChessModel.ChessEntity.DataBean chessDataBean = itemModel.getChessDataBean();
        /*
        棋牌item
         */
        if(chessDataBean!=null){
            commonHolder.xianIv.setVisibility(View.GONE);
            commonHolder.qishuCount.setVisibility(View.GONE);
            Glide.with(context)
                    .load(Utils.checkImageUrl(chessDataBean.getImage()))
                    .into(commonHolder.lolletyImage);
            commonHolder.lolletyName.setText(chessDataBean.getTypename());
        }else {
            /*
            彩票item
             */
            Glide.with(context)
                    .load(Utils.checkImageUrl(lotteryDataBean.getImage()))
                    .into(commonHolder.lolletyImage);
            commonHolder.lolletyName.setText(lotteryDataBean.getTypename());
            commonHolder.qishuCount.setVisibility(View.GONE);//期数不显示
            commonHolder.qishuCount.setText(lotteryDataBean.getRemark());
            if(lotteryDataBean.isXian()){
                commonHolder.xianIv.setVisibility(View.VISIBLE);
            }else {
                commonHolder.xianIv.setVisibility(View.GONE);
            }

        }
        commonHolder.itemView.setTag(position);
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickLintener!=null){
                    itemClickLintener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.all_tollery_recycleview_two;
    }
    public static interface ItemClickLintener {
        void onItemClick(View view, int position);
    }

    public ItemClickLintener itemClickLintener = null;

    public void setItemClickLintener(ItemClickLintener itemClickLintener) {
        this.itemClickLintener = itemClickLintener;
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
