//package com.example.administrator.aaa.adapter;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.text.Html;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.administrator.aaa.R;
//import com.example.administrator.aaa.model.Reward;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
//
//import java.util.ArrayList;
//
///**
// * 首页中奖信息adapter
// */
//public class GetWinnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private ImageLoader imageLoader = ImageLoader.getInstance();
//    public static final int TYPE_HEADER = 0;
//    public static final int TYPE_NORMAL = 1;
//    public static final int TYPE_FOOT = 2;
//    private ArrayList<Reward> rewardArrayList =new ArrayList<>();
//    private View mHeaderView;
//    private View mFootView;
//    private GetWinnerAdapter.OnItemClickListener mListener;
//
//    public GetWinnerAdapter(ArrayList<Reward> rewardArrayList) {
//        this.rewardArrayList=rewardArrayList;
//
//    }
//
//    public void setOnItemClickListener(GetWinnerAdapter.OnItemClickListener li) {
//        mListener = li;
//    }
//    public void setHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }
//    public void setFootView(View footView) {
//        mFootView = footView;
//        notifyItemInserted(0);
//    }
//    public View getHeaderView() {
//        return mHeaderView;
//    }
//    public View getFootView() {
//        return mFootView;
//    }
//    @Override
//    public int getItemViewType(int position) {
//        if(mHeaderView == null&&mFootView==null) return TYPE_NORMAL;
//        if(position == 0) return TYPE_HEADER;
//        if(position == 2) return TYPE_FOOT;
//        return TYPE_NORMAL;
//    } @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if(manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    return getItemViewType(position) == TYPE_HEADER
//                            ? gridManager.getSpanCount() : 1;
//                }
//            });
//        }
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
//        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_winner, parent, false);
//        return new Holder(layout);
//    }
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        if(lp != null
//                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
//            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
//            p.setFullSpan(holder.getLayoutPosition() == 0);
//        }
//    }
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
//        //itemType为head时,直接返回, 不作处理
//        if(getItemViewType(position) == TYPE_HEADER) return;
//        final int pos = getRealPosition(viewHolder);
////        final  Reward prize = rewardArrayList.get(pos);
//        if(viewHolder instanceof GetWinnerAdapter.Holder && !rewardArrayList.isEmpty()) {
//
////            ((GetWinnerAdapter.Holder) viewHolder).imgIdTop.setImageResource(rewardArrayList.get(position% rewardArrayList.size()).getImgIdTop());
////            ((GetWinnerAdapter.Holder) viewHolder).imageIdBottom.setImageResource(rewardArrayList.get(position% rewardArrayList.size()).getImageIdBottom());
//            DisplayImageOptions options = new DisplayImageOptions.Builder()
//                    .displayer(new RoundedBitmapDisplayer(50))//设置圆角图片
//                    .cacheInMemory(true)//下载的图片是否缓存在内存中
//                    .build();//创建displayImageOptions对象
//            /**
//             * 使用imageLoader加载图片
//             * 第一个参数为image的url资源,
//             * 第二个参数为imageView控件,
//             * 第三个参数为设置了图片加载样式的displayImageOptions对象
//             */
//            imageLoader.displayImage(rewardArrayList.get(position).getImgIdTop(),((GetWinnerAdapter.Holder) viewHolder).imgIdTop,options);
//            DisplayImageOptions options1 = new DisplayImageOptions.Builder()
//                    .cacheInMemory(true)//下载的图片是否缓存在内存中
//                    .build();//创建displayImageOptions对象
//            imageLoader.displayImage(rewardArrayList.get(position).getImageIdBottom(),((GetWinnerAdapter.Holder) viewHolder).imageIdBottom,options1);
//            ((GetWinnerAdapter.Holder) viewHolder).name.setText(rewardArrayList.get(position).getName());
//            ((GetWinnerAdapter.Holder) viewHolder).time.setText(rewardArrayList.get(position).getTime());
////            ((GetWinnerAdapter.Holder) viewHolder).money.setText(rewardArrayList.get(position).getMoney());
//            String money = rewardArrayList.get(position).getMoney();
//            String finalStr1=Utils.getString(R.string.我在)+rewardArrayList.get(position).getLotteryName()+Utils.getString(R.string.喜中了)+"<font color=\"#FF0000\">"+money+"</font>"+Utils.getString(R.string.元！);
//            ((GetWinnerAdapter.Holder) viewHolder).money.setText(Html.fromHtml(finalStr1));
//            if(mListener == null) return;
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    mListener.onItemClick(pos, prize);
//                }
//            });
//        }
//    }
//    public int getRealPosition(RecyclerView.ViewHolder holder) {
//        int position = holder.getLayoutPosition();
//        return mHeaderView == null ? position : position - 1;
//    }
//    @Override
//    public int getItemCount() {
////        return mHeaderView == null ? rewardArrayList.size() : rewardArrayList.size() + 1;
//        return rewardArrayList.size();
//    }
//    class Holder extends RecyclerView.ViewHolder {
//        ImageView imgIdTop;
//        ImageView imageIdBottom;
//        TextView name;
//        TextView time;
//        TextView money;
//
//        public Holder(View itemView) {
//            super(itemView);
//            if(itemView == mHeaderView) return;
//            time = itemView.findViewById(R.id.winner_time);
//            name = itemView.findViewById(R.id.winner_name);
//            money = itemView.findViewById(R.id.winner_content);
//            imgIdTop = itemView.findViewById(R.id.winner_img);
//            imageIdBottom = itemView.findViewById(R.id.winner_type);
//        }
//    }
//    interface OnItemClickListener {
//        void onItemClick(int position, Reward reward);
//    }
//}
