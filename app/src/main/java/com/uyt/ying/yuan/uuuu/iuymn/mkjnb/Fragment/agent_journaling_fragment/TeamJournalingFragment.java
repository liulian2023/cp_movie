package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.agent_journaling_fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Headers;

public class TeamJournalingFragment extends BaseFragment implements View.OnClickListener {
    private TextView searchText;//搜索按钮
    private EditText searchEdit;//搜索框
    private TextView childNum;//下级人数
    private TextView touzhu;//投注金额
    private TextView winNum;//中奖金额
    private TextView mineCharges;//个人返佣
    private TextView activityRebate;//活动返点
    private TextView teamWinOrLose;//团队盈亏
    private TextView teamCharges;//团队返佣
    private TextView invasMoney;//充值金额
    private TextView withdrawMoney;//提现金额
    private TextView teamMoney;//团队余额
    private TextView firstChongZhi;//首充人数
    private TextView regiestNum;//注册人数
    private TextView playNum;//投注人数
    private TextView versionText;//名词解释按钮
    private PopupWindow popupWindow;//名词解释弹出窗
    private PopupWindow popupWindow1;//筛选弹出窗(团队报表)
    private LinearLayout linearLayout;//(用于名词解释popupwindow定位)
    private LinearLayout chooseLinear;//筛选按钮
    private LinearLayout actionBar;//用于筛选popupwindow定位
    private RadioButton today;//今日筛选按钮
    private RadioButton yestoday;//昨日筛选按钮
    private RadioButton thisMonth;//本月筛选按钮
    private RadioButton lastMonth;//上月筛选按钮
    private View inflate1;//筛选popupwindow的视图(团队报表)
    private View inflate;//名词解释popuwindow的视图
    private ImageView backImg;//名词解释的返回图片
    private String  editText;//输入框内容
//    private ArrayList<String> childList=new ArrayList<>();//下级昵称容器
    private TextView whoJournaling;
    private String appName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.team_journaling_fragment,container,false);
        appName=SharePreferencesUtil.getString(getContext(),"appName","");
        initView();
        bindView(view);

//        getChildNickName();
        return view;
    }
    /*
    得到所有下级代理的昵称(用于搜索时判断是否有该下级)(不再请求下级列表,如果输入的下级不存在,直接toast后台的判断结果即可)
     */
