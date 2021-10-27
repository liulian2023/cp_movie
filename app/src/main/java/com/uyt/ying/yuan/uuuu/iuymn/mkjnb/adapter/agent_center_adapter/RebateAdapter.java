package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Odds;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RebateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class RebateAdapter extends CommonAdapter<RebateAdapter.MyHolder,RebateModel> {
    private Context context;
    ArrayList<Odds> oddsArrayList =new ArrayList<>();//里层recycleView数据
    private LinearLayout linearLayout;

//    public RebateAdapter(ArrayList<RebateModel> list, Context context, ArrayList<Odds> oddsArrayList) {
//        super(list);
//        this.context = context;
//        this.oddsArrayList = oddsArrayList;
//    }

    public RebateAdapter(ArrayList<RebateModel> list, Context context, ArrayList<Odds> oddsArrayList, LinearLayout linearLayout) {
        super(list);
        this.context = context;
        this.oddsArrayList = oddsArrayList;
        this.linearLayout = linearLayout;
    }

    public RebateAdapter() {
    }
//    public RebateAdapter(ArrayList<RebateModel> list) {
//        super(list);
//    }

    @Override
    public int getLayOutRes() {
        return R.layout.rebate_recycle_item;//布局文件
    }

    /*
    相当于bindViewHolder,recycleView数据相关处理只需重写此方法
     */
    @Override
    public void handleViewHolder(final MyHolder myHolder, int position) {
        //getItemModel()方法 返回的是当前position对应的been对象
        RebateModel itemModel = getItemModel(position);

        myHolder.textView.setText(itemModel.getText());
        myHolder.itemView.setTag(position);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            //点击itemView 显示或者隐藏里层recycleView,并控制图片的旋转
            public void onClick(View v) {
                if(myHolder.recyclerView.getVisibility()==View.GONE){//里层recycleView不可见时点击,则显示里层recycleView,并旋转图片
                    myHolder.imageView.setPivotX(myHolder.imageView.getWidth()/2);
                    myHolder.imageView.setPivotY(myHolder.imageView.getHeight()/2);//支点在图片中心
                    myHolder.imageView.setRotation(90);
                    myHolder.recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                }else {//里层recycleView可见时点击,则隐藏里层recycleView,并恢复图片
                    myHolder.imageView.setPivotX(myHolder.imageView.getWidth()/2);
                    myHolder.imageView.setPivotY(myHolder.imageView.getHeight()/2);//支点在图片中心
                    myHolder.imageView.setRotation(0);
                    myHolder.recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                }
                myHolder.recyclerView.setAdapter(new RebatePupopwindowMoreAdapter(oddsArrayList));//给里层recycleView填充数据(数据的具体处理在里层recycleView中实现)
                myHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                //点击事件的接口回调
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        myHolder.itemView.setTag(position);
        myHolder.setIsRecyclable(false);//取消复用
    }

    public static class MyHolder extends CommonHolder {
        TextView textView;
        LinearLayout linearLayout;
        ImageView imageView;
        RecyclerView recyclerView;
//        FrameLayout frameLayout;
//        LinearLayout shouqi;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.rebate_text);
            linearLayout=itemView.findViewById(R.id.linear);
            imageView=itemView.findViewById(R.id.rebate_image);
            recyclerView=itemView.findViewById(R.id.more_table_recycle);
//            frameLayout=itemView.findViewById(R.id.wrap_frame);
//            shouqi=itemView.findViewById(R.id.shouqi);
        }
    }
}
