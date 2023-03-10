package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveFollowMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonSurePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusGiftPop;
import org.greenrobot.eventbus.EventBus;
import java.math.BigDecimal;
import java.util.HashMap;
import okhttp3.Headers;

public class FollowPop extends PopupWindow implements View.OnClickListener, CommonTipPop.OnClickLintener {
    private TextView collectBtn;
    private ImageView collectIv;
    private TextView collectTitleTv;
    private ImageView collectDeleteIv;
    private ImageView moreIv;
    private TextView idTv;
    private TextView calling_information_tv;
    Fragment fragment;
    int isCollect;//?????????????????????
    String liveName;//??????
    String liveId;//??????id
    String account;//????????????
    String imageUrl; //?????????
    static  String CANEL_FOLLOW=Utils.getString(R.string.????????????);
    static  String FOLLOW=Utils.getString(R.string.????????????);
    Context context;
    LiveEntity.AnchorMemberRoomListBean mLiveData;
    //?????????????????????????????????pop
    CommonTipPop businessTipPop;
    private CusGiftPop cusGiftPop;
    private String anchorBusinessCard;
    private LiveFragment liveFragment;
    private String BUSINESS=Utils.getString(R.string.????????????);
    private String NO_BUSINESS=Utils.getString(R.string.???????????????);
    private CommonSurePop commonSurePop;
    boolean isSuperManager;
    int isUseToy;
    public FollowPop(Context context, Fragment fragment, LiveFragment liveFragment,boolean isSuperManager,int isUseToy) {
        super(context);
        this.context = context;
        this.fragment = fragment;
        this.liveFragment = liveFragment;
        this.isSuperManager = isSuperManager;
        this.isUseToy = isUseToy;
        mLiveData = liveFragment.getmLiveData();
        isCollect = mLiveData.getIsFollow();
        liveName = mLiveData.getAnchorNickName();
        imageUrl = mLiveData.getHeadImg();
        liveId = mLiveData.getAnchorMemberId()+"";
        account = mLiveData.getAnchorAccount();
        this.mLiveData = liveFragment.getmLiveData();
        initView();
    }

