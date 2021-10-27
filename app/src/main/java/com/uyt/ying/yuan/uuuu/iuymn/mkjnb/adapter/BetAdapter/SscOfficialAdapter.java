


package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.ssc.SscBetActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class SscOfficialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private ArrayList<SscOfiicialBetMedol>sscOfiicialBetMedolArrayList=new ArrayList<>();//四级列表数据源
    private ArrayList<SscOfiicialBetMedol>selectorList=new ArrayList<>();//已选中item
    private ArrayList<ArrayList<TextView>>allTextViewList=new ArrayList<>();//全大小单双清 容器
    private ArrayList<SscOfiicialBetMedol>sscofficialFourModelList=new ArrayList<>();//四级列表子recycelView数据源

    public SscOfficialAdapter(Context context, ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedolArrayList, ArrayList<SscOfiicialBetMedol> selectorList,ArrayList<SscOfiicialBetMedol>sscofficialFourModelList) {
        this.context = context;
        this.sscOfiicialBetMedolArrayList = sscOfiicialBetMedolArrayList;
        this.selectorList = selectorList;
        this.sscofficialFourModelList = sscofficialFourModelList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder=null;
        Context context = viewGroup.getContext();
        if(viewType==0){
            //大小单双-复式需要单独的布局
            view = LayoutInflater.from(context).inflate(R.layout.ssc_official_item_one, viewGroup, false);
            viewHolder=new ItemOneHolder(view);
        }else if(viewType==1){
            //其他玩法的布局
            view = LayoutInflater.from(context).inflate(R.layout.ssc_official_item_two, viewGroup, false);
            viewHolder=new ItemTwoHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType==0){
            //大小单双-复式数据处理
            ItemOneHolder itemOneHolder=(ItemOneHolder)viewHolder;
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(position);
            String name = sscOfiicialBetMedol.getName();
             itemOneHolder.nameTv.setText(name);
            //通过parentId筛选子recycleView数据
            final ArrayList<SscOfiicialBetMedol> list =new ArrayList<>();
            for (int j = 0; j < sscofficialFourModelList.size(); j++) {
                if(sscOfiicialBetMedol.getId().equals(sscofficialFourModelList.get(j).getId())){
                    list.add(sscofficialFourModelList.get(j));
                }
            }
//            --------------start---------------     子recycleView 适配器相关    --------------------start---------------

                SscItemOneAdapter  sscItemOneAdapter=new SscItemOneAdapter(context,list,selectorList);
                RecyclerView itemOneRecy = itemOneHolder.itemOneRecy;//四级列表子recycleView
                itemOneRecy.setLayoutManager(new GridLayoutManager(context,4));
                itemOneRecy.setAdapter(sscItemOneAdapter);
                sscItemOneAdapter.notifyDataSetChanged();
                sscItemOneAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(onItemOneClickListener!=null){
                            onItemOneClickListener.onItemOneClick(view,list,position);
                        }
                    }
                });
