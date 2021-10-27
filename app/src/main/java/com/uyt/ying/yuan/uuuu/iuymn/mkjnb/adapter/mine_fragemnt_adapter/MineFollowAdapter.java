package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class MineFollowAdapter extends CommonAdapter<MineFollowAdapter.MyHolder, LiveEntity.AnchorFollowListBean> {
    Activity activity;

    public MineFollowAdapter(ArrayList<LiveEntity.AnchorFollowListBean> list, Activity activity) {
        super(list);
        this.activity = activity;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        LiveEntity.AnchorFollowListBean itemModel = getItemModel(position);
        if(itemModel.getIsPrivate()==0){
            GlideLoadViewUtil.LoadCircleView(activity, Utils.checkImageUrl(itemModel.getHeadImg()),commonHolder.titleIv);//头像
        }else {
            GlideLoadViewUtil.LoadCircleView(activity, Utils.checkLiveImageUrl(itemModel.getHeadImg()),commonHolder.titleIv);//头像

        }
        commonHolder.nameTv.setText(itemModel.getAnchorNickName());//主播名
        commonHolder.idTv.setText("ID:"+itemModel.getAnchorAccount()+"");//主播id
        if(itemModel.getStatus().equals("1")){//是否在直播
            commonHolder.liveIv.setVisibility(View.VISIBLE);
        }else {
            commonHolder.liveIv.setVisibility(View.INVISIBLE);
        }
        ImageView followIv = commonHolder.followIv;
        if(itemModel.getIsFollow()==1){//是否关注
            followIv.setImageResource(R.drawable.alrady_follow);
        }else {
            followIv.setImageResource(R.drawable.follow_img);
        }
        followIv.setTag(position);
        commonHolder.itemView.setTag(position);
        followIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
                }
            }
        });
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.mine_follow_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        ImageView titleIv;
        TextView nameTv;
        TextView idTv;
        ImageView liveIv;
        ImageView followIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            titleIv = itemView.findViewById(R.id.mine_follow_title_iv);
            nameTv = itemView.findViewById(R.id.mine_follow_name_tv);
            idTv = itemView.findViewById(R.id.mine_follow_id_tv);
            liveIv = itemView.findViewById(R.id.mine_follow_live_iv);
            followIv = itemView.findViewById(R.id.mine_follow_isFollow_iv);

        }
    }
}
