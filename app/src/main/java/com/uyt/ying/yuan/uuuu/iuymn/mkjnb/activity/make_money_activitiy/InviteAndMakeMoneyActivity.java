package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.AgentJournalingActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedStatus;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow.AgentShowPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow.IntegralDetailsDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.enum_pakege.PopType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.InviteRedAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.MakeMoneyAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money.RuleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.InviteRedModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MakeMoneyModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RuleModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusPackPop;
import com.sunfusheng.marqueeview.MarqueeView;
import org.greenrobot.eventbus.EventBus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

public class InviteAndMakeMoneyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.money_back_iv)
    ImageView backIv;
    @BindView(R.id.money_right_iv)
    ImageView rightIv;
    TextView inviteCodeTv;
    TextView copyTv;
    ImageView dianIv;
    RecyclerView mRecyckeView;
    RecyclerView ruleRecycle;
    @BindView(R.id.invite_tv)
    TextView inviteTv;
    @BindView(R.id.qr_code_tv)
    TextView qrCodeTv;
    @BindView(R.id.make_money_main_recycle)
    RecyclerView mainRecy;
    @BindView(R.id.integral_iv)
    ImageView integralIv;
    @BindView(R.id.get_reward_tv)
    TextView getRewardTv;
    private MakeMoneyAdapter makeMoneyAdapter;
    private ArrayList<MakeMoneyModel> makeMoneyModelArrayList =  new ArrayList<>();
    private RuleAdapter ruleAdapter;
    private ArrayList<RuleModel> ruleModelArrayList = new ArrayList<>();
    //邀请红包详情recycle
    private MakeMoneyAdapter processAdapter;
    private ArrayList<MakeMoneyModel> processArrayList =  new ArrayList<>();
    String shareContent="";//分享的文字内容
    String inviteCode;
    String inviteAddress;
    int inviteRedCount;
    //领取红包进度recycle
    private RecyclerView inviteRedRecy;
    private InviteRedAdapter inviteRedAdapter;
    private ArrayList<InviteRedModel> inviteRedModelArrayList = new ArrayList<>();
    //有效邀请人数
    private TextView effective_invite_num_tv;
    //累计邀请人数
    private  TextView total_invite_tv;
    private MarqueeView username_tv;
    private int redNum;
    private Integer canReceive;
    private Integer receive;
    private Integer effectPeople;
    private Integer totalNum;
    private List<SpannableString> noticeList = new ArrayList<>();//跑马灯字符串数据
    private String chatRoomId;
    private AgentShowPop agentShowPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_and_make_money);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this,true);
        StatusBarUtil.setColor(this,Color.WHITE);
        getIntentData();
        initRecy();
        initClick();
        requestShareContent();
        requestInviteRedList();
