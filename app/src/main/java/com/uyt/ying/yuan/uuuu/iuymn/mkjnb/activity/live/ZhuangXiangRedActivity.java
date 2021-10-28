package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.ZxEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class ZhuangXiangRedActivity extends BaseActivity {
    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    @BindView(R.id.yesterday_bet_amount_tv)
    TextView yesterday_bet_amount_tv;
    @BindView(R.id.yesterday_xian_bet_amount_tv)
    TextView yesterday_xian_bet_amount_tv;
    @BindView(R.id.zx_proportion_tv)
    TextView zx_proportion_tv;
    @BindView(R.id.zx_amount_tv)
    TextView zx_amount_tv;
    @BindView(R.id.mine_grade_tv)
    TextView mine_grade_tv;
    @BindView(R.id.zx_game_rule_tv)
    TextView zx_game_rule_tv;
    @BindView(R.id.zx_target_grade_tv)
    TextView zx_target_grade_tv;
    @BindView(R.id.can_not_receive_linear)
    LinearLayout can_not_receive_linear;
    @BindView(R.id.can_receive_tip_tv)
    TextView can_receive_tip_tv;
    @BindView(R.id.cannot_receive_amount_tv)
    TextView cannot_receive_amount_tv;
    @BindView(R.id.cannot_receive_vip_tv)
    TextView cannot_receive_vip_tv;
    @BindView(R.id.get_zx_red_btn)
    Button get_zx_red_btn;
    @BindView(R.id.zx_tip_tv)
    TextView zx_tip_tv;
    @BindView(R.id.ax_banner_iv)
    ImageView ax_banner_iv;
    MyHandler myHandler;
    private ZxEntity zxEntity;
    //专享红包领取等级
    private int zxHBGrade;
    private static  String GET_ZX_RED=Utils.getString(R.string.领取专享红包);
    private static  String UPDATE_LEVEL=Utils.getString(R.string.提升等级);
    private static  String ALREADY_GET=Utils.getString(R.string.已领取);
    private String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuang_xiang_red);
        ButterKnife.bind(this);
        StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        getIntentData();
        myHandler= new MyHandler(new SoftReference<>(this));
        initToolBar();
        requestZRedInfo();
        GlideLoadViewUtil.LoadNormalView(this,Utils.getHomeLogo("liveIcon9"),ax_banner_iv);
    }

    private void getIntentData() {
        roomId=getIntent().getStringExtra("roomId");
    }

    private void requestZRedInfo() {
        HttpApiUtils.CPnormalRequest(this, null,RequestUtil.ZX_RED_INFO, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                zxEntity = JSONObject.parseObject(result, ZxEntity.class);
                String totalPrice = zxEntity.getTotalPrice();
                yesterday_bet_amount_tv.setText(totalPrice);
                if(totalPrice.length()>7&&totalPrice.length()<10){
                    yesterday_bet_amount_tv.setTextSize(16);
                }else if(totalPrice.length()>=10){
                    yesterday_bet_amount_tv.setTextSize(14);
                }else {
                    yesterday_bet_amount_tv.setTextSize(20);
                }
                String xdPrice = zxEntity.getXdPrice();
                if(xdPrice.length()>7&&xdPrice.length()<10){
                    yesterday_xian_bet_amount_tv.setTextSize(16);
                }else if(xdPrice.length()>=10){
                    yesterday_xian_bet_amount_tv.setTextSize(14);
                }else {
                    yesterday_xian_bet_amount_tv.setTextSize(20);
                }
                yesterday_xian_bet_amount_tv.setText(xdPrice);
                String zxHBRate = zxEntity.getZxHBRate();
                zx_proportion_tv.setText(zxHBRate);
                String bonus = zxEntity.getBonus();
                if(bonus.length()>7&&bonus.length()<10){
                    zx_amount_tv.setTextSize(16);
                }else if(bonus.length()>=10){
                    zx_amount_tv.setTextSize(14);
                }else {
                    zx_amount_tv.setTextSize(20);
                }
                zx_amount_tv.setText(bonus);
                int grade = zxEntity.getGrade();
                mine_grade_tv.setText("VIP"+ SharePreferencesUtil.getInt(MyApplication.getInstance(), CommonStr.GRADE,1));

                zx_tip_tv.setText(Utils.getString(R.string.达到条件后投注推荐玩法即可享受投注金额的)+zxHBRate+Utils.getString(R.string.专享回馈红包));
                myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
    private void requestHBParameter() {
        Utils.docking(new HashMap<>(), RequestUtil.HB_PARAMETER, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(content, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
                String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                instance.setHbParameter(hbParameter);
                instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                initHbRule(rongcloudHBParameter);

            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });

    }
    private void initHbRule(RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter) {
      
        getMoneyInfo();

        /*
        活动规则
         */
        zxHBGrade = rongcloudHBParameter.getZxHBGrade();    //专享红包领取等级
        double zxHBRate = rongcloudHBParameter.getZxHBRate();
        zx_target_grade_tv.setText(Utils.getString(R.string.等级达到VIP)+zxHBGrade);
        zx_game_rule_tv.setText(Utils.getString(R.string.只要您等级达到VIP) + zxHBGrade + Utils.getString(R.string.投注推荐玩法即可享受投注金额的)+zxHBRate+Utils.getString(R.string.专享回馈红包达到VIP) + zxHBGrade + Utils.getString(R.string.的会员请第二天晚上12点前登录领取此活动只有投注推荐玩法才能享受)+zxHBRate+Utils.getString(R.string.专享红包没有达到等级的请抓紧时间提升自己的等级));


    }
    @OnClick({R.id.get_zx_red_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get_zx_red_btn:
                String btnToString = get_zx_red_btn.getText().toString();
                if(btnToString.equals(GET_ZX_RED)){
                    //领取专享红包
                    resOpenZxRed(new HashMap<String, Object>(), SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l));
                }else {
                    //跳转充值
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest.equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(ZhuangXiangRedActivity.this,ZhuangXiangRedActivity.this);
                    }else {

                        startActivity(new Intent(ZhuangXiangRedActivity.this, RechargeActivity.class));
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     * 请求专享 开红包接口
     *  @param data
     * @param user_id
     */
    private void resOpenZxRed(HashMap<String, Object> data, Long user_id) {
        data.put("user_id",user_id);
        data.put("chatRoomId",roomId);
        HttpApiUtils.CpRequest(this, null,RequestUtil.HB_OPEN_ZX_HB, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String isComplete = jsonObject.getString("isComplete");
                isComplete = StringMyUtil.isEmptyString(isComplete)?"0":isComplete;
                if(isComplete.equals("1")){//可以领取
                    String isReceive = jsonObject.getString("isReceive");
                    if(isReceive.equals("1")){//已领取完
                        String message = jsonObject.getString("message");
                        showToast(message);
                    }else {//未领取完
                        BigDecimal  tzPrice   = jsonObject.getBigDecimal("tzPrice");
                        if(null==tzPrice){
                            tzPrice=BigDecimal.ZERO;
                        }
                        if(tzPrice.compareTo(BigDecimal.ONE)>0){//昨日投注金额大于0
                            BigDecimal   zxHBPrice   = jsonObject.getBigDecimal("zxHBPrice");
                            showToast(Utils.getString(R.string.领取成功));
                            get_zx_red_btn.setBackgroundResource(R.drawable.can_not_get_hb_shape);
                            get_zx_red_btn.setText(ALREADY_GET);
                            get_zx_red_btn.setClickable(false);
                        }else {//昨日投注金额不达标
                            String message = jsonObject.getString("message");
                            if(StringMyUtil.isEmptyString(message)){
                                message =Utils.getString(R.string.昨日)+Utils.getString(R.string.投注)+Utils.getString(R.string.金额未达标);
                            }
                            showToast(message);
                        }
                    }

                }else {//不能领取
                    String message = jsonObject.getString("message");
                    showToast(message);
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initToolBar() {
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.专享红包));
    }
    public static void  startAty(Context context,String roomId){
        Intent intent = new Intent(context, ZhuangXiangRedActivity.class);
        intent.putExtra("roomId",roomId);
        context.startActivity(intent);
    }
    /*
    获得用户资金信息
     */
    private void getMoneyInfo() {
        HashMap<String, Object> personMoney = new HashMap<>();//REQUEST_06rzq
        Utils.docking(personMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                //成长积分
                BigDecimal growthIntegral = jsonObject.getJSONObject("memberMoney").getBigDecimal("growthIntegral");
                getDengji(growthIntegral);
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
    private void getDengji( BigDecimal growthIntegral ) {
        Map<String, Object> dataMoneny = new HashMap<>();
        Utils.docking(dataMoneny, RequestUtil.REQUEST_08rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                Integer minePointGrade = jsonObject.getInteger(CommonStr.GRADE);
                JSONArray memberGradeMechanismList = jsonObject.getJSONArray("memberGradeMechanismList");
                JSONObject membeyGradeObject=null;
                for (int i=0;i<memberGradeMechanismList.size();i++){
                    JSONObject jsonObject1 = memberGradeMechanismList.getJSONObject(i);
                    int grade = jsonObject1.getInteger("grade");//vip等级
                    if((grade+1+"").equals(zxHBGrade+"")){
                        membeyGradeObject = jsonObject1;
                        break;
                    }
                }

                if(membeyGradeObject!=null){
                    if(minePointGrade+1 <zxHBGrade){
                        //等级未达到领取要求
                        can_not_receive_linear.setVisibility(View.VISIBLE);
                        can_receive_tip_tv.setVisibility(View.GONE);
                        BigDecimal needCharge = membeyGradeObject.getBigDecimal("growthIntegral").subtract(growthIntegral);
                        cannot_receive_vip_tv.setText("VIP"+zxHBGrade);
                        cannot_receive_amount_tv.setText(Utils.getString(R.string.逗号还需充值)+needCharge+Utils.getString(R.string.元));
                        get_zx_red_btn.setText(UPDATE_LEVEL);

                    }else {
                        //显示可以领取相关提示
                        can_not_receive_linear.setVisibility(View.GONE);
                        can_receive_tip_tv.setVisibility(View.VISIBLE);
                        int isReceive = zxEntity.getIsReceive();
                        if(isReceive==1){
                            //可以领取专享红包
                            get_zx_red_btn.setClickable(true);
                            get_zx_red_btn.setText(GET_ZX_RED);
                        }else {
                            //等级达标，但是不能领取（已领取 | 限定玩法投注金额为0）
                            get_zx_red_btn.setBackgroundResource(R.drawable.can_not_get_hb_shape);
                            get_zx_red_btn.setClickable(false);
                            BigDecimal zxPrice = new BigDecimal(zxEntity.getXdPrice());
                            if(zxPrice.compareTo(BigDecimal.ZERO)>0){
                                //已领取
                                get_zx_red_btn.setText(ALREADY_GET);
                            }else {
                                //昨日限定投注为0,
                                get_zx_red_btn.setText(GET_ZX_RED);

                            }
                        }
                    }
                }

            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    static class  MyHandler extends Handler{
        SoftReference<ZhuangXiangRedActivity> activitySoftReference;

        public MyHandler(SoftReference<ZhuangXiangRedActivity> activitySoftReference) {
            this.activitySoftReference = activitySoftReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ZhuangXiangRedActivity zhuangXiangRedActivity = activitySoftReference.get();
            if(zhuangXiangRedActivity !=null){
                switch (msg.what){
                    case 1:
                        HbGameClassModel instance = HbGameClassModel.getInstance();
                        if(instance==null){
                            zhuangXiangRedActivity.requestHBParameter();
                        }else {
                            zhuangXiangRedActivity.initHbRule(instance.getHbParameter().getRongcloudHBParameter());
                        }
                        break;
                    default:
                        break;
                }
            }

        }
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
