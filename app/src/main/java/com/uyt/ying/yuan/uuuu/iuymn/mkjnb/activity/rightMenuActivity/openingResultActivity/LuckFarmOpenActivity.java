package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity;

import android.content.Intent;
import androidx.annotation.NonNull;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.FarmOpenAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.FarmOpenResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
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
广东快乐10分  重庆幸运农场 开奖结果activity
 */
public class LuckFarmOpenActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;
    private TextView chooseTimeText;
    private ImageView choosetimeIma;
    private LinearLayout chooseTimeLinear;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
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
    private FarmOpenAdapter farmOpenAdapter;
    private ArrayList<FarmOpenResultModel> farmOpenResultModelArrayList = new ArrayList<>();
    private RefreshLayout refreshLayout;//上拉加载 下拉刷新
    private int pageNum = 1;
    private int type_id;//彩票type_id
    private int game;//彩票game(用于判断是请求哪种彩票的开奖结果)
    private LinearLayout nodataLinear;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_farm_open);
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
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        refreshLayout = findViewById(R.id.refresh);//刷新控件
        farmOpenAdapter = new FarmOpenAdapter(farmOpenResultModelArrayList);//recycleView适配器
        mRecyleLeft = findViewById(R.id.farm_open_recycle);//recycleView
        one = findViewById(R.id.radio_button_one);//号码 按钮
        two = findViewById(R.id.radio_button_two);//大小 按钮
        three = findViewById(R.id.radio_button_three);//单双 按钮
        four = findViewById(R.id.radio_button_four);//总和/形态 按钮
        chooseTimeText = findViewById(R.id.time_choose);//默认的今天的时间textView
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        chooseTimeText.setText(todayDate);
        choosetimeIma = findViewById(R.id.time_choose_img);//时间textView右侧的img(每次点击需要旋转)
        chooseTimeLinear = findViewById(R.id.time_choose_linear);//点击弹出时间选择pop的控件
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
    }

    /*
    上拉加载 下拉刷新 相关初始化
     */
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(LuckFarmOpenActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(LuckFarmOpenActivity.this));
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;//每次上拉加载,pagenum递增
                /*
                判断当前是哪个按钮选中(选中的按钮,决定了数据的处理和显示)
                 */
                if (one.isChecked()) {//号码按钮选中
                    getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, true, false, false, false, true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,true);
                } else if (two.isChecked()) {//大小按钮选中
                    getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, true, false, false, true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,true);
                } else if (three.isChecked()) {//单双按钮选中
                    getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, false, true, false, true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,true);
                } else if (four.isChecked()) {//冠亚/龙虎按钮选中
                    getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, false, false, true, true,false);
