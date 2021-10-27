package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.WinOrLoseRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.WinOrLoseModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MainClidItemGridDecoration;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//做好聊天室后,把webView相关代码删除, 把注释的代码恢复
public class TodayWinOrLoseActvity extends BaseActivity implements View.OnClickListener {
    private String chatRoomId/*="7b99f4f9a89742d29b81183a4790f853"*/;
    String token/*="BGe/L2YYyE1iw+yLI/D/bKrF+sb+AdE5DZUHqe01o1nfvHcBDpmL/iZyZ1OP13ZNsJs8d4cvujAKO+oH3EdNug=="*/;
    private static String TAG="todoyWinOrloseActivity";
    private RecyclerView recyclerView;
    private WinOrLoseRecycleAdapter winOrLoseRecycleAdapter;
    private ArrayList<WinOrLoseModel> winOrLoseModelArrayList =new ArrayList<>();
    private TextView showBeiZhu;
    private TextView actionRight;
    private PopupWindow popupWindow;
    private TextView winPrice;
    private TextView back;
    private TextView actionBatCenter;
    private ImageView popupDismiss;
    private BigDecimal profitAndLoss;
    private ArrayList<String> typeList=new ArrayList<>();

    private String betPrice;
    private String zjPrice;
    Long user_id;
    private ConstraintLayout loadingLinear;
    private String appName;
    private LinearLayout errorLinear;
    private TextView reloadTv;






//    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_win_or_lose_actvity);
        user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        loadingLinear=findViewById(R.id.loading_linear);
        appName=SharePreferencesUtil.getString(this,"appName","");
        chatRoomId=SharePreferencesUtil.getString(TodayWinOrLoseActvity.this,"chatroomId","");
        token=SharePreferencesUtil.getString(TodayWinOrLoseActvity.this,"chatroomToken","");
        bindView();
        initRecycle();
        initData();


    }

    @Override
    protected void init() {

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        initData();
    }

    private void bindView() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        recyclerView=findViewById(R.id.win_or_lose_recycle);
        winPrice=findViewById(R.id.win_price);
        actionBatCenter=findViewById(R.id.action_bar_text);
        actionBatCenter.setText(Utils.getString(R.string.今日盈亏));
        actionRight=findViewById(R.id.action_bar_right);
        actionRight.setText(Utils.getString(R.string.分享));
        actionRight.setVisibility(View.GONE);
        //盈众没有聊天室,隐藏分享按钮
       /* if(appName.equals(Utils.getString(R.string.盈众彩票))){
            actionRight.setVisibility(View.INVISIBLE);
            actionRight.setClickable(false);
        }*/
       //根据聊天室开关是否显示分享
        String chatRoomSwitch = SharePreferencesUtil.getString(this,"chatRoomSwitch","");
        if (chatRoomSwitch.equals("0")){
            actionRight.setVisibility(View.VISIBLE);
        }else {
            actionRight.setVisibility(View.INVISIBLE);
        }
        actionRight.setOnClickListener(this);
        back=findViewById(R.id.action_bar_return);
        back.setOnClickListener(this);
    }
    private void initData() {
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        loadingLinear.setVisibility(View.VISIBLE);
        typeList.clear();
        typeList.add(Utils.getString(R.string.投注金额));
        typeList.add(Utils.getString(R.string.中奖金额));
        typeList.add(Utils.getString(R.string.活动返点));
        typeList.add(Utils.getString(R.string.个人佣金));
        typeList.add(Utils.getString(R.string.充值金额));
        typeList.add(Utils.getString(R.string.提现金额));
        typeList.add(Utils.getString(R.string.领普通红包));
        typeList.add(Utils.getString(R.string.发普通红包));
        typeList.add(Utils.getString(R.string.红包退回));
        Map<String, Object> data = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_06me, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                JSONObject jsonObject = JSONObject.parseObject(content);
                BigDecimal bettingPrice = jsonObject.getBigDecimal("bettingPrice");//投注金额
                betPrice=bettingPrice+"";
                BigDecimal lotteryTotalPrice = jsonObject.getBigDecimal("lotteryTotalPrice");//中奖金额
                zjPrice=lotteryTotalPrice.setScale(2)+"";
                BigDecimal activityReturn = jsonObject.getBigDecimal("activityReturn");//活动返点
                BigDecimal commissionTotalPrice = jsonObject.getBigDecimal("commissionTotalPrice");//个人佣金
                BigDecimal rechargeTotalPrice = jsonObject.getBigDecimal("rechargeTotalPrice");//充值金额
                BigDecimal drawTotalPrice = jsonObject.getBigDecimal("drawTotalPrice");//提现金额
                BigDecimal reciveRed = jsonObject.getBigDecimal("reciveRed");//红包领取
                BigDecimal returnRed = jsonObject.getBigDecimal("returnRed");//红包发放
                BigDecimal giveRed = jsonObject.getBigDecimal("giveRed");//红包退回
                profitAndLoss = jsonObject.getBigDecimal("profitAndLoss");//盈利金额
//                BigDecimal  getTotalPrice = jsonObject.getBigDecimal("getTotalPrice");//返点金额
                winPrice.setText(profitAndLoss.setScale(2)+"");
                for (int i=0;i<typeList.size();i++) {
                    if(i==0){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(bettingPrice+"",typeList.get(0)));
                    }else if(i==1){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(lotteryTotalPrice.setScale(2)+"",typeList.get(1)));
                    }else if(i==2){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(activityReturn+"",typeList.get(2)));
                    }else if(i==3){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(commissionTotalPrice+"",typeList.get(3)));
                    }else if(i==4){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(rechargeTotalPrice+"",typeList.get(4)));
                    }else if(i==5){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(drawTotalPrice+"",typeList.get(5)));
                    }else if(i==6){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(reciveRed+"",typeList.get(6)));
                    }else if(i==7){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(returnRed+"",typeList.get(7)));
                    }else if(i==8){
                        winOrLoseModelArrayList.add(new WinOrLoseModel(giveRed+"",typeList.get(8)));
                    }
                }
                winOrLoseRecycleAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ErrorUtil.showErrorLayout(TodayWinOrLoseActvity.this,recyclerView,errorLinear,reloadTv);
            }
        });
    }
    private void initRecycle() {
        winOrLoseRecycleAdapter= new WinOrLoseRecycleAdapter(winOrLoseModelArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        MainClidItemGridDecoration mainClidItemGridDecoration = new MainClidItemGridDecoration(this);
        recyclerView.addItemDecoration(mainClidItemGridDecoration);
        View footView=LayoutInflater.from(this).inflate(R.layout.win_or_lose_recycle_foot,null);
        recyclerView.setAdapter(winOrLoseRecycleAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        //先setAdapter再添加尾部(别问为什么,反正这样搞能正常添加尾部)
        winOrLoseRecycleAdapter.addFooterView(footView);
        showBeiZhu=footView.findViewById(R.id.show);
        showBeiZhu.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show:
                View popupView= LayoutInflater.from(this).inflate(R.layout.win_or_lose_popupwindow,null);
                popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setAnimationStyle(R.style.popupAnimationNormol2);//设置进出动画
                popupWindow.setTouchable(true);//响应内部点击
                popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM,0,0);
                popupDismiss=popupView.findViewById(R.id.dismiss);
                popupDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.action_bar_return:
                finish();
                break;
//                //分享今日盈亏
            case R.id.action_bar_right:
                //跳转聊天室列表
                if(loadingLinear.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.请等待数据加载完成));
                }
                else{
/*                    Intent intent = new Intent(TodayWinOrLoseActvity.this, ChatroomListActivity.class);
                    intent.putExtra("isShare",true);
                    intent.putExtra("betPrice",betPrice);
                    intent.putExtra("zjPrice",zjPrice);
                    intent.putExtra("profitAndLoss",profitAndLoss+"");
                    intent.putExtra("fromWhere","winOrLose");
                    startActivity(intent);*/
                }

                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
