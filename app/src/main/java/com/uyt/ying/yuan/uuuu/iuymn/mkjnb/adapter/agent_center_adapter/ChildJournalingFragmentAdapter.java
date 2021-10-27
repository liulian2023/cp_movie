package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildJournaling;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ChildJournalingFragmentAdapter extends RecyclerView.Adapter<ChildJournalingFragmentAdapter.MyHolder> implements View.OnClickListener {

    private ArrayList<ChildJournaling> childJournalingArrayList =new ArrayList<>();
    private RecyclerView mRecyclerView;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    public ChildJournalingFragmentAdapter(ArrayList<ChildJournaling> childJournalingArrayList) {
        this.childJournalingArrayList = childJournalingArrayList;
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);
    }
    private ChildJournalingFragmentAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
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

    @Override
    public int getItemCount() {
        int count = (childJournalingArrayList == null ? 0 : childJournalingArrayList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }
    @NonNull
    @Override
    public ChildJournalingFragmentAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_journaling_fragment_item, viewGroup, false);
            view.setOnClickListener(this);
            MyHolder myHolder = new MyHolder(view);
//            view.setOnClickListener(this);
            return myHolder;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChildJournalingFragmentAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            myHolder.name.setText(childJournalingArrayList.get(position).getName());
            myHolder.level.setText(childJournalingArrayList.get(position).getLevel());
            myHolder.touzhu.setText(childJournalingArrayList.get(position).getTouzhu());
            String winOrLose = childJournalingArrayList.get(position).getWinOrLose();
            BigDecimal bigDecimal = new BigDecimal(winOrLose);
            if(bigDecimal.compareTo(BigDecimal.ZERO)==-1||bigDecimal.compareTo(BigDecimal.ZERO)==0){
                myHolder.winOrLose.setTextColor(Color.parseColor("#4A9E4A"));
            }else {
                myHolder.winOrLose.setTextColor(Color.RED);
            }
            myHolder.winOrLose.setText(winOrLose);
            myHolder.cz.setText(childJournalingArrayList.get(position).getCz());
            myHolder.tx.setText(childJournalingArrayList.get(position).getTx());
            myHolder.tz.setText(childJournalingArrayList.get(position).getTouzhu());
            myHolder.zj.setText(childJournalingArrayList.get(position).getZj());
            myHolder.gryj.setText(childJournalingArrayList.get(position).getGryj());
            myHolder.hdfd.setText(childJournalingArrayList.get(position).getHdfd());
            myHolder.gryk.setText(childJournalingArrayList.get(position).getWinOrLose());
            myHolder.teamList.setOnClickListener(this);
            myHolder.showByType.setOnClickListener(this);
            myHolder.itemView.setTag(position);
            myHolder.teamList.setTag(position);
            myHolder.showByType.setTag(position);
        }
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView level;
        TextView touzhu;
        TextView winOrLose;
        TextView cz;
        TextView tx;
        TextView tz;
        TextView zj;
        TextView gryj;
        TextView hdfd;
        TextView gryk;
        TextView teamList;
        TextView showByType;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nikeName);
            level=itemView.findViewById(R.id.level);
            touzhu=itemView.findViewById(R.id.touzhu);
            winOrLose=itemView.findViewById(R.id.winOrLose);
            cz=itemView.findViewById(R.id.cz);
            tx=itemView.findViewById(R.id.tx);
            tz=itemView.findViewById(R.id.tz);
            zj=itemView.findViewById(R.id.zj);
            gryj=itemView.findViewById(R.id.gryj);
            hdfd=itemView.findViewById(R.id.hdfd);
            gryk=itemView.findViewById(R.id.gryk);
            showByType=itemView.findViewById(R.id.lettory_show);
            teamList=itemView.findViewById(R.id.team_table);
        }
    }
}