//            --------------end---------------     子recycleView 适配器相关    --------------------end---------------
        }
        //大小单双-复式 之外的数据处理
        else if(itemViewType==1){
            final ItemTwoHolder itemTwoHolder=(ItemTwoHolder)viewHolder;
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(position);
            String name = sscOfiicialBetMedol.getName();
            itemTwoHolder.itemTwoName.setText(name);
                //全大小单双按钮的选中状态设置
                if(sscOfiicialBetMedol.isQuanSelecter()){
                    itemTwoHolder.quanTv.setSelected(true);
                }else {
                    itemTwoHolder.quanTv.setSelected(false);
                }
                if(sscOfiicialBetMedol.isDaSelecter()){
                    itemTwoHolder.daTv.setSelected(true);
                }else {
                    itemTwoHolder.daTv.setSelected(false);
                }
                if(sscOfiicialBetMedol.isXiaoSelecter()){
                    itemTwoHolder.xiaoTv.setSelected(true);
                }else {
                    itemTwoHolder.xiaoTv.setSelected(false);
                }
                if(sscOfiicialBetMedol.isDanSelecter()){
                    itemTwoHolder.danTv.setSelected(true);
                }else {
                    itemTwoHolder.danTv.setSelected(false);
                }
                if(sscOfiicialBetMedol.isShuangSelecter()){
                    itemTwoHolder.shuangTv.setSelected(true);
                }else {
                    itemTwoHolder.shuangTv.setSelected(false);
                }
                //通过parentId筛选子recycleView数据
                ArrayList<SscOfiicialBetMedol> list =new ArrayList<>();
                for (int j = 0; j < sscofficialFourModelList.size(); j++) {
                    if(sscOfiicialBetMedol.getId().equals(sscofficialFourModelList.get(j).getId())){
                        list.add(sscofficialFourModelList.get(j));
                    }
                }
//            --------------start---------------     子recycleView 适配器相关    --------------------start---------------
                final SscItemTwoAdapter  sscItemTwoAdapter=new SscItemTwoAdapter(list,selectorList);
                RecyclerView itemTwoRecy = itemTwoHolder.itemTwoRecy;
                itemTwoRecy.setLayoutManager(new GridLayoutManager(context,5));
                itemTwoRecy.setAdapter(sscItemTwoAdapter);
                sscItemTwoAdapter.notifyDataSetChanged();
                sscItemTwoAdapter.setOnFourItemClickLintener(new SscItemTwoAdapter.onFourItemClickLintener() {
                    @Override
                    public void onFourItemClick(View view, ArrayList<SscOfiicialBetMedol> officialSelector, int position) {
                        if(onItemFourClickListener!=null){
                            onItemFourClickListener.onItemFourClick(view,sscofficialFourModelList.get(position),position);
                        }
                    }
                });
                //添加全大小单双清 按钮(初始化选中状态时需要用到)
                final ArrayList<TextView>textViewArrayList=new ArrayList<>();
                textViewArrayList.add(itemTwoHolder.quanTv);
                textViewArrayList.add(itemTwoHolder.daTv);
                textViewArrayList.add(itemTwoHolder.xiaoTv);
                textViewArrayList.add(itemTwoHolder.danTv);
                textViewArrayList.add(itemTwoHolder.shuangTv);
                textViewArrayList.add(itemTwoHolder.qingTv);
                if(!allTextViewList.contains(textViewArrayList)){

                    allTextViewList.add(textViewArrayList);
                }
                //子recycleView点击事件回调
                sscItemTwoAdapter.setOnBetTypeClickLintener(new SscItemTwoAdapter.OnBetTypeClickLintener() {
                    @Override
                    public void onBetTypeListener(View view, ArrayList<SscOfiicialBetMedol> sscBetChildMedols, int position) {

                    }

                    @Override
                    public void initSelectedNum(int size) {
                       if(itemTwoHolder.quanTv.isSelected()){
                           itemTwoHolder.quanTv.setSelected(false);
                       }
                       else if(itemTwoHolder.daTv.isSelected()||itemTwoHolder.xiaoTv.isSelected()||itemTwoHolder.danTv.isSelected()||itemTwoHolder.shuangTv.isSelected()){
                           if(size<sscofficialFourModelList.size()/2||size>sscofficialFourModelList.size()/2){
                               for (int j = 0; j <textViewArrayList.size() ; j++) {
                                   textViewArrayList.get(j).setSelected(false);
                               }
                           }
                       }

                    }
                });


                if(list.size()!=0){
                    if(list.get(0).getIsQuick().equals("0")){//isQuick为0,隐藏全大小单双清
                        String name1 = list.get(0).getName();
                        if(name1.equals(Utils.getString(R.string.和值))||name1.equals(Utils.getString(R.string.组三))||name1.equals(Utils.getString(R.string.不定位))||name1.equals(Utils.getString(R.string.组六))){//新加的,需要显示全大小单双清
                            itemTwoHolder.quanTv.setVisibility(View.VISIBLE);
                            itemTwoHolder.daTv.setVisibility(View.VISIBLE);
                            itemTwoHolder.xiaoTv.setVisibility(View.VISIBLE);
                            itemTwoHolder.danTv.setVisibility(View.VISIBLE);
                            itemTwoHolder.shuangTv.setVisibility(View.VISIBLE);
                            itemTwoHolder.qingTv.setVisibility(View.VISIBLE);
                            //全大小单双清 选中状态处理
                            initIsQuick(itemTwoHolder, list, sscItemTwoAdapter, textViewArrayList,sscOfiicialBetMedol);
                        }else {
                            itemTwoHolder.quanTv.setVisibility(View.INVISIBLE);
                            itemTwoHolder.daTv.setVisibility(View.INVISIBLE);
                            itemTwoHolder.xiaoTv.setVisibility(View.INVISIBLE);
                            itemTwoHolder.danTv.setVisibility(View.INVISIBLE);
                            itemTwoHolder.shuangTv.setVisibility(View.INVISIBLE);
                            itemTwoHolder.qingTv.setVisibility(View.INVISIBLE);
                        }
                    }else {
                        itemTwoHolder.quanTv.setVisibility(View.VISIBLE);
                        itemTwoHolder.daTv.setVisibility(View.VISIBLE);
                        itemTwoHolder.xiaoTv.setVisibility(View.VISIBLE);
                        itemTwoHolder.danTv.setVisibility(View.VISIBLE);
                        itemTwoHolder.shuangTv.setVisibility(View.VISIBLE);
                        itemTwoHolder.qingTv.setVisibility(View.VISIBLE);
                        //全大小单双清 选中状态处理
                        initIsQuick(itemTwoHolder, list, sscItemTwoAdapter, textViewArrayList,sscOfiicialBetMedol);
                    }
                }
                //点击重置按钮,清空全大小单双清 按钮的状态
                ((SscBetActivity) context).setOnRandomListener(new SscBetActivity.OnRandomListener() {
                    @Override
                    public void onRandomClilck(ArrayList<SscOfiicialBetMedol> selectedList) {
                        for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                            SscOfiicialBetMedol medol = sscOfiicialBetMedolArrayList.get(j);
                            medol.setQuanSelecter(false);
                            medol.setDaSelecter(false);
                            medol.setXiaoSelecter(false);
                            medol.setDanSelecter(false);
                            medol.setShuangSelecter(false);
                            medol.setQingSelecter(false);
                        }
                        notifyDataSetChanged();
                    }
                });

            //投注成功 清空全大小单双清 按钮的状态
            ((SscBetActivity) context).setOnBetSucceseListener(new SscBetActivity.onBetSucceseListener() {
                @Override
                public void onBetSuccese(ArrayList<SscOfiicialBetMedol> selectedList) {
                    for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                        SscOfiicialBetMedol medol = sscOfiicialBetMedolArrayList.get(j);
                        medol.setQuanSelecter(false);
                        medol.setDaSelecter(false);
                        medol.setXiaoSelecter(false);
                        medol.setDanSelecter(false);
                        medol.setShuangSelecter(false);
                        medol.setQingSelecter(false);
                    }
                    notifyDataSetChanged();
                }
            });

        }

    }

    /**
     *
     * @param itemTwoHolder 布局2的ViewHolder
     * @param sscOfiicialBetMedols //布局2中子recycleVIew适配器数据
     * @param sscItemTwoAdapter//子recyceView适配器
     * @param textViewArrayList //存放 全大小单双清的 容器
     * @param sscOfiicialBetMedol//当前的数据model(外层recycleView)
     */
    public void initIsQuick(ItemTwoHolder itemTwoHolder, final ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedols, final SscItemTwoAdapter sscItemTwoAdapter, final ArrayList<TextView> textViewArrayList, final SscOfiicialBetMedol sscOfiicialBetMedol) {
        //Utils.getString(R.string.全) 按钮

        itemTwoHolder.quanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!v.isSelected()){
//                    initButtonStatus(textViewArrayList);//没被选中时点击,将所有button都设置为不选中(实现只能有一个butto是选中状态的效果)
                    initButtonStatus(sscOfiicialBetMedol);//没被选中时点击,将所有button都设置为不选中(实现只能有一个butto是选中状态的效果)
//                    v.setSelected(true);//将当前点击的设置为选中
                    sscOfiicialBetMedol.setQuanSelecter(true);//直接根据isQuanSelecter将当前点击的设置为选中

                    for (int j = 0; j < sscOfiicialBetMedols.size(); j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        sscOfiicialBetMedol.setStatus(1);
                        if(!selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.add(sscOfiicialBetMedol);
                        }
                    }
                }else {
//                    v.setSelected(false);
                    sscOfiicialBetMedol.setQuanSelecter(false);
                    for (int j = 0; j < sscOfiicialBetMedols.size(); j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        sscOfiicialBetMedol.setStatus(0);
                        selectorList.remove(sscOfiicialBetMedol);
                    }
                }
