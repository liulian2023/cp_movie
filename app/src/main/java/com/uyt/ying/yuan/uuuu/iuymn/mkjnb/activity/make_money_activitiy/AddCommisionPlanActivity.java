package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.RebateTableActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DecimalDigitsInputFilter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.xw.repo.BubbleSeekBar;
import java.math.BigDecimal;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MathUtils.FloatsetScale;


public class AddCommisionPlanActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.seek_bar)
    BubbleSeekBar seek_bar;
    @BindView(R.id.k3Rate_edit)
    EditText k3Rate_edit;
    @BindView(R.id.sscaiRate_edit)
    EditText sscaiRate_edit;
    @BindView(R.id.raceRate_edit)
    EditText raceRate_edit;
    @BindView(R.id.sixRate_edit)
    EditText sixRate_edit;
    @BindView(R.id.happy8Rate_edit)
    EditText happy8Rate_edit;
    @BindView(R.id.farmRate_edit)
    EditText farmRate_edit;
    @BindView(R.id.happytenRate_edit)
    EditText happytenRate_edit;
    @BindView(R.id.xuanwuRate_edit)
    EditText xuanwuRate_edit;
    @BindView(R.id.danRate_edit)
    EditText danRate_edit;
    @BindView(R.id.get_invite)
    Button get_invite_btn;
    @BindView(R.id.add_commision_back_iv)
    ImageView add_commision_back_iv;
    @BindView(R.id.show_rebate_list)
    TextView show_rebate_list_tv;
    @BindView(R.id.type_parent_btn)
    RadioButton type_parent_btn;
    private float k3Rate;
    private float happytenRate;
    private float sscaiRate;
    private float happy8Rate;
    private float xuanwuRate;
    private float farmRate;
    private float raceRate;
    private float sixRate;
    private float danRate;
    private float max;
    private float min;
    private String islimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commision_plan);
        ButterKnife.bind(this);
        StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        initView();
        getMineRate();
    }

    private void getMineRate() {
  /*
        获取自身返点信息
         */
        HttpApiUtils.CPnormalRequest(this,null, RequestUtil.REQUEST_13r, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberAgent = jsonObject.getJSONObject("memberAgent");
                k3Rate = memberAgent.getFloatValue("k3Rate");//快三返点
                happytenRate = memberAgent.getFloatValue("happytenRate");//快乐十分返点
                sscaiRate = memberAgent.getFloatValue("sscaiRate");//时时彩返点
                happy8Rate = memberAgent.getFloatValue("happy8Rate");//快乐8返点
                xuanwuRate = memberAgent.getFloatValue("xuanwuRate");//11选5返点
                farmRate = memberAgent.getFloatValue("farmRate");//重庆农场返点
                raceRate = memberAgent.getFloatValue("raceRate");//赛车返点
                sixRate = memberAgent.getFloatValue("sixRate");//六合彩返点
                danRate = memberAgent.getFloatValue("danRate");//pc蛋蛋返点
                max = memberAgent.getFloatValue("max");//最大返点
                min = memberAgent.getFloatValue("min");//最小返点
                islimit = memberAgent.getString("islimit");  //是否限制

                if ("1".equals(islimit)) {  //限制
                    k3Rate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(k3Rate) + Utils.getString(R.string.,可设置返点) + (k3Rate - max > 0 ? initFloat(k3Rate - max) : 0) +
                            "-" +initFloat(k3Rate - min));
                    sscaiRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(sscaiRate) + Utils.getString(R.string.,可设置返点) + (sscaiRate - max > 0 ?initFloat(sscaiRate - max) : 0) +
                            "-" +initFloat(sscaiRate - min));
                    raceRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(raceRate) + Utils.getString(R.string.,可设置返点) + (raceRate - max > 0 ? initFloat(raceRate - max) : 0) +
                            "-" + initFloat(raceRate-min));
                    sixRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(sixRate) + Utils.getString(R.string.,可设置返点) + (sixRate - max > 0 ? initFloat(sixRate-max) : 0) +
                            "-" +initFloat(sixRate - min));
                    happy8Rate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(happy8Rate) + Utils.getString(R.string.,可设置返点) + (happy8Rate - max > 0 ? initFloat(happy8Rate - max) : 0) +
                            "-" + initFloat(happy8Rate - min));
                    farmRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(farmRate) + Utils.getString(R.string.,可设置返点) + (farmRate - max > 0 ? initFloat(farmRate - max ): 0) +
                            "-" + initFloat(farmRate - min));
                    happytenRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(happytenRate) + Utils.getString(R.string.,可设置返点) + (happytenRate - max > 0 ?initFloat(happytenRate - max ) : 0) +
                            "-" + initFloat(happytenRate - min));
                    xuanwuRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(xuanwuRate) + Utils.getString(R.string.,可设置返点) + (xuanwuRate - max > 0 ?initFloat(xuanwuRate - max) : 0) +
                            "-" +initFloat(xuanwuRate - min));
                    danRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(danRate) + Utils.getString(R.string.,可设置返点) + (danRate - max > 0 ? initFloat(danRate - max) : 0) +
                            "-" + initFloat(danRate - min));
                } else {  //不限制
                    k3Rate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(k3Rate) + Utils.getString(R.string.,可设置返点0-) + initFloat(k3Rate));
                    sscaiRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(sscaiRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(sscaiRate));
                    raceRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(raceRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(raceRate));
                    sixRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(sixRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(sixRate));
                    happy8Rate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(happy8Rate) + Utils.getString(R.string.,可设置返点0-) + initFloat(happy8Rate));
                    farmRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(farmRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(farmRate));
                    happytenRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(happytenRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(happytenRate));
                    xuanwuRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(xuanwuRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(xuanwuRate));
                    danRate_edit.setHint(Utils.getString(R.string.自身返点) + initFloat(xuanwuRate) + Utils.getString(R.string.,可设置返点0-) + initFloat(xuanwuRate));
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    private BigDecimal initFloat (float rate){
        return   new BigDecimal(rate+"").setScale(1,BigDecimal.ROUND_HALF_UP);
    };

    private void initView() {

        k3Rate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        sscaiRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        raceRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        sixRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        happy8Rate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        farmRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        happytenRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        xuanwuRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        danRate_edit.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        type_parent_btn.performClick();
        seek_bar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                if ("1".equals(islimit)) {  //限制
                    k3Rate_edit.setText(initFloat(k3Rate - min-progressFloat)+"");
                    sscaiRate_edit.setText(initFloat(sscaiRate - min-progressFloat)+"");
                    raceRate_edit.setText(initFloat(raceRate - min-progressFloat)+"");
                    sixRate_edit.setText(initFloat(sixRate - min-progressFloat)+"");
                    happy8Rate_edit.setText(initFloat(happy8Rate - min-progressFloat)+"");
                    farmRate_edit.setText(initFloat(farmRate - min-progressFloat)+"");
                    happytenRate_edit.setText(initFloat(happytenRate - min-progressFloat)+"");
                    xuanwuRate_edit.setText(initFloat(xuanwuRate - min-progressFloat)+"");
                    danRate_edit.setText(initFloat(danRate - min-progressFloat)+"");
                } else {//不限制
                    k3Rate_edit.setText(initFloat(k3Rate-progressFloat)+"");
                    sscaiRate_edit.setText(initFloat(sscaiRate-progressFloat)+"");
                    raceRate_edit.setText(initFloat(raceRate-progressFloat)+"");
                    sixRate_edit.setText(initFloat(sixRate-progressFloat)+"");
                    happy8Rate_edit.setText(initFloat(happy8Rate-progressFloat)+"");
                    farmRate_edit.setText(initFloat(farmRate-progressFloat)+"");
                    happytenRate_edit.setText(initFloat(happytenRate-progressFloat)+"");
                    xuanwuRate_edit.setText(initFloat(xuanwuRate-progressFloat)+"");
                    danRate_edit.setText(initFloat(danRate-progressFloat)+"");
                }

            }
        });
        get_invite_btn.setOnClickListener(this);
        add_commision_back_iv.setOnClickListener(this);
        show_rebate_list_tv.setOnClickListener(this);
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
            case R.id.get_invite:
                if (EditCheck()){
                    get_invite_btn.setEnabled(false);
                    HashMap<String, Object> dataGetChild = new HashMap<>();
                    dataGetChild.put("k3Rate", k3Rate_edit.getText().toString());
                    dataGetChild.put("sscaiRate", sscaiRate_edit.getText().toString());
                    dataGetChild.put("raceRate", raceRate_edit.getText().toString());
                    dataGetChild.put("sixRate", sixRate_edit.getText().toString());
                    dataGetChild.put("danRate", danRate_edit.getText().toString());
                    dataGetChild.put("happy8Rate", happy8Rate_edit.getText().toString());
                    dataGetChild.put("farmRate", farmRate_edit.getText().toString());
                    dataGetChild.put("happytenRate", happytenRate_edit.getText().toString());
                    dataGetChild.put("xuanwuRate", xuanwuRate_edit.getText().toString());
                    dataGetChild.put("isagent", "1");//代理类型
                    HttpApiUtils.CpRequest(AddCommisionPlanActivity.this,null, RequestUtil.REQUEST_cr11, dataGetChild, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            get_invite_btn.setEnabled(true);
                            showToast(Utils.getString(R.string.创建成功));
                            //返回刷新列表
                            setResult(RESULT_OK);
                            finish();
                        }

                        @Override
                        public void onFailed(String msg) {
                            get_invite_btn.setEnabled(true);
                        }
                    });

                }
                break;
            case R.id.add_commision_back_iv:
                finish();
                break;
            case R.id.show_rebate_list:
                   startActivity(new Intent(AddCommisionPlanActivity.this, RebateTableActivity.class));//跳转返点赔率表界面
                break;
                default:
                    break;

        }
    }

    private boolean EditCheck() {
        if (StringMyUtil.isEmptyString(k3Rate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.快三返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(sscaiRate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.时时彩返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(raceRate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.赛车返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(sixRate_edit.getText().toString())) {

            showToast(Utils.getString(R.string.六合彩返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(happy8Rate_edit.getText().toString())) {

            showToast(Utils.getString(R.string.快乐8返点不能为空));
            return false;
        } else if (StringMyUtil.isEmptyString(farmRate_edit.getText().toString())) {

            showToast(Utils.getString(R.string.重庆农场返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(happytenRate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.快乐十分返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(xuanwuRate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.11选5返点不能为空));
            return false;
        }
        if (StringMyUtil.isEmptyString(danRate_edit.getText().toString())) {
            showToast(Utils.getString(R.string.pc蛋蛋返点不能为空));
            return false;
        }



        if ("1".equals(islimit)) {
            if (Float.parseFloat(k3Rate_edit.getText().toString()) < (FloatsetScale(k3Rate - max,1) > 0 ? FloatsetScale(k3Rate - max ,1): 0) || Float.parseFloat(k3Rate_edit.getText().toString()) >(FloatsetScale(k3Rate - min,1))) {
                showToast(Utils.getString(R.string.快三返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(sscaiRate_edit.getText().toString()) < (FloatsetScale(sscaiRate - max,1) > 0 ? FloatsetScale(sscaiRate - max,1) : 0) || Float.parseFloat(sscaiRate_edit.getText().toString()) > (FloatsetScale(sscaiRate - min,1))) {

                showToast(Utils.getString(R.string.时时彩返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(raceRate_edit.getText().toString()) < (FloatsetScale(raceRate - max,1) > 0 ? FloatsetScale(raceRate - max,1) : 0) || Float.parseFloat(raceRate_edit.getText().toString()) > (FloatsetScale(raceRate - min,1))) {
                showToast(Utils.getString(R.string.赛车返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(sixRate_edit.getText().toString()) < (FloatsetScale(sixRate - max,1) > 0 ? FloatsetScale(sixRate - max,1) : 0) || Float.parseFloat(sixRate_edit.getText().toString()) > FloatsetScale(sixRate - min,1)) {
                showToast(Utils.getString(R.string.六合彩返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(danRate_edit.getText().toString()) < (FloatsetScale(danRate - max,1) > 0 ? FloatsetScale(danRate - max,1) : 0) || Float.parseFloat(danRate_edit.getText().toString()) > FloatsetScale(danRate - min,1)) {
                showToast(Utils.getString(R.string.pc蛋蛋返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(happy8Rate_edit.getText().toString()) < (FloatsetScale(happy8Rate - max,1) > 0 ?FloatsetScale( happy8Rate - max,1) : 0) || Float.parseFloat(happy8Rate_edit.getText().toString()) > FloatsetScale(happy8Rate - min,1)) {
                showToast(Utils.getString(R.string.快乐8返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(farmRate_edit.getText().toString()) < (FloatsetScale(farmRate - max,1) > 0 ? FloatsetScale(farmRate - max ,1): 0) || Float.parseFloat(farmRate_edit.getText().toString()) > FloatsetScale(farmRate - min,1)) {
                showToast(Utils.getString(R.string.幸运农场返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(happytenRate_edit.getText().toString()) < (FloatsetScale(happytenRate - max,1) > 0 ? FloatsetScale(happytenRate - max ,1): 0) || Float.parseFloat(happytenRate_edit.getText().toString()) > FloatsetScale(happytenRate - min,1)) {
                showToast(Utils.getString(R.string.快乐十分返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(xuanwuRate_edit.getText().toString()) < (FloatsetScale(xuanwuRate - max,1) > 0 ? FloatsetScale(xuanwuRate - max,1) : 0) || Float.parseFloat(xuanwuRate_edit.getText().toString()) > FloatsetScale(xuanwuRate - min,1)) {
                showToast(Utils.getString(R.string.11选5返点超出区间,请重新输入));
                return false;
            }
        } else {
            if (Float.parseFloat(k3Rate_edit.getText().toString()) < 0 || Float.parseFloat(k3Rate_edit.getText().toString()) > (k3Rate)) {
                showToast(Utils.getString(R.string.快三返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(sscaiRate_edit.getText().toString()) < (0) || Float.parseFloat(sscaiRate_edit.getText().toString()) > (sscaiRate)) {
                showToast(Utils.getString(R.string.时时彩返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(raceRate_edit.getText().toString()) < (0) || Float.parseFloat(raceRate_edit.getText().toString()) > (raceRate)) {
                showToast(Utils.getString(R.string.赛车返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(sixRate_edit.getText().toString()) < (0) || Float.parseFloat(sixRate_edit.getText().toString()) > (sixRate)) {
                showToast(Utils.getString(R.string.六合彩返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(danRate_edit.getText().toString()) < (0) || Float.parseFloat(danRate_edit.getText().toString()) > (danRate)) {
                showToast(Utils.getString(R.string.pc蛋蛋返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(happy8Rate_edit.getText().toString()) < (0) || Float.parseFloat(happy8Rate_edit.getText().toString()) > (happy8Rate)) {
                showToast(Utils.getString(R.string.快乐8返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(farmRate_edit.getText().toString()) < (0) || Float.parseFloat(farmRate_edit.getText().toString()) > (farmRate)) {
                showToast(Utils.getString(R.string.幸运农场返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(happytenRate_edit.getText().toString()) < (0) || Float.parseFloat(happytenRate_edit.getText().toString()) > (happytenRate)) {
                showToast(Utils.getString(R.string.快乐十分返点超出区间,请重新输入));
                return false;
            } else if (Float.parseFloat(xuanwuRate_edit.getText().toString()) < (0) || Float.parseFloat(xuanwuRate_edit.getText().toString()) > (xuanwuRate)) {
                showToast(Utils.getString(R.string.11选5返点超出区间,请重新输入));
                return false;
            }
        }
        return true;
    }
}
