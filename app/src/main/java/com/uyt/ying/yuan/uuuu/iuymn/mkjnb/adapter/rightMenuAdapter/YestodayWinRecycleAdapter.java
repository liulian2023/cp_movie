package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YestodayWinMedol;


import java.util.ArrayList;

public class YestodayWinRecycleAdapter extends RecyclerView.Adapter<YestodayWinRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<YestodayWinMedol>yestodayWinMedolArrayList=new ArrayList<>();
    Context context;
    public YestodayWinRecycleAdapter(ArrayList<YestodayWinMedol> yestodayWinMedolArrayList,Context context) {
        this.yestodayWinMedolArrayList = yestodayWinMedolArrayList;
        this.context=context;
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
    private YestodayWinRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(YestodayWinRecycleAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @NonNull
    @Override
    public YestodayWinRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        MyHolder myHolder;
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yestoday_win_recycle_item_two,viewGroup,false);
            view.setOnClickListener(this);
            myHolder=new MyHolder(view);
        return myHolder;
    }
    /*
    根据position 判断viewType(多布局)
     */
    @Override
    public int getItemViewType(int position) {
        //跟据position对应的条目返回去对应的样式（Type）
        if(position ==0 ||position ==1||position ==2) {
            return 0;
        }
        else  {
            return 1;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull YestodayWinRecycleAdapter.MyHolder myHolder, int i) {
        int itemViewType = getItemViewType(i);//得到viewType
        if(itemViewType==0){
            if(i==0){
            myHolder.numText.setBackgroundResource(R.drawable.num_one);
            myHolder.numText.setText("");
            }
            else if(i==1){
                myHolder.numText.setBackgroundResource(R.drawable.num_two);
                myHolder.numText.setText("");
            }else if(i==2){
                myHolder.numText.setBackgroundResource(R.drawable.num_three);
                myHolder.numText.setText("");
            }
        }else {
            myHolder.numText.setText((i+1)+"");
        }


        YestodayWinMedol yestodayWinMedol = yestodayWinMedolArrayList.get(i);
        Glide.with(context)
                .load(yestodayWinMedol.getImageURl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(myHolder.imageView);
        myHolder.nickNime.setText(yestodayWinMedol.getNickName());
        myHolder.price.setText("¥"+yestodayWinMedol.getPrice());
        myHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return yestodayWinMedolArrayList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nickNime;
        TextView price;
        TextView numText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.yestoday_win_img);
            nickNime=itemView.findViewById(R.id.yestoday_win_nickname);
            price=itemView.findViewById(R.id.yestoday_win_price);
            numText=itemView.findViewById(R.id.yestoday_num);
        }
    }
}
