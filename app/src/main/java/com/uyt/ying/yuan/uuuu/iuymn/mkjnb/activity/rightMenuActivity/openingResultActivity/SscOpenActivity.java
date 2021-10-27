package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.SscOpenAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOpenResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
        /*
        时时彩 11选5 开奖结果actiivty
         */
public class SscOpenActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;
    private TextView chooseTimeText;
    private ImageView choosetimeIma;
    private LinearLayout chooseTimeLinear;
    private ArrayList<RadioButton> radioButtons =new ArrayList<>();
    private PopupWindow popupWindow;
    private TextView timeOne;
    private TextView timeTwo;
    private TextView timeThree;
    private TextView timeFour;
    private TextView timeFive;
    private TextView timeSix;
    private TextView timeSeven;
    private String todayDate;
    private View backgroundView;
    private RecyclerView mRecyleLeft;
    private SscOpenAdapter sscOpenAdapter;
    private ArrayList<SscOpenResultModel>sscOpenResultModels =new ArrayList<>();
    private RefreshLayout refreshLayout;//上拉加载 下拉刷新
    private int pageNum=1;
    private int type_id;//彩票type_id
    private int game;
    private int lastItemPosition;
    private int loadmoreCount=1;
    private LinearLayout nodataLinear;
    private ConstraintLayout loadingLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssc_open);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.开奖结果));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        Intent intent = getIntent();
        type_id = intent.getIntExtra("type_id", 1);//进入此activity需要传入的值
        game = intent.getIntExtra("game", 1);//进入此activity需要传入的值

        initPop();//时间选择pop相关初始化
        bindView();//控件绑定和点击事件的绑定
        initRecycleView();//recycleview的相关初始化
        initView();//主页面(除了recycleView部分)的相关初始化
        initRefresh();//下拉刷新 上拉加载相关初始化
    }


    private void bindView() {
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        refreshLayout=findViewById(R.id.refresh);//刷新控件
        sscOpenAdapter =new SscOpenAdapter(sscOpenResultModels,game);//recycleView适配器
        mRecyleLeft=findViewById(R.id.ssc_open_recycle);//recycleView
        one=findViewById(R.id.radio_button_one);//号码 按钮
        two=findViewById(R.id.radio_button_two);//大小 按钮
        three=findViewById(R.id.radio_button_three);//单双 按钮
        four=findViewById(R.id.radio_button_four);//亚冠/龙虎 按钮
        chooseTimeText=findViewById(R.id.time_choose);//默认的今天的时间textView
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        chooseTimeText.setText(todayDate);
        choosetimeIma=findViewById(R.id.time_choose_img);//时间textView右侧的img(每次点击需要旋转)
        chooseTimeLinear=findViewById(R.id.time_choose_linear);//点击弹出时间选择pop的控件
        //将四个按钮加入容器,后面通过遍历初始化按钮的选中状态
        radioButtons.add(one);
        radioButtons.add(two);
        radioButtons.add(three);
        radioButtons.add(four);
        /*
        点击事件相关
         */
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        chooseTimeLinear.setOnClickListener(this);
        one.performClick();
        mRecyleLeft.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState==RecyclerView.SCROLL_STATE_IDLE){//停止滚动或没有滚动
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    //判断是当前layoutManager是否为LinearLayoutManager
                    // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                        //获取最后一个可见view的位置
                        lastItemPosition = linearManager.findLastVisibleItemPosition();
                        //获取第一个可见view的位置
//                      firstItemPosition = linearManager.findFirstVisibleItemPosition();

                    }
                }
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }
    /*
    上拉加载 下拉刷新 相关初始化
     */
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(SscOpenActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(SscOpenActivity.this));
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;//每次上拉加载,pagenum递增
                loadmoreCount++;
                /*
                判断当前是哪个按钮选中(选中的按钮,决定了数据的处理和显示)
                 */
                if(one.isChecked()){//号码按钮选中
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,true);
                }else if(two.isChecked()){//大小按钮选中
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,true);
                }else  if(three.isChecked()){//单双按钮选中
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,true);
                }else if(four.isChecked()){//冠亚/龙虎按钮选中
                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,true);
                }
                refreshLayout.finishLoadMore();//结束加载
            }
        });

        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;//每次刷新,将pagenum初始化
                loadmoreCount=1;
                lastItemPosition=0;
                initRadioButon();
                refreshLayout.finishRefresh();//结束刷新
            }
        });
    }

    /**
     * 请求开奖结果的数据
     * @param pageNum 当前请求的页数(上拉加载时需要递增)
     * @param pageSize 每次请求的数据数量(此处固定为15条)
     * @param date     时间 (格式 2019-6-10)
     * @param typeId   彩票type_id(跳转时传入的值)
     * @param isNum    是否选中了Utils.getString(R.string.号码)按钮(需要进行数据处理)
     * @param isDaXiao 是否选中了Utils.getString(R.string.大小)按钮(需要进行数据处理)
     * @param isDanShuang 是否选中了Utils.getString(R.string.单双)按钮(需要进行数据处理)
     * @param isYaGuang  是否选中了冠亚/龙虎按钮(需要进行数据处理)
     * @param isLoadMore 是否是上拉加载时的调用(上拉加载时,不用清空recycleView的数据)
     */
    private void getDada(int pageNum, int pageSize, String date, int typeId, final boolean isNum, final boolean isDaXiao, final boolean isDanShuang, final boolean isYaGuang, final boolean isLoadMore,boolean showLoad) {
        if(showLoad){
            nodataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNum);
        if(isLoadMore){
            data.put("pageSize",pageSize);
        }else {
            data.put("pageSize",pageSize*loadmoreCount);
        }
        data.put("date",date);
        data.put("type_id",typeId);
        String url="";
        if(game==9){
            url=RequestUtil.REQUEST_04xuanwu;
        }else{
            url=  RequestUtil.REQUEST_19r;
        }

        Utils.docking(data,url , 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                JSONObject jsonObject1= JSONObject.parseObject(content);
                JSONArray lotterylist=null;
                if(game==9){
                    lotterylist=jsonObject1.getJSONArray("xuanwuLotterylist");
                }else {
                    lotterylist    = jsonObject1.getJSONArray("sscaiLotterylist");
                }
                int size = lotterylist.size();
                if(!isLoadMore){//不是上拉加载,清空数据,即每次请求都是全新的数据
                    sscOpenResultModels.clear();
                }
                if(size==0){//后台返回的数据容器为0时, 初始化refreshLayout的状态
                    if(!isLoadMore){
                        nodataLinear.setVisibility(View.VISIBLE);
                    }
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    nodataLinear.setVisibility(View.GONE);
                }
                for (int i = 0; i < lotterylist.size(); i++) {
                    JSONObject jsonObject = lotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//彩票期数
                    String createdDate = jsonObject.getString("lotterytime");//时间
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//形态中的和值
                    String markdx = jsonObject.getString("markdx");//形态中的大小
                    String markds = jsonObject.getString("markds");//形态中的单双
                    String marklh = jsonObject.getString("marklh");//形态中的龙虎
                    String markqs = jsonObject.getString("markqs");//形态中的形态的第一个
                    String markzs = jsonObject.getString("markzs");//形态中的形态的第二个
                    String markhs = jsonObject.getString("markhs");//形态中的形态的第三个
                    String markwdx="";
                    //  11选5
                    if(game==9){
                        markwdx = jsonObject.getString("markwdx");//11选5位大小
                    }
                    String[] split = lotteryNo.split(",");
                    List<String> strings = Arrays.asList(split);
                    if(isNum){//选中了数字按钮, 直接显示lotteryNo,
                        if(game!=9){
                            sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,lotteryNo,sum,markdx,markds,marklh,markqs,markzs,markhs,false,lotteryNo));
                        }else {
                            sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,lotteryNo,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,false,lotteryNo));

                        }
                    }else if(isDaXiao){//选中了大小按钮 (需要将lotteryNo转换成Utils.getString(R.string.大小),并拼接成适配器需要的参数格式(用逗号拼接 ))
                        if(game==9){
                            String needString = "";
                            for (String s: strings) {
                                int i1 = Integer.parseInt(s);
                                if(i1<=5){
                                    s=Utils.getString(R.string.小);
                                }else if(i1>=6&&i1<11){
                                    s=Utils.getString(R.string.大);
                                }else {//和
                                    s=Utils.getString(R.string.和);
                                }
                                needString+=s+"," ;
                            }//gradlew installXunboDebuggra
                            needString = needString.substring(0, needString.length()-1);
                            if(game!=9){
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,false,lotteryNo));
                            }else{
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,false,lotteryNo));
                            }
                        }else {
                            String needString = "";
                            for (String s: strings) {
                                int i1 = Integer.parseInt(s);
                                if(i1<=4){
                                    s=Utils.getString(R.string.小);
                                }else if(i1>=5){
                                    s=Utils.getString(R.string.大);
                                }
                                needString+=s+"," ;
                            }
                            needString = needString.substring(0, needString.length()-1);
                            if(game!=9){
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,false,lotteryNo));
                            }else{
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,false,lotteryNo));
                            }
                        }
                    }else if(isDanShuang){//选中了单双按钮 (需要将lotteryNo转换成Utils.getString(R.string.单双),并拼接成适配器需要的参数格式(用逗号拼接 ))
                        if(game==9){
                            String needString = "";
                            for (String s: strings) {
                                int i1 = Integer.parseInt(s);
                                if(i1==11){//和
                                    s=Utils.getString(R.string.和);
                                }
                                else if(i1%2!=0){
                                    s=Utils.getString(R.string.单);
                                }
                                else {
                                    s=Utils.getString(R.string.双);
                                }
                                needString+=s+"," ;
                            }
                            needString = needString.substring(0, needString.length()-1);
                            if(game!=9){
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,false,lotteryNo));
                            }else {
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,false,lotteryNo));
                            }
                        }else {
                            String needString = "";
                            for (String s: strings) {
                                int i1 = Integer.parseInt(s);

                                if(i1%2!=0){
                                    s=Utils.getString(R.string.单);
                                }
                                else {
                                    s=Utils.getString(R.string.双);
                                }
                                needString+=s+"," ;
                            }
                            needString = needString.substring(0, needString.length()-1);
                            if(game!=9){
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,false,lotteryNo));
                            }else {
                                sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,false,lotteryNo));
                            }
                        }
                    }
                    else if(isYaGuang){//形态/总和
                        if(game !=9){
                            sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,lotteryNo,sum,markdx,markds,marklh,markqs,markzs,markhs,true,lotteryNo));
                        }else {
                            sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,lotteryNo,sum,markdx,markds,marklh,markqs,markzs,markhs,markwdx,true,lotteryNo));
                        }
                    }

                }
