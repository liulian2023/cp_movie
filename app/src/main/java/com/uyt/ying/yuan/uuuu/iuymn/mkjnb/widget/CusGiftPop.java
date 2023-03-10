package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import okhttp3.Headers;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveGiftSVGAEntity;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.SendRedActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.GiftSvgaManage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveGiftMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.CusGiftRecyAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.GiftNumAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.GiftZSQAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GiftNumEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GradeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SkipModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.GiftEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;


import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CusGiftPop extends PopupWindow {
    private static  String TAG="CusGiftPop";
    private Activity mActivity;
    private Fragment fragment;
    private int userId;
    private final View view;
    private ViewPager2 viewpager2;
    private RecyclerView recy_zsq;
    private RecyclerView recy_giftnum;
    private ImageView iv_gift_num;
    private LinearLayout ll_gift_sendtype;
    private LinearLayout ll_gift_num;
    private TextView tv_send;
    private Animation loadAnimation;
    private TextView tv_num;
    private TextView moneyTv;
    private ImageView level_iv;
    private ProgressBar level_progress;
    private TextView need_amount_tv;
    //  private GiftNumPop giftNumPop;
    private LiveFragmentViewModel mViewModel;
    private CusGiftRecyAdapter cusGiftRecyAdapter;
    private GiftZSQAdapter giftZSQAdapter;
    int isUseToy;
    /**
     * ????????????
     */
    private List<GiftEntity.GiftListsBean> mList = new ArrayList<>();
    /**
     * ??????????????????
     */
    private List<GiftNumEntity.GiftNumListBean> mgiftNumList = new ArrayList<>();
    private List<Boolean> zsqList = new ArrayList<>();
    private GiftNumAdapter giftNumAdapter;
    private LiveFragment liveFragment;
    private  GiftEntity.GiftListsBean giftListsBean;
    private LiveEntity.AnchorMemberRoomListBean mLiveData;
  //  private  ArrayList<SVGAVideoEntity> svgaList;
    private GiftSvgaManage GiftSvgaMage;
    private ConstraintLayout wrapConLayout;
    private LinearLayout ll_count;




    public CusGiftPop(Activity mActivity, Fragment fragment, int userId, LiveEntity.AnchorMemberRoomListBean mLiveData, GiftSvgaManage GiftSvgaMage,int isUseToy) {
        super(mActivity);
        this.mActivity = mActivity;
        this.fragment = fragment;
        this.userId = userId;
        this.mLiveData = mLiveData;
        this.GiftSvgaMage = GiftSvgaMage;
        this.isUseToy = isUseToy;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_cusgift, null);
        mViewModel = ViewModelProviders.of(fragment).get(LiveFragmentViewModel.class);
        initView();
        initPopWindow();

    }
    private void initView() {
        liveFragment= (LiveFragment) fragment;
        viewpager2 = view.findViewById(R.id.viewpager2_cusgiftpop);
        ll_count = view.findViewById(R.id.ll_count);
        recy_zsq = view.findViewById(R.id.recy_cusgift);
        recy_giftnum = view.findViewById(R.id.recy_giftnum);
        iv_gift_num = view.findViewById(R.id.iv_gift_num);
        ll_gift_sendtype = view.findViewById(R.id.ll_gift_sendtype);
        ll_gift_num = view.findViewById(R.id.ll_gift_num);
        tv_send = view.findViewById(R.id.tv_send);
        tv_num = view.findViewById(R.id.tv_num);
        wrapConLayout =view.findViewById(R.id.send_gift_wrap_linear);
        moneyTv=view.findViewById(R.id.tv_account);
        level_iv=view.findViewById(R.id.level_iv);
        need_amount_tv=view.findViewById(R.id.need_amount_tv);
        level_progress=view.findViewById(R.id.level_progress);
        /**
         * ??????????????? viewpager2 + recyclclerView ?????????recyclerview ?????????
         */
        cusGiftRecyAdapter = new CusGiftRecyAdapter(R.layout.item_cusgift_recy, mList,isUseToy);
        viewpager2.setAdapter(cusGiftRecyAdapter);
        requestGiftList();
        wrapConLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_gift_sendtype.setVisibility(View.GONE);
            }
        });
        ll_count.setOnClickListener(v->{
            String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
            if(isTest.equals("-1")){
                Utils.initSkipVisitorSafeCenterPop(fragment.getContext(),mActivity);
            }else {
                mActivity.startActivity(new Intent(mActivity, RechargeActivity.class));
            }
            CusGiftPop.this.dismiss();
        });
        /**
         * ?????????????????? ???????????? ????????????????????????????????? ?????? ????????????
         */
        cusGiftRecyAdapter.setmOnItemClickListener((int position)->{
            if (position == 0){
                SendRedActivity.startAty(mActivity,liveFragment.roomId);
                EventBus.getDefault().postSticky(new SkipModel(true));
                CusGiftPop.this.dismiss();
                return;
            }
            LogUtils.e(Utils.getString(R.string.???????????????)+position);
            giftListsBean = mList.get(position);
            for (int i=0;i<mList.size();i++){
                mList.get(i).setSecect(false);
                if (position==i){
                    if(i!=mList.size()-1){
                        mList.get(i).setSecect(true);
                    }
                }
            }
            //????????????????????????recy
            ll_gift_sendtype.setVisibility(View.GONE);
            //??????recyclerview
            cusGiftRecyAdapter.notifyDataSetChanged();
            swichSendBtn();
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  ???????????????????????????, ??????????????????
                Long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
                String anchorMemberId = mLiveData.getAnchorMemberId();
                if(mList.size()>=2){
                    giftListsBean= giftListsBean==null?mList.get(1):giftListsBean;
                }else {
                    ToastUtil.showToast(Utils.getString(R.string.?????????????????????????????????));
                    return;
                }
                long giftId= giftListsBean.getId();
                int giftCount;
                if(StringMyUtil.isNotEmpty(giftListsBean.getSpecialEffects())){//??????????????????????????????
                    giftCount=1;
                }else {
                    giftCount = Integer.parseInt(tv_num.getText().toString());
                }
                String giftAmount = giftListsBean.getGiftAmount();

                    resSendGift(user_id, anchorMemberId, giftCount, giftId,giftAmount, giftListsBean.getGear(),giftListsBean.getGearTime());
//                mViewModel.sendGift(liveFragment, RequestUtil.SEND_GIFT, user_id, anchorMemberId,giftListsBean.getId(), giftCount);
            }
        });
        /**
         * ?????? viewpager2 ??????????????????
         */
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Utils.logE("onPageSelected:"," position:"+position);
                for (int i =0;i<zsqList.size();i++){
                    zsqList.set(i,false);
                }
                if(zsqList.size()>position){
                    zsqList.set(position,true);
                }
                giftZSQAdapter.notifyDataSetChanged();
            }
        });
        /**
         * ????????????????????????
         */
        giftZSQAdapter = new GiftZSQAdapter(R.layout.item_zsq,zsqList, GiftZSQAdapter.SelectorColor.WHITE);
        LinearLayoutManager zsqLinearLayoutManager =  new LinearLayoutManager(mActivity);
        zsqLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_zsq.setLayoutManager(zsqLinearLayoutManager);
        recy_zsq.setHasFixedSize(true);
        recy_zsq.setAdapter(giftZSQAdapter);

        /**
         * ?????????giftnum recy
         */
        giftNumAdapter = new GiftNumAdapter(R.layout.item_giftnum,mgiftNumList);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy_giftnum.setLayoutManager(linearLayoutManager);
        recy_giftnum.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        recy_giftnum.setHasFixedSize(true);
        recy_giftnum.setAdapter(giftNumAdapter);
        giftNumAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mViewModel.selectGiftNumByPosition(position);
                ll_gift_sendtype.setVisibility(View.GONE);
                if (position==0){
                    CusSetMaDialog cusSetMaDialog = new CusSetMaDialog.Builder(mActivity)
                            .setTitle(Utils.getString(R.string.????????????????????????))
                            .setMessage(Utils.getString(R.string.????????????1???10000))
                            .setNo(Utils.getString(R.string.??????), v -> {
                            })
                            .setYes(Utils.getString(R.string.??????), (View v, int value) -> {
                                tv_num.setText(""+value);
                            })
                            .create(2);
                    cusSetMaDialog.show();
                }
            }
        });


        /**
         * ????????????
         */
        ll_gift_num.setOnClickListener(v -> {
            int isVisibel=ll_gift_sendtype.getVisibility();
            loadAnimation = AnimationUtils.loadAnimation(mActivity,
                    R.anim.scale_animation);
            // decodeSvgaFromURL.setFillAfter(true);
            iv_gift_num.startAnimation(loadAnimation);
            if (isVisibel == View.VISIBLE){
                ll_gift_sendtype.setVisibility(View.GONE);
            }else {
                ll_gift_sendtype.setVisibility(View.VISIBLE);
            }
        });

