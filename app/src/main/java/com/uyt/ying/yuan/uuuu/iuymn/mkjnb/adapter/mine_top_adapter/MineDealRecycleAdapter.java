package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineDealMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MineDealRecycleAdapter extends RecyclerView.Adapter<MineDealRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<MineDealMedol>mineDealMedolArrayList =new ArrayList<>();

    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private RecyclerView mRecyclerView;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
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
    public MineDealRecycleAdapter(ArrayList<MineDealMedol> mineDealMedolArrayList) {
        this.mineDealMedolArrayList = mineDealMedolArrayList;
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private MineDealRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @NonNull
    @Override
    public MineDealRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (i == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        }else{
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_deal_recycle_item,viewGroup,false);
            view.setOnClickListener(this);
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MineDealRecycleAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MineDealMedol mineDealMedol = mineDealMedolArrayList.get(position);
            long l = Long.parseLong(mineDealMedol.getTime());
            String format = simpleDateFormat.format(l);
            if(mineDealMedol.getWhat().equals("1")){//全部类型
                if(mineDealMedol.getType()>100){
                    myHolder.amount.setTextColor(Color.parseColor("#8b8b8b"));
                    myHolder.amount.setText("-"+ mineDealMedol.getAmout().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));

                }else{
                    myHolder.amount.setTextColor(Color.RED);
                    BigDecimal amout = mineDealMedol.getAmout();
                    if(amout.compareTo(BigDecimal.ZERO)==-1){
                        myHolder.amount.setText( amout.setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));
                    }else {

                        myHolder.amount.setText("+"+ amout.setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));
                    }
                }
                myHolder.name.setText(mineDealMedol.getRemark());
