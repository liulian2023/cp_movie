

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import androidx.annotation.NonNull;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Lottety;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class AllLotteryAdapterTwo extends CommonAdapter<AllLotteryAdapterTwo.MyHolder, Lottety> {
    Context context;
    public AllLotteryAdapterTwo(ArrayList<Lottety> list,Context context) {
        super(list);
        this.context=context;
    }
    //ShoppingContentChessAdapter
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        Lottety itemModel = getItemModel(position);
        Glide.with(context)
                .load(itemModel.getImageId())
                .into(commonHolder.lolletyImage);
        commonHolder.lolletyName.setText(itemModel.getName());

            commonHolder.qishuCount.setVisibility(View.GONE);//期数不显示
            commonHolder.qishuCount.setText(itemModel.getQishuCount());
        if(itemModel.isXian()){
            commonHolder.xianIv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.xianIv.setVisibility(View.GONE);

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
        void onItemClick(View view, int position );
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