//                notifyDataSetChanged();
                sscItemTwoAdapter.notifyDataSetChanged();
                //此处不调用父adapter的notifyDataSetChanged方法(会和子adaptaer的notifyDataSetChanged冲突),
                // 所以根据model的isQuanSelecter来判断,直接调用textView的setSelected,没有改变数据源,则不需要调用notifyDataSetChanged
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);//将所有按钮都设为未选中
                    //判断isQuanSelecter,然后重新setSelected
                    if(sscOfiicialBetMedol.isQuanSelecter()){
                        textViewArrayList.get(0).setSelected(true);
                    }
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }
            }

        });

        //Utils.getString(R.string.大) 按钮
        itemTwoHolder.daTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = sscOfiicialBetMedols.size();
                if(!v.isSelected()){
                    initButtonStatus(sscOfiicialBetMedol);
                    sscOfiicialBetMedol.setDaSelecter(true);
                    //没被选中时点击,如果selectorList中包含当前sscOfiicialBetMedols中的model,需要先remove,并将stasus设置为0
                    //避免先点击其他按钮后,再点击当前按钮,selectorList中还是有当前上次点击添加的数据
                    //例:先点击Utils.getString(R.string.全)按钮后,再点击Utils.getString(R.string.大)按钮,selectorList中还是有当前sscOfiicialBetMedols的全部model
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.remove(sscOfiicialBetMedol);
                            sscOfiicialBetMedol.setStatus(0);
                        }
                    }
                    for (int j = 0; j < size; j++) {
                            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                            if(j>=size/2){
                                sscOfiicialBetMedol.setStatus(1);
                                if(!selectorList.contains(sscOfiicialBetMedol)){
                                    selectorList.add(sscOfiicialBetMedol);
                                }
                            }
                    }
                }else {
                    sscOfiicialBetMedol.setDaSelecter(false);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(j>=size/2){
                            sscOfiicialBetMedol.setStatus(0);
                            selectorList.remove(sscOfiicialBetMedol);
                        }

                    }
                }
                sscItemTwoAdapter.notifyDataSetChanged();
                //此处不调用父adapter的notifyDataSetChanged方法(会和子adaptaer的notifyDataSetChanged冲突),
                // 所以根据model的isDaSelecter来判断,直接调用textView的setSelected,没有改变数据源,则不需要调用notifyDataSetChanged
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);
                    if(sscOfiicialBetMedol.isDaSelecter()){
                        textViewArrayList.get(1).setSelected(true);
                    }
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }

            }
        });

        //Utils.getString(R.string.小) 按钮
        itemTwoHolder.xiaoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = sscOfiicialBetMedols.size();
                if(!v.isSelected()){
                    initButtonStatus(sscOfiicialBetMedol);
                    sscOfiicialBetMedol.setXiaoSelecter(true);
                    //没被选中时点击,如果selectorList中包含当前sscOfiicialBetMedols中的model,需要先remove,并将stasus设置为0
                    //避免先点击其他按钮后,再点击当前按钮,selectorList中还是有当前上次点击添加的数据
                    //例:先点击Utils.getString(R.string.大)按钮后,再点击Utils.getString(R.string.小)按钮,selectorList中还是有当前sscOfiicialBetMedols中属于Utils.getString(R.string.大)的model
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.remove(sscOfiicialBetMedol);
                            sscOfiicialBetMedol.setStatus(0);
                        }
                    }
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(j<size/2){
                            sscOfiicialBetMedol.setStatus(1);
                            if(!selectorList.contains(sscOfiicialBetMedol)){
                                selectorList.add(sscOfiicialBetMedol);
                            }
                        }
                    }
                }else {
//                    v.setSelected(false);
                    sscOfiicialBetMedol.setXiaoSelecter(false);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(j<size/2){
                            sscOfiicialBetMedol.setStatus(0);
                            selectorList.remove(sscOfiicialBetMedol);
                        }
                    }
                }
