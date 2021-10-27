package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/*
转入余额宝
*/
public class InYueBaoActivity extends BaseActivity implements View.OnClickListener {
    private TextView spanTv;
    private TextView actionBarTv;
    //全部textView(点击填充editText)
    private TextView allAmountTv;
    private long user_id;
    //用户余额
    private String userAmount;
    private EditText amountEt;
    private TextView actionBack;
    private Button zhuanRuVtn;
    private String jiZhunAmount;
    private String hongYunAmount;
    private String addString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_balance_bao);
        jiZhunAmount=getIntent().getStringExtra("jiZhunAmount");
        hongYunAmount=getIntent().getStringExtra("hongYunAmount");
        if(StringMyUtil.isEmptyString(jiZhunAmount)){
            jiZhunAmount="0";
        }
        if(StringMyUtil.isEmptyString(hongYunAmount)){
            hongYunAmount="0";
        }
        user_id= SharePreferencesUtil.getLong(InYueBaoActivity.this,"user_id",0l);
        bindView();
        initSpanString();
        getMoney();

    }

    private void getMoney() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                /*
                用户资金
                 */
                userAmount = memberMoney.getString("amount");
                amountEt.setText("");
                amountEt.setHint(Utils.getString(R.string.可转入余额)+userAmount);

            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    private void bindView() {
        zhuanRuVtn=findViewById(R.id.zhuanru_btn);
        zhuanRuVtn.setOnClickListener(this);
        actionBack=findViewById(R.id.action_bar_return);
        actionBack.setOnClickListener(this);
        amountEt=findViewById(R.id.amount_edit);
        allAmountTv=findViewById(R.id.all_amount);
        allAmountTv.setOnClickListener(this);
        spanTv=findViewById(R.id.span_textview);
        BigDecimal jizhun = new BigDecimal(jiZhunAmount);
        BigDecimal bigDecimal = new BigDecimal(hongYunAmount);
        BigDecimal add = jizhun.add(bigDecimal);
        addString=add+"";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Utils.getString(R.string.MM月dd日));
        Date tomorrowDate = DateUtil.getTomorrowDate();
        String format = simpleDateFormat.format(tomorrowDate);
        spanTv.setText(Utils.getString(R.string.现在转入,预计)+format+Utils.getString(R.string.收益到账,1万元1天可享收益,¥)+ add +Utils.getString(R.string. 收益规则));
        actionBarTv=findViewById(R.id.action_bar_text);
        actionBarTv.setText(Utils.getString(R.string.转入余额宝));
    }

    @Override
    protected void init() {

    }
    /*
    转入按钮下的混排textView相关
     */
    private void initSpanString(){
        String content=spanTv.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(content);
        int length = content.length();
        //---------- 设置 金额 文字的样式开始------------------
        int index = content.indexOf(Utils.getString(R.string.可享收益));
        //设置部分字体的颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#e53333"));
        //start: Utils.getString(R.string.可享收益) .length+加逗号 ==需要设置红色的部分开始  end:   Utils.getString(R.string.可享收益) .length+加逗号 + ¥ +addString.length == 需要设置红色的部分结束
        spannableStringBuilder.setSpan(foregroundColorSpan,index+5,index+6+addString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置部分字体的背景颜色(点击后,默认背景颜色为红色,此处覆盖为白色)
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#F6F6F6"));
        spannableStringBuilder.setSpan(backgroundColorSpan,index+5,index+addString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //---------- 设置 金额 文字的样式结束------------------
        //设置部分字体的点击事件(收益规则点击事件)
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(InYueBaoActivity.this,YueBaoExplainActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        //点击事件span中的endIndex设为length-1,否则点击Utils.getString(R.string.收益规则)所在行的空白区域也会触发点击事件
        spannableStringBuilder.setSpan(clickableSpan2,length-4,length-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#007aff"));
        spannableStringBuilder.setSpan(foregroundColorSpan2,length-4,length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        BackgroundColorSpan backgroundColorSpan2 = new BackgroundColorSpan(Color.parseColor("#F6F6F6"));
        spannableStringBuilder.setSpan(backgroundColorSpan2,length-4,length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//        Drawable drawable=getResources().getDrawable(R.drawable.xiaoqizi1);
//        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        spannableStringBuilder.setSpan(imageSpan,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //将上面的span设置给textView
        spanTv.setMovementMethod(LinkMovementMethod.getInstance());
        spanTv.setText(spannableStringBuilder);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all_amount:
                    if(StringMyUtil.isNotEmpty(userAmount)){
                        amountEt.setText(userAmount);
                        amountEt.setSelection(amountEt.getText().length());
                    }
                break;
            case R.id.action_bar_return:
                finish();
                break;
            //点击 转入按钮
            case R.id.zhuanru_btn:
                zhuanRu();
                break;
        }
    }
    /*
    余额转入余额宝
     */
    private void zhuanRu(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        data.put("amount",amountEt.getText().toString());
        Utils.docking(data, RequestUtil.IN_YUEBAO, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
//                getMoney();
                finish();

            }

            @Override
            public void failed(MessageHead messageHead) {
                showToast(messageHead.getInfo());
            }
        });
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
