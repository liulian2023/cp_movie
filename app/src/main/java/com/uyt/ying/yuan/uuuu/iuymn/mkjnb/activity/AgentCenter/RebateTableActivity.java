package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.RebateAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.RebatePupopwindowMoreAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Odds;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RebateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class RebateTableActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;//返点类型recycleView
    private RebateAdapter rebateAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;//返点详情recycleView
    private ArrayList<Odds> oddsArrayList = new ArrayList<>();
    private RebatePupopwindowMoreAdapter rebatePupopwindowMoreAdapter;
    private ArrayList<RebateModel>rebateModelArrayList =new ArrayList<>();
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();;
    private LinearLayout linearChoose;
    private LinearLayout linearChoose2;
    private LinearLayout bigLinear;
    private TextView name;
    private RadioButton guangfang;
    private RadioButton chuantong;
    private PopupWindow popupWindow;

    private RadioButton k3;
    private RadioButton ssc;
    private RadioButton race;
    private RadioButton six;
    private RadioButton dandan;
    private RadioButton happy8;
    private RadioButton farm;
    private RadioButton happy10;
    private RadioButton xuanwu;
    private TextView explain;
    private TextView rebateReturn;
    private RefreshLayout refreshLayout;
    private boolean popIsShow=false;
    private int i=1;
    private LinearLayout shouqiLinear;
    private int recyPosition;
    private RecyclerView recy;//里层recycle
    private ConstraintLayout loadlingLinear;
    TextView choose_linear_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebate_table);
        initView();
        bindView();
