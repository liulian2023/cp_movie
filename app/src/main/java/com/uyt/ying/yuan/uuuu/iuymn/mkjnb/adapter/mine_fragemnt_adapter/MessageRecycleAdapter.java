package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MessageModel;

import java.util.ArrayList;

public class MessageRecycleAdapter extends RecyclerView.Adapter<MessageRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<MessageModel> messageModelArrayList=new ArrayList<>();

    public MessageRecycleAdapter(ArrayList<MessageModel> messageModelArrayList) {
        this.messageModelArrayList = messageModelArrayList;
    }
    private RecyclerView mRecyclerView;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    private MessageRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @NonNull
    @Override
    public MessageRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_message_recycle_item, viewGroup, false);
            MyHolder holder = new MyHolder(view);
            view.setOnClickListener(this);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRecycleAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            if(position%4==0){
                myHolder.linearLayout.setBackgroundResource(R.drawable.ac_icon1);
            }else if(position%4==1){
                myHolder.linearLayout.setBackgroundResource(R.drawable.ac_icon2);
            }
            else if(position%4==2){
                myHolder.linearLayout.setBackgroundResource(R.drawable.ac_icon3);
            }
            else if(position%4==3){
                myHolder.linearLayout.setBackgroundResource(R.drawable.ac_icon4);
            }
            if(messageModelArrayList.get(position).getIsRead()==0){
                myHolder.redCicle.setVisibility(View.VISIBLE);
            }else{
                myHolder.redCicle.setVisibility(View.GONE);
            }
            myHolder.num.setText(position+1+"");
            myHolder.title.setText(messageModelArrayList.get(position).getTitle());
            myHolder.content.setText(messageModelArrayList.get(position).getContent());
            myHolder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        int count = (messageModelArrayList == null ? 0 : messageModelArrayList.size());
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


    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView num;
        TextView title;
        TextView content;
        TextView redCicle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.message_background_linear);
            num=itemView.findViewById(R.id.message_num);
            title=itemView.findViewById(R.id.message_title);
            content=itemView.findViewById(R.id.message_content);
            redCicle=itemView.findViewById(R.id.red_cicle);
        }
    }
}