//                    getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,true);
                }
                refreshLayout.finishLoadMore();//结束加载
            }
        });

        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;//每次刷新,将pagenum初始化
                initRadioButon();
                refreshLayout.finishRefresh();//结束刷新
            }
        });
    }

    /**
     * 请求开奖结果的数据
     *
     * @param pageNum     当前请求的页数(上拉加载时需要递增)
     * @param pageSize    每次请求的数据数量(此处固定为15条)
     * @param date        时间 (格式 2019-6-10)
     * @param typeId      彩票type_id(跳转时传入的值)
     * @param isNum       是否选中了Utils.getString(R.string.号码)按钮(需要进行数据处理)
     * @param isDaXiao    是否选中了Utils.getString(R.string.大小)按钮(需要进行数据处理)
     * @param isDanShuang 是否选中了Utils.getString(R.string.单双)按钮(需要进行数据处理)
     * @param isYaGuang   是否选中了冠亚/龙虎按钮(需要进行数据处理)
     * @param isLoadMore  是否是上拉加载时的调用(上拉加载时,不用清空recycleView的数据)
     */
    private void getDada(int pageNum, int pageSize, String date, int typeId, final boolean isNum, final boolean isDaXiao, final boolean isDanShuang, final boolean isYaGuang, final boolean isLoadMore,boolean showLoad) {
        if(showLoad){
            nodataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo", pageNum);
        data.put("pageSize", pageSize);
        data.put("date", date);
        data.put("type_id", typeId);
        String url = "";
        if (game == 7) {//重庆幸运农场
            url = RequestUtil.REQUEST_04farm;
        } else {//快乐10分
            url = RequestUtil.REQUEST_04happyten;
        }
        Utils.docking(data, url, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(LuckFarmOpenActivity.this);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray Lotterylist = null;
                if (game == 7) {
                    Lotterylist = jsonObject1.getJSONArray("farmLotterylist");
                } else {
                    Lotterylist = jsonObject1.getJSONArray("happytenLotterylist");
                }
                int size = Lotterylist.size();
                if (!isLoadMore) {//不是上拉加载,清空数据,即每次请求都是全新的数据
                    farmOpenResultModelArrayList.clear();
                }
                if (size == 0) {//后台返回的数据容器为0时, 初始化refreshLayout的状态
                    if(!isLoadMore){

                        nodataLinear.setVisibility(View.VISIBLE);
                    }
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    nodataLinear.setVisibility(View.GONE);
                }
                for (int i = 0; i < Lotterylist.size(); i++) {
                    JSONObject jsonObject = Lotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//彩票期数
                    String createdDate = jsonObject.getString("lotterytime");//时间
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//总和/形态中的和
                    String markdx = jsonObject.getString("markdx");//总和/形态中的大小
                    String markds = jsonObject.getString("markds");//总和/形态中的单双
                    String markwdx = jsonObject.getString("markwdx");//总和/形态中的尾大小
//                    JSONArray marklh = jsonObject.getJSONArray("marklh");
                    List<String> marklh = JSONObject.parseArray(jsonObject.getString("marklh"), String.class);//得到json中的数组数据(冠亚龙虎的数据)

                    String[] split = lotteryNo.split(",");
                    List<String> strings = Arrays.asList(split);
                    if (isNum) {//选中了数字按钮, 直接显示lotteryNo,
                        farmOpenResultModelArrayList.add(new FarmOpenResultModel(lotteryqishu, createdDate, lotteryNo, sum, markdx, markds, markwdx, "", false,marklh,lotteryNo));
                    } else if (isDaXiao) {//选中了大小按钮 (需要将lotteryNo转换成Utils.getString(R.string.大小),并拼接成适配器需要的参数格式(用逗号拼接 ))
                        String needString = "";
                        for (String s : strings) {
                            int i1 = Integer.parseInt(s);
                            if (i1 <=10) {
                                s = Utils.getString(R.string.小);
                            } else if (i1 >= 11) {
                                s = Utils.getString(R.string.大);
                            }
                            needString += s + ",";
                        }
                        needString = needString.substring(0, needString.length() - 1);
                        farmOpenResultModelArrayList.add(new FarmOpenResultModel(lotteryqishu, createdDate, needString, sum, markdx, markds, markwdx, "", false,marklh,lotteryNo));
                    } else if (isDanShuang) {//选中了单双按钮 (需要将lotteryNo转换成Utils.getString(R.string.单双),并拼接成适配器需要的参数格式(用逗号拼接 ))
                        String needString = "";
                        for (String s : strings) {
                            int i1 = Integer.parseInt(s);
                            if (i1 % 2 != 0) {
                                s = Utils.getString(R.string.单);
                            } else {
                                s = Utils.getString(R.string.双);
                            }
                            needString += s + ",";
                        }
                        needString = needString.substring(0, needString.length() - 1);
                        farmOpenResultModelArrayList.add(new FarmOpenResultModel(lotteryqishu, createdDate, needString, sum, markdx, markds, markwdx, "", false,marklh,lotteryNo));
                    } else if (isYaGuang) {//选中了冠亚/龙虎(需要将数据转换成适配器需要的参数格式(用逗号拼接))
                        String needString = "";
                        for (String s : marklh) {//遍历拼接marklh
                            needString += s + ",";
                        }
                        needString = needString.substring(0, needString.length() - 1);
                        farmOpenResultModelArrayList.add(new FarmOpenResultModel(lotteryqishu, createdDate, lotteryNo, sum, markdx, markds, markwdx, needString, true,marklh,lotteryNo));
                    }
                }

                farmOpenAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(LuckFarmOpenActivity.this);
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
            }
        });

    }
    private void initSwich(String type){
        for (int i = 0; i < farmOpenResultModelArrayList.size(); i++) {
            FarmOpenResultModel farmOpenResultModel = farmOpenResultModelArrayList.get(i);
            //lotteryqishu, createdDate, lotteryNo, sum, markdx, markds, markwdx, needString, true,marklh,lotteryNo
            String numListCopy = farmOpenResultModel.getNumListCopy();
            List<String> strings = Arrays.asList(numListCopy.split(","));
            String sum = farmOpenResultModel.getHezhi();
            String markdx = farmOpenResultModel.getDaxiao();
            String markds = farmOpenResultModel.getDanshuang();
            String markwdx = farmOpenResultModel.getWeida();
            List<String> longHuList = farmOpenResultModel.getLongHuList();
            if(type.equals(Utils.getString(R.string.号码))){
                farmOpenResultModel.setNumList(numListCopy);
                farmOpenResultModel.setGuanYa(false);
            }else if(type.equals(Utils.getString(R.string.大小))){
                String needString = "";
                for (String s : strings) {
                    int i1 = Integer.parseInt(s);
                    if (i1 <=10) {
                        s = Utils.getString(R.string.小);
                    } else if (i1 >= 11) {
                        s = Utils.getString(R.string.大);
                    }
                    needString += s + ",";
                }
                needString = needString.substring(0, needString.length() - 1);
                farmOpenResultModel.setNumList(needString);
                farmOpenResultModel.setGuanYa(false);
            }else if(type.equals(Utils.getString(R.string.单双))){
                String needString = "";
                for (String s : strings) {
                    int i1 = Integer.parseInt(s);
                    if (i1 % 2 != 0) {
                        s = Utils.getString(R.string.单);
                    } else {
                        s = Utils.getString(R.string.双);
                    }
                    needString += s + ",";
                }
                needString = needString.substring(0, needString.length() - 1);
                farmOpenResultModel.setNumList(needString);
                farmOpenResultModel.setGuanYa(false);
            }else {
                String needString = "";
                for (String s : longHuList) {//遍历拼接marklh
                    needString += s + ",";
                }
                needString = needString.substring(0, needString.length() - 1);
                farmOpenResultModel.setMarklh(needString);
                farmOpenResultModel.setGuanYa(true);
            }
        }
        farmOpenAdapter.notifyDataSetChanged();
    }
    /*
    recycleView相关初始化
     */
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyleLeft.setLayoutManager(linearLayoutManager);
        mRecyleLeft.setAdapter(farmOpenAdapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        //默认请求今天的数据
        getDada(1, 15, todayDate, type_id, true, false, false, false, false,true);
//        getDada(1,15,todayDate,type_id,true,false,false,false,false);
    }

    /*
    时间选择的pop弹窗
     */
    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.race_open_result_time_pop, null);
        backgroundView = view.findViewById(R.id.background_view);//pop下面的阴影部分
        timeOne = view.findViewById(R.id.time_one);
        timeTwo = view.findViewById(R.id.time_two);
        timeThree = view.findViewById(R.id.time_three);
        timeFour = view.findViewById(R.id.time_four);
        timeFive = view.findViewById(R.id.time_five);
        timeSix = view.findViewById(R.id.time_six);
        timeSeven = view.findViewById(R.id.time_seven);
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
        popupWindow.setAnimationStyle(R.style.popupAnimationNormol);//设置进出动画
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
                choosetimeIma.setPivotX(choosetimeIma.getWidth() / 2);
                choosetimeIma.setPivotY(choosetimeIma.getHeight() / 2);//支点在图片中心
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
        for (int i = 0; i < radioButtons.size(); i++) {
            radioButtons.get(i).setChecked(false);
        }
        radioButton.setChecked(true);
        //每次点击 号码 大小 单双 冠亚/龙虎 按钮时,都是调用这个方法,然后请求接口,所以在这里初始化pageNum和refreshLayout的状态
        //避免一次上拉加载到没有数据时, 下一次请求数据后,上拉加载时,还会没有数据加载
        //还有一处初始化是在每次点击pop,按时间来请求数据时
        pageNum = 1;
        refreshLayout.resetNoMoreData();
    }

    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button_one://号码按钮
                initRadioClick(one);
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, true, false, false, false, false);
                initSwich(Utils.getString(R.string.号码));
                break;
            case R.id.radio_button_two://大小按钮
                initRadioClick(two);
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, false, true, false, false, false);
                initSwich(Utils.getString(R.string.大小));
                break;
            case R.id.radio_button_three://单双 按钮
                initRadioClick(three);
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, false, false, true, false, false);
                initSwich(Utils.getString(R.string.单双));
               break;
            case R.id.radio_button_four://总和/形态
                initRadioClick(four);
