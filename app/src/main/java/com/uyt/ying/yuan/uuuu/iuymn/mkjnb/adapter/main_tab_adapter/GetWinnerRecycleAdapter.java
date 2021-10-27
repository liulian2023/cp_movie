package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetWinnerMedol;


import java.util.ArrayList;

public class GetWinnerRecycleAdapter extends RecyclerView.Adapter<GetWinnerRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<GetWinnerMedol>getWinnerMedolArrayList=new ArrayList<>();
    Context context;
    public GetWinnerRecycleAdapter(ArrayList<GetWinnerMedol> getWinnerMedolArrayList,Context context) {
        this.getWinnerMedolArrayList = getWinnerMedolArrayList;
        this.context=context;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (GetWinnerMedol) v.getTag());
        }
    }

    public  static interface OnRecyclerViewItemClickListener{
        /*
        点击回调中直接将当前第一次onBindView时对应position的medel回调出去,(因为在fragment中有循环增减的操作,且需要保存增减动画,不能使用notifyDataChanged
        如果只回调position,那么增减操作进行结束后,由于无法调用otifyDataChanged,position没有全部更新,tag没有重新设置,
        在fragment中通过position从list中拿数据的话,拿到的model是增减操作之前的position对应的model,导致点击跳转用户信息时,数据出错
         */
        void onItemClick(View view ,GetWinnerMedol getWinnerMedol);
    }
    private GetWinnerRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(GetWinnerRecycleAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @NonNull
    @Override
    public GetWinnerRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.get_winner_fragment_recycle_item,viewGroup,false);
       //onCreateViewHolder中就设置点击回调,tag则在onBindView中设置,保证每个position对应的model都是唯一的,不会受fragment中循环增减操作的影响
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetWinnerRecycleAdapter.MyHolder myHolder, int i) {


        GetWinnerMedol getWinnerMedol = getWinnerMedolArrayList.get(i);

        Glide.with(context)
                .load(getWinnerMedol.getImageURl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into( myHolder.imageView);
        myHolder.lotteryName.setText(getWinnerMedol.getLotteryName());
        myHolder.nickNime.setText(getWinnerMedol.getNickName());
        myHolder.price.setText("¥"+getWinnerMedol.getPrice());
        //将当前position对应的model设置为tag
        myHolder.wrapLinear.setTag(getWinnerMedol);
//        myHolder.wrapLinear.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return getWinnerMedolArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nickNime;
        TextView lotteryName;
        TextView price;
        LinearLayout wrapLinear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.get_winner_img);
            nickNime=itemView.findViewById(R.id.get_winner_nickname);
            lotteryName=itemView.findViewById(R.id.get_winner_lottery_name);
            price=itemView.findViewById(R.id.get_winner_price);
            wrapLinear=itemView.findViewById(R.id.getwinner_wrap_linear);
        }
    }
}
