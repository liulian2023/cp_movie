package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.InviteCode;

import java.util.ArrayList;

public class InviteCodeRecylceAdapter extends RecyclerView.Adapter<InviteCodeRecylceAdapter.MyHolder>implements View.OnClickListener {
    ArrayList<InviteCode> inviteCodeArrayList = new ArrayList<>();

    public InviteCodeRecylceAdapter(ArrayList<InviteCode> inviteCodeArrayList) {
        this.inviteCodeArrayList = inviteCodeArrayList;
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
        void onItemClick(View view,int position);
    }
    private InviteCodeRecylceAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
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
    public int getItemCount() {
        int count = (inviteCodeArrayList == null ? 0 : inviteCodeArrayList.size());
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

    @NonNull
    @Override
    public InviteCodeRecylceAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invite_code_recycle_item, viewGroup, false);
            MyHolder myHolder = new MyHolder(view);
//            view.setOnClickListener(this);
            return myHolder;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull InviteCodeRecylceAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
                myHolder.createdDate.setText( inviteCodeArrayList.get(position).getCreatedDate());
                myHolder.inviteCode.setText( inviteCodeArrayList.get(position).getInviteCode());
                myHolder.num.setText( inviteCodeArrayList.get(position).getNum());
                myHolder.showReturn.setOnClickListener(this);
                myHolder.showReturn.setTag(position);
                myHolder.copyInviteUrl.setOnClickListener(this);
                myHolder.copyInviteUrl.setTag(position);
                myHolder.copyInviteCode.setOnClickListener(this);
                myHolder.copyInviteCode.setTag(position);
                myHolder.imageView.setOnClickListener(this);
                myHolder.imageView.setTag(position);

//              myHolder.itemView.setTag(position);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView inviteCode;
        private TextView createdDate;
        private TextView num;
        private TextView copyInviteCode;
        private TextView copyInviteUrl;
        private TextView showReturn;
        private ImageView imageView;
        private ImageView deleteImg;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            inviteCode=itemView.findViewById(R.id.invite_code);
            createdDate=itemView.findViewById(R.id.code_create_date);
            num=itemView.findViewById(R.id.register_num);
            copyInviteCode=itemView.findViewById(R.id.copy_invite_code);
            copyInviteUrl=itemView.findViewById(R.id.copy_invite_url);
            showReturn=itemView.findViewById(R.id.show_return);
            imageView=itemView.findViewById(R.id.delete_img);
            deleteImg=itemView.findViewById(R.id.delete_img);

        }
    }
}
