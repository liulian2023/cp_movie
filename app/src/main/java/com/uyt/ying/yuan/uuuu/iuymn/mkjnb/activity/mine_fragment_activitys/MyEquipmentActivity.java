package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveGiftSVGAEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveShoppingActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MyEquipmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.EquipmentAllEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.EquipmentGetEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.DiamondPayPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.opensource.svgaplayer.SVGAImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import pl.droidsonroids.gif.GifImageView;

public class MyEquipmentActivity extends BaseActivity {
    @BindView(R.id.mount_tv)
    TextView mount_tv;
    @BindView(R.id.medal_tv)
    TextView medal_tv;
    @BindView(R.id.title_brand_tv)
    TextView title_brand_tv;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.svga_loading_iv)
    GifImageView svga_loading_iv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.equpment_svga)
    SVGAImageView equpment_svga;
    @BindView(R.id.diamond_num_tv)
    TextView diamond_num_tv;
    @BindView(R.id.equipment_recycler)
    RecyclerView equipment_recycler;
    ArrayList<TextView>titleTvList = new ArrayList<>();

    MyEquipmentAdapter myEquipmentAdapter;
    ArrayList<Object> entityList = new ArrayList<>();
    String medalInfoType;
    String mountJsonStr;
    String medalJsonStr;
    String titleBrandJsonStr;
    EqupmentSvgaMage equpmentSvgaMage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_equipment);
        StatusBarUtil.setLightMode(this,true);
        StatusBarUtil.setColor(this, Color.WHITE);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.商城中心));
        titleTvList.add(mount_tv);
        titleTvList.add(medal_tv);
        titleTvList.add(title_brand_tv);
        initRecycler();
        mount_tv.setSelected(true);
        mount_tv.performClick();
        initRefresh();
        requestUserInfo();
        equpmentSvgaMage = new EqupmentSvgaMage(this,equpment_svga,svga_loading_iv);

    }

    private void initRefresh() {
        RefreshUtil.initRefresh(new SoftReference<>(MyEquipmentActivity.this), refresh, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestList(true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    private void initRecycler() {
        myEquipmentAdapter = new MyEquipmentAdapter(entityList);
        equipment_recycler.setLayoutManager(new LinearLayoutManager(this));
        equipment_recycler.setAdapter(myEquipmentAdapter);
        myEquipmentAdapter.addChildClickViewIds(R.id.mount_get_btn,R.id.mount_iv,R.id.title_get_btn);
        myEquipmentAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = entityList.get(position);
                switch (view.getId()){
                    case R.id.mount_get_btn:
                    case R.id.title_get_btn:
                        itemButtonClick(position, o);
                        break;
                    case R.id.mount_iv:
                        if(o instanceof EquipmentAllEntity.MemberMedalListBean){
                            EquipmentAllEntity.MemberMedalListBean memberMedalListBean = (EquipmentAllEntity.MemberMedalListBean) o;
                            String txImage = memberMedalListBean.getMedalInfo().getTxImage();
                            if(equpment_svga.isAnimating()){
                                equpment_svga.clearAnimation();
                                equpment_svga.stopAnimation(true);
                            }
                            equpment_svga.setVisibility(View.VISIBLE);
                            ProgressDialogUtil.darkenBackground(MyEquipmentActivity.this,0.9f);
                            LiveMessageModel liveMessageModel = new LiveMessageModel();
                            liveMessageModel.setUserName(SharePreferencesUtil.getString(MyApplication.getInstance(),"userNickName",""));
                            liveMessageModel.setLevel(SharePreferencesUtil.getInt(MyApplication.getInstance(), CommonStr.GRADE,1)+"");
                            liveMessageModel.setPortrait(Utils.checkImageUrl(Utils.getUserInfoEntity().getMemberInfo().getImage()));
                            liveMessageModel.setMountName(memberMedalListBean.getMedalInfo().getName());
                            LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.checkImageUrl(txImage), liveMessageModel);
                            equpmentSvgaMage.startAnimator(liveGiftSVGAEntity);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void requestUserInfo() {
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_06rzq, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                String totalZhuanShi = memberMoney.getString("totalZhuanShi");
                if(StringMyUtil.isNotEmpty(totalZhuanShi)){
                    diamond_num_tv.setText(totalZhuanShi);
                }
            }
            @Override
            public void onFailed(String msg) {

            }
        });
    }
    /**
     * recyclerView item右侧的button点击事件
     * @param position
     * @param o
     */
    private void itemButtonClick(int position, Object o) {
        if(o instanceof EquipmentAllEntity.MemberMedalListBean){
            EquipmentAllEntity.MemberMedalListBean memberMedalListBean = (EquipmentAllEntity.MemberMedalListBean) o;
            EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo = memberMedalListBean.getMedalInfo();
            String conditionsType = medalInfo.getConditionsType();//0砖石购买；1邀请条件；2充值条件；3打码条件
            if(StringMyUtil.isEmptyString(conditionsType)){
                return;
            }
            int customType = medalInfo.getCustomType();  //customType  1未获取 2已使用 3 未使用
            if(customType==1){
                if(conditionsType.equals("0")){
                    DiamondPayPop diamondPayPop = new DiamondPayPop(MyEquipmentActivity.this,true,memberMedalListBean,refresh);
                    diamondPayPop.showAtLocation(equipment_recycler, Gravity.BOTTOM,0,0);
                    ProgressDialogUtil.darkenBackground(MyEquipmentActivity.this,0.7f);
                    diamondPayPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
                        @Override
                        public void onDismiss() {
                            requestUserInfo();
                        }
                    });
                }else if(conditionsType.equals("1")){
                    //跳转邀请赚钱
                    InviteAndMakeMoneyActivity.startAty(MyEquipmentActivity.this,"");
                }else if(conditionsType.equals("2")){
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest.equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(MyEquipmentActivity.this,MyEquipmentActivity.this);
                    }else {
                        //跳转充值
                        startActivity(new Intent(MyEquipmentActivity.this, RechargeActivity.class));
                    }
                }else {
                    //跳转购彩游戏页面
                    startActivity(new Intent(MyEquipmentActivity.this, LiveShoppingActivity.class));
                }
            }else if(customType==2) {
                //在使用, 请求取消穿戴装备 status为0
                requestUseMount(medalInfo, 0,position);
            }else {
                //未使用, 请求穿戴装备 status为1
                requestUseMount(medalInfo, 1,position);
            }
        }
    }

    /**
     * 请求穿戴/取消穿戴装备
     * @param medalInfo
     * @param status  0取消穿戴装备  1请求穿戴装备
     */
    private void requestUseMount(EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo, int status,int position) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", status);
        data.put("medalInfoId", medalInfo.getId());
        HttpApiUtils.CpRequest(MyEquipmentActivity.this, null, RequestUtil.USE_MOUNT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                    if(status==0){
                        changeCustomType(position, 3);
                        showToast(Utils.getString(R.string.取消成功));
                    }else {
                        changeCustomType(position, 2);
                        showToast(Utils.getString(R.string.穿戴成功));
                    }

                    Utils.RequestUsingEquipment(MyEquipmentActivity.this,null);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    /**
     * 改变item的穿戴状态
     * @param position
     * @param customType
     */
    private void changeCustomType(int position, int customType) {
        Object o = entityList.get(position);
        if (o instanceof EquipmentAllEntity.MemberMedalListBean) {
            EquipmentAllEntity.MemberMedalListBean memberMedalListBean = (EquipmentAllEntity.MemberMedalListBean) o;
            EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo = memberMedalListBean.getMedalInfo();
            medalInfo.setCustomType(customType);//1未获取 2已使用 3 未使用
//            myEquipmentAdapter.setData(position, memberMedalListBean);
            myEquipmentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestList(false);
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.mount_tv,R.id.medal_tv,R.id.title_brand_tv,R.id.equpment_svga})
    public void  onClick(View v){
      switch (v.getId()){
          case R.id.mount_tv:
              controlClick(v);
              medalInfoType="0";
              if(StringMyUtil.isEmptyString(mountJsonStr)){
                  requestList(false);
              }else {
                  handlerJsonStr(mountJsonStr,false);
              }
              break;
          case R.id.medal_tv:
              controlClick(v);
              medalInfoType="1";
              if(StringMyUtil.isEmptyString(medalJsonStr)){
                  requestList(false);
              }else {
                  handlerJsonStr(medalJsonStr,false);
              }
              break;
          case R.id.title_brand_tv:
              controlClick(v);
              medalInfoType="2";
              if(StringMyUtil.isEmptyString(titleBrandJsonStr)){
                  requestList(false);
              }else {
                  handlerJsonStr(titleBrandJsonStr,false);
              }
              break;
          case R.id.equpment_svga:
              equpment_svga.setVisibility(View.GONE);
              svga_loading_iv.setVisibility(View.GONE);
              if(equpment_svga.isAnimating()){
                  equpment_svga.clearAnimation();
                  equpment_svga.stopAnimation(true);
              }
              ProgressDialogUtil.darkenBackground(MyEquipmentActivity.this,1f);
              if(equpmentSvgaMage!=null){
                  equpmentSvgaMage.getSvgaList().clear();
              }
              break;
          default:
              break;
      }
    }

    private void requestList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("medalInfoType",medalInfoType);
        HttpApiUtils.cpShowLoadRequest(MyEquipmentActivity.this, null, RequestUtil.MINE_EQUIPMENT, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(medalInfoType.equals("0")){
                    mountJsonStr = result;
                }else if(medalInfoType.equals("1")){
                    medalJsonStr = result;
                }else {
                    titleBrandJsonStr = result;
                }
                handlerJsonStr(result, isRefresh);
            }
            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,false,1,refresh);
            }
        });
    }

    private void handlerJsonStr(String result, boolean isRefresh) {
        entityList.clear();
        EquipmentAllEntity equipmentAllEntity = JSONObject.parseObject(result, EquipmentAllEntity.class);
        //已获得
        List<EquipmentAllEntity.MemberMedalListBean> memberMedalList = equipmentAllEntity.getMemberMedalList();
        //未获得
        List<EquipmentAllEntity.MemberMedalListBean.MedalInfoBean> medalInfoList = equipmentAllEntity.getMedalInfoList();
        RefreshUtil.success(1,refresh,loading_linear,nodata_linear,memberMedalList.size()+medalInfoList.size(),false,isRefresh,entityList);
        if(memberMedalList.size()!=0){
            //添加一个已获得的item
            entityList.add(new EquipmentGetEntity(true));
        }

        for (int i = 0; i < memberMedalList.size(); i++) {
            EquipmentAllEntity.MemberMedalListBean memberMedalListBean = memberMedalList.get(i);
            EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo = memberMedalListBean.getMedalInfo();
            if(medalInfo!=null){
                String status = memberMedalListBean.getStatus();
                if(status.equals("0")){
                    // 未使用
                    medalInfo.setCustomType(3); //1未获取 2已使用 3 未使用
                }else {
                    medalInfo.setCustomType(2); //1未获取 2已使用 3 未使用
                }
                entityList.add(memberMedalListBean);
            }

        }


        if(medalInfoList.size()!=0){
            //添加一个未获得的item
            entityList.add(new EquipmentGetEntity(false));
        }

        for (int i = 0; i < medalInfoList.size(); i++) {
            EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfoBean = medalInfoList.get(i);
            EquipmentAllEntity.MemberMedalListBean memberMedalListBean = new EquipmentAllEntity.MemberMedalListBean();
            medalInfoBean.setCustomType(1);
            memberMedalListBean.setMedalInfo(medalInfoBean);
            memberMedalListBean.setMedalInfoType(medalInfoType);
            entityList.add(memberMedalListBean);
        }
        myEquipmentAdapter.notifyDataSetChanged();
    }

    private void controlClick(View view) {
        for (int i = 0; i < titleTvList.size(); i++) {
            titleTvList.get(i).setSelected(false);
        }
        view.setSelected(true);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}