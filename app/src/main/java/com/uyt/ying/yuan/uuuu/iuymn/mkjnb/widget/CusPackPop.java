package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import okhttp3.Headers;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveNormalRedMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PackType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RankTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedStatus;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CusPackPop extends PopupWindow {

    private Activity mActivity;
    private Fragment fragment;
    private String packId;

    private View view;
    private String qbPrice;


    private LiveFragmentViewModel mViewModel;
    /**
     * ????????????????????????
     */
    private ConstraintLayout coslayout_open;
    private ImageView iv_hb_open;
    private TextView tv_open_msg1;
    private TextView tv_open_msg2;
    /**
     * ????????????????????????
     */
    private ConstraintLayout coslayout_unclock;
    private ImageView iv_hb_unclock;
    private TextView tv_unclock_msg;
    private TextView tv_unclock_gamerules;
    private TextView tv_unclock_range;
    /**
     * ????????????????????????
     */
    private ConstraintLayout coslayout_pack;
    private TextView tv_pack_rank;
    private TextView tv_pack_money;
    private TextView getAlreadyTv;

    /**
     * showtype ????????????????????? 3???
     */
    public enum SHOWTYPE {
        OPEN,   //???????????????
        UNLOCK, //????????????
        PACK   //????????????????????????
    }
    private RedStatus redStatus;
    private RedType redType;
    private String roomId;
    private String failInfo;

    private LiveFragment liveFragment;

    public String getFailInfo() {
        return failInfo;
    }

    public void setFailInfo(String failInfo) {
        this.failInfo = failInfo;
    }

    /**
     * ??????????????????
     */
    public enum CLICKTYPE{
        OPEN,
        UNLOCK,
        UNLOCK_GAMERULES,
        UNLOCK_RANK,
        PACK_RANK
    }


    public interface OnItemClickListener {
        void onItemClick(View view,CLICKTYPE clicktype);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     *   ????????????????????????????????????
     * @param packId
     * @param mActivity
     * @param fragment
     * @param redStatus
     * @param redType
     */
    public CusPackPop(String packId, Activity mActivity, Fragment fragment, RedStatus redStatus, RedType redType) {
        super(mActivity);
        this.packId = packId;
        this.mActivity = mActivity;
        this.fragment = fragment;
        this.redStatus = redStatus;
        this.redType = redType;
        init(mActivity, fragment);
    }




    /**
     * ??????????????????????????????????????????
     * @param packId
     * @param mActivity
     * @param fragment
     * @param redStatus
     * @param qbPrice
     * @param redType
     * @param roomId
     * @param failInfo
     */
    public CusPackPop(String packId, Activity mActivity, Fragment fragment, RedStatus redStatus,String qbPrice,RedType redType,String roomId,String failInfo) {
        super(mActivity);
        this.packId = packId;
        this.mActivity = mActivity;
        this.fragment = fragment;
        this.redStatus = redStatus;
        this.redStatus = redStatus;
        this.qbPrice = qbPrice;
        this.redType = redType;
        this.roomId = roomId;
        this.failInfo = failInfo;
        init(mActivity, fragment);
    }

    private void init(Activity mActivity, Fragment fragment) {
        liveFragment = (LiveFragment) fragment;
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_cuspack, null);
        initView();
        initPopWindow();
    }

    private void initView(){
        coslayout_open = view.findViewById(R.id.coslayout_open);
        iv_hb_open = view.findViewById(R.id.iv_hb_open);
        tv_open_msg1 = view.findViewById(R.id.tv_open_msg1);
        tv_open_msg2 = view.findViewById(R.id.tv_open_msg2);

        coslayout_unclock = view.findViewById(R.id.coslayout_unclock);
        iv_hb_unclock = view.findViewById(R.id.iv_hb_unclock);
        tv_unclock_msg = view.findViewById(R.id.tv_unclock_msg);
        tv_unclock_gamerules = view.findViewById(R.id.tv_unclock_gamerules);
        tv_unclock_range = view.findViewById(R.id.tv_unclock_range);

        coslayout_pack = view.findViewById(R.id.coslayout_pack);
        tv_pack_rank = view.findViewById(R.id.tv_pack_rank);
        tv_pack_money = view.findViewById(R.id.tv_pack_money);
        getAlreadyTv = view.findViewById(R.id.get_already_tv);

        /**
         * ??????????????????
         */
        iv_hb_open.setOnClickListener(v -> {
//            mViewModel.reqPackInfo(packId,1);
            HashMap<String, Object> data = new HashMap<>();
            Long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
            switch (redType){
                case PT:
                    resOpenPtRed(data, user_id);
                    break;
                case QY:
                    resOpenQyRed(data, user_id);
                    break;
                case ZX:
                    resOpenZxRed(data, user_id);
                    break;
                default:
                    break;
            }

            mOnItemClickListener.onItemClick(v,CLICKTYPE.OPEN);

        });
        iv_hb_unclock.setOnClickListener(v -> {
            String string = tv_unclock_range.getText().toString();
            if(string.equals(CommonStr.SHOW_ZX_RANK)){//????????????????????????
                liveFragment.showPackPopType(PackType.ZX);
                CusPackPop.this.dismiss();
            }else if(string.equals(CommonStr.SHOW_QY_RANK)){//????????????????????????
//                liveFragment.showPackPopType(PackType.QY);
                if(!(mActivity instanceof  InviteAndMakeMoneyActivity)){
//                    mActivity.startActivity(new Intent(mActivity, InviteAndMakeMoneyActivity.class));
                    InviteAndMakeMoneyActivity.startAty(mActivity,liveFragment.roomId);
                }

            }else {
                if(redType==RedType.PT){
                //??????
                    if(SharePreferencesUtil.getString(MyApplication.getInstance(),"isTest","").equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(fragment.getContext(),mActivity);
                    }else {
                        liveFragment.startActivity(new Intent(liveFragment.getContext(), RechargeActivity.class));
                    }
                }else {
                //??????
                    liveFragment.showPackPopType(PackType.TJ);
                }
            }
            CusPackPop.this.dismiss();
            mOnItemClickListener.onItemClick(v,CLICKTYPE.UNLOCK);
        });
        tv_unclock_gamerules.setOnClickListener(v -> {
            String string = tv_unclock_range.getText().toString();
            if(string.equals(CommonStr.SHOW_ZX_RANK)){//????????????????????????
                liveFragment.showPackPopType(PackType.ZX);
                CusPackPop.this.dismiss();
            }else if(string.equals(CommonStr.SHOW_QY_RANK)){//????????????????????????
//                liveFragment.showPackPopType(PackType.QY);
                if(!(mActivity instanceof  InviteAndMakeMoneyActivity)){
//                    mActivity.startActivity(new Intent(mActivity, InviteAndMakeMoneyActivity.class));
                    InviteAndMakeMoneyActivity.startAty(mActivity,liveFragment.roomId);
                }
                CusPackPop.this.dismiss();
            }
            mOnItemClickListener.onItemClick(v,CLICKTYPE.UNLOCK_GAMERULES);
        });
        tv_unclock_range.setOnClickListener(v -> {

            String string = tv_unclock_range.getText().toString();
            if(string.equals(CommonStr.SHOW_ZX_RANK)){//?????????????????????
                RouteUtils.start2PaihangActivity(mActivity, RankTypeEnum.ZX);
            }else if(string.equals(CommonStr.SHOW_QY_RANK)){//?????????????????????
                RouteUtils.start2PaihangActivity(mActivity, RankTypeEnum.QY);
            }
//            CusPackPop.this.dismiss();
            mOnItemClickListener.onItemClick(v,CLICKTYPE.UNLOCK_RANK);
        });
        tv_pack_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = tv_pack_rank.getText().toString();
                if(toString.equals(CommonStr.SHOW_QY_RANK)){
                    RouteUtils.start2PaihangActivity(mActivity, RankTypeEnum.QY);
                }else if(toString.equals(CommonStr.SHOW_ZX_RANK)){
                    RouteUtils.start2PaihangActivity(mActivity, RankTypeEnum.ZX);

                }
            }
        });


        initRedStatus();
        /**
         * ?????? PackLiveData
         */
