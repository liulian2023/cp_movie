package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.animation.Animator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.OpenInviteRedActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.OpenInviteRedModel;

import java.util.ArrayList;

public class OpenInviteAdapter extends RecyclerView.Adapter<OpenInviteAdapter.MyHolder>{
    ArrayList<OpenInviteRedModel>openInviteRedModelArrayList = new ArrayList<>();
    OpenInviteRedActivity openInviteRedActivity;
    MyHolder myHolder;
    public OpenInviteAdapter(ArrayList<OpenInviteRedModel> openInviteRedModelArrayList, OpenInviteRedActivity openInviteRedActivity) {
        this.openInviteRedModelArrayList = openInviteRedModelArrayList;
        this.openInviteRedActivity = openInviteRedActivity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.open_invite_red_recycle_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        this.myHolder=myHolder;
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder commonHolder, int position) {
        OpenInviteRedModel itemModel = openInviteRedModelArrayList.get(position);
        LottieAnimationView lottie_view = commonHolder.lottie_view;
        final  TextView red_amount_tv = commonHolder.red_amount_tv;
        final ImageView red_bg_iv = commonHolder.red_bg_iv;
        lottie_view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                red_bg_iv.setVisibility(View.GONE);
                red_amount_tv.setVisibility(View.GONE);
                lottie_view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                red_amount_tv.setVisibility(View.VISIBLE);
                red_bg_iv.setVisibility(View.VISIBLE);
                red_bg_iv.setImageResource(R.drawable.hongbao_kai);
                lottie_view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        red_amount_tv.setText(itemModel.getAmount()+"");
        if(itemModel.isOpened()){
            lottie_view.playAnimation();
        }else {
            red_amount_tv.setVisibility(View.GONE);
            red_bg_iv.setVisibility(View.VISIBLE);
            red_bg_iv.setImageResource(R.drawable.hb_da);
        }


    }
    public void  startAnimation(){

    }
    @Override
    public int getItemCount() {
        return openInviteRedModelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView red_bg_iv;
        TextView red_amount_tv;
        LottieAnimationView lottie_view;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            red_amount_tv=itemView.findViewById(R.id.red_amount_tv);
            red_bg_iv=itemView.findViewById(R.id.red_bg_iv);
            lottie_view=itemView.findViewById(R.id.lottie_view);
        }
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //define interface
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}
