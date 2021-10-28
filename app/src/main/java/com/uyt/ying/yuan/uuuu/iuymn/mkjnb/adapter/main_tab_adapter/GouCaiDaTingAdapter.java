package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GouCaiDaTingAdapter extends RecyclerView.Adapter<GouCaiDaTingAdapter.MyHolder> implements View.OnClickListener{
    private ArrayList<CountDown> countDowns = new ArrayList<>();
    Context context;
    public GouCaiDaTingAdapter(ArrayList<CountDown> countDowns,Context context) {
        this.countDowns = countDowns;
        this.context = context;
    }
    //define interface
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public  interface EndListener {
        void onEnd(String id,int index);
    }
    private EndListener endListener;
    public void setOnEndListener(EndListener endListener) {
        this.endListener = endListener;
    }


    @NonNull
    @Override

    public GouCaiDaTingAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goaucaidating_recycleview_item,viewGroup,false);
        MyHolder hoder = new MyHolder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GouCaiDaTingAdapter.MyHolder viewHolder, final int i) {
        CountDown countDown = countDowns.get(i);
        if(countDown.getIsStart().equals("1")){
            String name = countDown.getName();
            viewHolder.Name.setText(name);
            String periodsNum = countDown.getPeriodsNum();
            viewHolder.periodsNum.setText(periodsNum);
            String nextPeriod = countDown.getNextPeriod();
            viewHolder.nextPeriod.setText(nextPeriod);
            String currentLottery = countDown.getCurrentLotteryTime();
            long currentLotteryTime = Long.parseLong(currentLottery);//倒计时结束时间
//        long shijiancha1 = countDown.getShijiancha();//服务器和本地时间差(弃用)
            long shijiancha1= SharePreferencesUtil.getLong(context,"shijiancha",0l);//时间差(每次请求接口都会更新)
            long nowTime = System.currentTimeMillis();
            long countTime =currentLotteryTime-nowTime+shijiancha1;//TODO 加括号long countTime =currentLotteryTime-(nowTime+shijiancha1);
            final String id = countDown.getId();
//                long days = countTime / (1000 * 60 * 60 * 24);
            long hours = (countTime / 1000) / (60 * 60);//(countTime/* - days * (1000 * 60 * 60 * 24)*/) / (1000 * 60 * 60);
            long minutes = ((countTime / 1000) % (60 * 60))/ 60;//(countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = (countTime / 1000) % 60;//(countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;

            viewHolder.hour.setText(hours+"");
            viewHolder.minutes.setText(minutes+"");
            viewHolder.secand.setText(seconds+"");
            if(hours==0||hours<10){
                viewHolder.hour.setText("0"+hours);
            }else{
                viewHolder.hour.setText(""+hours);
            }
            if(minutes==0||minutes<10){
                viewHolder.minutes.setText("0"+minutes);
            }else{
                viewHolder.minutes.setText(""+minutes);
            }
            if(seconds==0||seconds<10){
                viewHolder.secand.setText("0"+seconds);
            }else{
                viewHolder.secand.setText(""+seconds);
            }
            viewHolder.itemView.setOnClickListener(this);
            viewHolder.itemView.setTag(i);
//        viewHolder.countdownView.setText(hours+":"+minutes+":"+seconds);
//        long abs = Math.abs(countTime);
//        viewHolder.countdownView.start(countTime);

//        viewHolder.countdownView.updateShow(countTime);

//        viewHolder.countdownView.updateShow(countTime);
//        }

            Glide.with(context)
                    .load(countDown.getImageId())
                    .into(viewHolder.imageId);
            Glide.with(context)
                    .load(countDown.getIconId())
                    .into(viewHolder.iconId);
//        viewHolder.countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
//            @Override
//            public void onEnd(CountdownView cv) {
//                    endListener.onEnd(id,i);
//
//            }
//        });
            if(countTime<=0){
                viewHolder.hour.setText("00");
                viewHolder.minutes.setText("00");
                viewHolder.secand.setText("00");
                endListener.onEnd(id,i);
            }
//        viewHolder.setIsRecyclable(false);
        }else {
            String name = countDown.getName();
            viewHolder.Name.setText(name);
            String periodsNum = countDown.getPeriodsNum();
            viewHolder.periodsNum.setText(Utils.getString(R.string.停止销售));
            viewHolder.nextPeriod.setVisibility(View.INVISIBLE);
            Glide.with(context)
                    .load(countDown.getImageId())
                    .into(viewHolder.imageId);
            Glide.with(context)
                    .load(countDown.getIconId())
                    .into(viewHolder.iconId);
        /*    String nextPeriod = countDown.getNextPeriod();
            viewHolder.nextPeriod.setText(nextPeriod);*/
        }
        List<String> idList = Arrays.asList(HbGameClassModel.getInstance().getGameIdListStr().split(","));
        for (int j = 0; j < idList.size(); j++) {
            String id = idList.get(j);
            if(id.equals(countDown.getId())){
                countDown.setXian(true);
                break;
            }
        }

        if(countDown.isXian()){
            viewHolder.xianIv.setVisibility(View.VISIBLE);
        }else {
            viewHolder.xianIv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return countDowns.size();
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

//    /*
//    item移进屏幕内调用
//     */
//    @Override
//    public void onViewAttachedToWindow(@NonNull MyHolder holder) {
//        int pos = holder.getAdapterPosition();
//        CountDown countDown = countDowns.get(pos);
//        String currentLottery = countDown.getCurrentLotteryTime();
//        long currentLotteryTime = Long.parseLong(currentLottery);//倒计时结束时间
//        long shijiancha1 =countDown.getShijiancha();//服务器和本地时间差
//        long nowTime = System.currentTimeMillis();
//        long countTime =currentLotteryTime+shijiancha1-nowTime;
////        holder.countdownView.start(countTime);
//        super.onViewAttachedToWindow(holder);
//    }
//    /*
//    item移出屏幕外调用
//     */
//    @Override
//    public void onViewDetachedFromWindow(MyHolder holder) {
////            int pos = holder.getAdapterPosition();
////            Log.d("MyViewHolder", String.format("mCvCountdownView %s is detachedFromWindow", pos));
////        holder.countdownView.stop();
//    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView periodsNum;
        TextView nextPeriod;
        ImageView imageId;
        ImageView iconId;
        //        CountdownView countdownView;
        TextView hour;
        TextView minutes;
        TextView secand;
        ImageView xianIv;

        public MyHolder(@NonNull View view) {
            super(view);
            Name = view.findViewById(R.id.goucai_name);
            periodsNum = view.findViewById(R.id.goucai_periodsNum);
            nextPeriod = view.findViewById(R.id.goucai_nextPeriod);
            imageId = view.findViewById(R.id.goucai_img);
            iconId = view.findViewById(R.id.goucai_icon);
            hour =view.findViewById(R.id.goucai_hour);
            minutes =view.findViewById(R.id.goucai_minute);
            secand =view.findViewById(R.id.goucai_secand);
            xianIv =view.findViewById(R.id.xian_iv);
        }
    }
}
