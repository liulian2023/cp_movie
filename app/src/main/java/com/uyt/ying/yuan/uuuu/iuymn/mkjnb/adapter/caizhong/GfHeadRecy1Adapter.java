/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GfHeadRecy1Adapter extends RecyclerView.Adapter<GfHeadRecy1Adapter.VH> {

    Context mContext;
    Fragment fragment;
    List<String>  list;
    int index_gf_x;

    public GfHeadRecy1Adapter(Context mContext, List<String> list, int index_gf_x) {
        this.mContext = mContext;
        this.list = list;
        this.index_gf_x = index_gf_x;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_headrecy1, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.ll_item_headrecy1.setTag(position);

        if (position==index_gf_x){
            holder.ll_item_headrecy1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            holder.item_headrecy1_tv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.ll_item_headrecy1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            holder.item_headrecy1_tv.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        }
        holder.item_headrecy1_tv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_headrecy1)
        LinearLayout ll_item_headrecy1;
        @BindView(R.id.item_headrecy1_tv)
        TextView item_headrecy1_tv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ll_item_headrecy1.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

                    index_gf_x = (int)v.getTag();
                    notifyDataSetChanged();
                    ((XuanwuActivity) mContext).OnHeadRecy1Listener(index_gf_x);

                }
            });

        }
    }
}

