/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.app.Activity;
//import android.support.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.util.List;


//RecyclerView的适配器，主要是指定泛型， 一般我们就是类名的ViewHolder继承Viewholder (内部已经实现了复用机制)
public class K3RecyclerAdapter extends RecyclerView.Adapter<K3RecyclerAdapter.VH>{
    private Activity mContext;
    private List<GetGroup.GameruleBean> list1;
    private  List<String[]> list2;
  //  private String group_id;
  //  private String groupname;
    private int[] shaizis;//筛子点数
//    LinkedHashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //7、定义点击事件回调接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position,List<IsClickModel> list);
    }

    //构造方法，一般需要接受两个参数，上下文，集合对象（包含我们所需要的数据）
    public K3RecyclerAdapter(Activity context, List<GetGroup.GameruleBean> list1, List<String[]> list2,int[] shaizis ,List<IsClickModel> isClickList) {
        mContext = context;
        this.list1 = list1;
        this.list2 = list2;
        this.shaizis = shaizis;
        this.isClickList = isClickList;
    }

    //创建ViewHolder也就是说创建出来一条,并把ViewHolder（item）返回出去
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个View布局对象，决定了item的样子， 参数1：上下文 2. xml布局对象 3.为null
     //   View view = View.inflate(mContext, R.layout.item_k3_recyclerview, null);
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_k3_recyclerview,parent,false);
        //创建一个ViewHolder对象
        VH vh = new VH(view);
        //把ViewHolder对象传出去
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        //从集合里拿对应item的数据对象
        final GetGroup.GameruleBean bean = list1.get(position);
        //给holder里面的控件对象设置数据
       /* List<String >nameList = new ArrayList<String>();
        boolean isImage = false;
        String[] arry = bean.getName().split("_");*/
       /* for (int i = 0; i < arry.length; i++) {
            //       System.out.println(sourceStrArray[i]);
            nameList.add(arry[i]);
        }*/
        boolean isImage = false;
        for (String s :list2.get(position)){
            if (s.contains(Utils.getString(R.string.点))){
                isImage = false;
            }else if (Utils.isNumeric(s)){
                isImage = true;
            }else {
                isImage = false;
            }
        }


 /*       if(isClickMap.get(bean.getId())){
            holder.mTv1.setTextColor(ContextCompat.getColor(R.color.white));
            holder.mTv1.setBackground(null);
            holder.mTv.setTextColor(ContextCompat.getColor(R.color.white));
            holder.itemLayout.setBackground(ContextCompat.getDrawable(R.drawable.shape_bkg_onpress));
        }else{
            holder.mTv1.setTextColor(ContextCompat.getColor(R.color.dimgray));
            holder.mTv1.setBackground(null);
            holder.mTv.setTextColor(ContextCompat.getColor(R.color.dimgray));
            holder.itemLayout.setBackground(ContextCompat.getDrawable(R.drawable.shape_bkg_no_enable));
        }*/
        if (isImage){
         //   holder.mIv1.setImageResource(shaizis[1]);

            if(StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
                holder.mTv1.setBackground(null);
                holder.mTv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.itemLayout.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            }else{
                holder.mTv1.setBackground(null);
                holder.mTv.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
                holder.itemLayout.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            }

            switch (list2.get(position).length){
                case 1:
                    holder.mTv1.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[0])-1]))); //(ContextCompat.getDrawable(i))
               //     holder.mTv1.setBackground((ContextCompat.getDrawable(shaizis[0])));
                    holder.mTv1.setVisibility(View.VISIBLE);
                    holder.mTv2.setVisibility(View.GONE);
                    holder.mTv3.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.mTv1.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[0])-1]))); //(ContextCompat.getDrawable(i))
                    holder.mTv2.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[1])-1])));
             //       holder.mTv1.setBackground((ContextCompat.getDrawable(shaizis[3])));
                    holder.mTv1.setVisibility(View.VISIBLE);
                    holder.mTv2.setVisibility(View.VISIBLE);
                    holder.mTv3.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.mTv1.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[0])-1]))); //(ContextCompat.getDrawable(i))
                    holder.mTv2.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[1])-1])));
                    holder.mTv3.setBackground((ContextCompat.getDrawable(mContext,shaizis[Integer.parseInt(list2.get(position)[2])-1])));
            //        holder.mTv1.setBackground((ContextCompat.getDrawable(shaizis[5])));
                    holder.mTv1.setVisibility(View.VISIBLE);
                    holder.mTv2.setVisibility(View.VISIBLE);
                    holder.mTv3.setVisibility(View.VISIBLE);
                    break;
                 default:
                     break;
            }
        }else {
            if(StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
                holder.mTv1.setBackground(null);
                holder.mTv1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.mTv.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                holder.itemLayout.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_onpress));
            }else{
                holder.mTv1.setBackground(null);
                holder.mTv1.setTextColor(ContextCompat.getColor(mContext,R.color.action_bar_color));
                holder.mTv.setTextColor(ContextCompat.getColor(mContext,R.color.textgray));
                holder.itemLayout.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_bkg_no_enable));
            }
            if (list2.get(position)[0].contains(Utils.getString(R.string.点))){
                LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) holder.mTv1.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
                linearParams.width = 60;// 控件的宽强制设成30
                holder.mTv1.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

                holder.mTv1.setText(list2.get(position)[0]);
                holder.mTv1.setVisibility(View.VISIBLE);
                holder.mTv2.setVisibility(View.GONE);
                holder.mTv3.setVisibility(View.GONE);
            }else {
                holder.mTv1.setText(list2.get(position)[0]);
                holder.mTv1.setVisibility(View.VISIBLE);
                holder.mTv2.setVisibility(View.GONE);
                holder.mTv3.setVisibility(View.GONE);
            }

        }

        holder.setData(Utils.getString(R.string.赔)+bean.getOdds());



        //4：设置点击事件
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    if (StrUtil.isclick(isClickList,String.valueOf(bean.getId()))){
                     //   isClickMap.replace(String.valueOf(bean.getId()),false);
                        StrUtil.isclickReplace(isClickList,String.valueOf(bean.getId()),false);
                    }else {
                     //   isClickMap.replace(String.valueOf(bean.getId()),true);
                        StrUtil.isclickReplace(isClickList,String.valueOf(bean.getId()),true);
                    }

                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, position,isClickList); // 2
                }
            });
        }






    }

    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (list1 != null && list1.size() > 0) {
            return list1.size();
        }
        return 0;
    }

    public static class VH extends RecyclerView.ViewHolder{

    //    private final ImageView mIvIcon;
        private final TextView mTv;
        private final TextView mTv1;
        private final TextView mTv2;
        private final TextView mTv3;
    //    private final RecyclerView mRecyclerView;
        LinearLayout itemLayout;

        public VH(View itemView) {
            super(itemView);

            mTv = itemView.findViewById(R.id.item_k3_recyclerview_tv);
            mTv1 = itemView.findViewById(R.id.item_k3_recyclerview_tv1);
            mTv2 = itemView.findViewById(R.id.item_k3_recyclerview_tv2);
            mTv3 = itemView.findViewById(R.id.item_k3_recyclerview_tv3);
            itemLayout = itemView.findViewById(R.id.item_k3_recyclerview_item);

        }

        public void setData(String data) {
            //给imageView设置图片
      //      mIvIcon.setImageResource(data.icon);
            //给TextView设置文本
            mTv.setText(data);
        }
    }


}