    private void initView() {
        View v = LayoutInflater.from(fragment.getContext()).inflate(R.layout.follow_movie_pop, null);
        idTv=v.findViewById(R.id.follow_id_tv);
        moreIv=v.findViewById(R.id.more_iv);
        collectBtn = v.findViewById(R.id.collect_btn);
        collectIv = v.findViewById(R.id.collect_big_iv);
        collectTitleTv = v.findViewById(R.id.collect_title_tv);
        collectDeleteIv = v.findViewById(R.id.collect_delete_iv);
        calling_information_tv = v.findViewById(R.id.calling_information_tv);
        if(liveFragment.managerTypeEntity.getAnchorBusinessCard().equals("1")){
            calling_information_tv.setText(BUSINESS);
        }else {
            calling_information_tv.setText(NO_BUSINESS);
        }
        collectTitleTv.setText(liveName);
        idTv.setText("ID:"+account);
        if (isCollect == 1) {
            collectBtn.setText(CANEL_FOLLOW);
        } else {
            collectBtn.setText(FOLLOW);
        }
        if(mLiveData.getIsPrivate()==1){
            GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkLiveImageUrl(imageUrl),collectIv);
        }else {
            GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(imageUrl),collectIv);
        }
        //????????????
        collectBtn.setOnClickListener(this);
        collectDeleteIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
        calling_information_tv.setOnClickListener(this);
        initPop(v);
    }



    private void initPop(View v) {
        this.setContentView(v);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(fragment.getActivity(),1f);
            }
        });
        this.showAtLocation(Utils.getContentView(fragment.getActivity()), Gravity.CENTER,0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more_iv:
                new ReportPop(context,fragment,liveId,isSuperManager);
                break;
            case R.id.collect_btn:
                if(Utils.isNotFastClick()){
                    requestCollect();
                }
                break;
            case R.id.collect_delete_iv:
                FollowPop.this.dismiss();
                break;
            case R.id.calling_information_tv:
                if(mLiveData.getIsPrivate()==0){
                    ToastUtil.showToast(Utils.getString(R.string.??????????????????));
                    FollowPop.this.dismiss();
                    return;
                }
                if(calling_information_tv.getText().toString().equals(BUSINESS)){
                    requestBusinessCard();
                }else {
                    initNoBussinessPop();
                    commonSurePop.showAtLocation(Utils.getContentView(fragment.getActivity()),Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(fragment.getActivity(),0.7f);
                    FollowPop.this.dismiss();
                }
                break;
            default:
                break;
        }
    }

    /**
     * ??????????????????
     */
    private void initNoBussinessPop() {
        if(commonSurePop==null){
            commonSurePop = new CommonSurePop(context, false, Utils.getString(R.string.??????), Utils.getString(R.string.??????????????????));
        }
    }

    /**
     * ??????????????????
     */
    private void requestBusinessCard() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorAccount",mLiveData.getAnchorAccount());
        data.put("remoteLiveManagementId",mLiveData.getRemoteLiveManagementId());
        HttpApiUtils.CpRequest(fragment.getActivity(), fragment,RequestUtil.ANCHOR_BUSINESS, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                FollowPop.this.dismiss();
                JSONObject jsonObject = JSONObject.parseObject(result);
                //??????????????????????????????????????????
                String price = jsonObject.getString("price");
                //??????????????????????????????
                String message = jsonObject.getString("message");
                //??????????????????
                anchorBusinessCard = jsonObject.getString("anchorBusinessCard");
                if(new BigDecimal(StringMyUtil.isEmptyString(price)?"0":price).compareTo(BigDecimal.ZERO)>0){
                    //price ??????0 ,????????????pop
                    initBusinessPop(message, Utils.getString(R.string.??????), Utils.getString(R.string.???????????????));
                }else {
                    //price ??????0,??????????????????
                    initBusinessPop(anchorBusinessCard, Utils.getString(R.string.????????????), Utils.getString(R.string.??????));
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initBusinessPop(String tipContent, String title, String sureContent) {
        businessTipPop = new CommonTipPop(context, (Activity) context, title, tipContent, sureContent);
        businessTipPop.setOnClickLintener(FollowPop.this);
        businessTipPop.showAsDropDown(Utils.getContentView(fragment.getActivity()), Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground((Activity) context, 0.7f);
    }


    private void requestCollect() {
        if(collectBtn.getText().toString().equals(CANEL_FOLLOW)){//????????????
            HashMap<String, Object> data = new HashMap<>();
            data.put("anchorMemberId",liveId);
            HttpApiUtils.CpRequest(fragment.getActivity(),fragment, RequestUtil.CANCEL_FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    //{"message":"success","status":"success"}
                    collectBtn.setText(Utils.getString(R.string.??????));
                    ToastUtil.showToast(Utils.getString(R.string.???????????????????????????));
                    EventBus.getDefault().postSticky(new FollowEvenModel(liveId+"",false));
                    FollowPop.this.dismiss();
                }

                @Override
                public void onFailed(String msg) {
                }
            });
        }else {//??????
            HashMap<String, Object> data = new HashMap<>();
            data.put("anchorMemberId",liveId);
            HttpApiUtils.CpRequest(fragment.getActivity(),fragment, RequestUtil.FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    //{"message":"success","status":"success"}
                    collectBtn.setText(Utils.getString(R.string.????????????));
                    ToastUtil.showToast(Utils.getString(R.string.????????????));
                    String zkCode = SharePreferencesUtil.getString(MyApplication.getInstance(), "zkCode", "");
                    if(fragment instanceof LiveFragment){
                        //????????????????????????
                         LiveFragment liveFragment = (LiveFragment) fragment;
                        LiveFollowMessage liveFollowMessage = new LiveFollowMessage(zkCode,RongLibUtils.setUserInfo(MyApplication.getInstance(),liveFragment.managerTypeEntity));
                        RongLibUtils.sendMessage(liveFragment.roomId,liveFollowMessage);
                    }
                    EventBus.getDefault().postSticky(new FollowEvenModel(liveId+"",true));
                    FollowPop.this.dismiss();
                }

                @Override
                public void onFailed(String msg) {
                }
            });
        }
    }

    /**
     * ???????????????????????????pop?????????????????????
     * @param view
     */
    @Override
    public void onSureClick(View view) {
        TextView sure_tv = (TextView)view;
        String sureContent = sure_tv.getText().toString();
        if(sureContent.equals(Utils.getString(R.string.???????????????))){
            businessTipPop.dismiss();
            //??????giftPop
            OpenGiftPop();

        }else {
            //????????????????????????
            Utils.copyStr("anchorBusinessCard",anchorBusinessCard);
            businessTipPop.dismiss();
        }

    }

    /**
     * ????????????pop
     */
    private void OpenGiftPop() {
        if(fragment instanceof LiveFragment){
            LiveFragment liveFragment = (LiveFragment) fragment;
            cusGiftPop = new CusGiftPop(fragment.getActivity(), fragment, 1, mLiveData,liveFragment.giftSvgaMage,isUseToy);
            cusGiftPop.showAtLocation(liveFragment.giftView, Gravity.BOTTOM, 0, 0);
        }

    }
}
