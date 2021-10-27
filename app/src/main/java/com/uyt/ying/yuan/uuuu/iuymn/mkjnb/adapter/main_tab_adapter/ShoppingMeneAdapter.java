package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MenuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;

import java.util.ArrayList;

public class ShoppingMeneAdapter extends CommonAdapter<ShoppingMeneAdapter.MyHolder, MenuModel.ChessGameListBean> {
    Context context;

    public ShoppingMeneAdapter(ArrayList<MenuModel.ChessGameListBean> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        MenuModel.ChessGameListBean itemModel = getItemModel(position);
        TextView menu_name_tv = commonHolder.menu_name_tv;
        ImageView menu_big_bg_iv = commonHolder.menu_big_bg_iv;
        ImageView menu_name_iv = commonHolder.menu_name_iv;
        if(itemModel.isSelector()){
//            commonHolder.menu_left_tv.setVisibility(View.VISIBLE);
            menu_name_tv.setSelected(true);
            menu_name_tv.setTextColor(Color.WHITE);
            menu_big_bg_iv.setBackgroundResource(R.drawable.gamehall_menu_item_s);
        }else {
//            commonHolder.menu_left_tv.setVisibility(View.GONE);
            menu_name_tv.setSelected(false);
            menu_name_tv.setTextColor(Color.parseColor("#99366A"));
            menu_big_bg_iv.setBackgroundResource(R.drawable.gamehall_menu_icon_n);
        }
        String image = itemModel.getImage();
        if(StringMyUtil.isNotEmpty(image)){
            GlideLoadViewUtil.LoadNormalView(context, image,menu_name_iv);
        }else {
            menu_name_iv.setImageResource(R.drawable.gamehall_recommad_icon);
        }
        menu_name_tv.setText(itemModel.getName2());
        View itemView = commonHolder.itemView;
        menu_big_bg_iv.setTag(position);
        menu_big_bg_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    MenuModel.ChessGameListBean chessGameListBean = list.get(i);
                    if(i==position){
                        chessGameListBean.setSelector(true);
                    }else {
                        chessGameListBean.setSelector(false);
                    }
                }
                notifyDataSetChanged();
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.shopping_menu_recycler_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView menu_name_tv;
        ImageView menu_big_bg_iv;
        ImageView menu_name_iv;
//        TextView menu_left_tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            menu_name_tv = itemView.findViewById(R.id.menu_name_tv);
            menu_big_bg_iv = itemView.findViewById(R.id.menu_big_bg_iv);
            menu_name_iv = itemView.findViewById(R.id.menu_name_iv);
//            menu_left_tv = itemView.findViewById(R.id.menu_left_tv);
        }
    }
}
