package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.user_info_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserImage;

import java.util.ArrayList;

public class UserImageAdapter extends RecyclerView.Adapter<UserImageAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<UserImage> murlList = new ArrayList<>();
    Context context;
    public UserImageAdapter(ArrayList<UserImage> urlList,Context context) {
        murlList = urlList;
        this.context=context;
    }

    private UserImageAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_image_recycle_item,viewGroup,false);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserImageAdapter.MyHolder myHolder, int i) {
        Glide.with(context)
                .load(murlList.get(i).getUrl())
                .into(myHolder.imageView);
        myHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return murlList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.user_image_itemImg);
        }
    }
}