//    private void getChildNickName() {
//        HashMap<String, Object> dataChild = new HashMap<>();
//        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
//        dataChild.put("user_id",user_id);
//        dataChild.put("pageNo",1);
//        dataChild.put("pageSize",100);
//        Utils.docking(dataChild, RequestUtil.REQUEST_15rzq, 0, new DockingUtil.ILoaderListener() {
//            @Override
//            public void success(String content) {
//                childList.clear();
//
//                JSONObject jsonObject = JSONObject.parseObject(content);
//                JSONArray childManagementList = jsonObject.getJSONArray("childManagementList");
//                for (int i=0;i<childManagementList.size();i++) {
//                    JSONObject jsonObject1 = childManagementList.getJSONObject(i);
//                    String nickname = jsonObject1.getString("nickname");
//                    childList.add(nickname);
//                }
//            }
//            @Override
//            public void failed(MessageHead messageHead) {
//
//            }
//        });
//    }

    public void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());//布局管理器
        /*
        名词解释弹出窗相关配置
         */
        inflate = inflater.inflate(R.layout.noun_version_pupopwindow, null);//名词解释弹出窗视图
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化名词解释popupWindow
        popupWindow.setAnimationStyle(R.style.popupAnimationNormol);//设置进出动画
        popupWindow.setTouchable(true);//响应内部点击

        /*
        筛选弹出窗相关配置(团队报表)
         */
        inflate1 = inflater.inflate(R.layout.team_journaling_choose_popupwindow, null);//筛选弹出窗视图
        popupWindow1 = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindow1.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
            }
        });
        popupWindow1.setTouchable(true);//响应内部点击

    }


    private void bindView(View view) {
        chooseLinear =  getActivity().findViewById(R.id.choose_linear);//筛选按钮
        actionBar=getActivity().findViewById(R.id.linear);//筛选弹出框的定位元素
        backImg =inflate.findViewById(R.id.back);//名词解释弹出窗返回图片
        today=inflate1.findViewById(R.id.today);//今日赛选按钮
        yestoday=inflate1.findViewById(R.id.yestoday);//昨日赛选按钮
        thisMonth=inflate1.findViewById(R.id.this_month);//本月筛选按钮
        lastMonth=inflate1.findViewById(R.id.last_month);//上月筛选按钮
        if(appName.equals(Utils.getString(R.string.盈众彩票))||appName.equals(Utils.getString(R.string.申博国际))){
            thisMonth.setText(Utils.getString(R.string.七天));
            lastMonth.setVisibility(View.GONE);
        }else {
            thisMonth.setText(Utils.getString(R.string.本月));
            lastMonth.setVisibility(View.VISIBLE);
        }
        searchEdit= view.findViewById(R.id.search_edit);//下级代理账号输入框
        searchText =view.findViewById(R.id.search_text);
        childNum= view.findViewById(R.id.child_num);
        touzhu= view.findViewById(R.id.touzhujine);
        winNum= view.findViewById(R.id.win_num);
        mineCharges= view.findViewById(R.id.mine_charges);
        activityRebate= view.findViewById(R.id.activity_retabe);
        teamWinOrLose= view.findViewById(R.id.team_winorlose);
        teamCharges= view.findViewById(R.id.team_charges);
        invasMoney= view.findViewById(R.id.invase_money);
        teamMoney= view.findViewById(R.id.team_money);
        withdrawMoney= view.findViewById(R.id.withdraw_money);
        firstChongZhi= view.findViewById(R.id.first_chongzhi);
        regiestNum= view.findViewById(R.id.regiest_num);
        playNum= view.findViewById(R.id.paly_num);
        versionText= view.findViewById(R.id.version);
        linearLayout =view.findViewById(R.id.pupopwindowParent);//名词解释弹出窗定位元素
        whoJournaling=view.findViewById(R.id.who_journaling);
        today.setOnClickListener(this);
        today.performClick();
        yestoday.setOnClickListener(this);
        thisMonth.setOnClickListener(this);
        lastMonth.setOnClickListener(this);
        childNum.setOnClickListener(this);
        versionText.setOnClickListener(this);
        chooseLinear.setOnClickListener(this);
        searchText.setOnClickListener(this);
    }
    public void initData(Date startDate, Date endDate, final String nikeName) {
        ProgressDialogUtil.show(getContext());
        /*
        请求团队报表
         */
        HashMap<String, Object> dataTeam = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatStart = simpleDateFormat.format(startDate);
        String formatEnd = simpleDateFormat.format(endDate);
        dataTeam.put("user_id",user_id);
        dataTeam.put("startDate",formatStart);
        dataTeam.put("endDate",formatEnd);
        dataTeam.put("nickname",nikeName);
        HttpApiUtils.CpRequest(getActivity(), this, RequestUtil.REQUEST_14rzq, dataTeam, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                BigDecimal childBettingTotalPrice = jsonObject.getBigDecimal("childBettingTotalPrice");//投注金额
                touzhu.setText(childBettingTotalPrice+"");
                BigDecimal childLotteryTotalPrice = jsonObject.getBigDecimal("childLotteryTotalPrice");//中奖金额
                winNum.setText(childLotteryTotalPrice+"");
//                BigDecimal childActivityGift = jsonObject.getBigDecimal("childActivityGift");//活动礼金
                BigDecimal childMyselfTotalPrice = jsonObject.getBigDecimal("childMyselfTotalPrice");//活动返点
                activityRebate.setText(childMyselfTotalPrice+"");
                BigDecimal teamCommission = jsonObject.getBigDecimal("teamCommission");//团队返佣
                teamCharges.setText(teamCommission+"");
                BigDecimal childProfitTotalPrice = jsonObject.getBigDecimal("childProfitTotalPrice");//团队盈利
                teamWinOrLose.setText(childProfitTotalPrice+"");
                BigDecimal childAllRegisterPeopleNum = jsonObject.getBigDecimal("childAllRegisterPeopleNum");//下级人数
                childNum.setText(Utils.getString(R.string.下级人数共)+childAllRegisterPeopleNum+Utils.getString(R.string.人));
                BigDecimal childRechargeTotalPrice = jsonObject.getBigDecimal("childRechargeTotalPrice");//充值金额
                invasMoney.setText(childRechargeTotalPrice+"");
                BigDecimal childDrawTotalPrice = jsonObject.getBigDecimal("childDrawTotalPrice");//提现金额
                withdrawMoney.setText(childDrawTotalPrice+"");
                BigDecimal childFirstPeopleNum = jsonObject.getBigDecimal("childFirstPeopleNum");//首充人数
                firstChongZhi.setText(childFirstPeopleNum+"");
                BigDecimal childBettingPeopleNum = jsonObject.getBigDecimal("childBettingPeopleNum");//投注人数
                playNum.setText(childBettingPeopleNum+"");
                BigDecimal childRegisterPeopleNum = jsonObject.getBigDecimal("childRegisterPeopleNum");//注册人数
                regiestNum .setText(childRegisterPeopleNum+"");
                BigDecimal childAmountTotalPrice = jsonObject.getBigDecimal("childAmountTotalPrice");//团队余额
                teamMoney.setText(childAmountTotalPrice+"");
                BigDecimal childParentTotalPrice = jsonObject.getBigDecimal("childParentTotalPrice");//代理个人返佣
                mineCharges.setText(childParentTotalPrice+"");
                if(!StringMyUtil.isEmptyString(nikeName)){//输入框不为空,即显示输入nickName的团队报表
                    whoJournaling.setText(nikeName+Utils.getString(R.string.的团队报表));
                }else{//输入框为空,默认显示我的团队报表
                    whoJournaling.setText(Utils.getString(R.string.我的团队报表));
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Date date = new Date();
        switch (v.getId()) {
            case R.id.version://名词解释按钮
                popupWindow.showAtLocation(linearLayout , Gravity.BOTTOM, 0, -200); // 相对整个窗口定位,下方,y轴往上偏移200 第一个参数用来获得window标识,任意控件都可以,对位置无影响
                backImg.setOnClickListener(new View.OnClickListener() {//点击弹回
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.choose_linear://筛选按钮
                popupWindow1.showAsDropDown(actionBar , 0, 0); // 相对固定元素,无偏移
                ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                break;
            case R.id.today://今日报表
                getTodayData(date);
                popupWindow1.dismiss();
                break;
            case R.id.yestoday://昨日报表
                getYesTodayData();
                popupWindow1.dismiss();
                break;
            case R.id.this_month://本月报表
                getThisMonthData();
                popupWindow1.dismiss();
                break;
            case R.id.last_month://上月报表
                getLastMonthData();
                popupWindow1.dismiss();
                break;
            case R.id.search_text://搜索按钮
                if(today.isChecked()){
//                    if(childList.contains(initEdit())){
                        ProgressDialogUtil.show(getContext());
                        getTodayData(new Date());
//                    }else{
//                        showtoast(Utils.getString(R.string.未搜索到该下级账号,请重新输入));
//                    }
                }
                else if(yestoday.isChecked()){
//                    if(childList.contains(initEdit())){
                        ProgressDialogUtil.show(getContext());
                        getYesTodayData();
//                    }
//                    else{
//                        showtoast(Utils.getString(R.string.未搜索到该下级账号,请重新输入));
//                    }
                }

                else if(thisMonth.isChecked()){
//                    if(childList.contains(initEdit())){
                        ProgressDialogUtil.show(getContext());
                        getThisMonthData();
//                    }
//                    else{
//                        showtoast(Utils.getString(R.string.未搜索到该下级账号,请重新输入));
//                    }

                }
                else{
//                    if(childList.contains(initEdit())){
                        ProgressDialogUtil.show(getContext());
                        getLastMonthData();
//                    }
//                    else{
//                        showtoast(Utils.getString(R.string.未搜索到该下级账号,请重新输入));
//                    }
                }
                break;
        }


    }

    private void getLastMonthData() {
        Date thisMonth = DateUtil.getMonthBegin(new Date());//当前月份的开始时间
        Date yesterdayDate = DateUtil.getYesterdayDate(thisMonth);//当前月份开始时间减一天,得到上个月的最后一天
        Date lastMonthBegin = DateUtil.getMonthBegin(yesterdayDate);//将上个月最后一天设置为上个月第一天
        Date lastMonthEnd= DateUtil.getMonthEnd(yesterdayDate);//将上个月最后一天设置为上个月第一天
        initData(lastMonthBegin,lastMonthEnd,initEdit());
    }

    private void getThisMonthData() {
        Date dateBefore;
        if(appName.equals(Utils.getString(R.string.盈众彩票))||appName.equals(Utils.getString(R.string.申博国际))){
        dateBefore = DateUtil.getDateBefore(new Date(), 6);
        }else {
        dateBefore = DateUtil.getMonthBegin(new Date());//得到当前月份的开始时间
        }
//        Date monthEnd = DateUtil.getMonthEnd(new Date());//得到当前月份的结束时间
        initData(dateBefore,new Date(),initEdit());
    }

    private void getYesTodayData() {
        Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//得到昨天的当前时间
        Date startTime = DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间
        Date endTime = DateUtil.getEndTime(yesterdayCalendar);//得到昨天到的结束时间
        initData(startTime,endTime,initEdit());
    }

    private void getTodayData(Date date) {
        initData(date,date, initEdit());
    }

    private String initEdit(){
        editText = searchEdit.getText().toString();
        if (editText.isEmpty()) {
            editText = "";
        }
        return  editText;
    }
}