//                notifyDataSetChanged();
                sscItemTwoAdapter.notifyDataSetChanged();
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);
                    if(sscOfiicialBetMedol.isXiaoSelecter()){
                        textViewArrayList.get(2).setSelected(true);
                    }
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }
            }
        });

        //Utils.getString(R.string.单) 按钮
        itemTwoHolder.danTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = sscOfiicialBetMedols.size();
                if(!v.isSelected()){
//                    initButtonStatus(textViewArrayList);//全大小单双清 只能有一个是选中状态
                    initButtonStatus(sscOfiicialBetMedol);
//                    v.setSelected(true);
                    sscOfiicialBetMedol.setDanSelecter(true);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.remove(sscOfiicialBetMedol);
                            sscOfiicialBetMedol.setStatus(0);
                        }
                    }
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        String codes = sscOfiicialBetMedol.getCodes();
                        int code = Integer.parseInt(codes);
                        if(code%2==1){
                            sscOfiicialBetMedol.setStatus(1);
                            if(!selectorList.contains(sscOfiicialBetMedol)){
                                selectorList.add(sscOfiicialBetMedol);
                            }
                        }
                    }
                }else {
//                    v.setSelected(false);

                    sscOfiicialBetMedol.setDanSelecter(false);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        String codes = sscOfiicialBetMedol.getCodes();
                        int code = Integer.parseInt(codes);
                        if(code%2==1){
                            sscOfiicialBetMedol.setStatus(0);
                            selectorList.remove(sscOfiicialBetMedol);
                        }
                    }
                }
                sscItemTwoAdapter.notifyDataSetChanged();
