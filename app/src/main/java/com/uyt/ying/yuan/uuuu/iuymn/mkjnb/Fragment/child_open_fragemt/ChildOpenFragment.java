package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.child_open_fragemt;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
//import com.example.administrator.aaa.adapter.agent_journaling_adapter.InviteCodeRecylceAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.ChildOpenActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.RebateTableActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DecimalDigitsInputFilter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.xw.repo.BubbleSeekBar;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MathUtils.FloatsetScale;

public class ChildOpenFragment extends BaseFragment implements View.OnClickListener {
    private RadioButton typeParent;
    private RadioButton typeUser;
    private Button sureButton;
    private EditText kuaisan;
    private EditText ssc;
    private EditText race;
    private EditText six;
    private EditText dandan;
    private EditText happy8;
    private EditText farm;
    private EditText happy10;
    private EditText xuan5;
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
    private TextView showRebateList;
    private String islimit;
    private BubbleSeekBar seek_bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.child_open_fragment, container, false);
        bindView(view);
        initData(view);
        return view;
    }

    private void bindView(View view) {
        seek_bar = view.findViewById(R.id.seek_bar);
        kuaisan = view.findViewById(R.id.k3Rate_edit);
        ssc = view.findViewById(R.id.sscaiRate_edit);
        race = view.findViewById(R.id.raceRate_edit);
        six = view.findViewById(R.id.sixRate_edit);
        happy8 = view.findViewById(R.id.happy8Rate_edit);
        farm = view.findViewById(R.id.farmRate_edit);
        happy10 = view.findViewById(R.id.happytenRate_edit);
        xuan5 = view.findViewById(R.id.xuanwuRate_edit);
        dandan = view.findViewById(R.id.danRate_edit);
        kuaisan.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        ssc.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        race.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        six.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        happy8.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        farm.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        happy10.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        xuan5.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        dandan.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});
        showRebateList = view.findViewById(R.id.show_rebate_list);//?????????????????????
        typeParent = view.findViewById(R.id.type_parent);//?????????????????????
        typeUser = view.findViewById(R.id.type_user);//?????????????????????
        sureButton = view.findViewById(R.id.get_invite);//?????????????????????
        sureButton.setOnClickListener(this);
        typeParent.setOnClickListener(this);
        typeUser.setOnClickListener(this);
        showRebateList.setOnClickListener(this);
        typeParent.performClick();
        seek_bar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                if ("1".equals(islimit)) {  //??????
                    kuaisan.setText(initFloat(k3Rate - min-progressFloat)+"");
                    ssc.setText(initFloat(sscaiRate - min-progressFloat)+"");
                    race.setText(initFloat(raceRate - min-progressFloat)+"");
                    six.setText(initFloat(sixRate - min-progressFloat)+"");
                    happy8.setText(initFloat(happy8Rate - min-progressFloat)+"");
                    farm.setText(initFloat(farmRate - min-progressFloat)+"");
                    happy10.setText(initFloat(happytenRate - min-progressFloat)+"");
                    xuan5.setText(initFloat(xuanwuRate - min-progressFloat)+"");
                    dandan.setText(initFloat(danRate - min-progressFloat)+"");
                } else {  //?????????
                    kuaisan.setText(initFloat(k3Rate-progressFloat)+"");
                    ssc.setText(initFloat(sscaiRate-progressFloat)+"");
                    race.setText(initFloat(raceRate-progressFloat)+"");
                    six.setText(initFloat(sixRate-progressFloat)+"");
                    happy8.setText(initFloat(happy8Rate-progressFloat)+"");
                    farm.setText(initFloat(farmRate-progressFloat)+"");
                    happy10.setText(initFloat(happytenRate-progressFloat)+"");
                    xuan5.setText(initFloat(xuanwuRate-progressFloat)+"");
                    dandan.setText(initFloat(danRate-progressFloat)+"");
                }


            }
        });
    }


    private void initData(View view) {
        /*
        ????????????????????????
         */
        String userInfo = SharePreferencesUtil.getString(getActivity(), "userInfo", "");//mineFragmemt ?????????json??????,?????????????????????
        long userId = SharePreferencesUtil.getLong(getActivity(), "user_id", 0L);
        HashMap<String, Object> aboutPerson = new HashMap<>();
        aboutPerson.put("user_id", userId);
        Utils.docking(aboutPerson, RequestUtil.REQUEST_13r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(getActivity());
                SharePreferencesUtil.putString(getContext(),"userInfo",content);//??????????????????
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberAgent = jsonObject.getJSONObject("memberAgent");
                k3Rate = memberAgent.getFloatValue("k3Rate");//????????????
                happytenRate = memberAgent.getFloatValue("happytenRate");//??????????????????
                sscaiRate = memberAgent.getFloatValue("sscaiRate");//???????????????
                happy8Rate = memberAgent.getFloatValue("happy8Rate");//??????8??????
                xuanwuRate = memberAgent.getFloatValue("xuanwuRate");//11???5??????
                farmRate = memberAgent.getFloatValue("farmRate");//??????????????????
                raceRate = memberAgent.getFloatValue("raceRate");//????????????
                sixRate = memberAgent.getFloatValue("sixRate");//???????????????
                danRate = memberAgent.getFloatValue("danRate");//pc????????????
                max = memberAgent.getFloatValue("max");//????????????
                min = memberAgent.getFloatValue("min");//????????????
                islimit = memberAgent.getString("islimit");  //????????????

                if ("1".equals(islimit)) {  //??????
                    kuaisan.setHint(Utils.getString(R.string.????????????) + initFloat(k3Rate) + Utils.getString(R.string.???????????????) + (k3Rate - max > 0 ? initFloat(k3Rate - max) : 0) +
                            "-" +initFloat(k3Rate - min));
                    ssc.setHint(Utils.getString(R.string.????????????) + initFloat(sscaiRate) + Utils.getString(R.string.???????????????) + (sscaiRate - max > 0 ?initFloat(sscaiRate - max) : 0) +
                            "-" +initFloat(sscaiRate - min));
                    race.setHint(Utils.getString(R.string.????????????) + initFloat(raceRate) + Utils.getString(R.string.???????????????) + (raceRate - max > 0 ? initFloat(raceRate - max) : 0) +
                            "-" + initFloat(raceRate-min));
                    six.setHint(Utils.getString(R.string.????????????) + initFloat(sixRate) + Utils.getString(R.string.???????????????) + (sixRate - max > 0 ? initFloat(sixRate-max) : 0) +
                            "-" +initFloat(sixRate - min));
                    happy8.setHint(Utils.getString(R.string.????????????) + initFloat(happy8Rate) + Utils.getString(R.string.???????????????) + (happy8Rate - max > 0 ? initFloat(happy8Rate - max) : 0) +
                            "-" + initFloat(happy8Rate - min));
                    farm.setHint(Utils.getString(R.string.????????????) + initFloat(farmRate) + Utils.getString(R.string.???????????????) + (farmRate - max > 0 ? initFloat(farmRate - max ): 0) +
                            "-" + initFloat(farmRate - min));
                    happy10.setHint(Utils.getString(R.string.????????????) + initFloat(happytenRate) + Utils.getString(R.string.???????????????) + (happytenRate - max > 0 ?initFloat(happytenRate - max ) : 0) +
                            "-" + initFloat(happytenRate - min));
                    xuan5.setHint(Utils.getString(R.string.????????????) + initFloat(xuanwuRate) + Utils.getString(R.string.???????????????) + (xuanwuRate - max > 0 ?initFloat(xuanwuRate - max) : 0) +
                            "-" +initFloat(xuanwuRate - min));
                    dandan.setHint(Utils.getString(R.string.????????????) + initFloat(danRate) + Utils.getString(R.string.???????????????) + (danRate - max > 0 ? initFloat(danRate - max) : 0) +
                            "-" + initFloat(danRate - min));
                } else {  //?????????
                    kuaisan.setHint(Utils.getString(R.string.????????????) + initFloat(k3Rate) + Utils.getString(R.string.???????????????0) + initFloat(k3Rate));
                    ssc.setHint(Utils.getString(R.string.????????????) + initFloat(sscaiRate) + Utils.getString(R.string.???????????????0) + initFloat(sscaiRate));
                    race.setHint(Utils.getString(R.string.????????????) + initFloat(raceRate) + Utils.getString(R.string.???????????????0) + initFloat(raceRate));
                    six.setHint(Utils.getString(R.string.????????????) + initFloat(sixRate) + Utils.getString(R.string.???????????????0) + initFloat(sixRate));
                    happy8.setHint(Utils.getString(R.string.????????????) + initFloat(happy8Rate) + Utils.getString(R.string.???????????????0) + initFloat(happy8Rate));
                    farm.setHint(Utils.getString(R.string.????????????) + initFloat(farmRate) + Utils.getString(R.string.???????????????0) + initFloat(farmRate));
                    happy10.setHint(Utils.getString(R.string.????????????) + initFloat(happytenRate) + Utils.getString(R.string.???????????????0) + initFloat(happytenRate));
                    xuan5.setHint(Utils.getString(R.string.????????????) + initFloat(xuanwuRate) + Utils.getString(R.string.???????????????0) + initFloat(xuanwuRate));
                    dandan.setHint(Utils.getString(R.string.????????????) + initFloat(danRate) + Utils.getString(R.string.???????????????0) + initFloat(danRate));
                }


                SharePreferencesUtil.putString(getActivity(), "1", k3Rate + "");
                SharePreferencesUtil.putString(getActivity(), "2", sscaiRate + "");
                SharePreferencesUtil.putString(getActivity(), "3", raceRate + "");
                SharePreferencesUtil.putString(getActivity(), "4", sixRate + "");
                SharePreferencesUtil.putString(getActivity(), "5", danRate + "");
                SharePreferencesUtil.putString(getActivity(), "6", happy8Rate + "");
                SharePreferencesUtil.putString(getActivity(), "7", farmRate + "");
                SharePreferencesUtil.putString(getActivity(), "8", happytenRate + "");
                SharePreferencesUtil.putString(getActivity(), "9", xuanwuRate + "");
//                String string = SharePreferencesUtil.getString(getActivity(), "1", "");
//                float v = Float.parseFloat(string);

            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(getActivity());
            }
        });

    }
    private BigDecimal initFloat (float rate){
      return   new BigDecimal(rate+"").setScale(1,BigDecimal.ROUND_HALF_UP);
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_rebate_list:
                startActivity(new Intent(getActivity(), RebateTableActivity.class));//???????????????????????????
//                getActivity().finish();
                break;
            case R.id.get_invite://?????????????????????
               if (EditCheck()){
                   ProgressDialogUtil.show(getActivity(), "loading", false);
                   sureButton.setEnabled(false);
                   Map<String, Object> dataGetChild = new HashMap<>();
                   Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
                   dataGetChild.put("user_id", user_id);
                   dataGetChild.put("k3Rate", kuaisan.getText().toString());
                   dataGetChild.put("sscaiRate", ssc.getText().toString());
                   dataGetChild.put("raceRate", race.getText().toString());
                   dataGetChild.put("sixRate", six.getText().toString());
                   dataGetChild.put("danRate", dandan.getText().toString());
                   dataGetChild.put("happy8Rate", happy8.getText().toString());
                   dataGetChild.put("farmRate", farm.getText().toString());
                   dataGetChild.put("happytenRate", happy10.getText().toString());
                   dataGetChild.put("xuanwuRate", xuan5.getText().toString());
                   if (typeParent.isChecked()) {
                       dataGetChild.put("isagent", "1");//????????????
                   } else {
                       dataGetChild.put("isagent", "0");//????????????
                   }
                   Utils.docking(dataGetChild, RequestUtil.REQUEST_cr11, 0, new DockingUtil.ILoaderListener() {
                       @Override
                       public void success(String content) {
                           sureButton.setEnabled(true);
                           ProgressDialogUtil.stop(getActivity());
                           showToast(Utils.getString(R.string.????????????));
                           Intent intent = new Intent(getActivity(), ChildOpenActivity.class);
                           intent.putExtra("isStartInviteCode", true);
                           startActivity(intent);
                           getActivity().finish();

                       }
                       @Override
                       public void failed(MessageHead messageHead) {
                           sureButton.setEnabled(true);
                           ProgressDialogUtil.stop(getActivity());
                           showToast(messageHead.getInfo());
                       }
                   });
               }
                break;
        }
    }


    private boolean EditCheck() {
        if (StringMyUtil.isEmptyString(kuaisan.getText().toString())) {
            showToast(Utils.getString(R.string.????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(ssc.getText().toString())) {
            showToast(getString(R.string.???????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(race.getText().toString())) {
            showToast(getString(R.string.????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(six.getText().toString())) {

            showToast(getString(R.string.???????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(happy8.getText().toString())) {

            showToast(getString(R.string.??????8??????????????????));
            return false;
        } else if (StringMyUtil.isEmptyString(farm.getText().toString())) {

            showToast(getString(R.string.??????????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(happy10.getText().toString())) {
            showToast(getString(R.string.??????????????????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(xuan5.getText().toString())) {
            showToast(getString(R.string.?????????5??????????????????));
            return false;
        }
        if (StringMyUtil.isEmptyString(dandan.getText().toString())) {
            showToast(getString(R.string.pc????????????????????????));
            return false;
        }

        if ("1".equals(islimit)) {
            if (Float.parseFloat(kuaisan.getText().toString()) < (FloatsetScale(k3Rate - max,1) > 0 ? FloatsetScale(k3Rate - max ,1): 0) || Float.parseFloat(kuaisan.getText().toString()) >(FloatsetScale(k3Rate - min,1))) {
                showToast(getString(R.string.????????????????????????));
                return false;
            } else if (Float.parseFloat(ssc.getText().toString()) < (FloatsetScale(sscaiRate - max,1) > 0 ? FloatsetScale(sscaiRate - max,1) : 0) || Float.parseFloat(ssc.getText().toString()) > (FloatsetScale(sscaiRate - min,1))) {

                showToast(getString(R.string.???????????????????????????));
                return false;
            } else if (Float.parseFloat(race.getText().toString()) < (FloatsetScale(raceRate - max,1) > 0 ? FloatsetScale(raceRate - max,1) : 0) || Float.parseFloat(race.getText().toString()) > (FloatsetScale(raceRate - min,1))) {
                showToast(getString(R.string.????????????????????????));
                return false;
            } else if (Float.parseFloat(six.getText().toString()) < (FloatsetScale(sixRate - max,1) > 0 ? FloatsetScale(sixRate - max,1) : 0) || Float.parseFloat(six.getText().toString()) > FloatsetScale(sixRate - min,1)) {
                showToast(getString(R.string.???????????????????????????));
                return false;
            } else if (Float.parseFloat(dandan.getText().toString()) < (FloatsetScale(danRate - max,1) > 0 ? FloatsetScale(danRate - max,1) : 0) || Float.parseFloat(dandan.getText().toString()) > FloatsetScale(danRate - min,1)) {
                showToast(getString(R.string.pc????????????????????????));
                return false;
            } else if (Float.parseFloat(happy8.getText().toString()) < (FloatsetScale(happy8Rate - max,1) > 0 ?FloatsetScale( happy8Rate - max,1) : 0) || Float.parseFloat(happy8.getText().toString()) > FloatsetScale(happy8Rate - min,1)) {
                showToast(getString(R.string.??????8??????????????????));
                return false;
            } else if (Float.parseFloat(farm.getText().toString()) < (FloatsetScale(farmRate - max,1) > 0 ? FloatsetScale(farmRate - max ,1): 0) || Float.parseFloat(farm.getText().toString()) > FloatsetScale(farmRate - min,1)) {
                showToast(getString(R.string.??????????????????????????????));
                return false;
            } else if (Float.parseFloat(happy10.getText().toString()) < (FloatsetScale(happytenRate - max,1) > 0 ? FloatsetScale(happytenRate - max ,1): 0) || Float.parseFloat(happy10.getText().toString()) > FloatsetScale(happytenRate - min,1)) {
                showToast(getString(R.string.??????????????????????????????));
                return false;
            } else if (Float.parseFloat(xuan5.getText().toString()) < (FloatsetScale(xuanwuRate - max,1) > 0 ? FloatsetScale(xuanwuRate - max,1) : 0) || Float.parseFloat(xuan5.getText().toString()) > FloatsetScale(xuanwuRate - min,1)) {
                showToast(getString(R.string.xuan5chaoqujian));
                return false;
            }
        } else {
            if (Float.parseFloat(kuaisan.getText().toString()) < 0 || Float.parseFloat(kuaisan.getText().toString()) > (k3Rate)) {
                showToast(getString(R.string.????????????????????????));
                return false;
            } else if (Float.parseFloat(ssc.getText().toString()) < (0) || Float.parseFloat(ssc.getText().toString()) > (sscaiRate)) {
                showToast(getString(R.string.???????????????????????????));
                return false;
            } else if (Float.parseFloat(race.getText().toString()) < (0) || Float.parseFloat(race.getText().toString()) > (raceRate)) {
                showToast(getString(R.string.????????????????????????));
                return false;
            } else if (Float.parseFloat(six.getText().toString()) < (0) || Float.parseFloat(six.getText().toString()) > (sixRate)) {
                showToast(getString(R.string.???????????????????????????));
                return false;
            } else if (Float.parseFloat(dandan.getText().toString()) < (0) || Float.parseFloat(dandan.getText().toString()) > (danRate)) {
                showToast(getString(R.string.pc????????????????????????));
                return false;
            } else if (Float.parseFloat(happy8.getText().toString()) < (0) || Float.parseFloat(happy8.getText().toString()) > (happy8Rate)) {
                showToast(getString(R.string.??????8??????????????????));
                return false;
            } else if (Float.parseFloat(farm.getText().toString()) < (0) || Float.parseFloat(farm.getText().toString()) > (farmRate)) {
                showToast(getString(R.string.??????????????????????????????));
                return false;
            } else if (Float.parseFloat(happy10.getText().toString()) < (0) || Float.parseFloat(happy10.getText().toString()) > (happytenRate)) {
                showToast(getString(R.string.??????????????????????????????));;
                return false;
            } else if (Float.parseFloat(xuan5.getText().toString()) < (0) || Float.parseFloat(xuan5.getText().toString()) > (xuanwuRate)) {
                showToast(getString(R.string.xuan5chaoqujian));
                return false;
            }
        }
        return true;
    }
}
