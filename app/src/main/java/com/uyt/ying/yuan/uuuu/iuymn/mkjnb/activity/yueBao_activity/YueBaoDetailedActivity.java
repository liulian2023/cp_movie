
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.yuebao_adapter.YueBaoAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YueBaoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/*
余额宝明细
 */
public class YueBaoDetailedActivity extends BaseActivity implements View.OnClickListener {
    private TextView backTv;
    //点击弹出筛选pop
    private LinearLayout chooseLinear;
    //筛选pop
    private PopupWindow choosePop;
    private RadioButton allTypeRb;
    private RadioButton zhuanRuRb;
    private RadioButton zhuanChuRb;
    private RadioButton shouYiRb;
    private Button chooseSureBtn;
    //存放pop按钮的容器,用于选中状态的初始化操作
    private ArrayList<RadioButton>radioButtonArrayList=new ArrayList<>();
    //筛选pop定位元素
    private LinearLayout wrapLinear;
    //请求余额宝明细的type  1转入;2转出;3收益记录;4提现记录;为空全部
    private int amountType=0;
    private LinearLayout nodataLinear;
    private ConstraintLayout loadingLinear;
    private RefreshLayout smartRefreshLayout;
    private long user_id;
    private int pageNo=1;
    private int pageSize=15;
    private RecyclerView recyclerView;
    private ArrayList<YueBaoMedol>yueBaoMedolArrayList=new ArrayList<>();
    private YueBaoAdapter yueBaoAdapter;
    //全部类型按钮点击的次数,只有第一次点击时(默认点击)才直接获取列表数据,其他情况只做pageNum errorRefresh 和amountType的初始化操作,点击筛选按钮后再请求数据
    private int allBtnClickedNum=1;

    //总金额tv
    private  TextView allAmountTv;
    //总金额(跳转时传递)
    private  String allAmount;
    //累计金额tv
    private TextView totalAmountTV;
    //累计金额(跳转时传递)
    private String totalAmount;

