package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.KuaiSanOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.KuaiSanOpenResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KuaiSanTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KuaiSanUtils {
         /*
    快三投注页面当天的开奖结果pop
     */

    public  PopupWindow kuaiSanTodayOpenResultPop;
    public  MyCornerTextView kuaiSanshowMore; //查看更多按钮
    public  RecyclerView todayResultRecy;//recycleView
    private  KuaiSanOpenResultAdapter kuaiSanKuaiSanOpenResultAdapter;//适配器
    private  ArrayList<KuaiSanTodayResultModel> kuaiSanTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器
    private  ConstraintLayout kuaisanLoadIngLinear;

/*    public KuaiSanUtils(PopupWindow kuaiSanTodayOpenResultPop, MyCornerTextView kuaiSanshowMore, RecyclerView todayResultRecy, KuaiSanOpenResultAdapter kuaiSanKuaiSanOpenResultAdapter, ArrayList<KuaiSanTodayResultModel> kuaiSanTodayResultModelArrayList, ConstraintLayout kuaisanLoadIngLinear) {
        this.kuaiSanTodayOpenResultPop = kuaiSanTodayOpenResultPop;
        this.kuaiSanshowMore = kuaiSanshowMore;
        this.todayResultRecy = todayResultRecy;
        this.kuaiSanKuaiSanOpenResultAdapter = kuaiSanKuaiSanOpenResultAdapter;
        this.kuaiSanTodayResultModelArrayList = kuaiSanTodayResultModelArrayList;
        this.kuaisanLoadIngLinear = kuaisanLoadIngLinear;
    }*/

    /**
     * 快三投注页面当日开奖结果pop
     * @param contextWeakReference 上下文
    //     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initKuaiSanTodayResult(final WeakReference<Context> contextWeakReference/* Context context*//*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){
        Context context = contextWeakReference.get();
        if(null==context){
            return;
        }
        View contentView = LayoutInflater.from(context).inflate(R.layout.kuaisan_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
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
                Intent intent = new Intent(context, KuaiSanOpenActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 请求快三当日开奖结果的数据
     * @param type_id 彩票type_id+
     *
     */
    public  void initKuaiSanTodayResultData(final Context context, final LinearLayout targetView, final int type_id){
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
                    CustomPopupWindow.darkenBackground((Activity) context,0.3f);
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
}