//                if(sscOpenResultModels.size()>=lastItemPosition){
//                    if(mRecyleLeft!=null&&mRecyleLeft.getLayoutManager()!=null){
//                        mRecyleLeft.getLayoutManager().scrollToPosition(lastItemPosition);
//                    }
//                }
                sscOpenAdapter.notifyDataSetChanged();
//                System.out.println("loadmoreCount = "+loadmoreCount+">>>> pageSize=  "+pageSize+" lastPosition =  "+lastItemPosition+" sscOpenResultModels.size() =  "+sscOpenResultModels.size());
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);

            }
        });
    }

    private void initSwich(String name){
        for (int i = 0; i < sscOpenResultModels.size(); i++) {
            SscOpenResultModel sscOpenResultModel = sscOpenResultModels.get(i);
            String numList = sscOpenResultModel.getNumListCopy();
            String[] split = numList.split(",");
            List<String> strings = Arrays.asList(split);
            if(name.equals(Utils.getString(R.string.大小))){
                if(game==9){
                    String needString = "";
                    for (String s: strings) {
                        int i1 = Integer.parseInt(s);
                        if(i1<=5){
                            s=Utils.getString(R.string.小);
                        }else if(i1>=6&&i1<11){
                            s=Utils.getString(R.string.大);
                        }else {//和
                            s=Utils.getString(R.string.和);
                        }
                        needString+=s+"," ;
                    }
                    needString = needString.substring(0, needString.length()-1);
                    sscOpenResultModel.setNumList(needString);
                    sscOpenResultModel.setGuanYa(false);
//                    sscOpenResultModels.add(new SscOpenResultModel(lotteryqishu,createdDate,needString,sum,markdx,
//                            "","","","","",markwdx,false));
                }else {
                    String needString = "";
                    for (String s: strings) {
                        int i1 = Integer.parseInt(s);
                        if(i1<=4){
                            s=Utils.getString(R.string.小);
                        }else if(i1>=5){
                            s=Utils.getString(R.string.大);
                        }
                        needString+=s+"," ;
                    }
                    needString = needString.substring(0, needString.length()-1);
                    sscOpenResultModel.setNumList(needString);
                    sscOpenResultModel.setGuanYa(false);
                }

            }else if(name.equals(Utils.getString(R.string.单双))){
                if(game==9){
                    String needString = "";
                    for (String s: strings) {
                        int i1 = Integer.parseInt(s);
                        if(i1==11){//和
                            s=Utils.getString(R.string.和);
                        }
                        else if(i1%2!=0){
                            s=Utils.getString(R.string.单);
                        }
                        else {
                            s=Utils.getString(R.string.双);
                        }
                        needString+=s+"," ;
                    }
                    needString = needString.substring(0, needString.length()-1);
                    sscOpenResultModel.setNumList(needString);
                    sscOpenResultModel.setGuanYa(false);
                }else {
                    String needString = "";
                    for (String s: strings) {
                        int i1 = Integer.parseInt(s);
                        if(i1%2!=0){
                            s=Utils.getString(R.string.单);
                        }
                        else {
                            s=Utils.getString(R.string.双);
                        }
                        needString+=s+"," ;
                    }
                    needString = needString.substring(0, needString.length()-1);
                    sscOpenResultModel.setNumList(needString);
                    sscOpenResultModel.setGuanYa(false);
                }
            }else if(name.equals(Utils.getString(R.string.号码))){
                    sscOpenResultModel.setNumList(sscOpenResultModel.getNumListCopy());
                    sscOpenResultModel.setGuanYa(false);
            }else if(name.equals(Utils.getString(R.string.总和/形态))){
              sscOpenResultModel.setGuanYa(true);
            }
        }
        sscOpenAdapter.notifyDataSetChanged();
    }

    /*
    recycleView相关初始化
     */
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyleLeft.setLayoutManager(linearLayoutManager);
        mRecyleLeft.setAdapter(sscOpenAdapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        //默认请求今天的数据
        getDada(1,15,todayDate,type_id,true,false,false,false,false,true);
//        getDada(1,15,todayDate,type_id,true,false,false,false,false);
    }
    /*
    时间选择的pop弹窗
     */
    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.race_open_result_time_pop, null);
        backgroundView=view.findViewById(R.id.background_view);//pop下面的阴影部分
        timeOne =view.findViewById(R.id.time_one);
        timeTwo =view.findViewById(R.id.time_two);
        timeThree =view.findViewById(R.id.time_three);
        timeFour =view.findViewById(R.id.time_four);
        timeFive =view.findViewById(R.id.time_five);
        timeSix =view.findViewById(R.id.time_six);
        timeSeven =view.findViewById(R.id.time_seven);
        timeOne.setOnClickListener(this);
        timeTwo.setOnClickListener(this);
        timeThree.setOnClickListener(this);
        timeFour.setOnClickListener(this);
        timeFive.setOnClickListener(this);
        timeSix.setOnClickListener(this);
        timeSeven.setOnClickListener(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        Date dateAfter1 = DateUtil.getDateBefore(date, 1);//1天前的日期
        Date dateAfter2 = DateUtil.getDateBefore(date, 2);//2天前的日期
        Date dateAfter3 = DateUtil.getDateBefore(date, 3);//3天前的日期
        Date dateAfter4 = DateUtil.getDateBefore(date, 4);//4天前的日期
        Date dateAfter5 = DateUtil.getDateBefore(date, 5);//5天前的日期
        Date dateAfter6 = DateUtil.getDateBefore(date, 6);//6天前的日期
        timeOne.setText(todayDate);
        timeTwo.setText(simpleDateFormat.format(dateAfter1));
        timeThree.setText(simpleDateFormat.format(dateAfter2));
        timeFour.setText(simpleDateFormat.format(dateAfter3));
        timeFive.setText(simpleDateFormat.format(dateAfter4));
        timeSix.setText(simpleDateFormat.format(dateAfter5));
        timeSeven.setText(simpleDateFormat.format(dateAfter6));
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        backgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();//点击阴影部分,弹回pop(因为这里的布局是MATCH_PARENT,点击弹回,相当于响应外部点击)
            }
        });
        //pop的弹回监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {//pop弹回时,将图片旋转恢复
                choosetimeIma.setPivotX(choosetimeIma.getWidth()/2);
                choosetimeIma.setPivotY(choosetimeIma.getHeight()/2);//支点在图片中心
                choosetimeIma.setRotation(0);