/*        mViewModel.getPackLiveData().observe(mContext,(PackEntity packEntity)-> {
                    if (packEntity.getData().getPackId() == packId) {
                        if (packEntity.getData().isLock()) {
                            showViewType(SHOWTYPE.UNLOCK);
                        } else {
                            showViewType(SHOWTYPE.PACK);
                            initPackView(packEntity.getData().getMoney());
                        }
                    }
                }
        );*/

    }
    /**
     * ???????????????????????????(???????????? ?????? ???????????????)
     */
    private void initRedStatus() {
        switch (redStatus){
            case NORMAL:
                break;
            case FAIL:
                tv_unclock_msg.setText(StringMyUtil.isEmptyString(failInfo)?"":failInfo);
                tv_unclock_msg.setText(failInfo);
                tv_unclock_gamerules.setVisibility(View.GONE);
                iv_hb_unclock.setVisibility(View.VISIBLE);
                tv_unclock_range.setVisibility(View.GONE);
                showViewType(SHOWTYPE.UNLOCK);
                break;
            case SUCCESS:
                showViewType(SHOWTYPE.PACK);
                getAlreadyTv.setVisibility(View.VISIBLE);
                tv_pack_money.setText(qbPrice);
                if(redType==RedType.PT||redType==RedType.TJ){
                    tv_pack_rank.setVisibility(View.INVISIBLE);
                    if(redType==RedType.TJ){
                        getAlreadyTv.setVisibility(View.INVISIBLE);
                    }
                }

                break;
            default:
                break;
        }
    }

    /**
     * ???????????? ???????????????
     *  @param data
     * @param user_id
     */
    private void resOpenQyRed(HashMap<String, Object> data, Long user_id) {
        data.put("user_id",user_id);
        data.put("chatRoomId",roomId);
        HttpApiUtils.CpRequest(mActivity,fragment, RequestUtil.HB_OPEN_QUYUE_HB, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String price = jsonObject.getString("price");
                if(jsonObject.containsKey("price")&&StringMyUtil.isNotEmpty(price)){
                    tv_pack_money.setText(price+"");
                    showViewType(SHOWTYPE.PACK);
                    tv_pack_rank.setText(CommonStr.SHOW_QY_RANK);
                }else {
                    String message = jsonObject.getString("message");
                    tv_unclock_msg.setText(message);
                    tv_unclock_range.setText(CommonStr.SHOW_QY_RANK);
                    showViewType(SHOWTYPE.UNLOCK);
                }
              /*  String isComplete = jsonObject.getString("isComplete");
                if(jsonObject.containsKey("lastNum")){
                    int lastNum = jsonObject.getInteger("lastNum");
                    if(lastNum==0){
                        SharePreferencesUtil.putBoolean(MyApplication.getInstance(),"haveGetRedNum",false);
                    }
                }
                if(isComplete.equals("1")){//    ???????????? ????????????
                    BigDecimal   price   = jsonObject.getBigDecimal("price");
//                  price   = jsonObject.getBigDecimal("zxHBPrice");
                    //      url=RequestUtil.HB_OPEN_ZX_HB;
                    tv_pack_money.setText(price+"");
                    showViewType(SHOWTYPE.PACK);
                }else {// ?????????????????????
                    String message = jsonObject.getString("message");
                    tv_unclock_msg.setText(message);
                    tv_unclock_range.setText(CommonStr.SHOW_QY_RANK);
                    showViewType(SHOWTYPE.UNLOCK);
                }*/
            }
            @Override
            public void onFailed(String msg) {

            }
        });
    }
    /**
     * ???????????? ???????????????
     *  @param data
     * @param user_id
     */
    private void resOpenZxRed(HashMap<String, Object> data, Long user_id) {
        data.put("user_id",user_id);
        data.put("chatRoomId",roomId);
        HttpApiUtils.CpRequest(mActivity,fragment, RequestUtil.HB_OPEN_ZX_HB, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String isComplete = jsonObject.getString("isComplete");
                isComplete = StringMyUtil.isEmptyString(isComplete)?"0":isComplete;
                    if(isComplete.equals("1")){//????????????
                        String isReceive = jsonObject.getString("isReceive");
                        if(isReceive.equals("1")){//????????????
                            String message = jsonObject.getString("message");
                            initZxFailView(message);
                        }else {//????????????
                            BigDecimal  tzPrice   = jsonObject.getBigDecimal("tzPrice");
                            if(null==tzPrice){
                                tzPrice=BigDecimal.ZERO;
                            }
                            if(tzPrice.compareTo(BigDecimal.ONE)>0){//????????????????????????1
                                BigDecimal   zxHBPrice   = jsonObject.getBigDecimal("zxHBPrice");
                                tv_pack_money.setText(zxHBPrice+"");
                                showViewType(SHOWTYPE.PACK);
                                tv_pack_rank.setText(CommonStr.SHOW_ZX_RANK);
                            }else {//???????????????????????????
                                String message = jsonObject.getString("message");
                                if(StringMyUtil.isEmptyString(message)){
                                    message = Utils.getString(R.string.???????????????????????????);
                                }
                                initZxFailView(message);
                            }
                        }

                    }else {//????????????
                        String message = jsonObject.getString("message");
                        initZxFailView(message);
                    }

            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     * ??????????????????????????????????????????
     * @param message
     */
    private void initZxFailView(String message) {
        tv_unclock_msg.setText(message);
        tv_unclock_range.setText(CommonStr.SHOW_ZX_RANK);
        showViewType(SHOWTYPE.UNLOCK);
    }


    /**
     * ???????????????????????????
     * @param data
     * @param user_id
     */
    private void resOpenPtRed(HashMap<String, Object> data, Long user_id) {
        data.put("redId",packId);
        data.put("user_id", user_id);
        HttpApiUtils.CpRequest(mActivity, fragment,RequestUtil.OPEN_RED, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String price = jsonObject.getString("price");
                String isOver = jsonObject.getString("isOver");
                isOver = StringMyUtil.isEmptyString(isOver)?"0":isOver;
                String isOpened = jsonObject.getString("isOpened");
                String message = jsonObject.getString("message");
      /*          if(StringMyUtil.isEmptyString(price)){
                    price ="0.00";
                }*/
                if(isOver.equals("1")){   //???????????????????????????????????????
                    if(isOpened.equals("1")){
                        //??????????????????
                        tv_pack_money.setText(price);
                        tv_pack_rank.setVisibility(View.INVISIBLE);
                        chooseByRedId(price,null);
                        showViewType(SHOWTYPE.PACK);

                    }else {
                        //???????????????
                        tv_unclock_msg.setText(message);
                        tv_unclock_gamerules.setVisibility(View.GONE);
                        //????????????????????????????????????
                        iv_hb_unclock.setVisibility(View.GONE);
                        tv_unclock_range.setVisibility(View.GONE);
                        tv_pack_rank.setVisibility(View.INVISIBLE);
                        showViewType(SHOWTYPE.UNLOCK);


                    }
                }else {
                    //???????????????
                    if(StringMyUtil.isEmptyString(price)){
                        //?????????
                        tv_unclock_msg.setText(message);
                        tv_unclock_gamerules.setVisibility(View.GONE);
                        iv_hb_unclock.setVisibility(View.VISIBLE);
                        tv_unclock_range.setVisibility(View.GONE);
                        tv_pack_rank.setVisibility(View.INVISIBLE);
                        showViewType(SHOWTYPE.UNLOCK);
                    }else {
                        //??????????????????
                        tv_pack_money.setText(price);
                        tv_pack_rank.setVisibility(View.INVISIBLE);
                        chooseByRedId(price,null);
                        showViewType(SHOWTYPE.PACK);
                        String userNickName = SharePreferencesUtil.getString(MyApplication.getInstance(), "userNickName", "");
                        if(liveFragment!=null){
                            LiveNormalRedMessage liveNormalRedMessage = new LiveNormalRedMessage(RongLibUtils.setUserInfo(MyApplication.getInstance(),liveFragment. managerTypeEntity), price, packId, userNickName);
                            RongLibUtils.sendMessage(roomId,liveNormalRedMessage);
                        }
                    }

                }
/*                if(StringMyUtil.isEmptyString(price)){
//                    chooseByRedId(null,message);
                    tv_unclock_msg.setText(message);
                    tv_unclock_gamerules.setVisibility(View.GONE);
                    iv_hb_unclock.setVisibility(View.VISIBLE);
                    tv_unclock_range.setVisibility(View.GONE);
                    tv_pack_rank.setVisibility(View.INVISIBLE);
                    showViewType(SHOWTYPE.UNLOCK);
                }else {
                    tv_pack_money.setText(price);
                    tv_pack_rank.setVisibility(View.INVISIBLE);
                    chooseByRedId(price,null);
                    showViewType(SHOWTYPE.PACK);
                }*/

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    /**
     * ????????????????????????redId?????????item,???????????????????????????????????????,???????????????????????????????????????????????????
     * @param price  ????????????
     */
    private void chooseByRedId(String price,String failInfo) {
        LiveFragment liveFragment = (LiveFragment) fragment;
        ArrayList<LiveMessageModel> liveMessageModelArrayList = liveFragment.liveChatFragment.liveMessageModelArrayList;
        if(StringMyUtil.isEmptyString(failInfo)){//????????????
            for (int i = 0; i < liveMessageModelArrayList.size(); i++) {
                LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(i);
                long redId = liveMessageModel.getRedId();
                if((redId+"").equals(packId)){
                    liveMessageModel.setRedStatus(RedStatus.SUCCESS);
                    liveMessageModel.setQbPrice(price);
                    break;
                }
            }
        }else {//????????????
            for (int i = 0; i < liveMessageModelArrayList.size(); i++) {
                LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(i);
                long redId = liveMessageModel.getRedId();
                if((redId+"").equals(packId)){
                    liveMessageModel.setRedStatus(RedStatus.FAIL);
                    liveMessageModel.setFailInfo(failInfo);
                    break;
                }
            }
        }

    }

    private void initPopWindow(){
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popAlphaanim0_5);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //???????????????????????????
        this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0
        this.setOnDismissListener(()->{
            backgroundAlpha(mActivity,1f);
        });
    }

    /**
     * ???????????? ?????? ??????coslayout
     * @param showtype
     */
    public void showViewType(SHOWTYPE showtype){
        switch (showtype){
            case OPEN:
                coslayout_open.setVisibility(View.VISIBLE);
                coslayout_unclock.setVisibility(View.GONE);
                coslayout_pack.setVisibility(View.GONE);
                break;
            case UNLOCK:
                coslayout_open.setVisibility(View.GONE);
                coslayout_unclock.setVisibility(View.VISIBLE);
                coslayout_pack.setVisibility(View.GONE);
                break;
            case PACK:
                coslayout_open.setVisibility(View.GONE);
                coslayout_unclock.setVisibility(View.GONE);
                coslayout_pack.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * ?????????????????? msg
     * @param msg1
     * @param msg2
     */
    public void initOpenView(String msg1,String msg2){
        tv_open_msg1.setText(msg1);
        tv_open_msg2.setText(msg2);
    }

    public void initUnlockView(String msg){
        tv_unclock_msg.setText(msg);
    }

    public void initPackView(String money){
        tv_pack_money.setText(money);
    }

    /**
     * ????????????????????????????????????(?????????,???????????????)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if(bgAlpha==1f){
            //?????????????????????????????????flag,???????????????activity?????????????????????
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }


}
