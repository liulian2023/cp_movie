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
    //????????????????????????
    private int zxHBGrade;
    private static  String GET_ZX_RED=Utils.getString(R.string.??????????????????);
    private static  String UPDATE_LEVEL=Utils.getString(R.string.????????????);
    private static  String ALREADY_GET=Utils.getString(R.string.?????????);
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

                zx_tip_tv.setText(Utils.getString(R.string.????????????????????????????????????????????????????????????)+zxHBRate+Utils.getString(R.string.??????????????????));
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
        ????????????
         */
        zxHBGrade = rongcloudHBParameter.getZxHBGrade();    //????????????????????????
        double zxHBRate = rongcloudHBParameter.getZxHBRate();
        zx_target_grade_tv.setText(Utils.getString(R.string.????????????VIP)+zxHBGrade);
        zx_game_rule_tv.setText(Utils.getString(R.string.?????????????????????VIP) + zxHBGrade + Utils.getString(R.string.?????????????????????????????????????????????)+zxHBRate+Utils.getString(R.string.????????????????????????VIP) + zxHBGrade + Utils.getString(R.string.???????????????????????????12???????????????????????????????????????????????????????????????)+zxHBRate+Utils.getString(R.string.?????????????????????????????????????????????????????????????????????));


    }
    @OnClick({R.id.get_zx_red_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get_zx_red_btn:
                String btnToString = get_zx_red_btn.getText().toString();
                if(btnToString.equals(GET_ZX_RED)){
                    //??????????????????
                    resOpenZxRed(new HashMap<String, Object>(), SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l));
                }else {
                    //????????????
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
     * ???????????? ???????????????
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
                if(isComplete.equals("1")){//????????????
                    String isReceive = jsonObject.getString("isReceive");
                    if(isReceive.equals("1")){//????????????
                        String message = jsonObject.getString("message");
                        showToast(message);
                    }else {//????????????
                        BigDecimal  tzPrice   = jsonObject.getBigDecimal("tzPrice");
                        if(null==tzPrice){
                            tzPrice=BigDecimal.ZERO;
                        }
                        if(tzPrice.compareTo(BigDecimal.ONE)>0){//????????????????????????0
                            BigDecimal   zxHBPrice   = jsonObject.getBigDecimal("zxHBPrice");
                            showToast(Utils.getString(R.string.????????????));
                            get_zx_red_btn.setBackgroundResource(R.drawable.can_not_get_hb_shape);
                            get_zx_red_btn.setText(ALREADY_GET);
                            get_zx_red_btn.setClickable(false);
                        }else {//???????????????????????????
                            String message = jsonObject.getString("message");
                            if(StringMyUtil.isEmptyString(message)){
                                message =Utils.getString(R.string.??????)+Utils.getString(R.string.??????)+Utils.getString(R.string.???????????????);
                            }
                            showToast(message);
                        }
                    }

                }else {//????????????
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
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.????????????));
    }
    public static void  startAty(Context context,String roomId){
        Intent intent = new Intent(context, ZhuangXiangRedActivity.class);
        intent.putExtra("roomId",roomId);
        context.startActivity(intent);
    }
    /*
    ????????????????????????
     */
    private void getMoneyInfo() {
        HashMap<String, Object> personMoney = new HashMap<>();//REQUEST_06rzq
        Utils.docking(personMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                //????????????
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
                    int grade = jsonObject1.getInteger("grade");//vip??????
                    if((grade+1+"").equals(zxHBGrade+"")){
                        membeyGradeObject = jsonObject1;
                        break;
                    }
                }

                if(membeyGradeObject!=null){
                    if(minePointGrade+1 <zxHBGrade){
                        //???????????????????????????
                        can_not_receive_linear.setVisibility(View.VISIBLE);
                        can_receive_tip_tv.setVisibility(View.GONE);
                        BigDecimal needCharge = membeyGradeObject.getBigDecimal("growthIntegral").subtract(growthIntegral);
                        cannot_receive_vip_tv.setText("VIP"+zxHBGrade);
                        cannot_receive_amount_tv.setText(Utils.getString(R.string.??????????????????)+needCharge+Utils.getString(R.string.???));
                        get_zx_red_btn.setText(UPDATE_LEVEL);

                    }else {
                        //??????????????????????????????
                        can_not_receive_linear.setVisibility(View.GONE);
                        can_receive_tip_tv.setVisibility(View.VISIBLE);
                        int isReceive = zxEntity.getIsReceive();
                        if(isReceive==1){
                            //????????????????????????
                            get_zx_red_btn.setClickable(true);
                            get_zx_red_btn.setText(GET_ZX_RED);
                        }else {
                            //????????????????????????????????????????????? | ???????????????????????????0???
                            get_zx_red_btn.setBackgroundResource(R.drawable.can_not_get_hb_shape);
                            get_zx_red_btn.setClickable(false);
                            BigDecimal zxPrice = new BigDecimal(zxEntity.getXdPrice());
                            if(zxPrice.compareTo(BigDecimal.ZERO)>0){
                                //?????????
                                get_zx_red_btn.setText(ALREADY_GET);
                            }else {
                                //?????????????????????0,
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