//        requestInvateRedCount();
    }

    private void getIntentData() {
        chatRoomId = getIntent().getStringExtra("chatRoomId");
    }

    public static void startAty(Context context,String chatRoomId){
        Intent intent = new Intent(context, InviteAndMakeMoneyActivity.class);
        intent.putExtra("chatRoomId",chatRoomId);
        context.startActivity(intent);
    }

    /*
    *//**
     * 邀请红包可领取次数(没有领取次数时点击立即领取toast提示)
     *//*
    private void requestInvateRedCount() {
        HttpApiUtils.CPnormalRequest(this, RequestUtil.INVITE_RED_COUNT, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONparseObject(result);
                Integer data = jsongetInteger("data");
                inviteRedCount=data;
            }

            @Override
            public void onFaild(String msg) {
            }
        });
    }*/

    /**
     *直接分享的url路径
     */
    private void requestShareContent() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",10);
        Utils.docking(data, RequestUtil.REQUEST_28rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject promptWords = jsonObject.getJSONObject("promptWords");
                shareContent = promptWords.getString("contentTxt");
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            canReceive--;
            receive++;
            handlerRedNum();
            requestInviteRedList();
        }
    }

    private void initClick() {
        backIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
        integralIv.setOnClickListener(this);
        qrCodeTv.setOnClickListener(this);
        inviteTv.setOnClickListener(this);
        dianIv.setOnClickListener(this);
        getRewardTv.setOnClickListener(this);

    }

    /**
     *初始化页面主列表(主要是要添加头部)
     *
     * */
    private void initRecy() {
        makeMoneyAdapter = new MakeMoneyAdapter(makeMoneyModelArrayList);
        mainRecy.setLayoutManager(new GridLayoutManager(this,2));
        mainRecy.setAdapter(makeMoneyAdapter);
        View headView  = LayoutInflater.from(this).inflate(R.layout.make_money_headview,null);
        makeMoneyAdapter.addHeaderView(headView);
        bindHeadView(headView);
        initInviteNumRecycle();
        initProcessRecycle();//赚钱流程和详情recycleView
        initRuleRecycle();
/*        noticeList.add(theme);
        paoma.startWithList(noticeList, R.anim.anim_bottom_in, R.anim.anim_top_out);  */
        getWinner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        username_tv.startFlipping();
    }

    private void getWinner() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageSize", "200");
        data.put("pageNo",1);
        Utils.docking(data, RequestUtil.REQUEST_zz3, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject object = JSONObject.parseObject(content);
                JSONArray bonusRecordList = object.getJSONArray("bonusRecordList");
                for (int i = 0; i < bonusRecordList.size(); i++) {
                    JSONObject jsonObject = bonusRecordList.getJSONObject(i);
                    String nickname = jsonObject.getString("nickname");
                    String yqRedPrice = jsonObject.getString("yqRedPrice");
                    String info =Utils.getString(R.string.恭喜)+nickname+Utils.getString(R.string.成功邀请会员注册获得红包)+yqRedPrice+Utils.getString(R.string.元);
                    SpannableString spannableString = new SpannableString(info);
                    ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#F46518"));
                    spannableString.setSpan(span,2,2+nickname.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    noticeList.add(spannableString);
                }
                username_tv.startWithList(noticeList);

            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    private void initInviteNumRecycle() {
        inviteRedAdapter = new InviteRedAdapter(inviteRedModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        inviteRedRecy.setLayoutManager(linearLayoutManager);
        inviteRedRecy.setAdapter(inviteRedAdapter);
        inviteRedAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                InviteRedModel inviteRedModel = inviteRedModelArrayList.get(position);
                if(inviteRedModel.canOpen){
//                  OpenInviteRedActivity.startAty(InviteAndMakeMoneyActivity.this,redNum,"");
                    OpenRedActivity.startAty(InviteAndMakeMoneyActivity.this,redNum,chatRoomId);
                }
            }
        });
    }
    private void initRuleRecycle() {
        ruleAdapter = new RuleAdapter(ruleModelArrayList);
        ruleRecycle.setLayoutManager(new LinearLayoutManager(this));
        ruleRecycle.setAdapter(ruleAdapter);
        HbGameClassModel instance = HbGameClassModel.getInstance();
        if(null==instance){
            requestHBParameter();
        }else {
            initRuleList(instance);
        }
    }

    private void initRuleList(  HbGameClassModel instance) {
        RongcloudHBParameter hbParameter = instance.getHbParameter();
        if(hbParameter==null){
            return;
        }
        RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
        int scoreByOnePerson = rongcloudHBParameter.getScoreByOnePerson();
        int quYueHBMinAmount = rongcloudHBParameter.getQuYueHBMinAmount();
        int quYueHBMaxAmount = rongcloudHBParameter.getQuYueHBMaxAmount();
        int quYueHBGrade = rongcloudHBParameter.getQuYueHBGrade();
        int quYueHBScore = rongcloudHBParameter.getQuYueHBScore();
        int times = scoreByOnePerson==0?0:(quYueHBScore/scoreByOnePerson);
        String cpTzFyRate = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_TZ_FLY_RATE, "");
        cpTzFyRate = StringMyUtil.isEmptyString(cpTzFyRate)?"0":cpTzFyRate;
        ruleModelArrayList.add(new RuleModel(Utils.getString(R.string.每邀请1人成功注册可获得1个邀请红包每包金额)+quYueHBMinAmount+"-"+quYueHBMaxAmount+Utils.getString(R.string.元)));
        ruleModelArrayList.add(new RuleModel(Utils.getString(R.string.有效邀请人数每满)+times+Utils.getString(R.string.人即可拆一轮红包等级要求必须达到VIP)+quYueHBGrade+Utils.getString(R.string.才能参与)));
        ruleModelArrayList.add(new RuleModel(Utils.getString(R.string.打开红包100获得奖金最高每包可以抢到)+quYueHBMaxAmount+Utils.getString(R.string.元)));
        ruleModelArrayList.add(new RuleModel(Utils.getString(R.string.相同IP地相同设备码均视为同一个人注册如未真实注册重复注册等非法行为均会取消资格)));
        BigDecimal multiply = new BigDecimal(cpTzFyRate).multiply(new BigDecimal("10")).setScale(2,BigDecimal.ROUND_DOWN);
        ruleModelArrayList.add(new RuleModel(String.format(Utils.getString(R.string.邀请朋友成为下级将获得平台给您的返佣下级投注后) +
                Utils.getString(R.string.您可以获得独家返佣朋友每下注1000元平台返您元),cpTzFyRate, multiply)));
    }

    private void requestHBParameter() {
        Utils.docking(new HashMap<>(), RequestUtil.HB_PARAMETER, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(content, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
//                if(zxHBSwitch==1){
                    HbGameClassModel instance = HbGameClassModel.getInstance();
                    instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                    instance.setHbParameter(hbParameter);
                    EventBus.getDefault().postSticky(instance);
                    initRuleList(instance);
//                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });

    }
    private void initProcessRecycle() {
        processAdapter = new MakeMoneyAdapter(processArrayList);
        mRecyckeView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyckeView.setAdapter(processAdapter);
        processAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击item右上方问号图标, 弹出有效好友规则说明
                initRulePop();
            }
        });
    }

    private void initRulePop() {
        if (agentShowPop == null) {
            agentShowPop = new AgentShowPop(InviteAndMakeMoneyActivity.this, InviteAndMakeMoneyActivity.this, PopType.RULE);
        }
        agentShowPop.showAtLocation(mRecyckeView, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(InviteAndMakeMoneyActivity.this, 0.7f);
    }

    private void requestInviteRedList( ) {
        HttpApiUtils.CPnormalRequest(this,null, RequestUtil.MAKE_MONEY_RULE, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
                if(null==data){
                    return;
                }
                processArrayList.clear();
                inviteCode = data.getString("inviteCode");//邀请码
                inviteAddress =Utils.getRandomAppDownloadUrl();//邀请地址
                inviteCodeTv.setText(inviteCode);
                BigDecimal redPriceYesterday = data.getBigDecimal("redPriceYesterday");//昨日收益
                BigDecimal totalPrice = data.getBigDecimal("totalPrice");// 累计收益
                BigDecimal totalInvitePoint = data.getBigDecimal("totalInvitePoint");// 累计积分
                Integer peopleCount = data.getInteger("peopleCount");//已邀请好友
                receive  = data.getInteger("receive");//已领取
                canReceive  = data.getInteger("canReceive");//还可以领取
                redNum  = data.getInteger("redNum");// 一次可以领几个红包
                effectPeople = data.getInteger("effectivePeople");//有效邀请人数
                totalNum = data.getInteger("totalNum");//所有可领的红包数(已领取+未领取)
                BigDecimal invitePoint = data.getBigDecimal("invitePoint");//剩余积分
                processArrayList.add(new MakeMoneyModel(R.drawable.img1,Utils.getString(R.string.已邀请好友),peopleCount+""));
                processArrayList.add(new MakeMoneyModel(R.drawable.img2,Utils.getString(R.string.有效邀请人数),effectPeople+""));
/*                processArrayList.add(new MakeMoneyModel(R.drawable.img3,Utils.getString(R.string.累计积分),totalInvitePoint+""));
                processArrayList.add(new MakeMoneyModel(R.drawable.img4,Utils.getString(R.string.剩余积分),invitePoint+""));*/
                processArrayList.add(new MakeMoneyModel(R.drawable.img5,Utils.getString(R.string.昨日收益),redPriceYesterday+Utils.getString(R.string.元)));
                processArrayList.add(new MakeMoneyModel(R.drawable.img6,Utils.getString(R.string.累计收益),totalPrice+Utils.getString(R.string.元)));
                processAdapter.notifyDataSetChanged();
                total_invite_tv.setText(peopleCount+"");
                effective_invite_num_tv.setText(effectPeople+"");
/*              totalNum=3;
                effectPeople=10;
                receive=1;
                canReceive=2;*/
                handlerRedNum();
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     *领取进度 recycleView
     */
    private void handlerRedNum() {
        inviteRedModelArrayList.clear();
        if (redNum < 1) {
            return;
        }
        int redCount = totalNum + 5;
        for (int i = 0; i < redCount; i ++) {
            InviteRedModel redEntity = new InviteRedModel();
            redEntity.setPeoperNum(redNum * (i+1));
            redEntity.setRedCount(redNum);
            if (i <= receive-1) {
                redEntity.isOpened = true;
            }
            if (!redEntity.isOpened) {
                if (i<totalNum && i==receive) {
                    redEntity.canOpen = true;
                }
            }

            int num = effectPeople % redNum;
            float progress = num/(redNum*1.f);
            if (totalNum == 0 && i == 0) {
                redEntity.leftProgress = (float) (progress*0.5);
                redEntity.rightProgress = 0.f;
            }else{
                if (i < totalNum-1) {
                    redEntity.leftProgress = 0.5f;
                    redEntity.rightProgress = 0.5f;
                }else if (i == totalNum-1){
                    redEntity.leftProgress = 0.5f;
                    if (progress >= 0.5) {
                        redEntity.rightProgress = 0.5f;
                    }else{
                        redEntity.rightProgress = progress;
                    }
                }else if (i == totalNum-1+1){
                    if (progress >= 0.5) {
                        redEntity.leftProgress = progress-0.5f;
                    }else{
                        redEntity.leftProgress = 0.f;
                    }
                    redEntity.rightProgress = 0.f;
                }else{
                    redEntity.leftProgress = 0.f;
                    redEntity.rightProgress = 0.f;
                }
            }
            inviteRedModelArrayList.add(redEntity);
        }


        inviteRedAdapter.notifyDataSetChanged();
        for (int i = 0; i < inviteRedModelArrayList.size(); i++) {
            boolean canOpen = inviteRedModelArrayList.get(i).canOpen;
            if(canOpen){
                inviteRedRecy.scrollToPosition(i);
                break;
            }
        }
    }

    private void bindHeadView(View headView) {
        username_tv=headView.findViewById(R.id.recharge_marquee_view);
        total_invite_tv=headView.findViewById(R.id.total_invite_tv);
        effective_invite_num_tv=headView.findViewById(R.id.effective_invite_num_tv);
        inviteRedRecy=headView.findViewById(R.id.invite_num_recycle);
        inviteCodeTv=headView.findViewById(R.id.invite_code_tv);
        copyTv=headView.findViewById(R.id.copy_invite_code_tv);
        dianIv=headView.findViewById(R.id.money_dian_iv);
        LinearLayout wrap_linear=headView.findViewById(R.id.wrap_linear);
        GlideLoadViewUtil.setBackGround(this,Utils.getHomeLogo("liveIcon11"),wrap_linear);
        if(SharePreferencesUtil.getString(MyApplication.getInstance(),"isInfiniteAgent","").equals("0")){
            dianIv.setVisibility(View.VISIBLE);
        }else {
            //非无限代理隐藏图标
            dianIv.setVisibility(View.INVISIBLE);
        }
        mRecyckeView=headView.findViewById(R.id.make_money_recycle);
        ruleRecycle=headView.findViewById(R.id.aty_rule_recycle);
        copyTv.setOnClickListener(this);
/*        copyTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                OpenInviteRedActivity.startAty(InviteAndMakeMoneyActivity.this,redNum);
                OpenRedActivity.startAty(InviteAndMakeMoneyActivity.this,redNum,chatRoomId);
                return false;
            }
        });*/
    }


    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.money_back_iv:
            finish();
            break;
            //规则说明
            case R.id.money_right_iv:
                initRulePop();
                break;
                //复制邀请码
            case R.id.copy_invite_code_tv:
                Utils.copyStr("inviteCode",inviteCodeTv.getText().toString());
                break;
                //跳转代理报表
            case R.id.money_dian_iv:
                startActivity(new Intent(InviteAndMakeMoneyActivity.this, AgentJournalingActivity.class));
                break;
            case R.id.integral_iv:
                IntegralDetailsDialog integralDetailsDialog = new IntegralDetailsDialog();
                integralDetailsDialog.show(getSupportFragmentManager(),"IntegralDetailsDialog");
                break;
            case R.id.qr_code_tv://跳转二维码页面
                Intent intent = new Intent(InviteAndMakeMoneyActivity.this, MineQRCodeActivity.class);
                intent.putExtra("shareContent",shareContent);
                startActivity(intent);
                break;
            case R.id.invite_tv:
            /*    List<String> inviteAddressList = Arrays.asList(inviteAddress.split(","));
                int nextInt = new Random().nextInt(inviteAddressList.size() - 1);*/
                RouteUtils.start2Share(InviteAndMakeMoneyActivity.this,shareContent+inviteAddress+"?code="+inviteCodeTv.getText().toString());
                break;
            case R.id.get_reward_tv:
                if(inviteRedCount==0){
                    showToast(Utils.getString(R.string.暂无可领奖励));
                }else {
                    OpenPackPop("",RedStatus.NORMAL,"",RedType.QY,"","");
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 开红包pop 和回调
     */
    public void OpenPackPop(String redId, RedStatus redStatus, String qbPrice, RedType redType, String roomId, String failInfo) {
       CusPackPop mCusPackPop = new CusPackPop(redId, this, new LiveFragment(), redStatus, qbPrice, redType, roomId, failInfo);
        mCusPackPop.showAtLocation(getRewardTv, Gravity.CENTER, 0, 0);
        mCusPackPop.setmOnItemClickListener((View v, CusPackPop.CLICKTYPE clicktype) -> {
            switch (clicktype) {
                case OPEN:
//                    ToastUtils.showToast(Utils.getString(R.string.点击开红包));
                    break;
                case UNLOCK:
//                    ToastUtils.showToast(Utils.getString(R.string.点击解锁));
                    RouteUtils.start2Share(InviteAndMakeMoneyActivity.this,shareContent+inviteCode);
                    break;
                case UNLOCK_GAMERULES:
//                    ToastUtils.showToast(Utils.getString(R.string.点击游戏规则));
                    break;
                case UNLOCK_RANK:
//                    ToastUtils.showToast(Utils.getString(R.string.点击排行榜1));
                    break;
                case PACK_RANK:
//                    ToastUtils.showToast(Utils.getString(R.string.点击排行榜2));
                    break;
            }
        });
    }
}