//                notifyDataSetChanged();
                //此处不调用父adapter的notifyDataSetChanged方法(会和子adaptaer的notifyDataSetChanged冲突),所以根据model的isDanSelecter来判断,直接调用textView的setSelected,没有改变数据源,则不需要调用notifyDataSetChanged
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);
                    if(sscOfiicialBetMedol.isDanSelecter()){
                        textViewArrayList.get(3).setSelected(true);
                    }
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }
            }
        });


        //Utils.getString(R.string.双) 按钮
        itemTwoHolder.shuangTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = sscOfiicialBetMedols.size();
                if(!v.isSelected()){
//                    initButtonStatus(textViewArrayList);//全大小单双清 只能有一个是选中状态
                    initButtonStatus(sscOfiicialBetMedol);
//                    v.setSelected(true);
                    sscOfiicialBetMedol.setShuangSelecter(true);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.remove(sscOfiicialBetMedol);
                            sscOfiicialBetMedol.setStatus(0);
                        }
                    }
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        String codes = sscOfiicialBetMedol.getCodes();
                        int code = Integer.parseInt(codes);
                        if(code%2==0){
                            sscOfiicialBetMedol.setStatus(1);
                            if(!selectorList.contains(sscOfiicialBetMedol)){
                                selectorList.add(sscOfiicialBetMedol);
                            }
                        }
                    }
                }else {
//                    v.setSelected(false);

                    sscOfiicialBetMedol.setShuangSelecter(false);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        String codes = sscOfiicialBetMedol.getCodes();
                        int code = Integer.parseInt(codes);
                        if(code%2==0){
                            sscOfiicialBetMedol.setStatus(0);
                            selectorList.remove(sscOfiicialBetMedol);
                        }
                    }
                }
                sscItemTwoAdapter.notifyDataSetChanged();
//                notifyDataSetChanged();
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);
                    if(sscOfiicialBetMedol.isShuangSelecter()){
                        textViewArrayList.get(4).setSelected(true);
                    }
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }
            }
        });


        //Utils.getString(R.string.清) 按钮
        itemTwoHolder.qingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = sscOfiicialBetMedols.size();
//                    initButtonStatus(textViewArrayList);//全大小单双清 只能有一个是选中状态
                initButtonStatus(sscOfiicialBetMedol);
                    for (int j = 0; j < size; j++) {
                        SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedols.get(j);
                        if(selectorList.contains(sscOfiicialBetMedol)){
                            selectorList.remove(sscOfiicialBetMedol);
                            sscOfiicialBetMedol.setStatus(0);
                        }
                    }
