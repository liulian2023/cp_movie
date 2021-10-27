package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;
/**
 * 活动中心RecycleView适配器
 */
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AtyCenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
public class AtyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
 ArrayList<AtyCenter> atyCenters = new ArrayList<>();
 Context context;
 private String firstUrl;
 public AtyRecycleViewAdapter( ArrayList<AtyCenter> atyCenters,Context context) {
  this.atyCenters = atyCenters;
  this.context = context;
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
 private AtyRecycleViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

 public void setOnItemClickListener(AtyRecycleViewAdapter.OnRecyclerViewItemClickListener listener) {
  this.mOnItemClickListener = listener;
 }

 @NonNull
 @Override
 public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
  View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aty_recycleview_item, viewGroup, false);
  view.setOnClickListener(this);
  firstUrl=SharePreferencesUtil.getString(viewGroup.getContext(),"FirstImageUrl","");
  return new Holder(view);
 }
 @Override
 public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
  if(viewHolder instanceof  Holder){
   AtyCenter atyCenter = atyCenters.get(i);
   if(atyCenter.getWhat()==1){
    ((Holder) viewHolder).aty_recycleview_linear.setVisibility(View.VISIBLE);
    if(StringMyUtil.isNotEmpty(atyCenter.getImageUrl())){
     Glide.with(MyApplication.getInstance())
             .load(Utils.checkImageUrl(atyCenter.getImageUrl()))
             .into(((Holder) viewHolder).imageId);
    }/*else {
    ((Holder) viewHolder).imageId.setImageResource(atyCenter.getImageId());
    }*/
   }else{
    if(atyCenter.getThemetype().equals("1")){
     ((Holder) viewHolder).aty_recycleview_linear.setVisibility(View.VISIBLE);
     Glide.with(MyApplication.getInstance())
             .load(Utils.checkImageUrl(atyCenter.getImageUrl()))
             .into(((Holder) viewHolder).imageId);
    }else {
     ((Holder) viewHolder).aty_recycleview_linear.setVisibility(View.GONE);
    }

   }
   ((Holder) viewHolder).titleText.setText(atyCenter.getTitle());
   ((Holder) viewHolder).contentText.setText(atyCenter.getContent());
//   ((Holder) viewHolder).linearLayout.setOnClickListener(this);
   ((Holder) viewHolder).itemView.setTag(i);
  }

 }
 @Override
 public int getItemCount() {
  return atyCenters.size();
 }
 class Holder extends RecyclerView.ViewHolder{
  TextView titleText;
  TextView contentText;
  ImageView imageId;
  ImageView iconId;
  LinearLayout linearLayout;
  LinearLayout aty_recycleview_linear;
  public Holder(@NonNull View itemView) {
   super(itemView);
   titleText = itemView.findViewById(R.id.aty_recycleview_title);
   contentText =  itemView.findViewById(R.id.aty_recycleview_content);
   imageId =  itemView.findViewById(R.id.aty_recycleview_image);
   iconId =  itemView.findViewById(R.id.aty_recycleview_icon);
   aty_recycleview_linear =  itemView.findViewById(R.id.aty_recycleview_linear);
  }
 }
}