    private LinearLayout errorLinear;
    private TextView reloadTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_ebao_detailed);
        user_id= SharePreferencesUtil.getLong(YueBaoDetailedActivity.this,"user_id",0l);
        allAmount=getIntent().getStringExtra("allAmount");
        totalAmount=getIntent().getStringExtra("totalAmount");
        bindView();
        initRecycle();
        initChoosePop();
        initRefresh();
    }
    public static void  activityStart(Context context,String allAmount, String totalAmount){
        Intent intent = new Intent(context, YueBaoDetailedActivity.class);
        intent.putExtra("allAmount",allAmount);
        intent.putExtra("totalAmount",totalAmount);
        context.startActivity(intent);
    }
        /*
        余额宝明细列表
         */
    private void initRecycle() {
        yueBaoAdapter=new YueBaoAdapter(yueBaoMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(yueBaoAdapter);
    }
    /*
    下拉刷新 上拉加载
     */
    private void initRefresh() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(YueBaoDetailedActivity.this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(YueBaoDetailedActivity.this));
        //数据未满一页,禁止上拉加载
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo=1;
                getYueBaoList(pageNo,pageSize,amountType+"",false,false);
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
             public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            pageNo++;
            getYueBaoList(pageNo,pageSize,amountType+"",true,false);
            }
        });
    }

    @Override
    protected void init() {

    }
        /*
        筛选pop
         */
    private void initChoosePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.yu_e_bao_detailed_choose_popupwindow,null);
        choosePop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        choosePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(YueBaoDetailedActivity.this,1f);
            }
        });
        allTypeRb=view.findViewById(R.id.all_type);
        zhuanRuRb=view.findViewById(R.id.zhuanru_type);
        zhuanChuRb=view.findViewById(R.id.zhuanchu_type);
        shouYiRb=view.findViewById(R.id.shouyi_type);
        chooseSureBtn=view.findViewById(R.id.choose_button);
        allTypeRb.setOnClickListener(this);
        zhuanRuRb.setOnClickListener(this);
        zhuanChuRb.setOnClickListener(this);
        shouYiRb.setOnClickListener(this);
        chooseSureBtn.setOnClickListener(this);
        radioButtonArrayList.add(allTypeRb);
        radioButtonArrayList.add(zhuanRuRb);
        radioButtonArrayList.add(zhuanChuRb);
        radioButtonArrayList.add(shouYiRb);
        allTypeRb.performClick();
    }
    /*
        控件绑定
     */
    private void bindView() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        allAmountTv=findViewById(R.id.all_amount);
        allAmountTv.setText(allAmount);
        totalAmountTV=findViewById(R.id.total_amount);
        totalAmountTV.setText(totalAmount);
        recyclerView=findViewById(R.id.yue_bao_recycle);
        backTv=findViewById(R.id.yuebao_return);
        backTv.setOnClickListener(this);
        smartRefreshLayout=findViewById(R.id.refresh);
        nodataLinear=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        wrapLinear=findViewById(R.id.choose_linear);
        chooseLinear=findViewById(R.id.choose_linear);
        chooseLinear.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yuebao_return:
                finish();
                break;
            case R.id.mine_deal_return:
                finish();
                break;
            case R.id.choose_linear:
            choosePop.showAsDropDown(wrapLinear,0,0);
            ProgressDialogUtil.darkenBackground(YueBaoDetailedActivity.this,0.5f);
              break;
              //全部类型
            case R.id. all_type:
                initRadioStatus(allTypeRb);
                amountType=0;
                //第一次默认点击时,直接请求数据
                if(allBtnClickedNum==1){
                    getYueBaoList(pageNo,pageSize,amountType+"",false,true);
                }
                allBtnClickedNum++;
                break;
                //转出记录
            case R.id.zhuanru_type:
                initRadioStatus(zhuanRuRb);
                amountType=1;
                break;
                //转出记录
            case R.id.zhuanchu_type:
                initRadioStatus(zhuanChuRb);
                amountType=2;
                break;
                //收益记录
            case R.id.shouyi_type:
                initRadioStatus(shouYiRb);
                amountType=3;
                break;
                //筛选按钮
            case R.id.choose_button:
                choosePop.dismiss();
                getYueBaoList(pageNo,pageSize,amountType+"",false,true);
                break;
        }
    }
    /*
    筛选按钮选中状态的处理
     */
    private void initRadioStatus(RadioButton radioButton){
        pageNo=1;
        smartRefreshLayout.resetNoMoreData();
        for (int i = 0; i < radioButtonArrayList.size(); i++) {
            radioButtonArrayList.get(i).setChecked(false);
        }
        radioButton.setChecked(true);
    }

    /**
     *  请求余额宝列表
     * @param pageNo 当前请求的页数
     * @param pageSize 每页请求的数据个数
     * @param amountType 筛选的类型 1转入;2转出;3收益记录;4提现记录;为空全部
     * @param isloadMore 是否是上拉加载(上拉加载不清空数据)
     * @param showLoad 是否显示加载gif(下拉刷新 上拉加载不显示)
     */

    private void getYueBaoList(int pageNo, int pageSize, String amountType, boolean isloadMore, boolean showLoad){
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        //传0时设置为空
        if(Integer.parseInt(amountType)==0){
            amountType="";
        }
        if(showLoad){
            nodataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        HashMap<String, Object> data = new HashMap<>();
        //amountType为空时,不传
        if(StringMyUtil.isNotEmpty(amountType)){
            data.put("amountType",amountType);
        }
        data.put("user_id",user_id);
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        Utils.docking(data, RequestUtil.YUEBAO_MORE, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                if(!isloadMore){
                    yueBaoMedolArrayList.clear();
                }
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                int size = jsonArray.size();
                if(!isloadMore){
                    if(size==0){
                        nodataLinear.setVisibility(View.VISIBLE);
                    }else{
                        nodataLinear.setVisibility(View.GONE);
                    }
                }else {
                    if(size==0){
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        smartRefreshLayout.finishLoadMore();
                    }
                }
                for (int i = 0; i <size ; i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    //remark
                    String amountTypeRemark = jsonObject1.getString("amountTypeRemark");
                    //时间
                    String createdDate = jsonObject1.getString("createdDate");
                    //金额
                    BigDecimal amountIn = jsonObject1.getBigDecimal("amountIn");
                    String amountOut = jsonObject1.getString("amountOut");
                    String amountType = jsonObject1.getString("amountType");
                    String amount="";
                    if(amountIn.compareTo(BigDecimal.ZERO)==1){
                        amount=amountIn+"";
                    }else {
                        amount=amountOut+"";
                    }
                    yueBaoMedolArrayList.add(new YueBaoMedol(amountTypeRemark,createdDate,amount,amountType));
                }
                yueBaoAdapter.notifyDataSetChanged();
          /*     BigDecimal bigDecimalIn = BigDecimal.ZERO;
                BigDecimal bigDecimalOut = BigDecimal.ZERO;
               for (int i = 0; i < yueBaoMedolArrayList.size(); i++) {
                    YueBaoMedol yueBaoMedol = yueBaoMedolArrayList.get(i);
                    //amountType为1 3 为转入或者收益
                    if(yueBaoMedol.getAmountType().equals("1")||yueBaoMedol.getAmountType().equals("3")){
                        bigDecimalIn=bigDecimalIn.add(new BigDecimal(yueBaoMedol.getAmount()));
                    }else {
                        //其他为转出或者提现
                        bigDecimalOut=bigDecimalOut.add(new BigDecimal(yueBaoMedol.getAmount()));
                    }
                }
                //当前数据存入大于或者等于转出.直接显示
                if(bigDecimalIn.compareTo(bigDecimalOut)==1||bigDecimalIn.compareTo(bigDecimalOut)==0){
                    totalAmount=bigDecimalIn.subtract(bigDecimalOut)+"";
                }else {
                    //存入小于转出,加上"-"
                    totalAmount="-"+bigDecimalOut.subtract(bigDecimalIn);
                }

                totalAmountTV.setText(totalAmount);*/

            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
                ErrorUtil.showErrorLayout(YueBaoDetailedActivity.this,recyclerView,errorLinear,reloadTv);
            }
        });

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getYueBaoList(1,pageSize,amountType+"",false,true);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
