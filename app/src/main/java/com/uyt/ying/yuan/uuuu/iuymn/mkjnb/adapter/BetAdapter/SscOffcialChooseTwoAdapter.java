

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseTwoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class SscOffcialChooseTwoAdapter extends CommonAdapter<SscOffcialChooseTwoAdapter.MyHolder, SscOfficialChooseTwoMedol> {
    Context context;
    ArrayList<SscOfficialChooseMedol> sscThreeLIstAll = new ArrayList<>();//所有的三级列表数据
    SscOffcialChooseThreeAdapter sscOffcialChooseThreeAdapter;//三级列表适配器

    public SscOffcialChooseTwoAdapter(Context context, ArrayList<SscOfficialChooseTwoMedol> list, /* ArrayList<SscOfficialChooseMedol> sscThreeLIst,*/ ArrayList<SscOfficialChooseMedol> sscThreeLIstAll) {
        super(list);
        this.context = context;
//        this.sscThreeLIst = sscThreeLIst;
        this.sscThreeLIstAll = sscThreeLIstAll;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        SscOfficialChooseTwoMedol itemModel = getItemModel(position);//二级列表的medol
        commonHolder.twoTv.setText(itemModel.getName());
        for (int i = 0; i < list.size(); i++) {//遍历二级列表
            if(i==position){//避免每个里层item都取出所有的值(以后不把里层数据放一级列表里,有时间优化)
//                sscThreeLIst.clear();
                SscOfficialChooseTwoMedol sscOfficialChooseTwoMedol = list.get(i);
                String id = sscOfficialChooseTwoMedol.getId();//取出id,用于筛选三级列表数据
                //创建三级列表适配器时传入此list
                ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList = sscOfficialChooseTwoMedol.getSscOfficialChooseMedolArrayList();
                for (int j = 0; j < sscThreeLIstAll.size(); j++) {//遍历所有的三级列表数据,进行筛选
                    SscOfficialChooseMedol sscOfficialChooseMedol = sscThreeLIstAll.get(j);
                    if(sscOfficialChooseMedol.getParentId().equals(id)){
                        //根据parentid取出model,并添加到二级列表medol的变量sscOfficialChooseMedolArrayList中
                        if(!sscOfficialChooseMedolArrayList.contains(sscOfficialChooseMedol)){
                            //每次添加都将model的status设为0,(解决点击一级item时,之前点击过的三级item在再次切换回时,选中状态没有重置的问题)
                            sscOfficialChooseMedol.setStatus(0);
                            sscOfficialChooseMedolArrayList.add(sscOfficialChooseMedol);
                        }
                        sscOfficialChooseTwoMedol.setSscOfficialChooseMedolArrayList(sscOfficialChooseMedolArrayList);
                    }
                }
            }
        }
            //getSscOfficialChooseMedolArrayList得到的是筛选出的三级列表数据
        final ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList = list.get(position).getSscOfficialChooseMedolArrayList();
            if(position==0){//二级列表的第一个item对应的三级列表第一个item需要默认选中
                boolean haveClick=false;//设置一个boolean值,然后遍历所有的三级列表,如果三级列表中有status为1的model,将haveClick设置为true,解决三级列表的第一个item一直是选中状态的问题
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedols = list.get(i).getSscOfficialChooseMedolArrayList();
                    for (int j = 0; j < sscOfficialChooseMedols.size(); j++) {
                       if( sscOfficialChooseMedols.get(j).getStatus()==1){
                           haveClick=true;
                           break;
                       }
                    }
                }
                //当前三级列表数据中,没有选中的item,则默认选中第一个item
                if(!haveClick){
                    sscOfficialChooseMedolArrayList.get(0).setStatus(1);//每次加载的时候需要将三级列表第一个item的第一个model设置为选中状态(如果当前item有model为选中状态(status为1)则不设置),
                }
        }

//        }
        //创建适配器,传入的是二级列表medol的变量list,保证每个二级列表的item都对应一个三级列表的list(否则每次添加的数据都是二级列表最后一个item对应的三级列表数据)
         sscOffcialChooseThreeAdapter = new SscOffcialChooseThreeAdapter(context,sscOfficialChooseMedolArrayList,list,sscThreeLIstAll);
        RecyclerView threeRecy = commonHolder.threeRecy;
        threeRecy.setLayoutManager(new GridLayoutManager(context,3));
        threeRecy.setAdapter(sscOffcialChooseThreeAdapter);
        //三级列表适配器点击事件中position==i的回调(除了点击的item,其他所有三级列表model的status都设置为0)解决三级列表中每个item中都可以有一个item被选中(实际效果应该是所有三级列表的model只能有一个被选中)
        //需要配合三级列表适配器中点击事件一起处理
        sscOffcialChooseThreeAdapter.setOnStatusListener(new SscOffcialChooseThreeAdapter.OnStatusListener() {
            @Override
            public void statusListener(SscOfficialChooseMedol model, int position) {
                for (int i = 0; i < list.size(); i++) {//将所有二级列表对应的三级列表item的选中状态重置
                    ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList1 = list.get(i).getSscOfficialChooseMedolArrayList();
                    for (int j = 0; j < sscOfficialChooseMedolArrayList1.size(); j++) {
                        sscOfficialChooseMedolArrayList1.get(j).setStatus(0);
                    }
                }
                model.setStatus(1);//将当前点击的三级item设为选中
                notifyDataSetChanged();
                sscOffcialChooseThreeAdapter.notifyDataSetChanged();
            }
        });
        sscOffcialChooseThreeAdapter.notifyDataSetChanged();
        sscOffcialChooseThreeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(onThreeItemClick!=null){
                    onThreeItemClick.threeClick(view,sscOfficialChooseMedolArrayList,position);
                }
            }
        });

    }
    public  static  interface  OnThreeItemClick{
        void threeClick(View view,  ArrayList<SscOfficialChooseMedol> threeList,int position);
    }
    public SscOffcialChooseTwoAdapter.OnThreeItemClick  onThreeItemClick=null;

    public void setOnThreeItemClick(OnThreeItemClick onThreeItemClick) {
        this.onThreeItemClick = onThreeItemClick;
    }

    @Override
    public int getLayOutRes() {
        return R.layout.ssc_official_choose_item_two;
    }
    public static class MyHolder extends CommonHolder {
        private TextView twoTv;
        private RecyclerView threeRecy;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            twoTv=itemView.findViewById(R.id.ssc_official_item_two_textview);
            threeRecy=itemView.findViewById(R.id.official_choose_three_recycle);
        }
    }
}