/*
        *//**
         * ????????????????????????
         *//*
        mViewModel.getGiftLiveData().observe(mContext, (List<GiftEntity.GiftListsBean> list) -> {
            if (list != null) {
                LogUtils.e("getGiftLiveData observe:");
                mList.clear();
                mList.addAll(list);
                cusGiftRecyAdapter.notifyDataSetChanged();
            }
        });*/
        /**
         * ???????????????????????? ?????? ??????
         */
        mViewModel.getGiftNumLiveData().observe(fragment,(List<GiftNumEntity.GiftNumListBean> list)->{
            mgiftNumList.clear();
            mgiftNumList.addAll(list);
            giftNumAdapter.notifyDataSetChanged();
            for (int i=0;i<list.size();i++){
                if (i!=0&&list.get(i).isIsSelect()){
                    tv_num.setText(""+list.get(i).getNum());
                }
            }
        });

        getMoney();

        requestDengji();
    }

    private void swichSendBtn() {
        //??????????????????
        if ( StringMyUtil.isNotEmpty(giftListsBean.getSpecialEffects())){
            ll_gift_num.setVisibility(View.GONE);
            tv_send.setBackground(ContextCompat.getDrawable(mActivity,R.drawable.bkg_gift_gradient3));
        }else {
            ll_gift_num.setVisibility(View.VISIBLE);
            tv_send.setBackground(ContextCompat.getDrawable(mActivity,R.drawable.bkg_gift_gradient2));
        }
    }

    private void requestDengji() {
            HttpApiUtils.CPnormalRequest(mActivity, fragment, RequestUtil.REQUEST_08rzq, new HashMap<String, Object>(),new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    handGradeJson(result);
                }

                @Override
                public void onFailed(String msg) {
                }
            });


    }

    private void handGradeJson(String result) {
        GradeEntity gradeEntity = JSONObject.parseObject(result, GradeEntity.class);
        String mineIntegral = gradeEntity.getGrowthIntegral();//??????????????????
        String nextGradeNeedGrowthIntegral = gradeEntity.getNextGradeNeedGrowthIntegral();//?????????????????????????????????????????????
        String nextGradeGrowthIntegral = gradeEntity.getNextGradeGrowthIntegral();//???????????????????????????
        String pointGrade = gradeEntity.getPointGrade();//????????????
        List<GradeEntity.MemberGradeMechanismListBean> memberGradeMechanismList = gradeEntity.getMemberGradeMechanismList();
        String growthIntegral="1";
        for (int i = 0; i < memberGradeMechanismList.size(); i++) {
            GradeEntity.MemberGradeMechanismListBean memberGradeMechanismListBean = memberGradeMechanismList.get(i);
            if(memberGradeMechanismListBean.getGrade().equals(pointGrade)){
                growthIntegral = memberGradeMechanismListBean.getGrowthIntegral();//???????????????????????????
                break;
            }
        }
        //???????????????
        if(!growthIntegral.equals("1")){
            DecimalFormat df=new DecimalFormat("0.00");//??????????????????
            // mineIntegral ??????????????????  growthIntegral ???????????????????????????  nextGradeGrowthIntegral ???????????????????????????
            String format = df.format((float) (Double.parseDouble(mineIntegral)- Double.parseDouble(growthIntegral)) / (Double.parseDouble(nextGradeGrowthIntegral)-Double.parseDouble(growthIntegral)));
            int v = (int) (Float.parseFloat(format) * 100);
            level_progress.setProgress(v);
        }
        //????????????icon

//                level_iv.setImageResource(Utils.getLevelDrawble(Integer.parseInt(pointGrade)+1));
        GlideLoadViewUtil.LoadNormalView(fragment.getContext(), Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(pointGrade)+1)),level_iv);
        GradeEntity.MemberGradeMechanismListBean lastItem = memberGradeMechanismList.get(memberGradeMechanismList.size() - 1);
        //??????????????????????????????
        if(pointGrade.equals(lastItem.getGrade())){
            need_amount_tv.setText(Utils.getString(R.string.????????????));
        }else {
            need_amount_tv.setText(Utils.getString(R.string.???)+nextGradeNeedGrowthIntegral+Utils.getString(R.string.??????));
        }
    }

    private void requestGiftList() {
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.GIFT_LIST_CACHE, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HttpApiUtils.CpRequest(mActivity, fragment,RequestUtil.GIFT_LIST, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.GIFT_LIST_CACHE,result);
                    handGiftJson(result);
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            handGiftJson(jsonStr);
        }

    }

    private void handGiftJson(String result) {
        GiftEntity giftEntity = JSONObject.parseObject(result, GiftEntity.class);
        mList.clear();
        GiftEntity.GiftListsBean redBean = new GiftEntity.GiftListsBean();
        redBean.setGiftTitle(Utils.getString(R.string.?????????));
        mList.add(0,redBean);
        mList.addAll(giftEntity.getGiftLists());
        //??????????????????bean,???????????????????????????????????????????????????, pop??????????????????, ????????????bean????????????????????????
        GiftEntity.GiftListsBean lastBean = new GiftEntity.GiftListsBean();
        lastBean.setGiftTitle(Utils.getString(R.string.??????));
        mList.add(mList.size(),lastBean);
        int totalPage;
        if (mList.size()%8==0){
            totalPage = mList.size()/8;
        }else {
            totalPage = mList.size()/8+1;
        }
        for (int i=0;i<totalPage;i++){
            zsqList.add(false);
        }
        //  mList ????????????????????????
        if(mList.size()>1){
            mList.get(1).setSecect(true);//?????????????????????
            giftListsBean = mList.get(1);
            swichSendBtn();
        }
        cusGiftRecyAdapter.notifyDataSetChanged();
    }

    /*
     ??????????????????
       */
    private void getMoney() {
        HashMap<String, Object> dataMoney = new HashMap<>();//REQUEST_06rzq
        long user_id = SharePreferencesUtil.getLong(fragment.getContext(),"user_id",0L);
        dataMoney.put("user_id",user_id);
        Utils.docking(dataMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                SharePreferencesUtil.putString(fragment.getContext(),"moneyString",content);
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                /*
                ????????????
                 */
                String amount = memberMoney.getString("amount");
                moneyTv.setText(amount);
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    /**
     * ????????????????????????
     * @param user_id   ??????id
     * @param anchorMemberId ??????id
     * @param giftCount ????????????
     * @param giftId ??????id
     */
    private void resSendGift(Long user_id, String anchorMemberId, int giftCount, long giftId,String giftAmount,String gear,String gearTime) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        data.put("anchorMemberId",anchorMemberId);
        data.put("giftId",giftId);
        data.put("giftCount",giftCount);
        data.put("remoteLiveManagementId",mLiveData.getRemoteLiveManagementId());
        data.put("gear",gear);
        data.put("gearTime",gearTime);
        HttpApiUtils.CpRequest(mActivity, fragment, RequestUtil.SEND_GIFT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
//                CusGiftPop.this.dismiss();
                String giftUrl = giftListsBean.getSpecialEffects();
                String userInfoJson = RongLibUtils.setUserInfo(mActivity,liveFragment.managerTypeEntity);
                String giftTitle = giftListsBean.getGiftTitle();//?????????
                String giftIcon = giftListsBean.getGiftLogo();
                LiveGiftMessage liveGiftMessage = new LiveGiftMessage(userInfoJson, giftId+"", giftTitle, giftCount+"", giftUrl, giftIcon,giftAmount,gear,gearTime);
                RongLibUtils.sendMessage(liveFragment.roomId,liveGiftMessage);
                ToastUtil.showToast(Utils.getString(R.string.????????????));
                if(StringMyUtil.isNotEmpty(giftUrl)){//???????????????????????????
                    LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.checkLiveImageUrl(giftUrl), null);
                    GiftSvgaMage.startAnimator(liveGiftSVGAEntity);
                }
                String toString = liveFragment.money_tv.getText().toString();

                //?????????????????????
                BigDecimal oldNum = new BigDecimal(toString.contains("-")?"0":toString);//money_tv?????????- - - ,??????????????????
                        BigDecimal add = oldNum.add((new BigDecimal(giftAmount).multiply(new BigDecimal(giftCount))));
                    liveFragment. money_tv.setText(add +"");
                /*
                ????????????
                 */
                getMoney();
            }
            @Override
            public void onFailed(String msg) {
            }
        });
    }


    private void initPopWindow() {
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popAlphaanim0_5);
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(mActivity,R.color.alpha_90_black));
        //???????????????????????????
        this.setBackgroundDrawable(dw);
        //    backgroundAlpha(mActivity, 0.5f);//0.0-1.0

        this.setOnDismissListener(() -> {
            backgroundAlpha(mActivity, 1f);
            ll_gift_sendtype.setVisibility(View.GONE);
            liveFragment.mViewModel.setShowingAmount(false);
        });
    }
    /**
     * ????????????????????????????????????(?????????,???????????????)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1f) {
            //?????????????????????????????????flag,???????????????activity?????????????????????
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }

}