//                 backgroundView.getBackground().setAlpha(100);
            }
        });
    }
    //初始化当前时间,并显示在textView
    public void initView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        todayDate = simpleDateFormat.format(date);//今天日期
        chooseTimeText.setText(todayDate);
    }

    @Override
    protected void init() {

    }
    /*
    每次点击 号码 大小 单双 冠亚/龙虎 按钮中的一个, 将其他3个的选中状态初始化
    并将refreshLayout和pageNum的状态初始化
     */
    private void initRadioClick(RadioButton radioButton) {
        for (int i = 0; i < radioButtons.size();i++) {
            radioButtons.get(i).setChecked(false);
        }
        radioButton.setChecked(true);
        //每次点击 号码 大小 单双 冠亚/龙虎 按钮时,都是调用这个方法,然后请求接口,所以在这里初始化pageNum和refreshLayout的状态
        //避免一次上拉加载到没有数据时, 下一次请求数据后,上拉加载时,还会没有数据加载
        //还有一处初始化是在每次点击pop,按日期来请求数据时
        pageNum=1;
        refreshLayout.resetNoMoreData();
    }
    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_button_one://号码按钮
                initRadioClick(one);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false);
                initSwich(Utils.getString(R.string.号码));
                break;
            case R.id.radio_button_two://大小按钮
                initRadioClick(two);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false);
                initSwich(Utils.getString(R.string.大小));
                break;
            case R.id.radio_button_three://单双 按钮
                initRadioClick(three);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,false);
                initSwich(Utils.getString(R.string.单双));
                break;
            case R.id.radio_button_four://冠亚龙虎
                initRadioClick(four);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,false);
