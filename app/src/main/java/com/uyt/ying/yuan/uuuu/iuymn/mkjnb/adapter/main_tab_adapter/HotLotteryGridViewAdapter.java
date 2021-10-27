package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeGridView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.ArrayList;

public class HotLotteryGridViewAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<HomeGridView> homeGridViews = new ArrayList<>();
    private Context context;
    public HotLotteryGridViewAdapter(ArrayList<HomeGridView> homeGridViews, Context context) {
        this.homeGridViews = homeGridViews;
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private HotLotteryGridViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(HotLotteryGridViewAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public int getCount() {
        return homeGridViews.size();
    }

    @Override
    public Object getItem(int position) {
        return homeGridViews.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
   ViewHolder viewHolder;
        HomeGridView homeGridView = homeGridViews.get(position);
        if(convertView == null){
               convertView = LayoutInflater.from(context).inflate(R.layout.home_gridview_item,null);
               viewHolder = new ViewHolder();
               viewHolder.LotteryCount =convertView.findViewById(R.id.hot_count);
               viewHolder.LotteryType =convertView.findViewById(R.id.hot_content);
               viewHolder.LotteryImage =convertView.findViewById(R.id.hot_img);
                   viewHolder.LotteryCount.setVisibility(View.VISIBLE);
                   viewHolder.LotteryCount.setText(homeGridView.getCount());
               convertView.setTag(viewHolder);
           }else {
               viewHolder = (ViewHolder) convertView.getTag();
           }
        viewHolder.LotteryType.setText(homeGridView.getLottoeryName());
        if(StringMyUtil.isEmptyString(homeGridView.getImageUrl())){
            if(homeGridView.getLottoeryName().equals(Utils.getString(R.string.更多彩票))){
                GlideLoadViewUtil.LoadNormalView(context, Utils.getHomeLogo("liveIcon8"), viewHolder.LotteryImage);
            }
        }else {
            Glide.with(context)
                    .load(homeGridView.getImageUrl())
                    .into(viewHolder.LotteryImage);
        }

        viewHolder.LotteryImage.setOnClickListener(this);
        viewHolder.LotteryImage.setTag(position);
        return convertView;
    }

    private class  ViewHolder{
   ImageView LotteryImage;
   TextView LotteryType;
   TextView LotteryCount;
    }
}