//                myHolder.name.setText(mineDealMedol.getTypeName());
                myHolder.type.setText("");
            }
            else if(mineDealMedol.getWhat().equals("2")){//提现
                myHolder.amount.setTextColor(Color.parseColor("#8b8b8b"));//提现时 amout传price
                myHolder.amount.setText("-"+ mineDealMedol.getAmout().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));
                if(mineDealMedol.getStatus().equals("-2")){//提现时,type传status
                    myHolder.type.setText(Utils.getString(R.string.拒绝申请));
                    myHolder.type.setTextColor(Color.parseColor("#A7A7A7"));
                }else if(mineDealMedol.getStatus().equals("-1")){
                    myHolder.type.setText(Utils.getString(R.string.申请未通过));
                    myHolder.type.setTextColor(Color.parseColor("#FFCF77"));
                }else if(mineDealMedol.getStatus().equals("0")){
//                    myHolder.type.setText(Utils.getString(R.string.申请中));
                    myHolder.type.setText(Utils.getString(R.string.处理中));
                    myHolder.type.setTextColor(Color.parseColor("#238BFF"));
                }else if(mineDealMedol.getStatus().equals("1")){
//                    myHolder.type.setText(Utils.getString(R.string.待打款));
                    myHolder.type.setText(Utils.getString(R.string.处理中));
                    myHolder.type.setTextColor(Color.parseColor("#238BFF"));
//                    myHolder.type.setTextColor(Color.parseColor("#A7A7A7"));
                }else if(mineDealMedol.getStatus().equals("2")){
//                    myHolder.type.setText(Utils.getString(R.string.已打款));
                    myHolder.type.setText(Utils.getString(R.string.提现成功));
                    myHolder.type.setTextColor(Color.parseColor("#FF4540"));
                }
                if(mineDealMedol.getType()==0l){
                    myHolder.name.setText(Utils.getString(R.string.余额提现));
                }
                else if(mineDealMedol.getType()==1l) {
                    myHolder.name.setText(Utils.getString(R.string.佣金提现));
                }else{
                    myHolder.name.setText(Utils.getString(R.string.余额提现));
                }
            }
            else if(mineDealMedol.getWhat().equals("3")){//充值明细
                if(mineDealMedol.getType()==0l){
                    myHolder.name.setText(mineDealMedol.getRemark());//type为0, 设置银行名
                }else if(mineDealMedol.getType()==1l){
                    myHolder.name.setText(Utils.getString(R.string.微信));//type为1, 设置为 Utils.getString(R.string.微信)
                }else if(mineDealMedol.getType()==2l){
                    myHolder.name.setText(Utils.getString(R.string.支付宝));//type为1, 设置为 Utils.getString(R.string.支付宝)
                } else if(mineDealMedol.getType()==3l){
                    if(mineDealMedol.getAmout().compareTo(BigDecimal.ZERO)==-1){
                        myHolder.name.setText(Utils.getString(R.string.人工扣款余额));//金额小于0,
                    }
                    else{
                        myHolder.name.setText(Utils.getString(R.string.人工入款余额));
                    }
                }else if(mineDealMedol.getType()==4l){
                    myHolder.name.setText(Utils.getString(R.string.代理充值));
                }
                //类型/充值类型 0银行 1微信 2支付宝 3后台充值-余额 4代理充值(代理佣金余额充值给下级) 8(price<0 人工扣款-佣金  price>0 人工入款-佣金)后台充值-佣金 9百乐汇 else 在线充值
                else if(mineDealMedol.getType()==5l){
                    if(mineDealMedol.getAmout().compareTo(BigDecimal.ZERO)==-1){
                        myHolder.name.setText(R.string.人工扣款佣金);//金额小于0,
                    }else{
                        myHolder.name.setText(Utils.getString(R.string.人工入款佣金));//金额小于0,
                    }
                } else if(mineDealMedol.getType()==9l){
                    myHolder.name.setText(Utils.getString(R.string.百汇付));
                }else{
                    myHolder.name.setText(Utils.getString(R.string.在线充值));
                }
                if(mineDealMedol.getStatus().equals("0")){
                    myHolder.type.setText(Utils.getString(R.string.充值中));
                    myHolder.type.setTextColor(Color.parseColor("#238BFF"));
                    BigDecimal amout = mineDealMedol.getAmout();
                    if(amout.compareTo(BigDecimal.ZERO)==-1){
                        myHolder.amount.setText(mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }else{
                        myHolder.amount.setText("+"+mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }
                    myHolder.amount.setTextColor(Color.parseColor("#238BFF"));
                }else if(mineDealMedol.getStatus().equals("1")){
                    myHolder.type.setText(Utils.getString(R.string.充值成功));
                    myHolder.type.setTextColor(Color.parseColor("#FF4540"));
                    BigDecimal amout = mineDealMedol.getAmout();
                    if(amout.compareTo(BigDecimal.ZERO)==-1){
                        myHolder.amount.setText(mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }else{
                        myHolder.amount.setText("+"+mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }
                    myHolder.amount.setTextColor(Color.parseColor("#FF4540"));
                }else /*if (mineDealMedol.getStatus().equals("2"))*/{
                    String text = String.format(Utils.getString(R.string.充值失败括号占位), mineDealMedol.getRemark());
                    myHolder.type.setText(text);
                    int length = text.length();
                    if(length <=10){
                        myHolder.type.setTextSize(16);
                    }else if(length >10&&length<=20) {
                        myHolder.type.setTextSize(13);
                    }else {
                        myHolder.type.setTextSize(10);
                    }
                    myHolder.type.setTextColor(Color.parseColor("#A7A7A7"));
                    BigDecimal amout = mineDealMedol.getAmout();
                    if(amout.compareTo(BigDecimal.ZERO)==-1){
                        myHolder.amount.setText(mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }else{
                        myHolder.amount.setText("+"+mineDealMedol.getAmout()+Utils.getString(R.string.元));
                    }
                    myHolder.amount.setTextColor(Color.parseColor("#A7A7A7"));
                }
            }
            myHolder.time.setText(format);
            myHolder.itemView.setTag(position);
            String typeName = mineDealMedol.getTypeName();
            if(!StringMyUtil.isEmptyString(typeName)){
                myHolder.name.setText(typeName);
            }
/*            if(StringMyUtil.isNotEmpty(mineDealMedol.getRemark())){
                myHolder.name.setText(typeName+"("+mineDealMedol.getRemark()+")");
            }*/
        }

    }

    @Override
    public int getItemCount() {
        return mineDealMedolArrayList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView amount;
        TextView time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nikename);
            type=itemView.findViewById(R.id.type);
            amount=itemView.findViewById(R.id.amount);
            time=itemView.findViewById(R.id.time);
        }
    }
}