//                getDada(1,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,false);
                initSwich(Utils.getString(R.string.总和/形态));
                break;
            case R.id.time_choose_linear://点击弹出时间选择pop
                popupWindow.showAsDropDown(chooseTimeLinear,0,0, Gravity.BOTTOM);
                //图片旋转
                choosetimeIma.setPivotX(choosetimeIma.getWidth()/2);
                choosetimeIma.setPivotY(choosetimeIma.getHeight()/2);//支点在图片中心
                choosetimeIma.setRotation(180);
                backgroundView.getBackground().setAlpha(50);
                break;
            case R.id.time_one://pop的第一个数据(没次点击pop,都将点击的时间值,显示在chooseTimeText)(请求数据时的date,就是chooseTimeText的getText())
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeOne.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_two://pop的第二个数据.....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeTwo.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_three://三....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeThree.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_four://四....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeFour.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_five://五....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeFive.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_six://六....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeSix.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_seven://七....
                loadmoreCount=1;
                lastItemPosition=0;
                chooseTimeText.setText(timeSeven.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            default:
                break;
        }
    }

    private void initRadioButon() {
        //每次点击 号码 大小 单双 冠亚/龙虎 按钮时,都是调用这个方法,然后请求接口,所以在这里初始化pageNum和refreshLayout的状态
        //避免一次上拉加载到没有数据时, 下一次请求数据后,上拉加载时,还会没有数据加载
        //还有一处初始化是在每次点击 号码 大小 单双 冠亚龙虎按钮时
        pageNum=1;
        refreshLayout.resetNoMoreData();
        if(one.isChecked()){
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false);
        }else if(two.isChecked()){
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false);
        }else  if(three.isChecked()){
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,false);
        }else if(four.isChecked()){
            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,false);
        }
    }


            @Override
            public void onNetChange(boolean netWorkState) {

            }
        }
