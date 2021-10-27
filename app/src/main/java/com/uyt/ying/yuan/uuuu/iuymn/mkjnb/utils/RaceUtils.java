package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.rightMenuActivity.openingResultActivity.RaceOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RaceOpenresultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RaceUtils {

    public  PopupWindow raceTodayOpenResultPop;
    public  MyCornerTextView raceShowMore; //查看更多按钮
    public  RecyclerView raceTodayResultRecy;//recycleView
    private  ConstraintLayout raceLoadingLinear;
    private  RaceOpenresultAdapter raceOpenresultAdapter;//适配器
    private   RaceLottery raceLottery;
    private  List<RaceLottery.RaceLotteryBean> raceTodayOpenList =  new ArrayList<>();



    public  void initRaceTodayResultPop(final Context context, final  int game, final int type_id, boolean isLive){
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

    public  void initRaceTodayResultData(final Context context, final int game,final int type_id ,final LinearLayout targetView ){
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