//                getDada(1, 15, chooseTimeText.getText().toString(), type_id, false, false, false, true, false);
                initSwich(Utils.getString(R.string.总和形态));
                break;
            case R.id.time_choose_linear://点击弹出时间选择pop
                popupWindow.showAsDropDown(chooseTimeLinear, 0, 0, Gravity.BOTTOM);
                //图片旋转
                choosetimeIma.setPivotX(choosetimeIma.getWidth() / 2);
                choosetimeIma.setPivotY(choosetimeIma.getHeight() / 2);//支点在图片中心
                choosetimeIma.setRotation(180);
                backgroundView.getBackground().setAlpha(50);
                break;
            case R.id.time_one://pop的第一个数据(每次点击pop,都将点击的时间值,显示在chooseTimeText)(请求数据时的date,就是chooseTimeText的getText())
                chooseTimeText.setText(timeOne.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_two://pop的第二个数据.....
                chooseTimeText.setText(timeTwo.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_three://三....
                chooseTimeText.setText(timeThree.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_four://四....
                chooseTimeText.setText(timeFour.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_five://五....
                chooseTimeText.setText(timeFive.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_six://六....
                chooseTimeText.setText(timeSix.getText().toString());
                popupWindow.dismiss();
                initRadioButon();
                break;
            case R.id.time_seven://七....
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
        pageNum = 1;
        refreshLayout.resetNoMoreData();
        if (one.isChecked()) {
            getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, true, false, false, false, false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,true,false,false,false,false);
        } else if (two.isChecked()) {
            getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, true, false, false, false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,true,false,false,false);
        } else if (three.isChecked()) {
            getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, false, true, false, false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,true,false,false);
        } else if (four.isChecked()) {
            getDada(pageNum, 15, chooseTimeText.getText().toString(), type_id, false, false, false, true, false,true);
//            getDada(pageNum,15,chooseTimeText.getText().toString(),type_id,false,false,false,true,false);
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