//        initData("1",false);

    }

    @Override
    protected void init() {

    }

    private void bindView() {
        loadlingLinear=findViewById(R.id.loading_linear);
        rebateReturn=findViewById(R.id.rebate_table_return);
        chuantong =findViewById(R.id.chuantong);
        guangfang =findViewById(R.id.guangfang);
        explain =findViewById(R.id.explain);
        linearChoose=findViewById(R.id.choose_linear);
        choose_linear_tv = findViewById(R.id.choose_linear_tv);
        bigLinear=findViewById(R.id.linear);
        LayoutInflater inflater = LayoutInflater.from(this);//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.rebate_table_choose_popupwindow, null);//读取布局管理器
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        k3=inflate.findViewById(R.id.k3);
        ssc=inflate.findViewById(R.id.ssc);
        race=inflate.findViewById(R.id.race);
        six=inflate.findViewById(R.id.six);
        dandan=inflate.findViewById(R.id.dandan);
        happy8=inflate.findViewById(R.id.happy8);
        farm=inflate.findViewById(R.id.farm);
        happy10=inflate.findViewById(R.id.happy10);
        xuanwu=inflate.findViewById(R.id.xuanwu);
        radioButtons.add(k3);
        radioButtons.add(ssc);
        radioButtons.add(race);
        radioButtons.add(six);
        radioButtons.add(dandan);
        radioButtons.add(happy8);
        radioButtons.add(farm);
        radioButtons.add(happy10);
        radioButtons.add(xuanwu);
        k3.setOnClickListener(this);
        ssc.setOnClickListener(this);
        race.setOnClickListener(this);
        six.setOnClickListener(this);
        dandan.setOnClickListener(this);
        happy8.setOnClickListener(this);
        farm.setOnClickListener(this);
        happy10.setOnClickListener(this);
        xuanwu.setOnClickListener(this);
        k3.performClick();
        popupWindow.setTouchable(true);
        linearChoose.setOnClickListener(this);
        chuantong.setOnClickListener(this);
        guangfang.setOnClickListener(this);
        rebateReturn.setOnClickListener(this);
        chuantong.performClick();
        explain.setVisibility(View.VISIBLE);
    }

    /**
     * 请求赔率表数据
     * @param name  彩票类型id
     * @param isChuantong 是否点击了传统类型
     */
    private void initData(final String name, final boolean isChuantong) {
        loadlingLinear.setVisibility(View.VISIBLE);
        showC1hooseTv(name);
        HashMap<String, Object> dataAll = new HashMap<>();
        Utils.docking(dataAll, RequestUtil.REQUEST_03dh, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadlingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(RebateTableActivity.this);
                SharePreferencesUtil.putString(RebateTableActivity.this,"rebateData",content);
                rebateModelArrayList.clear();
                oddsArrayList.clear();
                JSONObject jsonObject = JSONObject.parseObject(content);
                if(isChuantong){
                    JSONArray oldRuleList = jsonObject.getJSONArray("oldRuleList");
                    for (int a =0;a<oldRuleList.size();a++) {
                        JSONObject jsonObject1 = oldRuleList.getJSONObject(a);
                        oddsArrayList.add(new Odds(getString(R.string.返点),getString(R.string.赔率)));//添加列表的第一项
                        String odds = jsonObject1.getString("odds");
                        String game = jsonObject1.getString("game");
                        String TypeName = jsonObject1.getString("name");
//                        if(!name.equals(Utils.getString(R.string.自选不中))){
//                         BigDecimal odds1 = new BigDecimal(odds);
////                            BigDecimal odds1 = new BigDecimal(odds);//赔率的BigDecimal类型(用于精准的计算赔率)
////                            String gameType = rebateModelArrayList.get(position).getGameType();//彩票类型id
//                            String s = SharePreferencesUtil.getString(RebateTableActivity.this, game, "");//下级开户页面传入的key为彩票类型id,值为彩票返点的数据
//                            float v1 = Float.parseFloat(s);//返点的float类型(用于循环递减)
////                BigDecimal rebate = new BigDecimal(string);//返点(BigDecimal类型(用于精准的计算赔率)
//
//                            for (float i = (float) (v1-0.1); i>0; i= (float) (i-0.1)) {//返点每次递减0.1
//                                BigDecimal bigDecimal = new BigDecimal(i).setScale(1,RoundingMode.HALF_UP);//递减得到的返点值
//                                BigDecimal  bigDecimal1=odds1.subtract(new BigDecimal((10-i)/100.0).multiply(odds1).setScale(3,RoundingMode.HALF_UP));//赔率的计算公式
//                                oddsArrayList.add(new Odds(bigDecimal+"",bigDecimal1+""));
//                            }
//                        }
                        if(game.equals(name)){
                            rebateModelArrayList.add(new RebateModel(TypeName,game,odds));
                        }
//                        22,2.62,3.11,3.75,4.55,5.45
                    }
                }else{
                    JSONArray newRuleList = jsonObject.getJSONArray("newRuleList");
                    for (int i =0;i<newRuleList.size();i++) {
                        JSONObject jsonObject1 = newRuleList.getJSONObject(i);
                        oddsArrayList.add(new Odds(getString(R.string.返点),getString(R.string.赔率)));//添加列表的第一项
                        String odds = jsonObject1.getString("odds");
                        String game = jsonObject1.getString("game");
                        String TypeName = jsonObject1.getString("name");
//                        if(!name.equals(Utils.getString(R.string.自选不中))){
//                            BigDecimal odds1 = new BigDecimal(odds);
////                            BigDecimal odds1 = new BigDecimal(odds);//赔率的BigDecimal类型(用于精准的计算赔率)
////                            String gameType = rebateModelArrayList.get(position).getGameType();//彩票类型id
//                            String string = SharePreferencesUtil.getString(RebateTableActivity.this, game, "");//下级开户页面传入的key为彩票类型id,值为彩票返点的数据
//                            float v1 = Float.parseFloat(string);//返点的float类型(用于循环递减)
////                BigDecimal rebate = new BigDecimal(string);//返点(BigDecimal类型(用于精准的计算赔率)
//
//                            for (float a = (float) (v1-0.1); a>0; a= (float) (i-0.1)) {//返点每次递减0.1
//                                BigDecimal bigDecimal = new BigDecimal(a).setScale(1,RoundingMode.HALF_UP);//递减得到的返点值
//                                BigDecimal  bigDecimal1=odds1.subtract(new BigDecimal((10-a)/100.0).multiply(odds1).setScale(3,RoundingMode.HALF_UP));//赔率的计算公式
//                                oddsArrayList.add(new Odds(bigDecimal+"",bigDecimal1+""));
//                            }
//                        }
                        if(game.equals(name)){
                            rebateModelArrayList.add(new RebateModel(TypeName,game,odds));
                        }
                    }
                }

                rebateAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(RebateTableActivity.this);
                loadlingLinear.setVisibility(View.GONE);
                            }
        });
    }

    private void showC1hooseTv(String game){
        switch (game){
            case "1":
                choose_linear_tv.setText(Utils.getString(R.string.快三));
                break;
            case "2":
                choose_linear_tv.setText(Utils.getString(R.string.时时彩));
                break;
            case "3":
                choose_linear_tv.setText(Utils.getString(R.string.赛车));
                break;
            case "4":
                choose_linear_tv.setText(Utils.getString(R.string.六合彩));
                break;
            case "5":
                choose_linear_tv.setText(Utils.getString(R.string.pc蛋蛋));
                break;
            case "6":
                choose_linear_tv.setText(Utils.getString(R.string.快乐8));
                break;
            case "7":
                choose_linear_tv.setText(Utils.getString(R.string.幸运农场));
                break;
            case "8":
                choose_linear_tv.setText(Utils.getString(R.string.快乐10分));
                break;
            case "9":
                choose_linear_tv.setText(Utils.getString(R.string.十一选5));
                break;

        }
    }




    //初始化控件
    public void initView() {
        /*
        彩票玩法RecycleView的相关配置
         */
        mRecyclerView =  findViewById(R.id.rebate_recycle);
        refreshLayout =  findViewById(R.id.ptr_classic_frame_layout);
        shouqiLinear=findViewById(R.id.shouqi_linear);
        shouqiLinear.setOnClickListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));//分割线
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        rebateAdapter = new RebateAdapter(rebateModelArrayList,this,oddsArrayList,shouqiLinear);
        mRecyclerView.setAdapter(rebateAdapter);
        /*
        彩票玩法RecycleView的点击事件
         */
        rebateAdapter.setOnItemClickListener(new RebateAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                oddsArrayList.clear();//清空容器,每次点击都是新的数据
                recy=view.findViewById(R.id.more_table_recycle);
                /*
                赔率详情列表的数据处理相关(里层recycleView数据)
                 */
                recyPosition=position;
                String odds = rebateModelArrayList.get(position).getOdds();//赔率
//                float v = Float.parseFloat(odds);//赔率的float类型(用于循环递减)
                BigDecimal odds1 = new BigDecimal(odds);//赔率的BigDecimal类型(用于精准的计算赔率)
                String gameType = rebateModelArrayList.get(position).getGameType();//彩票类型id
                String cacheRate = SharePreferencesUtil.getString(RebateTableActivity.this, gameType, "");//下级开户页面传入的key为彩票类型id,值为彩票返点的数据
                float v1 = Float.parseFloat(cacheRate);//返点的float类型(用于循环递减)
//                BigDecimal rebate = new BigDecimal(cacheRate);//返点(BigDecimal类型(用于精准的计算赔率)
                oddsArrayList.add(new Odds(getString(R.string.返点),getString(R.string.赔率)));//添加列表的第一项
                double maxPoint;
                for (double i =  v1; i>0; i=  (i-0.1)) {//返点每次递减0.1
                    BigDecimal bigDecimal = new BigDecimal(i).setScale(1,BigDecimal.ROUND_HALF_UP);//递减得到的返点值
                    if (gameType.equals("4")){
                        maxPoint = 7;
                    }else{
                        maxPoint  = 10;
                    }
                    BigDecimal decimal = new BigDecimal(maxPoint+"").subtract(new BigDecimal(bigDecimal+"")).divide(new BigDecimal("100"));
                    BigDecimal a=odds1.subtract(decimal.multiply(odds1)).setScale(3,BigDecimal.ROUND_FLOOR);//赔率的计算公式
                    oddsArrayList.add(new Odds(bigDecimal+"",a+""));//添加里层recycleView数据
            }
            }
        });
        /**
         * 经典 风格的头部实现
         */
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                for (int i =0;i<radioButtons.size();i++){
                    RadioButton radioButton = radioButtons.get(i);
                    if(chuantong.isChecked()){
                        if(radioButton.isChecked()){
                            initData(rebateModelArrayList.get(i).getGameType(),true);
                        }
                    }
                    else {
                        if(radioButton.isChecked()){
                            initData(rebateModelArrayList.get(i).getGameType(),false);
                        }
                    }

                }
                refreshLayout.finishRefresh();
            }
        });



    }
    /**
     * 选择彩票类型后,取消其他radioButton的选中效果
     * @param radioButton //当前选中的radioButton
     */
  private  void initRadioButton(RadioButton radioButton){
      for (int i =0;i<radioButtons.size();i++){
          if(radioButton != radioButtons.get(i)){
              radioButtons.get(i).setChecked(false);
          }
          explain.setVisibility(View.GONE);
      }
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_linear://筛选按钮
                popupWindow.showAsDropDown(bigLinear , Gravity.CENTER, 0, 0); // 相对某个控件的位置
                break;
            case R.id.shouqi_linear:
                recy.setVisibility(View.GONE);
                shouqiLinear.setVisibility(View.GONE);
            case R.id.k3:
                if(guangfang.isChecked()){
                    initRadioButton(k3);
//                    if(i!=1){
                    initData("1",false);
//                    i++;
//                    }
                    popupWindow.dismiss();
                }else{
                    initRadioButton(k3);
                    initData("1",true);
                    popupWindow.dismiss();
                }
                break;
            case R.id.ssc:
                if(guangfang.isChecked()){//判断传统玩法按钮是否有点击,根据传入boolean决定请求的数据
                    initRadioButton(ssc);
                    initData("2",false);
                    popupWindow.dismiss();
                }
                else{
                    initRadioButton(ssc);
                    initData("2",true);
                    popupWindow.dismiss();
                }

                break;
            case R.id.race:
                if(guangfang.isChecked()){
                    initRadioButton(race);
                    initData("3",false);
                    popupWindow.dismiss();
                }
                else{
                    initRadioButton(race);
                    initData("3",true);
                    popupWindow.dismiss();
                }
                break;
            case R.id.six:
                if(guangfang.isChecked()){
                    initRadioButton(six);
                    initData("4",false);//官方玩法只有 1 2 3 9 有返点数据,为了避免界面为空的情况,此处请求传统玩法的数据(后期有数据的话,再传入true请求官方玩法)
                    popupWindow.dismiss();
                }
                else {
                    initData("4",true);
//                    initData("4",true);
                    initRadioButton(six);
                    popupWindow.dismiss();
                }

                break;
            case R.id.dandan:
                if(guangfang.isChecked()){
                    initRadioButton(dandan);
                    initData("5",false);
                    popupWindow.dismiss();
                }
                else{
                    initData("5",true);
                    initRadioButton(dandan);
//                    initData("5",true);
                    popupWindow.dismiss();
                }

                break;
            case R.id.happy8:
                if(guangfang.isChecked()){
                    initRadioButton(happy8);
                    initData("6",false);
                    popupWindow.dismiss();
                }
                else{
//                    initData("6",true);
                    initRadioButton(happy8);
                    initData("6",true);
                    popupWindow.dismiss();
                }

                break;
            case R.id.farm:
                if(guangfang.isChecked()){
                    initRadioButton(farm);
                    initData("7",false);
                    popupWindow.dismiss();
                }
                else{
//                    initData("7",true);
                    initRadioButton(farm);
                    initData("7",true);
                    popupWindow.dismiss();
                }

                break;
            case R.id.happy10:
                if(guangfang.isChecked()){
                    initRadioButton(happy10);
                    initData("8",false);
                    popupWindow.dismiss();
                }
                else{
                    initRadioButton(happy10);
                    initData("8",true);
//                    initData("8",true);
                    popupWindow.dismiss();
                }
                break;
            case R.id.xuanwu:
                if(guangfang.isChecked()){
                    initRadioButton(xuanwu);
                    initData("9",false);
                    popupWindow.dismiss();
                }
                else{
                    initRadioButton(xuanwu);
                    initData("9",true);
                    popupWindow.dismiss();
                }
                break;
            case R.id.rebate_table_return://返回键
                onBackPressed();
                break;
            case R.id.chuantong://点击传统玩法
                if(ssc.isChecked()){
                    initData("2",true);
                }
                if(race.isChecked()){
                    initData("3",true);
                }
                if(six.isChecked()){
                    initData("4",true);
                }
                 if(dandan.isChecked()){
                    initData("5",true);
                }
                if(happy8.isChecked()){
                    initData("6",true);
                }
                if(farm.isChecked()){
                    initData("7",true);
                }
                if(happy10.isChecked()){
                    initData("8",true);
                }
                if(xuanwu.isChecked()){
                    initData("9",true);
                }
                if(k3.isChecked()){
                    initData("1",true);
                }
                break;
            case R.id.guangfang://点击官方玩法
                if(ssc.isChecked()){
                    initData("2",false);
                }
                if(race.isChecked()){
                    initData("3",false);
                }
                if(six.isChecked()){
                    initData("4",false);//官方玩法只有 1 2 3 9 有返点数据,为了避免界面为空的情况,此处请求传统玩法的数据(后期有数据的话,再传入false请求官方玩法)
//                    initData("4",true);
                }
                if(dandan.isChecked()){
                    initData("5",false);
//                    initData("5",true);
                }
                if(happy8.isChecked()){
                    initData("6",false);
//                    initData("6",true);
                }
                if(farm.isChecked()){
                    initData("7",false);
//                    initData("7",true);
                }
                if(happy10.isChecked()){
                    initData("8",false);
//                    initData("8",true);
                }
                if(xuanwu.isChecked()){
                    initData("9",false);
                }
                if(k3.isChecked()){
                    initData("1",false);
                }
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
