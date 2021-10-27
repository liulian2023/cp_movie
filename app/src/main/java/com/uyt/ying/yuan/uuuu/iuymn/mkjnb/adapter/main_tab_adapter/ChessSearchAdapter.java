package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessSearchEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class ChessSearchAdapter extends CommonAdapter<ChessSearchAdapter.MyHolder, ChessSearchEntity.DataBean> {
    Activity activity;

    public ChessSearchAdapter(ArrayList<ChessSearchEntity.DataBean> list, Activity activity) {
        super(list);
        this.activity = activity;
    }


    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ChessSearchEntity.DataBean itemModel = getItemModel(position);
        if(itemModel.isXian()){
            commonHolder.xian_iv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.xian_iv.setVisibility(View.GONE);

        }
        GlideLoadViewUtil.LoadNormalView(activity, Utils.checkImageUrl(itemModel.getImage()),commonHolder.imageView);
        commonHolder.textView.setText(itemModel.getTypename());
        View itemView = commonHolder.itemView;
        itemView.setTag(position);
        itemView.setOnClickListener(this);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.chess_search_recycler_item;
    }

    public static class  MyHolder extends CommonHolder {
        ImageView imageView;
        ImageView xian_iv;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.chess_search_iv);
            textView = itemView.findViewById(R.id.chess_search_name_tv);
            xian_iv = itemView.findViewById(R.id.xian_iv);

        }
    }
}
