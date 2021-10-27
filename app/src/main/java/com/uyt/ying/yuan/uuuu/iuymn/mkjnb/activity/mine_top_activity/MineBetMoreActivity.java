package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

/*
投注详情页面
 */
public class MineBetMoreActivity extends BaseActivity implements View.OnClickListener {
    private String perordercode;//请求投注详情需要的参数
    private String ordercode;//注单号
    private String remark;//状态(中奖 未中奖 等待开奖 撤单 )
    private String lotteryNo;//开奖号码
    private TextView back;
    private TextView actionBarText;
    private TextView copy;
    private Button oneMoreTime;
    private Button cancelOrder;
    private TextView lotteryName;
    private TextView qishu;
    private TextView neirong;
    private TextView jine;
    private TextView zhudanhao;
    private TextView time;
    private TextView peilv;
    private TextView kaijianghaoma;
    private TextView zhuangtai;
    private TextView zhongjinagjine;
    private TextView yingkui;
    private ImageView imageView;
    /*
    跳转投注页面需要的参数
     */
    private Integer type_id;
    private String isopenOffice;
    private String isStart;
    private Integer game;
    private String typename;
    private long id;
    private ImageView xian_iv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_bet_more);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        perordercode = getIntent().getStringExtra("perordercode");
        bindView();
        getData();

    }

    @Override
    protected void init() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian (HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }
    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        if(null!=xian_iv){
            if(idList.contains(id+"")){
                xian_iv.setVisibility(View.VISIBLE);
            } else {
                xian_iv.setVisibility(GONE);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getData() {
        HashMap<String, Object> orderDetail = new HashMap<>();
        orderDetail.put("perordercode", perordercode);
        Utils.docking(orderDetail, RequestUtil.REQUEST_13rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(MineBetMoreActivity.this);
                JSONObject jsonObject1 = JSONArray.parseObject(content);
                JSONObject gametouzhu = jsonObject1.getJSONObject("gametouzhu");
                /*
                跳转投注页面需要传的参数
                 */
                typename = gametouzhu.getString("typename");//彩票名
                type_id = gametouzhu.getInteger("type_id");
                String string = gametouzhu.getString("isStart");
                isStart = StringMyUtil.isEmptyString(string)?"1": string;
                isopenOffice = gametouzhu.getString("isopenOffice");
                game = gametouzhu.getInteger("game");
                id = gametouzhu.getLong("id");

                //=-----------------------------------------------------------------------
                lotteryNo = gametouzhu.getString("lotteryNo");//开奖号码
                BigDecimal amount = gametouzhu.getBigDecimal("amount");//投注金额
                BigDecimal bonus = gametouzhu.getBigDecimal("bonus");//中奖金额
                ordercode = gametouzhu.getString("ordercode");//注单号
                remark = gametouzhu.getString("remark");//状态
                String rulename = gametouzhu.getString("rulename");//投注内容 后
                String groupname = gametouzhu.getString("groupname");//投注内容 前
                String createdDate = gametouzhu.getString("createdDate");//投注时间
                BigDecimal odds = gametouzhu.getBigDecimal("odds");//赔率
                String lotteryqishu = gametouzhu.getString("lotteryqishu");//彩票期数
                String image = gametouzhu.getString("image");//彩票图片
                perordercode = gametouzhu.getString("perordercode");//撤销订单需要传的参数
                String firstImageUrl = SharePreferencesUtil.getString(MineBetMoreActivity.this, "FirstImageUrl", "");
                String Img = firstImageUrl + image;//图片路劲
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long l = Long.parseLong(createdDate);
                String format = simpleDateFormat.format(l);
                lotteryName.setText(typename);
                qishu.setText(Utils.getString(R.string.第) + lotteryqishu + Utils.getString(R.string.期));
                neirong.setText("[" + groupname + "]" + rulename);
                jine.setText(amount + Utils.getString(R.string.元));
                zhudanhao.setText(perordercode);
                time.setText(format);
                peilv.setText(odds + "");
                BigDecimal yingKuiJine = bonus.subtract(amount);
                if (remark.equals(Utils.getString(R.string.中奖))) {
                    zhuangtai.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.red));
                    zhuangtai.setText(remark);
                    zhongjinagjine.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.red));
                    zhongjinagjine.setText(bonus + Utils.getString(R.string.元));
                    yingkui.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.red));
                    if(yingKuiJine.compareTo(BigDecimal.ZERO)==-1||yingKuiJine.compareTo(BigDecimal.ZERO)==0){//盈亏金额小于等于0
                    yingkui.setText(  yingKuiJine + Utils.getString(R.string.元));//中奖金额减投注金额
                    }else if(yingKuiJine.compareTo(BigDecimal.ZERO)==1){//盈亏金额大于0
                        yingkui.setText( "+"+ yingKuiJine + Utils.getString(R.string.元));//中奖金额减投注金额
                    }
                    kaijianghaoma.setText(lotteryNo);
                    cancelOrder.setVisibility(View.GONE);
                } else if (remark.equals(Utils.getString(R.string.未中))) {
                    zhuangtai.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhuangtai.setText(remark);
                    zhongjinagjine.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhongjinagjine.setText(bonus + Utils.getString(R.string.元));
                    yingkui.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    yingkui.setText(yingKuiJine + Utils.getString(R.string.元));
                    kaijianghaoma.setText(lotteryNo);
                    cancelOrder.setVisibility(View.GONE);
                } else if(remark.equals(Utils.getString(R.string.撤单))) {
                    zhuangtai.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhuangtai.setText(Utils.getString(R.string.已撤单));
                    zhongjinagjine.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhongjinagjine.setText("- -");
                    yingkui.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    yingkui.setText("- -");
                    kaijianghaoma.setText(Utils.getString(R.string.已撤单));
                    cancelOrder.setVisibility(View.GONE);
                }else if (remark.equals(Utils.getString(R.string.等待开奖))){
                    zhuangtai.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhuangtai.setText(remark);
                    zhongjinagjine.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhongjinagjine.setText("- -");
                    yingkui.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    yingkui.setText("- -");
                    kaijianghaoma.setText(remark);
                    cancelOrder.setVisibility(View.VISIBLE);
                }else {
                    zhuangtai.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhuangtai.setText(remark);
                    zhongjinagjine.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.black));
                    zhongjinagjine.setText(bonus + Utils.getString(R.string.元));
                    yingkui.setTextColor(ContextCompat.getColor(MineBetMoreActivity.this,R.color.red));
                    if(yingKuiJine.compareTo(BigDecimal.ZERO)==-1||yingKuiJine.compareTo(BigDecimal.ZERO)==0){//盈亏金额小于等于0
                        yingkui.setText(  yingKuiJine + Utils.getString(R.string.元));//中奖金额减投注金额
                    }else if(yingKuiJine.compareTo(BigDecimal.ZERO)==1){//盈亏金额大于0
                        yingkui.setText( "+"+ yingKuiJine + Utils.getString(R.string.元));//中奖金额减投注金额
                    }
                    kaijianghaoma.setText(lotteryNo);
                    cancelOrder.setVisibility(View.GONE);
                }
                if(!isFinishing()&&!isDestroyed()){
                    Glide.with(MineBetMoreActivity.this)
                            .load(Img)
                            .into(imageView);
                }
                HbGameClassModel instance = HbGameClassModel.getInstance();
                selectorId(instance);
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(MineBetMoreActivity.this);
            }
        });
    }

    private void bindView() {
        xian_iv = findViewById(R.id.xian_iv);
        back = findViewById(R.id.action_bar_return);
        actionBarText = findViewById(R.id.action_bar_text);
        copy = findViewById(R.id.copy_zhudanhao);
        oneMoreTime = findViewById(R.id.one_more_time);
        cancelOrder = findViewById(R.id.cancel_order);
        lotteryName = findViewById(R.id.lottery_name);
        qishu = findViewById(R.id.lottery_qishu);
        neirong = findViewById(R.id.touzhuneirong);
        jine = findViewById(R.id.touzhujine);
        zhudanhao = findViewById(R.id.zhudanhao);
        time = findViewById(R.id.touzhushijian);
        peilv = findViewById(R.id.peilv);
        kaijianghaoma = findViewById(R.id.kaijianghaoma);
        zhuangtai = findViewById(R.id.zhuangtai);
        zhongjinagjine = findViewById(R.id.zhongjiangjine);
        yingkui = findViewById(R.id.yingkui);
        actionBarText.setText(Utils.getString(R.string.订单详情));
        imageView = findViewById(R.id.lottery_image);
        back.setOnClickListener(this);
        copy.setOnClickListener(this);
        oneMoreTime.setOnClickListener(this);
        cancelOrder.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_bar_return:
                finish();
                break;
                /*
                点击撤销注单
                 */
            case R.id.cancel_order:
                AlertDialog isExit = new AlertDialog.Builder(MineBetMoreActivity.this).create();
                isExit.setTitle(Utils.getString(R.string.撤单提示));
                isExit.setMessage(Utils.getString(R.string.是否撤单));
                isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("ordercode", ordercode);
                        data.put("perordercode", perordercode);
                        Utils.docking(data, RequestUtil.REQUEST_23rzq, 0, new DockingUtil.ILoaderListener() {
                            @Override
                            public void success(String content) {
                                JSONObject jsonObject = JSONObject.parseObject(content);
                                String message = jsonObject.getString("message");
                                showToast(message);
                                kaijianghaoma.setText(Utils.getString(R.string.已撤单));
                                zhuangtai.setText(Utils.getString(R.string.已撤单));
                                cancelOrder.setVisibility(View.GONE);
                                Intent intent = new Intent();
                                intent.putExtra("perordercode",perordercode);
                                setResult(1,intent);
                                finish();
                            }

                            @Override
                            public void failed(MessageHead messageHead) {
                                showToast(messageHead.getInfo());
                            }
                        });
                    }
                });
                isExit.show();

                break;
                     /*
                点击再来一注
                 */
            case R.id.one_more_time:
                if(game == null || type_id == null){
                    showToast(Utils.getString(R.string.数据读取错误, 请重试));
                return;
                }
                ToBetAtyUtils.toBetActivity(MineBetMoreActivity.this,game,typename,type_id,isopenOffice,isStart);
                break;
                 /*
                点击复制注单号
                 */
            case R.id.copy_zhudanhao:
                Utils.copyStr("perordercode",perordercode);
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
