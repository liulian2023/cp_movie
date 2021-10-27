package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ChangLongActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetAcitivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.Happy8OpentActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.KuaiSanOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.LuckFarmOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.PcDanOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.RaceOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.SixOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.SscOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.XuanWuOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.today_win_lose.TodayWinLoseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.twoLongDragon.TwoLongDragonActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.bet_activity_adpter.LotteryClassPopRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.Happy10OPenResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.Happy8OPenResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.KuaiSanOpenResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.PcddOpentTodayResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscTodayOpenAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.MarksixOpenresultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RaceOpenresultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy10OpenResultMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8OpenResultMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KuaiSanTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PcddTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscTodayOpenMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class CustomPopupWindow {
    /*
    彩票分类pop
     */
    public  RecyclerView classfyRecycle;
    public  ArrayList<LotteryClassModel> lotteryClassModelArrayList =new ArrayList<>();
    public  ArrayList<LotteryClassModel> lotteryClassModelArrayListTwo =new ArrayList<>();
    public  LotteryClassPopRecycleAdapter lotteryClassPopRecycleAdapter;
    public   PopupWindow classfyPop;
    /*
    右侧菜单pop
     */
    public   PopupWindow menuPop;
    public  TextView betNote;//右侧菜单中 Utils.getString(R.string.投注记录)
    public  TextView openResult;//右侧菜单中 Utils.getString(R.string.开奖结果)
    public  TextView gameRule;//右侧菜单中 Utils.getString(R.string.游戏规则)
    public  TextView twoCahngLongTv;//右侧菜单中 Utils.getString(R.string.两面长龙)
    public  TextView investTv;//右侧菜单中 Utils.getString(R.string.充值中心)
    public  TextView mineCenterTv;//右侧菜单中 Utils.getString(R.string.会员中心)
    public  TextView todayWinLoseTv;//右侧菜单中 Utils.getString(R.string.今日输赢)
    public  TextView onlineKf;//右侧菜单中Utils.getString(R.string.在线客服)

    /*
    快三投注页面当天的开奖结果pop
     */

    public  PopupWindow kuaiSanTodayOpenResultPop;
    public  MyCornerTextView kuaiSanshowMore; //查看更多按钮
    public  RecyclerView todayResultRecy;//recycleView
    private  KuaiSanOpenResultAdapter kuaiSanKuaiSanOpenResultAdapter;//适配器
    private  ArrayList<KuaiSanTodayResultModel> kuaiSanTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器
    private  ConstraintLayout kuaisanLoadIngLinear;

    /*        *//*
    六合彩投注页面当天的开奖结果pop
     *//*

    public  PopupWindow sixTodayOpenResultPop;
    public  MyCornerTextView sixshowMore; //查看更多按钮
    public  RecyclerView sixTodayResultRecy;//recycleView
    private  KuaiSanOpenResultAdapter sixKuaiSanOpenResultAdapter;//适配器
    private  ArrayList<KuaiSanTodayResultModel> sixTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器*/


        /*
    pcdd投注页面当天的开奖结果pop
     */

    public  PopupWindow pcddTodayOpenResultPop;
    public  MyCornerTextView pcddshowMore; //查看更多按钮
    public  RecyclerView pcddtodayResultRecy;//recycleView
    private  ConstraintLayout pcddLoadingLinear;
    private  PcddOpentTodayResultAdapter pcddOpenResultAdapter;//适配器
    private  ArrayList<PcddTodayResultModel> pcddTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器

    /*
    北京快乐8投注页面当天的开奖结果pop
     */

    public  PopupWindow bjTodayOpenResultPop;
    public  MyCornerTextView bjshowMore; //查看更多按钮
    public  RecyclerView bjtodayResultRecy;//recycleView
    private  ConstraintLayout bjLoadingLinear;
    private  Happy8OPenResultAdapter  happy8OPenResultAdapter;//适配器
    private  ArrayList<Happy8OpenResultMedol> bjTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器
    /*
    北京快乐10投注页面当天的开奖结果pop
     */

    public  PopupWindow happy10TodayOpenResultPop;
    public  MyCornerTextView happy10showMore; //查看更多按钮
    public  RecyclerView happy10todayResultRecy;//recycleView
    private  ConstraintLayout happy10LoadingLinear;
    private  Happy10OPenResultAdapter happy10OPenResultAdapter;//适配器
    private  ArrayList<Happy10OpenResultMedol> happy10TodayResultModelArrayList =new ArrayList<>();//recycleView数据容器


    /*
      ssc投注页面当天的开奖结果pop
     */

    public  PopupWindow sscTodayOpenResultPop;
    public  MyCornerTextView sscShowMore; //查看更多按钮
    public  RecyclerView sscTodayResultRecy;//recycleView
    private  ConstraintLayout sscLoadingLinear;
    private  SscTodayOpenAdapter sscTodayOpenAdapter;//适配器
    private  ArrayList<SscTodayOpenMedol> sscTodayOpenMedolArrayList =new ArrayList<>();//recycleView数据容器

    public  PopupWindow raceTodayOpenResultPop;
    public  MyCornerTextView raceShowMore; //查看更多按钮
    public  RecyclerView raceTodayResultRecy;//recycleView
    private  ConstraintLayout raceLoadingLinear;
    private  RaceOpenresultAdapter raceOpenresultAdapter;//适配器
    private   RaceLottery raceLottery;
    private  List<RaceLottery.RaceLotteryBean> raceTodayOpenList =  new ArrayList<>();

    public  PopupWindow marksixTodayOpenResultPop;
    public  MyCornerTextView marksixShowMore; //查看更多按钮
    public  RecyclerView marksixTodayResultRecy;//recycleView
    private  ConstraintLayout sixLoadingLinear;
    private  MarksixOpenresultAdapter marksixOpenresultAdapter;//适配器
    private   MarksixLottery marksixLottery;
    private  List<MarksixLottery.marksixLotteryBean> marksixTodayOpenList =  new ArrayList<>();


    /*
    投注页面右侧菜单中  Utils.getString(R.string.游戏规则) popwindow
     */
    public  PopupWindow gameRulePop; //pop视图
    public  TextView gameRuleCancel; //取消按钮
    public  TextView gameRuleSure;  //确定按钮
//    public  TextView lotteryNameText; //顶部的typename
    public  TextView gameRuleTextView; //第二行的规则说明
    public WebView game_rule_webView;



    /**
     * 初始化彩票分类pop视图,recycleVirw的初始化,点击事件的回调
     * @param context 上下文
     * @param onItemClidkListener 点击事件的回调
     */
    public   void initClassfyPop(final Context context,final CustomPopupWindow.OnItemClidkListener onItemClidkListener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.lottery_classify_pop, null);
        classfyPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        classfyPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        classfyRecycle=contentView.findViewById(R.id.lottery_classify_recycle);
        lotteryClassPopRecycleAdapter =new LotteryClassPopRecycleAdapter(lotteryClassModelArrayList,context, lotteryClassModelArrayListTwo);
        classfyRecycle.setAdapter(lotteryClassPopRecycleAdapter);
        classfyRecycle.setLayoutManager(new LinearLayoutManager(context));
        lotteryClassPopRecycleAdapter.setOnItemClickListener(new LotteryClassPopRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnclassfyClick(View view, int position, ArrayList<LotteryClassModel> modelList) {
                if(onItemClidkListener!=null){
                    classfyPop.dismiss();
                    onItemClidkListener.onItemClick(view,position,modelList.get(position));
                }
            }
        });
        classfyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {//dismiss的监听,dismiss后,回复背景亮度
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);
            }
        });
    }

    /**
     * 右侧菜单pop初始化
     * @param context 上下文对象
     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initMenuPop(final  Context context,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener){
        View contentView = LayoutInflater.from(context).inflate(R.layout.bet_menu_pop, null);
        menuPop = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        menuPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //右侧菜单pop的disMIss监听
        menuPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        betNote=contentView.findViewById(R.id.bet_note);
        openResult=contentView.findViewById(R.id.open_result);
        gameRule=contentView.findViewById(R.id.game_rule);
        investTv=contentView.findViewById(R.id.invest_center_tv);
        mineCenterTv=contentView.findViewById(R.id.my_center);
        twoCahngLongTv=contentView.findViewById(R.id.two_changlong);
        todayWinLoseTv=contentView.findViewById(R.id.today_winlose_tv);
        onlineKf=contentView.findViewById(R.id.online_kefu);
    }

    /**
     * 快三投注页面当日开奖结果pop
     * @param contextWeakReference 上下文
    //     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initKuaiSanTodayResult(final  WeakReference<Context> contextWeakReference/* Context context*//*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){
        Context context = contextWeakReference.get();
        if(null==context){
            return;
        }
        Context applicationContext = context;
        View contentView = LayoutInflater.from(applicationContext).inflate(R.layout.kuaisan_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) applicationContext);
            int height = (int) (i * 0.65);
            kuaiSanTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            kuaiSanTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }
        kuaiSanTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        todayResultRecy=contentView.findViewById(R.id.today_open_result_pop_recycle);
        kuaisanLoadIngLinear =contentView.findViewById(R.id.live_loading_linear);
        kuaiSanKuaiSanOpenResultAdapter =new KuaiSanOpenResultAdapter(kuaiSanTodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        todayResultRecy.setLayoutManager(linearLayoutManager);
        todayResultRecy.setAdapter(kuaiSanKuaiSanOpenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        kuaiSanTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        kuaiSanshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        kuaiSanshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        kuaiSanshowMore.setCornerSize(10);//设置圆角

        //点击 查看更多 跳转到快三开奖结果activity
        kuaiSanshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuaiSanTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context,KuaiSanOpenActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /**
     * pcdd投注页面当日开奖结果pop
     * @param context 上下文
    //     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initPcddTodayResult(final  Context context/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id,boolean isLive){

        View contentView = LayoutInflater.from(context).inflate(R.layout.pcdd_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            pcddTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            pcddTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }
        pcddTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        pcddtodayResultRecy=contentView.findViewById(R.id.pcdd_open_result_pop_recycle);
        pcddLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        pcddOpenResultAdapter =new PcddOpentTodayResultAdapter(pcddTodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        pcddtodayResultRecy.setLayoutManager(linearLayoutManager);
        pcddtodayResultRecy.setAdapter(pcddOpenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        pcddTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        pcddshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        pcddshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        pcddshowMore.setCornerSize(10);//设置圆角

        //点击 查看更多 跳转到快三开奖结果activity
        pcddshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcddTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context,PcDanOpenActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /*
    初始化北京快乐8 开奖结果pop
     */
    public  void initHappy8TodayResult(final  Context context/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/,final int type_id,boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.happy8_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            bjTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            bjTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }
        bjTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        bjtodayResultRecy=contentView.findViewById(R.id.bj_today_open_result_pop_recycle);
        happy8OPenResultAdapter =new Happy8OPenResultAdapter(bjTodayResultModelArrayList);
        bjLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        bjtodayResultRecy.setLayoutManager(linearLayoutManager);
        bjtodayResultRecy.setAdapter(happy8OPenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        bjTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        bjshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        bjshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        bjshowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到快三开奖结果activity
        bjshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, Happy8OpentActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /*
   初始化广东快乐10开奖结果pop
    */
    public  void initHappy10TodayResult(final  Context context, final int game/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id,boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.happy10_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            happy10TodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            happy10TodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }

        happy10TodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        happy10todayResultRecy=contentView.findViewById(R.id.happy10_today_open_result_pop_recycle);
        happy10LoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        happy10OPenResultAdapter =new Happy10OPenResultAdapter(happy10TodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        happy10todayResultRecy.setLayoutManager(linearLayoutManager);
        happy10todayResultRecy.setAdapter(happy10OPenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        happy10TodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        happy10showMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        happy10showMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        happy10showMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到开奖结果activity
        happy10showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LuckFarmOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
                happy10TodayOpenResultPop.dismiss();
            }
        });
    }
    /*
初始化时时彩 开奖结果pop
 */
    public  void initSscTodayResultPop(final  Context context,final  int game/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/,final int type_id,Boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.ssc_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }
//        sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        sscTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        sscTodayResultRecy=contentView.findViewById(R.id.ssc_today_open_result_pop_recycle);
        sscLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        sscTodayOpenAdapter =new SscTodayOpenAdapter(sscTodayOpenMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        sscTodayResultRecy.setLayoutManager(linearLayoutManager);
        sscTodayResultRecy.setAdapter(sscTodayOpenAdapter);

        //右侧菜单pop的disMIss监听
        sscTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        sscShowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        sscShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        sscShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到ssc开奖结果activity
        sscShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sscTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, SscOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }
    public  void initRaceTodayResultPop(final  Context context,final  int game,final int type_id,boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.race_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            raceTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            raceTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }
        raceTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        raceTodayResultRecy=contentView.findViewById(R.id.race_pop_recycle);
        raceLoadingLinear =contentView.findViewById(R.id.live_loading_linear);
        if (raceOpenresultAdapter==null){
            raceOpenresultAdapter =new RaceOpenresultAdapter(context,raceTodayOpenList);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        raceTodayResultRecy.setLayoutManager(linearLayoutManager);
        raceTodayResultRecy.setAdapter(raceOpenresultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        raceTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        raceShowMore=contentView.findViewById(R.id.race_pop_showmore);//查看更多按钮
        raceShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        raceShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到ssc开奖结果activity
        raceShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raceTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, RaceOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }
    public  void initMarksixTodayResultPop(final  Context context,final  int game,final int type_id,boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.marksix_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            marksixTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, true);//实例化popupWindow
        }else {
            marksixTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        }

        marksixTodayOpenResultPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        //recycleView配置
        marksixTodayResultRecy=contentView.findViewById(R.id.marksix_pop_recycle);
        sixLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        if (marksixOpenresultAdapter==null){
            marksixOpenresultAdapter =new MarksixOpenresultAdapter(context,marksixTodayOpenList);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        marksixTodayResultRecy.setLayoutManager(linearLayoutManager);
        marksixTodayResultRecy.setAdapter(marksixOpenresultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        marksixTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        marksixShowMore=contentView.findViewById(R.id.marksix_pop_showmore);//查看更多按钮
        marksixShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        marksixShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到六合彩开奖结果activity
        marksixShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marksixTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, SixOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }
    /**
     * 游戏规则pop相关配置
     * @param context  上下文
     * @param game 彩票种类id(用于判断加载哪个彩票类型的规则说明pop)
     * @param typename 彩票名 (用于设置在pop顶部TExtView)
     */
    public  void initGameRule(final Context context,int game,final String typename,final int isrestore,boolean isLive){
        View contentView=LayoutInflater.from(context).inflate(R.layout.game_rule_pop_layout, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            gameRulePop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,  height==0?1000:height, true);//实例化popupWindow
            LinearLayout wrap_linear =contentView.findViewById(R.id.wrap_linear);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)wrap_linear.getLayoutParams();
            layoutParams.topMargin = 0;
            layoutParams.leftMargin = 0;
            layoutParams.bottomMargin = 0;
            layoutParams.rightMargin = 0;
            wrap_linear.setLayoutParams(layoutParams);
            gameRulePop.setTouchable(true);
            gameRulePop.setOutsideTouchable(true);
        }else {
            gameRulePop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);//实例化popupWindow
        }

        gameRulePop.setAnimationStyle(R.style.popAlphaanim0_5);

        gameRulePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);
            }
        });

        game_rule_webView =contentView.findViewById(R.id.game_rule_webView);
        gameRuleTextView =contentView.findViewById(R.id.lottety_rule_show);
        gameRuleTextView.setText(typename+Utils.getString(R.string.规则说明));
        gameRuleCancel =contentView.findViewById(R.id.game_rule_cancel);
        gameRuleSure =contentView.findViewById(R.id.game_rule_sure);
        gameRuleCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRulePop.dismiss();
            }
        });
        gameRuleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRulePop.dismiss();
            }
        });
        game_rule_webView.getSettings().setJavaScriptEnabled(true);
          if(game==1){//1 表示快三
//              game_rule_webView.loadUrl("file:///android_asset/k3_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/k3Rule.html"));
        }else if(game==6){//北京快乐8
//              game_rule_webView.loadUrl("file:///android_asset/happy8_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/happy8Rule.html"));
        }else if(game==5){//pc蛋蛋
              if(typename.equals(Utils.getString(R.string.加拿大28))){
                if(isrestore==1){//isrestore为1   不显示退还本金
//                    game_rule_webView.loadUrl("file:///android_asset/jianada28.html");
                    game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/jianada28.html"));
                }else {
                    game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/jianada28_2.html"));
                }
            }else {
                if(isrestore==1){//isrestore为1   不显示退还本金
//                    game_rule_webView.loadUrl("file:///android_asset/pcdd_rule.html");
                    game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/pcddRule.html"));
                }else {
//                    game_rule_webView.loadUrl("file:///android_asset/pcdd_rule_2.html");
                    game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/pcddRule_2.html"));

                }
            }

        }
        else if(game==2){//时时彩
//              game_rule_webView.loadUrl("file:///android_asset/ssc_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/sscRule.html"));
        }else if(game==3){//赛车
//              game_rule_webView.loadUrl("file:///android_asset/race_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/raceRule.html"));
        }else if (game == 4){//六合彩
//              game_rule_webView.loadUrl("file:///android_asset/marksix_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/marksixRule.html"));
        }
        else if(game==8){//快乐10分
//              game_rule_webView.loadUrl("file:///android_asset/happy10_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/happy10Rule.html"));
        }
        else if(game==7){//重庆幸运农场

//              game_rule_webView.loadUrl("file:///android_asset/farm_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/farmRule.html"));
        }else if(game==9){//11选5

//              game_rule_webView.loadUrl("file:///android_asset/11x5_rule.html");
              game_rule_webView.loadUrl(Utils.checkImageUrl("/rules/11x5Rule.html"));
        }
    }


    /**
     * 点击右侧菜单中的Utils.getString(R.string.投注记录),进行跳转
     * @param context 上下文
     * @param typename 当前彩票名
     * @param game 当前的彩票game
     * @param type_id 当前的彩票type_id
     *
     */
    public  void toBetNote(final Context context, final String typename, final int game, final int type_id) {
        betNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MineBetAcitivity.class);
                intent.putExtra("fromShopping",typename);//跳转到投注记录页面后,用于筛选按钮旁的textView显示彩票名
                intent.putExtra("game",game);//跳转到投注记录后,默认请求所点击的彩票投注记录时需要用到的参数
                intent.putExtra("type_id",type_id);//跳转到投注记录后,默认请求所点击的彩票投注记录时需要用到的参数
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }


    /**
     *点击右侧菜单中的 Utils.getString(R.string.两面长龙),进行跳转
     * @param context 上下文
     */
    public  void toTwoChangLongAty(final Context context, final int game, final int type_id) {

        twoCahngLongTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game==3||game==2||game==9||game==8){
                    Intent intent = new Intent(context, TwoLongDragonActivity.class);
                    intent.putExtra("game",game);
                    intent.putExtra("type_id",type_id);
                    context.startActivity(intent);
                    menuPop.dismiss();
                }else{
                    menuPop.dismiss();
                    ToastUtil.showToast(Utils.getString(R.string.暂无两面长龙));
                }

            }
        });

    }

    /**
     *点击右侧菜单中的 Utils.getString(R.string.今日输赢),进行跳转
     * @param context 上下文
     */
    public  void toTodayWinLose(final Context context, final int game, final int type_id) {

        todayWinLoseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TodayWinLoseActivity.class);
                intent.putExtra("game",game);
                intent.putExtra("type_id",type_id);
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }
    /**
     *点击右侧菜单中的 Utils.getString(R.string.在线客服),进行跳转
     * @param context 上下文
     */
    public  void toOnlineKf(final Context context) {

        onlineKf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangLongActivity.class);
                intent.putExtra("toKeFu",true);
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }

    /**
     *点击右侧菜单中的 Utils.getString(R.string.充值中心),进行跳转
     * @param context 上下文
     */
    public  void toInvestCenter(final Context context) {
        investTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");

                if(isTest.equals("-1")){
                 Utils.initSkipVisitorSafeCenterPop(context,(Activity)context);
                }else {
                    Intent intent  = new Intent(context, RechargeActivity.class);
                    context.startActivity(intent);
                    menuPop.dismiss();

                }
            }
        });

    }

    /**
     * 点击右侧菜单中的Utils.getString(R.string.会员中心), 进行跳转
     * @param context
     */
    public  void tovVipCenter(final Context context) {
        mineCenterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("toMineFragment",true);
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }

    /**
     * 点击右侧菜单中Utils.getString(R.string.开奖结果), 跳转对应的开奖结果activity
     * @param context 上下文
     * @param type_id 彩票type_id
     * @param game 彩票分类的id(用于判断点击后,需要跳转哪一个彩种的开奖结果)
     */
    public  void toOpenResult(final Context context, final int type_id,final  int game) {
        openResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(game==3){//3 表示跳转赛车类
                    intent  = new Intent(context, RaceOpenActivity.class);
                }
                else if(game==1){//1 表示跳转快三类
                    intent  = new Intent(context, KuaiSanOpenActivity.class);
                }
                else if(game==6){//北京快乐8
                    intent  = new Intent(context, Happy8OpentActivity.class);
                }else if(game==5){//pc蛋蛋
                    intent  = new Intent(context, PcDanOpenActivity.class);
                }/*else if(game==8){//快乐10分
                    intent  = new Intent(context, Happy10OpentActivity.class);
                }*/else if(game==7||game==8){
                    intent  = new Intent(context, LuckFarmOpenActivity.class);
                }
                else if(game==2||game==9){//时时彩  11选5
                    intent  = new Intent(context, SscOpenActivity.class);
                }else if(game==4){//六合彩
                    intent  = new Intent(context, SixOpenActivity.class);
                }else {
                    intent  = new Intent(context, XuanWuOpenActivity.class);
                }

                //测试代码 记得删
//                intent  = new Intent(context, KuaiSanOpenActivity.class);

                intent.putExtra("type_id",type_id);//跳转到开奖结果activity后,请求数据需要用的到参数
                intent.putExtra("game",game);//跳转到开奖结果activity后,请求数据需要用的到参数
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }

    /**
     * 弹出快三规则说明pop
     * @param activity
     */
    public  void showGameRulePop(final Activity activity,boolean isLive){
        if(isLive){
            if(gameRulePop!=null&&!activity.isFinishing()){
                gameRulePop.showAtLocation(activity.getWindow().getDecorView(),Gravity.BOTTOM,0,0);
            }
        }else {
            gameRule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(gameRulePop !=null){
                        if(menuPop!=null){
                            menuPop.dismiss();
                        }
                        if(!activity.isFinishing()){
                            gameRulePop.showAtLocation(activity.getWindow().getDecorView(),Gravity.CENTER,0,0);
                        }
                        darkenBackground(activity,0.3f);
                    }
                }
            });
        }

    }

    /**
     * 显示彩票分类pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showClassfyPop(View targetView,Context context) {
        if (classfyPop != null) {
            classfyPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            darkenBackground((Activity) context,0.3f);//背景变暗
        }
    }

    /**
     * 显示右侧菜单pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showMenuPop(View targetView,Context context) {
        if (menuPop != null) {
            menuPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            darkenBackground((Activity) context,0.5f);
        }
    }
    /**
     * 显示快三当日开奖结果pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showKuaiSanTodayResultPop(View targetView,Context context) {
        if (kuaiSanTodayOpenResultPop != null) {
            kuaiSanTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            darkenBackground((Activity) context,0.3f);
        }
    }

    /**
     * 显示北京快乐8当日开奖结果pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showHappy8TodayResultPop(View targetView,Context context) {
        if (bjTodayOpenResultPop != null) {
            bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            darkenBackground((Activity) context,0.3f);
        }
    }

    public  long getJgTIme(int game,int type_id){
        long time=10000;
        for (int i = 0; i < lotteryClassModelArrayList.size(); i++) {
            LotteryClassModel lotteryClassModel = lotteryClassModelArrayList.get(i);
            long jgTime = lotteryClassModel.getJgTime();
            if(lotteryClassModel.getGame()==game&& lotteryClassModel.getType_id()==type_id){
                if(jgTime<=180){
                    time=4000;
                    break;

                }else{
                    time=8000;
                }
            }else{
                time=10000;
            }
        }
        return time;
    }

    /**
     * 请求彩票分类pop中recycleView的数据
     * @param context
     * @param typename  彩票名(用于默认选中彩票)
     */
    public  void initClassfyData(final Context context, final String typename) {

        Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                lotteryClassModelArrayList.clear();
                lotteryClassModelArrayListTwo.clear();
                JSONObject gameInfoList = JSONObject.parseObject(content);//得到购彩大厅jsonObject
                JSONArray jsonArray = gameInfoList.getJSONArray("gameInfoList");//得到购彩大厅彩票分类
                String imaUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
                JSONArray gameClassList = gameInfoList.getJSONArray("gameClassList");//所有彩票
                for (int i = 0 ;i<jsonArray.size();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String name = jsonObject1.getString("name");//得到彩票类型名称
                    String image = jsonObject1.getString("image");//得到彩票类型图片路劲
//                String url = "https://xunbo678.com/assets/i/mobile/home/5.png";
                    String classIds1 = jsonObject1.getString("classIds");//类型下的彩票数组
                    String[] split = classIds1.split(",");
                    List<String> strings = Arrays.asList(split);//得到彩票id
                    //添加彩票分类的item
                    lotteryClassModelArrayList.add(new LotteryClassModel("",0,name,0,"",imaUrl+image,"",0l,strings));
                }
                for (int c= 0;c<gameClassList.size();c++){//遍历所有彩票
                    JSONObject jsonObject = gameClassList.getJSONObject(c);
                    String id1 = jsonObject.getString("id");//得到彩票id
                    String typename1 = jsonObject.getString("typename");
                    String image1 = jsonObject.getString("image");
                    String lotteryImg = imaUrl+image1;
                    String isStart = jsonObject.getString("isStart");
                    String isopenOffice = jsonObject.getString("isopenOffice");
                    Integer game = jsonObject.getInteger("game");
                    Integer type_id = jsonObject.getInteger("type_id");
                    Long openjgtime = jsonObject.getLong("openjgtime");
                    List<String>stringList=new ArrayList<>();
                    //添加彩票item
                    if(isStart.equals("1")){
                        LotteryClassModel lotteryClassModel = new LotteryClassModel(id1, type_id, typename1, game, isopenOffice, lotteryImg,isStart,openjgtime,stringList);
                        lotteryClassModelArrayListTwo.add(lotteryClassModel);
                        if(typename.equals(typename1)){//typeName相等
                            lotteryClassModel.setStatus(1);//将status设置为1(点击效果)
                        }
                    }

                }
                //滚动到选中item
                for (int j = 0; j < lotteryClassModelArrayListTwo.size(); j++) {
                    LotteryClassModel lotteryClassModel = lotteryClassModelArrayListTwo.get(j);
                    if(lotteryClassModel.getStatus()==1){
                        for (int k = 0; k < lotteryClassModelArrayList.size(); k++) {
                            if(lotteryClassModelArrayList.get(k).getIdList().contains(lotteryClassModel.getId())){
                                classfyRecycle.getLayoutManager().scrollToPosition(k);
                            }
                        }
                    }
                }
                HbGameClassModel instance = HbGameClassModel.getInstance();
                if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
                    selectorId(instance);
                    ;                }
                lotteryClassPopRecycleAdapter.notifyDataSetChanged();

            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });

    }
    public void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < lotteryClassModelArrayListTwo.size(); i++) {
            LotteryClassModel lotteryClassModel = lotteryClassModelArrayListTwo.get(i);
            for (int j = 0; j < idList.size(); j++) {
                if((lotteryClassModel.getId()+"").equals(idList.get(j))){
                    lotteryClassModel.setXian(true);
                    break;
                }
            }
        }
        if(null!=lotteryClassPopRecycleAdapter){
            lotteryClassPopRecycleAdapter.notifyDataSetChanged();
        }
    }
    /**
     * 请求快三当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initKuaiSanTodayResultData(final Context context, final View targetView, final int type_id){
        if (kuaiSanTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                kuaiSanTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {
                kuaiSanTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        kuaisanLoadIngLinear.setVisibility(View.VISIBLE);
        kuaiSanshowMore.setVisibility(View.GONE);
        todayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_8r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                kuaisanLoadIngLinear.setVisibility(View.GONE);
                kuaiSanshowMore.setVisibility(View.VISIBLE);
                todayResultRecy.setVisibility(View.VISIBLE);
                kuaiSanTodayResultModelArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray gameLotterylist = jsonObject1.getJSONArray("gameLotterylist");
                for (int i = 0; i < gameLotterylist.size(); i++) {
                    JSONObject jsonObject = gameLotterylist.getJSONObject(i);
                    String typeqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String remark = jsonObject.getString("remark");//大小
                    String createdDate = jsonObject.getString("lotterytime");//时间
                    kuaiSanTodayResultModelArrayList.add(new KuaiSanTodayResultModel(typeqishu,lotteryNo,remark,createdDate));
                }
                kuaiSanKuaiSanOpenResultAdapter.notifyDataSetChanged();

 /*               if (kuaiSanTodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        kuaiSanTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0,Gravity.BOTTOM);
                    }else {

                        kuaiSanTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }*/

            }

            @Override
            public void failed(MessageHead messageHead) {
//                System.out.println(messageHead.getInfo());
                kuaisanLoadIngLinear.setVisibility(View.GONE);
                kuaiSanshowMore.setVisibility(View.VISIBLE);
                todayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 请求快三当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initPcddTodayResultData(final Context context, final View targetView, final int type_id){
        if (pcddTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                pcddTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                pcddTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        pcddLoadingLinear.setVisibility(View.VISIBLE);
        pcddshowMore.setVisibility(View.GONE);
        pcddtodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_05dd, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                pcddTodayResultModelArrayList.clear();
                pcddLoadingLinear.setVisibility(View.GONE);
                pcddshowMore.setVisibility(View.VISIBLE);
                pcddtodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray danLotterylist = jsonObject1.getJSONArray("danLotterylist");
                for (int i = 0; i < danLotterylist.size(); i++) {
                    JSONObject jsonObject = danLotterylist.getJSONObject(i);
                    String typeqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotterytime = jsonObject.getString("lotterytime");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String markdx = jsonObject.getString("markdx");//大小
                    String markds = jsonObject.getString("markds");//单双
                    pcddTodayResultModelArrayList.add(new PcddTodayResultModel(typeqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                pcddOpenResultAdapter.notifyDataSetChanged();
                if (pcddTodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        pcddTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0,Gravity.BOTTOM);
                    }else {

                        pcddTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }

            }

            @Override
            public void failed(MessageHead messageHead) {
//                System.out.println(messageHead.getInfo());
                pcddLoadingLinear.setVisibility(View.GONE);
                pcddshowMore.setVisibility(View.VISIBLE);
                pcddtodayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求北京快乐8当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initHappy8TodayResultData(final Context context, final LinearLayout targetView, final int type_id){
        if (bjTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                bjTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        bjLoadingLinear.setVisibility(View.VISIBLE);
        bjshowMore.setVisibility(View.GONE);
        bjtodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_04ha, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                bjTodayResultModelArrayList.clear();
                bjLoadingLinear.setVisibility(View.GONE);
                bjshowMore.setVisibility(View.VISIBLE);
                bjtodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happyLotterylist = jsonObject1.getJSONArray("happyLotterylist");
                for (int i = 0; i < happyLotterylist.size(); i++) {
                    JSONObject jsonObject = happyLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    bjTodayResultModelArrayList.add(new Happy8OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,false,sum,"","","","","",""));
                }
                happy8OPenResultAdapter .notifyDataSetChanged();
                if (bjTodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        bjTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0,Gravity.BOTTOM);
                    }else {

                        bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                bjLoadingLinear.setVisibility(View.GONE);
                bjshowMore.setVisibility(View.VISIBLE);
                bjtodayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求g广东快乐10当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initHappy10TodayResultData(final Context context, final View targetView, final int type_id){
        if (happy10TodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                happy10TodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        happy10LoadingLinear.setVisibility(View.VISIBLE);
        happy10showMore.setVisibility(View.GONE);
        happy10todayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_04happyten, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                happy10TodayResultModelArrayList.clear();
                happy10LoadingLinear.setVisibility(View.GONE);
                happy10showMore.setVisibility(View.VISIBLE);
                happy10todayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("happytenLotterylist");
                for (int i = 0; i < happytenLotterylist.size(); i++) {
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String markdx = jsonObject.getString("markdx");//和值大小
                    String markds = jsonObject.getString("markds");//和值单双
                    happy10TodayResultModelArrayList.add(new Happy10OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                happy10OPenResultAdapter .notifyDataSetChanged();
                if (happy10TodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        happy10TodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0,Gravity.BOTTOM);
                    }else{

                        happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                happy10LoadingLinear.setVisibility(View.GONE);
                happy10showMore.setVisibility(View.VISIBLE);
                happy10todayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求重庆幸运农场当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initfarmTodayResultData(final Context context, final View targetView, final int type_id){
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_04farm, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                happy10TodayResultModelArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("farmLotterylist");
                for (int i = 0; i < happytenLotterylist.size(); i++) {
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String markdx = jsonObject.getString("markdx");//和值大小
                    String markds = jsonObject.getString("markds");//和值单双
                    happy10TodayResultModelArrayList.add(new Happy10OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                happy10OPenResultAdapter .notifyDataSetChanged();
                if (happy10TodayOpenResultPop != null) {
                    happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }


    /**
     * 请求 时时彩开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initSscTodayResultData(final Context context, final int game,final int type_id ,final View targetView){
        if (sscTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                sscTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {
                sscTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        sscLoadingLinear.setVisibility(View.VISIBLE);
        sscShowMore.setVisibility(View.GONE);
        sscTodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        String url="";
        if(game==9){
            url=RequestUtil.REQUEST_04xuanwu;
        }else{
            url=RequestUtil.REQUEST_05ssc;
        }
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                sscTodayOpenMedolArrayList.clear();
                sscShowMore.setVisibility(View.VISIBLE);
                sscLoadingLinear.setVisibility(View.GONE);
                sscTodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray lotterylist=null;
                if(game==2){
                    lotterylist = jsonObject1.getJSONArray("sscaiLotterylist");
                }
                else {
                    lotterylist = jsonObject1.getJSONArray("xuanwuLotterylist");
                }
                for (int i = 0; i < lotterylist.size(); i++) {
                    JSONObject jsonObject = lotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String marklh = jsonObject.getString("marklh");//龙虎
                    String markdx = jsonObject.getString("markdx");//大小
                    String markds = jsonObject.getString("markds");//单双
                    SscTodayOpenMedol sscTodayOpenMedol = new SscTodayOpenMedol(lotteryqishu, lotteryNo, markdx, markds, marklh,lotterytime);
                    sscTodayOpenMedol.setGame(game);
                    sscTodayOpenMedolArrayList.add(sscTodayOpenMedol);
                }
                sscTodayOpenAdapter .notifyDataSetChanged();

            }

            @Override
            public void failed(MessageHead messageHead) {
                sscLoadingLinear.setVisibility(View.GONE);
                sscShowMore.setVisibility(View.VISIBLE);
                sscTodayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求 赛车开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initRaceTodayResultData(final Context context, final int game,final int type_id ,final View targetView ){
        if (raceTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                raceTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                raceTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        raceLoadingLinear.setVisibility(View.VISIBLE);
        raceShowMore.setVisibility(View.GONE);
        raceTodayResultRecy.setVisibility(View.GONE);
        Activity activity=(Activity)context;
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        String url="";
        Resources res = context.getResources();
        final String[] lotteryurls = res.getStringArray(R.array.lastLottery);
        if (game <= lotteryurls.length) {
            url = lotteryurls[game - 1];
        }
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content){
                raceTodayOpenList.clear();
                raceLoadingLinear.setVisibility(View.GONE);
                raceShowMore.setVisibility(View.VISIBLE);
                raceTodayResultRecy.setVisibility(View.VISIBLE);
                Utils.logE("content:",content);
                Gson gson = new Gson();

                raceLottery =    gson.fromJson(content,RaceLottery.class);
                List<RaceLottery.RaceLotteryBean> kjlist = new ArrayList<>();
                kjlist = raceLottery.getRaceLotterylist();
                raceTodayOpenList.addAll(kjlist);
                raceOpenresultAdapter .notifyDataSetChanged();

                if (raceTodayOpenResultPop != null) {

                    if(context!=null&&!activity.isFinishing()){
                        if(null==targetView){
                            Activity activity =(Activity)context;
                            raceTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0,Gravity.BOTTOM);
                        }else {

                            raceTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                        }
                        darkenBackground((Activity) context,0.3f);
                    }

                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                raceLoadingLinear.setVisibility(View.GONE);
                raceShowMore.setVisibility(View.VISIBLE);
                raceTodayResultRecy.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 请求 六合彩开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initMarksixTodayResultData(final Context context, final int game,final int type_id ,final View targetView){
        if (marksixTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                marksixTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                marksixTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        sixLoadingLinear.setVisibility(View.VISIBLE);
        marksixShowMore.setVisibility(View.GONE);
        marksixTodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        String url="";
        Resources res = context.getResources();
        final String[] lotteryurls = res.getStringArray(R.array.lastLottery);
        if (game <= lotteryurls.length) {
            url = lotteryurls[game - 1];
        }
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {


            @Override
            public void success(String content)  {
                marksixTodayOpenList.clear();
                sixLoadingLinear.setVisibility(View.GONE);
                marksixShowMore.setVisibility(View.VISIBLE);
                marksixTodayResultRecy.setVisibility(View.VISIBLE);
                Utils.logE("content:",content);
                Gson gson = new Gson();

                marksixLottery =    gson.fromJson(content,MarksixLottery.class);
                List<MarksixLottery.marksixLotteryBean> kjlist = new ArrayList<>();
                kjlist = marksixLottery.getMarksixLotterylist();
                marksixTodayOpenList.addAll(kjlist);
                marksixOpenresultAdapter .notifyDataSetChanged();

                if (marksixTodayOpenResultPop != null) {
                    marksixTodayOpenResultPop.showAsDropDown(targetView, 0, 0,Gravity.BOTTOM);
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                sixLoadingLinear.setVisibility(View.GONE);
                marksixShowMore.setVisibility(View.VISIBLE);
                marksixTodayResultRecy.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 设置背景亮度
     * @param activity activity实例
     * @param bgcolor 亮度值(0f-1f)值越小,背景越暗
     */
    public  void darkenBackground(Activity activity, Float bgcolor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor==1f){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }
        activity.getWindow().setAttributes(lp);
    }

    /*
    分类pop点击事件的回调
     */
    public  interface OnItemClidkListener{
        void onItemClick(View view, int position, LotteryClassModel lotteryClassModel);
    }
    private  CustomPopupWindow. OnItemClidkListener onItemClidkListener =null;
    public void setOnItemClidkListener(OnItemClidkListener onItemClidkListener) {
        this.onItemClidkListener = onItemClidkListener;
    }

    /*
    右侧菜单pop点击事件的回调
     */
    public  interface OnMenuPopClickListener{
        void onMenuClicked(View view);
    }
    private CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener=null;


    public void setOnMenuPopClickListener(OnMenuPopClickListener onMenuPopClickListener) {
        this.onMenuPopClickListener = onMenuPopClickListener;
    }
}
