package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Reward;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

/**
 * 首页中奖信息adapter
 */
public class GetWinnerAdapter1 extends RecyclerView.Adapter<GetWinnerAdapter1.MyHolder> implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ArrayList<Reward> rewardArrayList =new ArrayList<>();
    private Context mContext;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public GetWinnerAdapter1(ArrayList<Reward> rewardArrayList, Context mContext) {
        this.rewardArrayList=rewardArrayList;
        this.mContext = mContext;
    }
    private GetWinnerAdapter1.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public GetWinnerAdapter1.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new GetWinnerAdapter1.MyHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new GetWinnerAdapter1.MyHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_winner, viewGroup, false);
            GetWinnerAdapter1.MyHolder holder = new GetWinnerAdapter1.MyHolder(view);
            view.setOnClickListener(this);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            Glide.with(mContext)
                    .load(rewardArrayList.get(position).getImgIdTop())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(holder.imgIdTop);

            Glide.with(mContext)
                    .load(rewardArrayList.get(position).getImageIdBottom())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(holder.imageIdBottom);

                holder.name.setText(rewardArrayList.get(position).getName());
                holder.time.setText(rewardArrayList.get(position).getTime());
                String money = rewardArrayList.get(position).getMoney();
                String finalStr1= Utils.getString(R.string.我在)+rewardArrayList.get(position).getLotteryName()+Utils.getString(R.string.喜中了)+"<font color=\"#FF0000\">"+money+"</font>"+Utils.getString(R.string.元);
                holder.money.setText(Html.fromHtml(finalStr1));
                holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        int count = (rewardArrayList == null ? 0 : rewardArrayList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }
    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    public void setOnItemClickListener(GetWinnerAdapter1.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgIdTop;
        ImageView imageIdBottom;
        TextView name;
        TextView time;
        TextView money;

        public MyHolder( View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.winner_time);
            name = itemView.findViewById(R.id.winner_name);
            money = itemView.findViewById(R.id.winner_content);
            imgIdTop = itemView.findViewById(R.id.winner_img);
            imageIdBottom = itemView.findViewById(R.id.winner_type);
        }
    }
}
