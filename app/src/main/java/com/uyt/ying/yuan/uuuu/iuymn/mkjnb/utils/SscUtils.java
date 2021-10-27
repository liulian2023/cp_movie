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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.SscOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscTodayOpenAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscTodayOpenMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SscUtils {
     /*
      ssc投注页面当天的开奖结果pop
     */

    public  PopupWindow sscTodayOpenResultPop;
    public  MyCornerTextView sscShowMore; //查看更多按钮
    public  RecyclerView sscTodayResultRecy;//recycleView
    private  ConstraintLayout sscLoadingLinear;
    private  SscTodayOpenAdapter sscTodayOpenAdapter;//适配器
    private  ArrayList<SscTodayOpenMedol> sscTodayOpenMedolArrayList =new ArrayList<>();//recycleView数据容器

    public  void initSscTodayResultPop(final Context context, final  int game/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, Boolean isLive){
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

    /**
     * 请求 时时彩开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initSscTodayResultData(final Context context, final int game,final int type_id ,final LinearLayout targetView){
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
            url= RequestUtil.REQUEST_04xuanwu;
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
