

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;
/*
余额宝主页面
 */
public class YueBaoActivity extends BaseActivity implements View.OnClickListener {
    private TextView returnTv;
    private ConstraintLayout leiJiLayout;
    private ConstraintLayout jiZhunLayout;
    private ConstraintLayout hongYunLayout;
    private Button zhuanRuBtn;
    private Button zhuanChuBtn;
    private long user_id;
    private ImageView rightImg;


    //总金额tv
    private TextView allAmountTv;
    //今日收益tv
    private TextView todayPriceTv;
    //累计收益tv
    private TextView leiJiTv;
    //基准收益率tv
    private TextView jizhunTv;
    //红运收益率tv
    private TextView hongyunTv;

    //基准收益率
    private String jiZhunRate;
    //红运收益率
    private String hongYunRate;
    //基准有效份额
    private String jiZhunValidAmount;
    //红运有效份额
    private String hongYunValidAmount;
    //基准万份收益
    private String jiZhunAmount;
    //红运万份收益
    private String hongYunAmount;
    /*
    右侧pop
     */
    private PopupWindow rightPop;
    private TextView yuebaoMoreTv;
    private TextView yuebaoShowTv;
    private ImageView loadImg;

    private boolean showLoad=true;

    private int status;  // 0关闭 1禁止转入  2开启

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_bao);
        user_id= SharePreferencesUtil.getLong(this,"user_id",0l);
        status=getIntent().getIntExtra("status",0);
        bindView();
        getData();
        initRightPop();
    }
        public static void actionStart(Context context,int status){
            Intent intent = new Intent(context, YueBaoActivity.class);
            intent.putExtra("status",status);
            context.startActivity(intent);
        }
    private void initRightPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.yue_bao_right_pop,null);
        rightPop=new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        rightPop.setAnimationStyle(R.style.popAlphaanim0_3);
        yuebaoMoreTv=view.findViewById(R.id.yuebao_more);
        yuebaoShowTv=view.findViewById(R.id.yuebao_show);
        yuebaoShowTv.setOnClickListener(this);
        yuebaoMoreTv.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    private void getData() {
        if(showLoad){
            loadImg.setVisibility(View.VISIBLE);
        }
        showLoad=false;
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.YUEBAO_INFO, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadImg.setVisibility(View.GONE);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONObject yueBaoVo = jsonObject1.getJSONObject("yueBaoVo");
                if(yueBaoVo!=null){
                    //余额宝余额
                    String amount = yueBaoVo.getString("amount");
                    //今日收益
                    String todayProfit = yueBaoVo.getString("todayProfit");
                    //累计收益
                    String totalProfit = yueBaoVo.getString("totalProfit");
                    //基准有效份额
                    jiZhunValidAmount = yueBaoVo.getString("jiZhunValidAmount");
                    //红运有效份额
                    hongYunValidAmount = yueBaoVo.getString("hongYunValidAmount");
                    allAmountTv.setText(amount);
                    todayPriceTv.setText(todayProfit);
                    leiJiTv.setText(totalProfit);
                }
             ;

                JSONObject settingVo = jsonObject1.getJSONObject("settingVo");

                status = settingVo.getInteger("status");
                SharePreferencesUtil.putInt(YueBaoActivity.this,"yueBaoStatus",status);
                //基准收益年化收益率
                jiZhunRate = settingVo.getString("jiZhunRate");
                if(!jiZhunRate.contains(".")){
                    jiZhunRate+=".0000";
                }
                jizhunTv.setText(jiZhunRate);
                //红运收益年化收益率
                hongYunRate = settingVo.getString("hongYunRate");
                if(!hongYunRate.contains(".")){
                    hongYunRate+=".0000";
                }
                hongyunTv.setText(hongYunRate);
                //基准万份收益
                jiZhunAmount = settingVo.getString("jiZhunAmount");
                //红运万份收益
                hongYunAmount = settingVo.getString("hongYunAmount");
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadImg.setVisibility(View.GONE);
            showToast(messageHead.getInfo());
            }
        });
    }

    private void bindView() {
        loadImg=findViewById(R.id.load_img);
        rightImg=findViewById(R.id.yuebao_right_img);
        rightImg.setOnClickListener(this);
        jizhunTv=findViewById(R.id.jizhun_textview);
        hongyunTv=findViewById(R.id.hongyun_textview);
        returnTv=findViewById(R.id.return_textview);
        returnTv.setOnClickListener(this);
        leiJiTv=findViewById(R.id.leiji_shouyi);
        todayPriceTv =findViewById(R.id.today_price);
        allAmountTv =findViewById(R.id.all_amount);
        leiJiLayout=findViewById(R.id.leiji_layout);
        jiZhunLayout=findViewById(R.id.jizhun_layout);
        hongYunLayout=findViewById(R.id.hongyun_layout);
        zhuanRuBtn=findViewById(R.id.zhuanru_btn);
        zhuanChuBtn=findViewById(R.id.zhuanchu_btn);
        zhuanChuBtn.setOnClickListener(this);
        zhuanRuBtn.setOnClickListener(this);
        hongYunLayout.setOnClickListener(this);
        jiZhunLayout.setOnClickListener(this);
        leiJiLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.leiji_layout:
                Intent intent1 = new Intent(YueBaoActivity.this, YueBaoDetailedActivity.class);
                intent1.putExtra("allAmount",allAmountTv.getText().toString());
                intent1.putExtra("totalAmount",leiJiTv.getText().toString());
                startActivity(intent1);
                break;
            case R.id.jizhun_layout:
                Intent intent = new Intent(YueBaoActivity.this, JiZhunActivty.class);
                //基准有效份额
                intent.putExtra("jiZhunValidAmount",jiZhunValidAmount);
                //基准收益率(年化率)
                intent.putExtra("jiZhunRate",jiZhunRate);
                //基准万份收益
                intent.putExtra("jiZhunAmount",jiZhunAmount);
                startActivity(intent);
                break;
            case R.id.hongyun_layout:
                Intent intentHongYun = new Intent(YueBaoActivity.this, HongYunActivity.class);
                //红运有效份额
                intentHongYun.putExtra("hongYunValidAmount",hongYunValidAmount);
                //红运收益率(年化率)
                intentHongYun.putExtra("hongYunRate",hongYunRate);
                //红运万份收益
                intentHongYun.putExtra("hongYunAmount",hongYunAmount);
                startActivity(intentHongYun);
                break;
            case R.id.zhuanru_btn:
                if(status==1){
                    showToast(Utils.getString(R.string.当前禁止转入));
                }else {
                    //jiZhunAmount 和 hongYunAmount的和为转入activit中 1天的收益
                    Intent intentIn = new Intent(YueBaoActivity.this, InYueBaoActivity.class);
                    intentIn.putExtra("jiZhunAmount",jiZhunAmount);
                    intentIn.putExtra("hongYunAmount",hongYunAmount);
                    startActivity(intentIn);
                }
                break;
            case R.id.zhuanchu_btn:
                if(allAmountTv.getText().toString().equals("0.00")){
                    showToast(Utils.getString(R.string.您当前余额宝账号暂无可转出金额));
                }else {
                    Intent intentOut = new Intent(YueBaoActivity.this, OutYueBaoActivity.class);
//                intentOut.putExtra("userAmount",allAmountTv.getText().toString());
                    startActivity(intentOut);
                }
                break;
            case R.id.return_textview:
                finish();
                break;
                //点击弹出右侧菜单
            case R.id.yuebao_right_img:
                rightPop.showAsDropDown(rightImg, Gravity.BOTTOM,0,0);
                break;
                //余额宝说明
            case R.id.yuebao_show:
                startActivity(new Intent(YueBaoActivity.this,YueBaoExplainActivity.class));
                rightPop.dismiss();
                break;
            //余额宝明细
            case R.id.yuebao_more:
                YueBaoDetailedActivity.activityStart(YueBaoActivity.this,allAmountTv.getText().toString(),leiJiTv.getText().toString());
                rightPop.dismiss();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