//                notifyDataSetChanged();
                sscItemTwoAdapter.notifyDataSetChanged();
                for (int i = 0; i < textViewArrayList.size(); i++) {
                    TextView textView = textViewArrayList.get(i);
                    textView.setSelected(false);
                }
                if(onQuickClickLintener!=null){
                    onQuickClickLintener.onQuickClick(v,selectorList);
                }
            }
        });
    }

    private void initButtonStatus(SscOfiicialBetMedol sscOfiicialBetMedol ){
        sscOfiicialBetMedol.setQuanSelecter(false);
        sscOfiicialBetMedol.setDaSelecter(false);
        sscOfiicialBetMedol.setXiaoSelecter(false);
        sscOfiicialBetMedol.setDanSelecter(false);
        sscOfiicialBetMedol.setShuangSelecter(false);
        sscOfiicialBetMedol.setQingSelecter(false);

        }
    @Override
    public int getItemCount() {
        return sscOfiicialBetMedolArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        String id = sscOfiicialBetMedolArrayList.get(position).getId();
        String typeOneName = sscOfiicialBetMedolArrayList.get(position).getTypeOneName();
//        if(id.equals("2778")||id.equals("2779")||id.equals("2780")||id.equals("2781")||id.equals("2782")){
//            return 0;
//        }else {
//            return 1;
//        }
        if(typeOneName.equals(Utils.getString(R.string.大小单双))){
            return 0;
        }else {
            return 1;
        }
    }

    //viewHolderOne 点击回调
    public  static  interface  onItemOneClickListener{
        void onItemOneClick(View view,  ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedols,int position);
    }
    public SscOfficialAdapter.onItemOneClickListener onItemOneClickListener=null;

    public void setOnItemOneClickListener(SscOfficialAdapter.onItemOneClickListener onItemOneClickListener) {
        this.onItemOneClickListener = onItemOneClickListener;
    }


    public  static  interface  onItemFourClickListener{
        void onItemFourClick(View view,   SscOfiicialBetMedol sscOfiicialBetMedol,int position);
    }
        public SscOfficialAdapter.onItemFourClickListener onItemFourClickListener=null;

    public void setOnItemFourClickListener(SscOfficialAdapter.onItemFourClickListener onItemFourClickListener) {
        this.onItemFourClickListener = onItemFourClickListener;
    }
    //viewHolderOne 点击回调
    public  static  interface  onItemTwoClickListener{
        void onItemTwoClick(View view,  ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedols,int position);
    }
    public SscOfficialAdapter.onItemTwoClickListener onItemTwoClickListener=null;

    public void setOnItemTwoClickListener(SscOfficialAdapter.onItemTwoClickListener onItemTwoClickListener) {
        this.onItemTwoClickListener = onItemTwoClickListener;
    }
    //全大小单双清 点击回调
    public  static  interface  OnQuickClickLintener{
        void onQuickClick(View view,ArrayList<SscOfiicialBetMedol>officialSeleterList);
    }
    public SscOfficialAdapter.OnQuickClickLintener onQuickClickLintener=null;

    public void setOnQuickClickLintener(OnQuickClickLintener onQuickClickLintener) {
        this.onQuickClickLintener = onQuickClickLintener;
    }
    //
//    public  static  interface  InitQuickStatus{
//        void onQuickStatus(View view,ArrayList<SscOfiicialBetMedol>officialSeleterList);
//    }
//    public SscOfficialAdapter.InitQuickStatus initQuickStatus=null;
//
//    public void setInitQuickStatus(InitQuickStatus initQuickStatus) {
//        this.initQuickStatus = initQuickStatus;
//    }
    //itemOne  大小单双复式布局
    public class ItemOneHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private RecyclerView itemOneRecy;

        public ItemOneHolder(@NonNull View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.ssc_bet_item_one_name);

            itemOneRecy=itemView.findViewById(R.id.ssc_item_one_recycle);
        }
    }
    //itemTwo  大小单双复式以外的布局
    public class ItemTwoHolder extends RecyclerView.ViewHolder {
        private TextView itemTwoName;
        private RecyclerView itemTwoRecy;
        private TextView quanTv;
        private TextView daTv;
        private TextView xiaoTv;
        private TextView danTv;
        private TextView shuangTv;
        private TextView qingTv;
        public ItemTwoHolder(@NonNull View itemView) {
            super(itemView);
            itemTwoName=itemView.findViewById(R.id.ssc_official_item_two_name);
            itemTwoRecy=itemView.findViewById(R.id.ssc_official_bet_item_two_recycle);
            quanTv=itemView.findViewById(R.id.quan);
            daTv=itemView.findViewById(R.id.da);
            xiaoTv=itemView.findViewById(R.id.xiao);
            danTv=itemView.findViewById(R.id.dan);
            shuangTv=itemView.findViewById(R.id.shuang);
            qingTv=itemView.findViewById(R.id.qing);
        }
    }
}
